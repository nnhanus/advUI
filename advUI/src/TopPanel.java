import javax.swing.*;
import java.awt.*;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragSource;

public class TopPanel extends JPanel {

    protected GridBagConstraints gbc;
    public TopPanel() {
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        BlockModel.setLevel(2);
        //could create array of all button types. for i in 0 to level-1 create button
        for (int i = 1; i <= BlockModel.getLevel(); i++) {
            BlockControl controlBtn = new BlockControl(i);
            MyDragGestureListener dlistener = new MyDragGestureListener();
            DragSource ds = new DragSource();
            ds.createDefaultDragGestureRecognizer(controlBtn, DnDConstants.ACTION_COPY, dlistener);
            this.add(controlBtn, gbc);

        }
    }

    public void resetBtns() {
        repaint();
    }
}
