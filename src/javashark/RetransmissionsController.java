package javashark;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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

public class RetransmissionsController implements Initializable, ControllerInterface{

    @FXML
    private PieChart rePie;

    @FXML
    private Label label;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test");
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("Retransmissions", PcapParse.getInstance().getRetransmissions()), new PieChart.Data("Non Retransmitted Packets",PcapParse.getInstance().getPack()- PcapParse.getInstance().getRetransmissions()));
        rePie.setData(pieChartData);
        label.setText("Total Packets: "+PcapParse.getInstance().getPack()+" Retransmissions: "+PcapParse.getInstance().getRetransmissions());


    }

    public void homeButton(ActionEvent event) throws IOException {
        System.out.println("HOME");
        Stage stage = new Stage();
        stage.initStyle(StageStyle.TRANSPARENT); //Removes window decorations

        Button close = new Button("Goodbye Rob...");
        close.setOnAction((actionEvent) -> {
            Timeline timeline = new Timeline();
            KeyFrame key = new KeyFrame(Duration.millis(2000), new KeyValue(stage.getScene().getRoot().opacityProperty(), 0));
            timeline.getKeyFrames().add(key);
            timeline.setOnFinished((ae) -> System.exit(1));
            timeline.play();
        });

        Scene scene = new Scene(close, 1920, 1080);
        scene.setFill(Color.TRANSPARENT); //Makes scene background transparent
        stage.setScene(scene);
        stage.show();
    }

}
