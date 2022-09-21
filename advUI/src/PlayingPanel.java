import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;
public class PlayingPanel extends JPanel {

    public static TopPanel topPanel;
    public static BottomPanel bottomPanel;


    public PlayingPanel() {
        super();
        Color bgColor = new Color(0xD6EAF8);
        setBackground(bgColor);
        //setSize(new Dimension(View.Width/2, View.Height));
        setLayout(new GridLayout(2, 1));
        bottomPanel= new BottomPanel();
        TransferHandler dnd = new TransferHandler() {
            @Override
            public boolean canImport(TransferSupport support) {
                if (!support.isDrop()) {
                    return false;
                }
                //only Strings
                if (!support.isDataFlavorSupported(DataFlavor.imageFlavor)) {
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
                Icon icon;
                try {
                    icon = (Icon) transferable.getTransferData(DataFlavor.imageFlavor);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                //NEED TO KNOW WHAT CONTROLNUM TO PUT
                BlockControl block = new BlockControl(1);
                block.setIcon(icon);
                bottomPanel.add(block);
                return true;
            }
        };
        topPanel=new TopPanel();
        this.add(topPanel);
        this.add(bottomPanel);
    }

}