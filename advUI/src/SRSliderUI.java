import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class SRSliderUI extends BasicSliderUI {
    Image image = null;
    Image locked, level1,level2,level3,level4,level5;

    ArrayList<Image> labels = new ArrayList<>();

    SRSlider slider;

    public SRSliderUI(SRSlider slider) {
        super(slider);
        this.slider=slider;
        chargeImage();
    }


    @Override
    public void paintThumb(Graphics pen){
        int x = thumbRect.x;
        pen.drawImage(image, x, thumbRect.y, thumbRect.width, thumbRect.height, null);
    }

    public Dimension getThumbSize(){
        return new Dimension(45, 50);
    }

    @Override
    public void paintLabels(Graphics pen){
        int unlocked = slider.unlocked;
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
            Image label;
            if(i>unlocked){label=labels.get(0);}
            else{label = labels.get(i);}
            if (i == 1 && i==2){
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
            locked = ImageIO.read(new File("advUI/Icons/lock.png"));
            level1 = ImageIO.read(new File("advUI/Icons/mintChoco.png"));
            level2 = ImageIO.read(new File("advUI/Icons/funfetti.png"));
            level3 = ImageIO.read(new File("advUI/Icons/choco.png"));
            level4 = ImageIO.read(new File("advUI/Icons/strawberry.png"));
            level5 = ImageIO.read(new File("advUI/Icons/caramel.png"));
        } catch (Exception e){
            System.out.println("Missing File");
        }
        labels.add(locked);
        labels.add(level1);
        labels.add(level2);
        labels.add(level3);
        labels.add(level4);
        labels.add(level5);
    }
}
