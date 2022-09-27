import javax.sound.midi.Soundbank;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AnimationPanel extends JPanel {
    public Grid grid;
    public Character character;
    public List<ChangeListener> changeListeners = new ArrayList<>();

    public AnimationPanel (){
        super ();
        Color bgColor = new Color(0xFED1FF);
        setBackground(bgColor);
        grid = new Grid();
        character = new Character();
        changeListeners.add( e -> repaint());
    }

    public void addChangeListener(ChangeListener listener){
        this.changeListeners.add(listener);
    }
    public void removeChangeListener(ChangeListener listener){
        this.changeListeners.remove(listener);
    }

    public void triggerChangeListener(){
        for (ChangeListener listener : changeListeners){
            listener.stateChanged(new ChangeEvent(this));
        }
    }

    @Override
    public void paintComponent(Graphics g){
        System.out.println("Repaint");
        super.paintComponent(g);
        grid.paintGrid(g);
        character.presentation.paintCharacter(g);
    }
}
