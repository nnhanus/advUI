import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockModel {
    int level = GameWindow.getLevelNumber();
    int index;
    String name;
    List<String> blockType= new ArrayList<String>();
    public Map<Integer,String> nameMap=new HashMap();
    public Map<Integer,ImageIcon> iconMap=new HashMap();

    public ImageIcon icon;

    public BlockModel(int type){
        nameMap.put(0,"Move");
        nameMap.put(1,"Turn");
        nameMap.put(2,"For Loop");
        nameMap.put(3,"If");
        nameMap.put(4,"While Loop");

        iconMap.put(0,new ImageIcon(new ImageIcon("advUI/Icons/puzzle teal.png").getImage().getScaledInstance(75, 60, Image.SCALE_DEFAULT)));
        iconMap.put(1,new ImageIcon(new ImageIcon("advUI/Icons/puzzle red.png").getImage().getScaledInstance(75, 60, Image.SCALE_DEFAULT)));
        iconMap.put(2,new ImageIcon(new ImageIcon("advUI/Icons/puzzle blue.png").getImage().getScaledInstance(75, 60, Image.SCALE_DEFAULT)));
        iconMap.put(3,new ImageIcon(new ImageIcon("advUI/Icons/puzzle yellow.png").getImage().getScaledInstance(75, 60, Image.SCALE_DEFAULT)));
        iconMap.put(4,new ImageIcon(new ImageIcon("advUI/Icons/puzzle yellow.png").getImage().getScaledInstance(75, 60, Image.SCALE_DEFAULT)));
        setIndex(type);
        this.name=nameMap.get(type);
        this.icon= iconMap.get(type);
        System.out.println(type);
        //giving all blocks the last icon of the group?

    }

    public String checkType(int indexCheck){
        return nameMap.get(indexCheck);
    }
    public void setIndex(int newIndex){
        this.index = newIndex;
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
