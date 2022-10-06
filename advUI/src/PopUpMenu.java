import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

public class PopUpMenu extends JDialog {

    public PopUpMenu(Frame owner){
        super(owner);
        setLayout(new BorderLayout());
        Color BGColor = new Color(0xFED1FF);

        JPanel levelSelect = new JPanel();
        levelSelect.setLayout(new BorderLayout());
        levelSelect.setPreferredSize(new Dimension(500, 300));
        levelSelect.setMinimumSize(new Dimension(500, 300));
        levelSelect.setMaximumSize(new Dimension(500, 300));

        SRSlider slider = new SRSlider();
        levelSelect.add(slider, BorderLayout.CENTER);

        JLabel level = new JLabel("Choose your level!", SwingConstants.CENTER);
        level.setFont(new Font("Bradley Hand", Font.BOLD, 30));
        level.setBackground(BGColor);
        level.setOpaque(true);
        levelSelect.add(level, BorderLayout.NORTH);

        add(levelSelect, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(BGColor);

        JButton restart = new JButton("Restart");
        restart.setFont(new Font("Bradley Hand", Font.BOLD, 18));
        restart.setBackground(BGColor);


        JButton menu = new JButton("Main Menu");
        menu.setFont(new Font("Bradley Hand", Font.BOLD, 18));
        menu.setBackground(BGColor);

        JButton quit = new JButton("Quit");
        quit.setFont(new Font("Bradley Hand", Font.BOLD, 18));
        quit.setBackground(BGColor);

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
