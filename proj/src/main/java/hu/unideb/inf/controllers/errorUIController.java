package hu.unideb.inf.controllers;

import hu.unideb.inf.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/*---- error UI ----*/
public class errorUIController{
    private Stage stage;

    @FXML
    private Label hibauzenet;

    @FXML
    void okGombLenyomva(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize(){
        switch (Main.getErrorUzenet()){
            case 0:
                hibauzenet.setText("Hibás felhasználónév");
                break;
            case 1:
                hibauzenet.setText("Helytelen jelszó");
                break;
            case 2:
                hibauzenet.setText("Írj be felhasználónevet");
                break;
            case 3:
                hibauzenet.setText("Írj be jelszót!");
                break;
            case 4:
                hibauzenet.setText("Ez a felhasználónév már foglalt");
                break;
            case 5:
                hibauzenet.setText("Írj be egy e-mail címet");
                break;
            case 6:
                hibauzenet.setText("Írj be egy weboldalt");
                break;
            case 7:
                hibauzenet.setText("A két jelszó nem egyezik");
                break;
            case 8:
                hibauzenet.setText("Eddig is ez volt a jelszavad");
                break;
            case 9:
                hibauzenet.setText("A minimális jelszóhossz 6 karakter");
                break;
            default:
                break;
        }
    }
}
