import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class GlassPaneWrapper extends JPanel {

    PlayingPanel wrappedPanel;
    public GlassPaneWrapper(PlayingPanel givenPanel) {
        this.wrappedPanel=givenPanel;
        this.setOpaque(false);
        this.setVisible(false);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                wrappedPanel.bottomPanel.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.bottomPanel));
                wrappedPanel.topPanel.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.topPanel));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                wrappedPanel.bottomPanel.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.bottomPanel));

            }

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}

        });
        this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //get blocks played from index that corresponds with location of cell clicked, on release add block to cell location and
                //delete cell from previous index in blocks played and action list
                wrappedPanel.bottomPanel.mouseEvent=true;
                Point mouse = SwingUtilities.convertPoint(e.getComponent(),e.getPoint(),wrappedPanel.bottomPanel);
                for (cellRectangle cell : wrappedPanel.bottomPanel.getCells()) {
                    if(cell.contains(mouse)) {
                        cell.highlight = true;
                        wrappedPanel.bottomPanel.repaint();
                    }else{
                        cell.highlight = false;
                        wrappedPanel.bottomPanel.repaint();
                    }
                }
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                wrappedPanel.bottomPanel.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.bottomPanel));
                wrappedPanel.topPanel.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.topPanel));
            }


        });
        this.setFocusable(true);
    }

    public void activateGlassPane(boolean activate) {
        this.setVisible(activate);
        if (activate) {
            this.requestFocusInWindow();
            this.setFocusTraversalKeysEnabled(false);
        }
    }

}
