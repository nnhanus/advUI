import java.util.ArrayList;
import java.util.List;

/**
 * Thread to execute the actions one by one
 */
public class readActionThread implements Runnable{
    public AnimationPanel animation;
    public dropPanel parent;

    public readActionThread(AnimationPanel animation, dropPanel parent){
        this.animation=animation;
        this.parent=parent;
    }

    @Override
    public void run() {
        Character character = animation.character;
        List<String> loop = new ArrayList<>();
        boolean isNextIf = false;
        boolean loopFlag = false;
        int blockIter = 0;
        int inForLoop = 0;
        cellRectangle cellFor = null;

        //parent.mouseEvent = true;


        while (!parent.getActions().isEmpty() && !Thread.currentThread().isInterrupted()) {
            synchronized (parent) {
                String actionCall = parent.getActions().get(0);
                //parent.mouseEvent = true;

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
                   // parent.mouseEvent = true;
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                    readAction(action, character, isNextIf);
                    parent.getCells().get(blockIter).setReadHighlight(true);
                    parent.notify();
                    try {
                        parent.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (inForLoop > 0){
                        cellFor.setReadHighlight(true);
                        inForLoop--;
                        if (inForLoop == 0){
                            blockIter++;
                        }
                    } else {
                        blockIter++;
                    }
                    parent.getActions().remove(0);
                }
            }

        }
        synchronized (parent) {
            try {
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
        //parent.mouseEvent = false;
        parent.clearAll();
        parent.buildGrid();
        parent.repaint();
        parent.animation.endOfLevelMessage();
    }


    private void readAction(String action, Character character, boolean isNextIf){
            if (action.equalsIgnoreCase("Move")) {
                character.move(isNextIf);
            } else if (action.equalsIgnoreCase("Turn")) {
                character.turn();
            }else if(action.equalsIgnoreCase("If")){
                character.ifStatement();
            }
            animation.revalidate();
            animation.repaint();
        }
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
