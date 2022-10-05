import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu(){
        super("Sccop Recoup");
        Color BGColor = new Color(0xFED1FF);
        setPreferredSize(new Dimension(1000, 500));
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

        JButton play = new JButton("PLAY!");
        play.addActionListener( e -> new GameWindow() );
        mainPane.add(play, BorderLayout.SOUTH);

        JPanel levelSelect = new JPanel();
        levelSelect.setLayout(new GridLayout(7, 1));
        levelSelect.setPreferredSize(new Dimension(100, 400));

        JButton level1 = new JButton("Level 1");
        level1.addActionListener( e -> new GameWindow() );
        levelSelect.add(level1);
        JButton level2 = new JButton("Level 2");
        level2.addActionListener( e -> new GameWindow() );
        levelSelect.add(level2);
        JButton level3 = new JButton("Level 3");
        level3.addActionListener( e -> new GameWindow() );
        levelSelect.add(level3);
        JButton level4 = new JButton("Level 4");
        level4.addActionListener( e -> new GameWindow() );
        levelSelect.add(level4);
        JButton level5 = new JButton("Level 5");
        level5.addActionListener( e -> new GameWindow() );
        levelSelect.add(level5);
        JButton level6 = new JButton("Level 6");
        level6.addActionListener( e -> new GameWindow() );
        levelSelect.add(level6);
        JButton level7 = new JButton("Level 7");
        level7.addActionListener( e -> new GameWindow() );
        levelSelect.add(level7);

       // ImageIcon = new ImageIcon("Icons/mintChoco.png");

        mainPane.add(levelSelect, BorderLayout.CENTER);

        //add(levelSelect);

        add(mainPane, BorderLayout.CENTER);

        pack();
    }

}
