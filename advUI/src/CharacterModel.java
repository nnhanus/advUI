import java.util.ArrayList;
import java.util.List;

public class CharacterModel {
    enum Direction{
        NORTH,
        SOUTH,
        EAST,
        WEST
    }
    public static Direction orientation;
    public static int x;
    public static int y;
    public static int width = 70;
    public static int height = 70;
    public List<Scoop> scoops = new ArrayList<>();
    public static Grid grid;

    public static Character parent;
    public CharacterModel(Character control, Direction dir, int posX, int posY){
        parent=control;
        orientation = dir;
        x = posX;
        y = posY;
    }

    //moves the character from 1 cell according to the orientation
    public void move(boolean isNextIf){
        if (orientation == Direction.NORTH && y > 0) {
            y--;
            parent.presentation.updateCharacterNorth();
        } else if (orientation == Direction.SOUTH && y < 2){
            y++;
            parent.presentation.updateCharacterSouth();
        } else if (orientation == Direction.EAST && x < 2){
            x++;
            parent.presentation.updateCharacterWest();
        } else if (orientation == Direction.WEST && x > 0){
            x--;
            parent.presentation.updateCharacterEast();
        }
        if(!isNextIf)pickScoop();
    }

    //rotates the character 90° clockwise
    public static void turn(){
        if (orientation == Direction.NORTH){
            orientation = Direction.EAST;
        } else if (orientation == Direction.WEST){
            orientation = Direction.NORTH;
        } else if (orientation == Direction.SOUTH){
            orientation = Direction.WEST;
        } else if (orientation == Direction.EAST){
            orientation = Direction.SOUTH;
        }
    }

    public void ifStatement (boolean condition, Scoop s){
        if (condition){
            scoops.add(s);
            Grid.scoops.remove(s);
        }
    }

    //Picks up a scoop if in the same position as character and smaller than the last scoop on the cone
    public void pickScoop(){
        grid = parent.animation.grid;
        for (Scoop s : grid.scoops){
            if (s.x == x && s.y == y){
                if (scoops.size() > 0){
                    if (scoops.get(scoops.size()-1).size > s.size){
                        scoops.add(s);
                        grid.scoops.remove(s);
                        break;
                    }
                } else {
                    scoops.add(s);
                    grid.scoops.remove(s);
                    break;
                }
            }
        }
    }

}
