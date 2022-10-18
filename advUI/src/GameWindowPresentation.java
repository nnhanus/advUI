import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameWindowPresentation{

    public GameWindow control;

    public AnimationPanel animation;

    public PlayingPanel playingZone;
    public GlassPaneWrapper glassPanel;
    JLabel helper= new JLabel(" ");
    String fontFamily = "Bradley Hand";
    Font buttonFont = new Font(fontFamily, Font.BOLD, 16);

    public GameWindowPresentation(GameWindow controller, String character){
        control=controller;
        animation = new AnimationPanel(control, character);
        //menuBar();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));

        playingZone = new PlayingPanel(control);
        glassPanel = new GlassPaneWrapper(playingZone);
        control.setGlassPane(glassPanel);
        glassPanel.activateGlassPane(true);




        JLabel menu = new JLabel(new ImageIcon(new ImageIcon("advUI/Icons/menu.png").getImage().getScaledInstance(38,50,Image.SCALE_DEFAULT)));
        menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PopUpMenu(control);
            }
        });

        helper.setIcon(new ImageIcon(new ImageIcon("advUI/Icons/info.png").getImage().getScaledInstance(20,20,Image.SCALE_DEFAULT)));
        helper.setHorizontalTextPosition(JLabel.LEFT);
        helper.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                makePopUpInstructions();
            }
        });

        mainPanel.add(animation);
        mainPanel.add(playingZone);

        if(GameWindow.getLevelNumber()>2){glassPanel.addNumberSpinner();}

        glassPanel.add(menu);
        glassPanel.add(helper);
        SpringLayout layout = (SpringLayout) glassPanel.getLayout();
        layout.putConstraint(SpringLayout.EAST, menu,-2,SpringLayout.EAST, glassPanel);
        layout.putConstraint(SpringLayout.NORTH, menu,1,SpringLayout.NORTH, glassPanel);
        layout.putConstraint(SpringLayout.EAST, helper,-1,SpringLayout.EAST, glassPanel);
        layout.putConstraint(SpringLayout.NORTH, helper,62,SpringLayout.SOUTH, menu);
        control.add(mainPanel, BorderLayout.CENTER);

    }

    private void makePopUpInstructions() {
        JDialog instructions= new JDialog(control);
        instructions.setLayout(new BorderLayout());
        JPanel buttonPanel= new JPanel(new BorderLayout());
        JButton close=new JButton("Close");
        close.addActionListener(e -> {instructions.dispose();});
        close.setFont(buttonFont);
        buttonPanel.add(close, BorderLayout.EAST);
        instructions.add(new instructionPopUp(), BorderLayout.CENTER);
        instructions.add(buttonPanel, BorderLayout.SOUTH);
        instructions.pack();
        instructions.setVisible(true);
    }

    public void changeLevel(int levelNumber){
        //playingZone.getBottomPanel().mouseEvent = false;
        playingZone.getBottomPanel().clearCells();
        playingZone.getBottomPanel().clearAll();
        playingZone.getBottomPanel().buildGrid();
        Color BGColor = new Color(0xFED1FF);
        if( levelNumber>GameWindow.getUnlocked()){
            levelNumber= GameWindow.getLevelNumber();
        }
        control.setLevel(levelNumber);
        animation.changeLevel(GameWindow.getLevel(), animation.character.getPath());
        playingZone.topPanel.resetBtns();
        playingZone.revalidate();
        glassPanel.removeForSpinner();
        if(levelNumber>2){glassPanel.addNumberSpinner();}
        control.repaint();
        if(levelNumber==GameWindow.getUnlocked()&& control.tutorialOn&&levelNumber<5) {
            glassPanel.makeAnnouncement();
        }
    }

    public void setHelperText(String s) {
        helper.setText(s);
    }

    public void rainScoops(Character character) {
        List<BufferedImage> scoops = new ArrayList<>();
        BufferedImage mintChoco = null;
        BufferedImage funfetti = null;
        BufferedImage caramel=null;
        BufferedImage choco = null;
        BufferedImage strawberry = null;
        Image characterImage= character.getCharImage();
        try {
            mintChoco = ImageIO.read(new File("advUI/Icons/mintChoco.png"));
            funfetti = ImageIO.read(new File("advUI/Icons/funfetti.png"));
            caramel = ImageIO.read(new File("advUI/Icons/caramel.png"));
            choco = ImageIO.read(new File("advUI/Icons/choco.png"));
            strawberry = ImageIO.read(new File("advUI/Icons/strawberry.png"));

        } catch (IOException ex) {
            System.out.println("Missing file");
        }
        scoops.add(mintChoco);
        scoops.add(funfetti);
        scoops.add(caramel);
        scoops.add(choco);
        scoops.add(strawberry);
        //create transparent JPanel with custom paint?
        //use random to get scoop type
        //use random to determine start point

        //if location of scoop is passed glass pane, delete
//        Timer timer = new Timer(10000, new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//                //while timer move scoops 5 pts down and rotate
//            }
//        });
//        timer.setRepeats(false);
//        timer.start();
    }
}
