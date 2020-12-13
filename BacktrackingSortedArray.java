public class BacktrackingSortedArray implements Array<Integer>, Backtrack {
    private Stack stack;
    private int[] arr;
    private int end;

    // Do not change the constructor's signature
    public BacktrackingSortedArray(Stack stack, int size) {
        this.stack = stack;
        arr = new int[size];
        end = 0;
    }
    @Override
    public Integer get(int index){
        if(index >= end) return null;
        return arr[index];
    }

    @Override
    public Integer search(int x) {

        int low = 0, high = end-1;

        while (low <= high){
            int middle = (low+high)/2;
            if(arr[middle] == x){
                return middle;
            }
            else
            if (x < arr[middle])
                high = middle-1;
            else
                low = middle+1;
        }

        return -1;

    }

    @Override
    public void insert(Integer x) {
        if(end == arr.length) throw new IllegalArgumentException();
        int index = 0;
        while (end!= index && x > arr[index]){
            index = index +1;
        }

        BackTracking insert = new BackTracking(1, x, index);
        stack.push(insert);

        for (int i = end-1; i>=index ; i--){
            arr[i+1] = arr[i];
        }

        arr[index] = x;
        end = end +1;
    }


    @Override
    public void delete(Integer index) {
        if(index < end){
            BackTracking delete = new BackTracking(0, get(index), index);
            stack.push(delete);

            for (int i= index+1 ; i<end; i++){
                arr[i-1] = arr[i];
            }
            arr[end-1] = 0;
            end = end -1;
        }

    }

    @Override
    public Integer minimum() {
        if(end == 0) return -1;
        return 0;
    }

    @Override
    public Integer maximum() {
        if(end == 0) return -1;
        return end-1;
    }

    @Override
    public Integer successor(Integer index) {
        if(index == end -1) return -1;
        return index+1;
    }

    @Override
    public Integer predecessor(Integer index) {
        if(index == 0) return -1;
        return index -1;
    }

    @Override
    public void backtrack() {
        if(!stack.isEmpty()){
            BackTracking back = (BackTracking) stack.pop();
            int index = back.getIndex();

            if(back.getAction() == 1){ // insertion action
                for (int i= index+1 ; i<end; i++){
                    arr[i-1] = arr[i];
                }
                end = end-1;

            }else {
                for (int i = end-1; i>=index ; i--){
                    arr[i+1] = arr[i];
                }

                arr[index] = back.getData();
                end = end +1;
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
