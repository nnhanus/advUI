
import javax.swing.*;
import java.awt.datatransfer.Transferable;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BlockControl extends JLabel {
    BlockModel model;
    int typeNum;
    private BlockPresentation view;
    public BlockControl (int controlNum){
        super();
        typeNum=controlNum;
        model = new BlockModel(controlNum);
        view = new BlockPresentation(this);
        this.addMouseMotionListener(PlayingPanel.bottomPanel);
        this.addMouseListener(PlayingPanel.bottomPanel);
        this.setTransferHandler(BLOCK_TRANSFER);
        //use a switch to match action to button type
       // this.setTransferHandler(new ValueExportTransferHandler(controlNum));

    }
    static final TransferHandler BLOCK_TRANSFER = new TransferHandler( "typeNum" ) {    };
    public int getIndex (){
        return model.index;
    }
}
