import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Scoop {
    private int x;
    private int y;
    private int size;
    private BufferedImage image;

    public Scoop(int x, int y, int size, BufferedImage image){
        this.image = image;
        this.x = x;
        this.y = y;
        this.size = size;
    }

    public BufferedImage getImage(){ return image;}

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }
}
