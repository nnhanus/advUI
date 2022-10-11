import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockModel {
    int level = GameWindow.getLevelNumber();
    int index;
    String name;
    public Map<Integer,String> nameMap=new HashMap();
    public Map<Integer,ImageIcon> iconMap=new HashMap();
    String forLoopIter="";
    public ImageIcon icon;
    public BlockControl control;

    public BlockModel(int type, BlockControl controller){
        control=controller;
        nameMap.put(0,"Move");
        nameMap.put(1,"Turn");
        nameMap.put(2,"For");
        nameMap.put(3,"If");

        iconMap.put(0,new ImageIcon(new ImageIcon("advUI/Icons/puzzle teal.png").getImage().getScaledInstance(75, 60, Image.SCALE_DEFAULT)));
        iconMap.put(1,new ImageIcon(new ImageIcon("advUI/Icons/puzzle red.png").getImage().getScaledInstance(75, 60, Image.SCALE_DEFAULT)));
        iconMap.put(2,new ImageIcon(new ImageIcon("advUI/Icons/puzzle blue.png").getImage().getScaledInstance(75, 60, Image.SCALE_DEFAULT)));
        iconMap.put(3,new ImageIcon(new ImageIcon("advUI/Icons/puzzle yellow.png").getImage().getScaledInstance(75, 60, Image.SCALE_DEFAULT)));
        setIndex(type);
        this.name=nameMap.get(type);
        this.icon= iconMap.get(type);
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

    public void setForLoopIter(String forLoopIter) {
        this.forLoopIter = forLoopIter;
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

    public String getForLoopIter() {
        return forLoopIter;
    }
}
