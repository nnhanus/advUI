import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class cellRectangle extends Rectangle {
    public boolean close;

    dropPanel control;
    boolean hasBlock;
    public cellRectangle(int x, int y, int width, int height, dropPanel control){
        super(x,y,width,height);
        this.control=control;
        close =false;
        hasBlock=false;
    }

}
