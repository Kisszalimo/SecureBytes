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

import java.io.IOException;
import java.util.List;

public class loginUIController {
    private Stage stage;
    private Scene scene;
    private Parent loader;

    @FXML
    private TextField fnevLogin;

    @FXML
    private PasswordField jelszoLogin;

    @FXML
    void ujFiokGombLenyomva(ActionEvent event) throws IOException {
        loader = FXMLLoader.load(getClass().getResource("/fxml/createAccUI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void bejelentkezesGombLenyomva(ActionEvent event) throws Exception {
        JpaFelhasznaloDAO jfd = new JpaFelhasznaloDAO();
        List<Felhasznalo> felhasznalok = jfd.getFelhasznalok();

        if(fnevLogin.getText().equals("")) {
            Main.setErrorUzenet(2);
            hibaUzenet(event);
        }
        else if(jelszoLogin.getText().equals("")){
            Main.setErrorUzenet(3);
            hibaUzenet(event);
        }
        else if(jfd.szerepelE(felhasznalok, fnevLogin.getText()))
        {
            if (jfd.jelszoMatch(felhasznalok, fnevLogin.getText(), jelszoLogin.getText()))
            {
                Felhasznalo fh = new Felhasznalo();
                fh.setFelhasznalonev(fnevLogin.getText());
                fh.setJelszo(jelszoLogin.getText());
                Main.setBejelentkezett(fh);
                loader = FXMLLoader.load(getClass().getResource("/fxml/mainUI.fxml"));
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(loader);
                stage.setScene(scene);
                stage.show();
                Main.setSikerUzenet(2);
                sikerUzenet(event);
                Main.setBejelentkezve(true);
            }
            else
            {
                Main.setErrorUzenet(1);
                hibaUzenet(event);
            }
        }
        else
        {
            Main.setErrorUzenet(0);
            hibaUzenet(event);
        }
        jfd.close();
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
