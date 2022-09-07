import javax.swing.*;
import java.awt.*;

public class PlayingPanel extends JPanel {

    public JPanel buttonGrid;

    public PlayingPanel(){
        super();
        Color bgColor = new Color(0xD6EAF8);
        setBackground(bgColor);
        //setSize(new Dimension(View.Width/2, View.Height));

        buttonGrid = new JPanel();
        buttonGrid.setLayout(new GridLayout(3, 3));

        Move move = new Move();
        buttonGrid.add(move);
        this.add(buttonGrid);
    }

}
