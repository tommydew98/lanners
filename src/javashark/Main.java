package javashark;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        primaryStage.setTitle("DTY");
        primaryStage.setScene(new Scene(root, 700, 100));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
