import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CharacterPresentation {
    private BufferedImage character = null;
    private BufferedImage cone = null;
    private BufferedImage north = null;
    private BufferedImage south = null;
    private BufferedImage east = null;
    private BufferedImage west = null;
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
            north = ImageIO.read(new File("advUI/Icons/north.png"));
            south = ImageIO.read(new File("advUI/Icons/south.png"));
            east =  ImageIO.read(new File("advUI/Icons/east.png"));
            west = ImageIO.read(new File("advUI/Icons/west.png"));

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

    //draws an arrow to indicate the orientation of the character
    private void drawOrientation(Graphics pen) {
        if (control.getOrientation() == CharacterModel.Direction.NORTH){
            pen.drawImage(north, currentX + control.getWidth()/2 - 15, currentY - 30, 30, 30, null);
        } else if (control.getOrientation() == CharacterModel.Direction.SOUTH){
            pen.drawImage(south, currentX + control.getWidth()/2 - 15, currentY + control.getHeight(), 30, 30, null);
        } else if (control.getOrientation() == CharacterModel.Direction.WEST){
            pen.drawImage(west, currentX - 30 , currentY + control.getHeight()/2, 30, 30, null);
        } else if (control.getOrientation() == CharacterModel.Direction.EAST) {
            pen.drawImage(east, currentX + control.getWidth(), currentY + control.getHeight()/2, 30, 30, null);
        }
    }

    public Image getCharImage() {
        return character;
    }
}
