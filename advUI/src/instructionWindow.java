import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class instructionWindow extends JDialog {
    GameWindow parent;
    instructionWindowPresentation view;
    instructionWindowModel model;

    int level;
    public instructionWindow(GameWindow owner){
        super(owner);
        parent = owner;
        level=parent.getLevelNumber();
        model= new instructionWindowModel();
        view= new instructionWindowPresentation(this);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("closed");
                owner.makeAnnouncement();
            }
        });
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        pack();
    }
    private String getInstructions() {
        return model.getInstructions();
    }

    private class instructionWindowPresentation {
        instructionWindow control;
        JLabel next;
        JLabel currentPage=new JLabel();
        JTextArea instructionLabel= new JTextArea();
        JLabel imagePanel = new JLabel();
        List<String> instructionText;
        List<ImageIcon> instructionImages;
        int page=0;
        public instructionWindowPresentation(instructionWindow instructionWindow) {
            //embed video or animate small pieces
            control= instructionWindow;
            control.setLayout(new BorderLayout());
            control.setPreferredSize(new Dimension(500,250));
            control.setLocation(250,200);
            control.setBackground(Color.WHITE);
            control.getContentPane().setBackground(Color.WHITE);
            instructionImages = control.getImages();
            String instructionList=control.getInstructions();
            instructionText= new ArrayList<>(Arrays.asList(instructionList.split("@")));
            instructionLabel.setEditable(false);
            instructionLabel.setLineWrap(true);
            instructionLabel.setWrapStyleWord(true);
            instructionLabel.setFont(new Font("Bradley Hand", Font.PLAIN, 20));

            imagePanel.setOpaque(false);
            changeSlide();
            next = new JLabel(new ImageIcon(new ImageIcon("advUI/Icons/next.png").getImage().getScaledInstance(40,40,Image.SCALE_DEFAULT)));
            next.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if(instructionText.isEmpty()){control.dispose();}
                    else {
                        changeSlide();
                    }
                }
            });

            JPanel progressPanel= new JPanel(new BorderLayout());
            progressPanel.setBackground(Color.white);
            progressPanel.add(currentPage,BorderLayout.WEST);
            progressPanel.add(next,BorderLayout.EAST);

            control.add(instructionLabel,BorderLayout.CENTER);
            control.add(imagePanel, BorderLayout.WEST);
            control.add(progressPanel, BorderLayout.SOUTH);
        }

        private void changeSlide() {
            instructionLabel.setText(instructionText.get(0));
            instructionText.remove(0);
            imagePanel.setIcon(instructionImages.get(0));
            instructionImages.remove(0);
            page++;
            currentPage.setText("Page "+page+"/5");
        }
    }

    private List<ImageIcon> getImages() {
        return model.getImages();
    }


    private class instructionWindowModel {
        String instructions;
        List<ImageIcon> images ;
        public instructionWindowModel(){
            images = new ArrayList<>(Arrays.asList(new ImageIcon(Character.presentation.getCharImage().getScaledInstance(100,100,Image.SCALE_DEFAULT)),new ImageIcon(new ImageIcon("advUI/Icons/grid.png").getImage().getScaledInstance(200,180,Image.SCALE_DEFAULT)),
                    new ImageIcon(new ImageIcon("advUI/Icons/buttonPanel.png").getImage().getScaledInstance(150,150,Image.SCALE_DEFAULT)),new ImageIcon(new ImageIcon("advUI/Icons/2scoop.png").getImage().getScaledInstance(80,150,Image.SCALE_DEFAULT)),
                    new ImageIcon(new ImageIcon("advUI/Icons/playing.jpg").getImage().getScaledInstance(190,175,Image.SCALE_DEFAULT))));
            instructions="Oh no! You've dropped your ice cream. Well...5 second rule applies, right? Move your character to the scoop to add it to your cone." +
                    "@Drag and drop action blocks into the grid below to move.@When you're ready, press Play! If you want to retry the level, press the Redo button. If you want to empty the grid, but keep your current level progress, press the Clear button.@"
                    +"Collect the scoops from largest to smallest to succeed. You cannot place a large scoop on top of a small scoop.@You can complete the challenge in one go, or in a combination of smaller movements. Keep in mind, to move to the next level, your last movement must end on the smallest scoop.";

        }
        public String getInstructions(){
            return instructions;
        }

        public List<ImageIcon> getImages() {
            return images;
        }
    }
}
