import java.util.*;

public class dropPanelModel {
    public List<String> actionList;

    public dropPanelModel(){
        actionList = new ArrayList<String>() {};

    }

    public void addAction(String action) {
        actionList.add(action);
    }
    public void clearList (){
        actionList=new ArrayList<String>();
    }
}
