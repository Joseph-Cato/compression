package compression.huffman;

import org.junit.Assert;
import org.junit.Test;
import compression.huffman.Codec;

public class CodecTest extends Tree{

    public CodecTest(Node root) {
        super(root);
    }

    @Test
    public void encodeTest() {

        //Test 1
        String text = "aaabbc";

        Tree tree; //TODO - make tree

        String encodedText = Codec.encode(tree, "text");

        Assert.assertEquals("111010100", encodedText);
    }
}
