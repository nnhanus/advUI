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
    public int draggedBlockIndex=-1;


    //public boolean drag = false;

    public dropPanel(PlayingPanel parent) {
        container = parent;
        mouseEvent=false;
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

    public List<String> getActionList(){return model.actionList;}

    public static void clearList() {
        model.clearList();
    }

    public void readList() {
        animation = GameWindow.getAnimation();
        Thread doActions = new Thread(new readActionThread(animation, this));
        Thread paintMove = new Thread(new paintMove(animation, this));
        paintMove.start();
        doActions.start();


    }

    public void mouseClicked(MouseEvent e) {
        Point clicked = e.getPoint();
        for (cellRectangle cell : model.cells) {
            if (cell.contains(clicked)) {
                int toDelete = model.cells.indexOf(cell);
                cell.hasBlock=false;
                model.actionList.remove(toDelete);
                model.blocksPlayed.remove(toDelete);
                this.repaint();
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        Point point = e.getPoint();
        for(cellRectangle cell: model.cells){
            if (cell.contains(point)&&cell.hasBlock){
                draggedBlockIndex=model.cells.indexOf(cell);
                container.selectedBlock=model.blocksPlayed.get(draggedBlockIndex);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.mouseEvent=false;
        container.container.setCursor(Cursor.getDefaultCursor());
        if (container.selectedBlock != null) {
            if(draggedBlockIndex>=0){
                model.blocksPlayed.remove(draggedBlockIndex);
                model.cells.get(draggedBlockIndex).hasBlock=false;
                model.actionList.remove(draggedBlockIndex);
                draggedBlockIndex=-1;
            }
            model.setCell(e, container.selectedBlock);
            container.selectedBlock = null;
            repaint();
        }
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseEvent=true;
        Point mouse = e.getPoint();
        for (cellRectangle cell : model.cells) {
            if (cell.contains(mouse) && container.selectedBlock == null && cell.hasBlock) {
                cell.close = true;
                // cell.highlight = true;
                this.repaint();
            } else if(cell.contains(mouse)&& container.selectedBlock != null) {
                cell.highlight = true;
                this.repaint();
            }else{
                cell.close = false;
                cell.highlight = false;
                this.repaint();
            }

        }
    }

    public void clearAll() {
        model.blocksPlayed.clear();
        model.cells.clear();
        model.actionList.clear();
    }

    public List<cellRectangle> getCells() {
        return model.cells;
    }
}
