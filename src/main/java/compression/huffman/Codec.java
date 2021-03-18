package compression.huffman;

import org.apache.commons.collections4.BidiMap;

import java.util.HashMap;

public class Codec {


    static String encode(String text) {

        HashMap<Character, String> codes = Tree.getCodes(Tree.createHuffmanTree(text));

        StringBuilder output = new StringBuilder();

        for (int i = 0; i<text.length(); i++) {
            output.append(codes.get(text.charAt(i)));
        }

        return output.toString();
    }

    static String decode(Node tree, String text) {

        BidiMap<Character, String> codes = Tree.getCodes(tree);

        StringBuilder output = new StringBuilder();

        return "";

    }

    private static String checkCode(String code, HashMap<Character, String> codes) {

        return "";
    }

}
