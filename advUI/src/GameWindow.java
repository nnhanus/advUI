import javax.swing.*;
import java.awt.*;

public class GameWindow  extends JFrame {

    public static GameWindowPresentation view;
    public static GameWindowModel model;

    public GameWindow(){
        super("Scoop Recoup");
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.model = new GameWindowModel();
        setLevel(3);
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

    public static void setLevel(int newLevel) {
        model.setLevel(newLevel);
    }
    public static AnimationPanel getAnimation(){
        return view.animation;
    }

}
