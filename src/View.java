import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    public int Height = 400;
    public int Width = 600;
    public View (){
        super("Ice Cream Test");

        setPreferredSize(new Dimension(Width, Height));
        setVisible(true);
        setLayout(new GridLayout(1, 2));

        JPanel animation = new JPanel();
        this.add(animation, BorderLayout.WEST);
        animation.setBackground(Color.PINK);
        animation.setSize(new Dimension(Width/2, Height));

        JPanel playingZone = new JPanel();
        this.add(playingZone, BorderLayout.EAST);
        playingZone.setBackground(Color.BLUE);
        playingZone.setSize(new Dimension(Width/2, Height));

        pack();





    }



}
