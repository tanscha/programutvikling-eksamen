package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import org.openjfx.App;

import java.io.IOException;

public class AdminLogginnController {


    public Button btnTilbake;
    public Button btnLoggInn;
    public Button btnUten;
    @FXML
    private PasswordField txtPassord;
    @FXML
    private javafx.scene.control.TextField txtBrukernavn;
    @FXML
    private Label lblFeil;

    //Metode som sjekker om brukernavn og passord er riktig
    //Passord er case-sensitiv, det er ikke brukernavnet
    private void innlogging() throws IOException {
        if (!txtPassord.getText().equals("passord") || !txtBrukernavn.getText().toLowerCase().equals("admin")) {
            lblFeil.setText("Feil brukernavn og/eller passord!");
            txtPassord.clear();
        } else {
            App.setRoot("adminkomponenter");
        }
    }

    //tilbake knapp
    @FXML
    void btnTilbake(ActionEvent event) throws IOException {
        App.setRoot("hovedside");
    }

    //logg inn knapp
    @FXML
    void btnLoggInn(ActionEvent event) throws IOException {
        innlogging();

    }

    //Knapp som gjøre det lettere å gå frem og tilbake uten alltid å måtte logge inn (under vurdering og programmering)
    @FXML
    void loggInnUten(ActionEvent event) throws IOException {
        App.setRoot("adminkomponenter");
    }
}