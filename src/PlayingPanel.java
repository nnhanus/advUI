import javax.swing.*;
import java.awt.*;

public class PlayingPanel extends JPanel {

    public JPanel buttonGrid;

    public PlayingPanel(){
        super();
        setBackground(Color.BLUE);
        setSize(new Dimension(View.Width/2, View.Height));

        buttonGrid = new JPanel();
        buttonGrid.setLayout(new GridLayout(3, 3));

        Move move = new Move();
        buttonGrid.add(move);
        this.add(buttonGrid);
    }

}
