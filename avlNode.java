import java.util.ArrayList;
public class avlNode {
    public static int counter;
    private int value;
    private avlNode parent;
    private avlNode leftChild;
    private avlNode rightChild;

    //constructor
    public avlNode(int val){
        value = val;
    }

    //getters
    public int getValue(){
        return value;
    }

    public avlNode getParent(){
        return parent;
    }

    public avlNode getLeftChild(){
        return leftChild;
    }

    public avlNode getRightChild(){
        return rightChild;
    }

    //setters
    public  void setValue(int val){
        value = val;
    }

    public void setParent(avlNode p){
        parent = p;
    }

    public  void setLeftChild(avlNode lc){
        leftChild = lc;
    }

    public void setRightChild(avlNode rc){
        rightChild = rc;
    }

    public void setCounter(int num){
        counter=num;
    }

    public static int getBF(avlNode an){
        int leftHeight;
        if(an.getLeftChild() == null){
            leftHeight = 0;
        }
        else{
            leftHeight = getHeight(an.getLeftChild());
        }
        int rightHeight;
        if(an.getRightChild() == null){
            rightHeight = 0;
        }
        else{
            rightHeight = getHeight(an.getRightChild());
        }
        int BF = leftHeight - rightHeight;
        return BF;
    }

    public static int getHeight(avlNode an){
        int height = 1;
        if(an.getLeftChild() == null && an.getRightChild() == null){
            return height;
        }
        ArrayList<avlNode> levelArray = new ArrayList<>();
        if(an.getLeftChild()!= null){
            levelArray.add(an.getLeftChild());
        }
        if(an.getRightChild()!=null){
            levelArray.add(an.getRightChild());
        }
        while(!levelArray.isEmpty()){
            ArrayList<avlNode>  tempArray = new ArrayList<>();
            for(int j = 0; j<levelArray.size(); j++){
                tempArray.add(levelArray.get(j));
            }
            levelArray.clear();
            for(int i = 0; i < tempArray.size(); i++){
                if(tempArray.get(i).getLeftChild()!= null){
                    levelArray.add(tempArray.get(i).getLeftChild());
                }
                if(tempArray.get(i).getRightChild()!= null){
                    levelArray.add(tempArray.get(i).getRightChild());
                }
            }
            height+=1;
        }
        return height;
    }
    public static int recgetHeight(avlNode an){
        if(an.getLeftChild() == null && an.getRightChild() == null){
            return 1;
        }
        if(an.getRightChild() == null){
            return recgetHeight(an.getLeftChild()) + 1;
        }
        else if(an.getLeftChild() == null){
            return recgetHeight(an.getRightChild()) + 1;
        }
        else{
            return Math.max(recgetHeight(an.getLeftChild()), recgetHeight(an.getRightChild()))+1;
        }
    }
    //LL Rotation
    public static void LL(avlNode a, avlNode b, avlNode c, avlNode aParent, avlNode bRightChild){
        b.setParent(null);
        if(aParent!=null){
            if(aParent.getValue() > b.getValue()){
                aParent.setLeftChild(b);
                b.setParent(aParent);
            }
            else{
                aParent.setRightChild(b);
                b.setParent(aParent);
            }
        }

        a.setLeftChild(null);
        b.setRightChild(a);
        a.setParent(b);

        b.setLeftChild(c);
        c.setParent(b);

        if(bRightChild!=null){
            a.setLeftChild(bRightChild);
            bRightChild.setParent(a);
        }
    }

    //LR Rotation
    public static void LR(avlNode a, avlNode b, avlNode c, avlNode aParent, avlNode cRightChild, avlNode cLeftChild){
        c.setParent(null);
        if(aParent!=null){
            if(aParent.getValue() > c.getValue()){
                aParent.setLeftChild(c);
                c.setParent(aParent);
            }
            else{
                aParent.setRightChild(c);
                c.setParent(aParent);
            }
        }

        a.setLeftChild(null);
        c.setRightChild(a);
        a.setParent(c);

        b.setRightChild(null);
        c.setLeftChild(b);
        b.setParent(c);

        if(cRightChild!= null){
            a.setLeftChild(cRightChild);
            cRightChild.setParent(a);
        }

        if(cLeftChild != null){
            b.setRightChild(cLeftChild);
            cLeftChild.setParent(b);
        }


    }

    //RR Rotation
    public static void RR(avlNode a, avlNode b, avlNode c, avlNode aParent, avlNode bLeftChild){
        b.setParent(null);
        if(aParent!=null){
            if(aParent.getValue() > b.getValue()){
                aParent.setLeftChild(b);
                b.setParent(aParent);
            }
            else{
                aParent.setRightChild(b);
                b.setParent(aParent);
            }
        }

        a.setRightChild(null);
        b.setLeftChild(a);
        a.setParent(b);

        b.setLeftChild(a);
        a.setParent(b);

        if(bLeftChild!=null){
            a.setRightChild(bLeftChild);
            bLeftChild.setParent(a);
        }
    }

    //RL Rotation
    public static void RL(avlNode a, avlNode b, avlNode c, avlNode aParent, avlNode cLeftChild, avlNode cRightChild){
        c.setParent(null);
        if(aParent!=null){
            if(aParent.getValue() > c.getValue()){
                aParent.setLeftChild(c);
                c.setParent(aParent);
            }
            else{
                aParent.setRightChild(c);
                c.setParent(aParent);
            }
        }

        b.setLeftChild(null);
        c.setRightChild(b);
        b.setParent(c);

        a.setRightChild(null);
        c.setLeftChild(a);
        a.setParent(c);

        if(cLeftChild!=null){
            a.setRightChild(cLeftChild);
            cLeftChild.setParent(a);
        }
        if(cRightChild!=null){
            b.setLeftChild(cRightChild);
            cRightChild.setParent(b);
        }


    }

    //AVL Tree insert
    // Call BST insert iter
    //1.Traverse up from inserted node till root
    //2. If balance factor of ancestor does not == -1 , 0, 1 - check if it is LL, LR, RR, RL imbalance
    //3. Call balance function depending on type of imbalance
    //4. Check to see if entire tree is balanced
    //insertIter
    public static avlNode BSTinsertIter(avlNode n, int val){
        boolean insertSuccessful = false;
        avlNode insertNode = new avlNode(val);
        while(!insertSuccessful){
            counter+=1;
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
        return insertNode;
    }

    public static void insertIter(avlNode n, int val){
        avlNode theNode = BSTinsertIter(n, val);
        ArrayList<avlNode> bfArray = new ArrayList<avlNode>();
        int index = 0;
        while(theNode != null){
            bfArray.add(theNode);
            index+=1;
            if(getBF(theNode)>1 || getBF(theNode)<-1){
                //case LL imbalance
                if(getBF(bfArray.get(index-1)) == 2 && getBF(bfArray.get(index-2)) == 1){
                    LL(bfArray.get(index-1), bfArray.get(index-2), bfArray.get(index-3), bfArray.get(index-1).getParent(), bfArray.get(index-2).getRightChild());
                }
                //case LR imbalance
                else if(getBF(bfArray.get(index-1)) == 2 && getBF(bfArray.get(index-2)) == -1){
                    LR(bfArray.get(index-1), bfArray.get(index-2), bfArray.get(index-3), bfArray.get(index-1).getParent(),bfArray.get(index-3).getRightChild(), bfArray.get(index-3).getLeftChild());
                }
                //case RR imbalance
                else if(getBF(bfArray.get(index-1)) == -2 && getBF(bfArray.get(index-2)) == -1){
                    RR(bfArray.get(index-1), bfArray.get(index-2), bfArray.get(index-3), bfArray.get(index-1).getParent(), bfArray.get(index-2).getLeftChild());
                }
                //case RL imbalance
                else if(getBF(bfArray.get(index-1)) == -2 && getBF(bfArray.get(index-2)) == 1 ){
                    RL(bfArray.get(index-1), bfArray.get(index-2), bfArray.get(index-3), bfArray.get(index-1).getParent(), bfArray.get(index-3).getLeftChild(), bfArray.get(index-3).getRightChild());

                }
                else{
                    System.out.println(theNode.getValue());
                    System.out.println("MAJOR ERROR ALERT: THINGS ARE GOING CATASTROPHICALLY WRONG!!! ");
                }
                break;
            }
            theNode = theNode.getParent();
        }
    }

    //findMinIter
    public static avlNode findMinIter(avlNode n){
        while(n.getLeftChild() != null){
            n = n.getLeftChild();
        }
        return n;
    }

    //findMaxIter
    public static avlNode findMaxIter(avlNode n){
        while(n.getRightChild() != null){
            n = n.getRightChild();
        }
        return n;
    }


    //findNextIter
    public static avlNode findNextIter(avlNode n){
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

    public static avlNode findPrevIter(avlNode n){
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

    public static void BSTdeleteIter(avlNode n, avlNode dn){
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
                    avlNode minRightSubTree = findMinIter(n.getRightChild());
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

    public static void deleteIter(avlNode n, avlNode dn){
        BSTdeleteIter(n, dn);
        while(dn!=null){
            if(getBF(dn) < -1 || getBF(dn) > 1){
                if(dn.getRightChild()!=null && dn.getLeftChild()!=null){
                    if(getHeight(dn.getLeftChild()) > getHeight(dn.getRightChild())){
                        if(dn.getLeftChild().getLeftChild() != null){
                            LL(dn, dn.getLeftChild(), dn.getLeftChild().getLeftChild(), dn.getParent(), dn.getLeftChild().getRightChild());
                        }
                        else{
                            LR(dn, dn.getLeftChild(), dn.getLeftChild().getRightChild(), dn.getParent(), dn.getLeftChild().getRightChild().getLeftChild(), dn.getLeftChild().getRightChild().getRightChild());
                        }
                    }
                    else{
                        if(dn.getRightChild().getRightChild() != null){
                            RR(dn, dn.getRightChild(), dn.getRightChild(), dn.getParent(), dn.getRightChild().getLeftChild());
                        }
                        else{
                            RL(dn, dn.getRightChild(), dn.getRightChild().getLeftChild(), dn.getParent(), dn.getRightChild().getLeftChild().getLeftChild(), dn.getRightChild().getLeftChild().getRightChild());
                        }
                    }
                }
                else if(dn.getRightChild()==null){
                    if(dn.getLeftChild().getLeftChild() != null){
                        LL(dn, dn.getLeftChild(), dn.getLeftChild().getLeftChild(), dn.getParent(), dn.getLeftChild().getRightChild());
                    }
                    else{
                        LR(dn, dn.getLeftChild(), dn.getLeftChild().getRightChild(), dn.getParent(), dn.getLeftChild().getRightChild().getLeftChild(), dn.getLeftChild().getRightChild().getRightChild());
                    }
                }
                else{
                    if(dn.getRightChild().getRightChild() != null){
                        RR(dn, dn.getRightChild(), dn.getRightChild(), dn.getParent(), dn.getRightChild().getLeftChild());
                    }
                    else{
                        RL(dn, dn.getRightChild(), dn.getRightChild().getLeftChild(), dn.getParent(), dn.getRightChild().getLeftChild().getLeftChild(), dn.getRightChild().getLeftChild().getRightChild());
                    }
                }
                break;
            }
            dn = dn.getParent();
        }



    }

    public static avlNode getRoot(avlNode an){
        while(an.getParent() != null){
            an = an.getParent();
        }
        return an;
    }

    public static void main(String[] args){

        arraysOfIntegers ai = new arraysOfIntegers();
        int[] ran = ai.getRandomArray(10000);
        avlNode a1 = new avlNode(ran[0]);
        avlNode a2 = new avlNode(ran[0]);
        for(int i = 1; i < ran.length; i++){
            insertIter(getRoot(a1), ran[i]);
            BSTinsertIter(getRoot(a2), ran[i]);
        }
        System.out.println("-------------------------------------");
        //System.out.println("AVL Counter: " + a1.counterAVL);
        //System.out.println("BST Counter: " + a2.counterBST);
        System.out.println(getHeight(getRoot(a2)));
        System.out.println(recgetHeight(getRoot(a2)));

        // TEST CODE
        /*avlNode a1 = new avlNode(40);
        BSTinsertIter(getRoot(a1), 20);
        BSTinsertIter(getRoot(a1), 10);
        BSTinsertIter(getRoot(a1), 25);
        BSTinsertIter(getRoot(a1), 30);
        BSTinsertIter(getRoot(a1), 22);
        BSTinsertIter(getRoot(a1), 50);
        BSTinsertIter(getRoot(a1), 12);*/
        /*avlNode a1 = new avlNode(40);
        insertIter(getRoot(a1), 20);
        insertIter(getRoot(a1), 10);
        insertIter(getRoot(a1), 25);
        insertIter(getRoot(a1), 30);
        insertIter(getRoot(a1), 22);
        insertIter(getRoot(a1), 50);
        insertIter(getRoot(a1), 12);
        insertIter(getRoot(a1), 5);
        deleteIter(getRoot(a1), getRoot(a1).getLeftChild());
        System.out.println(getRoot(a1).getLeftChild().getValue());
        avlNode aa = new avlNode(14);
        insertIter(getRoot(aa), 12);
        insertIter(getRoot(aa), 21);
        insertIter(getRoot(aa), 16);
        deleteIter(getRoot(aa), getRoot(aa).getLeftChild());
        System.out.println(getRoot(aa).getValue());
        System.out.println(getRoot(aa).getLeftChild().getValue());
        System.out.println("--------------------------------");
        avlNode a1 = new avlNode(17);
        insertIter(getRoot(a1), 12);
        insertIter(getRoot(a1), 21);
        insertIter(getRoot(a1), 10);
        insertIter(getRoot(a1), 15);
        insertIter(getRoot(a1), 19);
        insertIter(getRoot(a1), 27);
        insertIter(getRoot(a1), 14);
        insertIter(getRoot(a1), 16);
        // delete 10
        deleteIter(getRoot(a1), getRoot(a1).getLeftChild().getLeftChild());
        // delete 15
        deleteIter(getRoot(a1), getRoot(a1).getLeftChild());
        // delete 17
        deleteIter(getRoot(a1), getRoot(a1));
        //delete 27
        deleteIter(getRoot(a1), getRoot(a1).getRightChild().getRightChild());
        // delete 19
        deleteIter(getRoot(a1), getRoot(a1));
        // delete 12
        deleteIter(getRoot(a1), getRoot(a1).getLeftChild());
        System.out.println(getRoot(a1).getValue());
        System.out.println(getRoot(a1).getLeftChild().getValue());*/

    }

}
