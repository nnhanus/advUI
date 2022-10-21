import javax.swing.*;
import java.awt.*;

public class AnimationPanel extends JPanel {
    public Grid grid;
    public Character character;
    public GameWindow parent;
    public boolean overplay=false;
    AnimationPanelPresentation view;

    public AnimationPanel (GameWindow controller, String character){
        super ();
        parent=controller;
        view= new AnimationPanelPresentation(this);
        changeLevel(GameWindow.getLevel(), character);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        grid.paintGrid(g);
        Character.presentation.paintCharacter(g);
    }

    public void setGrid(Level level){
        grid = new Grid(level.getScoops(), level.getBlockedCells(),level.getBackground(),parent);
    }

    public void setCharacter(Level level, String characterPath){
        character = new Character(this, level.getOrientation(), level.getPosX(), level.getPosY(), characterPath);
    }

    public void changeLevel(Level level, String character){
        setGrid(level);
        setCharacter(level, character);
        parent.repaint();
    }

    public boolean isLevelComplete(){
        return Grid.scoops.size() == 0;
    }

    public boolean isFailed(){
        if(overplay){
            overplay=false;
            return true;
        }
        else if (Grid.scoops.isEmpty()){
            return false;
        } else {
            if (character.getScoops().isEmpty()){
                return false;
            }
            for (Scoop scoop : Grid.scoops){
                if (character.getScoops().get(character.getScoops().size()-1).getSize() < scoop.getSize()){
                    return true;
                }
            }
        }
        return false;
    }

    public void endOfLevelMessage() {
        if (isFailed()){
            new EndLevelMessage(parent, "fail");
        }else if(isLevelComplete()){
            if(GameWindow.getLevelNumber()==5){
                parent.rainScoops();
            }
            new EndLevelMessage(parent, "win");
        }


    }

    public static class AnimationPanelPresentation {
        AnimationPanel control;
        public AnimationPanelPresentation(AnimationPanel control){
            this.control=control;
            this.control.setBackground(Main.bgColor);
        }
    }
}
