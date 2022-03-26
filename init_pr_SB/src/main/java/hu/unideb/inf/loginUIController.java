package hu.unideb.inf;

import hu.unideb.inf.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

public class loginUIController {
    private Model model;
}

    /*public void setModel(Model model) {
        this.model = model;
    }
}*/

    /*@FXML
    private Label birthDayLabel;

    @FXML
    private Label creditsLabel;

    @FXML
    private Label helloLabel;

    @FXML
    private Label nameLabel;

    @FXML
    void handleChangeName(ActionEvent event) {
        model.getStudent().setName("Tom Smith");
        //refreshUI();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Button pushed");
        alert.setHeaderText("You have pushed the Change Name button");
        alert.setContentText("This shows that you have pushed a button...");
        alert.showAndWait();
    }

    private void refreshUI() {
        //nameLabel.setText(model.getStudent().getName());
        creditsLabel.setText("" + model.getStudent().getCredits());
        birthDayLabel.setText(model.getStudent().getDateOfBirth().toString());
    }

    @FXML
    void handleLoadButtonPushed(ActionEvent event) {
        nameLabel.textProperty().bind(model.getStudent().nameProperty());
        refreshUI();
        //TODO
    }

    @FXML
    void handleButtonPushed(){
        System.out.println("Hello world!!!");
        if (helloLabel.getText().equals("fekete")) helloLabel.setText("fehér");
        else helloLabel.setText("fekete");
    }
}*/
