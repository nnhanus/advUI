import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Grid {
    public int height ;
    public int width;
    public static int cellHeight;
    public static int cellWidth;
    public static ArrayList<Scoop> scoops = new ArrayList<>();
    public static ArrayList<Point> obstaclePos = new ArrayList<>();
    public static List<BufferedImage> backgroundImages = new ArrayList<>();
    public int numRows;
    public int numCols;
    public GameWindow frame;

    public Grid(int numRows, int numCol, ArrayList<Scoop> scoopsToAdd, ArrayList<Point> obstaclesToAdd,List<BufferedImage> backgroundImages, GameWindow parent){
        frame=parent;
        this.numCols = numCol;
        this.numRows = numRows;
        height = frame.getHeight();
        width = frame.getWidth()/2;
        cellHeight = height/4;
        cellWidth = width/4;
        chargeScoops(scoopsToAdd);
        obstaclePos = obstaclesToAdd;
        this.backgroundImages=backgroundImages;
    }

    public void chargeScoops(ArrayList<Scoop> scoopsToAdd){
        scoops = scoopsToAdd;
    }

    public void paintGrid(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        height = frame.getHeight() - 35;
        width= frame.getWidth()/2;
        cellHeight = height/4;
        cellWidth = width/4;
        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                g2d.drawImage(backgroundImages.get(16),Grid.getCellWidth()*x, Grid.getCellHeight()*y, Grid.getCellWidth(), Grid.getCellHeight(), null);
                g2d.drawImage(backgroundImages.get(x+4*y),Grid.getCellWidth()*x, Grid.getCellHeight()*y, Grid.getCellWidth(), Grid.getCellHeight(), null);
            }
        }
        g2d.setStroke(new BasicStroke(1f));
        g2d.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < numRows; i++){
            g2d.drawLine(0, cellHeight*i, width, cellHeight*i);
        }
        for (int i = 0; i < numCols; i++){
            g2d.drawLine(cellWidth*i, 0, cellWidth*i, height);
        }
        for (Scoop scoop : scoops){
            g2d.drawImage(scoop.getImage(), Grid.getCellWidth()*scoop.getX()+Grid.getCellWidth()/4,Grid.getCellHeight()*scoop.getY(), scoop.getSize(), scoop.getSize(), null );
        }
    }

    public static int getCellHeight(){
        return cellHeight;
    }

    public static int getCellWidth(){
        return cellWidth;
    }





}
