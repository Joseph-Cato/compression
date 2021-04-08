package compression.huffman;

import org.junit.Assert;
import org.junit.Test;
import compression.huffman.FileControl;

import java.io.File;
import java.io.IOException;

public class FileControlTest {

    @Test
    public void testBinaryOne(){

        FileControl.writeBinary("0101000000001", "/home/joseph/Desktop", "joeIsGay.bin");

        String text = FileControl.readBinary("/home/joseph/Desktop/joeIsGay.bin");

        Assert.assertEquals("0101000000001", text);
    }

    @Test
    public void testTreeOne(){

        Node tree = Tree.createHuffmanTree("aaabbc");

        FileControl.writeTree(tree, "/home/joseph/Desktop");

        Node readTree = FileControl.readTree("/home/joseph/Desktop/tree.ser");

        Assert.assertEquals(tree.toString(), readTree.toString());
    }

    @Test
    public void writeTextTestOne(){
        try {
            FileControl.writeText("Hi my name is Bobington :)", "/home/joseph/Desktop", "hi.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readTextTestOne() {
        try {
            String text = FileControl.readText("/home/joseph/Desktop/hi.txt");

            Assert.assertEquals("Hi my name is Bobington :)", text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
