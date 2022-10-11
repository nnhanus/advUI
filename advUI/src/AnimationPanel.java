import javax.swing.*;
import java.awt.*;

public class AnimationPanel extends JPanel {
    public Grid grid;
    public Character character;
    public GameWindow parent;
    AnimationPanelPresentation view;

    public AnimationPanel (GameWindow controller){
        super ();
        parent=controller;
        view= new AnimationPanelPresentation(this);
        changeLevel(parent.getLevel());

    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        grid.paintGrid(g);
        character.presentation.paintCharacter(g);
    }

    public void setGrid(Level level){
        grid = new Grid(level.getNumRows(), level.getNumCols(), level.getScoops(), level.getBlockedCells(), parent);
    }

    public void setCharacter(Level level){
        character = new Character(this, level.getOrientation(), level.getPosX(), level.getPosY() );
    }

    public void changeLevel(Level level){
        setGrid(level);
        setCharacter(level);
        parent.repaint();
    }

    public boolean isLevelComplete(){
        if (grid.scoops.size() == 0){
            return true;
        }
        return false;
    }

    public boolean isFailed(){
        if (Grid.scoops.isEmpty()){
            return false;
        } else {
            if (character.getScoops().isEmpty()){
                return false;
            }
            for (Scoop scoop : Grid.scoops){
                if (character.getScoops().get(character.getScoops().size()-1).size < scoop.size){
                    return true;
                }
            }
        }
        return false;
    }

    public void endOfLevelMessage() {
        //need to reset character and scoops at end "repaint" from beginning of level
        if(isLevelComplete()){
            parent.setLevelUnlocked(parent.getLevelNumber()+1);
            parent.setLevel(parent.getLevelNumber()+1);
            EndLevelMessage end = new EndLevelMessage(parent, "win");
        } else if (isFailed()){
            EndLevelMessage end = new EndLevelMessage(parent, "fail");
        }


    }


    public static class AnimationPanelPresentation {
        AnimationPanel control;
        public AnimationPanelPresentation(AnimationPanel control){
            this.control=control;
            Color bgColor = new Color(0xFED1FF);
            this.control.setBackground(bgColor);
        }
    }
}
