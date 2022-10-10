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
        levelSelect.setPreferredSize(new Dimension(500, 250));
        levelSelect.setMinimumSize(new Dimension(500, 250));
        levelSelect.setMaximumSize(new Dimension(500, 250));

        SRSlider slider = new SRSlider(parent.getLevelNumber());
        levelSelect.add(slider, BorderLayout.CENTER);

        JLabel level = new JLabel("Choose your level!", SwingConstants.CENTER);
        level.setFont(new Font("Bradley Hand", Font.BOLD, 30));
        level.setBackground(BGColor);
        level.setOpaque(true);
        levelSelect.add(level, BorderLayout.NORTH);

        JPanel playPanel = new JPanel();
        playPanel.setBackground(BGColor);
        JButton go = new JButton("Go");
        go.setFont(new Font("Bradley Hand", Font.BOLD, 24));
        go.setBackground(BGColor);
        go.addActionListener(
                e -> {
                    int selectedLevel=slider.getValue();
                    if(selectedLevel==0){
                        MainMenu menu=new MainMenu();
                        owner.dispose();
                        dispose();
                    }
                    else if (selectedLevel==6) {
                        owner.dispose();
                        dispose();
                    }else {
                        owner.changeLevel(slider.getValue());
                        dispose();
                    }

                }
        );
        levelSelect.add(playPanel, BorderLayout.SOUTH);

        add(levelSelect, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(BGColor);

        JButton restart = new JButton("Restart");
        restart.setFont(new Font("Bradley Hand", Font.BOLD, 24));
        restart.setBackground(BGColor);
        restart.addActionListener( e -> {
                parent.changeLevel(parent.getLevelNumber());
                dispose();
        });


//        JButton menu = new JButton("Main Menu");
//        menu.setFont(new Font("Bradley Hand", Font.BOLD, 18));
//        menu.setBackground(BGColor);

        buttonPanel.add(go);
        buttonPanel.add(restart);
        add(buttonPanel,BorderLayout.SOUTH);

        JLabel title = new JLabel("Menu", SwingConstants.CENTER);
        title.setFont(new Font("Bradley Hand", Font.BOLD, 35));
        title.setBackground(BGColor);
        title.setOpaque(true);

        add(title, BorderLayout.NORTH);


        setVisible(true);
        pack();
    }

}
