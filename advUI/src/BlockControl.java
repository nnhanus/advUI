
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
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
        model = new BlockModel(controlNum, this);
        view = new BlockPresentation(this);
        this.addMouseListener(this);

    }
    public String getDescription(){
      System.out.println(model.getHelper());
        return model.getHelper();
    }
    public String getType(){return model.getType();}
    public ImageIcon getIcon(){return model.getIcon();}
    public void setForLoopIter(int iterNum){
        model.setForLoopIter(Integer.toString(iterNum));
    }
    @Override
    public Dimension getPreferredSize(){
        return view.getPreferredSize();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = this.getIcon().getImage();
        Cursor c = toolkit.createCustomCursor(image , new Point(0,0), "block img");
        container.getContainer().getContainer().setCursor (c);
        container.getContainer().selectedBlock=this;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       container.getContainer().getBottomPanel().dispatchEvent(e);

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    public BufferedImage getIconAsImage() {
        return model.getIconAsImage();
    }

    public String getForLoopIter() {
        return model.getForLoopIter();
    }


}
