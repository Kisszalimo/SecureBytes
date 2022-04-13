package hu.unideb.inf.controllers;

import hu.unideb.inf.Main;
import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.Adatok;
import hu.unideb.inf.model.AdatokDao;
import hu.unideb.inf.model.Felhasznalo;
import hu.unideb.inf.model.JpaAdatokDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class mainUIController {
    private Stage stage;
    private Scene scene;
    private Parent loader;

    @FXML
    private TextField fnevUj;

    @FXML
    private PasswordField jelszoUj;

    @FXML
    private TextField emailUj;

    @FXML
    private TextField weboldalUj;

    @FXML
    private TextArea leirasUj;

    @FXML
    private Label bejelentkezettNeve;

    @FXML
    void mentesGombLenyomva(ActionEvent event) throws Exception {
        if(jelszoUj.getText().equals(""))
        {
            Main.setErrorUzenet(3);
            hibaUzenet(event);
        }
        else if(emailUj.getText().equals(""))
        {
            Main.setErrorUzenet(5);
            hibaUzenet(event);
        }
        else if(weboldalUj.getText().equals(""))
        {
            Main.setErrorUzenet(6);
            hibaUzenet(event);
        }
        else
        {
            Adatok adatok = new Adatok();
            adatok.setFelhasznalonev(fnevUj.getText());
            adatok.setJelszo(jelszoUj.getText());
            adatok.setEmail(emailUj.getText());
            adatok.setWeboldal(weboldalUj.getText());
            adatok.setLeiras(leirasUj.getText());
            adatok.setTulajdonos(Main.getBejelentkezett().getFelhasznalonev());
            AdatokDao jad = new JpaAdatokDao();
            jad.saveAdatok(adatok);
            jad.close();
            Main.setSikerUzenet(0);
            sikerUzenet(event);
            fnevUj.setText("");
            jelszoUj.setText("");
            emailUj.setText("");
            weboldalUj.setText("");
            leirasUj.setText("");
        }
    }

    @FXML
    void nevjegyGombLenyomva(ActionEvent event) throws Exception{
        Stage stageError = new Stage();
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/AboutUI.fxml"));
        Scene scene = new Scene(loader.load());
        stageError.setTitle("Névjegy");
        stageError.setScene(scene);
        stageError.show();
    }

    @FXML
    void ujFiokGombLenyomva(ActionEvent event) throws Exception{
        Main.setBejelentkezett(new Felhasznalo());
        loader = FXMLLoader.load(getClass().getResource("/fxml/createAccUI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void modositasGombLenyomva(ActionEvent event) throws Exception{
        loader = FXMLLoader.load(getClass().getResource("/fxml/editAccUI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader);
        stage.setScene(scene);
        stage.show();
    }

    void hibaUzenet(ActionEvent event) throws Exception
    {
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

    @FXML
    public void initialize(){
        bejelentkezettNeve.setText(Main.getBejelentkezett().getFelhasznalonev());
    }
}
