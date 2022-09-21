import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class dropPanelModel {
    public List<String> actionList;

    public JLabel play = new JLabel(new ImageIcon(new ImageIcon("advUI/Icons/play.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
    public JLabel redo = new JLabel(new ImageIcon(new ImageIcon("advUI/Icons/redo.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
    public JPanel dropField;

    public dropPanelModel(){
        actionList = new ArrayList<String>() {};
    }

    public void addAction(String action) {
        actionList.add(action);
    }
    public void clearList (){
        actionList=new ArrayList<String>();
    }
}
