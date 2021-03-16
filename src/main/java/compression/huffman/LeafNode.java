package compression.huffman;

public class LeafNode extends Node{
    private final char CHARACTER;

    public LeafNode(char character) {
        CHARACTER = character;
    }

    public char getCHARACTER() {
        return CHARACTER;
    }

}
