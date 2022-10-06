import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class PopUpMenu extends JDialog {
    GameWindow parent;

    public PopUpMenu(GameWindow owner){
        super(owner);
        parent = owner;
        setLayout(new BorderLayout());
        Color BGColor = new Color(0xFED1FF);

        JPanel levelSelect = new JPanel();
        levelSelect.setLayout(new BorderLayout());
        levelSelect.setPreferredSize(new Dimension(500, 400));
        levelSelect.setMinimumSize(new Dimension(500, 400));
        levelSelect.setMaximumSize(new Dimension(500, 400));

        SRSlider slider = new SRSlider(parent.getLevelNumber());
        levelSelect.add(slider, BorderLayout.CENTER);

        JLabel level = new JLabel("Choose your level!", SwingConstants.CENTER);
        level.setFont(new Font("Bradley Hand", Font.BOLD, 30));
        level.setBackground(BGColor);
        level.setOpaque(true);
        levelSelect.add(level, BorderLayout.NORTH);

        JPanel playPanel = new JPanel();
        playPanel.setBackground(BGColor);
        JButton play = new JButton("Play");
        play.setFont(new Font("Bradley Hand", Font.BOLD, 24));
        play.setBackground(BGColor);
        play.addActionListener(
                e -> {
                    owner.changeLevel(slider.getValue());
                    dispose();
                }
        );
        playPanel.add(play);
        levelSelect.add(playPanel, BorderLayout.SOUTH);

        add(levelSelect, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(BGColor);

        JButton restart = new JButton("Restart");
        restart.setFont(new Font("Bradley Hand", Font.BOLD, 18));
        restart.setBackground(BGColor);
        restart.addActionListener( e -> {
                parent.changeLevel(parent.getLevelNumber());
                dispose();
        });


        JButton menu = new JButton("Main Menu");
        menu.setFont(new Font("Bradley Hand", Font.BOLD, 18));
        menu.setBackground(BGColor);

        JButton quit = new JButton("Quit");
        quit.setFont(new Font("Bradley Hand", Font.BOLD, 18));
        quit.setBackground(BGColor);
        quit.addActionListener( e -> System.exit(0) );

        buttonPanel.add(restart);
        buttonPanel.add(menu);
        buttonPanel.add(quit);

        add(buttonPanel, BorderLayout.SOUTH);

        JLabel title = new JLabel("Menu", SwingConstants.CENTER);
        title.setFont(new Font("Bradley Hand", Font.BOLD, 35));
        title.setBackground(BGColor);
        title.setOpaque(true);

        add(title, BorderLayout.NORTH);


        setVisible(true);
        pack();
    }

}
