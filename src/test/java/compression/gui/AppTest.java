package compression.gui;

import org.junit.Assert;
import org.junit.Test;
import compression.gui.App;

import java.io.IOException;

public class AppTest {

    @Test
    public void compressTestOne(){
        try {
            App.compress("/home/joseph/Desktop/hi.txt", "/home/joseph/Desktop");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void decompressionTestOne(){
        try {
            App.decompress("/home/joseph/Desktop/CompressedFile.bin", "/home/joseph/Desktop");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
