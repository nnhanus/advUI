import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    public static int Height = 400;
    public static int Width = 600;
    public View (){
        super("Ice Cream Test");
        this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setPreferredSize(new Dimension(Width, Height));
        setVisible(true);
        setLayout(new GridLayout(1, 2));

        AnimationPanel animation = new AnimationPanel();
        this.add(animation, BorderLayout.EAST);

        PlayingPanel playingZone = new PlayingPanel();
        this.add(playingZone, BorderLayout.WEST);

        pack();

    }

}
