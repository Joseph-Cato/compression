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
    public void createHuffmanTreeTestTwo() {

        //Test 2
        Node base11 = new Node('S', 1);
        Node base12 = new Node('R', 1);
        Node base21 = new Node('L', 1);
        Node base22 = new Node('N', 1);
        Node base23 = new Node('K', 1);
        Node base24 = new Node('F', 1);
        Node base25 = new Node('B', 1);
        Node base26 = new Node('H', 1);
        Node base27 = new Node('U', 1);
        Node int28 = new Node(base11, base12);
        Node int31 = new Node(base21, base22);
        Node int32 = new Node(base23, base24);
        Node base33 = new Node('A', 2);
        Node int34 = new Node(base25, base26);
        Node base35 = new Node('I', 2);
        Node base36 = new Node('W', 2);
        Node base37 = new Node('D', 2);
        Node base38 = new Node('G', 2);
        Node base39 = new Node('E', 3);
        Node int310 = new Node(base27,int28);
        Node base311 = new Node('O', 3);
        Node base312 = new Node('T', 3);
        Node int41 = new Node(int31, int32);
        Node int42 = new Node(base33, int34);
        Node int43 = new Node(base35, base36);
        Node int44 = new Node(base37, base38);
        Node int45 = new Node(base39, int310);
        Node int46 = new Node(base311, base312);
        Node base51 = new Node(' ', 7);
        Node int52 = new Node(int41, int42);
        Node int53 = new Node(int43, int44);
        Node int54 = new Node(int45, int46);
        Node int61 = new Node(base51, int52);
        Node int62 = new Node(int53, int54);
        Node int71 = new Node(int61, int62);

        System.out.println(int71.toString());

        Assert.assertEquals(int71.toString(), Tree.createHuffmanTree("THE BIG DOG WENT OUTSIDE FOR A WALK").toString());

    }

    @Test
    public void getCodesTestOne() {

        //Test 1
        HashMap<Character, String> expectedHashMap = new HashMap<>();
        expectedHashMap.put('S', "00110");
        expectedHashMap.put('R', "00001");
        expectedHashMap.put('L', "00011");
        expectedHashMap.put('N', "00000");
        expectedHashMap.put('K', "00010");
        expectedHashMap.put('F', "101000");
        expectedHashMap.put('B', "10101");
        expectedHashMap.put('H', "101001");
        expectedHashMap.put('U', "00111");
        expectedHashMap.put('A', "1011");
        expectedHashMap.put('I', "1100");
        expectedHashMap.put('T', "1101");
        expectedHashMap.put('D', "1110");
        expectedHashMap.put('G', "1111");
        expectedHashMap.put('W', "0010");
        expectedHashMap.put('E', "1000");
        expectedHashMap.put('O', "1001");
        expectedHashMap.put(' ', "01");

        System.out.println(Tree.createHuffmanTree("HE BIG DOG WENT OUTSIDE FOR A WALK").toString());

        HashMap<Character, String> actualHashMap = Tree.getCodes(Tree.createHuffmanTree("HE BIG DOG WENT OUTSIDE FOR A WALK"));

        Assert.assertEquals(expectedHashMap, actualHashMap);
    }

}
