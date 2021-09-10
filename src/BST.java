import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * implementation of a BST.
 */
public class BST<T extends Comparable<? super T>> {

    private BSTNode<T> root;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the data to the tree.
     *
     * Should be O(log n) for best and average cases and O(n) for worst case.
     */
    public void add(T data) {
        if(data == null){
            throw new IllegalArgumentException("Can't add null data to the Tree!");
        } else {
            root = rAdd(root, data);
        }
    }

    private BSTNode<T> rAdd(BSTNode<T> curr, T data){
        if(curr == null){
            size ++;
            curr = new BSTNode<T>(data);

        } else if (curr.getData().compareTo(data) > 0){
            curr.setLeft(rAdd(curr.getLeft(), data));

        } else if (curr.getData().compareTo(data) < 0){
            curr.setRight(rAdd(curr.getRight(), data));
        }
        return curr;
    }
    /**
     * Removes and returns the data from the tree matching the given parameter.
     */
    // Wrapper Method
    public T remove(T data) {
        if(data == null){
            throw new IllegalArgumentException("Can't remove null data from the Tree!");
        } else {
            BSTNode<T> removeNode = null;
            rRemove(root, data, removeNode);
        }
        return data;
    }

    // Main Method
    private BSTNode<T> rRemove(BSTNode<T> curr, T data, BSTNode<T> removeNode){
        /**
         * traverse down the tree looking for the specified data.
         * Case 1: data not found -> do nothing. Print "Data not in Tree!"
         * Case 2: data is a leaf node -> simply remove it
         * Case 3: data has one child -> grandparent.setNext(child)
         * Case 4: data has two children -> replace the node using the successor method. Need a successor helper method.
         */

        // Case 1: data not found
        if (curr == null) {
            System.out.println("Data not in Tree!");

            // Base case: curr == data
        } else if(curr.getData().equals(data)){

            // Case 2: if data is a leaf
            if(curr.getRight() == null && curr.getLeft() == null) {
               curr = null;

            // Case 3: if data has one child
            } else if (curr.getLeft() == null){
                if(curr.getRight() != null)
                    curr = curr.getRight();

            } else if (curr.getRight() == null) {
                if (curr.getLeft() != null)
                    curr = curr.getLeft();

                // Case 4: if data has two children. Replace node using successor helper method.
            } else if (curr.getRight() != null && curr.getLeft() != null) {

                curr.setData(successValue(curr.getRight()));

                //save right side tree
                BSTNode<T> rightNode = successor(curr.getRight());

                //save left side tree
                BSTNode<T> leftNode = curr.getLeft();
                curr.setLeft(leftNode);
                curr.setRight(rightNode);
            }
            size --;
            return curr;

          // continue to traverse through tree
        } else if (curr.getData().compareTo(data) > 0){
            curr.setLeft(rRemove(curr.getLeft(), data, removeNode));

        } else if (curr.getData().compareTo(data) < 0){
            curr.setRight(rRemove(curr.getRight(), data, removeNode));
        }
        return curr;
    }

    // Helper method for getting the new tree after removing the successor node.
    private BSTNode<T> successor(BSTNode<T> curr){
        //traverse left until null is reached.
        //base case
        if(curr.getLeft() == null){

            // Case 1: if successor node is a leaf
            if(curr.getRight() == null)
                curr = null;
            // Case 2: successor has a child node
            else
                curr = curr.getRight();

        } else
            curr.setLeft(successor(curr.getLeft()));

        return curr;
    }

    // Helper method for getting value of successor node
    private T successValue(BSTNode<T> curr) {
        while(curr.getLeft() != null)
            curr = curr.getLeft();

        System.out.println("Success value: " + curr.getData());
        return curr.getData();
    }

    /**
     * Returns the root of the tree.
     */
    public BSTNode<T> getRoot() {
        return root;
    }

    /**
     * Returns the size of the tree.
     */
    public int size() {
        return size;
    }

    /**
     * Pre Order Traversal
     */
    public List<T> preorder(BSTNode<T> root) {
        // C,L,R

        List<T> returnVals = new ArrayList<T>();
        if (baseCase(root) == true){
            returnVals.add(root.getData());
        }
        else {
            List<T> leftVals = new ArrayList<T>();
            List<T> rightVals = new ArrayList<T>();
            if(root.getLeft() != null){
                leftVals = preorder(root.getLeft());
            }
            if(root.getRight() != null){
                rightVals = preorder(root.getRight());
            }

            returnVals.add(root.getData());
            returnVals.addAll(leftVals);
            returnVals.addAll(rightVals);
        }
        return returnVals;
    }
    /**
     * Base Case Helper Method
     */
    private boolean baseCase(BSTNode<T> root){
        if(root != null){
            if (root.getLeft() == null && root.getRight() == null)
                return true;
        }
        return false;
    }
}
// ******* Brad BST *******
/*
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class BST<T extends Comparable<? super T>> {


    private BSTNode<T> root;
    private int size;




    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null)
            throw new IllegalArgumentException();
        // Call wrapper method
        root = innerAdd(data, root);
    }

    private BSTNode<T> innerAdd(T data, BSTNode<T> currNode) {
        // Create new node if null
        if (currNode == null) {
            currNode = new BSTNode<T>(data);
            size++;
        }
        // Recurse left if currNode > data
        if (currNode.getData().compareTo(data) > 0)
            currNode.setLeft(innerAdd(data, currNode.getLeft()));
            // Recurse right if currNode < data
        else if (currNode.getData().compareTo(data) < 0)
            currNode.setRight(innerAdd(data, currNode.getRight()));
        return currNode;
    }


    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        // Except Catch #1
        if (data == null) {
            throw new IllegalArgumentException();
        }
        BSTNode<T> dummy = new BSTNode<T>(null);
        root = innerRemove(data, root, dummy);
        return dummy.getData();
    }

    private BSTNode<T> innerRemove(T data, BSTNode<T> currData, BSTNode<T> dummy) {
        // if Not found
        if (currData == null)
            throw new NoSuchElementException();
        // Find data
        if (currData.getData().compareTo(data) < 0) {
            currData.setRight(innerRemove(data, currData.getRight(), dummy));
            return currData;
        } else if (currData.getData().compareTo(data) > 0) {
            currData.setLeft(innerRemove(data, currData.getLeft(), dummy));
            return currData;
        }
        // Data found
        else {
            // Set dummy
            dummy.setData(currData.getData());
            size--;
            // 0 Children Case
            if (currData.getLeft() == null && currData.getRight() == null){
                return null;
            }
            // 1 Child Case
            else if (currData.getLeft() != null && currData.getRight() == null){
                return currData.getLeft();
            }
            else if (currData.getRight() != null && currData.getLeft() == null){
                return currData.getRight();
            }
            // 2 Child Case
            else{
                // 2nd dummy for successor
                BSTNode<T> dummy2 = new BSTNode<>(null);
                currData.setRight(removeSuccessor(currData.getRight(),dummy2));
                currData.setData(dummy2.getData());
                return currData;
            }
        }
    }
    private BSTNode<T> removeSuccessor(BSTNode<T> currData, BSTNode<T> dummy2){
        if (currData.getLeft() == null){
            dummy2.setData(currData.getData());
            return currData.getRight();
        }
        else {
            currData.setLeft(removeSuccessor(currData.getLeft(),dummy2));
            return currData;
        }
    }

    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }
}
*/