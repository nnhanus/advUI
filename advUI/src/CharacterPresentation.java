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
        currentX = Grid.getCellWidth()*control.getX()+Grid.getCellWidth()/4;
        currentY = Grid.getCellHeight()*control.getY()+Grid.getCellHeight()/3;
        pen.drawImage(character, currentX, currentY, control.getWidth(), control.getHeight(), null);
        pen.drawImage(cone, currentX, currentY+control.getHeight()/2, control.getWidth()/3, control.getHeight()/2, null);
        for (int i = 0; i < control.getScoops().size(); i++){
            Scoop scoop = control.getScoops().get(i);
            pen.drawImage(scoop.getImage(), currentX + 5*i, currentY - scoop.getSize()*i, scoop.getSize(), scoop.getSize(), null );
        }
        drawOrientation(pen);
    }

    private void drawOrientation(Graphics pen) {
        if (control.getOrientation() == CharacterModel.Direction.NORTH){
            pen.fillOval(currentX+control.getWidth()/2, currentY - 20, 10, 10);
        } else if (control.getOrientation() == CharacterModel.Direction.SOUTH){
            pen.fillOval(currentX+control.getWidth()/2, currentY + control.getHeight() +10, 10, 10);
        } else if (control.getOrientation() == CharacterModel.Direction.WEST){
            pen.fillOval(currentX - 20, currentY + control.getHeight()/2, 10, 10);
        } else if (control.getOrientation() == CharacterModel.Direction.EAST) {
            pen.fillOval(currentX + control.getWidth() + 10, currentY + control.getHeight() / 2, 10, 10);
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
