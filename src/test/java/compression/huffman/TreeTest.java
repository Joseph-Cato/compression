package compression.huffman;

import compression.huffman.Tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Enumeration;
import java.util.Hashtable;

public class TreeTest {

    @Test
    public void charCounterTest() {

        //Test 1
        Hashtable<Character, Integer> test1 = new Hashtable<Character, Integer>();
        test1.put('a', 4);
        test1.put('b', 3);
        test1.put('c', 2);
        test1.put('d', 1);

        Assert.assertEquals(test1, Tree.charCounter("aaaabbbccd"));

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
    public void createHuffmanTreeTest() {

    }


}
