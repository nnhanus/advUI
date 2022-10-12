import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class Grid {
    public int height ;
    public int width;
    public static int cellHeight;
    public static int cellWidth;
    public static ArrayList<Scoop> scoops = new ArrayList<>();
    public static ArrayList<Point> obstacles = new ArrayList<>();
    public static Hashtable<Point, Integer> obs = new Hashtable<>();
    public int numRows;
    public int numCols;
    public GameWindow frame;
    public BufferedImage table = null;
    public BufferedImage cart = null;
    public BufferedImage umbrella = null;
    public BufferedImage flowers = null;

    public ArrayList<BufferedImage> images = new ArrayList<>();

    public Grid(int numRows, int numCol, ArrayList<Scoop> scoopsToAdd, ArrayList<Point> obstaclesToAdd, GameWindow parent){
        frame=parent;
        this.numCols = numCol;
        this.numRows = numRows;
        height = frame.getHeight();
        width = frame.getWidth()/2;
        cellHeight = height/4; //We might want to make the cells a uniform size and just have the grid fill up less of the panel space
        cellWidth = width/4;
        chargeScoops(scoopsToAdd);
        obstacles = obstaclesToAdd;
        try{
            table = ImageIO.read(new File("advUI/Icons/table.png"));
            cart = ImageIO.read(new File("advUI/Icons/iceCreamCart.png"));
            umbrella = ImageIO.read(new File("advUI/Icons/umbrella.png"));
            flowers = ImageIO.read(new File("advUI/Icons/flowers.png"));
        } catch (IOException ex) {
            System.out.println("Missing file");
        }
        images.add(table);
        images.add(flowers);
        images.add(umbrella);
        images.add(cart);
        Random rand = new Random();
        for (int i = 0; i < obstacles.size(); i++) {
            obs.put(obstacles.get(i), rand.nextInt(4));
        }
    }

    public void chargeScoops(ArrayList<Scoop> scoopsToAdd){
        scoops = scoopsToAdd;
    }

    public void paintGrid(Graphics g){
        height = frame.getHeight() - 15;
        width= frame.getWidth()/2;
        cellHeight = height/4; //We might want to make the cells a uniform size and just have the grid fill up less of the panel space
        cellWidth = width/4;
        for (int i = 0; i < numRows; i++){
            g.drawLine(0, cellHeight*i, width, cellHeight*i);
        }
        for (int i = 0; i < numCols; i++){
            g.drawLine(cellWidth*i, 0, cellWidth*i, height);
        }
        for (Scoop scoop : scoops){
            g.drawImage(scoop.getImage(), Grid.getCellWidth()*scoop.getX()+Grid.getCellWidth()/4,Grid.getCellHeight()*scoop.getY()/**+Grid.getCellHeight()/4**/, scoop.getSize(), scoop.getSize(), null );
        }
        for (Point point : obstacles){
            g.drawImage(images.get(obs.get(point)), Grid.getCellWidth()*point.x, Grid.getCellHeight()*point.y, Grid.getCellWidth(), Grid.getCellHeight(), null);
        }
    }

    public static int getCellHeight(){
        return cellHeight;
    }

    public static int getCellWidth(){
        return cellWidth;
    }





}
