import java.awt.image.BufferedImage;

public class Scoop {
    private final int x;
    private final int y;
    private final int size;
    private final BufferedImage image;

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
