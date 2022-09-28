import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.stream.IntStream;

public class GameWindow  extends JFrame {

    public static GameWindowPresentation view;
    public static GameWindowModel model;

    public GameWindow(){
        super("Scoop Recoup");
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.model = new GameWindowModel();
        setLevel(1);
        this.view = new GameWindowPresentation(this);

        setPreferredSize(new Dimension(model.Width, model.Height));

        setVisible(true);
        pack();
    }

    public static int getLevel() {
        return model.getLevel();
    }

    public static int getDefaultHeight() {
        return model.Height;
    }

    public static int getDefaultWidth() {
        return model.Width;
    }

    public void levelSelect() {
        Integer[] availableLevels = new Integer[model.unlockedLevel];
        Arrays.setAll(availableLevels, i -> i + 1);
        int choice=JOptionPane.showOptionDialog(this,"Select a level","Menu",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,null,availableLevels,0);
        setLevel(choice+1);
        this.repaint();
    }

    public void setLevel(int newLevel) {
        model.setLevel(newLevel);
    }
    public static AnimationPanel getAnimation(){
        return view.animation;
    }

}
