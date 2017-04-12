package javashark;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Yusuke on 2017-04-11.
 */
public class LastController {
    public void homeButton(ActionEvent event) throws IOException {
        System.out.println("HOME");
        Parent homeParent = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene homeScene = new Scene(homeParent);
        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        app_stage.setScene(homeScene);
        app_stage.show();
    }
}
