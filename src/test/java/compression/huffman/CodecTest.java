package compression.huffman;

import org.junit.Assert;
import org.junit.Test;
import compression.huffman.Codec;

public class CodecTest {

    @Test
    public void encodeTestOne() {

        // Test 1
        Assert.assertEquals("000101011", Codec.encode("aaabbc"));
    }

    @Test
    public void decodeTestOne() {

        // Test 1
        Node tree = Tree.createHuffmanTree("aaabbc");

        Assert.assertEquals("aaabbc", Codec.decode(tree ,"000101011"));
    }

}
