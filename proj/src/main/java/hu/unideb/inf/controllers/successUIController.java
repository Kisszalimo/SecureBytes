package hu.unideb.inf.controllers;

import hu.unideb.inf.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class successUIController {
    private Stage stage;

    @FXML
    private Text sikeruzenet;

    @FXML
    void okGombLenyomva(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize(){
        switch (Main.getSikerUzenet()){
            case 0:
                sikeruzenet.setText("Bejegyzés sikeresen hozzáadva.");
                break;
            case 1:
                sikeruzenet.setText("Új felhasználó sikeresen létrehozva.");
                break;
            case 2:
                sikeruzenet.setText("Sikeres bejelentkezés.");
                break;
            case 3:
                sikeruzenet.setText("Sikeres jelszóváltoztatás.");
                break;
            case 4:
                sikeruzenet.setText("Felhasználó sikeresen törölve.");
                break;
            case 5:
                sikeruzenet.setText("Sikeres kijelentkezés.");
                break;
            default:
                break;
        }
    }
}
