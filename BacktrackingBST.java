public class BacktrackingBST implements Backtrack, ADTSet<BacktrackingBST.Node> {
    private Stack stack;
    private Stack redoStack;
    BacktrackingBST.Node root = null;
    private boolean isBacktrack = false;

    // Do not change the constructor's signature
    public BacktrackingBST(Stack stack, Stack redoStack) {
        this.stack = stack;
        this.redoStack = redoStack;
    }

    public Node getRoot() {
        return root;
    }
	
    public Node search(int x) {
        BacktrackingBST.Node tempRoot = getRoot();

        while (tempRoot != null){
            int key = tempRoot.getKey();

            if(key == x){
                return tempRoot;
            } else if(key < x) {
                tempRoot = tempRoot.right;
            } else {
                tempRoot = tempRoot.left;
            }
        }

        return tempRoot;
    }

    private void newInsert(BacktrackingBST.Node z){
        BacktrackingBST.Node tempRoot = getRoot();

        if(tempRoot == null){
            root = z;
        } else {
            int zKey = z.getKey();
            boolean isInserted = false;

            while (!isInserted){ // check if we need to add the = check inside
                int key = tempRoot.getKey();

                if(key > zKey){
                    if(tempRoot.left == null){
                        tempRoot.left = z;
                        z.parent = tempRoot;
                        isInserted = true;
                    } else {
                        tempRoot = tempRoot.left;
                    }
                } else {
                    if(tempRoot.right == null){
                        tempRoot.right =z;
                        z.parent = tempRoot;
                        isInserted = true;
                    } else {
                        tempRoot = tempRoot.right;

                    }
                }

            }
        }


    }

    public void insert(BacktrackingBST.Node z) {

        newInsert(z);
        BackTrackNode insert = new BackTrackNode(1, z, 0,0, 0, null); // index & numOfSons not used
        stack.push(insert);
        isBacktrack = false;

    }

    public void delete(Node x) {
        if(x == null) throw new IllegalArgumentException();

        int whichSon = whichSon(x);

        if (x.left == null & x.right == null) { // case 1: leaf
            stack.push(new BackTrackNode(0, x, 0, whichSon, 0, null)); // for backTrack
            if(whichSon == 0){ //applies only if we have single node in our tree
               x = null;
           } else {
               if(whichSon == 1){
                  x.parent.left = null;
               }else {
                   x.parent.right = null;
               }
           }
        } else if(x.left == null || x.right == null) { // case 2: one child
            stack.push(new BackTrackNode(0, x, 1,whichSon, 0, null)); // for backTrack
            if(whichSon == 0){ // no dad situation
                if(x.left == null){
                    x = x.right;
                } else{
                    x = x.left;
                }
            } else{ // yes dad situation
                if (whichSon == 1){ // left son for parent
                    if (x.left == null){
                        x.right.parent = x.parent;
                        x.parent.left = x.right;
                    } else {
                        x.left.parent = x.parent;
                        x.parent.left = x.left;
                    }
                } else{ // right son for parent
                    if (x.left == null){
                        x.right.parent = x.parent;
                        x.parent.right = x.right;
                    } else {
                        x.left.parent = x.parent;
                        x.parent.right = x.left;
                    }
                }

            }

        } else { // case 3 : two sons
            BacktrackingBST.Node suc = successor(x);
            int keySuc = suc.getKey();

            BacktrackingBST.Node newX = new BacktrackingBST.Node(x.getKey(),null);
            newX.left = x.left;
            newX.right = x.right;
            newX.parent = x.parent;

            stack.push(new BackTrackNode(0, newX, 2, whichSon, keySuc, successor(newX))); // for backTrack --- check this one
            x.key = keySuc;
            int sucWichSon = whichSon(suc);
            if (suc.left == null & suc.right == null) { // 1 leaf to delete
                if (sucWichSon == 1) {
                    suc.parent.left = null;
                } else {
                    suc.parent.right = null;
                }
            } else {
                if (sucWichSon == 1){ // left son for parent
                    if (suc.left == null){
                        suc.right.parent = suc.parent;
                        suc.parent.left = suc.right;
                    } else {
                        suc.left.parent = suc.parent;
                        suc.parent.left = suc.left;
                    }
                } else{ // right son for parent
                    if (suc.left == null){
                        suc.right.parent = suc.parent;
                        suc.parent.right = suc.right;
                    } else {
                        suc.left.parent = suc.parent;
                        suc.parent.right = suc.left;
                    }
                }
            }


        }

        isBacktrack = false;
    }

    public Node minimum() {
        BacktrackingBST.Node tempRoot = getRoot();
        while (tempRoot.left != null){
            tempRoot = tempRoot.left;
        }

        return tempRoot;
    }

    public Node maximum() {
        BacktrackingBST.Node tempRoot = getRoot();

        while (tempRoot.right != null){
            tempRoot = tempRoot.right;
        }

        return tempRoot;
    }

    public Node successor(Node x) {
        if(x.right != null) { // find min in right tree
            BacktrackingBST.Node tempRoot = x.right;
            while (tempRoot.left != null){
                tempRoot = tempRoot.left;
            }
            return tempRoot;
        } else {
            BacktrackingBST.Node parent = x.parent;
            while (parent!= null & x == parent.right) {
                x = parent;
                parent = parent.parent;
            }
            return parent;
        }

    }

    public Node predecessor(Node x) {

        if(x.left != null) { // find max in left tree
            BacktrackingBST.Node tempRoot = x.left;
            while (tempRoot.right != null){
                tempRoot = tempRoot.right;
            }
            return tempRoot;
        } else {
            BacktrackingBST.Node parent = x.parent;
            while (parent!= null & x == parent.left) {
                x = parent;
                parent = parent.parent;
            }
            return parent;
        }

    }

    private int whichSon(Node x){
        // if no parent return 0
        // if returns 1 it means left son
        // else return 2
        if(x.parent == null){
            return 0;
        } else if (x.parent.left != null && x.parent.left.getKey() == x.getKey()){
            return 1;
        } else{
            return 2;
        }

    }

    @Override
    public void backtrack() {
        if(!stack.isEmpty()) {
            isBacktrack = true;
            BackTrackNode back = (BackTrackNode) stack.pop();
            if(back.getAction() == 1){ // case1: backtracking insert (delete)
                Node node = back.getNode();

                if(whichSon(node) == 0){
                    redoStack.push(new BackTrackNode(0, node, 0, 0, 0, null));
                    node = null;
                } else if(whichSon(node) == 1){
                    redoStack.push(new BackTrackNode(0, node, 0, 1, 0, null));
                    node.parent.left = null;
                } else {
                    redoStack.push(new BackTrackNode(0, node, 0, 2, 0, null));
                    node.parent.right = null;
                }

            } else{ // case2: backtracking delete
                int numOfSonsBefore = back.getNumOfSonsBefore();
                int whichSon = back.getWhichSon();
                BacktrackingBST.Node newBack = new BacktrackingBST.Node(back.getOriginalKey(), null); // new change due to restack bug
                if(back.getNode().left!=null){
                    newBack.left = back.getLeftSon();
                    newBack.left.parent = newBack;

                }
                if(back.getNode().right!=null){
                    newBack.right = back.getRightSon();
                    newBack.right.parent = newBack;
                }

                newBack.parent = back.getNode().parent;
                redoStack.push(new BackTrackNode(1, newBack, numOfSonsBefore, whichSon, back.getSuc(),null));


                if(numOfSonsBefore == 0) { // case 2.1: leaf
                    if(whichSon == 0){
                        root = back.getNode();
                    } else if(whichSon == 1){
                        back.getNode().parent.left = back.getNode();
                    } else {
                        back.getNode().parent.right = back.getNode();
                    }
                } else if(numOfSonsBefore == 1){ // case 2.2: one child
                    if(whichSon == 0){ // case 2.2.1 : no dad
                        root = back.getNode();
                        if(root.left !=null) {
                            root.left.parent = root;
                        } else {
                            root.right.parent = root;
                        }

                    } else if(whichSon == 1) { // case 2.2.2: left son to his parent
                        back.getNode().parent.left = back.getNode();

                        if(back.getLeftSon() !=null) {
                            back.getLeftSon().parent = back.getNode();
                        } else {
                            back.getRightSon().parent = back.getNode();
                        }
                    } else { // case 2.2.3: right son to his parent
                        back.getNode().parent.right = back.getNode();

                        if(back.getLeftSon() !=null) {
                            back.getLeftSon().parent = back.getNode();
                        } else {
                            back.getRightSon().parent = back.getNode();
                        }
                    }
                } else { // case 2.3: two sons
                    int keySuc = back.getSuc();
                    back.getNode().key = newBack.getKey();
                    if(whichSon == 0){
                        root = newBack;
                    } else if(whichSon == 1){
                        newBack.parent.left = newBack;
                    } else {
                        newBack.parent.right = newBack;
                    }

                    // insert the succsesor to tree again

                    if(back.getSucNode().parent.getKey() > back.getSucNode().getKey()){ // if left son
                        back.getSucNode().parent.left = back.getSucNode();
                    } else {
                        back.getSucNode().parent.right = back.getSucNode();
                    }
                }
            }

            System.out.println("backtracking performed");

        }


    }

    @Override
    public void retrack() {
        if(!isBacktrack){
            while (!redoStack.isEmpty()){
                redoStack.pop();
            }
        }

        if(!redoStack.isEmpty()){
            BackTrackNode redo = (BackTrackNode) redoStack.pop();
            if(redo.getAction() == 0){ //case 1: undo delete
                BacktrackingBST.Node node = new BacktrackingBST.Node(redo.getOriginalKey(), null);
                insert(node);

            } else { //case 2: undo insert (delete)
                delete(redo.getNode());
            }
        }

        isBacktrack = true;

    }

    public void printPreOrder(){
        print();
    }

    @Override
    public void print() {
        if(root != null){
            System.out.println(root.print().substring(1));
        }
    }

    public static class Node{
    	//These fields are public for grading purposes. By coding conventions and best practice they should be private.
        public BacktrackingBST.Node left;
        public BacktrackingBST.Node right;
        
        private BacktrackingBST.Node parent;
        private int key;
        private Object value;
        private int originalKey;

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
            this.originalKey = getKey();
        }

        public int getOriginalKey(){
            return originalKey;
        }

        public int getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public String print(){
            String s = "";
            s = s +" "+ getKey();

            if(left!= null){
                s = s + left.print();
            }

            if(right != null){
                s = s + right.print();
            }

            return s;
        }

    }


}
