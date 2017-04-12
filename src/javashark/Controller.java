package javashark;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.stage.*;
import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.jnetpcap.packet.format.FormatUtils.ip;

public class Controller{
    @FXML
    private TextField fileText;
    @FXML
    LineChart<String, Number> lineChart;
    LineChart<String, Number> packetSizeChart;
    PcapParse myJNet;

    private String fileName;



    public void btn(ActionEvent event){
        myJNet.scan();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

        for(int v=0; v<myJNet.getPack();v++){
            series.getData().add(new XYChart.Data<String, Number>("packet"+v, myJNet.getPackList().get(v)));
        }
        lineChart.getData().add(series);

    }





    public void browseButton(ActionEvent event){
        readFile file = new readFile();
        myJNet = new PcapParse(file.read());

    }




    //These buttons move around the scenes and should display chart with data
    public void packetSizeButton(ActionEvent event) throws IOException{
        System.out.println("PACKET SIZE");
        Parent packetSizeParent = FXMLLoader.load(getClass().getResource("packetsize.fxml"));
        Scene packetSizeScene = new Scene(packetSizeParent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(packetSizeScene);
        app_stage.show();


    }



    public void retransmissionButton(ActionEvent event) throws IOException{
        System.out.println("Test");
        Parent randomParent = FXMLLoader.load(getClass().getResource("retransmissions.fxml"));
        Scene randomScene = new Scene(randomParent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(randomScene);
        app_stage.show();
    }

    public void dnsButton(ActionEvent event) throws IOException{
        System.out.println("Test2");
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("dns.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(home_page_scene);
        app_stage.show();
    }

    public void lastButton(ActionEvent event) throws IOException{
        System.out.println("Test3");
        Parent randomParent3 = FXMLLoader.load(getClass().getResource("random3.fxml"));
        Scene randomScene3 = new Scene(randomParent3);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(randomScene3);
        app_stage.show();
    }






}