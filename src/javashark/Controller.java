package javashark;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import org.jnetpcap.Pcap;
import org.jnetpcap.packet.JPacket;
import org.jnetpcap.packet.JPacketHandler;
import org.jnetpcap.protocol.network.Ip4;
import org.jnetpcap.protocol.tcpip.Tcp;

import javax.swing.*;
import java.awt.*;
import java.awt.Button;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.jnetpcap.packet.format.FormatUtils.ip;

public class Controller{
    @FXML
    LineChart<String, Number> lineChart;
    private Desktop desktop = Desktop.getDesktop();
    private String fileName;
    private ArrayList<Integer> packSize = new ArrayList<>();
    private ArrayList<Long> retransmissions = new ArrayList<>();

    public void btn(ActionEvent event){
//        int i = 0;
        scan();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
//        series.getData().add(new XYChart.Data<String, Number>("Test", 100));
//        while(i < 10){
//            series.getData().add(new XYChart.Data<String, Number>(("Packet" + i), 100 + i));
//            i++;
//        }
        for(int v=0; v<packSize.size();v++){
            series.getData().add(new XYChart.Data<String, Number>("packet"+v, packSize.get(v)));
        }
        lineChart.getData().add(series);

    }


    public String browseButton(ActionEvent event){
        FileChooser fileChooser = new FileChooser();

        //Set extension filter
//        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PCAP files (*.pcap)");
//        fileChooser.getExtensionFilters().add(extFilter);
        configureFileChooser(fileChooser);

        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            System.out.print(file.getPath());
            fileName = file.getPath();
            new Projectv1();
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

    public void newWindowButton(ActionEvent event){
        Label secondLabel = new Label("Hello");

        StackPane secondaryLayout = new StackPane();
        secondaryLayout.getChildren().add(secondLabel);

        Scene secondScene = new Scene(secondaryLayout, 200, 100);
        Stage secondStage = new Stage();
        secondStage.setTitle("LineChart");
        secondStage.setScene(secondScene);

        secondStage.show();

    }



    public void scan(){

        final StringBuilder mrString = new StringBuilder();
        final Pcap pcap = Pcap.openOffline(fileName, mrString);

        if (pcap == null) {
            System.err.println(mrString);
            return;
        }




        packSize = new ArrayList<>();
        retransmissions = new ArrayList<>();
        pcap.loop(Pcap.LOOP_INFINITE, new JPacketHandler<StringBuilder>() {
            final Ip4 ipv4 = new Ip4();
            final Tcp tcp = new Tcp();
            Long ack = Long.valueOf(1);
            Long seq = Long.valueOf(1);
            @Override
            public void nextPacket(JPacket jPacket, StringBuilder stringBuilder) {
                if (jPacket.hasHeader(Ip4.ID)){

                    jPacket.getHeader(ipv4);
                    packSize.add(jPacket.getTotalSize());
                    System.out.println(ip(ipv4.source()));
                    System.out.println((ip(ipv4.destination())));
                    System.out.println(jPacket.getTotalSize()+" bytes");

                }
                if (jPacket.hasHeader(tcp)){

                    jPacket.getHeader(tcp);

                    if(ack.equals(tcp.ack()) && seq.equals(tcp.seq())){
                        //add frame# to list
                        retransmissions.add(jPacket.getFrameNumber());
                    } else {
                        ack = tcp.ack();
                        seq = tcp.seq();
                    }

                    System.out.println("ACK "+tcp.ack());
                    System.out.println("SEQ "+tcp.seq());


                }
            }
        }, mrString);
        System.out.println("Total # of packets: "+packSize.size());
        System.out.println(packSize);
        System.out.println(retransmissions);
        int total=0;
        for(int i : packSize){
            total= total+i;
        }
        System.out.println("Average Packet Size: "+total/packSize.size()+" bytes");
        System.out.println("Number of Retransmissions: "+retransmissions.size());

//Pcap.LOOP_INFINITE
        pcap.close();
    }


}