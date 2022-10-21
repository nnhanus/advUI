import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * In-game menu with level select, main menu, quit, restart options
 */
public class PopUpMenu extends JDialog {
    GameWindow parent;
    PopUpMenuPresentation view;

    public PopUpMenu(GameWindow owner){
        super(owner);
        parent = owner;
        this.setVisible(false);
        view= new PopUpMenuPresentation(this);
        setResizable(false);

        pack();
        this.setLocation(parent.getWidth()/2-this.getWidth()/2,parent.getHeight()/2-this.getHeight()/2);
        setVisible(true);

    }

    /**
     * handles the presentation of the pop-up menu
     */
    private class PopUpMenuPresentation {
        PopUpMenu control;
        Font buttonFont = Main.getFontBold(24);

        public PopUpMenuPresentation(PopUpMenu control) {
            this.control=control;

            this.control.setLayout(new BorderLayout());

            JPanel levelSelect = new JPanel();
            levelSelect.setLayout(new BorderLayout());
            levelSelect.setPreferredSize(new Dimension(500, 250));
            levelSelect.setMinimumSize(new Dimension(500, 250));
            levelSelect.setMaximumSize(new Dimension(500, 250));

            SRSlider slider = new SRSlider(GameWindow.getLevelNumber(), GameWindow.getUnlocked());
            levelSelect.add(slider, BorderLayout.CENTER);

            JLabel level = new JLabel("Choose your level!", SwingConstants.CENTER);
            level.setFont(Main.getFontBold(30));
            level.setBackground(Main.bgColor);
            level.setOpaque(true);
            levelSelect.add(level, BorderLayout.NORTH);
            this.control.add(levelSelect, BorderLayout.CENTER);

            buttonPanel(slider);

            JLabel title = new JLabel("Menu", SwingConstants.CENTER);
            title.setFont(Main.getFontBold(35));
            title.setBackground(Main.bgColor);
            title.setOpaque(true);

            this.control.add(title, BorderLayout.NORTH);
        }

        /**
         * Creates button panel
         * @param slider slider to get value
         */
        private void buttonPanel(SRSlider slider) {
            JButton go = new JButton("Go");
            go.setFont(buttonFont);
            go.setBackground(Main.bgColor);
            go.addActionListener( e -> handleGoListener(slider) );

            JButton restart = new JButton("Restart");
            restart.setFont(buttonFont);
            restart.setBackground(Main.bgColor);
            restart.addActionListener( e -> {
                parent.changeLevel(GameWindow.getLevelNumber());
                this.control.dispose();
            });

            JLabel tutorial;
            //Get the state of tutorial of the game
            if(parent.tutorialOn){
                tutorial = new JLabel("Tutorials On");
                tutorial.setForeground(Main.green);
            }else{
                tutorial = new JLabel("Tutorials Off");
                tutorial.setForeground(Main.red);
            }
            tutorial.setFont(Main.getFontPlain(18));
            tutorial.setOpaque(false);
            tutorial.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    handleTutorialMouseClicked(tutorial);
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

            //Creating the panel + setting the layout
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.LINE_AXIS));
            buttonPanel.setBackground(Main.bgColor);

            buttonPanel.add(Box.createRigidArea(new Dimension(5,0)));
            buttonPanel.add(tutorial);

            buttonPanel.add(Box.createRigidArea(new Dimension(100,0)));
            buttonPanel.add(go);

            buttonPanel.add(Box.createRigidArea(new Dimension(100,0)));
            buttonPanel.add(restart);

            add(buttonPanel,BorderLayout.SOUTH);
        }

        /**
         * Handles the MouseClicked event for the tutorial label
         * @param tutorial tutorial label
         */
        private void handleTutorialMouseClicked(JLabel tutorial) {
            if (parent.tutorialOn) {
                tutorial.setText("Tutorials Off");
                tutorial.setForeground(Main.red);
                parent.toggleTutorial();
            } else {
                tutorial.setText("Tutorials On");
                tutorial.setForeground(Main.green);
                parent.toggleTutorial();
            }
        }

        /**
         * Handle the ActionListener for the go button
         * @param slider slider to get value
         */
        private void handleGoListener(SRSlider slider) {
            int selectedLevel= slider.getValue();
            if(selectedLevel==0){ //go to main menu, close the game window
                new MainMenu(GameWindow.getLevelNumber(), GameWindow.view.animation.character.getPath());
                parent.dispose();
                this.control.dispose();
            }
            else if (selectedLevel==6) { //quit
                parent.dispose();
                this.control.dispose();
            }else { //got to selected level
                parent.changeLevel(slider.getValue());
                this.control.dispose();
            }
        }
    }
}
