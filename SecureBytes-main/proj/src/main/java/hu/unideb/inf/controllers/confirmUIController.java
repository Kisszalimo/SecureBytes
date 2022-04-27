package hu.unideb.inf.controllers;

import hu.unideb.inf.Main;
import hu.unideb.inf.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class confirmUIController {
    private Stage stage;

    @FXML
    void folytatasGombLenyomva(ActionEvent event) throws IOException{
        editAccUIController.torles(1);
        Main.setSikerUzenet(4);
        Stage stageError = new Stage();
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/successUI.fxml"));
        Scene scene = new Scene(loader.load());
        stageError.setTitle("SIKER");
        stageError.setScene(scene);
        stageError.show();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void megseGombLenyomva(ActionEvent event) throws  IOException{
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
