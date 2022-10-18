import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Animation grid
 * Draws the background of the game and the scoops
 */
public class Grid {
    //Dimensions
    public int height ;
    public int width;
    public static int cellHeight;
    public static int cellWidth;
    public int numRows = 4;
    public int numCols = 4;

    public static ArrayList<Scoop> scoops = new ArrayList<>(); //list of the scoop present on the grid
    public static ArrayList<Point> obstaclePos = new ArrayList<>(); //list of obstacles on the grid
    public static List<BufferedImage> backgroundImages = new ArrayList<>(); //list of background images

    public GameWindow frame;

    public Grid(ArrayList<Scoop> scoopsToAdd, ArrayList<Point> obstaclesToAdd,List<BufferedImage> bgImages, GameWindow parent){
        frame=parent;

        height = frame.getHeight();
        width = frame.getWidth()/2;
        cellHeight = height/4;
        cellWidth = width/4;

        scoops = scoopsToAdd;
        obstaclePos = obstaclesToAdd;
        backgroundImages = bgImages;
    }

    /**
     * Paints the grid, background images, and scoops
     * @param pen graphic context
     */
    public void paintGrid(Graphics pen){
        Graphics2D g2d = (Graphics2D) pen;

        height = frame.getHeight() - 35;
        width= frame.getWidth()/2;
        cellHeight = height/4;
        cellWidth = width/4;

        //Draws the background image for each cell
        for(int y=0;y<4;y++){
            for(int x=0;x<4;x++){
                g2d.drawImage(backgroundImages.get(16),Grid.getCellWidth()*x, Grid.getCellHeight()*y, Grid.getCellWidth(), Grid.getCellHeight(), null);
                g2d.drawImage(backgroundImages.get(x+4*y),Grid.getCellWidth()*x, Grid.getCellHeight()*y, Grid.getCellWidth(), Grid.getCellHeight(), null);
            }
        }

        //Draws the grid
        g2d.setStroke(new BasicStroke(1f));
        g2d.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < numRows; i++){
            g2d.drawLine(0, cellHeight*i, width, cellHeight*i);
        }
        for (int i = 0; i < numCols; i++){
            g2d.drawLine(cellWidth*i, 0, cellWidth*i, height);
        }

        //Draws the scoops
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
