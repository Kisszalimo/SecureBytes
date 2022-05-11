package hu.unideb.inf.controllers;

import hu.unideb.inf.Main;
import hu.unideb.inf.MainApp;
import hu.unideb.inf.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.jasypt.util.text.StrongTextEncryptor;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class mainUIController {
    private Stage stage;
    private Scene scene;
    private Parent loader;
    private List<Integer> keresettAdatokID = new ArrayList<>();



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
    private RadioButton autoRadio;

    @FXML
    void mentesGombLenyomva(ActionEvent event) throws Exception {
        if(jelszoUj.getText().equals(""))
        {
            Main.setErrorUzenet(3);
            hibaUzenet();
        }
        else if(emailUj.getText().equals(""))
        {
            Main.setErrorUzenet(5);
            hibaUzenet();
        }
        else if(weboldalUj.getText().equals(""))
        {
            Main.setErrorUzenet(6);
            hibaUzenet();
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
            sikerUzenet();
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
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/aboutUI.fxml"));
        Scene scene = new Scene(loader.load());
        if(Main.getBejelentkezett().getTema() == 1)
        {
            scene.getStylesheets().add(getClass().getResource("/fxml/css/dark_theme.css").toExternalForm());
        }
        stageError.setTitle("Névjegy");
        stageError.setScene(scene);
        stageError.show();
    }

    @FXML
    void ujFiokGombLenyomva(ActionEvent event) throws Exception{
        loader = FXMLLoader.load(getClass().getResource("/fxml/createAccUI.fxml"));
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
    void modositasGombLenyomva(ActionEvent event) throws Exception{
        loader = FXMLLoader.load(getClass().getResource("/fxml/editAccUI.fxml"));
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
    void osszesJelszoGombLenyomva(ActionEvent event) throws Exception{
        jelszavak.getItems().clear();
        keresettAdatokID.clear();

        JpaAdatokDao jad = new JpaAdatokDao();
        List<Adatok> adatok = jad.getAdatok();
        for (int i = 0; i < adatok.size(); i++)
        {
            if(adatok.get(i).getTulajdonos().equals(Main.getBejelentkezett().getFelhasznalonev()))
            {
                keresettAdatokID.add(adatok.get(i).getId());
                jelszavak.getItems().add("Felhasználónév: " + adatok.get(i).getFelhasznalonev() + "\nJelszó: " + adatok.get(i).getJelszo() + "\nE-mail cím: " + adatok.get(i).getEmail() + "\nWeboldal: " + adatok.get(i).getWeboldal() + "\nLeírás: " + adatok.get(i).getLeiras());
            }
        }
    }

    @FXML
    void keresesGombLenyomva(ActionEvent event) throws  Exception{
        jelszavak.getItems().clear();
        JpaAdatokDao jad = new JpaAdatokDao();
        List<Adatok> adatok = jad.getAdatok();
        keresettAdatokID.clear();

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
                    if(adatok.get(i).getTulajdonos().equals(Main.getBejelentkezett().getFelhasznalonev()) && adatok.get(i).getFelhasznalonev().toLowerCase().contains(keresesstring.getText().toLowerCase()))
                    {
                        keresettAdatokID.add(adatok.get(i).getId());
                        jelszavak.getItems().add("Felhasználónév: " + adatok.get(i).getFelhasznalonev() + "\nJelszó: " + adatok.get(i).getJelszo() + "\nE-mail cím: " + adatok.get(i).getEmail() + "\nWeboldal: " + adatok.get(i).getWeboldal() + "\nLeírás: " + adatok.get(i).getLeiras());
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
                    if(adatok.get(i).getTulajdonos().equals(Main.getBejelentkezett().getFelhasznalonev()) && adatok.get(i).getJelszo().toLowerCase().contains(keresesstring.getText().toLowerCase()))
                    {
                        keresettAdatokID.add(adatok.get(i).getId());
                        jelszavak.getItems().add("Felhasználónév: " + adatok.get(i).getFelhasznalonev() + "\nJelszó: " + adatok.get(i).getJelszo() + "\nE-mail cím: " + adatok.get(i).getEmail() + "\nWeboldal: " + adatok.get(i).getWeboldal() + "\nLeírás: " + adatok.get(i).getLeiras());
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
                    if(adatok.get(i).getTulajdonos().equals(Main.getBejelentkezett().getFelhasznalonev()) && adatok.get(i).getEmail().toLowerCase().contains(keresesstring.getText().toLowerCase()))
                    {
                        keresettAdatokID.add(adatok.get(i).getId());
                        jelszavak.getItems().add("Felhasználónév: " + adatok.get(i).getFelhasznalonev() + "\nJelszó: " + adatok.get(i).getJelszo() + "\nE-mail cím: " + adatok.get(i).getEmail() + "\nWeboldal: " + adatok.get(i).getWeboldal() + "\nLeírás: " + adatok.get(i).getLeiras());
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
                    if(adatok.get(i).getTulajdonos().equals(Main.getBejelentkezett().getFelhasznalonev()) && adatok.get(i).getWeboldal().toLowerCase().contains(keresesstring.getText().toLowerCase()))
                    {
                        keresettAdatokID.add(adatok.get(i).getId());
                        jelszavak.getItems().add("Felhasználónév: " + adatok.get(i).getFelhasznalonev() + "\nJelszó: " + adatok.get(i).getJelszo() + "\nE-mail cím: " + adatok.get(i).getEmail() + "\nWeboldal: " + adatok.get(i).getWeboldal() + "\nLeírás: " + adatok.get(i).getLeiras());
                    }
                }
            }
        }

        keresesstring.setText("");
    }

    @FXML
    void kijelentkezesGombLenyomva(ActionEvent event) throws IOException{
        Stage stageError = new Stage();
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/confirmUI.fxml"));
        Scene scene = new Scene(loader.load());
        if(Main.getBejelentkezett().getTema() == 1)
        {
            scene.getStylesheets().add(getClass().getResource("/fxml/css/dark_theme.css").toExternalForm());
        }
        stageError.setTitle("MEGERŐSÍTÉS");
        stageError.setScene(scene);
        stageError.show();
        editAccUIController.setEventFo(event);
        Main.setKijelentkezett(true);
    }

    @FXML
    void vilagosKivalasztva(ActionEvent event) throws IOException{
        sotetRadio.setSelected(false);
        autoRadio.setSelected(false);
        setAutoTheme(false);
        setTema(0, event);
    }

    @FXML
    void sotetKivalasztva(ActionEvent event) throws IOException{
        vilagosRadio.setSelected(false);
        autoRadio.setSelected(false);
        setAutoTheme(false);
        setTema(1, event);
    }

    @FXML
    void autoKivalasztva(ActionEvent event) throws IOException{
        vilagosRadio.setSelected(false);
        sotetRadio.setSelected(false);
        setAutoTheme(true);
        setTema(Main.getTema(), event);
    }

    void hibaUzenet() throws Exception
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

    void setTema(int n, ActionEvent event) throws IOException {
        Main.setTema(n);
        JpaFelhasznaloDAO jfd = new JpaFelhasznaloDAO();
        List<Felhasznalo> felhasznalok = jfd.getFelhasznalok();
        for (int i = 0; i < felhasznalok.size(); i++)
        {
            if(felhasznalok.get(i).getFelhasznalonev().equals(Main.getBejelentkezett().getFelhasznalonev()))
            {
                felhasznalok.get(i).setTema(n);
                Main.setBejelentkezett(felhasznalok.get(i));
                jfd.saveFelhasznalo(felhasznalok.get(i));
            }
        }
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

    void setAutoTheme(boolean state) {
        if(state)
        {
            Main.setAutoTheme();
        }
        JpaFelhasznaloDAO jfd = new JpaFelhasznaloDAO();
        List<Felhasznalo> felhasznalok = jfd.getFelhasznalok();

        for (int i = 0; i < felhasznalok.size(); i++) {
            if (felhasznalok.get(i).getFelhasznalonev().equals(Main.getBejelentkezett().getFelhasznalonev())) {
                felhasznalok.get(i).setAutoTheme(state); // true -- false
                Main.setBejelentkezett(felhasznalok.get(i));
                jfd.saveFelhasznalo(felhasznalok.get(i));
            }
        }
    }

    @FXML
    public void initialize(){
        Main.setTorolte(false);
        Main.setKijelentkezett(false);
        bejelentkezettNeve.setText(Main.getBejelentkezett().getFelhasznalonev());

        if(Main.getBejelentkezett().getAutoTheme()) {
            vilagosRadio.setSelected(false);
            sotetRadio.setSelected(false);
            autoRadio.setSelected(true);
        }
        else {
            if(Main.getBejelentkezett().getTema() == 0)
            {
                vilagosRadio.setSelected(true);
                sotetRadio.setSelected(false);
                autoRadio.setSelected(false);
            }
            else if(Main.getBejelentkezett().getTema() == 1)
            {
                vilagosRadio.setSelected(false);
                sotetRadio.setSelected(true);
                autoRadio.setSelected(false);
            }
        }
    }

    public void kijeloltElemTorleseGombLenyomva(ActionEvent event) throws Exception {

        if (keresettAdatokID.size() > 0)
        {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        /*if(Main.getBejelentkezett().getTema() == 1)
        {
            alert.getStylesheets().add(getClass().getResource("/fxml/css/dark_theme.css").toExternalForm());
        }*/
            alert.setTitle("MEGERŐSÍTÉS");
            alert.setHeaderText("Jelszó törlése?");
            alert.setContentText("Valóban törölni szeretné a kiválasztott bejegyzést?\n\n\n");

            ButtonType buttonTypeOne = new ButtonType("Törlés");
            ButtonType buttonTypeCancel = new ButtonType("Mégse", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne){
                JpaAdatokDao jad = new JpaAdatokDao();
                List<Adatok> adatok = jad.getAdatok();
                for (int i = 0; i < adatok.size(); i++)
                {
                    if(adatok.get(i).getId() == keresettAdatokID.get(jelszavak.getSelectionModel().getSelectedIndex()))
                    {
                        jad.deleteAdatok(adatok.get(i));
                        break;
                    }
                }
            }
            keresesGombLenyomva(event);
        }
    }
}
