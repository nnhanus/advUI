import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    public MainMenu(){
        setPreferredSize(new Dimension(GameWindowModel.Width, GameWindowModel.Height));
        setVisible(true);
        setBackground(new Color(0xFED1FF));
        setLayout(new BorderLayout());

        JButton play = new JButton("PLAY!");
        play.addActionListener( e -> new GameWindow());
        add(play, BorderLayout.CENTER);

        pack();
    }

}
