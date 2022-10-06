import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Level {
    int number;
    int numRows = 4;
    int numCols = 4;
    ArrayList<Scoop> scoops = new ArrayList<>();
    int posX;
    int posY;
    CharacterModel.Direction orientation;
    ArrayList<Point> blockedCells = new ArrayList<>();

    public Level(int number){
        BufferedImage mintChoco = null;
        BufferedImage funfetti = null;
        try{
            mintChoco = ImageIO.read(new File("advUI/Icons/mintChoco.png"));
            funfetti = ImageIO.read(new File("advUI/Icons/funfetti.png"));
        } catch (IOException ex) {
            System.out.println("Missing file");
        }
        this.number = number;
        if (number==1){ //Move
            posX = 0;
            posY = 1;
            orientation = CharacterModel.Direction.EAST;
            scoops.add(new Scoop(2, 1, 40, mintChoco));
            blockedCells.add(new Point(0, 0));
            blockedCells.add(new Point(1, 0));
            blockedCells.add(new Point(2, 0));
            blockedCells.add(new Point(3, 0));
            blockedCells.add(new Point(0, 2));
            blockedCells.add(new Point(1, 2));
            blockedCells.add(new Point(2, 2));
            blockedCells.add(new Point(3, 2));
            blockedCells.add(new Point(0, 3));
            blockedCells.add(new Point(1, 3));
            blockedCells.add(new Point(2, 3));
            blockedCells.add(new Point(3, 3));
        }
        if (number==2){ //Move + Turn
            posX = 1;
            posY = 1;
            orientation = CharacterModel.Direction.NORTH;
            scoops.add(new Scoop(2,2,40, mintChoco));
            blockedCells.add(new Point(0, 0));
            blockedCells.add(new Point(1, 0));
            blockedCells.add(new Point(2, 0));
            blockedCells.add(new Point(3, 0));

            blockedCells.add(new Point(0, 3));
            blockedCells.add(new Point(1, 3));
            blockedCells.add(new Point(2, 3));
            blockedCells.add(new Point(3, 3));
        }
        if (number==3){ //For loop + 2nd scoop introduced
           // numRows = 3;
            //numCols = 3;
            posX = 1;
            posY = 1;
            orientation = CharacterModel.Direction.NORTH;
            scoops.add(new Scoop(2,0,40, mintChoco));
            scoops.add(new Scoop(0,2, 30, funfetti));
        }

        if (number==4){ //If loop
            //numRows = 1;
            //numCols = 4;
            posX = 0;
            posY = 0;
            orientation = CharacterModel.Direction.EAST;
            scoops.add(new Scoop(3, 0, 40, mintChoco));
            scoops.add(new Scoop(2, 0, 30, funfetti));
        }
        if (number==5){
           // numRows = 1;
            //numCols = 1;
        }
    }

    public int getNumber(){
        return number;
    }

   public int getNumCols() {
        return numCols;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public CharacterModel.Direction getOrientation() {
        return orientation;
    }

    public ArrayList<Scoop> getScoops() {
        return scoops;
    }

    public ArrayList<Point> getBlockedCells(){
        return blockedCells;
    }
}
