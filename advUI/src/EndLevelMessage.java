import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

/**
 * End of level pop up
 */
public class EndLevelMessage extends JDialog {
    GameWindow parent;
    ImageIcon icon = null;
    JLabel messageP1 = new JLabel();
    JLabel messageP2 = new JLabel();


    /**
     * Constructor
     * @param owner owner
     * @param type win or fail
     */
    public EndLevelMessage(GameWindow owner, String type){
        super(owner);
        parent = owner;

        setVisible(false);
        setLayout(new BorderLayout());

        if (type.equalsIgnoreCase("win")) {
            makeWin();
        } else {
            makeFail();
        }

        JPanel image = new JPanel();
        image.setBackground(Main.bgColor);
        image.add(new JLabel(icon));
        image.setBorder(new EmptyBorder(new Insets(40, 10, 10, 40)));

        messageP1.setFont(Main.getFontPlain(30));
        messageP2.setFont(Main.getFontPlain(24));

        JPanel buttonsPanel;
        if (GameWindow.getLevelNumber() == 5 && type.equalsIgnoreCase("win")){ //final level doesn't have the same options if won
            buttonsPanel = makeButtonsEnd();
        } else {
            buttonsPanel = buttonsPanel(type);
        }

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Main.bgColor);
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(messageP1, BorderLayout.NORTH);
        rightPanel.add(messageP2, BorderLayout.CENTER);
        rightPanel.add(buttonsPanel, BorderLayout.SOUTH);
        rightPanel.setBorder(new EmptyBorder(new Insets(20, 0, 20, 10)));

        add(image, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        pack();

        this.setLocation(parent.getWidth()/2-this.getWidth()/2,parent.getHeight()/2-this.getHeight()/2); //puts it in the middle of the owner
        setVisible(true);
    }

    /**
     * Creates the buttons if the last level was won
     * @return panel with the buttons
     */
    private JPanel makeButtonsEnd() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Main.bgColor);
        buttonsPanel.setBorder(new EmptyBorder(20, 0, 10, 0));

        JButton mainMenu = new JButton("Main Menu");
        JButton quit = new JButton("Quit");

        mainMenu.setFont(Main.getFontPlain(18));
        quit.setFont(Main.getFontPlain(18));

        mainMenu.addActionListener( e -> {
            new MainMenu(5, GameWindow.view.animation.character.getPath());
            parent.dispose();
            this.dispose();
        });
        quit.addActionListener( e -> System.exit(0));

        buttonsPanel.add(mainMenu);
        buttonsPanel.add(quit);

        return buttonsPanel;
    }

    /**
     * Creates the buttons
     * @param type win or fail
     * @return panel with buttons
     */
    private JPanel buttonsPanel(String type) {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(Main.bgColor);
        buttonsPanel.setBorder(new EmptyBorder(20, 0, 10, 0));

        JButton yes = new JButton("Yes");
        JButton no = new JButton("No");

        yes.setFont(Main.getFontPlain(18));
        no.setFont(Main.getFontPlain(18));

        yes.addActionListener(
                e -> {
                    if (type.equalsIgnoreCase("win")){
                        parent.setLevel(GameWindow.getLevelNumber()+1);
                    }
                    parent.changeLevel(GameWindow.getLevelNumber());
                    dispose();
                });
        no.addActionListener(
                e -> {
                    new PopUpMenu(parent);
                    dispose();
                });

        buttonsPanel.add(yes);
        buttonsPanel.add(no);

        return buttonsPanel;
    }

    private void makeWin() {
        makeIcon("Win");
        String soundName;

        if(GameWindow.getLevelNumber()==5){
            //play end of game icecream jingle
            soundName = "advUI/Resources/Sounds/end_game.wav";
            messageP1.setText("Congratulations!");
            messageP2.setText("You did it! ");
        } else {
            //play happy sounds
            soundName = "advUI/Resources/Sounds/win_tone.wav";
            parent.setLevelUnlocked(GameWindow.getLevelNumber() + 1);
            messageP1.setText("Congratulations!");
            messageP2.setText("Ready for the next level?");
        }
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private void makeFail(){
        makeIcon("Fail");
        messageP1.setText("So close!");
        messageP2.setText("Want to try again?");
        //play sad sounds
        String soundName = "advUI/Resources/Sounds/lose_tone.wav";
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }catch(Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Gets the right image and sets it as the icon
     * @param type win or fail
     */
    private void makeIcon(String type){
        String characterPath = GameWindow.view.animation.character.getPath();
        String[] split = characterPath.split("/");
        String[] split2 = split[2].split("\\.");
        String character = split2[0];
        String path = "advUI/Resources/Icons/" + character + type + ".png";
        icon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(100, 90, Image.SCALE_SMOOTH));
    }
}
