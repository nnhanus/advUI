import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel with the blocks to drag
 */
public class TopPanel extends JPanel implements MouseListener, MouseMotionListener {
    protected static List<BlockControl> buttonList=new ArrayList<>();
    public PlayingPanel container;
    int level;
    int colCount;
    BlockControl inBlock;
    Timer timer;
    Point spinnerPos;

    JPanel blockPanel = new JPanel();
    public TopPanel(PlayingPanel parent) {
        container=parent;
        this.setMinimumSize(new Dimension(container.getWidth(),Math.round(container.getHeight()/3)));
        this.setLayout(new BoxLayout(this,BoxLayout.PAGE_AXIS));
        this.add(Box.createRigidArea(new Dimension(0,15)));
        this.add(blockPanel);
        resetBtns();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void resetBtns() {
        blockPanel.removeAll();
        level=GameWindow.getLevelNumber();
        colCount= Math.min(level,4);
        for (int i = 0; i < colCount ; i++) {
            BlockControl controlBtn = new BlockControl(i,this);
            buttonList.add(controlBtn);
            blockPanel.add(controlBtn);
            if (i == 2){
                spinnerPos = controlBtn.getLocation();
            }
        }
        blockPanel.repaint();
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
    public void mouseMoved(MouseEvent e) {
        Point point=e.getPoint();
        for (BlockControl block: buttonList){
            if (block.contains(SwingUtilities.convertPoint(this,point,block))){
                block.highlightOn();
                if(block!=inBlock){
                    inBlock=block;
                    addHelper(block); //add description of block on hover
                }
            }
            else {
                if(block==inBlock){
                    block.highlightOff();
                    inBlock=null;
                    if(timer!=null){timer.stop();}
                    container.getContainer().setHelperText(" ");
                }
            }
        }
    }

    /**
     * On hover on a block, display a short description of the block
     * @param block the block to describe
     */
    private void addHelper(BlockControl block) {
        //wait to make sure user is hovering on block before showing description
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                container.getContainer().setHelperText(block.getDescription());
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public List<BlockControl> getButtonList() {
        return buttonList;
    }


    //Unused MouseListener methods
    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}
}
