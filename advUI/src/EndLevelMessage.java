import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EndLevelMessage extends JDialog {
    GameWindow parent;
    ImageIcon icon = null;
    JLabel messageP1 = new JLabel();
    JLabel messageP2 = new JLabel();
    Color BGColor = new Color(0xFED1FF);


    public EndLevelMessage(GameWindow owner, String type){
        super(owner);
        parent = owner;
        setLayout(new BorderLayout());
        setVisible(true);

        if (type.equalsIgnoreCase("win")){
            makeWin();
        } else {
            makeFail();
        }

        JPanel image = new JPanel();
        image.setBackground(BGColor);
        image.add(new JLabel(icon));
        image.setBorder(new EmptyBorder(new Insets(40, 10, 10, 40)));

        messageP1.setFont(new Font("Bradley Hand", Font.PLAIN, 30));
        messageP2.setFont(new Font("Bradley Hand", Font.PLAIN, 24));

        JPanel buttonsPanel = buttonsPanel();

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(BGColor);
        rightPanel.setLayout(new BorderLayout());
        rightPanel.add(messageP1, BorderLayout.NORTH);
        rightPanel.add(messageP2, BorderLayout.CENTER);
        rightPanel.add(buttonsPanel, BorderLayout.SOUTH);
        rightPanel.setBorder(new EmptyBorder(new Insets(20, 0, 20, 10)));

        add(image, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        pack();
    }

    private JPanel buttonsPanel() {
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(BGColor);
        buttonsPanel.setBorder(new EmptyBorder(20, 0, 10, 0));

        JButton yes = new JButton("Yes");
        JButton no = new JButton("No");

        yes.setFont(new Font("Bradley Hand", Font.PLAIN, 18));
        no.setFont(new Font("Bradley Hand", Font.PLAIN, 18));

        yes.addActionListener(
                e -> {
                    parent.setLevel(parent.getLevelNumber()+1);
                    parent.changeLevel(parent.getLevelNumber());
                    dispose();
                });
        no.addActionListener(
                e -> {
                    new PopUpMenu(parent);
                    dispose();
                });

        buttonsPanel.add(yes);
        buttonsPanel.add(no);

        return buttonsPanel;
    }

    private void makeWin(){
        makeIcon("Win");
        if(parent.getLevelNumber()==5){
            messageP1.setText("Congratulations!");
            messageP2.setText("You did it! ");
        }
        parent.setLevelUnlocked(parent.getLevelNumber()+1);
        messageP1.setText("Congratulations!");
        messageP2.setText("Ready for the next level?");
    }

    private void makeFail(){
        makeIcon("Fail");
        messageP1.setText("So close!");
        messageP2.setText("Want to try again?");
    }

    private void makeIcon(String type){
        String characterPath = parent.view.animation.character.getPath();
        String split[] = characterPath.split("/");
        String split2[] = split[2].split("\\.");
        String character = split2[0];
        String path = "advUI/Icons/" + character + type + ".png";
        icon = new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(100, 90, Image.SCALE_SMOOTH));
    }
}
