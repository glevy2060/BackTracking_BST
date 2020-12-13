public class BackTrackNode {
        private int action; // 0 == delete , 1 == insert
        private int numOfSonsBefore;
        private int whichSon; // 0=noParent 1=left 2=right
        private int originalKey;
        private int suc;
        private BacktrackingBST.Node leftSon;
        private BacktrackingBST.Node rightSon;
        private BacktrackingBST.Node node;
        private BacktrackingBST.Node sucNode;

        public BackTrackNode(int action, BacktrackingBST.Node node, int numOfSonsBefore, int whichSon, int suc, BacktrackingBST.Node sucNode){
            this.action = action;
            this.node = node;
            this.numOfSonsBefore = numOfSonsBefore;
            this.leftSon = node.left;
            this.rightSon = node.right;
            this.whichSon = whichSon;
            this.originalKey = node.getKey(); // notice that this is the original key (depends on the code);
            this.suc = suc;
            this.sucNode = sucNode;
        }

        public int getSuc(){
            return suc;
        }

        public int getAction(){
            return action;
        }

        public BacktrackingBST.Node getNode(){
            return node;
        }


        public int getNumOfSonsBefore(){
            return numOfSonsBefore;
        }
        public BacktrackingBST.Node getSucNode(){
            return sucNode;
        }

        public int getWhichSon(){
            return whichSon;
        }

        public BacktrackingBST.Node getLeftSon(){
            return leftSon;
        }

        public BacktrackingBST.Node getRightSon(){
            return rightSon;
        }

        public int getOriginalKey(){
            return originalKey;
        }
    }


