

import java.awt.*;

import javax.swing.*;

public class BlockPresentation  {
    public BlockControl control;
    int width=100;
    int height =90;

    public BlockPresentation(BlockControl parent) {
        control=parent;
        control.setIcon(control.getIcon());
        control.setText(control.getType());
        control.setFont(new Font("Ariel", Font.BOLD, 14));
        control.setForeground(Color.white);
        control.setHorizontalTextPosition(JLabel.CENTER);
    }

    public Dimension getPreferredSize() {
        return new Dimension(width,height);
    }

    public void changeSize(int width, int height){
        this.width=width;
        this.height=height;
    }



}
