import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BlockControl extends Block {

    public BlockControl (int controlNum){

        super(BlockModel.getType(controlNum));
        //use a switch to match action to button type
         this.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                TransferHandler handle = button.getTransferHandler();
                handle.exportAsDrag(button, e, TransferHandler.COPY);
            }
        });
        this.setTransferHandler(new ValueExportTransferHandler("test"));
    }
}
