package org.openjfx.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.openjfx.App;
import org.openjfx.Kunde.AktivKunde;
import org.openjfx.Kunde.Bestilling;
import org.openjfx.Kunde.Bestillinger;
import org.openjfx.filbehandling2.FileOpener;
import org.openjfx.filbehandling2.FileOpenerJOBJ;
import org.openjfx.komponenter.Product;
import org.openjfx.komponenter.Komponentliste;
import org.openjfx.komponenter.ListeKonverterer;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static org.openjfx.Kunde.Bestillinger.lagreBestillinger;

public class KundeBestillController extends Window implements Initializable {


    public Button btnBestill;
    public Label lblFeilMld;

    public KundeBestillController() throws IOException, ClassNotFoundException {
    }

    @FXML
    private TableView TableViewKunde;
    @FXML
    private TableColumn colNavn;
    @FXML
    private TableColumn colType;
    @FXML
    private TableColumn colPris;
    @FXML
    private Label lblLastned;

    @FXML
    private ComboBox<Product> harddiskbox;
    @FXML
    private ComboBox<Product> minnebox;
    @FXML
    private ComboBox<Product> musbox;
    @FXML
    private ComboBox<Product> skjermbox;
    @FXML
    private ComboBox<Product> skjermkortbox;
    @FXML
    private ComboBox<Product> tastaturbox;
    @FXML
    private ComboBox<Product> prosessorbox;

    @FXML
    private Button btnHandlevogn;

    @FXML
    private Label lblPris;

    //Oppretter komponentliste som valgte produkter legges inn i
    Komponentliste nyttProdukt = new Komponentliste();

    //Finner aktiv bruker
    int aktivbruker = AktivKunde.getKundenr();
    int ordrenr = Bestillinger.finnAntall() + 106500;

    //Tilbakeknapp
    @FXML
    void btnTilbake(ActionEvent event) throws IOException {
        App.setRoot("kundevalg");
    }

    //Metode som returnerer ObservableList med komponenter av en valgt type
    private ObservableList<Product> FinnKomponenter(ArrayList<Product> liste, String type) {
        ArrayList<Product> typeliste = new ArrayList<>();
        for (Product k : liste) {
            if (type.equals(k.type)) {
                typeliste.add(k);
            }
        }
        ObservableList<Product> otypeliste = FXCollections.observableArrayList(typeliste);
        return otypeliste;
    }

    //Metode som beregner antall fra valgte produkter (Henter fra comobobox)
    //Skriver ut totalpris til lblPris
    private void beregnPris() {
        int harddiskpris = (harddiskbox.getValue()).antall;
        int minnepris = (minnebox.getValue()).antall;
        int muspris = (musbox.getValue()).antall;
        int skjermpris = (skjermbox.getValue()).antall;
        int skjermkortpris = (skjermkortbox.getValue()).antall;
        int tastaturpris = (tastaturbox.getValue()).antall;
        int prosessorpris = (prosessorbox.getValue()).antall;

        int totalsum = harddiskpris + minnepris + muspris + skjermpris + skjermkortpris + tastaturpris + prosessorpris;
        lblPris.setText(totalsum + " NOK");
    }

    //Metode som åpner et varselvindu ved lagring
    private void varselVindu() throws IOException {
        ButtonType Ja = new ButtonType("Ja", ButtonBar.ButtonData.OK_DONE);
        ButtonType Lukk = new ButtonType("Lukk", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.NONE, "Din ordre er lagret! \nVil du gå til din profil for å se dine ordre?", Ja, Lukk);
        alert.initOwner((Stage) App.getScene().getWindow());
        alert.setTitle("Bestilling lagret!");

        alert.showAndWait();

        if (alert.getResult() == Ja) {
            App.setRoot("alleordre");
        }
    }


    //Lagrer bestilling med kundeid til fil (bestillinger.jobj)
    public void lagreBestilling() throws IOException, ClassNotFoundException {
        Bestilling ny = new Bestilling(aktivbruker, ordrenr, ListeKonverterer.fraKomponenttilArray(nyttProdukt));
        ArrayList<Bestilling> alle = Bestillinger.leggtilNy(ny);
        lagreBestillinger(alle);
        nyttProdukt.attachTableView(TableViewKunde);
        btnBestill.setDisable(true);
        varselVindu();
    }

    public boolean sjekkOmTom() {
        //Sjekker at det er noe i alle boxene
        if (harddiskbox.getValue() == null || minnebox.getValue() == null || musbox.getSelectionModel() == null
                || skjermkortbox.getSelectionModel() == null || tastaturbox.getValue() == null || prosessorbox.getValue() == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Hemmer knapper
        btnBestill.setDisable(true);

        //Åpner komponenter.jobj fil (Som admin kan redigere)
        FileOpener les = new FileOpenerJOBJ();
        ArrayList<Product> liste = null;
        try {
            liste = (ArrayList<Product>) les.read("src/main/java/org/openjfx/Filer/Komponenter.jobj");

            //Comboboxer som henter komponenter fra fil
            harddiskbox.setItems(FinnKomponenter(liste, "Harddisk"));
            minnebox.setItems(FinnKomponenter(liste, "Minne"));
            musbox.setItems(FinnKomponenter(liste, "Mus"));
            skjermbox.setItems(FinnKomponenter(liste, "Skjerm"));
            skjermkortbox.setItems(FinnKomponenter(liste, "Skjermkort"));
            tastaturbox.setItems(FinnKomponenter(liste, "Tastatur"));
            prosessorbox.setItems(FinnKomponenter(liste, "Prosessor"));

            //Setter alle comboboxene til første komponent (Unngår at kunden ikke velger komponent)
            harddiskbox.getSelectionModel().selectFirst();
            minnebox.getSelectionModel().selectFirst();
            musbox.getSelectionModel().selectFirst();
            skjermbox.getSelectionModel().selectFirst();
            skjermkortbox.getSelectionModel().selectFirst();
            tastaturbox.getSelectionModel().selectFirst();
            prosessorbox.getSelectionModel().selectFirst();


        }
        //Catch hvis filen ikke kan åpnes eller er tom
        catch (IOException e) {
            lblFeilMld.setText("Finner ingen komponenter");
            btnHandlevogn.setDisable(true);
        }

        //Sjekker om det er noe i alle comboboxene
        if (sjekkOmTom()) {
            lblFeilMld.setText("Mangler komponenter");
            btnHandlevogn.setDisable(true);
        }

    }

    //Oppdater handlevogn-knapp
    @FXML
    void handlevogn(ActionEvent event) throws IOException, ClassNotFoundException {

        lagreNyttProdukt();
        nyttProdukt.attachTableView(TableViewKunde);
        beregnPris();
        btnBestill.setDisable(false);
        lblLastned.setText("");

    }

    //Metode som lagrer nyttprodukt fra valgte komponenter
    public void lagreNyttProdukt() {
        //Henter komponenter fra combobox
        Product prosessor = prosessorbox.getSelectionModel().getSelectedItem();
        Product harddisk = harddiskbox.getSelectionModel().getSelectedItem();
        Product minne = minnebox.getSelectionModel().getSelectedItem();
        Product mus = musbox.getSelectionModel().getSelectedItem();
        Product skjerm = skjermbox.getSelectionModel().getSelectedItem();
        Product skjermkort = skjermkortbox.getSelectionModel().getSelectedItem();
        Product tastatur = tastaturbox.getSelectionModel().getSelectedItem();

        //Tømmer komponentliste før nye produkter legges inn
        nyttProdukt.fjernAlt();

        //Legger valgte produkter inn i komponentliste
        nyttProdukt.addObjekt(prosessor);
        nyttProdukt.addObjekt(harddisk);
        nyttProdukt.addObjekt(minne);
        nyttProdukt.addObjekt(mus);
        nyttProdukt.addObjekt(skjerm);
        nyttProdukt.addObjekt(skjermkort);
        nyttProdukt.addObjekt(tastatur);

    }

    //Knapp som lagrer bestillingen
    @FXML
    void btnlagreBestilling(ActionEvent event) throws IOException, ClassNotFoundException {
        lagreBestilling();
        lblLastned.setText("Din ordre er lagret");
    }
}