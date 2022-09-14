import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
public class PlayingPanel extends JPanel {

    public JPanel buttonGrid;
    public static JPanel dropField;

    public PlayingPanel(){
        super();
        Color bgColor = new Color(0xD6EAF8);
        setBackground(bgColor);
        //setSize(new Dimension(View.Width/2, View.Height));
        setLayout(new GridLayout(2, 1));
        this.add(createTopPanel());
        this.add(createBottomPanel());
    }

    protected JPanel createTopPanel() {
        buttonGrid = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        //could create array of all button types. for i in 0 to level-1 create button
        for (int i=1;i<=BlockModel.getLevel();i++) {
            BlockControl controlBtn = new BlockControl(i);
            buttonGrid.add(controlBtn, gbc);

        }

        return buttonGrid;
    }

    protected JPanel createBottomPanel() {
        dropField = new JPanel(new GridLayout(1,10));
        JLabel label = new JLabel("Drop Blocks Here");
        dropField.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY), new EmptyBorder(20, 20, 20, 20)));
       // dropField.setTransferHandler(new ValueImportTransferHandler());
        dropField.add(label);
        DragListener drag = new DragListener();
        dropField.addMouseListener( drag );
        dropField.addMouseMotionListener( drag );
        return dropField;
    }
    public static void addActionToField(int actionNum){
        dropField.add(new BlockControl(actionNum));
    }

}

//create panel drop target
//drag button
//different action on drop