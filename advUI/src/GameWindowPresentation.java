import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
        playingZone.getBottomPanel().mouseEvent = false;
        playingZone.getBottomPanel().clearCells();
        playingZone.getBottomPanel().clearAll();
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
}
