package hu.unideb.inf.controllers;

import hu.unideb.inf.Main;
import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.Felhasznalo;
import hu.unideb.inf.model.JpaFelhasznaloDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;

public class editAccUIController {
    private Stage stage;
    private Scene scene;
    private Parent loader;

    @FXML
    private PasswordField jelszoUj;

    @FXML
    private PasswordField jelszoUjUjra;

    @FXML
    void megseGombLenyomva(ActionEvent event) throws IOException {
        loader = FXMLLoader.load(getClass().getResource("/fxml/mainUI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void alkalmazGombLenyomva(ActionEvent event) throws Exception{
        if(!jelszoUj.getText().equals(jelszoUjUjra.getText()))
        {
            Main.setErrorUzenet(7);
            hibaUzenet(event);
        }
        else if(jelszoUj.getText().equals("")){
            Main.setErrorUzenet(3);
            hibaUzenet(event);
        }
        else if(jelszoUj.getText().equals(Main.getBejelentkezett().getJelszo())){
            Main.setErrorUzenet(8);
            hibaUzenet(event);
        }
        else {
            JpaFelhasznaloDAO jfd = new JpaFelhasznaloDAO();
            Felhasznalo felhasznalo = new Felhasznalo();
            felhasznalo.setFelhasznalonev(Main.getBejelentkezett().getFelhasznalonev());
            felhasznalo.setJelszo(jelszoUj.getText());
            felhasznalo.setId(Main.getBejelentkezett().getId());
            Main.setBejelentkezett(felhasznalo);
            jfd.updateFelhasznalo(Main.getBejelentkezett());
            megseGombLenyomva(event);
            Main.setSikerUzenet(3);
            sikerUzenet(event);
            jfd.close();
        }
    }

    void hibaUzenet(ActionEvent event) throws IOException{
        Stage stageError = new Stage();
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/errorUI.fxml"));
        Scene scene = new Scene(loader.load());
        stageError.setTitle("HIBA");
        stageError.setScene(scene);
        stageError.show();
    }

    void sikerUzenet(ActionEvent event) throws Exception
    {
        Stage stageError = new Stage();
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/successUI.fxml"));
        Scene scene = new Scene(loader.load());
        stageError.setTitle("SIKER");
        stageError.setScene(scene);
        stageError.show();
    }
}
