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
            public void mouseClicked(MouseEvent e){
                Point point = e.getPoint();
                if(wrappedPanel.getBottomPanel().view.play.contains(SwingUtilities.convertPoint(e.getComponent(),point,wrappedPanel.bottomPanel.view.play))){
                    wrappedPanel.getBottomPanel().view.play.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.bottomPanel.view.play));
                }
                else if(wrappedPanel.getBottomPanel().view.clear.contains(SwingUtilities.convertPoint(e.getComponent(),point,wrappedPanel.bottomPanel.view.clear))){
                wrappedPanel.getBottomPanel().view.clear.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.bottomPanel.view.clear));}
                else if(wrappedPanel.getBottomPanel().view.redo.contains(SwingUtilities.convertPoint(e.getComponent(),point,wrappedPanel.bottomPanel.view.redo))){
                wrappedPanel.getBottomPanel().view.redo.dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.bottomPanel.view.redo));}
                else if(wrappedPanel.getBottomPanel().contains(SwingUtilities.convertPoint(e.getComponent(),point,wrappedPanel.getBottomPanel()))){
                    wrappedPanel.getBottomPanel().dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.getBottomPanel()));
                }

            }
            @Override
            public void mousePressed(MouseEvent e) {
                wrappedPanel.getBottomPanel().dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.getBottomPanel()));
                wrappedPanel.getTopPanel().dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.getTopPanel()));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                wrappedPanel.getBottomPanel().dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.getBottomPanel()));

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
                wrappedPanel.getBottomPanel().mouseEvent=true;
                Point mouse = SwingUtilities.convertPoint(e.getComponent(),e.getPoint(),wrappedPanel.getBottomPanel());
                for (cellRectangle cell : wrappedPanel.getBottomPanel().getCells()) {
                    if(cell.contains(mouse)) {
                        cell.setHighlight(true);
                        wrappedPanel.getBottomPanel().repaint();
                    }else{
                        cell.setHighlight(false);
                        wrappedPanel.getBottomPanel().repaint();
                    }
                }
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                wrappedPanel.getBottomPanel().dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.getBottomPanel()));
                wrappedPanel.getTopPanel().dispatchEvent(SwingUtilities.convertMouseEvent(e.getComponent(),e,wrappedPanel.getTopPanel()));
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
