package compression.huffman;

public class InternalNode extends Node {
    private Node left = null;
    private Node right = null;

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}
