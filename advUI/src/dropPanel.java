import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static javax.swing.SwingUtilities.convertPoint;

// create small zones with drop listeners that know where they are and then insert in list
public class dropPanel extends JPanel implements MouseListener, MouseMotionListener {

    public static dropPanelModel model;
    public dropPanelPresentation view;
    public AnimationPanel animation;
    public PlayingPanel container;
    public boolean mouseEvent;
    public int draggedBlockIndex = -1;


    //public boolean drag = false;

    public dropPanel(PlayingPanel parent) {
        container = parent;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.model = new dropPanelModel(this);
        this.view = new dropPanelPresentation(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        view.paint(g);
    }

    public dropPanelModel getModel() {
        return model;
    }


    public void readList() {
        mouseEvent = true;
        animation = GameWindow.getAnimation();
        Thread doActions = new Thread(new readActionThread(animation, this));
        Thread paintMove = new Thread(new paintMove(animation, this));
        paintMove.start();
        doActions.start();


    }

    public void mouseClicked(MouseEvent e) {
        Point clicked = e.getPoint();
        for (cellRectangle cell : getCells()) {
            if (cell.contains(clicked)) {
                int toDelete = getCells().indexOf(cell);
                cell.hasBlock = false;
                getActions().remove(toDelete);
                getBlocksPlayed().remove(toDelete);
                this.repaint();
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        Point point = e.getPoint();
        for (cellRectangle cell : getCells()) {
            if (cell.contains(point) && cell.hasBlock) {
                draggedBlockIndex = getCells().indexOf(cell);
                container.selectedBlock = getBlocksPlayed().get(draggedBlockIndex);
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Image image = container.selectedBlock.getIcon().getImage();
                Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "block img");
                container.getContainer().setCursor(c);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.mouseEvent = false;
        container.getContainer().setCursor(Cursor.getDefaultCursor());
        if (container.selectedBlock != null) {
            if (draggedBlockIndex >= 0) {
                getBlocksPlayed().remove(draggedBlockIndex);
                getCells().get(draggedBlockIndex).hasBlock = false;
                getActions().remove(draggedBlockIndex);
                draggedBlockIndex = -1;
            }
            setCell(e, container.selectedBlock);
            container.selectedBlock = null;
            repaint();
        }
    }


    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseEvent = true;
        Point mouse = e.getPoint();
        for (cellRectangle cell : model.cells) {
            if (cell.contains(mouse) && container.selectedBlock == null && cell.hasBlock) {
                cell.close = true;
                // cell.highlight = true;
                this.repaint();
            } else if (cell.contains(mouse) && container.selectedBlock != null) {
                cell.setHighlight(true);
                this.repaint();
            } else {
                cell.close = false;
                cell.setHighlight(false);
                this.repaint();
            }

        }
    }

    public void clearAll() {
        model.blocksPlayed.clear();
        model.cells.clear();
        model.actionList.clear();
    }

     List<cellRectangle> getCells() {
        return model.cells;
    }

    private void setCell(MouseEvent e, BlockControl selectedBlock) {
        model.setCell(e, selectedBlock);
    }

    List<String> getActions() {
        return model.actionList;
    }

    List<BlockControl> getBlocksPlayed() {
        return model.blocksPlayed;
    }
    PlayingPanel getContainer(){
        return container;
    }

    public void clearCells() {
        model.cells=new ArrayList<>();
    }
}
