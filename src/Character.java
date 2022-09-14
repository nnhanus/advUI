import java.util.ArrayList;

public class Character {
    enum Direction{
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    public Direction orientation = Direction.NORTH;
    public int x = 1;
    public int y = 1;
    public ArrayList<Scoop> scoops = new ArrayList<>();

    public Character(){}

    public void move(){
        if (orientation == Direction.NORTH && y > 0) {
            y--;
        } else if (orientation == Direction.SOUTH && y < 2){
            y++;
        } else if (orientation == Direction.WEST && x > 0){
            x--;
        } else if (orientation == Direction.EAST && x < 2){
            x++;
        }
    }

    public void turn(){
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
    public void pickScoop(){
        for (Scoop s : Grid.scoops){
            if (s.x == this.x && s.y == this.y){
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
