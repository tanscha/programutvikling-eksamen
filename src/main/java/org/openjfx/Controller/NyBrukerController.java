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
import java.util.ArrayList;

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

    private boolean finnes(String brukernavn){
        ArrayList<Bruker> brukere = Brukere.HentBrukere();

        for (Bruker b : brukere){
            if (b.getBrukernavn().equalsIgnoreCase(brukernavn)){
                return true;
            }
        }
        return false;
    }

    public void btnTilbake(javafx.event.ActionEvent event) throws IOException {
        App.setRoot("logginn");
    }

    public void btnRegistrer(javafx.event.ActionEvent event) throws FileNotFoundException {
        String navn = hentNavn();
        if (Regex.godkjentnavnRegex(navn) && !finnes(navn)){
            Bruker nyBruker = new Bruker(hentNavn(), hentPassord(), brukerNR());
            Brukere.LeggTil(nyBruker);
            lblMld.setText("Du er nå registrert!");
        } else if (finnes(navn)){
            lblMld.setText("Brukeren eksisterer fra før!");
            txtNavn.clear();
            txtPassord.clear();
        } else {
            lblMld.setText("Skriv inn gyldig brukernavn og passord!");
            txtNavn.clear();
            txtPassord.clear();
        }

    }
}
