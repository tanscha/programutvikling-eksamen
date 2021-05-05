package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.openjfx.App;
import org.openjfx.Kunde.AktivKunde;
import org.openjfx.Kunde.Kunde;
import org.openjfx.filbehandling2.FileOpener;
import org.openjfx.filbehandling2.FileOpenerJOBJ;

import java.io.IOException;
import java.util.ArrayList;

public class KundeLogginnController {


    public Button btnTilbake;
    public Button btnLoggInn;
    public TextField txtBrukernavn;
    public Label lblFeil;

    //Metode som sjekker om kundennr eksisterer
    private boolean sjekkKunde(int kundenr) throws IOException {
        boolean login = false;
        FileOpener les = new FileOpenerJOBJ();
        var liste = (ArrayList<Kunde>) les.read("src/main/java/org/openjfx/Filer/Kunde.jobj");

        for (Kunde k : liste) {
            if (k.kundenr == kundenr) {
                login = true;
            }
        }
        return login;
    }


    //Tilbakeknapp
    @FXML
    void btnTilbake(ActionEvent event) throws IOException {
        App.setRoot("hovedside");
    }

    //Knapp som sjekker kundenr, logger inn og setter kunden som aktivkunde
    @FXML
    void btnLoggInn(ActionEvent event) throws IOException {
        try {
            int kundenr = Integer.parseInt(txtBrukernavn.getText());

            if (sjekkKunde(kundenr)) {
                AktivKunde.setKundenr(kundenr);
                App.setRoot("kundevalg");
            } else {
                lblFeil.setText("Feil kundenr");
            }
        } catch (Exception e) {
            lblFeil.setText("Feil kundenr");
        }
    }



    @FXML
    void RegistrerNy(ActionEvent event) throws IOException {
        App.setRoot("nykunde");
    }
}