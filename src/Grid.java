import java.awt.*;
import java.util.ArrayList;

public class Grid {
    public int height = GameWindow.Height;
    public int width = GameWindow.Width/2;
    public int cellHeight;
    public int cellWidth;
    public static ArrayList<Scoop> scoops = new ArrayList<>();

    public Grid(){
        cellHeight = height/3;
        cellWidth = width/3;
    }

    public void paintGrid(Graphics g){
        for (int i = 0; i < 4; i++){
            g.drawLine(0, cellHeight*i, width, cellHeight*i);
        }
        for (int i = 0; i < 4; i++){
            g.drawLine(cellWidth*i, 0, cellWidth*i, height);
        }
    }





}
