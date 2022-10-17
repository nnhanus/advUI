import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CharacterModel {

    enum Direction{
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    public int x;
    public int y;
    public int width = 70;
    public int height = 70;
    public Direction orientation;
    public List<Scoop> scoops = new ArrayList<>();

    public Character parent;
    public Grid grid;

    public CharacterModel(Character control, Direction dir, int posX, int posY){
        parent=control;
        orientation = dir;
        x = posX;
        y = posY;
    }

    //moves the character from 1 cell according to the orientation
    public void move(boolean isNextIf){
        ArrayList<Point> blocked = grid.obstaclePos;
        if (orientation == Direction.NORTH && y > 0 && !blocked.contains(new Point(x, y-1))) {
            y--;
        } else if (orientation == Direction.SOUTH && y < 3 && !blocked.contains(new Point(x, y+1))){
            y++;
        } else if (orientation == Direction.EAST && x < 3 && !blocked.contains(new Point(x+1, y))){
            x++;
        } else if (orientation == Direction.WEST && x > 0 && !blocked.contains(new Point(x-1, y))){
            x--;
        }
        if(!isNextIf)pickScoop();
    }

    //rotates the character 90° clockwise
    public void turn(){
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

    public void ifStatement() {
        if(grid.scoops.isEmpty()){return;}
        grid = parent.animation.grid;
        Scoop pickIf=null;
        Scoop largest=grid.scoops.get(0);
        for (Scoop s : grid.scoops){
            if(s.getSize()>largest.getSize()){largest=s;}
            if (s.getX() == x && s.getY() == y){
                pickIf=s;
            }
        }
        if(pickIf==null){return;}
        if(pickIf==largest){
            if (scoops.size() > 0 && scoops.get(scoops.size()-1).getSize() <= pickIf.getSize()){return;}
            scoops.add(pickIf);
            grid.scoops.remove(pickIf);
        }
    }

    //Picks up a scoop if in the same position as character and smaller than the last scoop on the cone
    public void pickScoop(){
        grid = parent.animation.grid;
        if(grid.scoops.isEmpty()){parent.animation.overplay=true;}
        for (Scoop s : grid.scoops){
            if (s.getX() == x && s.getY() == y){
                if (scoops.size() > 0){
                    if (scoops.get(scoops.size()-1).getSize() > s.getSize()){
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
