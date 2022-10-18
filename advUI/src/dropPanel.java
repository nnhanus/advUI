import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

// create small zones with drop listeners that know where they are and then insert in list
public class dropPanel extends JPanel  {

    public static dropPanelModel model;
    public dropPanelPresentation view;
    public AnimationPanel animation;
    public PlayingPanel container;
   // public boolean mouseEvent;
    public int draggedBlockIndex = -1;
    boolean firstCreated = true;

    public dropPanel(PlayingPanel parent) {
        container = parent;
        this.model = new dropPanelModel(this);
        this.view = new dropPanelPresentation(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        view.paint(g);
    }

    public dropPanelModel getModel() {
        return model;
    }

    public void buildGrid(){
        view.buildGrid();
    }


    public void readList() {
        //mouseEvent = true;
        animation = GameWindow.getAnimation();
        Thread doActions = new Thread(new readActionThread(animation, this));
        Thread paintMove = new Thread(new paintMove(animation, this));
        paintMove.start();
        doActions.start();
    }



    public void clearAll() {
        model.blocksPlayed.clear();
        model.cells.clear();
        model.actionList.clear();
    }

     List<cellRectangle> getCells() {
        return model.cells;
    }

    void setCell(MouseEvent e, BlockControl selectedBlock) {
        model.setCell(e, new BlockControl(selectedBlock));
    }

    List<String> getActions() {
        return model.actionList;
    }

    List<BlockControl> getBlocksPlayed() {
        return model.blocksPlayed;
    }
    PlayingPanel getContainer(){
        return container;
    }

    public void clearCells() {
        model.cells=new ArrayList<>();
    }

    public void clearList() {
        model.clearList();
    }

    public int getColCount() {
        return model.columnCount;
    }

    public int getRowCount() {
        return model.rowCount;
    }

    public void setWidth(int width) {
        model.width=width;
    }

    public void setHeight(int height) {
        model.height=height;
    }

    public void setCellWidth(int cellWidth) {
        model.cellWidth=cellWidth;
    }

    public void setCellHeight(int cellHeight) {
        model.cellHeight=cellHeight;
    }

    public void setxOffset(int xOffset) {
        model.xOffset=xOffset;
    }

    public void setyOffset(int yOffset) {
        model.yOffset=yOffset;
    }
}
