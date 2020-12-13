public class BacktrackingArray implements Array<Integer>, Backtrack {
    private Stack stack;
    private int[] arr;
    private int end;

    // Do not change the constructor's signature
    public BacktrackingArray(Stack stack, int size) {
        this.stack = stack;
        arr = new int[size];
        this.end = 0;
    }

    @Override
    public Integer get(int index){
        if(index >= end ) return null;
        return arr[index];
    }

    @Override
    public Integer search(int x) {
        for(int i = 0; i< end; i ++){
            if(arr[i] == x){
                return i;
            }
        }
        return -1;
    }

    @Override
    public void insert(Integer x) {
        if(end == arr.length || x== null) throw new IllegalArgumentException();
        arr[end] = x;
        end = end +1;

        BackTracking insert = new BackTracking(1, x, search(x));
        stack.push(insert);
    }

    @Override
    public void delete(Integer index) {
        if(index!= null & index < end){
            BackTracking delete = new BackTracking(0, get(index), index);
            stack.push(delete);
            arr[index] = arr[end-1];
            arr[end-1] = 0;
            end = end -1;
        }




    }

    @Override
    public Integer minimum() {
        if(end == 0) return null;
        int min = 0;
        for(int i=1; i<end; i++){
            if(arr[i] < arr[min]){
                min = i;
            }
        }
        return min;
    }

    @Override
    public Integer maximum() {
        if(end == 0) return null;
        int max = 0;
        for(int i=1; i<end; i++){
            if(arr[i] > arr[max]){
                max = i;
            }
        }
        return max;
    }

    @Override
    public Integer successor(Integer index) {
        int suc = maximum();
        for(int i=0; i< end; i++){
            if(arr[i] > arr[index] & arr[i] < arr[suc]){
                suc = i;
            }
        }

        if(arr[suc] == arr [index]){
            return -1;
        }
        return suc;
    }

    @Override
    public Integer predecessor(Integer index) {
        int pre = minimum();
        for(int i=0; i< end; i++){
            if(arr[i] < arr[index] & arr[i] > arr[pre]){
                pre = i;
            }
        }

        if(arr[pre] == arr [index]){
            return -1;
        }

        return pre;
    }

    @Override
    public void backtrack() {
        if(!stack.isEmpty()){
            BackTracking back = (BackTracking) stack.pop();
            int index = back.getIndex();

            if(back.getAction() == 1){ //insertion
                arr[index] = 0;
                end = end -1;

            } else { // deletion
                arr[end] = arr[index];
                arr[index] = back.getData();
                end = end+1;
            }

            System.out.println("backtracking performed");
        }
    }

    @Override
    public void retrack() {
        // Do not implement anything here!!
    }

    @Override
    public void print() {
        String str = "";
        for (int i=0; i<end; i++){
            str = str+ " " + arr[i];
        }

        if(str.length() > 1){
            str = str.substring(1);
            System.out.println(str);
        }

    }


}

