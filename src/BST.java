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