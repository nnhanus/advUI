
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class BlockControl extends JLabel {
    BlockModel model;
    private BlockPresentation view;
    public BlockControl (int controlNum){
        super();
        model = new BlockModel(controlNum);
        view = new BlockPresentation(this);

        //use a switch to match action to button type
       // this.setTransferHandler(new ValueExportTransferHandler(controlNum));

    }
    public int getIndex (){
        return model.index;
    }
}
