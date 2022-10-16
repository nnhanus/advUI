import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class BlockModel {
    int index;
    String name;
    public Map<Integer,String> nameMap;
    public Map<Integer,String> descriptionMap;
    public Map<Integer,ImageIcon> iconMap;
    String forLoopIter="";
    public ImageIcon icon;

    public BlockModel(int type){
        nameMap = new HashMap<>();
        nameMap.put(0,"Move");
        nameMap.put(1,"Turn");
        nameMap.put(2,"For");
        nameMap.put(3,"If");

        iconMap = new HashMap<>();
        iconMap.put(0,new ImageIcon(new ImageIcon("advUI/Icons/puzzle teal.png").getImage().getScaledInstance(90, 80, Image.SCALE_DEFAULT)));
        iconMap.put(1,new ImageIcon(new ImageIcon("advUI/Icons/puzzle red.png").getImage().getScaledInstance(90, 80, Image.SCALE_DEFAULT)));
        iconMap.put(2,new ImageIcon(new ImageIcon("advUI/Icons/puzzle blue.png").getImage().getScaledInstance(90, 80, Image.SCALE_DEFAULT)));
        iconMap.put(3,new ImageIcon(new ImageIcon("advUI/Icons/puzzle yellow.png").getImage().getScaledInstance(90, 80, Image.SCALE_DEFAULT)));

        descriptionMap = new HashMap<>();
        descriptionMap.put(0,"Moves the character one cell in the current direction  ");
        descriptionMap.put(1,"Turns the character 90 degrees clockwise  ");
        descriptionMap.put(2,"Repeats the next block a specified amount of times  ");
        descriptionMap.put(3,"Tests if the ice cream scoop on the current square is the largest one left ");

        setIndex(type);
        this.name=nameMap.get(type);
        this.icon= iconMap.get(type);
    }

    public String getHelper(){
        return descriptionMap.get(index);
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
