import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class TopPanel extends JPanel implements MouseListener, MouseMotionListener {
    protected static List<BlockControl> buttonList=new ArrayList<>();
    public PlayingPanel container;
    int level;
    int colCount;
    GridBagConstraints gbc;
    public TopPanel(PlayingPanel parent) {
        container=parent;
        this.setMinimumSize(new Dimension(container.getWidth(),Math.round(container.getHeight()/3)));
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        resetBtns();
        //could create array of all button types. for i in 0 to level-1 create button
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }


    public void resetBtns() {
        this.removeAll();
        repaint();
        level=GameWindow.getLevelNumber();
        colCount= Math.min(level,5);
        for (int i = 0; i < colCount ; i++) {
            gbc.gridx = i%colCount;
            gbc.gridy = Math.floorDiv(i,colCount);
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
            }
            else{block.setBorder(new EmptyBorder(0,0,0,0));}
        }
    }
}
