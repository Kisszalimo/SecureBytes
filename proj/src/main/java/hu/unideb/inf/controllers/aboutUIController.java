/*---- about UI ----*/

package hu.unideb.inf.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class aboutUIController {
    private Stage stage;

    @FXML
    void okGombLenyomva(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
