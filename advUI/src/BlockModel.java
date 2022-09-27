import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class BlockModel {
    static int level = GameWindow.getLevel();
    static int index;
    static List<String> blockType= new ArrayList<String>();

    static ImageIcon icon;

    public BlockModel(int type){
        blockType.add("Move");
        blockType.add("Turn");
        blockType.add("For Loop") ;
        blockType.add("While");
        blockType.add("If ___ then ");
        setIndex(type);
        System.out.println(type);
        if ( type==0){
            icon= new ImageIcon(new ImageIcon("advUI/Icons/puzzle teal.png").getImage().getScaledInstance(75, 60, Image.SCALE_DEFAULT));
        }
        else if (type==1){
            icon= new ImageIcon(new ImageIcon("advUI/Icons/puzzle red.png").getImage().getScaledInstance(75, 60, Image.SCALE_DEFAULT));
        }
        else if (type==2){
            icon= new ImageIcon(new ImageIcon("advUI/Icons/puzzle blue.png").getImage().getScaledInstance(75, 60, Image.SCALE_DEFAULT));
        }
        else if (type==3){
            icon= new ImageIcon(new ImageIcon("advUI/Icons/puzzle yellow.png").getImage().getScaledInstance(75, 60, Image.SCALE_DEFAULT));
        }
    }

    public static String checkType(int indexCheck){
        return blockType.get(indexCheck);
    }
    public static void setIndex(int newIndex){
        index = newIndex;
    }
    public static int getIndex(String action){
        return blockType.indexOf(action);
    }

    public ImageIcon getIcon( ){
            return icon;
    }

    public BufferedImage getIconAsImage(){
        BufferedImage bufferedIcon = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedIcon.createGraphics();
        icon.paintIcon(null, g, 0,0);
        g.dispose();
        return bufferedIcon;
    }

    public String getType() {
        return checkType(index);
    }
}
