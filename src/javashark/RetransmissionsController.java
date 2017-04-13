package javashark;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Yusuke on 2017-04-11.
 */
public class RetransmissionsController implements Initializable, ControllerInterface{

    @FXML
    private PieChart rePie;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("test");
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new PieChart.Data("Retransmissions", 20), new PieChart.Data("Total Packets",1000));
        rePie.setData(pieChartData);

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
