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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class createAccUIController {
    private Stage stage;
    private Scene scene;
    private Parent loader;

    @FXML
    private TextField fnevCreate;
    @FXML
    private PasswordField jelszoCreate;

    @FXML
    void letrehozasGombLenyomva(ActionEvent event) throws Exception {

        if(fnevCreate.getText().equals("")) {
            Main.setErrorUzenet(2);
            hibaUzenet(event);
        }
        else if(jelszoCreate.getText().equals("")){
            Main.setErrorUzenet(3);
            hibaUzenet(event);
        }
        else {
            JpaFelhasznaloDAO jfd = new JpaFelhasznaloDAO();
            List<Felhasznalo> felhasznalok = jfd.getFelhasznalok();
            if(!jfd.szerepelE(felhasznalok, fnevCreate.getText()))
            {
                Felhasznalo felhasznalo = new Felhasznalo();
                felhasznalo.setFelhasznalonev(fnevCreate.getText());
                felhasznalo.setJelszo(jelszoCreate.getText());
                jfd.saveFelhasznalo(felhasznalo);
                Main.setBejelentkezett(felhasznalo);
                megseGombLenyomva(event);
                Main.setSikerUzenet(1);
                sikerUzenet(event);
            }
            else
            {
                Main.setErrorUzenet(4);
                hibaUzenet(event);
            }
            jfd.close();
        }
    }

    @FXML
    void megseGombLenyomva(ActionEvent event) throws Exception{
        if(Main.getBejelentkezve())
        {
            loader = FXMLLoader.load(getClass().getResource("/fxml/mainUI.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(loader);
            if(Main.getBejelentkezett().getTema() == 1)
            {
                scene.getStylesheets().add(getClass().getResource("/fxml/css/dark_theme.css").toExternalForm());
            }
            stage.setScene(scene);
            stage.show();
        }
        else
        {
            loader = FXMLLoader.load(getClass().getResource("/fxml/loginUI.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(loader);
            if(Main.getBejelentkezett().getTema() == 1)
            {
                scene.getStylesheets().add(getClass().getResource("/fxml/css/dark_theme.css").toExternalForm());
            }
            stage.setScene(scene);
            stage.show();
            Main.setBejelentkezett(new Felhasznalo());
        }
    }

    void hibaUzenet(ActionEvent event) throws Exception
    {
        Stage stageError = new Stage();
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/errorUI.fxml"));
        Scene scene = new Scene(loader.load());
        if(Main.getBejelentkezett().getTema() == 1)
        {
            scene.getStylesheets().add(getClass().getResource("/fxml/css/dark_theme.css").toExternalForm());
        }
        stageError.setTitle("HIBA");
        stageError.setScene(scene);
        stageError.show();
    }

    void sikerUzenet(ActionEvent event) throws Exception
    {
        Stage stageError = new Stage();
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/successUI.fxml"));
        Scene scene = new Scene(loader.load());
        if(Main.getBejelentkezett().getTema() == 1)
        {
            scene.getStylesheets().add(getClass().getResource("/fxml/css/dark_theme.css").toExternalForm());
        }
        stageError.setTitle("SIKER");
        stageError.setScene(scene);
        stageError.show();
    }
}
