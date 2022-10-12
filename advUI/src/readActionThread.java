import java.util.ArrayList;
import java.util.List;

public class readActionThread implements Runnable{
    public AnimationPanel animation;
    public dropPanel parent;
    public int blockIter;


    public readActionThread(AnimationPanel animation, dropPanel parent){
        this.animation=animation;
        this.parent=parent;
        this.blockIter = 0;

    }
    @Override
    public void run() {
        Character character = animation.character;
        List<String> loop = new ArrayList<>();
        boolean isNextIf = false;
        boolean loopFlag = false;

        while (!parent.getActions().isEmpty() && !Thread.currentThread().isInterrupted()) {
            synchronized (parent) {
                String actionCall = parent.getActions().get(0);
                parent.getCells().get(blockIter).setHighlight(true);

                if (parent.getActions().size() != 1) isNextIf = parent.getActions().get(1) == "If";

                String action = actionCall.split(" ")[0];

                if (action.equalsIgnoreCase("For")) {
                    loopFlag = true;
                    loop.add(actionCall.split(" ")[1]);
                }

                if (loopFlag) {
                    loopFlag = false;
                    loop.add(action);
                    readAction(loop);
                } else {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                    readAction(action, character, isNextIf);
                    parent.notify();
                    try {
                        parent.wait();
                        if (!parent.getCells().isEmpty()) {
                            parent.getCells().get(blockIter).setHighlight(false);
                        }
                        blockIter++;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    parent.getActions().remove(0);

                }
            }

        }
        synchronized (parent) {
            parent.notify();
        }
        parent.clearAll();
        parent.repaint();
        parent.animation.endOfLevelMessage();

    }


    private void readAction(String action, Character character, boolean isNextIf){
            if (action.equalsIgnoreCase("Move")) {
                character.move(isNextIf);
            } else if (action.equalsIgnoreCase("Turn")) {
                character.turn();
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
