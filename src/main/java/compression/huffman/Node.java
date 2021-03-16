package compression.huffman;

public class Node {
    private char character;
    private final int FREQUENCY;
    private final Node LEFT, RIGHT;

    //Leaf Node
    public Node(char character, int FREQUENCY) {
        LEFT = null;
        RIGHT = null;
        this.character = character;
        this.FREQUENCY = FREQUENCY;
    }

    //Internal Node
    public Node(Node LEFT, Node RIGHT) {
        FREQUENCY = LEFT.FREQUENCY + RIGHT.FREQUENCY;
        this.LEFT = LEFT;
        this.RIGHT = RIGHT;
    }

    public int getFREQUENCY() {
        return FREQUENCY;
    }
}
