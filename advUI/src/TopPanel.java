import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class TopPanel extends JPanel implements MouseListener, MouseMotionListener {
    protected static List<BlockControl> buttonList=new ArrayList<>();
    public PlayingPanel container;
    int level;
    int colCount;
    BlockControl inBlock;
    Timer timer;

    public TopPanel(PlayingPanel parent) {
        container=parent;
        this.setMinimumSize(new Dimension(container.getWidth(),Math.round(container.getHeight()/3)));
        resetBtns();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

    }


    public void resetBtns() {
        this.removeAll();
        repaint();
        level=GameWindow.getLevelNumber();
        colCount= Math.min(level,5);
        for (int i = 0; i < colCount ; i++) {
            BlockControl controlBtn = new BlockControl(i,this);
            buttonList.add(controlBtn);
            this.add(controlBtn);
        }

    }
    public PlayingPanel getContainer(){
        return container;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (BlockControl block: buttonList){
            if ( block.contains(SwingUtilities.convertPoint(this,e.getPoint(),block))){
                if(timer!=null){timer.stop();}
                inBlock=null;
                container.getContainer().setHelperText(" ");
                block.dispatchEvent(e);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point point=e.getPoint();
        for (BlockControl block: buttonList){
            if (block.contains(SwingUtilities.convertPoint(this,point,block))){
                block.setBorder(new LineBorder(Color.GREEN));
                if(block!=inBlock){
                    inBlock=block;
                    addHelper(block);
                }
//                MouseEvent enterBlock= new MouseEvent(block, MouseEvent.MOUSE_ENTERED,System.currentTimeMillis()+10,MouseEvent.NOBUTTON,SwingUtilities.convertPoint(this,point,block).x,SwingUtilities.convertPoint(this,point,block).y,0,false);
//                block.dispatchEvent(enterBlock);
            }
            else {
                if(block==inBlock){
                    block.setBorder(new EmptyBorder(0, 0, 0, 0));
                    inBlock=null;
                    if(timer!=null){timer.stop();}
                    container.getContainer().setHelperText(" ");
                }
//                MouseEvent exitBlock = new MouseEvent(block, MouseEvent.MOUSE_EXITED, System.currentTimeMillis() + 10, MouseEvent.NOBUTTON, SwingUtilities.convertPoint(this, point, block).x, SwingUtilities.convertPoint(this, point, block).y, 0, false);
//                block.dispatchEvent(exitBlock);


            }
        }
    }

    private void addHelper(BlockControl block) {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                container.getContainer().setHelperText(block.getDescription());
            }
        });
        timer.setRepeats(false);
        timer.start();

    }
}
