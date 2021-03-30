package compression.huffman;

import org.apache.commons.collections4.BidiMap;

import java.util.HashMap;

public class Codec {

    static Node TREE;

    public static Node getTREE(){
        return TREE;
    }

    public static String encode(String text) {

        Node huffmanTree = Tree.createHuffmanTree(text);

        TREE = huffmanTree;

        BidiMap<Character, String> codes = Tree.getCodes(huffmanTree);

        StringBuilder output = new StringBuilder();

        for (int i = 0; i<text.length(); i++) {
            output.append(codes.get(text.charAt(i)));
        }

        return output.toString();
    }

    public static String decode(Node tree, String text) {



        // Get BidiMap of codes and characters
        BidiMap<Character, String> codes = Tree.getCodes(tree);

        // Create StringBuilder objects for the final output and the current code being checked
        StringBuilder output = new StringBuilder();
        StringBuilder code = new StringBuilder();

        // Iterate through encoded text and append char's to output
        for (int i = 0; i < text.length(); i++) {

            // Add next value of encoded text to current code
            code.append(text.charAt(i));

            try {
                // Get key char from BidiMap according to code value
                // If key is not found the BidiMap methods will assign this char to null, throwing an Exception
                char c = codes.getKey(code.toString());

                // code will be reset and char will be appended to output
                code = new StringBuilder();
                output.append(c);

            } catch (NullPointerException e) {
                // If key is not found loop will continue
            }
        }

        return output.toString();
    }

}
