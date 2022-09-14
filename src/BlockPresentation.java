

import java.awt.*;
import javax.swing.*;

public class BlockPresentation {

    public BlockPresentation(BlockControl block) {

        block.setIcon(block.model.icon);
        block.setText(BlockModel.checkType(block.getIndex()));
        block.setHorizontalTextPosition(JLabel.CENTER);
    }
}
