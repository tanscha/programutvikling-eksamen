package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.openjfx.App;
import org.openjfx.Kunde.Kunde;
import org.openjfx.Kunde.Kunder;
import org.openjfx.validering.Regex;

import java.io.IOException;

public class NyKundeController {
    public Label lblMld;
    public TextField txtNavn;

    private String HentNavn() {
        return txtNavn.getText();
    }

    //Metode som skriver ut velkomstmelding til ny kunde
    private void SkrivMld(Kunde kunde) {
        String navn = kunde.navn.substring(0, 1).toUpperCase() + kunde.navn.substring(1).toLowerCase();
        lblMld.setText("Hei, " + navn + "! Du er nå registrert! \nDitt Kundenummer er: " + kunde.kundenr + "\nKundenummeret brukes for å logge inn");
    }

    // Metode som generer et nytt kundenummer utifra hvor mange kunder som allerede eksisterer
    private int genererKundenr() throws IOException {
        int kundenr = 1001 + Kunder.HentKunder().size();
        return kundenr;
    }

    //Tilbakeknapp
    public void btnTilbake(ActionEvent event) throws IOException {
        App.setRoot("kundelogginn");

    }

    //Registererkanpp
    public void Registrer(ActionEvent event) throws IOException {
        //Sjekker om navnet er gyldig (uten tall og andre tegn)
        //Hvis det er gyldig opprettes en ny kunde
        String navn = HentNavn();
        if (Regex.regexNavn(navn)) {
            Kunde nyKunde = new Kunde(HentNavn(), genererKundenr());
            Kunder.LeggTil(nyKunde);
            SkrivMld(nyKunde);
        } else {
            lblMld.setText("Skriv inn et gyldig name");
            txtNavn.clear();
        }

    }
}
