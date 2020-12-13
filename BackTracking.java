public class BackTracking {
    private int action; // 0 == delete , 1 == insert
    private int data;
    private int index;

    public BackTracking(int action, int data, int index){
        this.action = action;
        this.data = data;
        this.index = index;
    }

    public int getAction(){
        return action;
    }

    public int getData(){
        return data;
    }

    public int getIndex(){
        return index;
    }
}
