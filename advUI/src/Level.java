import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level {
    public BufferedImage table = null;
    public BufferedImage cart = null;
    public BufferedImage umbrella = null;
    public BufferedImage flowers = null;
    public BufferedImage ground =null;
    public BufferedImage grass = null;
    int number;
    int numRows = 4;
    int numCols = 4;
    ArrayList<Scoop> scoops = new ArrayList<>();
    int posX;
    int posY;
    CharacterModel.Direction orientation;
    ArrayList<BufferedImage> background = new ArrayList<>(16);
    ArrayList<Point> blockedCells = new ArrayList<>();

    public Level(int number){
        BufferedImage mintChoco = null;
        BufferedImage funfetti = null;
        try{
            mintChoco = ImageIO.read(new File("advUI/Icons/mintChoco.png"));
            funfetti = ImageIO.read(new File("advUI/Icons/funfetti.png"));
            table = ImageIO.read(new File("advUI/Icons/table.png"));
            cart = ImageIO.read(new File("advUI/Icons/iceCreamCart.png"));
            umbrella = ImageIO.read(new File("advUI/Icons/umbrella.png"));
            flowers = ImageIO.read(new File("advUI/Icons/flowers.png"));
            ground =ImageIO.read(new File("advUI/Icons/ground.jpg"));
            grass= ImageIO.read(new File("advUI/Icons/grass.jpg"));
        } catch (IOException ex) {
            System.out.println("Missing file");
        }
        this.number = number;

        if (number==1){ //Move
            posX = 0;
            posY = 1;
            orientation = CharacterModel.Direction.EAST;
            scoops.add(new Scoop(2, 1, 40, mintChoco));
            int[] fullRows={0,2,3};
            for(int i=0; i<fullRows.length;i++){
                for(int n=0; n<4;n++){
                    blockedCells.add(new Point(n,fullRows[i]));
                }
            }
            int extraNum=blockedCells.size()/3;
            List<Integer> extras=getObstaclePos(extraNum,new int[]{0,1,2,3,8,9,10,11,12,13,14,15});
            
            for(int i=0;i<16;i++){
                if(i>3&&i<8){
                    background.add(ground);
                }
                else if(extras.contains(i)){
                    extraNum--;
                    background.add(getObstacle(extraNum));

                }
                else{
                    background.add(grass);
                }
            }


        }
        if (number==2){ //Move + Turn
            posX = 1;
            posY = 1;
            orientation = CharacterModel.Direction.NORTH;
            scoops.add(new Scoop(2,2,40, mintChoco));
            int[] fullRows={0,3};
            for(int i=0; i<fullRows.length;i++){
                for(int n=0; n<4;n++){
                    blockedCells.add(new Point(n,fullRows[i]));
                }
            }
            int extraNum=blockedCells.size()/2;
            List<Integer> extras=getObstaclePos(extraNum,new int[]{0,1,2,3,12,13,14,15});

            for(int i=0;i<16;i++){
                if(i>3&&i<12){
                    background.add(ground);
                }
                else if(extras.contains(i)){
                    extraNum--;
                    background.add(getObstacle(extraNum));
                }
                else{
                    background.add(grass);
                }
            }
        }
        if (number==3){ //For loop + 2nd scoop introduced
            posX = 1;
            posY = 1;
            orientation = CharacterModel.Direction.SOUTH;
            scoops.add(new Scoop(1,0,40, mintChoco));
            scoops.add(new Scoop(3,0, 30, funfetti));
            for(int n=0; n<4;n++){
                blockedCells.add(new Point(0,n));
            }
            for(int n=1; n<4;n++){
                blockedCells.add(new Point(n,3));
            }
            blockedCells.add(new Point(2,1));
            int extraNum=blockedCells.size()/2;
            List<Integer> extras=getObstaclePos(extraNum,new int[]{0,4,8,12,13,14,15});
            for(int i=0;i<16;i++){
                if((i>0&&i<4)||(i==5)||(i==7)||(i>8&&i<12)){
                    background.add(ground);
                }
                else if(extras.contains(i)){
                    extraNum--;
                    background.add(getObstacle(extraNum));

                }
                else{
                    background.add(grass);
                }
            }
        }

        if (number==4){ //If loop
            posX = 0;
            posY = 1;
            orientation = CharacterModel.Direction.EAST;
            scoops.add(new Scoop(3, 1, 40, mintChoco));
            scoops.add(new Scoop(2, 1, 30, funfetti));
            int[] fullRows={0,2,3};
            for(int i=0; i<fullRows.length;i++){
                for(int n=0; n<4;n++){
                    blockedCells.add(new Point(n,fullRows[i]));
                }
            }

            int extraNum=blockedCells.size()/3;
            List<Integer> extras=getObstaclePos(extraNum,new int[]{0,1,2,3,8,9,10,11,12,13,14,15});

            for(int i=0;i<16;i++){
                if(i>3&&i<8){
                    background.add(ground);
                }
                else if(extras.contains(i)){
                    extraNum--;
                    background.add(getObstacle(extraNum));

                }
                else{
                    background.add(grass);
                }
            }
        }
        if (number==5){
           posX = 3;
           posY = 0;
           orientation = CharacterModel.Direction.NORTH;
           scoops.add(new Scoop(1, 1, 40, mintChoco));
           scoops.add(new Scoop(3, 3, 30, funfetti));
           blockedCells.add(new Point(2, 1));
           blockedCells.add(new Point(2, 2));
           blockedCells.add(new Point(1, 2));

            int extraNum=2;
            List<Integer> extras=getObstaclePos(extraNum,new int[]{6,9,10});

            for(int i=0;i<16;i++){
                if(i!=6&&i!=9&&i!=10){
                    background.add(ground);
                }
                else if(extras.contains(i)){
                    extraNum--;
                    background.add(getObstacle(extraNum));
                }
                else{
                    background.add(grass);
                }
            }
        }
    }

    private List<Integer> getObstaclePos(int extraNum, int[] available) {
        Random rand = new Random();
        List<Integer> extras=new ArrayList<>(extraNum);
        for(int i=0;i<extraNum;i++){
            extras.add(available[rand.nextInt(available.length-1)]);
        }
        return extras;
    }

    private BufferedImage getObstacle(int extraNum) {
        if(extraNum==3){return cart;}
        else if(extraNum==1|| extraNum==0||extraNum==5){return flowers;}
        else if (extraNum==2||extraNum==4) {return table;}
        else{return umbrella;}
    }

   public int getNumCols() {
        return numCols;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public CharacterModel.Direction getOrientation() {
        return orientation;
    }

    public ArrayList<Scoop> getScoops() {
        return scoops;
    }

    public ArrayList<Point> getBlockedCells(){
        return blockedCells;
    }

    public List<BufferedImage> getBackground() {
        background.add(grass);
        return background;
    }
}
