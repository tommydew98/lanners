package javashark;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.application.Application.launch;

/**
 * Created by Yusuke on 2017-04-11.
 */
public class PacketSizeController implements Initializable{



    @FXML
    private CategoryAxis x;

    @FXML
    private LineChart<String, Integer> packetChart;

    @FXML
    private NumberAxis y;

    public void homeButton(ActionEvent event){
//        PcapParse me = new PcapParse(readFile.getInstance().getFileName());
//        me.scan();
        System.out.println(readFile.getInstance().getFileName());

//        XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
//        series1.getData().add(new XYChart.Data<String, Number>("packet", 10));
//        series1.getData().add(new XYChart.Data<String, Number>("packet", 20));
////        for(int v=0; v<25;v++){
////            series.getData().add(new XYChart.Data<String, Number>("packet"+v, me.getPackList().get(v)));
////        }
//        packetSizeChart.getData().add(series1);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test");
        XYChart.Series series = new XYChart.Series();
        PcapParse me = new PcapParse(readFile.getInstance().getFileName());
        me.scan();

        for(int v=0; v<25;v++){
            series.getData().add(new XYChart.Data("packet"+v, me.getPackList().get(v)));
        }

//        series.getData().add(new XYChart.Data("1",23));
//        series.getData().add(new XYChart.Data("2",26));
//        series.getData().add(new XYChart.Data("3",28));

        packetChart.getData().addAll(series);
    }
}
