import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.border.TitledBorder;

public class dropPanelPresentation {
    public JLabel play = new JLabel(new ImageIcon(new ImageIcon("advUI/Icons/play.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
    public JLabel redo = new JLabel(new ImageIcon(new ImageIcon("advUI/Icons/redo.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
    public JLabel clear = new JLabel(new ImageIcon(new ImageIcon("advUI/Icons/close.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));

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
        control.setLayout(new BorderLayout());
        control.add(createButtons(), BorderLayout.EAST);


    }

    private JPanel createButtons() {
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                control.readList();
                model.clearList();
                model.blocksPlayed.clear();
                control.repaint();
                control.animation.endOfLevelMessage();
            }
        });
        redo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model.clearList();
                model.blocksPlayed.clear();
                clear();
                control.container.container.changeLevel(control.container.container.getLevelNumber());

            }
        });
        clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model.clearList();
                model.blocksPlayed.clear();
                clear();
                control.repaint();
            }
        });
        JPanel buttonPanel = new JPanel(new GridLayout(3,1));
        buttonPanel.add(play);
        buttonPanel.add(clear);
        buttonPanel.add(redo);

        return  buttonPanel;
    }


    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        clear();
        int width = control.getWidth()-80;
        int height = control.getHeight();
        int cellWidth = width / model.columnCount;
        int cellHeight = Math.min(Math.round((float)(cellWidth*(0.6))),height / model.rowCount);
        int xOffset = 20;
        int yOffset = (height - (model.rowCount * cellHeight)) / 2;
        model.width=width;
        model.height = height;
        model.cellWidth = cellWidth;
        model.cellHeight = cellHeight;
        model.xOffset=xOffset;
        model.yOffset=yOffset;
            for (int row = 0; row < model.rowCount; row++) {
                for (int col = 0; col < model.columnCount; col++) {
                    Rectangle cell = new Rectangle(
                            xOffset + (col * cellWidth),
                            yOffset + (row * cellHeight),
                            cellWidth,
                            cellHeight);
                    model.cells.add(cell);
                }
            }

        for (int index=0;index<model.blocksPlayed.size();index++) {
            BlockControl block=model.blocksPlayed.get(index);
            Rectangle cell = model.cells.get(index);
            g2d.setPaint(new TexturePaint(block.getIconAsImage(),cell));
            //make this prettier
            g2d.fill(cell);

        }

        g2d.setColor(Color.GRAY);
        for (Rectangle cell : model.cells) {
            g2d.draw(cell);
        }

        g2d.dispose();
    }

    private void clear() {
        model.cells=new ArrayList<>();
    }
}
