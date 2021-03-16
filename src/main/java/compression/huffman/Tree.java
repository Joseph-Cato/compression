package compression.huffman;

import java.util.Hashtable;

public class Tree {

    static Tree createHuffmanTree(String text) {
        Hashtable<Character, Integer> characterIntegerHashtable = charCounter(text);

        Node[] nodes = new Node[characterIntegerHashtable.size()];

        int counter = 0;

        characterIntegerHashtable.forEach( (c, f) -> {
            Node node = new Node(c, f);
            nodes[counter] = node;
            counter += 1;
        });

    }

    static Hashtable<Character, Integer> charCounter(String text) {

        //Returns an empty hashtable if text string is empty/null
        if (text == null || text.equals("")) return new Hashtable<>();

        //Character[] characters = new Character[text.length()];
        Hashtable<Character, Integer> charHashtable = new Hashtable<>();

        //Creates array of chars
        char[] chars = text.toCharArray();

        //Runs through all chars. If key is present integer value += 1, otherwise it is added with integer value 1
        for (char c : chars) {
            if(charHashtable.containsKey(c)) {
                charHashtable.put(c, charHashtable.get(c) + 1);
            } else {
                charHashtable.put(c, 1);
            }
        }

        return charHashtable;

    }
}
