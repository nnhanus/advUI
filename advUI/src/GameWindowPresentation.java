import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

        JButton menu = new JButton("Menu");
        menu.setFont(new Font("Bradley Hand", Font.BOLD, 18));
        menu.addActionListener( e -> new PopUpMenu(control));

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

        glassPanel.add(menu);
        glassPanel.add(helper);
        SpringLayout layout = new SpringLayout();
        glassPanel.setLayout(layout);
        layout.putConstraint(SpringLayout.EAST, menu,0,SpringLayout.EAST, glassPanel);
        layout.putConstraint(SpringLayout.NORTH, menu,0,SpringLayout.NORTH, glassPanel);
        layout.putConstraint(SpringLayout.EAST, helper,0,SpringLayout.EAST, glassPanel);
        layout.putConstraint(SpringLayout.NORTH, helper,60,SpringLayout.SOUTH, menu);
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
    void makeAnnouncement() {
        newActionWindow newBlock = new newActionWindow(control);
        glassPanel.add(newBlock);
        newBlock.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                glassPanel.remove(newBlock);
                glassPanel.repaint();
            }
        });
        SpringLayout layout= (SpringLayout) glassPanel.getLayout();
        layout.putConstraint(SpringLayout.WEST, newBlock,150,SpringLayout.WEST, glassPanel);
        layout.putConstraint(SpringLayout.NORTH, newBlock,80,SpringLayout.NORTH, glassPanel);
        layout.putConstraint(SpringLayout.EAST, newBlock,-150,SpringLayout.EAST, glassPanel);
        layout.putConstraint(SpringLayout.SOUTH, newBlock,-20,SpringLayout.SOUTH, glassPanel);
        glassPanel.revalidate();
        glassPanel.repaint();
        Timer timer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                glassPanel.remove(newBlock);
                glassPanel.repaint();
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    public void changeLevel(int levelNumber){
        playingZone.getBottomPanel().mouseEvent = false;
        Color BGColor = new Color(0xFED1FF);
        if( levelNumber>GameWindow.getUnlocked()){
            levelNumber= GameWindow.getLevelNumber();
        }
        control.setLevel(levelNumber);
        animation.changeLevel(GameWindow.getLevel(), animation.character.getPath());
        playingZone.topPanel.resetBtns();
        playingZone.revalidate();
        control.repaint();
        if(levelNumber==GameWindow.getLevelNumber()&& control.tutorialOn) {
            makeAnnouncement();
        }
    }

    public void setHelperText(String s) {
        helper.setText(s);
    }
}
