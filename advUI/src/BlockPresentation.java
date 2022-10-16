import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class BlockPresentation  implements MouseListener {
    public BlockControl control;
    int width=100;
    int height =90;

    public BlockPresentation(BlockControl parent) {
        control=parent;
        control.addMouseListener(this);
        control.setIcon(control.getIcon());
    }

    public Dimension getPreferredSize() {
        return new Dimension(width,height);
    }

    public void highlightOn() {
        control.setBorder(new LineBorder(Color.GREEN));
    }

    public void highlightOff() {
        control.setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    public void paint(Graphics2D g) {
        String name= control.getType();
        g.setFont(new Font("Ariel", Font.BOLD, 14));
        g.setColor(Color.white);
        g.drawString(name,(width- g.getFontMetrics().stringWidth(name))/2,height/3+10);
    }
    @Override
    public void mousePressed(MouseEvent e) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image image = control.getIcon().getImage();
        Cursor c = toolkit.createCustomCursor(image , new Point(0,0), "block img");
        if (control.typeNum == 2) {
            //if the selected block is a for block, capture the current value of the number spinner
            control.setForLoopIter((int) control.getContainer().getContainer().getContainer().getGlassPanel().forCount.getValue());
        }
        control.getContainer().getContainer().getContainer().setCursor (c);
        control.getContainer().getContainer().selectedBlock=control; //save the block info to pass to action reader
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        control.getContainer().getContainer().getBottomPanel().dispatchEvent(e);
    }

    //Unused MouseListener methods
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
}
