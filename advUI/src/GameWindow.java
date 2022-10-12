import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;

public class GameWindow  extends JFrame {

    public static GameWindowPresentation view;
    public static GameWindowModel model;

    public GameWindow(int level){
        super("Scoop Recoup");
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        this.model = new GameWindowModel(level);
        this.view = new GameWindowPresentation(this);

        setPreferredSize(new Dimension(model.Width, model.Height));
        setVisible(true);
        pack();
        instructionWindow tutorial= new instructionWindow(this);
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
        Color BGColor = new Color(0xFED1FF);
        if( levelNumber>model.unlockedLevel){
            JDialog lockedLevel = new JDialog(this, "Locked Level");
            lockedLevel.setLayout(new BorderLayout());
            JLabel locked=new JLabel(new ImageIcon(new ImageIcon("advUI/Icons/locked.png").getImage().getScaledInstance(350,300,Image.SCALE_DEFAULT)));
            lockedLevel.add(locked);
            JLabel message=new JLabel("<html>You need more training!\nComplete all previous levels to unlock.</html>");
            message.setFont(new Font("Bradley Hand",Font.PLAIN,18));
            message.setOpaque(true);
            message.setBackground(BGColor);
            lockedLevel.add(message,BorderLayout.SOUTH);
            lockedLevel.setSize(350,350);
            lockedLevel.setLocation(this.getWidth()/2,this.getHeight()/2);
            lockedLevel.setVisible(true);
            levelNumber=getLevelNumber();
        }
        setLevel(levelNumber);
        getAnimation().changeLevel(getLevel());
        view.playingZone.topPanel.resetBtns();
        view.playingZone.revalidate();
        repaint();
        instructionWindow tutorial = new instructionWindow(this);
    }

    public void setLevelUnlocked(int levelUnlocked) {
        model.unlockedLevel=levelUnlocked;
    }


}
