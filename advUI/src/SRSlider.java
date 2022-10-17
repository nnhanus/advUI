import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SRSlider extends JSlider {
    int level;
    int unlocked;
    public SRSlider(int level, int unlocked){
        super();
        this.level=level;
        this.unlocked=unlocked;
        setUI(new SRSliderUI(this));
        setMinimum(0);
        setMaximum(6);
        setValue(level);
        setMajorTickSpacing(1);
        Color BGColor = new Color(0xFED1FF);
        setPaintLabels(true);
        setBackground(BGColor);
        setForeground(BGColor);
        setPreferredSize(new Dimension(480, 300));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clickToLevel(e.getPoint());
            }
        });
        this.addChangeListener(
                ce -> {
                    //if user tries to go to a locked level, bounce back to last unlocked level
                    if (this.getValue() > unlocked&&this.getValue()<6) {
                        this.setValue(unlocked);
                    }
                    this.repaint();
                }
        );
    }

    private void clickToLevel(Point point) {
        double percent = point.x / ((double) getWidth());
        int range = getMaximum() - getMinimum();
        double newVal = range * percent;
        int result = (int)(getMinimum() + newVal);
        setValue(result);
    }

}
