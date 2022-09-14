// Code borrowed from https://stackoverflow.com/questions/28844574/drag-and-drop-from-jbutton-to-jcomponent-in-java
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
public class ValueImportTransferHandler extends TransferHandler {

    public static final DataFlavor SUPPORTED_DATE_FLAVOR = DataFlavor.stringFlavor;

    public ValueImportTransferHandler() {
    }

    @Override
    public boolean canImport(TransferHandler.TransferSupport support) {
        return support.isDataFlavorSupported(SUPPORTED_DATE_FLAVOR);
    }

    @Override
    public boolean importData(TransferHandler.TransferSupport support) {
        boolean accept = false;
        if (canImport(support)) {
            try {
                Transferable t = support.getTransferable();
                Object value = t.getTransferData(SUPPORTED_DATE_FLAVOR);
                if (value instanceof String) {
                  //  PlayingPanel.addActionToField(Integer.parseInt((String) value));
               /*     Component component = support.getComponent();
                    if (component instanceof JLabel) {
                        ((JLabel) component).setText(value.toString());
                        accept = true;
                    }
                */}
            } catch (Exception exp) {
                exp.printStackTrace();
            }
        }

        return accept;
    }
}