import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    public static int Height = 600;
    public static int Width = 1000;
    public AnimationPanel animation;

    public GameWindow(){
        super("Ice Cream Test");
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setPreferredSize(new Dimension(Width, Height));
        setVisible(true);
        BlockModel.setLevel(1);

        menuBar();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));

        animation = new AnimationPanel();
        mainPanel.add(animation);

        PlayingPanel playingZone = new PlayingPanel();
        mainPanel.add(playingZone);

        this.add(mainPanel, BorderLayout.CENTER);

        pack();

    }

    private void menuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem restart = new JMenuItem("Restart");
        menu.add(restart);

        levelMenu(menu);

        JMenuItem quit = new JMenuItem("Quit");
        menu.add(quit);
        quit.addActionListener( e -> System.exit(0) );

        menuBar.add(menu);
        this.add(menuBar, BorderLayout.NORTH);
    }

    private void levelMenu(JMenu menu) {
        JMenu levels = new JMenu("Levels");
        JRadioButton levelOne = new JRadioButton("Level 1");
        levels.add(levelOne);
        JRadioButton levelTwo = new JRadioButton("Level 2");
        levels.add(levelTwo);
        JRadioButton levelThree = new JRadioButton("Level 3");
        levels.add(levelThree);
        JRadioButton levelFour = new JRadioButton("Level 4");
        levels.add(levelFour);
        JRadioButton levelFive = new JRadioButton("Level 5");
        levels.add(levelFive);
        JRadioButton levelSix = new JRadioButton("Level 6");
        levels.add(levelSix);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(levelOne);
        buttonGroup.add(levelTwo);
        buttonGroup.add(levelThree);
        buttonGroup.add(levelFour);
        buttonGroup.add(levelFive);
        buttonGroup.add(levelSix);

        levelOne.setSelected(true);

        menu.add(levels);
    }

}
