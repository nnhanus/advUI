
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
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
    public BlockControl (int controlNum, TopPanel parent) {
        container = parent;
        typeNum = controlNum;
        model = new BlockModel(controlNum, this);
        view = new BlockPresentation(this);
        this.setIcon(getIcon());
        this.setText(model.name);
        this.setHorizontalTextPosition(JLabel.CENTER);
        this.addMouseListener(this);
        //use a switch to match action to button type
        // this.setTransferHandler(new ValueExportTransferHandler(controlNum));

    }
    public int getIndex (){
        return model.index;
    }
    public String getType(){return model.getType();}
    public ImageIcon getIcon(){return model.getIcon();}

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
        container.container.setCursor (c);
        container.container.selectedBlock=this;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       container.container.bottomPanel.dispatchEvent(e);

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.setBorder(new LineBorder(Color.YELLOW));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.setBorder(new EmptyBorder(0,0,0,0));

    }

    public BufferedImage getIconAsImage() {
        return model.getIconAsImage();
    }
}
