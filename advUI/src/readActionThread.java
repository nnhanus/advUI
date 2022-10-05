import java.util.ArrayList;
import java.util.List;

public class readActionThread implements Runnable{
    public AnimationPanel animation;
    public dropPanel parent;
    public readActionThread(AnimationPanel animation, dropPanel parent){
        this.animation=animation;
        this.parent =parent;
    }
    @Override
    public void run() {

        Character character = animation.character;
        List<String> loop = new ArrayList<>();
        boolean isNextIf = false;
        boolean loopFlag = false;
        dropPanelModel model = this.parent.getModel();
        while(!model.actionList.isEmpty()){

            String actionCall = model.actionList.get(0);
            if (model.actionList.size()!=1) isNextIf = model.actionList.get(1) == "If";
            String action = actionCall.split(" ")[0];
            if (action.equalsIgnoreCase("For")) {
                loopFlag = true;
                loop.add(actionCall.split(" ")[1]);
            } else if (loopFlag) {
                loopFlag = false;
                loop.add(action);
                readAction(loop, character, isNextIf);
                this.notify();
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            } else {
                readAction(action, character, isNextIf);
                this.notify();
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            model.actionList.remove(0);
        }
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
        private void readAction(List<String> loop, Character character, boolean isNextIf){
            int iter=Integer.parseInt(loop.get(0));
            String action=loop.get(1);
            if (action.equalsIgnoreCase("Move")) {
                for(int i =0;i<iter;i++){
                    character.move(isNextIf);
                    animation.revalidate();
                    animation.repaint();
                }
            } else if (action.equalsIgnoreCase("Turn")) {
                for(int i =0;i<iter;i++){
                    character.turn();
                    animation.revalidate();
                    animation.repaint();
                }
            }

    }

}
