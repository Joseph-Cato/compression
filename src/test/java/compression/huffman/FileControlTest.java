package compression.huffman;

import org.junit.Assert;
import org.junit.Test;
import compression.huffman.FileControl;

import java.io.File;
import java.io.IOException;

public class FileControlTest {

    @Test
    public void testBinaryOne(){

        FileControl.writeBinary("0101000000001", "../", "test.bin");

        String text = FileControl.readBinary("..//test.bin");

        Assert.assertEquals("0101000000001", text);
    }

    @Test
    public void testTreeOne(){

        Node tree = Tree.createHuffmanTree("aaabbc");

        FileControl.writeTree(tree, "../");

        Node readTree = FileControl.readTree("../tree.ser");

        Assert.assertEquals(tree.toString(), readTree.toString());
    }

    @Test
    public void writeTextTestOne(){
        try {
            FileControl.writeText("Example Text", "../", "test2.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readTextTestOne() {
        try {
            String text = FileControl.readText("../test2.txt");

            Assert.assertEquals("Example Text", text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
