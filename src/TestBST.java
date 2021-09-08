public class TestBST {

    public static void main(String[] args) {
        //Declare Nodes
        BSTNode<Integer> rooty = new BSTNode<>(50);

        BSTNode<Integer> left1 = new BSTNode<>(15);
        BSTNode<Integer> left2 = new BSTNode<>(5);
        BSTNode<Integer> left3 = new BSTNode<>(10);
        BSTNode<Integer> left4 = new BSTNode<>(25);
        BSTNode<Integer> left5 = new BSTNode<>(20);

        BSTNode<Integer> right1 = new BSTNode<>(75);
        BSTNode<Integer> right2 = new BSTNode<>(100);
        BSTNode<Integer> right3 = new BSTNode<>(85);


        //Link Nodes
        rooty.setLeft(left1);
        left1.setLeft(left2);
        left2.setRight(left3);

        rooty.setRight(right1);
        right1.setRight(right2);

        //Test Add / Remove methods
        BST<Integer> misty = new BST<>();
        misty.add(50);
        misty.add(15);
        misty.add(5);
        misty.add(10);
        misty.add(75);
        misty.add(100);

        System.out.println(misty.preorder(misty.getRoot()));

    }
}
