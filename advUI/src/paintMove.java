import java.util.ArrayList;

public class paintMove implements Runnable{

    public AnimationPanel animation;
    public dropPanel parent;

    public paintMove(AnimationPanel animation, dropPanel parent){
        this.animation=animation;
        this.parent=parent;
    }
    @Override
    public void run() {
        System.out.println("hi paint");
        while(!parent.getModel().actionList.isEmpty() && !Thread.currentThread().isInterrupted()){
            synchronized (parent) {
                animation.repaint();
                parent.notify();
                try {
                    parent.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
