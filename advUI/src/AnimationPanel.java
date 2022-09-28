import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;

public class AnimationPanel extends JPanel {
    public Grid grid;
    public Character character;

    public AnimationPanel (){
        super ();
        Color bgColor = new Color(0xFED1FF);
        setBackground(bgColor);
        chargeLevel(GameWindow.getLevel());
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        grid.paintGrid(g);
        character.presentation.paintCharacter(g);
    }

    public void setGrid(Level level){
        grid = new Grid(level.getNumRows(), level.getNumCols(), level.getScoops());
    }

    public void setCharacter(Level level){
        character = new Character(this, level.getPosX(), level.getPosY(), level.getOrientation());
    }

    public void chargeLevel(Level level){
        setGrid(level);
        setCharacter(level);
    }
}
