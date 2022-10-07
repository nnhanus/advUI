import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener {
    SRSlider slider;
    int level;

    public MainMenu(){
        super("Scoop Recoup");
        Color BGColor = new Color(0xFED1FF);
        setPreferredSize(new Dimension(500, 600));
        setVisible(true);
        setBackground(BGColor);
        setLayout(new BorderLayout());
        level=1;

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

        slider = new SRSlider(level);
        levelSelect.add(slider, BorderLayout.CENTER);

        JLabel levelPrompt = new JLabel("Choose your level!", SwingConstants.CENTER);
        levelPrompt.setFont(new Font("Bradley Hand", Font.BOLD, 30));
        levelPrompt.setBackground(BGColor);
        levelPrompt.setOpaque(true);
        levelSelect.add(levelPrompt, BorderLayout.NORTH);

        JPanel playPanel = new JPanel();
        playPanel.setBackground(BGColor);
        JButton play = new JButton("Play");
        play.addActionListener(this);
        play.setFont(new Font("Bradley Hand", Font.BOLD, 24));
        play.setBackground(BGColor);
        playPanel.add(play);
        levelSelect.add(playPanel, BorderLayout.SOUTH);

        //add(levelSelect, BorderLayout.CENTER);

        mainPane.add(levelSelect, BorderLayout.CENTER);

        add(mainPane, BorderLayout.CENTER);

        pack();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        level=slider.getValue();
        GameWindow game = new GameWindow(level);
        this.dispose();
    }
}
