import javax.swing.*;
import java.awt.*;

public class Helper extends JComponent {

    String message;
    int xPos;
    int yPos;
    public Helper(String message, int x, int y){
        this.message=message;
        xPos=x;
        yPos=y;
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d= (Graphics2D) g;
        int width=g2d.getFontMetrics().stringWidth(message)+2;
        int height=g2d.getFontMetrics().getHeight()+2;
        g2d.setColor(Color.BLUE);
        g2d.draw(new Rectangle(xPos,yPos,width,height));
        g2d.setColor(Color.BLACK);
        g2d.drawString(message,xPos+1,yPos+1);
    }
}
