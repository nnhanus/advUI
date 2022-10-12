import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CharacterChooser extends JPanel {
    Color BGColor = new Color(0xFED1FF);
    ArrayList<String> characters = new ArrayList<>();
    int index = 0;
    int width = 200;
    int height = 190;

    public CharacterChooser(){
        this.setVisible(true);
        setBackground(BGColor);
        setLayout(new BorderLayout());
        makeCharacter();
        mainPane();
    }

    public void makeCharacter(){
        characters.add("advUI/Icons/cow.png");
        characters.add("advUI/Icons/dino.png");
        characters.add("advUI/Icons/dog.png");
        characters.add("advUI/Icons/elephant.png");
        characters.add("advUI/Icons/lion.png");
    }

    public void mainPane(){
        JLabel imageLabel = new JLabel(new ImageIcon(new ImageIcon(characters.get(index)).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));


        JLabel right = new JLabel(new ImageIcon((new ImageIcon("advUI/Icons/next.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        right.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (index==4){
                    index = 0;
                } else {
                    index++;
                }
                imageLabel.setIcon(new ImageIcon(new ImageIcon(characters.get(index)).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
                repaint();
            }
        });

        JLabel left = new JLabel(new ImageIcon((new ImageIcon("advUI/Icons/previous.png")).getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT)));
        left.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (index==0){
                    index = 4;
                } else {
                    index--;
                }
                imageLabel.setIcon(new ImageIcon(new ImageIcon(characters.get(index)).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT)));
                repaint();
            }
        });

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(BGColor);
        rightPanel.setBorder(new EmptyBorder(new Insets(70, 0, 0, 0)));
        rightPanel.add(right);

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(BGColor);
        leftPanel.setBorder(new EmptyBorder(new Insets(70, 0, 0, 0)));
        leftPanel.add(left);

        JPanel mainPane = new JPanel();
        mainPane.setBackground(BGColor);
        mainPane.setLayout(new BorderLayout());
        mainPane.add(imageLabel, BorderLayout.CENTER);
        mainPane.add(leftPanel, BorderLayout.WEST);
        mainPane.add(rightPanel, BorderLayout.EAST);

        add(mainPane, BorderLayout.CENTER);
    }



}
