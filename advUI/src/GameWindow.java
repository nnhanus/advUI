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
        if(tutorialOn){ instructionWindow tutorial= new instructionWindow(this);}
    }

    public GlassPaneWrapper getGlassPanel(){
        return view.glassPanel;
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


//    public void levelSelect() {
//        Integer[] availableLevels = new Integer[model.unlockedLevel];
//        Arrays.setAll(availableLevels, i -> i + 1);
//        int choice=JOptionPane.showOptionDialog(this,"Select a level","Menu",
//                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,null,availableLevels,0);
//        changeLevel(choice+1);
//    }

    public void setLevel(int newLevel) {
        model.setLevel(newLevel);
    }

    public static AnimationPanel getAnimation(){
        return view.animation;
    }

    public void changeLevel(int levelNumber){
        view.playingZone.getBottomPanel().mouseEvent = false;
        Color BGColor = new Color(0xFED1FF);
        if( levelNumber>model.unlockedLevel){
            levelNumber=getLevelNumber();
        }
        setLevel(levelNumber);
        getAnimation().changeLevel(getLevel(), getAnimation().character.getPath());
        view.playingZone.topPanel.resetBtns();
        view.playingZone.revalidate();
        repaint();
        //if(tutorialOn){ instructionWindow tutorial = new instructionWindow(this);}
    }

    public void setLevelUnlocked(int levelUnlocked) {
        model.unlockedLevel=levelUnlocked;
    }

    public void toggleTutorial(){
        tutorialOn=!tutorialOn;
    }


}
