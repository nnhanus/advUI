import javax.swing.*;
import java.awt.*;

public class BlockModel {
    static int level;
    static int index;

    static ImageIcon icon;

    public BlockModel(int type){
        setIndex(type);
        if ( type==1){
            icon= new ImageIcon(new ImageIcon("Icons/puzzle teal.png").getImage().getScaledInstance(75, 60, Image.SCALE_DEFAULT));
        }
        else if (type==2){
            icon= new ImageIcon(new ImageIcon("Icons/puzzle red.png").getImage().getScaledInstance(75, 60, Image.SCALE_DEFAULT));
        }
        else if (type==3){
            icon= new ImageIcon(new ImageIcon("Icons/puzzle blue.png").getImage().getScaledInstance(75, 60, Image.SCALE_DEFAULT));
        }
        else if (type==4){
            icon= new ImageIcon(new ImageIcon("Icons/puzzle yellow.png").getImage().getScaledInstance(75, 60, Image.SCALE_DEFAULT));
        }
    }
    static String [] blockType= new String[]{"Move","For Loop", "While loop", "If ___ then "};
    public static int getLevel(){
        return level;
    }
    public static String getType(){
        return blockType[index-1];
    }
    public static String checkType(int indexCheck){
        return blockType[indexCheck-1];
    }
    public static void setLevel(int newLevel){
        level =newLevel;
    }
    public static void setIndex(int newIndex){
        index = newIndex;
    }
}
