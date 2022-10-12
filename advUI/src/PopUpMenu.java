import javax.swing.*;
import java.awt.*;

public class PopUpMenu extends JDialog {
    GameWindow parent;
    PopUpMenuPresentation view;

    public PopUpMenu(GameWindow owner){
        super(owner);
        parent = owner;
        view= new PopUpMenuPresentation(this);

        setVisible(true);
        pack();
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
                            MainMenu menu=new MainMenu();
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

//            JButton close = new JButton("Close");
//            close.setFont(buttonFont);
//            close.setBackground(BGColor);
//            close.addActionListener( e -> dispose());

            JCheckBox tutorial = new JCheckBox("Tutorials On",parent.tutorialOn);
            tutorial.addActionListener(e->{parent.toggleTutorial();});

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            buttonPanel.setBackground(BGColor);
            buttonPanel.add(tutorial);
            buttonPanel.add(go);
            buttonPanel.add(restart);
            //buttonPanel.add(close);
            add(buttonPanel,BorderLayout.SOUTH);
        }
    }
}
