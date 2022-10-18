import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class WinAnimationPanel extends JPanel {
    List<BufferedImage> scoopsWin;
    List<Point> locations;
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
            if (locations.indexOf(point) < 15) {
                point.y += 5;
            } else if (locations.indexOf(point) <35) {
                point.y += 7;
            } else {
                point.y += 9;
            }
            if (point.y > this.getHeight()) {
                toRemove.add(locations.indexOf(point));
            }
        }
        for (Integer index : toRemove) {
            locations.remove(index);
            scoopsWin.remove(index);
        }
        int count=0;
        for (BufferedImage scoop : scoopsWin) {
            count++;
            System.out.println(count);
            g2d.drawImage(scoop,locations.get(scoopsWin.indexOf(scoop)).x, locations.get(scoopsWin.indexOf(scoop)).y, 30, 30, null);
        }


    }
}
