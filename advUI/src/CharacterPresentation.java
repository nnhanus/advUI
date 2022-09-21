import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CharacterPresentation {
    private BufferedImage image = null;
    private int currentX;
    private int currentY;

    public CharacterPresentation(){
        try {
            image = ImageIO.read(new File("advUI/Icons/elephant.png"));
        } catch (IOException ex) {
            System.out.println("Fichier méduse manquant");
        }
        currentX = Grid.getCellWidth()*Character.x+Grid.getCellWidth()/4;
        currentY = Grid.getCellHeight()*Character.y+Grid.getCellHeight()/4;
    }


    public void paintCharacter(Graphics pen){
        pen.drawImage(image, currentX, currentY, Character.size, Character.size, null);
    }

    public void updateCharacterNorth(){
        currentY = currentY - Grid.getCellHeight();
        System.out.println(currentX + "    " + currentY);
    }

    public void updateCharacterSouth(){
        currentY = currentY + Grid.getCellHeight();
        System.out.println(currentX + "    " + currentY);
    }

    public void updateCharacterEast(){
        currentX = currentX - Grid.getCellWidth();
        System.out.println(currentX + "    " + currentY);
    }

    public void updateCharacterWest(){
        currentX = currentX + Grid.getCellWidth();
        System.out.println(currentX + "    " + currentY);
    }
}
