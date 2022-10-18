import java.util.ArrayList;
import java.util.List;

/**
 * Thread to execute the actions one by one
 */
public class readActionThread implements Runnable{
    public AnimationPanel animation;
    public final dropPanel parent;

    public readActionThread(AnimationPanel animation, dropPanel parent){
        this.animation=animation;
        this.parent=parent;
    }

    @Override
    public void run() {
        Character character = animation.character;
        List<String> loop = new ArrayList<>();
        boolean isNextIf = false; //checks if next action is if
        boolean loopFlag = false; //checks if the action is a loop
        int blockIter = 0;
        int inForLoop = 0; //number of iterations left in the for loop
        cellRectangle cellFor = null; //Cell being read that has to be lit up

        while (!parent.getActions().isEmpty() && !Thread.currentThread().isInterrupted()) {
            synchronized (parent) {
                String actionCall = parent.getActions().get(0); //Get the current action

                //Checks if the next action is if
                if (parent.getActions().size() != 1) isNextIf = parent.getActions().get(1).equalsIgnoreCase("If ");

                String action = actionCall.split(" ")[0];

                if (action.equalsIgnoreCase("For")) { //is it a for loop
                    loopFlag = true;
                    loop.add(actionCall.split(" ")[1]);
                }

                if (loopFlag) { // If it is a for loop
                    loopFlag = false;
                    loop.add(action);
                    inForLoop = Integer.parseInt(loop.get(0)); //number of actions in the loop
                    readAction(loop);
                    loop.clear();
                    cellFor = parent.getCells().get(blockIter); //the cell with the for block lights up
                    blockIter++; //the next cell that contains the action in the loop also lights up
                } else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    readAction(action, character, isNextIf);

                    parent.getCells().get(blockIter).setReadHighlight(true);

                    parent.notify(); //notify for the repaint
                    try {
                        parent.wait(); //wait for the repaint to be done
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    if (inForLoop > 0) { //if in a for loop
                        cellFor.setReadHighlight(true);
                        inForLoop--;
                        if (inForLoop == 0) {
                            blockIter++;
                        }
                    } else {
                        blockIter++;
                    }
                    parent.getActions().remove(0); //remove the action read
                }
            }
            try {
                Thread.sleep(500);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        parent.clearAll(); //clears the blocks on the drop panel
        parent.buildGrid(); //remakes the grid on the block panel
        parent.animation.endOfLevelMessage();
    }

    /**
     * Calls the right method for the action read
     * @param action the action to read
     * @param character the character that executes the action
     * @param isNextIf if the next block is an if block
     */
    private void readAction(String action, Character character, boolean isNextIf){
        if (action.equalsIgnoreCase("Move")) {
            character.move(isNextIf);
        } else if (action.equalsIgnoreCase("Turn")) {
            character.turn();
        }else if(action.equalsIgnoreCase("If")){
            character.ifStatement();
        }
    }

    /**
     * Reads the action in the loop
     * @param loop for + number of iterations + action
     */
    private void readAction(List<String> loop){
            int iter=Integer.parseInt(loop.get(0));
            String action=parent.getActions().get(1);
            parent.getActions().remove(1);
            parent.getActions().remove(0);
            for(int i =0;i<iter;i++) {
                parent.getActions().add(i, action);
            }
    }

}
