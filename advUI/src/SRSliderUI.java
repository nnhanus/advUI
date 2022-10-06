import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

//Inspired by : https://www.youtube.com/watch?v=jH8PNTXsAC0

public class SRSliderUI extends BasicSliderUI {
    BufferedImage image = null;
    //BufferedImage label = null;
    /**JLabel level1;
    JLabel level2;
    JLabel level3;
    JLabel level4;
    JLabel level5;**/
    Image level1;
    Image level2;
    Image level3;
    Image level4;
    Image level5;

    ArrayList<Image> labels = new ArrayList<>();


    public SRSliderUI(SRSlider slider) {
        super(slider);
        chargeImage();
    }


    @Override
    public void paintThumb(Graphics pen){
        pen.drawImage(image, thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height, null);
    }

    public Dimension getThumbSize(){
        return new Dimension(45, 50);
    }

    @Override
    public void paintLabels(Graphics pen){
        for (int i = 0; i < 5; i++){
            Image label = labels.get(i);
            int x = labelRect.x + i*(labelRect.width/5);
            if (i == 0){
                x -=20;
            } else if (i == 1) {
                x -=10;
            } else  if (i == 3) {
                x += 10;
            } else if (i == 4){
                x +=20;
            }
            int width = labelRect.width/5;
            int height = width - width/5;
            int y = labelRect.y - thumbRect.height*2 - 35;
            pen.drawImage(label, x , y , width, height, null);
            pen.setFont(new Font("Times", Font.BOLD, 16));
            pen.drawString(String.valueOf(i+1),x + width/2, y + height/2 );
        }
    }

    private void chargeImage(){
        try {
            image = ImageIO.read(new File("advUI/Icons/cone.png"));
            level1 = ImageIO.read(new File("advUI/Icons/mintChoco.png"));
            level2 = ImageIO.read(new File("advUI/Icons/funfetti.png"));
            level3 = ImageIO.read(new File("advUI/Icons/choco.png"));
            level4 = ImageIO.read(new File("advUI/Icons/strawberry.png"));
            level5 = ImageIO.read(new File("advUI/Icons/caramel.png"));
        } catch (Exception e){
            System.out.println("Missing File");
        }
        labels.add(level1);
        labels.add(level2);
        labels.add(level3);
        labels.add(level4);
        labels.add(level5);
    }
}
