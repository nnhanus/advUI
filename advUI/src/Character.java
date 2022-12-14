import java.awt.*;
import java.util.List;

public class Character {

    public static CharacterPresentation presentation ;
    public static CharacterModel model;
    public AnimationPanel animation;

    public Character(AnimationPanel parent, CharacterModel.Direction dir, int posX, int posY, String character){
        model= new CharacterModel(this, dir, posX, posY);
        presentation= new CharacterPresentation(this, character);
        animation=parent;
    }

    public void move(boolean isNextIf) {
        model.move(isNextIf);
    }

    public void turn() {
        model.turn();
    }

    public String getPath(){
        return presentation.getCharacterPath();
    }

    public int getWidth() {
        return model.width;
    }

    public int getHeight() {
        return model.height;
    }

    public CharacterModel.Direction getOrientation() {
        return model.orientation;
    }

    public int getX() {
        return model.x;
    }

    public int getY() {
        return model.y;
    }

    public List<Scoop> getScoops() {
        return model.scoops;
    }
    public static Image getCharImage(){
        return presentation.getCharImage();
    }

    public void ifStatement() {
        model.ifStatement();
    }
}
