import javax.swing.*;
import java.awt.*;
import javax.swing.border.TitledBorder;

public class dropPanelPresentation {
    public JLabel play = new JLabel(new ImageIcon(new ImageIcon("advUI/Icons/play.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
    public JLabel redo = new JLabel(new ImageIcon(new ImageIcon("advUI/Icons/redo.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
    public JPanel dropField;
    public dropPanel control;

    public dropPanelPresentation(dropPanel controller){
        control=controller;
        createUI();
    }

    public void createUI(){
        control.setLayout(new BorderLayout());
        JPanel buttonPanel = new JPanel(new GridLayout(2,1));
        buttonPanel.add(play);
        buttonPanel.add(redo);
        control.add(buttonPanel, BorderLayout.EAST);
        dropField=new JPanel();
        dropField.setBorder(new TitledBorder("Drag block onto this panel"));
        TransferHandler DND= control.createTransferHandle(dropField);
        dropField.setTransferHandler(DND);
        // dropField.setTransferHandler(new ValueImportTransferHandler());
        new MyDropTargetListener(dropField, control);
        control.add(dropField, BorderLayout.CENTER);


    }
}
