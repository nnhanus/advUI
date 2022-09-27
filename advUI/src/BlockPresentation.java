

import java.awt.*;

import javax.swing.*;

public class BlockPresentation  {
    public BlockControl control;

    public BlockPresentation(BlockControl parent) {
        control=parent;
    }

    public Dimension getPreferredSize() {
        return new Dimension(75,60);
    }
}
