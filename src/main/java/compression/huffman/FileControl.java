package compression.huffman;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileControl {

     public static void writeBinary(String binary, String destinationFolder, String fileName){

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
        Path path = Paths.get(destinationFolder+ "/" + fileName); //<- TODO - check this works
        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readBinary(String filePath){

        Path path = Paths.get(filePath); // <- TODO - check this works
        StringBuilder binary = null;

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
            binary = new StringBuilder(binaryList[0]);

            for (int i = 1; i<binaryList.length; i++){
                binary.append(binaryList[i]);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        assert binary != null;
        return binary.toString();
    }

    static void writeTree(Node tree){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("tree.ser"))) {
            out.writeObject(tree);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Node readTree() {
        Node tree = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("tree.ser"))) {
            tree = (Node) in.readObject();
            assert tree == in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return tree;
    }

    /**
     *
     * @param filePath - location of text file to be read
     * @return - returns a string of all text from the selected file
     * @throws IOException
     */
    public static String readText(String filePath) throws IOException {
        boolean done = false;

        File file = new File(filePath);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        StringBuilder output = new StringBuilder();

        while (!done) {
            String line = bufferedReader.readLine();
            if (line == null) done = true;
            output.append(line);
        }

        return output.toString();
    }

    public static void writeText(String text, String filePath, String fileName) throws IOException {
        File newFile = new File(filePath + fileName); // TODO - test if this concatenation works

        FileWriter fileWriter = new FileWriter(filePath + fileName);

        fileWriter.write(text);
        fileWriter.close();
    }
}
