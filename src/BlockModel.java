public class BlockModel {
    static int level;
    static String [] blockType= new String[]{"Move","For Loop", "While loop", "If ___ then "};
    public static int getLevel(){
        return level;
    }
    public static String getType(int index){
        return blockType[index-1];
    }
    public static void setLevel(int newLevel){
        level =newLevel;
    }
}
