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
        while(!parent.getActions().isEmpty() && !Thread.currentThread().isInterrupted()){

            synchronized (parent) {
                parent.mouseEvent = true;
                parent.repaint();
                animation.repaint();
                parent.notify();
                try {
                    parent.wait();
                    parent.mouseEvent = false;
                } catch (InterruptedException e) {
                    parent.mouseEvent = false;
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
