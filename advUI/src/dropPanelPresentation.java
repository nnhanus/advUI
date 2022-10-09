import javax.imageio.ImageIO;
import javax.swing.*;
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
        control.add(createButtons(), BorderLayout.EAST);


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
        //need to not clear and repaint for the delete button
        Graphics2D g2d = (Graphics2D) g.create();
        if(!control.mouseEvent) {
            clear();
            buildGrid();
        }
        Stroke standard= g2d.getStroke();
        for (cellRectangle cell : model.cells) {
            g2d.setStroke(standard);
            g2d.setColor(Color.GRAY);
            if (cell.highlight) {
                g2d.setStroke(new BasicStroke(3));
                g2d.setColor(Color.GREEN);
            }
            g2d.draw(cell);
        }
        for (int index=0;index<model.blocksPlayed.size();index++) {
            BlockControl block=model.blocksPlayed.get(index);
            cellRectangle cell = model.cells.get(index);
            cell.hasBlock=true;
            if (cell.close){
                g2d.setPaint(new TexturePaint(getIconAsImage(closeIcon),cell));
                g2d.fill(cell);
            }
            else{
                g2d.setPaint(new TexturePaint(block.getIconAsImage(),cell));
                g2d.fill(cell);
                g2d.setColor(Color.WHITE);
                g2d.setFont(new Font("Ariel", Font.BOLD, 14));
                g2d.drawString(block.getType(),cell.x+cell.width/3,cell.y+cell.height/2);
            }
            //make this prettier
        }

        g2d.dispose();
        control.mouseEvent=false;
    }

    private void clear() {
        model.cells=new ArrayList<>();
    }


    private void buildGrid() {
        int width = control.getWidth() - 80;
        int height = control.getHeight();
        int cellWidth = width / model.columnCount;
        int cellHeight = Math.min(Math.round((float) (cellWidth * (0.6))), height / model.rowCount);
        int xOffset = 20;
        int yOffset = (height - (model.rowCount * cellHeight)) / 2;
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
                        cellHeight,
                        control);
                model.cells.add(cell);
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

