import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DragListener extends MouseAdapter
{
    Boolean dragging=false;
    Point location;
    MouseEvent pressed;

    @Override
    public void mousePressed(MouseEvent e)
    {
        super.mousePressed(e);
        pressed = e;
        dragging=true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        dragging=false;

    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        super.mouseDragged(e);
        Component component = e.getComponent();
        location = component.getLocation(location);
        int x = location.x - pressed.getX() + e.getX();
        int y = location.y - pressed.getY() + e.getY();
        if(dragging){
            component.repaint();
        }
    }
}
