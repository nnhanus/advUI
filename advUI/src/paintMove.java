public class paintMove implements Runnable{

    public AnimationPanel animation;
    public dropPanel parent;

    public paintMove(AnimationPanel animation, dropPanel parent){
        this.animation=animation;
        this.parent =parent;
    }
    @Override
    public void run() {
        dropPanelModel model = this.parent.getModel();
        while(!model.actionList.isEmpty()){
//            try {
//                this.wait();
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
            animation.repaint();
            notify();
        }
    }
}
