public class Warmup {
    public static int backtrackingSearch(int[] arr, int x, int fd, int bk, Stack myStack) {
        if(arr == null | bk >= arr.length) throw new IllegalArgumentException();

        int index = 0;
        int counter = 1;

        while (index < arr.length & index >= 0) {
            if(arr[index] == x) {
                return index;
            }
            myStack.push(index);
            if(counter == fd) {
                counter = 1;
                index = index -bk +1 ;
                for(int i=0 ; i<bk; i++){
                    myStack.pop();
                }

            } else {
                counter = counter+1;
                index = index +1;
            }

        }

        return -1;
    }

    public static int consistentBinSearch(int[] arr, int x, Stack myStack) {
        if(arr == null) throw new IllegalArgumentException();
        int low = 0;
        int high = arr.length-1;

        while (low <= high){
            int middle = (low + high) / 2;

            myStack.push(high);
            myStack.push(middle);
            myStack.push(low);

            int isConsistent = isConsistent(arr);

            if(isConsistent != 0) {
                for (int i = 0; i < isConsistent; i++) {
                    if (!myStack.isEmpty()) {
                        low = (Integer)myStack.pop();
                        middle = (Integer)myStack.pop();
                        high = (Integer)myStack.pop();
                    }
                }
            }

            if (arr[middle] == x){
                return middle;
            }
            if(x > arr[middle]){
                low = middle +1;
            } else {
                high = middle -1;
            }

        }

        return -1;
    }


    private static int isConsistent(int[] arr) {
        double res = Math.random() * 100 - 75;

        if (res > 0) {
            return (int)Math.round(res / 10);
        } else {
            return 0;
        }
    }
}
