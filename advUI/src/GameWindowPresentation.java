import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameWindowPresentation{

    public GameWindow control;

    public AnimationPanel animation;

    public PlayingPanel playingZone;
    public GlassPaneWrapper glassPanel;

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

        mainPanel.add(animation);
        mainPanel.add(playingZone);

        glassPanel.add(menu, BorderLayout.NORTH);
        SpringLayout layout = new SpringLayout();
        glassPanel.setLayout(layout);
        layout.putConstraint(SpringLayout.EAST, menu,0,SpringLayout.EAST, glassPanel);
        layout.putConstraint(SpringLayout.NORTH, menu,0,SpringLayout.NORTH, glassPanel);
        control.add(mainPanel, BorderLayout.CENTER);


    }


}
