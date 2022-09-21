import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;

public class dropPanelPresentation {

    public dropPanel control;
    private static dropPanelModel model;

    public dropPanelPresentation(dropPanel controller){
        control=controller;
        model=control.getModel();
        createUI();
    }

    public void createUI(){
        //make play and redo clickable
        //when redo is clicked, clear panel and repaint, call clearList
        control.makeBtnClick();
        control.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(2,1));
        buttonPanel.add(model.play);
        buttonPanel.add(model.redo);
        control.add(buttonPanel, BorderLayout.EAST);
        model.dropField=new JPanel();
        model.dropField.setBorder(new TitledBorder("Drag block onto this panel"));
        TransferHandler DND= control.createTransferHandle(model.dropField);
        model.dropField.setTransferHandler(DND);
        // dropField.setTransferHandler(new ValueImportTransferHandler());
        new MyDropTargetListener(model.dropField, control);
        control.add(model.dropField, BorderLayout.CENTER);


    }


}
