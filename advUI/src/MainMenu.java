import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MainMenu extends JDialog {

    MainMenuPresentation view;

    public MainMenu(){
        super();
        this.setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
        view=new MainMenuPresentation(this);
        setVisible(true);
        pack();
    }


    private class MainMenuPresentation {

        MainMenu control;
        public MainMenuPresentation(MainMenu control){
            this.control=control;
            Color BGColor = new Color(0xFED1FF);
            this.control.setPreferredSize(new Dimension(500, 500));
            this.control.setBackground(BGColor);
            this.control.setLayout(new BorderLayout());

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

            SRSlider slider = new SRSlider(1);
            levelSelect.add(slider, BorderLayout.CENTER);

            JLabel level = new JLabel("Choose your level!", SwingConstants.CENTER);
            level.setFont(new Font("Bradley Hand", Font.BOLD, 30));
            level.setBackground(BGColor);
            level.setOpaque(true);
            levelSelect.add(level, BorderLayout.NORTH);

            JPanel playPanel = new JPanel();
            playPanel.setBackground(BGColor);
            JButton go = new JButton("Go");
            go.setFont(new Font("Bradley Hand", Font.BOLD, 24));
            go.setBackground(BGColor);
            go.addActionListener(
                    e -> {
                        int selectedLevel=slider.getValue();
                        if(selectedLevel==0){}
                        else if (selectedLevel==6) {
                            dispose();
                        }else {
                            GameWindow game = new GameWindow(slider.getValue());
                            dispose();
                        }
                    }
            );
            playPanel.add(go);
            levelSelect.add(playPanel, BorderLayout.SOUTH);

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
