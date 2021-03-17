package compression.huffman;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;

public class TreeTest {

    @Test
    public void charCounterTestOne() {

        //Test 1
        Hashtable<Character, Integer> test1 = new Hashtable<Character, Integer>();
        test1.put('a', 4);
        test1.put('b', 3);
        test1.put('c', 2);
        test1.put('d', 1);

        Assert.assertEquals(test1, Tree.charCounter("aaaabbbccd"));

    }

    @Test
    public void charCounterTestTwo() {

        //Test 2
        Hashtable<Character, Integer> test2 = new Hashtable<Character, Integer>();
        test2.put('i', 1);
        test2.put('I', 3);
        test2.put('w', 1);
        test2.put('e', 3);
        test2.put('n', 1);
        test2.put('t', 3);
        test2.put('o', 2);
        test2.put('h', 3);
        test2.put('s', 2);
        test2.put('p', 3);
        test2.put('!', 5);
        test2.put('v', 1);
        test2.put('r', 1);
        test2.put('y', 2);
        test2.put('d', 3);
        test2.put('.', 2);
        test2.put(',', 1);
        test2.put('/', 1);
        test2.put('a', 2);
        test2.put('\n', 2);
        test2.put('-', 2);
        test2.put('=', 2);
        test2.put(' ', 11);
        test2.put('m', 1);

        Assert.assertEquals(test2, Tree.charCounter("I went to the shop!!!!! Im very happy I did.,/. asd\n\n --=="));

    }

    @Test
    public void createHuffmanTreeTestOne() {

        //Test 1
        Node base11 = new Node('b', 2);
        Node base12 = new Node('c', 1);
        Node base21 = new Node('a', 3);
        Node internal22 = new Node(base11, base12);
        Node internal31 = new Node(base21, internal22);

        Assert.assertEquals(internal31.toString(), Tree.createHuffmanTree("aaabbc").toString());
    }

    @Test
    public void getCodesTestOne() {

        //Test 1
        HashMap<Character, String> expectedHashMap = new HashMap<>();
        expectedHashMap.put('S', "110110");
        expectedHashMap.put('R', "110111");
        expectedHashMap.put('L', "01100");
        expectedHashMap.put('N', "01101");
        expectedHashMap.put('K', "01110");
        expectedHashMap.put('F', "01111");
        expectedHashMap.put('B', "01000");
        expectedHashMap.put('H', "01001");
        expectedHashMap.put('U', "11010");
        expectedHashMap.put('A', "0101");
        expectedHashMap.put('I', "1000");
        expectedHashMap.put('T', "1001");
        expectedHashMap.put('D', "1010");
        expectedHashMap.put('G', "1011");
        expectedHashMap.put('W', "1100");
        expectedHashMap.put('E', "1110");
        expectedHashMap.put('O', "1111");
        expectedHashMap.put(' ', "00");

        System.out.println(Tree.createHuffmanTree("HE BIG DOG WENT OUTSIDE FOR A WALK").toString());

        HashMap<Character, String> actualHashMap = Tree.getCodes(Tree.createHuffmanTree("HE BIG DOG WENT OUTSIDE FOR A WALK"));

        Assert.assertEquals(expectedHashMap, actualHashMap);
    }

}
