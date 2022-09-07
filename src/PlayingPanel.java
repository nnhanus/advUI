import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
public class PlayingPanel extends JPanel {

    public JPanel buttonGrid;

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
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;
        //could create array of all button types. for i in 0 to level-1 create button
        Move moveBtn = new Move();
        panel.add(moveBtn, gbc);
        moveBtn.setTransferHandler(new ValueExportTransferHandler("test"));

        moveBtn.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                TransferHandler handle = button.getTransferHandler();
                handle.exportAsDrag(button, e, TransferHandler.COPY);
            }
        });

        return panel;
    }

    protected JPanel createBottomPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel label = new JLabel("Drop Blocks Here");
        label.setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY), new EmptyBorder(20, 20, 20, 20)));
        label.setTransferHandler(new ValueImportTransferHandler());
        panel.add(label);
        return panel;
    }

}
