package org.openjfx.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.openjfx.App;
import org.openjfx.Kunde.AktivKunde;
import org.openjfx.Kunde.Bestilling;
import org.openjfx.Kunde.Kunde;
import org.openjfx.filbehandling2.FileOpener;
import org.openjfx.filbehandling2.FileOpenerJOBJ;
import org.openjfx.filbehandling2.SkrivTXT;
import org.openjfx.komponenter.Komponent;
import org.openjfx.komponenter.Komponentliste;
import org.openjfx.komponenter.ListeKonverterer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static org.openjfx.Kunde.Bestillinger.kundensBestillinger;

public class AlleOrdreController extends Window implements Initializable {

    public Label lblKundenavn;
    public Label lblKundenr;
    public Label lblFeilMld;

    public TableView<Komponent> TableViewAlle;
    public TableColumn<Object, String> colNavn;
    public TableColumn<Object, Integer> colPris;
    public TableColumn<Object, String> colType;
    public Label lblTotalpris;
    public Button btnlastned;
    public ComboBox bestillingCOMBO;

    //Oppretter komponentliste som valgte produkter legges inn i
    Komponentliste liste = new Komponentliste();
    int ordrenr;

    //Finner aktiv bruker
    int aktivbruker = AktivKunde.getKundenr();

    //Tilbakeknapp
    public void btnTilbake(ActionEvent event) throws IOException {
        App.setRoot("kundevalg");

    }

    //Oppdaterer tableview
    private void oppdaterorder() {
        TableViewAlle.getItems().removeAll();
        liste.attachTableView(TableViewAlle);
    }

    //Finner kundens bestillinger og setter de i comobox (ordrenr)
    public void KundensBestillinger() throws IOException, ClassNotFoundException {
        ArrayList<Bestilling> kundens = kundensBestillinger(aktivbruker);
        ArrayList<Integer> ut = new ArrayList<>();
        for (Bestilling b : kundens) {
            if (b.kundenr == aktivbruker) {
                ut.add(b.ordrenr);
            }
        }
        ObservableList<Integer> obut = FXCollections.observableArrayList(ut);
        bestillingCOMBO.setItems(obut);
        bestillingCOMBO.getSelectionModel().selectLast();
    }

    // Finner valgt ordre i combobox og henter ut denne
    public void finnOrdre() {
        liste.fjernAlt();
        try {
            int valgt = (Integer) bestillingCOMBO.getValue();
            int sum = 0;

            ArrayList<Bestilling> kundens = kundensBestillinger(aktivbruker);
            for (Bestilling b : kundens) {
                if (b.ordrenr == valgt) {
                    ordrenr = b.ordrenr;
                    liste = (ListeKonverterer.fraArraytilKomponent(b.handlevogn2));
                    for (Komponent k : b.handlevogn2) {
                        sum += k.pris;
                    }
                }
            }
            lblTotalpris.setText(sum + " NOK");
        } catch (Exception e) {
            lblFeilMld.setText("Fant ingen ordre");
            btnlastned.setDisable(true);
        }
    }

    // Finner ordren og viser den i tableview når en ordre velges
    public void velgeOrdre(ActionEvent event) {
        finnOrdre();
        oppdaterorder();
    }


    //Last ned handlevogn-knapp
    @FXML
    void LastNed(ActionEvent event) throws IOException {
        //Skriver valgte produkter til Handlevogn.csv fil
        SkrivTXT.save(ListeKonverterer.fraKomponenttilArray(liste));

        //Åpner filechooser, slik at kunden kan velge hvor filen skal lagres og lagre
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lagre ordre...");
        fileChooser.setInitialFileName("Handlevogn_" + ordrenr);
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
        FileChooser.ExtensionFilter ext2Filter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.getExtensionFilters().add(ext2Filter);
        File dest = fileChooser.showSaveDialog(this);
        if (dest != null) {
            try {
                Files.copy(Paths.get("src/main/java/org/openjfx/Filer/Handlevogn.csv"), dest.toPath());
            } catch (IOException ex) {
                lblFeilMld.setText("Filen ble ikke lastet ned");
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Setter alle kundens bestillinger i choiceboxen
        try {
            KundensBestillinger();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        //Finner navn og kundenr i fil fra aktivbruker
        FileOpener les = new FileOpenerJOBJ();
        ArrayList<Kunde> liste = null;
        try {
            liste = (ArrayList<Kunde>) les.read("src/main/java/org/openjfx/Filer/Kunde.jobj");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Kunde k : liste) {
            if (k.kundenr == aktivbruker) {
                lblKundenavn.setText(k.navn.substring(0, 1).toUpperCase() + k.navn.substring(1).toLowerCase());
                lblKundenr.setText(String.valueOf(k.kundenr));
            }
        }

        finnOrdre();
        oppdaterorder();

    }



}

