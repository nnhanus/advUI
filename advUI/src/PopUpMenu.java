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
        this.setVisible(false);
        parent = owner;
        view= new PopUpMenuPresentation(this);
        setResizable(false);

        pack();
        this.setLocation(parent.getWidth()/2-this.getWidth()/2,parent.getHeight()/2-this.getHeight()/2);
        setVisible(true);

    }

    private class PopUpMenuPresentation {
        PopUpMenu control;
        Color BGColor = new Color(0xFED1FF);
        String fontFamily = "Bradley Hand";
        Font buttonFont = new Font(fontFamily, Font.BOLD, 24);

        public PopUpMenuPresentation(PopUpMenu control) {
            this.control=control;

            this.control.setLayout(new BorderLayout());

            JPanel levelSelect = new JPanel();
            levelSelect.setLayout(new BorderLayout());
            levelSelect.setPreferredSize(new Dimension(500, 250));
            levelSelect.setMinimumSize(new Dimension(500, 250));
            levelSelect.setMaximumSize(new Dimension(500, 250));

            SRSlider slider = new SRSlider(parent.getLevelNumber(), parent.getUnlocked());
            levelSelect.add(slider, BorderLayout.CENTER);

            JLabel level = new JLabel("Choose your level!", SwingConstants.CENTER);
            level.setFont(new Font(fontFamily, Font.BOLD, 30));
            level.setBackground(BGColor);
            level.setOpaque(true);
            levelSelect.add(level, BorderLayout.NORTH);
            this.control.add(levelSelect, BorderLayout.CENTER);

            buttonPanel(slider);

            JLabel title = new JLabel("Menu", SwingConstants.CENTER);
            title.setFont(new Font(fontFamily, Font.BOLD, 35));
            title.setBackground(BGColor);
            title.setOpaque(true);

            this.control.add(title, BorderLayout.NORTH);
        }

        private void buttonPanel(SRSlider slider) {
            JButton go = new JButton("Go");
            go.setFont(buttonFont);
            go.setBackground(BGColor);
            go.addActionListener(
                    e -> {
                        int selectedLevel= slider.getValue();
                        if(selectedLevel==0){
                            System.out.println(parent.getLevelNumber());
                            MainMenu menu=new MainMenu(parent.getLevelNumber());
                            parent.dispose();
                            this.control.dispose();
                        }
                        else if (selectedLevel==6) {
                            parent.dispose();
                            this.control.dispose();
                        }else {
                            parent.changeLevel(slider.getValue());
                            this.control.dispose();
                        }

                    }
            );

            JButton restart = new JButton("Restart");
            restart.setFont(buttonFont);
            restart.setBackground(BGColor);
            restart.addActionListener( e -> {
                parent.changeLevel(parent.getLevelNumber());
                this.control.dispose();
            });

            JLabel tutorial;
            if(parent.tutorialOn){
                tutorial = new JLabel("Tutorials On");
                tutorial.setForeground(new Color(0,153,0));
            }else{
                tutorial = new JLabel("Tutorials Off");
                tutorial.setForeground(new Color(204, 0, 0));
            }
            tutorial.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
            tutorial.setOpaque(false);
            tutorial.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (parent.tutorialOn) {
                        tutorial.setText("Tutorials Off");
                        tutorial.setForeground(new Color(204, 0, 0));
                        parent.toggleTutorial();
                    } else {
                        tutorial.setText("Tutorials On");
                        tutorial.setForeground(new Color(0, 153, 15));
                        parent.toggleTutorial();
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

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.LINE_AXIS));
            buttonPanel.setBackground(BGColor);
            buttonPanel.add(Box.createRigidArea(new Dimension(5,0)));
            buttonPanel.add(tutorial);
            buttonPanel.add(Box.createRigidArea(new Dimension(100,0)));
            buttonPanel.add(go);
            buttonPanel.add(Box.createRigidArea(new Dimension(100,0)));
            buttonPanel.add(restart);
            add(buttonPanel,BorderLayout.SOUTH);
        }
    }
}
