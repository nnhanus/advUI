import java.util.ArrayList;

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
//        System.out.println("hi paint");
        while(!parent.getModel().actionList.isEmpty() && !Thread.currentThread().isInterrupted()){

            synchronized (parent) {
                parent.getModel().cells.get(blockIter).highlight=true;
                parent.repaint();
                animation.repaint();
                parent.notify();
                try {
                    parent.wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                blockIter++;
            }
        }
    }
}
