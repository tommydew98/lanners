package javashark;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.InterfaceAddress;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by Yusuke on 2017-04-11.
 */
public class DNSController implements ControllerInterface, Initializable{


    @FXML
    private BarChart<String,Integer> barGraph;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int i =0;
        XYChart.Series series = new XYChart.Series();
        for (Map.Entry<String, Integer> entry : PcapParse.getInstance().getHttpMap().entrySet())
        {
            i++;
            System.out.println(entry.getKey() + "/" + entry.getValue());
            String test = entry.getKey();
            series.getData().add(new XYChart.Data(""+test,entry.getValue()));
        }
        barGraph.getData().addAll(series);
    }
}
