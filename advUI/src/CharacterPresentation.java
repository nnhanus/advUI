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
    public Character control;

    public CharacterPresentation(Character controller){
        control=controller;
        try {
            character = ImageIO.read(new File("advUI/Icons/elephant.png"));
            cone = ImageIO.read(new File("advUI/Icons/cone.png"));
        } catch (IOException ex) {
            System.out.println("Missing file");
        }
        currentX = Grid.getCellWidth()*control.getX()+Grid.getCellWidth()/4;
        currentY = Grid.getCellHeight()*control.getY()+Grid.getCellHeight()/4;
    }


    public void paintCharacter(Graphics pen){
        currentX = Grid.getCellWidth()*control.x+Grid.getCellWidth()/4;
        currentY = Grid.getCellHeight()*control.y+Grid.getCellHeight()/2;
        pen.drawImage(character, currentX, currentY, control.width, control.height, null);
        pen.drawImage(cone, currentX, currentY+control.height/2, control.width/3, control.height/2, null);
        for (Scoop scoop : control.scoops){
            pen.drawImage(scoop.getImage(), currentX,currentY, scoop.getSize(), scoop.getSize(), null );
        }
        drawOrientation(pen);
    }

    private void drawOrientation(Graphics pen) {
        if (control.orientation == Character.Direction.NORTH){
            pen.fillOval(currentX+control.width/2, currentY - 20, 10, 10);
        } else if (control.orientation == Character.Direction.SOUTH){
            pen.fillOval(currentX+control.width/2, currentY + control.height +10, 10, 10);
        } else if (control.orientation == Character.Direction.WEST){
            pen.fillOval(currentX - 20, currentY + control.height/2, 10, 10);
        } else if (control.orientation == Character.Direction.EAST) {
            pen.fillOval(currentX + control.width + 10, currentY + control.height / 2, 10, 10);
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
