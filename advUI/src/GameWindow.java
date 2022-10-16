import javax.swing.*;
import java.awt.*;

public class GameWindow  extends JFrame {

    public static GameWindowPresentation view;
    public static GameWindowModel model;
    boolean tutorialOn;

    public GameWindow(int level, boolean tutorialOn, String character){
        super("Scoop Recoup");
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.model = new GameWindowModel(level);
        this.view = new GameWindowPresentation(this, character);
        this.tutorialOn=tutorialOn;
        setPreferredSize(new Dimension(model.Width, model.Height));
        setVisible(true);
        pack();
        if(tutorialOn) {
            tutorialWindow tutorial = new tutorialWindow(this);
        }

    }

    public GlassPaneWrapper getGlassPanel(){
        return view.glassPanel;
    }
    public void setHelperText(String s) {
        view.setHelperText(s);
    }

    public static int getLevelNumber() {
        return model.getLevelNumber();
    }

    public static Level getLevel(){
        return model.getLevel();
    }

    public static int getUnlocked(){
        return model.unlockedLevel;
    }

    public void setLevel(int newLevel) {
        model.setLevel(newLevel);
    }

    public static AnimationPanel getAnimation(){
        return view.animation;
    }

    public void changeLevel(int levelNumber){
        view.changeLevel(levelNumber);
    }

    public void setLevelUnlocked(int levelUnlocked) {
        model.unlockedLevel=levelUnlocked;
    }

    public void toggleTutorial(){
        tutorialOn=!tutorialOn;
    }

}
