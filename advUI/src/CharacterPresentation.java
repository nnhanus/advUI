import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CharacterPresentation {
    private BufferedImage character = null;
    private BufferedImage cone = null;
    private int currentX;
    private int currentY;

    public CharacterPresentation(){
        try {
            character = ImageIO.read(new File("advUI/Icons/elephant.png"));
            cone = ImageIO.read(new File("advUI/Icons/cone.png"));
        } catch (IOException ex) {
            System.out.println("Missing file");
        }
        currentX = Grid.getCellWidth()*Character.x+Grid.getCellWidth()/4;
        currentY = Grid.getCellHeight()*Character.y+Grid.getCellHeight()/4;
    }


    public void paintCharacter(Graphics pen){
        pen.drawImage(character, currentX, currentY, Character.width, Character.height, null);
        pen.drawImage(cone, currentX, currentY+Character.height/2, Character.width/3, Character.height/2, null);
        for (Scoop scoop : Character.scoops){
            pen.drawImage(scoop.getImage(), currentX,currentY, scoop.getSize(), scoop.getSize(), null );
        }
        drawOrientation(pen);
    }

    private void drawOrientation(Graphics pen) {
        if (Character.orientation == Character.Direction.NORTH){
            pen.fillOval(currentX+Character.width/2, currentY - 20, 10, 10);
        } else if (Character.orientation == Character.Direction.SOUTH){
            pen.fillOval(currentX+Character.width/2, currentY + Character.height +10, 10, 10);
        } else if (Character.orientation == Character.Direction.WEST){
            pen.fillOval(currentX - 20, currentY + Character.height/2, 10, 10);
        } else if (Character.orientation == Character.Direction.EAST){
            pen.fillOval(currentX + Character.width + 10, currentY + Character.height/2, 10, 10);
        }
    }

    public void updateCharacterNorth(){
        currentY = currentY - Grid.getCellHeight();
    }

    public void updateCharacterSouth(){
        currentY = currentY + Grid.getCellHeight();
    }

    public void updateCharacterEast(){
        currentX = currentX - Grid.getCellWidth();
    }

    public void updateCharacterWest(){
        currentX = currentX + Grid.getCellWidth();
    }

}
