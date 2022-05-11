package compression.huffman;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileControl implements Serializable{

     public static void writeBinary(String binary, String destinationFolder, String fileName){

         System.out.println("FileControl.writeBinary running");
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
        Path path = Paths.get(destinationFolder+ "/" + fileName);
        try {
            Files.write(path, bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readBinary(String filePath){

         System.out.println("FileControl.readBinary running");
        Path path = Paths.get(filePath); // <- TODO - check this works
        StringBuilder binary = null;

        try {
            byte [] fileBytes = Files.readAllBytes(path);
            int finalBits = fileBytes[fileBytes.length-1];
            String[] binaryList = new String[fileBytes.length-1];

            for (int i = 0; i<fileBytes.length-1; i++){
                int formattedByte = fileBytes[i] & 0xff;
                String checkByte = Integer.toBinaryString(formattedByte);
                while (checkByte.length()<8) {
                    checkByte = "0"+checkByte;
                }
                binaryList[i] = checkByte;
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

    public static void writeTree(Node tree, String destinationFolder){

        System.out.println("FileControl.writeTree running");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(destinationFolder + "/tree.ser"))) {
            out.writeObject(tree);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Node readTree(String filePath) {

        System.out.println("FileControl.readTree running");
        System.out.println("filePath: " + filePath);
        Node tree = null;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filePath))) {
            tree = (Node) in.readObject();
            assert tree == in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("readTree exception catch");
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

        System.out.println("FileControl.readText running");
        boolean done = false;

        File file = new File(filePath);

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        StringBuilder output = new StringBuilder();

        while (!done) {
            String line = bufferedReader.readLine();
            if (line == null) {
                done = true;
            } else {
                output.append(line);
            }

        }

        return output.toString();
    }

    public static void writeText(String text, String filePath, String fileName) throws IOException {

        System.out.println("FileControl.writeText running");
        File newFile = new File(filePath + "/" + fileName); // TODO - test if this concatenation works

        FileWriter fileWriter = new FileWriter(filePath + "/" + fileName);

        fileWriter.write(text);
        fileWriter.close();
    }
}
