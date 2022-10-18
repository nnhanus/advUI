import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Defines the different levels
 */
public class Level {
    //obstacle images
    public BufferedImage table = null;
    public BufferedImage cart = null;
    public BufferedImage umbrella = null;
    public BufferedImage flowers = null;
    public BufferedImage ground =null;
    public BufferedImage grass = null;
    //scoop images
    public BufferedImage mintChoco = null;
    public BufferedImage funfetti = null;
    public BufferedImage strawberry = null;
    public BufferedImage caramel = null;

    int number; //level number

    ArrayList<Scoop> scoops = new ArrayList<>();

    //initial position and orientation of the character
    int posX;
    int posY;
    CharacterModel.Direction orientation;

    ArrayList<BufferedImage> background = new ArrayList<>(16);
    ArrayList<Point> blockedCells = new ArrayList<>();

    public Level(int number){
        chargeImage();
        this.number = number;

        if (number==1){ //First level, 1 scoop, Move
            posX = 0; //Initial position
            posY = 1;
            orientation = CharacterModel.Direction.EAST; //initial orientation
            scoops.add(new Scoop(2, 1, 40, mintChoco)); //scoops
            //defining blockedCells
            int[] fullRows={0,2,3};
            for (int fullRow : fullRows) {
                for (int n = 0; n < 4; n++) {
                    blockedCells.add(new Point(n, fullRow));
                }
            }
            int extraNum=blockedCells.size()/3;
            List<Integer> extras=getObstaclePos(extraNum,new int[]{0,1,2,3,8,9,10,11,12,13,14,15});

            //Defining the background needed, grass if blocked, dirt else
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
        if (number==2){ //Level 2, 1 scoop, Move + Turn
            posX = 1;
            posY = 1;
            orientation = CharacterModel.Direction.NORTH;
            scoops.add(new Scoop(2,2,40, caramel));
            int[] fullRows={0,3};
            for (int fullRow : fullRows) {
                for (int n = 0; n < 4; n++) {
                    blockedCells.add(new Point(n, fullRow));
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
        if (number==3){ //Level 3, 2 scoops, For + Move + Turn
            posX = 1;
            posY = 1;
            orientation = CharacterModel.Direction.SOUTH;
            scoops.add(new Scoop(1,0,40, strawberry));
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

        if (number==4){ //Level 4, 2 scoops, If + For + Turn + Move
            posX = 0;
            posY = 1;
            orientation = CharacterModel.Direction.EAST;
            scoops.add(new Scoop(3, 1, 40, mintChoco));
            scoops.add(new Scoop(2, 1, 30, funfetti));
            int[] fullRows={0,2,3};
            for (int fullRow : fullRows) {
                for (int n = 0; n < 4; n++) {
                    blockedCells.add(new Point(n, fullRow));
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
        if (number==5){ //Level 5, 2 scoops, If + For + Turn + Move
           posX = 3;
           posY = 0;
           orientation = CharacterModel.Direction.NORTH;
           scoops.add(new Scoop(1, 1, 40, mintChoco));
           scoops.add(new Scoop(3, 3, 30, caramel));
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

    /**
     * Charge the different needed images
     */
    private void chargeImage() {
        try{
            mintChoco = ImageIO.read(new File("advUI/Icons/mintChoco.png"));
            funfetti = ImageIO.read(new File("advUI/Icons/funfetti.png"));
            strawberry = ImageIO.read(new File("advUI/Icons/strawberry.png"));
            caramel = ImageIO.read(new File("advUI/Icons/caramel.png"));
            table = ImageIO.read(new File("advUI/Icons/table.png"));
            cart = ImageIO.read(new File("advUI/Icons/iceCreamCart.png"));
            umbrella = ImageIO.read(new File("advUI/Icons/umbrella.png"));
            flowers = ImageIO.read(new File("advUI/Icons/flowers.png"));
            ground =ImageIO.read(new File("advUI/Icons/ground.jpg"));
            grass= ImageIO.read(new File("advUI/Icons/grass.jpg"));
        } catch (IOException ex) {
            System.out.println("Missing file");
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

    /**
     * Get obstacle corresponding to number
     * @param extraNum id of the obstacle
     * @return the corresponding obstacle
     */
    private BufferedImage getObstacle(int extraNum) {
        if(extraNum==3){return cart;}
        else if(extraNum==1|| extraNum==0||extraNum==5){return flowers;}
        else if (extraNum==2||extraNum==4) {return table;}
        else{return umbrella;}
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
