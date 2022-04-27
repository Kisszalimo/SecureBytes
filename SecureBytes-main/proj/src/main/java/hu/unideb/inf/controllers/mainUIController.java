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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

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
    private ChoiceBox legordulo;

    @FXML
    private ListView jelszavak;

    @FXML
    private TextField keresesstring;

    @FXML
    private RadioButton vilagosRadio;

    @FXML
    private RadioButton sotetRadio;

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

    @FXML
    void osszesJelszoGombLenyomva(ActionEvent event) throws Exception{
        jelszavak.getItems().clear();

        JpaAdatokDao jad = new JpaAdatokDao();
        List<Adatok> adatok = jad.getAdatok();
        for (int i = 0; i < adatok.size(); i++)
        {
            if(adatok.get(i).getTulajdonos().equals(Main.getBejelentkezett().getFelhasznalonev()))
            {
                jelszavak.getItems().add(adatok.get(i).getWeboldal() + "\n" + adatok.get(i).getEmail());
            }
        }
    }

    @FXML
    void keresesGombLenyomva(ActionEvent event) throws  Exception{
        jelszavak.getItems().clear();
        JpaAdatokDao jad = new JpaAdatokDao();
        List<Adatok> adatok = jad.getAdatok();

        if(legordulo.getSelectionModel().getSelectedItem() == null)
        {
            osszesJelszoGombLenyomva(event);
        }
        else if(legordulo.getSelectionModel().getSelectedItem().equals("felhasználónév"))
        {
            if(keresesstring == null)
            {
                osszesJelszoGombLenyomva(event);
            }
            else
            {
                for(int i = 0; i < adatok.size(); i++)
                {
                    if(adatok.get(i).getTulajdonos().equals(Main.getBejelentkezett().getFelhasznalonev()) && adatok.get(i).getFelhasznalonev().contains(keresesstring.getText()))
                    {
                        jelszavak.getItems().add(adatok.get(i).getWeboldal() + "\n" + adatok.get(i).getEmail());
                    }
                }
            }
        }
        else if(legordulo.getSelectionModel().getSelectedItem().equals("jelszó"))
        {
            if(keresesstring == null)
            {
                osszesJelszoGombLenyomva(event);
            }
            else
            {
                for(int i = 0; i < adatok.size(); i++)
                {
                    if(adatok.get(i).getTulajdonos().equals(Main.getBejelentkezett().getFelhasznalonev()) && adatok.get(i).getJelszo().contains(keresesstring.getText()))
                    {
                        jelszavak.getItems().add(adatok.get(i).getWeboldal() + "\n" + adatok.get(i).getEmail());
                    }
                }
            }
        }
        else if(legordulo.getSelectionModel().getSelectedItem().equals("e-mail cím"))
        {
            if(keresesstring == null)
            {
                osszesJelszoGombLenyomva(event);
            }
            else
            {
                for(int i = 0; i < adatok.size(); i++)
                {
                    if(adatok.get(i).getTulajdonos().equals(Main.getBejelentkezett().getFelhasznalonev()) && adatok.get(i).getEmail().contains(keresesstring.getText()))
                    {
                        jelszavak.getItems().add(adatok.get(i).getWeboldal() + "\n" + adatok.get(i).getEmail());
                    }
                }
            }
        }
        else if(legordulo.getSelectionModel().getSelectedItem().equals("weboldal"))
        {
            if(keresesstring == null)
            {
                osszesJelszoGombLenyomva(event);
            }
            else
            {
                for(int i = 0; i < adatok.size(); i++)
                {
                    if(adatok.get(i).getTulajdonos().equals(Main.getBejelentkezett().getFelhasznalonev()) && adatok.get(i).getWeboldal().contains(keresesstring.getText()))
                    {
                        jelszavak.getItems().add(adatok.get(i).getWeboldal() + "\n" + adatok.get(i).getEmail());
                    }
                }
            }
        }

        keresesstring.setText("");
    }

    @FXML
    void kijelentkezesGombLenyomva(ActionEvent event) throws IOException{
        loader = FXMLLoader.load(getClass().getResource("/fxml/loginUI.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(loader);
        stage.setScene(scene);
        stage.show();
        Main.setBejelentkezett(new Felhasznalo());
        Main.setBejelentkezve(false);
    }

    @FXML
    void vilagosKivalasztva(ActionEvent event) throws IOException{
        sotetRadio.setSelected(false);
        Main.setTema(0);
    }

    @FXML
    void sotetKivalasztva(ActionEvent event) throws IOException{
        vilagosRadio.setSelected(false);
        Main.setTema(1);
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
