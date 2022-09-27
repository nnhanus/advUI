

import java.awt.*;

import javax.swing.*;

public class BlockPresentation  {
    public BlockControl control;

    public BlockPresentation(BlockControl parent) {
        control=parent;
        control.setIcon(control.getIcon());
        control.setText(BlockModel.checkType(control.getIndex()));
        control.setHorizontalTextPosition(JLabel.CENTER);
    }

    public Dimension getPreferredSize() {
        return new Dimension(75,60);
    }
}
