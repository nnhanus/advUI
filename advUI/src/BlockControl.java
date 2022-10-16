import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;


public class BlockControl extends JLabel implements MouseListener {
    public BlockModel model;
    int typeNum;
    public TopPanel container;
    private BlockPresentation view;
    boolean helper;

    public BlockControl (int controlNum, TopPanel parent) {
        container = parent;
        typeNum = controlNum;
        helper=false;
        model = new BlockModel(controlNum);
        view = new BlockPresentation(this);
        this.addMouseListener(this);
    }

    public BlockControl (BlockControl toCopy) {
        container = toCopy.container;
        typeNum = toCopy.getTypeNum();
        helper=false;
        model = new BlockModel(typeNum);
        view = new BlockPresentation(this);
        if(typeNum==2){
            this.setForLoopIter(Integer.parseInt(toCopy.getForLoopIter()));
        }
        this.addMouseListener(this);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        view.paint((Graphics2D) g);
    }

    public int getTypeNum(){
        return model.index;
    }
    public String getDescription(){
        return model.getHelper();
    }
    public void highlightOn(){
        view.highlightOn();
    }
    public void highlightOff(){
        view.highlightOff();
    }
    public String getType(){return model.getType();}
    public ImageIcon getIcon(){return model.getIcon();}
    public void setForLoopIter(int iterNum){
        model.setForLoopIter(Integer.toString(iterNum));
    }
    public BufferedImage getIconAsImage() {
        return model.getIconAsImage();
    }
    public String getForLoopIter() {
        return model.getForLoopIter();
    }

    @Override
    public Dimension getPreferredSize(){
        return view.getPreferredSize();
    }


    @Override
    public void mousePressed(MouseEvent e) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = this.getIcon().getImage();
        Cursor c = toolkit.createCustomCursor(image , new Point(0,0), "block img");
        if (typeNum == 2) {
            model.forLoopIter = String.valueOf((int) container.container.container.getGlassPanel().forCount.getValue());
        }
        container.getContainer().getContainer().setCursor (c);
        container.getContainer().selectedBlock=this;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       container.getContainer().getBottomPanel().dispatchEvent(e);
    }

    //Unused MouseListener methods
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}

}
