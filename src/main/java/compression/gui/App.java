package compression.gui;

import compression.huffman.Codec;
import compression.huffman.FileControl;
import compression.huffman.Node;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class App{
    private static boolean compress;
    public String selectedFile;
    public String destinationFolder;

    private JButton buttonStart;
    private JPanel panelMain;
    private JRadioButton radioButtonCompress;
    private JRadioButton radioButtonDecompress;
    private JButton selectFileButton;
    private JButton setDestinationFolderButton;

    public App() {
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start!");
                try {

                    if (compress) {
                        long timer = System.currentTimeMillis();
                        System.out.println("Compress: \nsleectedFile: " + selectedFile +"\ndestinationFolder: " +destinationFolder);
                        compress(selectedFile, destinationFolder);
                        System.out.println("Compression completed in: " + (timer-System.currentTimeMillis()) + "ms");
                    } else {
                        long timer = System.currentTimeMillis();
                        System.out.println("Decompress: \nsleectedFile: " + selectedFile +"\ndestinationFolder: " +destinationFolder);
                        decompress(selectedFile, destinationFolder);
                        System.out.println("Decompression completed in: " + (timer-System.currentTimeMillis()) + "ms");
                    }

                } catch (Exception exception) {
                    System.out.println(exception.toString());
                }
            }
        });
        radioButtonCompress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compress = true;
                System.out.println("App set to compress");
            }
        });
        radioButtonDecompress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compress = false;
                System.out.println("App set to decompress");
            }
        });
        setDestinationFolderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int r = j.showSaveDialog(null);

                if (r == JFileChooser.APPROVE_OPTION) {
                    destinationFolder = j.getSelectedFile().getAbsolutePath();
                }

                System.out.println("Destination folder set to: " + destinationFolder);
            }
        });
        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

                int r = j.showSaveDialog(null);

                if (r == JFileChooser.APPROVE_OPTION) {
                    selectedFile = j.getSelectedFile().getAbsolutePath();
                }

                System.out.println("File set to: " + selectedFile);

            }
        });

    }

    public static void compress(String filePath, String destinationFolder) throws IOException {

        String text = FileControl.readText(filePath);

        text = Codec.encode(text);

        FileControl.writeBinary(text, destinationFolder, "CompressedFile.bin");

        FileControl.writeTree(Codec.getTREE(), destinationFolder);
    }

    public static void decompress(String filePath, String destinationFolder) throws IOException {

        String text = FileControl.readBinary(filePath);

        StringBuilder treeFilePath = new StringBuilder();

        for (int i = 0; i<filePath.length()-18; i++) {
            treeFilePath.append(filePath.charAt(i));
        }
        treeFilePath.append("tree.ser");

        Node tree = FileControl.readTree(treeFilePath.toString());

        text = Codec.decode(tree, text);

        FileControl.writeText(text, destinationFolder, "DecompressedFile.txt");

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
