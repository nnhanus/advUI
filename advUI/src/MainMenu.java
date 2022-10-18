import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu extends JDialog {

    MainMenuPresentation view;
    String mode = "slider";
    String characterFile = "advUI/Icons/cow.png";
    JLabel character;
    boolean tutorialOn=true;

    public MainMenu(int levelUnlocked){
        super();
        this.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
        view=new MainMenuPresentation(this, 5/*levelUnlocked*/);
        setVisible(true);
        pack();
    }


    private class MainMenuPresentation {

        MainMenu control;
        int unlocked;
        public MainMenuPresentation(MainMenu control, int levelUnlocked){
            this.control=control;
            Color BGColor = new Color(0xFED1FF);
            this.control.setPreferredSize(new Dimension(500, 500));
            this.control.setBackground(BGColor);
            this.control.setLayout(new BorderLayout());
            unlocked = levelUnlocked;

            mainTitle(BGColor);

            mainPane(BGColor);
        }
        private void mainPane(Color BGColor) {
            JPanel mainPane = new JPanel();
            mainPane.setPreferredSize(new Dimension(GameWindowModel.Width, GameWindowModel.Height));
            mainPane.setBackground(BGColor);
            mainPane.setLayout(new BorderLayout());
            mainPane.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

            JPanel levelSelect = new JPanel();
            levelSelect.setLayout(new BorderLayout());

            CharacterChooser choose = new CharacterChooser();
            SRSlider slider = new SRSlider(1, unlocked);
            levelSelect.add(slider, BorderLayout.CENTER);

            JLabel tutorial = new JLabel("Tutorials On");
            tutorial.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
            tutorial.setForeground(new Color(0,153,0));
            tutorial.setOpaque(false);
            tutorial.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (tutorialOn) {
                        tutorial.setText("Tutorials Off");
                        tutorial.setForeground(new Color(204, 0, 0));
                        tutorialOn = false;
                    } else {
                        tutorial.setText("Tutorials On");
                        tutorial.setForeground(new Color(0, 153, 15));
                        tutorialOn = true;
                    }
                }
                @Override
                public void mouseEntered(MouseEvent e){
                    tutorial.setFont(new Font("Bradley Hand", Font.PLAIN, 20));
                }
                @Override
                public void mouseExited(MouseEvent e){
                    tutorial.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
                }
            });

            JLabel level = new JLabel("Choose your level!", SwingConstants.CENTER);
            level.setFont(new Font("Bradley Hand", Font.BOLD, 30));
            level.setBackground(BGColor);
            level.setOpaque(true);
            levelSelect.add(level, BorderLayout.NORTH);

            JPanel playPanel = new JPanel();
            playPanel.setLayout(new BoxLayout(playPanel, BoxLayout.LINE_AXIS));
            playPanel.setBackground(BGColor);
            JButton go = new JButton("Go");
            go.setFont(new Font("Bradley Hand", Font.BOLD, 24));
            go.setBackground(BGColor);
            go.addActionListener(
                    e -> {
                        if (mode.equalsIgnoreCase("slider")) {
                            int selectedLevel = slider.getValue();
                            if (selectedLevel == 0) {
                            } else if (selectedLevel == 6) {
                                dispose();
                                System.exit(0);
                            } else {
                                GameWindow game = new GameWindow(slider.getValue(), tutorialOn, characterFile, unlocked);
                                dispose();
                            }
                        } else {
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
                            //character.setText("Character");
                            mode = "slider";
                            level.setText("Choose your level!");
                            revalidate();
                            repaint();

                        }
                    }
            );


            character = new JLabel(new ImageIcon(new ImageIcon(characterFile).getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT)));
            character.setOpaque(false);
            character.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
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


        private void mainTitle(Color BGColor) {
            JPanel mainTitle = new JPanel();
            mainTitle.setLayout(new BorderLayout());

            JLabel welcome = new JLabel("WELCOME TO", SwingConstants.CENTER);
            welcome.setFont(new Font("Bradley Hand", Font.PLAIN, 35));
            welcome.setOpaque(true);
            welcome.setBackground(BGColor);
            mainTitle.add(welcome, BorderLayout.NORTH);

            JLabel title = new JLabel("SCOOP RECOUP!", SwingConstants.CENTER);
            title.setFont(new Font("Bradley Hand", Font.PLAIN, 50));
            title.setBackground(BGColor);
            title.setOpaque(true);
            mainTitle.add(title, BorderLayout.CENTER);

            control.add(mainTitle, BorderLayout.NORTH);
        }

    }
}
