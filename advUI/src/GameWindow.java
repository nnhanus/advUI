import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class GameWindow  extends JFrame {

    public static GameWindowPresentation view;
    public static GameWindowModel model;

    public GameWindow(){
        super("Scoop Recoup");
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.model = new GameWindowModel();
        this.view = new GameWindowPresentation(this);

        setPreferredSize(new Dimension(model.Width, model.Height));
        setVisible(true);
        pack();
    }

    public static int getLevelNumber() {
        return model.getLevelNumber();
    }

    public static Level getLevel(){
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
        changeLevel(choice+1);
    }

    public void setLevel(int newLevel) {
        model.setLevel(newLevel);
    }

    public static AnimationPanel getAnimation(){
        return view.animation;
    }

    public void changeLevel(int levelNumber){
        if( levelNumber>model.unlockedLevel){
            JDialog lockedLevel = new JDialog(this, "Locked Level");
            lockedLevel.add(new JTextArea("You do not have access to this level.\nPlay all the levels before to unlock it."));
            lockedLevel.setSize(250,100);
            lockedLevel.setLocation(this.getWidth()/2,this.getHeight()/2);
            lockedLevel.setVisible(true);
            levelNumber=getLevelNumber();
        }
        setLevel(levelNumber);
        getAnimation().changeLevel(getLevel());
        view.playingZone.topPanel.resetBtns();
        view.playingZone.
        revalidate();
        repaint();
    }

    public void setLevelUnlocked(int levelUnlocked) {
        model.unlockedLevel=levelUnlocked;
    }


}
