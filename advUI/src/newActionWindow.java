import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class newActionWindow extends JDialog {
    //could make into Jpanel and add to glass frame
    
    GameWindow parent;
    newActionWindowPresentation view;
    newActionWindowModel model;

    int level;
    public newActionWindow(GameWindow owner){
        super(owner);
        parent = owner;
        level=parent.getLevelNumber();
        model= new newActionWindowModel();
        view= new newActionWindowPresentation(this);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
    }
    private String getInstructions() {
        return model.getInstructions(level);
    }

    private class newActionWindowPresentation {
        newActionWindow control;
        JButton next;
        JTextArea itemDescript = new JTextArea();
        JLabel imagePanel = new JLabel();
        List<String> descriptionText;
        List<ImageIcon> itemImages;
        public newActionWindowPresentation(newActionWindow newWindow) {
            //embed video or animate small pieces
            control= newWindow;
            control.setLayout(new BorderLayout());
            control.setPreferredSize(new Dimension(500,250));
            itemImages = control.getImages();
            String instructionList=control.getInstructions();
            descriptionText = new ArrayList<>(Arrays.asList(instructionList.split("@")));
            itemDescript.setEditable(false);
            itemDescript.setLineWrap(true);
            itemDescript.setWrapStyleWord(true);
            itemDescript.setFont(new Font("Bradley Hand", Font.PLAIN, 20));
            changeSlide();
            next = new JButton("Next");
            next.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(descriptionText.isEmpty()){control.dispose();}
                    else {
                        changeSlide();
                    }
                }
            });
            control.add(itemDescript,BorderLayout.CENTER);
            control.add(imagePanel, BorderLayout.EAST);
            control.add(next, BorderLayout.SOUTH);
        }

        private void changeSlide() {
            itemDescript.setText(descriptionText.get(0));
            descriptionText.remove(0);
            imagePanel.setIcon(itemImages.get(0));
            itemImages.remove(0);

        }
    }

    private List<ImageIcon> getImages() {
        return model.getImages(level);
    }


    private class newActionWindowModel {
        Map<Integer, String> instructions=new HashMap<>();
        Map<Integer, List<ImageIcon>> images = new HashMap<>();
        public newActionWindowModel(){
            instructions.put(1,"Oh no! You've dropped your ice cream. Well...5 second rule applies, right? Move your character to the scoop to add it to your cone.@Drag and drop action blocks into the grid below to move. End movement on the last scoop to move to the next level.@When you're ready, press Play! If you want to retry the level, press the Redo button. If you want to empty the grid, but keep your current level progress, press the Clear button.@");
            instructions.put(2,"Collect the scoops from largest to smallest to succeed.@You can complete the challenge in one go, or in a combination of smaller movements. Keep in mind, to move to the next level, your last movement must end on the smallest scoop.");
            instructions.put(3,"");
            instructions.put(4,"");
            //need to scale images
            List<ImageIcon> level1= new ArrayList<>(Arrays.asList(new ImageIcon(Character.presentation.getCharImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)),new ImageIcon(new ImageIcon("advUI/Icons/puzzle teal.png").getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)),new ImageIcon(new ImageIcon("advUI/Icons/buttonPanel.png").getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT))));
            images.put(1,level1);
        }
        public String getInstructions(int level){
            return instructions.get(level);
        }

        public List<ImageIcon> getImages(int level) {
            return images.get(level);
        }
    }
}
