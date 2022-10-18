import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class WinAnimationPanel extends JPanel {
    List<BufferedImage> scoopsWin;
    List<Point> locations;

    //Transparent panel that paints falling icecream scoops at end of game
    public WinAnimationPanel(List<BufferedImage> scoopsWin,List<Point> locations,GameWindow container){
        this.setPreferredSize(container.getPreferredSize());
        this.locations=locations;
        this.scoopsWin=scoopsWin;
        this.setOpaque(false);
        this.setVisible(true);

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        List<Integer> toRemove = new ArrayList<>();
        Graphics2D g2d = (Graphics2D) g;
        for (Point point : locations) {
            //have the scoops fall at varying rates for visual appeal
            if (locations.indexOf(point) < 15) {
                point.y += 5;
            } else if (locations.indexOf(point) <35) {
                point.y += 7;
            } else {
                point.y += 3;
            }
            if (point.y > this.getHeight()) {
                toRemove.add(locations.indexOf(point));
            }
        }
        for (Integer index : toRemove) {
            //remove scoops that fall past the screen
            locations.remove(index);
            scoopsWin.remove(index);
        }
        for (BufferedImage scoop : scoopsWin) {
            g2d.drawImage(scoop,locations.get(scoopsWin.indexOf(scoop)).x, locations.get(scoopsWin.indexOf(scoop)).y, 30, 30, null);
        }


    }
}
