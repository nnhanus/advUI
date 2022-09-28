import javax.swing.*;
import java.awt.*;

public class GameWindowPresentation{

    public GameWindow control;

    public AnimationPanel animation;

    public PlayingPanel playingZone;

    public GameWindowPresentation(GameWindow controller){
        control=controller;
        animation = new AnimationPanel();
        menuBar();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.add(animation);

        playingZone = new PlayingPanel();
        mainPanel.add(playingZone);

        control.add(mainPanel, BorderLayout.CENTER);


    }

    private void menuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem restart = new JMenuItem("Restart");
        restart.addActionListener( e -> control.chargeLevel(control.getLevelNumber()));
        menu.add(restart);

        levelMenu(menu);

        JMenuItem quit = new JMenuItem("Quit");
        menu.add(quit);
        quit.addActionListener( e -> System.exit(0) );

        menuBar.add(menu);
        control.add(menuBar, BorderLayout.NORTH);
    }

    private void levelMenu(JMenu menu) {
        JMenu levels = new JMenu("Levels");
        JRadioButton levelOne = new JRadioButton("Level 1");
        levelOne.addActionListener( e -> control.chargeLevel(1));
        levels.add(levelOne);
        JRadioButton levelTwo = new JRadioButton("Level 2");
        levelTwo.addActionListener( e -> control.chargeLevel(2));
        levels.add(levelTwo);
        JRadioButton levelThree = new JRadioButton("Level 3");
        levelThree.addActionListener( e -> control.chargeLevel(3));
        levels.add(levelThree);
        JRadioButton levelFour = new JRadioButton("Level 4");
        levelFour.addActionListener( e -> control.chargeLevel(4));
        levels.add(levelFour);
        JRadioButton levelFive = new JRadioButton("Level 5");
        levelFive.addActionListener( e -> control.chargeLevel(5));
        levels.add(levelFive);
        JRadioButton levelSix = new JRadioButton("Level 6");
        levelSix.addActionListener( e -> control.chargeLevel(6));
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
