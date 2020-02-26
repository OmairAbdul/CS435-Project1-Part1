import java.sql.SQLOutput;

public class node {
    private int value;
    private node parent;
    private node leftChild;
    private node rightChild;

    //constructor
    public node(int val){
        value = val;
    }

    //getters
    public int getValue(){
        return value;
    }

    public node getParent(){
        return parent;
    }

    public node getLeftChild(){
        return leftChild;
    }

    public node getRightChild(){
        return rightChild;
    }

    //setters
    public  void setValue(int val){
        value = val;
    }

    public void setParent(node p){
        parent = p;
    }

    public  void setLeftChild(node lc){
        leftChild = lc;
    }

    public void setRightChild(node rc){
        rightChild = rc;
    }

    //insertRec
    public static void insertRec(node n, int val){
        if(n.getLeftChild() == null && n.getValue() > val){
            node insertNode = new node(val);
            insertNode.setParent(n);
            n.setLeftChild(insertNode);
            return;
        }
        if(n.getRightChild() == null && n.getValue() < val){
            node insertNode = new node(val);
            insertNode.setParent(n);
            n.setRightChild(insertNode);
            return;
        }
        if(n.getValue() > val){
            insertRec(n.getLeftChild(), val);
        }
        else{
            insertRec(n.getRightChild(), val);
        }

    }

    //insertIter
    public static void insertIter(node n, int val){
        boolean insertSuccessful = false;
        node insertNode = new node(val);
        while(!insertSuccessful){
            if(n.getValue() > val){
                if(n.getLeftChild() == null){
                    n.setLeftChild(insertNode);
                    insertNode.setParent(n);
                    insertSuccessful = true;
                }
                else{
                    n = n.getLeftChild();
                }
            }
            else{
                if(n.getRightChild() == null){
                    n.setRightChild(insertNode);
                    insertNode.setParent(n);
                    insertSuccessful = true;
                }
                else{
                    n = n.getRightChild();
                }
            }
        }
    }

    //deleteRec
    public static void deleteRec(node n, node dn){
        if(n.getValue() == dn.getValue()){
            if(n.getLeftChild() == null && n.getRightChild() == null){
                if(n.getParent().getLeftChild() == n){
                    n.getParent().setLeftChild(null);
                }
                else{
                    n.getParent().setRightChild(null);
                }
                return;
            }
            if(n.getLeftChild() != null && n.getRightChild() == null){
                if(n.getParent().getValue() > n.getLeftChild().getValue()){
                    n.getParent().setLeftChild(n.getLeftChild());
                    n.getLeftChild().setParent(n.getParent());
                }
                else{
                    n.getParent().setRightChild(n.getLeftChild());
                    n.getLeftChild().setParent(n.getParent());
                }
                n = null;
                return;
            }
            if(n.getLeftChild() == null && n.getRightChild() != null){
                if(n.getParent().getValue() > n.getRightChild().getValue()){
                    n.getParent().setLeftChild(n.getRightChild());
                    n.getRightChild().setParent(n.getParent());
                }
                else{
                    n.getParent().setRightChild(n.getRightChild());
                    n.getRightChild().setParent(n.getParent());
                }
                n = null;
                return;
            }
            if(n.getLeftChild() != null && n.getRightChild() != null){
                node minRightSubTree = findMinRec(n.getRightChild());
                n.setValue(minRightSubTree.getValue());
                deleteRec(n.getRightChild(), findMinRec(n.getRightChild()));
                return;
            }
        }
        if(n.getValue() > dn.getValue()){
            deleteRec(n.getLeftChild(), dn);
        }
        else{
            deleteRec(n.getRightChild(), dn);
        }

    }

    //deleteIter
    public static void deleteIter(node n, node dn){
        boolean deleteSuccessful = false;
        while(!deleteSuccessful){
            if(n.getValue() == dn.getValue()){
                if(n.getLeftChild() == null && n.getRightChild() == null){
                    if(n.getParent().getLeftChild() == n){
                        n.getParent().setLeftChild(null);
                    }
                    else{
                        n.getParent().setRightChild(null);
                    }
                }
                else if(n.getLeftChild() != null && n.getRightChild() == null){
                    if(n.getParent().getValue() > n.getLeftChild().getValue()){
                        n.getParent().setLeftChild(n.getLeftChild());
                        n.getLeftChild().setParent(n.getParent());
                    }
                    else{
                        n.getParent().setRightChild(n.getLeftChild());
                        n.getLeftChild().setParent(n.getParent());
                    }
                }
                else if(n.getLeftChild() == null && n.getRightChild() != null){
                    if(n.getParent().getValue() > n.getRightChild().getValue()){
                        n.getParent().setLeftChild(n.getRightChild());
                        n.getRightChild().setParent(n.getParent());
                    }
                    else{
                        n.getParent().setRightChild(n.getRightChild());
                        n.getRightChild().setParent(n.getParent());
                    }
                }
                else{
                    node minRightSubTree = findMinRec(n.getRightChild());
                    n.setValue(minRightSubTree.getValue());
                    n = n.getRightChild();
                    while(n.getLeftChild() != null){
                        n = n.getLeftChild();
                    }
                    if(n.getLeftChild() == null && n.getRightChild() == null){
                        if(n.getParent().getLeftChild() == n){
                            n.getParent().setLeftChild(null);
                        }
                        else{
                            n.getParent().setRightChild(null);
                        }
                    }
                    else if(n.getLeftChild() != null && n.getRightChild() == null){
                        if(n.getParent().getValue() > n.getLeftChild().getValue()){
                            n.getParent().setLeftChild(n.getLeftChild());
                            n.getLeftChild().setParent(n.getParent());
                        }
                        else{
                            n.getParent().setRightChild(n.getLeftChild());
                            n.getLeftChild().setParent(n.getParent());
                        }
                    }
                    else if(n.getLeftChild() == null && n.getRightChild() != null){
                        if(n.getParent().getValue() > n.getRightChild().getValue()){
                            n.getParent().setLeftChild(n.getRightChild());
                            n.getRightChild().setParent(n.getParent());
                        }
                        else{
                            n.getParent().setRightChild(n.getRightChild());
                            n.getRightChild().setParent(n.getParent());
                        }
                    }
                }
                deleteSuccessful = true;
            }
            else{
                if( n.getValue() > dn.getValue()){
                    n = findPrevIter(n);
                }
                else{
                    n = findNextIter(n);
                }
            }
        }
    }
    //findNextRec
    public static node findNextRec(node n){
        if(n.getRightChild() != null){
            return findNextRecHelper1(n.getRightChild());
        }
        else{
            return findNextRecHelper2(n);
        }

    }

    //findNextRec helper function 1
    public static node findNextRecHelper1(node n){
        if(n.getLeftChild() == null){
            return n;
        }
        return(findNextRecHelper1(n.getLeftChild()));
    }

    //findNextRec helper function 2
    public static node findNextRecHelper2(node n){
        if(n.getParent().getLeftChild() == n){
            return n.getParent();
        }
        return findNextRecHelper2(n.getParent());
    }

    //findNextIter
    public static node findNextIter(node n){
        if(n.getRightChild() != null){
            return findMinIter(n.getRightChild());
        }
        else{
            while(n.getParent().getLeftChild() != n){
                n = n.getParent();
            }
            return n.getParent();
        }

    }

    public static node findPrevIter(node n){
        if(n.getLeftChild() != null){
            return findMaxIter(n.getLeftChild());
        }
        else{
            while(n.getParent().getRightChild() != n){
                n = n.getParent();
            }
            return n.getParent();
        }
    }

    //findPrevRec
    public static node findPrevRec(node n){
        if(n.getLeftChild() != null){
            return findPrevRecHelper1(n.getLeftChild());
        }
        else{
            return findPrevRecHelper2(n);
        }
    }


    //findPrevRecHelper1
    public static node findPrevRecHelper1(node n){
        if(n.getRightChild() == null){
            return n;
        }
        return findPrevRecHelper1(n.getRightChild());
    }

    //findPrevRecHelper2
    public static node findPrevRecHelper2(node n){
        if(n.getParent().getRightChild() == n){
            return n.getParent();
        }
        else{
            return findPrevRecHelper2(n.getParent());
        }
    }


    //findMinRec
    public static node findMinRec(node n){
        if(n.getLeftChild() == null){
            return n;
        }
        return findMinRec(n.getLeftChild());
    }

    //findMinIter
    public static node findMinIter(node n){
        while(n.getLeftChild() != null){
            n = n.getLeftChild();
        }
        return n;
    }


    //findMaxRec
    public static node findMaxRec(node n){
        if(n.getRightChild() == null){
            return n;
        }
        return findMaxRec(n.getRightChild());
    }

    //findMaxIter
    public static node findMaxIter(node n){
        while(n.getRightChild() != null){
            n = n.getRightChild();
        }
        return n;
    }

    //sorted BST Problem 2c
    public static int[] sortedBST(int[] unsortedArray){
        int[] sortedArray= new int[unsortedArray.length];

        //making a BST from given array
        node root = new node(unsortedArray[0]);
        for(int i = 1; i < unsortedArray.length; i++){
            insertIter(root, unsortedArray[i]);
        }
        int pos = 0;
        while(pos != unsortedArray.length){
            sortedArray[pos] = findMinRec(root).getValue();
            if(root == findMinRec(root)){
                if(root.getRightChild() != null){
                    root= root.getRightChild();
                }
            }
            else{
                deleteIter(root, findMinRec(root));
            }
            pos+=1;

        }

        return sortedArray;
    }

    public static void main(String[] args){
        node n1 = new node(5);
        node n2 = new node(3);
        node n3 = new node(10);
        node n4 = new node(1);
        node n5 = new node(4);
        node n6 = new node(8);
        node n7 = new node(13);
        node n8 = new node(12);
        node n9 = new node(15);
        node n10 = new node(11);
        node n11 = new node(20);
        n1.setLeftChild(n2);
        n2.setParent(n1);
        n1.setRightChild(n3);
        n3.setParent(n1);
        n2.setLeftChild(n4);
        n4.setParent(n2);
        n2.setRightChild(n5);
        n5.setParent(n2);
        n3.setLeftChild(n6);
        n6.setParent(n3);
        n3.setRightChild(n7);
        n7.setParent(n3);
        n7.setLeftChild(n8);
        n8.setParent(n7);
        n7.setRightChild(n9);
        n9.setParent(n7);
        n8.setLeftChild(n10);
        n10.setParent(n8);
        n9.setRightChild(n11);
        n11.setParent(n9);

        //deleteIter(n11, n5);
        //System.out.println(n2.getRightChild().getValue());
        int[] testArray = {5, 3, 10, 1, 4, 8, 13, 12, 15, 11, 20};
        int[] solvedArray = sortedBST(testArray);
        for(int i = 0; i < solvedArray.length; i++){
            System.out.println(solvedArray[i]);
        }

    }



}
