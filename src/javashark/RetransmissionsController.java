package javashark;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;

import java.util.ResourceBundle;

/**
 * Created by Yusuke on 2017-04-11.
 */
public class RetransmissionsController implements Initializable {

    @FXML
    private PieChart rePie;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test");


        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("Retransmissions", 20), new PieChart.Data("Total Packets",1000));
        rePie.setData(pieChartData);

    }
}
