import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;


public class BlockControl extends JLabel {
    public BlockModel model;
    int typeNum;
    public TopPanel container;
    private final BlockPresentation view;
    boolean helper;

    public BlockControl (int controlNum, TopPanel parent) {
        container = parent;
        typeNum = controlNum;
        helper=false;
        model = new BlockModel(controlNum);
        view = new BlockPresentation(this);
    }

    public BlockControl (BlockControl toCopy) {
        container = toCopy.container;
        typeNum = toCopy.getTypeNum();
        helper=false;
        model = new BlockModel(typeNum);
        view = new BlockPresentation(this);
        if(typeNum==2){
            this.setForLoopIter(Integer.parseInt(toCopy.getForLoopIter()));
        }

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        view.paint((Graphics2D) g);
    }

    public TopPanel getContainer(){
        return container;
    }
    public int getTypeNum(){
        return model.index;
    }
    public String getDescription(){
        return model.getHelper();
    }
    public void highlightOn(){
        view.highlightOn();
    }
    public void highlightOff(){
        view.highlightOff();
    }
    public String getType(){return model.getType();}
    public ImageIcon getIcon(){return model.getIcon();}
    public void setForLoopIter(int iterNum){
        model.setForLoopIter(Integer.toString(iterNum));
    }
    public BufferedImage getIconAsImage() {
        return model.getIconAsImage();
    }
    public String getForLoopIter() {
        return model.getForLoopIter();
    }

    @Override
    public Dimension getPreferredSize(){
        return view.getPreferredSize();
    }




}
