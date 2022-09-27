import java.awt.*;
import java.util.ArrayList;

public class Grid {
    public int height = GameWindow.Height;
    public int width = GameWindow.Width/2;
    public static int cellHeight;
    public static int cellWidth;
    public static ArrayList<Scoop> scoops = new ArrayList<>();

    public Grid(){
        cellHeight = height/3;
        cellWidth = width/3;
        scoops.add(new Scoop(2, 0, 40));

    }

    public void paintGrid(Graphics g){
        for (int i = 0; i < 4; i++){
            g.drawLine(0, cellHeight*i, width, cellHeight*i);
        }
        for (int i = 0; i < 4; i++){
            g.drawLine(cellWidth*i, 0, cellWidth*i, height);
        }
        for (Scoop scoop : scoops){
            g.drawImage(scoop.getImage(), Grid.getCellWidth()*scoop.getX()+Grid.getCellWidth()/4,Grid.getCellHeight()*scoop.getY()+Grid.getCellHeight()/4, scoop.getSize(), scoop.getSize(), null );
        }
    }

    public static int getCellHeight(){
        return cellHeight;
    }

    public static int getCellWidth(){
        return cellWidth;
    }





}
