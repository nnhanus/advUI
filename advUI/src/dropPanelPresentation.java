import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class dropPanelPresentation {
    public JLabel play = new JLabel(new ImageIcon(new ImageIcon("advUI/Icons/play.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
    public JLabel redo = new JLabel(new ImageIcon(new ImageIcon("advUI/Icons/redo.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
    public JLabel clear = new JLabel(new ImageIcon(new ImageIcon("advUI/Icons/close.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
    ImageIcon closeIcon = new ImageIcon("advUI/Icons/cancel.png");
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
        control.add(createButtons(), BorderLayout.SOUTH);


    }

    private JPanel createButtons() {
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                control.readList();

            }
        });
        redo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model.clearList();
                control.getBlocksPlayed().clear();
                clear();
                control.getContainer().getContainer().changeLevel(control.getContainer().getContainer().getLevelNumber());

            }
        });
        clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model.clearList();
                control.getBlocksPlayed().clear();
                clear();
                control.repaint();
            }
        });
        GridLayout layout = new GridLayout(1,3);
        JPanel buttonPanel = new JPanel(layout);
        buttonPanel.add(play);
        buttonPanel.add(clear);
        buttonPanel.add(redo);
        buttonPanel.setBorder(new EmptyBorder(0,0,5,0));
        return  buttonPanel;
    }


    public void paint(Graphics g) {
        //need to not clear and repaint for the delete button
        Graphics2D g2d = (Graphics2D) g.create();
        if(!control.mouseEvent) {
            clear();
            buildGrid();
        }
        Stroke standard= g2d.getStroke();
        for (cellRectangle cell : control.getCells()) {
            g2d.setColor(Color.BLACK);
            g2d.fill(cell);
            g2d.setStroke(standard);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.draw(cell);
        }
        for (int index=0;index<control.getBlocksPlayed().size();index++) {
            BlockControl block = control.getBlocksPlayed().get(index);
            cellRectangle cell = control.getCells().get(index);
            cell.setHasBlock(true);
                g2d.setPaint(new TexturePaint(block.getIconAsImage(), cell));
                g2d.fill(cell);
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Ariel", Font.BOLD, 14));
                String name=block.getType()+" "+block.getForLoopIter();
                g2d.drawString(name, cell.x + cell.width / 2 - g2d.getFontMetrics().stringWidth(name) / 2, cell.y + cell.height / 2);
            if (cell.getClose()) {
                g2d.drawImage(getIconAsImage(closeIcon),cell.x+3*cell.width/4,cell.y+3,cell.width/4,cell.height/4,null);
            }
        }
        for (cellRectangle cell : control.getCells()) {
            if (cell.getHighlight()) {
                g2d.setStroke(new BasicStroke(3));
                g2d.setColor(Color.GREEN);
                g2d.draw(cell);
            }
            if (cell.getReadHighlight()){
                g2d.setStroke(new BasicStroke(3));
                g2d.setColor(Color.GREEN);
                g2d.draw(cell);
            }
            //make this prettier
        }

        g2d.dispose();
        //control.mouseEvent=false;
    }

    private void clear() {
        control.clearCells();
    }


    private void buildGrid() {
        int width = control.getWidth()-20;
        int height = control.getHeight()-30;
        int cellWidth = width / model.columnCount;
        int cellHeight = Math.min(Math.round((float) 9*cellWidth/10 ), height / model.rowCount);
        int xOffset = 10;
        int yOffset = 20;
        model.width = width;
        model.height = height;
        model.cellWidth = cellWidth;
        model.cellHeight = cellHeight;
        model.xOffset = xOffset;
        model.yOffset = yOffset;
        for (int row = 0; row < model.rowCount; row++) {
            for (int col = 0; col < model.columnCount; col++) {
                cellRectangle cell = new cellRectangle(
                        xOffset + (col * cellWidth),
                        yOffset + (row * cellHeight),
                        cellWidth,
                        cellHeight/*,
                        /*control*/);
                control.getCells().add(cell);
            }
        }
    }
    public BufferedImage getIconAsImage(ImageIcon icon) {
        BufferedImage bufferedIcon = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedIcon.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();
        return bufferedIcon;
    }
}

