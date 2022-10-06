import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameWindowPresentation{

    public GameWindow control;

    public AnimationPanel animation;

    public PlayingPanel playingZone;

    public GameWindowPresentation(GameWindow controller){
        control=controller;
        animation = new AnimationPanel(control);
        //menuBar();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.add(animation);

        playingZone = new PlayingPanel(control);
        mainPanel.add(playingZone);

        control.add(mainPanel, BorderLayout.CENTER);


    }

    /**private void menuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem restart = new JMenuItem("Restart");
        restart.addActionListener( e -> control.changeLevel(control.getLevelNumber()));
        menu.add(restart);

        levelMenu(menu);

        JMenuItem quit = new JMenuItem("Quit");
        menu.add(quit);
        quit.addActionListener( e -> System.exit(0) );

        menuBar.add(menu);
        control.add(menuBar, BorderLayout.NORTH);
    }**/

    /**private void levelMenu(JMenu menu) {

        List<JRadioButton> levelOptions=new ArrayList<>();
        JRadioButton levelOne = new JRadioButton("Level 1");
        levelOne.addActionListener( e -> control.changeLevel(1));
        levelOptions.add(levelOne);
        JRadioButton levelTwo = new JRadioButton("Level 2");
        levelTwo.addActionListener( e -> control.changeLevel(2));
        levelOptions.add(levelTwo);
        JRadioButton levelThree = new JRadioButton("Level 3");
        levelThree.addActionListener( e -> control.changeLevel(3));
        levelOptions.add(levelThree);
        JRadioButton levelFour = new JRadioButton("Level 4");
        levelFour.addActionListener( e -> control.changeLevel(4));
        levelOptions.add(levelFour);
        JRadioButton levelFive = new JRadioButton("Level 5");
        levelFive.addActionListener( e -> control.changeLevel(5));
        levelOptions.add(levelFive);
        JRadioButton levelSix = new JRadioButton("Level 6");
        levelSix.addActionListener( e -> control.changeLevel(6));
        levelOptions.add(levelSix);

        ButtonGroup buttonGroup = new ButtonGroup();
        JMenu levels = new JMenu("Levels");

        for(JRadioButton levelBtn: levelOptions){
            levels.add(levelBtn);
            buttonGroup.add(levelBtn);
            if(levelOptions.indexOf(levelBtn)==control.getLevelNumber()-1){levelBtn.setSelected(true);}
        }

        menu.add(levels);
    }**/

}
