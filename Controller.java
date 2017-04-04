package javashark;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

public class Controller {
    @FXML
    LineChart<String, Number> lineChart;

    public void btn(ActionEvent event){
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.getData().add(new XYChart.Data<String, Number>("Test", 100));
        series.getData().add(new XYChart.Data<String, Number>("Test2", 200));
        series.getData().add(new XYChart.Data<String, Number>("Test3", 300));
        lineChart.getData().add(series);
    }
}
