import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.io.IOException;


class MyDropTargetListener extends DropTargetAdapter {

    private DropTarget dropTarget;
    private JPanel p;

    public MyDropTargetListener(JPanel panel) {
        p = panel;
        dropTarget = new DropTarget(panel, DnDConstants.ACTION_COPY, this, true, null);

    }

    @Override
    public void drop(DropTargetDropEvent event) {
        try {
            DropTarget test = (DropTarget) event.getSource();
            Component ca = (Component) test.getComponent();
            Point dropPoint = ca.getMousePosition();
            Transferable tr = event.getTransferable();

            if (event.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                String index = (String) tr.getTransferData(DataFlavor.stringFlavor);

                if (index != null) {

                    p.add(new JLabel(index));
                    p.revalidate();
                    p.repaint();
                    event.dropComplete(true);
                }
            } else {
                event.rejectDrop();
            }
        } catch (Exception e) {
            e.printStackTrace();
            event.rejectDrop();
        }
    }
}

class MyDragGestureListener implements DragGestureListener {

    @Override
    public void dragGestureRecognized(DragGestureEvent event) {
        JLabel label = (JLabel) event.getComponent();
        final String title = label.getText();


        Transferable transferable = new Transferable() {
            @Override
            public DataFlavor[] getTransferDataFlavors() {
                return new DataFlavor[]{DataFlavor.stringFlavor};
            }

            @Override
            public boolean isDataFlavorSupported(DataFlavor flavor) {
                if (!isDataFlavorSupported(flavor)) {
                    return false;
                }
                return true;
            }

            @Override
            public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
                return title;
            }
        };
        event.startDrag(null, transferable);
    }
}