import java.awt.*;
import java.util.ArrayList;

public class Grid {
    public int height ;
    public int width;
    public static int cellHeight;
    public static int cellWidth;
    public static ArrayList<Scoop> scoops = new ArrayList<>();
    public int numRows;
    public int numCols;
    public GameWindow frame;

    public Grid(int numRows, int numCol, ArrayList<Scoop> scoopsToAdd, GameWindow parent){
        frame=parent;
        this.numCols = numCol;
        this.numRows = numRows;
        height = frame.getHeight();
        width= frame.getWidth()/2;
        cellHeight = height/3; //We might want to make the cells a uniform size and just have the grid fill up less of the panel space
        cellWidth = width/3;
        chargeScoops(scoopsToAdd);
    }

    public void chargeScoops(ArrayList<Scoop> scoopsToAdd){
        scoops = scoopsToAdd;
    }

    public void paintGrid(Graphics g){
        height = frame.getHeight()-70;
        width= frame.getWidth()/2;
        cellHeight = height/3; //We might want to make the cells a uniform size and just have the grid fill up less of the panel space
        cellWidth = width/3;
        for (int i = 0; i < numRows; i++){
            g.drawLine(0, cellHeight*i, width, cellHeight*i);
        }
        for (int i = 0; i < numCols; i++){
            g.drawLine(cellWidth*i, 0, cellWidth*i, height);
        }
        for (Scoop scoop : scoops){
            g.drawImage(scoop.getImage(), Grid.getCellWidth()*scoop.getX()+Grid.getCellWidth()/4,Grid.getCellHeight()*scoop.getY()/**+Grid.getCellHeight()/4**/, scoop.getSize(), scoop.getSize(), null );
        }
    }

    public static int getCellHeight(){
        return cellHeight;
    }

    public static int getCellWidth(){
        return cellWidth;
    }





}
