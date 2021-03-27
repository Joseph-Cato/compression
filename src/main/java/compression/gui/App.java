package compression.gui;

import compression.huffman.Codec;
import compression.huffman.FileControl;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class App{
    private boolean compress;
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
                        compress(selectedFile, destinationFolder);
                    } else {
                        decompress(selectedFile, destinationFolder);
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

    public void compress(String filePath, String destinationFolder) throws IOException {

        String text = FileControl.readText(filePath);

        text = Codec.encode(text);

        FileControl.writeBinary(text, destinationFolder, "CompressedFile");

        //TODO - write tree to file, not sure if this is the appropriate method or not
    }

    public void decompress(String filePath, String destinationFolder) throws IOException {

        String text = FileControl.readBinary(filePath);
/*
        //TODO - get tree from file
        text = Codec.decode(tree, text);
*/
        FileControl.writeText(text, destinationFolder, "DecompressedFile");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("App");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
