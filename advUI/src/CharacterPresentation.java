import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class CharacterPresentation {
    private BufferedImage character = null;
    private BufferedImage cone = null;
    private BufferedImage direction = null;
    String characterPath;
    private int currentX;
    private int currentY;
    public Character control;

    public CharacterPresentation(Character controller, String characterPath){
        control=controller;
        this.characterPath = characterPath;
        try {
            character = ImageIO.read(new File(characterPath));
            cone = ImageIO.read(new File("advUI/Icons/cone.png"));
        } catch (IOException ex) {
            System.out.println("Missing file");
        }
        currentX = Grid.getCellWidth()*control.getX()+Grid.getCellWidth()/4;
        currentY = Grid.getCellHeight()*control.getY()+Grid.getCellHeight()/4;
    }

    public String getCharacterPath(){
        return characterPath;
    }


    public void paintCharacter(Graphics pen){
        currentX = Grid.getCellWidth()*control.getX()+Grid.getCellWidth()/4;
        currentY = Grid.getCellHeight()*control.getY()+Grid.getCellHeight()/3;
        pen.drawImage(character, currentX, currentY, control.getWidth(), control.getHeight(), null);
        pen.drawImage(cone, currentX, currentY+control.getHeight()/2, control.getWidth()/3, control.getHeight()/2, null);
        for (int i = 0; i < control.getScoops().size(); i++){
            Scoop scoop = control.getScoops().get(i);
            pen.drawImage(scoop.getImage(), currentX - 5 + 5*i, currentY + scoop.getSize()/(3*(i+1)), scoop.getSize(), scoop.getSize(), null );
        }
        drawOrientation(pen);
    }

    //draws a dot to indicate the orientation of the character
    private void drawOrientation(Graphics pen) {
        try{
           if (control.getOrientation() == CharacterModel.Direction.NORTH){
               direction = ImageIO.read(new File("advUI/Icons/north.png"));
           } else if (control.getOrientation() == CharacterModel.Direction.SOUTH){
               direction = ImageIO.read(new File("advUI/Icons/south.png"));
           } else if (control.getOrientation() == CharacterModel.Direction.WEST){
               direction = ImageIO.read(new File("advUI/Icons/west.png"));
           } else if (control.getOrientation() == CharacterModel.Direction.EAST){
               direction = ImageIO.read(new File("advUI/Icons/east.png"));
           }
        } catch (Exception e){
            System.out.println("missing file");
        }
        if (control.getOrientation() == CharacterModel.Direction.NORTH){
            pen.drawImage(direction, currentX + control.getWidth()/2 - 15, currentY - 30, 30, 30, null);
        } else if (control.getOrientation() == CharacterModel.Direction.SOUTH){
            pen.drawImage(direction, currentX + control.getWidth()/2 - 15, currentY + control.getHeight(), 30, 30, null);
        } else if (control.getOrientation() == CharacterModel.Direction.WEST){
            pen.drawImage(direction, currentX - 30 , currentY + control.getHeight()/2, 30, 30, null);
        } else if (control.getOrientation() == CharacterModel.Direction.EAST) {
            pen.drawImage(direction, currentX + control.getWidth(), currentY + control.getHeight()/2, 30, 30, null);
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

    public Image getCharImage() {
        return character;
    }
}
