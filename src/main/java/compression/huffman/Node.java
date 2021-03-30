package compression.huffman;

import java.io.Serializable;

public class Node implements Serializable {
    private final char CHARACTER;
    private final int FREQUENCY;
    private final Node LEFT, RIGHT;

    //Leaf Node
    public Node(char CHARACTER, int FREQUENCY) {
        LEFT = null;
        RIGHT = null;
        this.CHARACTER = CHARACTER;
        this.FREQUENCY = FREQUENCY;
    }

    //Internal Node
    public Node(Node LEFT, Node RIGHT) throws IllegalArgumentException {
        if (LEFT == null || RIGHT == null) throw new IllegalArgumentException();
        CHARACTER = '\0';
        FREQUENCY = LEFT.FREQUENCY + RIGHT.FREQUENCY;
        this.LEFT = LEFT;
        this.RIGHT = RIGHT;
    }

    public int getFREQUENCY() {
        return FREQUENCY;
    }

    public char getCHARACTER() {
        return CHARACTER;
    }

    public Node getLEFT() {
        return LEFT;
    }

    public Node getRIGHT() {
        return RIGHT;
    }

    @Override
    public String toString() {
        return Tree.printTree(this, 0).toString();
    }

}
