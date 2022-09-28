import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.TimeUnit;

// create small zones with drop listeners that know where they are and then insert in list
public class dropPanel extends JPanel implements MouseListener, MouseMotionListener {

    public static dropPanelModel model;
    public dropPanelPresentation view;
    public AnimationPanel animation;
    public PlayingPanel container;

    //public boolean drag = false;

    public dropPanel(PlayingPanel parent){
        container=parent;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.model= new dropPanelModel(this);
        this.view= new dropPanelPresentation(this);


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        view.paint(g);
    }
    public dropPanelModel getModel(){
        return model;
}
    public static void clearList(){
        model.clearList();
    }

    public void readList(){
        animation = GameWindow.getAnimation();
        Character character = animation.character;
        for (String action : model.actionList){
            readAction(action, character);
            animation.revalidate();
            animation.repaint();
        }
    }

    private void readAction(String action, Character character){
        //System.out.println(action);
        if (action.equalsIgnoreCase("Move")) {
            character.move();
        } else if (action.equalsIgnoreCase("Turn")) {
            character.turn();
        }
    }

    void makeBtnClick() {
        model.play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                readList();
                //add function to pass list to character for actions
            }
        });

        model.redo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                clearList();
                repaint();
                System.out.println("reset");

            }
        });

    }

    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse pressed drop panel");
//        BlockControl block = (BlockControl) e.getSource();
//        TransferHandler handler = block.getTransferHandler();
//        handler.exportAsDrag(block, e, TransferHandler.COPY);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        container.setCursor (Cursor.getDefaultCursor());
        if (container.selectedBlock!=null) {
            model.setCell(e, container.selectedBlock);
            container.selectedBlock = null;
            repaint();
        }
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) { }

    public void mouseMoved(MouseEvent e) { }
}
