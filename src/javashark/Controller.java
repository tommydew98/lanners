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



import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller{
    @FXML
    private TextField fileText;
    @FXML
    LineChart<String, Number> lineChart;
    LineChart<String, Number> packetSizeChart;
    PcapParse myJNet;




    public void btn(ActionEvent event){
        myJNet.scan();
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

        for(int v=0; v<myJNet.getPack();v++){
            series.getData().add(new XYChart.Data<String, Number>("packet"+v, myJNet.getPackList().get(v)));
        }
        lineChart.getData().add(series);

    }





    public void browseButton(ActionEvent event){

        myJNet = new PcapParse(readFile.getInstance().read());

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
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }

    }



    public void retransmissionButton(ActionEvent event) throws IOException{


        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("retransmissions.fxml"));
        /*
         * if "fx:controller" is not set in fxml
         * fxmlLoader.setController(NewWindowController);
         */
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
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