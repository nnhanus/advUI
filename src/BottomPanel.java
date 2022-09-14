import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class BottomPanel extends JPanel implements MouseListener, MouseMotionListener {

    public static boolean drag=false;
    public BottomPanel () {
        this.setBorder(new TitledBorder("Drag block onto this panel"));
        TransferHandler DND= createTransferHandle(this);
        this.setTransferHandler(DND);
        // dropField.setTransferHandler(new ValueImportTransferHandler());
        new MyDropTargetListener(this);


    }

    private TransferHandler createTransferHandle(JPanel panel) {
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
