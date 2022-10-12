import java.awt.*;
import java.util.List;

public class Character {

    public static CharacterPresentation presentation ;
    public static CharacterModel model;
    public AnimationPanel animation;

    public Character(AnimationPanel parent, CharacterModel.Direction dir, int posX, int posY){
        model= new CharacterModel(this, dir, posX, posY);
        presentation= new CharacterPresentation(this);
        animation=parent;
    }

    public static void move(boolean isNextIf) {
        model.move(isNextIf);
    }

    public static void turn() {
        model.turn();
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
    public Image getCharImage(){
        return presentation.getCharImage();
    }
}
