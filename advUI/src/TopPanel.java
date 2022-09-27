import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TopPanel extends JPanel {
protected static List<BlockControl> buttonList=new ArrayList<BlockControl>();
    public PlayingPanel container;
    public TopPanel(PlayingPanel parent) {
        container=parent;
        this.setMinimumSize(new Dimension(container.getWidth(),Math.round(container.getHeight()/3)));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        GameWindow.setLevel(2);
        int level=GameWindow.getLevel();
        int colCount= Math.min(level,5);
        //could create array of all button types. for i in 0 to level-1 create button
        for (int i = 0; i < level ; i++) {
            gbc.gridx = i%colCount;
            gbc.gridy = Math.floorDiv(i,colCount);
            BlockControl controlBtn = new BlockControl(i,this);
            buttonList.add(controlBtn);
            this.add(controlBtn);
        }
    }


    public void resetBtns() {
        repaint();
    }
}
