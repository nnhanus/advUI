import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;

public class AnimationPanel extends JPanel {
    public Grid grid;
    public Character character;
    public GameWindow parent;

    public AnimationPanel (GameWindow controller){
        super ();
        parent=controller;
        Color bgColor = new Color(0xFED1FF);
        setBackground(bgColor);
        chargeLevel(GameWindow.getLevel());
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        grid.paintGrid(g);
        character.presentation.paintCharacter(g);
    }

    public void setGrid(Level level){
        grid = new Grid(level.getNumRows(), level.getNumCols(), level.getScoops());
    }

    public void setCharacter(Level level){
        character = new Character(this, level.getOrientation(), level.getPosX(), level.getPosY() );
    }

    public void chargeLevel(Level level){
        setGrid(level);
        setCharacter(level);
    }

    public boolean isLevelComplete(){
        /**for ( Scoop scoop : Grid.scoops){
            if (character.getScoops().contains(scoop)){continue;}
            return false;

        }
         return true;**/
        System.out.println(grid.scoops.size());
        if (grid.scoops.size() == 0){
            return true;
        }
        return false;
    }

    public void endOfLevelMessage() {
        //need to reset character and scoops at end "repaint" from beginning of level
        if(isLevelComplete()){
            parent.setLevel(parent.getLevelNumber()+1);
            parent.chargeLevel(parent.getLevelNumber());
            //create a congratulation pop up with next level button (JOption)
            int winChoice=JOptionPane.showConfirmDialog(this,"Ready for the next level?","Congratulations!",JOptionPane.YES_NO_OPTION);
            if(winChoice==0){parent.repaint();}
            else{parent.levelSelect();}
        }
        else{
            //create try again pop up
            int loseChoice=JOptionPane.showConfirmDialog(this,"So close! Want to try again?","Try again",JOptionPane.YES_NO_OPTION);
            if(loseChoice==0){parent.repaint();}
            else{parent.levelSelect();}
        }
    }
}
