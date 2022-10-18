/**
 * Thread to handle the repaint while the actions are being read
 */
public class paintMove implements Runnable{

    public AnimationPanel animation;
    public dropPanel parent;

    public paintMove(AnimationPanel animation, dropPanel parent){
        this.animation=animation;
        this.parent=parent;
    }

    @Override
    public void run() {
        while(!parent.getActions().isEmpty()){
            synchronized (parent) {
                try {
                    parent.wait(); //Wait for the readActionThread to notify
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                parent.repaint();
                animation.repaint();
                parent.notify(); //Notifies the readActionThread
            }
        }
    }
}
