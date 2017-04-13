package javashark;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/** 
 * JavaShark Final Project
 * Create a program that can parse through a .pcap file and return information that 
 * a user may find useful.
 * 
 * This program DTY allows the user to open a pcap file from a directory, scan it and
 * visualize information onto graphs. This version includes Packet Sizes, Retransmission rates and DNS names.
 * 
 * Authors: Tommy, Yusuke, Dickson
 * 
 * Version 1.3.7
 */

public class readFile {

    private String fileName;
    private FileChooser fileChooser = new FileChooser();

    private static readFile instance = null;
    protected readFile(){

    }
    public static readFile getInstance(){
        if(instance == null){
            instance = new readFile();
        }
        return instance;
    }

    public String read(){


        configureFileChooser(fileChooser);

        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            System.out.print(file.getPath());
            fileName = file.getPath();


            return(fileName);
        }

        return null;
    }

    private static void configureFileChooser(final FileChooser fileChooser){
        fileChooser.setTitle("Choose PCAP");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All", "*.*"),
                new FileChooser.ExtensionFilter("PCAP", "*.pcap")
        );
    }
    public FileChooser getFileChooser(){
        return fileChooser;
    }

    public String getFileName(){
        return fileName;
    }
}