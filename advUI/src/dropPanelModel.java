import org.w3c.dom.css.Rect;

import javax.swing.*;
import javax.swing.SwingUtilities;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

import static javax.swing.SwingUtilities.convertPoint;

public class dropPanelModel {
    public List<String> actionList;
    public List<BlockControl> blocksPlayed ;
    public int width;
    public int height;
    public int cellWidth;
    public int cellHeight;
    int columnCount = 4;
    int rowCount = 4;
    int xOffset;
    int yOffset;
    public List<Rectangle> cells;
    public Point selectedCell;

    public JLabel play = new JLabel(new ImageIcon(new ImageIcon("advUI/Icons/play.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
    public JLabel redo = new JLabel(new ImageIcon(new ImageIcon("advUI/Icons/redo.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
    public dropPanel control;

    public dropPanelModel(dropPanel parent){
        control=parent;
        actionList = new ArrayList<>() {};
        blocksPlayed= new ArrayList<>() {};
        cells = new ArrayList<>(columnCount*rowCount);
       }
    public void clearList (){
        actionList=new ArrayList<String>();
    }

    public void addActionBlock(BlockControl block) {
        int index=selectedCell.x+selectedCell.y*columnCount;
        if(index>blocksPlayed.size()-1){index=blocksPlayed.size();}
        actionList.add(index,block.getType());
        blocksPlayed.add(index,block);
    }

    public void setCell(MouseEvent e, BlockControl block) {
        Point convertedPoint= convertPoint(e.getComponent(),e.getPoint(),control);
        int xPos= convertedPoint.x;
        int yPos=convertedPoint.y;
        if (xPos >= xOffset && yPos >= yOffset) {
            int column = (xPos - xOffset) / cellWidth;
            int row = (yPos - yOffset) / cellHeight;
            if (column >= 0 && row >= 0 && column < columnCount && row < rowCount) {
                selectedCell = new Point(column, row);
                addActionBlock(block);
            }

        }
    }
}
