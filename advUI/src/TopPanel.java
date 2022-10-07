import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class TopPanel extends JPanel{
protected static List<BlockControl> buttonList=new ArrayList<>();
    public PlayingPanel container;
    int level;
    int colCount;
    GridBagConstraints gbc;
    public TopPanel(PlayingPanel parent) {
        container=parent;
        this.setMinimumSize(new Dimension(container.getWidth(),Math.round(container.getHeight()/3)));
        this.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        resetBtns();
        //could create array of all button types. for i in 0 to level-1 create button

    }


    public void resetBtns() {
        this.removeAll();
        repaint();
        level=GameWindow.getLevelNumber();
        colCount= Math.min(level,5);
        for (int i = 0; i < colCount ; i++) {
            gbc.gridx = i%colCount;
            gbc.gridy = Math.floorDiv(i,colCount);
            BlockControl controlBtn = new BlockControl(i,this);
            buttonList.add(controlBtn);
            this.add(controlBtn);
        }
    }

}
