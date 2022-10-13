import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Random;

public class Grid {
    public int height ;
    public int width;
    public static int cellHeight;
    public static int cellWidth;
    public static ArrayList<Scoop> scoops = new ArrayList<>();
    public static ArrayList<Point> obstaclePos = new ArrayList<>();
    public static List<BufferedImage> backgroundImages = new ArrayList<>();
    public static Hashtable<Point, Integer> obstacleTable = new Hashtable<>();
    public int numRows;
    public int numCols;
    public GameWindow frame;
//    public BufferedImage table = null;
//    public BufferedImage cart = null;
//    public BufferedImage umbrella = null;
//    public BufferedImage flowers = null;
//    public BufferedImage ground =null;
//    public BufferedImage grass = null;
   // public BufferedImage trail = null;

    public ArrayList<BufferedImage> images = new ArrayList<>();

    public Grid(int numRows, int numCol, ArrayList<Scoop> scoopsToAdd, ArrayList<Point> obstaclesToAdd,List<BufferedImage> backgroundImages, GameWindow parent){
        frame=parent;
        this.numCols = numCol;
        this.numRows = numRows;
        height = frame.getHeight();
        width = frame.getWidth()/2;
        cellHeight = height/4; //We might want to make the cells a uniform size and just have the grid fill up less of the panel space
        cellWidth = width/4;
        chargeScoops(scoopsToAdd);
        obstaclePos = obstaclesToAdd;
        this.backgroundImages=backgroundImages;
//        try{
//            table = ImageIO.read(new File("advUI/Icons/table.png"));
//            cart = ImageIO.read(new File("advUI/Icons/iceCreamCart.png"));
//            umbrella = ImageIO.read(new File("advUI/Icons/umbrella.png"));
//            flowers = ImageIO.read(new File("advUI/Icons/flowers.png"));
//            ground =ImageIO.read(new File("advUI/Icons/ground.jpg"));
//            grass= ImageIO.read(new File("advUI/Icons/grass.jpg"));
//            //trail=ImageIO.read(new File("advUI/Icons/path.jpg"));
//        } catch (IOException ex) {
//            System.out.println("Missing file");
//        }
//        images.add(table);
//        images.add(flowers);
//        images.add(umbrella);
//        images.add(grass);
//        Random rand = new Random();
//        List<Integer> extras=new ArrayList<>(obstaclePos.size()/3);
//        for(int i=0;i<extras.size();i++){
//            extras.add(rand.nextInt(obstaclePos.size()));
//        }
//        for (int i = 0; i < obstaclePos.size(); i++) {
//            int imageIndex =4;
//            obstacleTable.put(obstaclePos.get(i), imageIndex);
//        }
//
    }

    public void chargeScoops(ArrayList<Scoop> scoopsToAdd){
        scoops = scoopsToAdd;
    }

    public void paintGrid(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        height = frame.getHeight() - 35;
        width= frame.getWidth()/2;
        cellHeight = height/4; //We might want to make the cells a uniform size and just have the grid fill up less of the panel space
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
            g2d.drawImage(scoop.getImage(), Grid.getCellWidth()*scoop.getX()+Grid.getCellWidth()/4,Grid.getCellHeight()*scoop.getY()/**+Grid.getCellHeight()/4**/, scoop.getSize(), scoop.getSize(), null );
        }
    }

    public static int getCellHeight(){
        return cellHeight;
    }

    public static int getCellWidth(){
        return cellWidth;
    }





}
