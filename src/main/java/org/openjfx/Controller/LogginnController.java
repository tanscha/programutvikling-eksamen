package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import org.openjfx.App;

import java.io.IOException;

public class LogginnController {

    public Button btnLoggInn;
    public Button btnUten;

    @FXML
    private PasswordField txtPassord;
    @FXML
    private javafx.scene.control.TextField txtBrukernavn;
    @FXML
    private Label lblFeil;


    private void logginn() throws IOException {
        if (!txtPassord.getText().equals("passord") || !txtBrukernavn.getText().toLowerCase().equals("admin")) {
            lblFeil.setText("Feil brukernavn og/eller passord!");
            txtPassord.clear();
        } else {
            App.setRoot("produkter");
        }
    }

    @FXML
    void btnLoggInn(ActionEvent event) throws IOException {
        logginn();
    }

    @FXML
    void loggInnUten(ActionEvent event) throws IOException {
        App.setRoot("produkter");
    }
}