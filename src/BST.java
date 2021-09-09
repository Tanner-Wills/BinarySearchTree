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
        if(data == null){
            throw new IllegalArgumentException("Can't remove null data from the Tree!");
        } else {
            BSTNode<T> removeNode = null;
            rRemove(root, data, removeNode);
        }
        return data;
    }

    // Helper method to handle various cases for removing a node from the tree.
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
            // set removeNode == curr.data

            // Case 2: if data is a leaf
            if(curr.getRight() == null && curr.getLeft() == null) {
                removeNode = curr;
                removeNode.setData(curr.getData());
                curr = null;

            // Case 3: if data has one child
            } else if (curr.getLeft() == null){
                if(curr.getRight() != null){
                    removeNode = curr;
                    removeNode.setData(curr.getRight().getData());

                    curr.setData(curr.getRight().getData());
                    curr.setRight(null);
                }
            } else if (curr.getRight() == null){
                if(curr.getLeft() != null){
                    removeNode = curr;
                    removeNode.setData(curr.getLeft().getData());

                    curr.setData(curr.getLeft().getData());
                    curr.setLeft(null);
                }
            }

            // Case 4: if data has two children. Replace node using successor helper method.
            else if (curr.getRight() != null && curr.getLeft() != null){
                removeNode = curr;
                removeNode.setData(curr.getData());

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
            if(curr.getRight() == null){
                curr = null;
            // Case 2: successor has a child node
            } else {
                curr.setData(curr.getRight().getData());
                curr.setRight(null);
            }

        } else {
            curr.setLeft(successor(curr.getLeft()));
        }
        return curr;
    }

    // Helper method for getting value of successor node
    private T successValue(BSTNode<T> curr) {
        while(curr.getLeft() != null){
            curr = curr.getLeft();
        }
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