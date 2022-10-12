public class paintMove implements Runnable{

    public AnimationPanel animation;
    public dropPanel parent;
    public int blockIter;

    public paintMove(AnimationPanel animation, dropPanel parent){
        this.animation=animation;
        this.parent=parent;
        this.blockIter = 0;
    }
    @Override
    public void run() {
        while(!parent.getActions().isEmpty()){
            synchronized (parent) {
                try {
                    parent.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                parent.repaint();
                animation.repaint();
                parent.notify();
            }
        }
    }
}
