import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu(){
        super("Sccop Recoup");
        Color BGColor = new Color(0xFED1FF);
        setPreferredSize(new Dimension(500, 600));
        setVisible(true);
        setBackground(BGColor);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("SCOOP RECOUP", SwingConstants.CENTER);
        title.setFont(new Font("Times", Font.PLAIN, 20));
        title.setBackground(BGColor);

        add(title, BorderLayout.NORTH);

        JPanel mainPane = new JPanel();
        mainPane.setPreferredSize(new Dimension(GameWindowModel.Width, GameWindowModel.Height));
        mainPane.setBackground(BGColor);
        mainPane.setLayout(new BorderLayout());

        JPanel levelSelect = new JPanel();
        levelSelect.setLayout(new BorderLayout());
        /**levelSelect.setPreferredSize(new Dimension(500, 400));
        levelSelect.setMinimumSize(new Dimension(500, 400));
        levelSelect.setMaximumSize(new Dimension(500, 400));**/

        SRSlider slider = new SRSlider(1);
        levelSelect.add(slider, BorderLayout.CENTER);

        JLabel level = new JLabel("Choose your level!", SwingConstants.CENTER);
        level.setFont(new Font("Bradley Hand", Font.BOLD, 30));
        level.setBackground(BGColor);
        level.setOpaque(true);
        levelSelect.add(level, BorderLayout.NORTH);

        JPanel playPanel = new JPanel();
        playPanel.setBackground(BGColor);
        JButton play = new JButton("Play");
        play.setFont(new Font("Bradley Hand", Font.BOLD, 24));
        play.setBackground(BGColor);
        playPanel.add(play);
        levelSelect.add(playPanel, BorderLayout.SOUTH);

        add(levelSelect, BorderLayout.CENTER);

        mainPane.add(levelSelect, BorderLayout.CENTER);

        add(mainPane, BorderLayout.CENTER);

        pack();
    }

}
