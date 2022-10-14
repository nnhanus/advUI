import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class instructionPopUp extends JPanel {
    instructionPopUpPresentation view;

    public instructionPopUp() {
        view = new instructionPopUpPresentation(this);
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        view.paint((Graphics2D) g );
    }


    private class instructionPopUpPresentation {
        instructionPopUp control;
        BufferedImage instructions;
        public instructionPopUpPresentation(instructionPopUp control) {
            this.control=control;
            this.control.setPreferredSize(new Dimension(600,450));
            this.control.setLayout(new BorderLayout());
            try{
                instructions = ImageIO.read(new File("advUI/Icons/instructions.png"));
            } catch (IOException ex) {
                System.out.println("Missing file");
            }
        }
        public void paint(Graphics2D g2d){
            g2d.drawImage(instructions,0,0, control.getWidth(),control.getHeight(),null);
        }
    }
}
