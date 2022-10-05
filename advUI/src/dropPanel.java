import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.awt.event.MouseEvent;

// create small zones with drop listeners that know where they are and then insert in list
public class dropPanel extends JPanel implements MouseListener, MouseMotionListener {

    public static dropPanelModel model;
    public dropPanelPresentation view;
    public AnimationPanel animation;
    public PlayingPanel container;
    public boolean mouseEvent;

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

    public static void clearList() {
        model.clearList();
    }

    public void readList() {
        animation = GameWindow.getAnimation();
        Thread doActions = new Thread(new readActionThread(animation, this));
        Thread paintMove = new Thread(new readActionThread(animation, this));
        paintMove.start();
        doActions.start();

        //
//        Character character = animation.character;
//        List<String> loop = new ArrayList<>();
//        boolean isNextIf =false;
//        boolean loopFlag=false;
//        for (int i =0; i<model.actionList.size();i++){
//            String actionCall=model.actionList.get(i);
//            if(i!=model.actionList.size()-1) isNextIf=model.actionList.get(i+1)=="If";
//            String action=actionCall.split(" ")[0];
//            if (action.equalsIgnoreCase("For")) {
//                loopFlag=true;
//                loop.add(actionCall.split(" ")[1]);
//            }else if(loopFlag){
//                loopFlag=false;
//                loop.add(action);
//                readAction(loop,character,isNextIf);
//            }else {
//                readAction(action,character,isNextIf);
//            }
//        }
    }

//    private void readAction(String action, Character character, boolean isNextIf){
//        if (action.equalsIgnoreCase("Move")) {
//            character.move(isNextIf);
//        } else if (action.equalsIgnoreCase("Turn")) {
//            character.turn();
//        }
//        animation.revalidate();
//        animation.repaint();
//    }
//    private void readAction(List<String> loop, Character character, boolean isNextIf){
//        int iter=Integer.parseInt(loop.get(0));
//        String action=loop.get(1);
//        if (action.equalsIgnoreCase("Move")) {
//            for(int i =0;i<iter;i++){
//                character.move(isNextIf);
//                animation.revalidate();
//                animation.repaint();
//            }
//        } else if (action.equalsIgnoreCase("Turn")) {
//            for(int i =0;i<iter;i++){
//                character.turn();
//                animation.revalidate();
//                animation.repaint();
//            }
//        }

    // }

    /**
     * void makeBtnClick() {
     * model.play.addMouseListener(new MouseAdapter() {
     *
     * @Override public void mouseClicked(MouseEvent e) {
     * super.mouseClicked(e);
     * readList();
     * //add function to pass list to character for actions
     * }
     * });
     * <p>
     * model.redo.addMouseListener(new MouseAdapter() {
     * @Override public void mouseClicked(MouseEvent e) {
     * super.mouseClicked(e);
     * clearList();
     * repaint();
     * System.out.println("reset");
     * <p>
     * }
     * });
     * <p>
     * }
     **/

    public void mouseClicked(MouseEvent e) {
        Point clicked = e.getPoint();
        for (cellRectangle cell : model.cells) {
            if (cell.contains(clicked)) {
                int toDelete = model.cells.indexOf(cell);
                model.actionList.remove(toDelete);
                model.blocksPlayed.remove(toDelete);
                this.repaint();
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        container.setCursor(Cursor.getDefaultCursor());
        if (container.selectedBlock != null) {
            model.setCell(e, container.selectedBlock);
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

    public void mouseMoved(MouseEvent e) {
        mouseEvent=true;
        Point mouse = e.getPoint();
        for (cellRectangle cell : model.cells) {
            if (cell.contains(mouse) && container.selectedBlock == null&& cell.hasBlock) {
                cell.close = true;
                this.repaint();
            }
            else{
                cell.close =false;
                this.repaint();
            }
        }
    }
}
