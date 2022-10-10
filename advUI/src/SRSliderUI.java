import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

//Inspired by : https://www.youtube.com/watch?v=jH8PNTXsAC0

public class SRSliderUI extends BasicSliderUI {
    Image image = null;
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
        int x = thumbRect.x;
//        if (slider.getValue()==1){
//            x += 20;
//        } else if (slider.getValue()==2){
//            x+=10;
//        } else if (slider.getValue()==4){
//            x-=10;
//        } else if (slider.getValue()==5){
//            x-=25;
//        }
        pen.drawImage(image, x, thumbRect.y, thumbRect.width, thumbRect.height, null);
    }

    public Dimension getThumbSize(){
        return new Dimension(45, 50);
    }

    @Override
    public void paintLabels(Graphics pen){

        for (int i = 0; i < 7; i++){
            int x = labelRect.x + i*(labelRect.width/7);
            int width = labelRect.width/7;
            int height = width - width/7;
            int y = labelRect.y - thumbRect.height*2-10 ;
            if(i==0){
                pen.setFont(new Font("Times", Font.BOLD, 14));
                pen.drawString("Main",x+5, y + height/2 );
                pen.drawString("Menu",x+5, y + (height/4)*3 );
                continue;
            }else if(i==6){
                pen.setFont(new Font("Times", Font.BOLD, 14));
                pen.drawString("Quit",x + width/2, y + height/2 );
               continue;
            }
            Image label = labels.get(i-1);
            if (i == 1){
                x -=5;
            } else if (i == 2) {
                x -=5;
            } else  if (i == 4) {
                x += 5;
            } else if (i == 5){
                x +=15;
            }
            pen.setFont(new Font("Times", Font.BOLD, 16));
            pen.drawImage(label, x , y , width, height, null);
            pen.drawString(String.valueOf(i),x + width/2-2, y + height/2 );
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
