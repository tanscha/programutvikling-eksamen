package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import org.openjfx.App;
import org.openjfx.Bruker.Bruker;
import org.openjfx.Bruker.Brukere;

import java.io.IOException;
import java.util.ArrayList;

public class LogginnController {

    public Button btnLoggInn;
    public Button btnUten;
    public Button btnNyBruker;

    @FXML
    private PasswordField txtPassord;
    @FXML
    private javafx.scene.control.TextField txtBrukernavn;
    @FXML
    private Label lblFeil;


    private void logginn() throws IOException {
        String navn = txtBrukernavn.getText();
        String passord = txtPassord.getText();

        if (navn.equalsIgnoreCase("bruker") && passord.equalsIgnoreCase("bruker123")){
            App.setRoot("produkter");
        }
        else { sjekkBruker(navn, passord);}
    }

    private void sjekkBruker(String navn, String passord) throws IOException {
        ArrayList<Bruker> brukere = Brukere.HentBrukere();
        boolean finnes = false;

        for (Bruker b : brukere){

            if (b.getBrukernavn().equalsIgnoreCase(navn)){
                finnes = true;
                if (b.getPassord().equalsIgnoreCase(passord)){
                    App.setRoot("produkter");
                }
            }
        }

        if(finnes){
            lblFeil.setText("Feil passord! Prøv igjen");
            txtPassord.clear();
        }
        else {
            lblFeil.setText("Bruker eksisterer ikke. Lag ny bruker!");
            txtPassord.clear();
            txtBrukernavn.clear();
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

    public void btnNyBruker(ActionEvent event) throws IOException {
        App.setRoot("nyBruker");
    }
}