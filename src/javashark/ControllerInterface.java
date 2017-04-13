package javashark;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

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

public interface ControllerInterface {

    //Back Button
    public void homeButton(ActionEvent event) throws IOException;
}
