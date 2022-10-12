

import java.awt.*;

import javax.swing.*;

public class BlockPresentation  {
    public BlockControl control;

    public BlockPresentation(BlockControl parent) {
        control=parent;
        control.setIcon(control.getIcon());
        control.setText(control.getType());
        control.setFont(new Font("Ariel", Font.BOLD, 14));
        control.setForeground(Color.white);
        control.setHorizontalTextPosition(JLabel.CENTER);
    }

    public Dimension getPreferredSize() {
        return new Dimension(100,90);
    }



}
