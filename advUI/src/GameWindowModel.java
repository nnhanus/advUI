import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameWindowModel {
    //private static int level;
    private static Level level;
    public static int Height;
    public static int Width;
    private final List<ChangeListener> changeListeners = new ArrayList<>();

    public GameWindowModel() {
        Height = 600;
        Width = 1200;
        level = new Level(1);
    }

    public void setLevel(int newLevel) {
        triggerChangeListeners();
        level = new Level(newLevel);
    }

    public static Level getLevel() {
        return level;
    }

    public static int getLevelNumber(){
        return level.getNumber();
    }

    public void addChangeListener(ChangeListener listener) {
        changeListeners.add(listener);
    }

    public void removeChangeListener(ChangeListener listener) {
        changeListeners.remove(listener);
    }

    public void triggerChangeListeners() {
        for (ChangeListener listener : changeListeners) {
            listener.stateChanged(new ChangeEvent(this));
        }
    }
}
