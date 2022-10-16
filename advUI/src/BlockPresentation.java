

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class BlockPresentation  {
    public BlockControl control;
    int width=100;
    int height =90;

    public BlockPresentation(BlockControl parent) {
        control=parent;
        control.setIcon(control.getIcon());

    }

    public Dimension getPreferredSize() {
        return new Dimension(width,height);
    }

    public void changeSize(int width, int height){
        this.width=width;
        this.height=height;
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
}
