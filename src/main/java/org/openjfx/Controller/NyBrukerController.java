package org.openjfx.Controller;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.openjfx.App;
import org.openjfx.Bruker.Bruker;
import org.openjfx.Bruker.Brukere;
import org.openjfx.Validering.Regex;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class NyBrukerController {

    public Label lblMld;
    public TextField txtNavn;
    public PasswordField txtPassord;
    public Button btnRegistrer;
    public Button btnTilbake;

    private String hentNavn() {return txtNavn.getText();}
    private String hentPassord() {return txtPassord.getText();}

    private int brukerNR(){
        int brukernr = 1 + Brukere.HentBrukere().size();
        return brukernr;
    }

    public void btnTilbake(javafx.event.ActionEvent event) throws IOException {
        App.setRoot("logginn");
    }

    public void btnRegistrer(javafx.event.ActionEvent event) throws FileNotFoundException {
        String navn = hentNavn();
        if (Regex.godkjentnavnRegex(navn)){
            Bruker nyBruker = new Bruker(hentNavn(), hentPassord(), brukerNR());
            Brukere.LeggTil(nyBruker);
            lblMld.setText("Du er nå registrert! Logg inn for å registrere produkter.");
        } else {
            lblMld.setText("Skriv gyldig brukernavn og passord");
            txtNavn.clear();
            txtPassord.clear();
        }
    }
}
