import java.sql.SQLOutput;

public class constructingTrees {
    public static void main(String[] args){
        //Question 15 Part a
        System.out.println("Question 15 Part a");
        //System.out.println("Random Array 10,000 Elements Recursive BST");
        arraysOfIntegers ar = new arraysOfIntegers();
        int[] ranRec = ar.getRandomArray(10000);
        avlNode r1 = new avlNode(ranRec[0]);
        for(int i = 1; i < ranRec.length; i++){
            r1.BSTinsertIter(r1.getRoot(r1), ranRec[i]);
        }

        //System.out.println("BST Recursive Counter: " + r1.counter);
        r1.setCounter(0);

        System.out.println("-------------------------------------");

        System.out.println("Question 5 Part c");

        arraysOfIntegers ac = new arraysOfIntegers();
        int[] ranItr = ac.getRandomArray(10000);
        avlNode i1 = new avlNode(ranItr[0]);
        avlNode i2 = new avlNode(ranItr[0]);
        for(int i = 1; i < ranItr.length; i++){
            i1.BSTinsertIter(i1.getRoot(i1), ranItr[i]);
        }
        i1.setCounter(0);
        for(int i = 1; i < ranItr.length; i++){
            i2.insertIter(i2.getRoot(i2), ranItr[i]);
        }
        i2.setCounter(0);

        System.out.println("-------------------------------------");

        System.out.println("Question 6 Part b");
        System.out.println("Random Array 10,000 Elements");
        arraysOfIntegers ai = new arraysOfIntegers();
        int[] ran = ai.getRandomArray(10000);
        avlNode a1 = new avlNode(ran[0]);
        avlNode a2 = new avlNode(ran[0]);
        for(int i = 1; i < ran.length; i++){
            a1.insertIter(a1.getRoot(a1), ran[i]);
        }
        System.out.println("AVL Counter: " + a1.counter);
        a1.setCounter(0);
        for(int i = 1; i < ran.length; i++){
            a2.BSTinsertIter(a2.getRoot(a2), ran[i]);
        }
        System.out.println("BST Counter: " + a2.counter);
        a2.setCounter(0);

        System.out.println("-------------------------------------");
        System.out.println("Question 6 Part c");
        System.out.println("Sorted Array 10,000 Elements");
        arraysOfIntegers bi = new arraysOfIntegers();
        int[] sort = bi.getSortedArray(10000);
        avlNode b1 = new avlNode(sort[0]);
        avlNode b2 = new avlNode(sort[0]);
        for(int i = 1; i < sort.length; i++){
            b1.insertIter(b1.getRoot(b1), sort[i]);
        }
        System.out.println("AVL Counter: " + b1.counter);
        b1.setCounter(0);
        for(int i = 1; i < sort.length; i++){
            b2.BSTinsertIter(b2.getRoot(b2), sort[i]);
        }
        System.out.println("BST Counter: " + b2.counter);
        b2.setCounter(0);

    }
}
