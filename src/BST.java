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
     *
     * This must be done recursively.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the SUCCESSOR to
     * replace the data. You should use recursion to find and remove the
     * successor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do NOT return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for best and average cases and O(n) for worst case.
     *
     * @param data The data to remove.
     * @return The data that was removed.
     * @throws java.lang.IllegalArgumentException If data is null.
     * @throws java.util.NoSuchElementException   If the data is not in the tree.
     */
    public T remove(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        return data;
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