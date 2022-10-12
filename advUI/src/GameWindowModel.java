import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameWindowModel {
    public int unlockedLevel=1;
    private static int levelnum;
    //private static int level;
    private static Level level;
    public static int Height;
    public static int Width;
    private final List<ChangeListener> changeListeners = new ArrayList<>();

    public GameWindowModel(int levelFromMenu) {
        Height = 600;
        Width = 1000;
        levelnum = levelFromMenu;
        level = new Level(levelnum);
    }

    public void setLevel(int newLevel) {
        triggerChangeListeners();
        levelnum = newLevel;
        unlockedLevel=Math.max(levelnum,unlockedLevel);
        level = new Level(newLevel);
    }

    public static Level getLevel() {
        return level;
    }

    public static int getLevelNumber(){
        return levelnum;
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
