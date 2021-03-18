package compression.huffman;

import java.util.*;

public class Tree {
    private static HashMap<Character, String> codes = new HashMap<>();

    private static final int DISPLAY_SPACE_COUNT = 10;

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

        // Sorts and merges Nodes while there is more than one
        while (nodes.length > 1) {
            nodes = mergeNodes(nodes);
        }

        return nodes[0];
    }

    static private Node[] mergeNodes(Node[] nodes) {

        // Returns array of one node if only two nodes are left
        if (nodes.length == 2) return new Node[]{new Node(nodes[1], nodes[0])};

        // Sort nodes array into ascending order
        Comparator<Node> compareByFrequency = Comparator.comparing(Node::getFREQUENCY);
        Arrays.sort(nodes, compareByFrequency);

        // Copy array skipping first two nodes (smallest two nodes)
        Node[] newNodes = new Node[nodes.length-1];
        for (int i = 2; i < nodes.length; i++) {
            newNodes[i-1] = nodes[i];
        }

        // Create new node (internal) out of two smallest add to new array and return said array
        Node internalNode = new Node(nodes[1], nodes[0]);
        newNodes[0] = internalNode;

        return newNodes;
    }

    protected static Hashtable<Character, Integer> charCounter(String text) {

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

    static StringBuilder printTree(Node root, int spacing) {
        StringBuilder output = new StringBuilder();

        if (root == null) return output;

        spacing += DISPLAY_SPACE_COUNT;

        output.append(printTree(root.getRIGHT(), spacing));

        output.append("\n");
        for (int i = DISPLAY_SPACE_COUNT; i < spacing; i++) {
            output.append(" ");
        }
        if (root.getCharacter() == '\0') {
            output.append("NULL : ").append(root.getFREQUENCY());
        } else {
            output.append(root.getCharacter()).append(" : ").append(root.getFREQUENCY());
        }

        output.append(printTree(root.getLEFT(), spacing));

        return output;
    }

    static void setCodes(Node root, StringBuilder path) {
        if (root.getLEFT() != null) {
            path.append("0");
            setCodes(root.getLEFT(), path);
            path.deleteCharAt(path.length()-1);
        }
        if (root.getCharacter() != '\0') {
            codes.put(root.getCharacter(), path.toString());
        }
        if (root.getRIGHT() != null) {
            path.append("1");
            setCodes(root.getRIGHT(), path);
            path.deleteCharAt(path.length()-1);
        }
    }

    static HashMap<Character, String> getCodes(Node root) {
        setCodes(root, new StringBuilder());
        HashMap<Character, String> savedCodes = codes;
        codes = new HashMap<Character, String>();
        return savedCodes;
    }

}
