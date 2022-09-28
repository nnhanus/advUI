import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Level {
    int number;
    int numRows;
    int numCols;
    static ArrayList<Scoop> scoops = new ArrayList<>();
    int posX;
    int posY;
    CharacterModel.Direction orientation;

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
        if (number==1){
            numRows = 1;
            numCols = 3;
            posX = 0;
            posY = 0;
            orientation = CharacterModel.Direction.EAST;
            scoops.add(new Scoop(2, 0, 40, mintChoco));
        }
        if (number==2){
            numRows = 3;
            numCols = 3;
            posX = 1;
            posY = 1;
            orientation = CharacterModel.Direction.NORTH;
            scoops.add(new Scoop(2,0,40, mintChoco));
            scoops.add(new Scoop(0,2, 30, funfetti));
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
}
