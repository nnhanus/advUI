import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class newActionWindow extends JPanel{
    
    GameWindow parent;
    newActionWindowPresentation view;
    newActionWindowModel model;

    int level;
    public newActionWindow(GameWindow owner){
        parent = owner;
        level= GameWindow.getLevelNumber();
        model= new newActionWindowModel(level);
        view= new newActionWindowPresentation(this);
    }
    private String getDetails() {
        return model.getDetails(level);
    }
    private BlockControl getNewBlock() {
        return new BlockControl(level-1,null);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        view.paint((Graphics2D)g);
    }


    private static class newActionWindowPresentation {
        newActionWindow control;
        BlockControl newBlock;
        JTextArea itemDescript = new JTextArea();
        Image newStar;
        JLabel blockImage = new JLabel();
        JLabel title = new JLabel();

        public newActionWindowPresentation(newActionWindow newWindow) {
            control = newWindow;
            control.setLayout(new BoxLayout(control,BoxLayout.PAGE_AXIS));
            control.setPreferredSize(new Dimension(450, 580));
            newBlock=control.getNewBlock();
            try{
                newStar = ImageIO.read(new File("advUI/Resources/Icons/newStar.png"));
            } catch (IOException ex) {
                System.out.println("Missing file");
            }
            setUI();
        }

        private void setUI() {
            control.setBackground(Main.bgColor);
            String details = control.getDetails();
            itemDescript.setEditable(false);
            itemDescript.setLineWrap(true);
            itemDescript.setWrapStyleWord(true);
            itemDescript.setFont(Main.getFontPlain(20));
            itemDescript.setText(details);
            itemDescript.setBackground(Main.bgColor);

            blockImage.setIcon(new ImageIcon(newBlock.getIcon().getImage().getScaledInstance(150,125,Image.SCALE_DEFAULT)));
            blockImage.setText(newBlock.getType());
            blockImage.setHorizontalTextPosition(JLabel.CENTER);
            blockImage.setFont(new Font("Ariel", Font.BOLD, 16));
            blockImage.setForeground(Color.WHITE);


            title.setText("New Action: "+newBlock.getType()+"!");
            title.setFont(Main.getFontPlain(20));

            title.setAlignmentX(Component.CENTER_ALIGNMENT);
            blockImage.setAlignmentX(Component.CENTER_ALIGNMENT);
            itemDescript.setAlignmentX(Component.CENTER_ALIGNMENT);

            control.add(title);
            control.add(Box.createRigidArea(new Dimension(0,110)));
            control.add(Box.createVerticalGlue());
            control.add(blockImage);
            control.add(Box.createRigidArea(new Dimension(0,90)));
            control.add(Box.createVerticalGlue());
            control.add(itemDescript);

        }
        public void paint(Graphics2D g2d){
            g2d.drawImage(newStar,0,0,control.getWidth(),control.getHeight(),null);
        }
    }



    private static class newActionWindowModel {
       int level;
       Map<Integer,String> details=new HashMap<>();
        public newActionWindowModel(int level){
            this.level=level;
            details.put(0,"The Move Block is the base of all actions. It moves your character one square in the direction it is facing." +
                    " If you move onto a square with an ice cream scoop, your character will pick it up.");
            details.put(1,"The Turn Block allows for greater range of movement. When played, your character will turn 90 degrees (1/4 of a circle) inside its current square." +
                    "Use this in combination with the Move Block to get hard to reach scoops.");
            details.put(2,"The For Block is very special. When you play a For Block, it repeats the next action for a specified number of times." +
                    "Play the For Block followed by the action you would like to repeat (Move or Turn) and select the number of times to repeat it.");
            details.put(3,"The If Block checks whether the ice cream scoop on the current cell is the biggest one available to pick up." +
                    "This action is very helpful when there is more than one scoop on the board because you must collect scoops from largest to smallest.");
        }

        public String getDetails(int level) {
            return details.get(level-1);
        }

    }
}
