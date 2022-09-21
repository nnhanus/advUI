import javax.swing.*;
import java.awt.*;

public class AnimationPanel extends JPanel {
    public Grid grid;
    public Character character;

    public AnimationPanel (){
        super ();
        Color bgColor = new Color(0xFED1FF);
        setBackground(bgColor);
        grid = new Grid();
        character = new Character();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        grid.paintGrid(g);
        character.presentation.paintCharacter(g);
    }
}
