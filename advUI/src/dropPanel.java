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

        /**try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }**/

        //System.out.println("end");
        /**try {
            doActions.join();
            //System.out.println("DoAction interrupted");
            //paintMove.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }**/


        /** Character character = animation.character;
        ArrayList<String> loop = new ArrayList<>();
        boolean isNextIf =false;
        boolean loopFlag=false;
        for (int i =0; i<model.actionList.size();i++){
            String actionCall=model.actionList.get(i);
            if(i!=model.actionList.size()-1) isNextIf=model.actionList.get(i+1)=="If";
            String action=actionCall.split(" ")[0];
            if (action.equalsIgnoreCase("For")) {
                loopFlag=true;
                loop.add(actionCall.split(" ")[1]);
            }else if(loopFlag){
                loopFlag=false;
                loop.add(action);
                readAction(loop,character,isNextIf);
            }else {
                readAction(action,character,isNextIf);
            }
        }
    }
    private void readAction(String action, Character character, boolean isNextIf){
        if (action.equalsIgnoreCase("Move")) {
            character.move(isNextIf);
        } else if (action.equalsIgnoreCase("Turn")) {
            character.turn();
        }
        animation.revalidate();
        animation.repaint();
    }
    private void readAction(ArrayList<String> loop, Character character, boolean isNextIf) {
        int iter = Integer.parseInt(loop.get(0));
        String action = loop.get(1);
        if (action.equalsIgnoreCase("Move")) {
            for (int i = 0; i < iter; i++) {
                character.move(isNextIf);
                animation.revalidate();
                animation.repaint();
            }
        } else if (action.equalsIgnoreCase("Turn")) {
            for (int i = 0; i < iter; i++) {
                character.turn();
                animation.revalidate();
                animation.repaint();
            }
        }
    }**/
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
        container.setCursor(Cursor.getDefaultCursor());
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
        Point mouse = e.getPoint();
        for (cellRectangle cell : model.cells) {
            if (cell.contains(mouse)) {
                cell.highlight = true;
            } else {
                cell.highlight = false;
            }
        }
        repaint();
    }

    public void mouseExited(MouseEvent e) {}

@Override
    public void mouseDragged(MouseEvent e) {
        //get blocks played from index that corresponds with location of cell clicked, on release add block to cell location and
           //delete cell from previous index in blocks played and action list
        Point mouse = e.getPoint();
        for (cellRectangle cell : model.cells) {
            if(cell.contains(mouse)) {
                cell.highlight = true;
                this.repaint();
            }else{
                cell.highlight = false;
                this.repaint();
            }
        }
    }

@Override
    public void mouseMoved(MouseEvent e) {
        mouseEvent=true;
        Point mouse = e.getPoint();
        for (cellRectangle cell : model.cells) {
            if (cell.contains(mouse) && container.selectedBlock == null && cell.hasBlock) {
                cell.close = true;
                cell.highlight = true;
                this.repaint();
            } else if(cell.contains(mouse)) {
                cell.highlight = true;
                this.repaint();
            }else{
                cell.close = false;
                cell.highlight = false;
                this.repaint();
            }

        }
    }
}
