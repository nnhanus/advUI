import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Main menu of the game, what appears when the game is launched
 * Level select, character select, toggle tutorials
 */
public class MainMenu extends JDialog {

    MainMenuPresentation view;
    String mode = "slider"; //toggle between level select (=slider) and character select (=chooser)
    String characterFile = "advUI/Icons/cow.png"; //default character
    JLabel character;
    boolean tutorialOn=true;

    /**
     * Constructor
     * Character automatically set to Cow, unlocked levels set to 1
     */
    public MainMenu(){
        super();
        this.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
        view=new MainMenuPresentation(this, 1);
        setResizable(false);
        setVisible(true);
        pack();
    }

    /**
     * Constructor
     * @param levelUnlocked current unlocked levels
     * @param character current character
     */
    public MainMenu(int levelUnlocked, String character){
        super();
        this.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
        characterFile = character;
        view=new MainMenuPresentation(this, levelUnlocked);
        setResizable(false);
        setVisible(true);
        pack();
    }


    /**
     * Class to handle the presentation of the MainMenu
     */
    private class MainMenuPresentation {
        MainMenu control;
        int unlocked;

        public MainMenuPresentation(MainMenu control, int levelUnlocked){
            this.control=control;
            this.control.setPreferredSize(new Dimension(500, 500));
            this.control.setBackground(Main.bgColor);
            this.control.setLayout(new BorderLayout());
            unlocked = levelUnlocked;

            mainTitle();
            mainPane();
        }

        /**
         * Handles the creation and layout of the main pane
         */
        private void mainPane() {
            JPanel mainPane = new JPanel();
            mainPane.setPreferredSize(new Dimension(GameWindowModel.Width, GameWindowModel.Height));
            mainPane.setBackground(Main.bgColor);
            mainPane.setLayout(new BorderLayout());
            mainPane.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

            //Panel with the level select and the character select
            JPanel levelSelect = new JPanel();
            levelSelect.setLayout(new BorderLayout());

            //character select and level select
            CharacterChooser choose = new CharacterChooser();
            SRSlider slider = new SRSlider(1, unlocked);
            levelSelect.add(slider, BorderLayout.CENTER);

            //toggle tutorials
            JLabel tutorial = new JLabel("Tutorials On");
            tutorial.setFont(Main.getFontPlain(18));
            tutorial.setForeground(Main.green);
            tutorial.setOpaque(false);
            tutorial.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (tutorialOn) {
                        tutorial.setText("Tutorials Off");
                        tutorial.setForeground(Main.red);
                        tutorialOn = false;
                    } else {
                        tutorial.setText("Tutorials On");
                        tutorial.setForeground(Main.green);
                        tutorialOn = true;
                    }
                }
                @Override
                public void mouseEntered(MouseEvent e){
                    tutorial.setFont(Main.getFontPlain(20));
                }
                @Override
                public void mouseExited(MouseEvent e){
                    tutorial.setFont(Main.getFontPlain(18));
                }
            });

            //Level label
            JLabel level = new JLabel("Choose your level!", SwingConstants.CENTER);
            level.setFont(Main.getFontBold(30));
            level.setBackground(Main.bgColor);
            level.setOpaque(true);
            levelSelect.add(level, BorderLayout.NORTH);

            JPanel playPanel = new JPanel();
            playPanel.setLayout(new BoxLayout(playPanel, BoxLayout.LINE_AXIS));
            playPanel.setBackground(Main.bgColor);

            //Go button
            JButton go = new JButton("Go");
            go.setFont(Main.getFontBold(25));
            go.setBackground(Main.bgColor);
            go.addActionListener(e -> goButtonListener(levelSelect, choose, slider, tutorial, level, playPanel, go));

            //Character icon at bottom, clicking it goes to character select
            character = new JLabel(new ImageIcon(new ImageIcon(characterFile).getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT)));
            character.setOpaque(false);
            character.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    characterMouseClicked(levelSelect, slider, choose, playPanel, go, level);
                }
                @Override
                public void mouseEntered(MouseEvent e){
                    character.setIcon(new ImageIcon(new ImageIcon("advUI/Icons/exchange.png").getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT)));
                }
                @Override
                public void mouseExited(MouseEvent e){
                    character.setIcon(new ImageIcon(new ImageIcon(characterFile).getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT)));
                }
            });


            playPanel.add(character);
            playPanel.add(Box.createRigidArea(new Dimension(140,0)));
            playPanel.add(go);
            playPanel.add(Box.createRigidArea(new Dimension(100,0)));
            playPanel.add(tutorial);


            levelSelect.add(playPanel, BorderLayout.PAGE_END);

            mainPane.add(levelSelect, BorderLayout.CENTER);

            control.add(mainPane, BorderLayout.CENTER);
        }

        /**
         * Handle MouseClicked event for character icon
         * Move to chooser mode
         * Removes the slider and display the character chooser
         */
        private void characterMouseClicked(JPanel levelSelect, SRSlider slider, CharacterChooser choose, JPanel playPanel, JButton go, JLabel level) {
            if (mode.equalsIgnoreCase("slider")) {
                levelSelect.remove(slider);
                levelSelect.add(choose, BorderLayout.CENTER);
                playPanel.removeAll();
                playPanel.add(Box.createHorizontalGlue());
                playPanel.add(go);
                playPanel.add(Box.createHorizontalGlue());
                mode = "chooser";
                level.setText("Choose your character!");
                revalidate();
                repaint();
            }
        }

        /**
         *Handles the ActionListener for the go button
         * if in slider mode, start the game
         * if in chooser mode, goes back to slider mode
         */
        private void goButtonListener(JPanel levelSelect, CharacterChooser choose, SRSlider slider, JLabel tutorial, JLabel level, JPanel playPanel, JButton go) {
            if (mode.equalsIgnoreCase("slider")) { //start the game
                int selectedLevel = slider.getValue();
                if (selectedLevel == 6) {
                    dispose();
                    System.exit(0);
                } else if (selectedLevel != 0){
                    new GameWindow(slider.getValue(), tutorialOn, characterFile, unlocked);
                    dispose();
                }
            } else { //goes back to level select
                characterFile = choose.characters.get(choose.index);
                character.setIcon(new ImageIcon(new ImageIcon(characterFile).getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT)));
                levelSelect.remove(choose);
                levelSelect.add(slider, BorderLayout.CENTER);
                playPanel.removeAll();
                playPanel.add(character);
                playPanel.add(Box.createRigidArea(new Dimension(140,0)));
                playPanel.add(go);
                playPanel.add(Box.createRigidArea(new Dimension(100,0)));
                playPanel.add(tutorial);
                mode = "slider";
                level.setText("Choose your level!");
                revalidate();
                repaint();

            }
        }


        /**
         * Handle the layout of the main title
         */
        private void mainTitle() {
            JPanel mainTitle = new JPanel();
            mainTitle.setLayout(new BorderLayout());

            JLabel welcome = new JLabel("WELCOME TO", SwingConstants.CENTER);
            welcome.setFont(Main.getFontPlain(35));
            welcome.setOpaque(true);
            welcome.setBackground(Main.bgColor);
            mainTitle.add(welcome, BorderLayout.NORTH);

            JLabel title = new JLabel("SCOOP RECOUP!", SwingConstants.CENTER);
            title.setFont(Main.getFontPlain(50));
            title.setBackground(Main.bgColor);
            title.setOpaque(true);
            mainTitle.add(title, BorderLayout.CENTER);

            control.add(mainTitle, BorderLayout.NORTH);
        }

    }
}
