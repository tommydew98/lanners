package javashark;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
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
public class PacketSizeController implements Initializable, ControllerInterface {

    @FXML
    private CategoryAxis x;

    @FXML
    private LineChart<String, Integer> packetChart;

    @FXML
    private NumberAxis y;


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

    public void homeButton(ActionEvent event) throws IOException {
        System.out.println("HOME");
        System.out.println(readFile.getInstance().getFileName());
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
