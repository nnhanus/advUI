import javax.swing.*;
import java.awt.*;

public class PlayingPanel extends JPanel {

    public TopPanel topPanel;
    public static dropPanel bottomPanel;
    public BlockControl selectedBlock;

    public GameWindow container;


    public PlayingPanel(GameWindow parent) {
        super();
        container=parent;
        Color bgColor = new Color(0xD6EAF8);
        setBackground(bgColor);
        setLayout(new BorderLayout());
        topPanel=new TopPanel(this);
        bottomPanel= new dropPanel(this);

        this.add(topPanel,BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.CENTER);

    }

    public GameWindow getContainer(){
        return  container;
    }

    public dropPanel getBottomPanel() {
        return bottomPanel;
    }
    public TopPanel getTopPanel() {
        return topPanel;
    }
}