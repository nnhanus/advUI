import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.List;

public class dropPanelPresentation implements MouseListener, MouseMotionListener {
    public JLabel play = new JLabel(new ImageIcon(new ImageIcon("advUI/Resources/Icons/play.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
    public JLabel redo = new JLabel(new ImageIcon(new ImageIcon("advUI/Resources/Icons/redo.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
    public JLabel clear = new JLabel(new ImageIcon(new ImageIcon("advUI/Resources/Icons/close.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
    ImageIcon closeIcon = new ImageIcon("advUI/Resources/Icons/cancel.png");
    public dropPanel control;

    public dropPanelPresentation(dropPanel controller){
        control=controller;
        control.addMouseListener(this);
        control.addMouseMotionListener(this);
        createUI();
    }

    public void createUI(){
        //when redo is clicked, clear panel and repaint, call clearList
        control.setLayout(new BorderLayout());
        control.add(createButtons(), BorderLayout.SOUTH);
        buildGrid();
    }

    private JPanel createButtons() {
        play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                control.readList();

            }
        });
        //clear level and restart
        redo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                control.clearList();
                control.getBlocksPlayed().clear();
                clear();
                control.getContainer().getContainer().changeLevel(control.getContainer().getContainer().getLevelNumber());
                control.repaint();

            }
        });
        //clear drop panel but keep progress
        clear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                control.clearList();
                control.getBlocksPlayed().clear();
                buildGrid();
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
        if (control.firstCreated) {
            buildGrid();
            control.firstCreated = false;
        }
       //if(!control.mouseEvent) {
            //if repainting due to level change, empty and repaint grid
          //  buildGrid();
        //}
        Stroke standard= g2d.getStroke();
        //draw cell outlines
        for (cellRectangle cell : control.getCells()) {
            g2d.setColor(Color.BLACK);
            g2d.fill(cell);
            g2d.setStroke(standard);
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.draw(cell);
        }
        //fill with blocks if any
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
        //set highlights
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
        }

        g2d.dispose();
        //control.mouseEvent=false;
    }

    private void clear() {
        control.clearCells();
    }


    public void buildGrid() {
        clear();
        //build grid off current window measurements to adapt to resizing
        int width = control.getWidth()-20;
        int height = control.getHeight()-30;
        int cellWidth = width / control.getColCount();
        int cellHeight = Math.min(Math.round((float) 9*cellWidth/10 ), height / control.getRowCount());
        int xOffset = 10;
        int yOffset = 20;
        control.setWidth(width);
        control.setHeight (height);
        control.setCellWidth(cellWidth);
        control.setCellHeight(cellHeight);
        control.setxOffset(xOffset);
        control.setyOffset(yOffset);
        for (int row = 0; row < control.getRowCount(); row++) {
            for (int col = 0; col < control.getColCount(); col++) {
                cellRectangle cell = new cellRectangle(
                        xOffset + (col * cellWidth),
                        yOffset + (row * cellHeight),
                        cellWidth,
                        cellHeight);
                control.getCells().add(cell);
            }
        }
        control.repaint();
    }

    //helper function for painting that turns an ImageIcon into a buffered image
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
    public void mouseClicked(MouseEvent e) {
        //if a complete click occurs on a cell, delete any block that may be in it
        Point clicked = SwingUtilities.convertPoint(e.getComponent(),e.getPoint(),control);
        List<cellRectangle> cells=control.getCells();
        for (cellRectangle cell : cells) {
            if (cell.contains(clicked)&&cell.getHasBlock()) {
                int toDelete = cells.indexOf(cell);
                cell.setHasBlock(false);
                control.getActions().remove(toDelete);
                control.getBlocksPlayed().remove(toDelete);
                control.repaint();
            }
        }
    }


    @Override
    public void mousePressed(MouseEvent e) {
        Point point = e.getPoint();
        for (cellRectangle cell : control.getCells()) {
            if (cell.contains(point) && cell.getHasBlock()) {
                //if drag is initiated on a cell that has a block, change the cursor to the block style
                //and capture the block to pass information to wherever it is moved
                control.draggedBlockIndex = control.getCells().indexOf(cell);
                control.getContainer().selectedBlock = control.getBlocksPlayed().get(control.draggedBlockIndex);
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                Image image = control.getContainer().selectedBlock.getIcon().getImage();
                Cursor c = toolkit.createCustomCursor(image, new Point(0, 0), "block img");
                control.getContainer().getContainer().setCursor(c);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //control.mouseEvent = false;
        control.getContainer().getContainer().setCursor(Cursor.getDefaultCursor());
        if (control.getContainer().selectedBlock != null) {
            //if there was a block being dragged,and it was dropped into the grid, place it into the appropriate cell
            control.getContainer().selectedBlock.highlightOff();
            if (control.draggedBlockIndex >= 0) {
                control.getBlocksPlayed().remove(control.draggedBlockIndex);
                control.getCells().get(control.draggedBlockIndex).setHasBlock(false);
                control.getActions().remove(control.draggedBlockIndex);
                control.draggedBlockIndex = -1;
            }
            control.setCell(e, control.getContainer().selectedBlock);
            control.getContainer().selectedBlock = null;
            control.repaint();
        }
    }


    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
       // control.mouseEvent = true;
        Point mouse = e.getPoint();
        for (cellRectangle cell : control.getCells()) {
            if (cell.contains(mouse) && control.getContainer().selectedBlock == null && cell.getHasBlock()) {
                //if we arent dragging a block and the current cell contains a block, flash the close icon
                cell.setClose(true);
                control.repaint();
            } else if (cell.contains(mouse) && control.getContainer().selectedBlock != null) {
                //if we are dragging a block, highlight the cell we are over
                cell.setHighlight(true);
                control.repaint();
            } else {
                //mouse is not in cell, make idle
                cell.setClose(false);
                cell.setHighlight(false);
                control.repaint();
            }

        }
    }
}

