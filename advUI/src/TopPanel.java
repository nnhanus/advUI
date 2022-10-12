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
    GridBagConstraints gbc;
    BlockControl inBlock;
    Timer timer;
    JLabel helper= new JLabel(" ");
    public TopPanel(PlayingPanel parent) {
        container=parent;
        this.setMinimumSize(new Dimension(container.getWidth(),Math.round(container.getHeight()/3)));
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.insets=new Insets(2,0,30,0);
        resetBtns();
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.insets=new Insets(2,0,0,0);
        gbc.anchor=GridBagConstraints.WEST;
        //gbc.fill=GridBagConstraints.HORIZONTAL;
        helper.setIcon(new ImageIcon(new ImageIcon("advUI/Icons/info.png").getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT)));
        helper.setHorizontalTextPosition(JLabel.RIGHT);
        //helper.setVerticalTextPosition(JLabel.BOTTOM);
        this.add(helper,gbc);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

    }


    public void resetBtns() {
        this.removeAll();
        repaint();
        level=GameWindow.getLevelNumber();
        colCount= Math.min(level,5);
        for (int i = 0; i < colCount ; i++) {
            gbc.gridx = i;
            gbc.gridy = 0;
            BlockControl controlBtn = new BlockControl(i,this);
            buttonList.add(controlBtn);
            this.add(controlBtn,gbc);
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
                helper.setText(" ");
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
                    addHelper(block, (TopPanel) e.getComponent());
                }
//                MouseEvent enterBlock= new MouseEvent(block, MouseEvent.MOUSE_ENTERED,System.currentTimeMillis()+10,MouseEvent.NOBUTTON,SwingUtilities.convertPoint(this,point,block).x,SwingUtilities.convertPoint(this,point,block).y,0,false);
//                block.dispatchEvent(enterBlock);
            }
            else {
                block.setBorder(new EmptyBorder(0, 0, 0, 0));
//                MouseEvent exitBlock = new MouseEvent(block, MouseEvent.MOUSE_EXITED, System.currentTimeMillis() + 10, MouseEvent.NOBUTTON, SwingUtilities.convertPoint(this, point, block).x, SwingUtilities.convertPoint(this, point, block).y, 0, false);
//                block.dispatchEvent(exitBlock);
                if(timer!=null){timer.stop();}
                inBlock=null;
                helper.setText(" ");

            }
        }
    }

    private void addHelper(BlockControl block, TopPanel parent) {
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("here");
                helper.setText(block.getDescription());
            }
        });
        timer.setRepeats(false);
        timer.start();

    }
}
