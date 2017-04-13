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
public interface ControllerInterface {

    //Back Button
    public void homeButton(ActionEvent event) throws IOException;
}
