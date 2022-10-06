import javax.swing.*;
import java.awt.*;

public class SRSlider extends JSlider {

    public SRSlider(int level){
        super();
        setUI(new SRSliderUI(this));
        setMinimum(1);
        setMaximum(5);
        setValue(level);
        setMajorTickSpacing(1);
        Color BGColor = new Color(0xFED1FF);
        setPaintLabels(true);
        setBackground(BGColor);
        setForeground(BGColor);
        setPreferredSize(new Dimension(480, 300));
    }

}
