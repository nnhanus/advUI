import java.util.List;

public class Character {

    public static CharacterPresentation presentation ;
    public static CharacterModel model;
    public AnimationPanel animation;

    public Character(AnimationPanel parent){
        model= new CharacterModel(this);
        presentation= new CharacterPresentation(this);
        animation=parent;
    }

    public static void move() {
        model.move();
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
}
