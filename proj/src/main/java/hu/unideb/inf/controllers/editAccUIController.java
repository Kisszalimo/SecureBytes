package hu.unideb.inf.controllers;

import hu.unideb.inf.Main;
import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.Adatok;
import hu.unideb.inf.model.Felhasznalo;
import hu.unideb.inf.model.JpaAdatokDao;
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
import java.util.List;

public class editAccUIController {
    private Stage stage;
    private Scene scene;
    private Parent loader;
    private static ActionEvent eventFo;

    @FXML
    private PasswordField jelszoUj;

    @FXML
    private PasswordField jelszoUjUjra;

    @FXML
    void megseGombLenyomva(ActionEvent event) throws IOException {
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

    @FXML
    void alkalmazGombLenyomva(ActionEvent event) throws Exception{
        if(!jelszoUj.getText().equals(jelszoUjUjra.getText()))
        {
            Main.setErrorUzenet(7);
            hibaUzenet();
        }
        else if(jelszoUj.getText().equals("")){
            Main.setErrorUzenet(3);
            hibaUzenet();
        }
        else if(jelszoUj.getText().equals(Main.getBejelentkezett().getJelszo())){
            Main.setErrorUzenet(8);
            hibaUzenet();
        }
        else if(jelszoUj.getText().length() < 6)
        {
            Main.setErrorUzenet(9);
            hibaUzenet();
        }
        else {
            JpaFelhasznaloDAO jfd = new JpaFelhasznaloDAO();
            List<Felhasznalo> felhasznalok = jfd.getFelhasznalok();
            for (int i = 0; i < felhasznalok.size(); i++)
            {
                if(felhasznalok.get(i).getFelhasznalonev().equals(Main.getBejelentkezett().getFelhasznalonev()))
                {
                    felhasznalok.get(i).setJelszo(jelszoUj.getText());
                    Main.setBejelentkezett(felhasznalok.get(i));
                    jfd.updateFelhasznalo(felhasznalok.get(i));
                    break;
                }
            }
            megseGombLenyomva(event);
            Main.setSikerUzenet(3);
            sikerUzenet();
            jfd.close();
        }
    }

    @FXML
    void torlesGombLenyomva(ActionEvent event) throws IOException {
        Stage stageError = new Stage();
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/confirmUI.fxml"));
        Scene scene = new Scene(loader.load());
        if(Main.getBejelentkezett().getTema() == 1)
        {
            scene.getStylesheets().add(getClass().getResource("/fxml/css/dark_theme.css").toExternalForm());
        }
        stageError.setTitle("CONFIRM");
        stageError.setScene(scene);
        stageError.show();
        eventFo = event;
    }

    void hibaUzenet() throws IOException{
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

    void sikerUzenet() throws Exception
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

    static void torlesUtani() throws IOException{
        if(Main.getTorolte())
        {
            Stage stage;
            Scene scene;
            Parent loader;

            loader = FXMLLoader.load(editAccUIController.class.getResource("/fxml/loginUI.fxml"));
            stage = (Stage)((Node)eventFo.getSource()).getScene().getWindow();
            scene = new Scene(loader);
            if(Main.getBejelentkezett().getTema() == 1)
            {
                scene.getStylesheets().add(editAccUIController.class.getResource("/fxml/css/dark_theme.css").toExternalForm());
            }
            stage.setScene(scene);
            stage.show();
        }
    }

    static void torles(int n)
    {
        if(n == 1)
        {
            JpaAdatokDao jad = new JpaAdatokDao();
            List<Adatok> adatok = jad.getAdatok();
            for (int i = 0; i < adatok.size(); i++)
            {
                if(Main.getBejelentkezett().getFelhasznalonev().equals(adatok.get(i).getTulajdonos()))
                {
                    jad.deleteAdatok(adatok.get(i));
                }
            }
            JpaFelhasznaloDAO jfd = new JpaFelhasznaloDAO();
            List<Felhasznalo> felhasznalok = jfd.getFelhasznalok();
            for (int i = 0; i < felhasznalok.size(); i++)
            {
                if(Main.getBejelentkezett().getFelhasznalonev().equals(felhasznalok.get(i).getFelhasznalonev()))
                {
                    jfd.deleteFelhasznalo(felhasznalok.get(i));
                    break;
                }
            }
            Felhasznalo semmi = new Felhasznalo();
            Main.setBejelentkezett(semmi);
        }
    }

    public static void setEventFo(ActionEvent event)
    {
        eventFo = event;
    }
}
