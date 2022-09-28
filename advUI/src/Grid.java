import java.awt.*;
import java.util.ArrayList;

public class Grid {
    public int height = GameWindow.getDefaultHeight();
    public int width = GameWindow.getDefaultWidth()/2;
    public static int cellHeight;
    public static int cellWidth;
    public static ArrayList<Scoop> scoops = new ArrayList<>();
    public int numRows;
    public int numCols;

    public Grid(int numRows, int numCol, ArrayList<Scoop> scoopsToAdd){
        this.numCols = numCol;
        this.numRows = numRows;
        cellHeight = height/numRows;
        cellWidth = width/numCol;
        chargeScoops(scoopsToAdd);
    }

    public void chargeScoops(ArrayList<Scoop> scoopsToAdd){
        scoops = scoopsToAdd;
    }

    public void paintGrid(Graphics g){
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
