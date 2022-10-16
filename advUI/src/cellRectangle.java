import java.awt.*;

public class cellRectangle extends Rectangle {
    private boolean close;
    private boolean highlight;
    private boolean isReadHighlight;

    private boolean hasBlock;

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
