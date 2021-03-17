package compression.huffman;

import java.util.HashMap;

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
    public Node(Node LEFT, Node RIGHT) throws IllegalArgumentException {
        if (LEFT == null || RIGHT == null) throw new IllegalArgumentException();
        character = '\0';
        FREQUENCY = LEFT.FREQUENCY + RIGHT.FREQUENCY;
        this.LEFT = LEFT;
        this.RIGHT = RIGHT;
    }

    public int getFREQUENCY() {
        return FREQUENCY;
    }

    public char getCharacter() {
        return character;
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
