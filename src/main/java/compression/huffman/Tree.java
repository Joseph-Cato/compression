package compression.huffman;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Map;

public class Tree {

    static Node createHuffmanTree(String text) {
        Hashtable<Character, Integer> characterIntegerHashtable = charCounter(text);

        // Create array for nodes
        Node[] nodes = new Node[characterIntegerHashtable.size()];

        // Fill node array with nodes from characterIntegerHashtable
        int counter = 0;
        for (Map.Entry<Character, Integer> entry : characterIntegerHashtable.entrySet()) {
            Character c = entry.getKey();
            Integer f = entry.getValue();
            Node node = new Node(c, f);
            nodes[counter] = node;
            counter += 1;
        }

        // Sort nodes array into ascending order
        Comparator<Node> compareByFrequency = Comparator.comparing(Node::getFREQUENCY);


        // Sorts and merges Nodes while there is more than one
        while (nodes.length > 1) {
            Arrays.sort(nodes, compareByFrequency);
            nodes = mergeNodes(nodes);
        }

        return nodes[0];
    }

    static private Node[] mergeNodes(Node[] nodes) {

        // Returns array of one node if only two nodes are left
        if (nodes.length == 2) return new Node[]{new Node(nodes[1], nodes[0])};

        // Copy array skipping first two nodes (smallest two nodes)
        Node[] newNodes = new Node[nodes.length-1];
        for (int i = 2; i < nodes.length; i++) {
            newNodes[i-1] = nodes[i];
        }

        // Create new node (internal) out of two smallest add to new array and return said array
        Node internalNode = new Node(nodes[1], nodes[0]);
        newNodes[1] = internalNode;

        return newNodes;
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
