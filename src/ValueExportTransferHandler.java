//code borrowed from https://stackoverflow.com/questions/28844574/drag-and-drop-from-jbutton-to-jcomponent-in-java
import javax.swing.*;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
public class ValueExportTransferHandler extends TransferHandler {

    private int value;

    public ValueExportTransferHandler(int value) {
        this.value = value;
    }

    public String getValue() {
        return Integer.toString(value);
    }

    @Override
    public int getSourceActions(JComponent c) {
        return DnDConstants.ACTION_COPY_OR_MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        Transferable t = new StringSelection(getValue());
        return t;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        super.exportDone(source, data, action);
        // add associated function to list
    }

}