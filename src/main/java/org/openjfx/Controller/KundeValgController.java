package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.openjfx.App;
import org.openjfx.Kunde.AktivKunde;
import org.openjfx.Kunde.Kunde;
import org.openjfx.filbehandling2.FileOpener;
import org.openjfx.filbehandling2.FileOpenerJOBJ;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class KundeValgController implements Initializable {

    public Label lblNavn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Finner aktivkunde fra fil og skriver Velkommen+name
        FileOpener les = new FileOpenerJOBJ();
        ArrayList<Kunde> liste = null;
        try {
            liste = (ArrayList<Kunde>) les.read("src/main/java/org/openjfx/Filer/Kunde.jobj");
        } catch (IOException e) {
            e.printStackTrace();
        }
        String navn = null;
        for (Kunde k : liste) {
            if (k.kundenr == AktivKunde.getKundenr()) {
                navn = k.navn.substring(0, 1).toUpperCase() + k.navn.substring(1).toLowerCase();
            }
        }
        lblNavn.setText("Velkommen, " + navn + "!");
    }


    //Knapper
    @FXML
    void btnNyBestilling(ActionEvent event) throws IOException {
        App.setRoot("handlevogn");
    }

    @FXML
    void btnSeAlle(ActionEvent event) throws IOException {
        App.setRoot("alleordre");
    }

    @FXML
    void LoggUt(ActionEvent event) throws IOException {
        App.setRoot("kundelogginn");
    }
}
