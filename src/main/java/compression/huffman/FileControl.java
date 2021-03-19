package compression.huffman;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileControl {

    public void writeBinary(String binary){
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

    public String readBinary(){
        Path path = Paths.get("compressed.bin");
        String binary = null;
        try {
            byte [] fileBytes = Files.readAllBytes(path);
            int finalBits = fileBytes[fileBytes.length-1];
            String[] binaryList = new String[fileBytes.length-1];
            for (int i = 0; i<fileBytes.length-1; i++){
                int formattedByte = fileBytes[i] & 0xff;
                binaryList[i] = Integer.toBinaryString(formattedByte);
            }
            String lastByte = binaryList[binaryList.length-1];
            for (int i = 0; i<finalBits; i++){
                lastByte = lastByte.substring(0,lastByte.length()-1);
            }
            binaryList[binaryList.length-1] = lastByte;
            binary = binaryList[0];
            for (int i = 1; i<binaryList.length; i++){
                binary += binaryList[i];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return binary;
    }

    public void writeTree(Node tree){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("tree.ser"))) {
            out.writeObject(tree);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Node readTree(){
        Node tree = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("tree.ser"))) {
            tree = (Node) in.readObject();
            assert tree == in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tree;
    }
}
