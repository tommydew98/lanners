package javashark;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Group;

import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import javafx.fxml.FXML;

import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller{
    @FXML
    private TextField fileText;
    @FXML
    LineChart<String, Number> lineChart;
    LineChart<String, Number> packetSizeChart;
    private String fileName;
    private ArrayList<Integer> packSize = new ArrayList<>();
    private ArrayList<Long> retransmissions = new ArrayList<>();


    public void browseButton(ActionEvent event){


        fileText.setText(readFile.getInstance().read());
        PcapParse.getInstance().scan(readFile.getInstance().getFileName());
    }


    //These buttons move around the scenes and should display chart with data
    public void packetSizeButton(ActionEvent event) throws IOException{
        System.out.println("PACKET SIZE");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("packetsize.fxml"));
        /*
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Packet Size");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }

    }



    public void retransmissionButton(ActionEvent event) throws IOException{
        System.out.println("RETRANSMISSIONS");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("retransmissions.fxml"));
        /*
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Retransmissions");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void dnsButton(ActionEvent event) throws IOException{
        System.out.println("DNS");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("dns.fxml"));
        /*
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("DNS");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    //Close Program Button
    public void closeProgramButton(ActionEvent event) throws IOException{
        System.exit(0);
    }

    public void deleteButton(ActionEvent event) throws IOException{
        fileText.clear();
    }

    //Help button
    public void helpButton(ActionEvent event) throws  IOException{
        Stage stage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, 400, 200);
        stage.setScene(scene);
        stage.setTitle("Help");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);

        Label helpText = new Label();

        helpText.setText("BUTTON GUIDE\n" +
                "Browse - Choose a PCAP file to be scanned\n" +
                "Scan - Scans the PCAP file\n" +
                "PacketSize - Shows the packet size for each packet\n" +
                "Retransmissions - Shows the number of retransmissions\n" +
                "DNS - Shows DNS\n");

        final HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().add(helpText);
        scene.setRoot(hb);
        stage.show();
    }


    //BELOW IS JUST FOR FUN :)

    public void btn(ActionEvent event){
        PcapParse.getInstance().scan(readFile.getInstance().getFileName());
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

        for(int v=0; v<50;v++){
            series.getData().add(new XYChart.Data<String, Number>("packet"+v, PcapParse.getInstance().getPackList().get(v)));
        }
        lineChart.getData().add(series);

    }




    public void mouseEntered(MouseEvent mouse){
        lineChart.setStyle("-fx-opacity:1;");
    }

    public void mouseExit(MouseEvent mouse){
        lineChart.setStyle("-fx-opacity:0;");
    }


    public void sliderButton(){
        Stage stage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Slider");
        stage.initModality(Modality.APPLICATION_MODAL);

        final Slider slider = new Slider();
        slider.setMin(0);
        slider.setMax(50);

        final ProgressBar pb = new ProgressBar(0);
        final ProgressIndicator pi = new ProgressIndicator(0);

        slider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                pb.setProgress(new_val.doubleValue()/50);
                pi.setProgress(new_val.doubleValue()/50);
            }
        });

        final HBox hb = new HBox();
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(slider, pb, pi);
        scene.setRoot(hb);
        stage.show();
    }


    //Progress Bar
    final Float[] values = new Float[] {-1.0f, 0f, 0.6f, 1.0f};
    final Label[] labels = new Label[values.length];
    final ProgressBar[] pbs = new ProgressBar[values.length];
    final ProgressIndicator[] pins = new ProgressIndicator[values.length];
    final HBox hbs [] = new HBox [values.length];

    public void progressButton(){


        //Progress Bars
        Stage stage = new Stage();
        Group root = new Group();
        Scene scene = new Scene(root, 300, 150);
        stage.setScene(scene);
        stage.setTitle("Progress Controls");


        for (int i = 0; i < values.length; i++) {
            final Label label = labels[i] = new Label();
            label.setText("progress:" + values[i]);

            final ProgressBar pb = pbs[i] = new ProgressBar();
            pb.setProgress(values[i]);

            final ProgressIndicator pin = pins[i] = new ProgressIndicator();
            pin.setProgress(values[i]);
            final HBox hb = hbs[i] = new HBox();
            hb.setSpacing(5);
            hb.setAlignment(Pos.CENTER);
            hb.getChildren().addAll(label, pb, pin);
        }

        final VBox vb = new VBox();
        vb.setSpacing(5);
        vb.getChildren().addAll(hbs);
        scene.setRoot(vb);
        stage.show();
    }

}