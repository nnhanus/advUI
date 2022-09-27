import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Character {
    enum Direction{
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    public static Direction orientation = Direction.NORTH;
    public static int x = 1;
    public static int y = 1;
    public static int width = 80;
    public static int height = 80;
    public static ArrayList<Scoop> scoops = new ArrayList<>();
    public static CharacterPresentation presentation = new CharacterPresentation();
    public AnimationPanel animation;

    public Character(AnimationPanel parent){
        animation=parent;
    }

    //moves the character from 1 cell according to the orientation
    public static void move(){
        if (orientation == Direction.NORTH && y > 0) {
            y--;
            presentation.updateCharacterNorth();
        } else if (orientation == Direction.SOUTH && y < 2){
            y++;
            presentation.updateCharacterSouth();
        } else if (orientation == Direction.EAST && x < 2){
            x++;
            presentation.updateCharacterWest();
        } else if (orientation == Direction.WEST && x > 0){
            x--;
            presentation.updateCharacterEast();
        }
        pickScoop();
    }

    //rotates the character 90° left
    public static void turn(){
        if (orientation == Direction.NORTH){
            orientation = Direction.WEST;
        } else if (orientation == Direction.WEST){
            orientation = Direction.SOUTH;
        } else if (orientation == Direction.SOUTH){
            orientation = Direction.EAST;
        } else if (orientation == Direction.EAST){
            orientation = Direction.NORTH;
        }
    }

    public void forLoop (int repetition){
        for (int i = 0; i < repetition; i++){
            move();
        }
    }

    public void whileLoop (boolean condition){
        while (condition){
            move();
        }
    }

    public void ifStatement (boolean condition, Scoop s){
        if (condition){
            scoops.add(s);
            Grid.scoops.remove(s);
        }
    }

    //Picks up a scoop if in the same position as character and smaller than the last scoop on the cone
    public static void pickScoop(){
        for (Scoop s : Grid.scoops){
            if (s.x == x && s.y == y){
                if (scoops.size() > 0){
                    if (scoops.get(scoops.size()-1).size < s.size){
                        scoops.add(s);
                        Grid.scoops.remove(s);
                        break;
                    }
                } else {
                    scoops.add(s);
                    Grid.scoops.remove(s);
                    break;
                }
            }
        }
    }

}
