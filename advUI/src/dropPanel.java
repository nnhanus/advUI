import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.*;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// create small zones with drop listeners that know where they are
public class dropPanel extends JPanel implements MouseListener, MouseMotionListener {

    public static dropPanelModel model;
    public dropPanelPresentation view;

    public boolean drag = false;

    public dropPanel(){
        this.model= new dropPanelModel();
        this.view= new dropPanelPresentation(this);

    }
public dropPanelModel getModel(){
        return model;
}

    public static void addAction(String action){
        model.addAction(action);
    }
    public static void clearList(){
        model.clearList();
    }

    void makeBtnClick() {
        model.play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                //add function to pass list to character for actions
            }
        });

        model.redo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // super.mouseClicked(e);
                model.dropField.removeAll();
                repaint();
                clearList();
            }
        });

    }

    TransferHandler createTransferHandle(JPanel panel) {
        TransferHandler dnd = new TransferHandler() {
            @Override
            public boolean canImport(TransferSupport support) {
                if (!support.isDrop()) {
                    return false;
                }
                //only Strings
                if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    return false;
                }
                return true;
            }

            @Override
            public boolean importData(TransferSupport support) {
                if (!canImport(support)) {
                    return false;
                }

                Transferable transferable = support.getTransferable();
                String index;
                try {
                    index = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                panel.add(new BlockControl(Integer.parseInt(index)));
                return true;
            }
        };
        return dnd;
    }

    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) {
        drag=true;
        BlockControl block = (BlockControl) e.getSource();
        TransferHandler handler = block.getTransferHandler();
        handler.exportAsDrag(block, e, TransferHandler.COPY);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drag=false;
        PlayingPanel.topPanel.resetBtns();
    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {
        if (drag == true) {
            JComponent jc = (JComponent)e.getSource();
            jc.setLocation(jc.getX()+e.getX(), jc.getY()+e.getY());
        }
    }

    public void mouseMoved(MouseEvent e) {}
}
