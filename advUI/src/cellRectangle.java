import java.awt.*;

/**
 * Cell of the dropPanel grid
 */
public class cellRectangle extends Rectangle {
    private boolean close; //Do we need to draw the close icon on this cell?
    private boolean highlight; //Do we need to highlight this cell? When dragging into the dropPanel
    private boolean isReadHighlight; //Do we need to highlight this cell? When reading the actions

    private boolean hasBlock; //If the cell contains a block

    public cellRectangle(int x, int y, int width, int height) {
        super(x,y,width,height);
        close=false;
        highlight=false;
        hasBlock=false;
        isReadHighlight=false;
    }

    public void setClose(boolean bool){
        close = bool;
    }
    public boolean getClose(){
        return close;
    }

    public void setHighlight(boolean bool){
        highlight = bool;
    }
    public boolean getHighlight(){
        return highlight;
    }

    public void setReadHighlight(boolean bool){
        isReadHighlight = bool;
    }
    public boolean getReadHighlight(){
        return isReadHighlight;
    }

    public void setHasBlock(boolean bool){
        hasBlock = bool;
    }
    public boolean getHasBlock(){
        return hasBlock;
    }
}
