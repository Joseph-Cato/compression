package compression.huffman;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileControl {

    public static void writeBinary(String binary){
        String[] binaryList = binary.split("(?<=\\G........)");
        int finalBits = 8-binaryList[binaryList.length-1].length();
        for (int i = 0; i<finalBits; i++){
            binaryList[binaryList.length-1] += '0';
        }
        byte[] bytes = new byte[binaryList.length+1];
        for (int i = 0; i<binaryList.length; i++) {
            bytes[i] = (byte) Integer.parseInt(binaryList[i], 2);
        }
        bytes[binaryList.length] = (byte) finalBits;
        Path path = Paths.get("compressed.bin");
        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
