import java.util.ArrayList;
import java.util.List;

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
        //cellRectangle cellToLight = parent.getCells().get(blockIter);
        int inForLoop = 0;
        cellRectangle cellFor = null;

        parent.mouseEvent = true;


        while (!parent.getActions().isEmpty() && !Thread.currentThread().isInterrupted()) {
            synchronized (parent) {
                String actionCall = parent.getActions().get(0);
                parent.mouseEvent = true;

                if (parent.getActions().size() != 1) isNextIf = parent.getActions().get(1).equalsIgnoreCase("If ");
                //if (parent.getActions().size() != 1) isNextIf = parent.getActions().get(1) == "If"; //is an if the next action block

                String action = actionCall.split(" ")[0];

                if (action.equalsIgnoreCase("For")) { //is it a for loop
                    loopFlag = true;
                    loop.add(actionCall.split(" ")[1]);
                }

                if (loopFlag) { // If it is a foor loop
                    loopFlag = false;
                    loop.add(action);
                    inForLoop = Integer.parseInt(loop.get(0)); //how many actions in the loop
                    readAction(loop);
                    cellFor = parent.getCells().get(blockIter); //the "for block" cell keeps lighting up
                    //cellFor.setReadHighlight(true); //
                    blockIter++; //the next one is the cell to light
                    parent.mouseEvent = true;
                   // parent.getCells().get(blockIter).setReadHighlight(true);
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
        parent.mouseEvent = false;
        parent.clearAll();
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
