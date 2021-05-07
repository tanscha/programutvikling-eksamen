package org.openjfx.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.util.converter.IntegerStringConverter;
import org.openjfx.App;
import org.openjfx.Exceptions.InvalidAntallException;
import org.openjfx.Exceptions.InvalidEgenskapException;
import org.openjfx.Exceptions.InvalidNavnException;
import org.openjfx.Filbehandling.FileOpenerCSV;
import org.openjfx.Filbehandling.LagreCSV;
import org.openjfx.Lagring.LagringKategori;
import org.openjfx.Lagring.LagringProdukt;
import org.openjfx.Produkter.*;
import org.openjfx.Sleep;
import org.openjfx.Validering.FeilFil;
import org.openjfx.Validering.Regex;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static java.lang.Thread.sleep;
import static org.openjfx.Produkter.KonverterListe.fraKomponenttilArray;


public class ProdukterController implements Initializable {



    ObservableList<Produkt> produktObservableList;
    ObservableList<Kategori> kategoriObservableList;

    public ComboBox<String> comboKategori;

    public TextField txtNavn;
    public TextField txtEgenskap;
    public Spinner<Integer> spnAntall;
    public Label lblPrisogNavn;

    public TableColumn<Object, Integer> colAntall;
    public TableColumn<Object, String> colNavn;
    public TableColumn<Object, String> colKategori;
    public TableColumn<Object, String> colEgenskap;

    public TextField txtSøk;
    public ComboBox<String> KategoriValg;
    public Label lblVis0;
    public Label lblVis1;
    public Label lblVis2;

    public Button btnLeggtil;
    public Button btnSlett;
    public Button btnLagre;
    public Label lblFeilmld;
    public Button btnLeggtilKat;
    public Button btnFjernKat;
    public Button btnTilbake;

    private Sleep task;


    @FXML
    private TableView<Produkt> tableView;

    @FXML
    void btnTilbake(ActionEvent event) throws IOException {
        App.setRoot("logginn");
    }

    private Produktliste produktliste = new Produktliste();

    private void setKategorier() {
        ArrayList<Kategori> liste = LagringKategori.hentFraFil();
        ArrayList<String> ut = new ArrayList<>();

        if (liste.isEmpty()){
            try {
                FeilFil.kategorifraProdukt();
                liste = LagringKategori.hentFraFil();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for (Kategori k : liste){
            ut.add(k.getNavn());
        }

        ObservableList<String> os = KonverterListe.fraArraytilObservable(ut);
        comboKategori.setItems(os);
        comboKategori.getSelectionModel().selectFirst();
    }

    //Tømmer tekstfelt
    private void nullstillTxt() {
        txtNavn.setText("");
        txtEgenskap.setText("");
    }

    private void oppdater() {
        tableView.getItems().removeAll();
        tableView.setItems(valgtTypeListe());
        txtSøk.clear();


    }

    private void lastinn() {
        Produktliste.fjernAlt();
        hemKnapper();
        lblFeilmld.setText("Produkter oppdateres..");
        lblPrisogNavn.setText("");
        setKategorier();
        task = new Sleep();
        task.setOnSucceeded(this::threadDone);
        task.setOnFailed(this::threadFailed);
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    private void threadDone(WorkerStateEvent workerStateEvent) {
        produktliste = task.getValue();
        produktliste.attachTableView(tableView);
        if (produktliste == null) {
            lblFeilmld.setText("Fant ingen produkter");
        } else {
            produktliste.attachTableView(tableView);
            setKategorivalg();
            if (produktliste.isEmpty()) {
                lblFeilmld.setText("Fant ingen produkter");

            } else {
                lblFeilmld.setText("Viser alle lagrede produkter");

            }
        }
        aktiverKnapper();
        oppdater();

    }

    private void threadFailed(WorkerStateEvent event) {
        var e = event.getSource().getException();
        lblFeilmld.setText("Feil ved åpning av fil");
        aktiverKnapper();
    }


    // Metode som aktiver knapper osv
    private void aktiverKnapper() {
        btnLeggtil.setDisable(false);
        tableView.setDisable(false);
        txtSøk.setDisable(false);
        txtNavn.setDisable(false);
        txtEgenskap.setDisable(false);
        spnAntall.setDisable(false);
        comboKategori.setDisable(false);
        btnLagre.setDisable(false);
        btnLeggtilKat.setDisable(false);
        btnFjernKat.setDisable(false);
        btnSlett.setDisable(false);
        KategoriValg.setDisable(false);
        lblVis0.setDisable(false);
        lblVis1.setDisable(false);
        lblVis2.setDisable(false);
        btnTilbake.setDisable(false);

    }

    //Metode som hemmer knapper osv
    private void hemKnapper() {
        btnSlett.setDisable(true);
        btnLeggtil.setDisable(true);
        tableView.setDisable(true);
        txtSøk.setDisable(true);
        txtNavn.setDisable(true);
        KategoriValg.setDisable(true);
        btnLagre.setDisable(true);
        btnLeggtilKat.setDisable(true);
        btnFjernKat.setDisable(true);
        btnSlett.setDisable(true);
        lblVis0.setDisable(true);
        lblVis1.setDisable(true);
        lblVis2.setDisable(true);
        btnTilbake.setDisable(true);
    }


    //Søkefelt som filtrerer dataliste
    @FXML
    void SøkeValg(ActionEvent event) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setEditable(true);
        colEgenskap.setCellFactory(TextFieldTableCell.forTableColumn());
        colNavn.setCellFactory(TextFieldTableCell.forTableColumn());
        colAntall.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        comboKategori.getSelectionModel().selectFirst();
        btnLeggtil.setDisable(true);
        txtNavn.setDisable(true);
        txtEgenskap.setDisable(true);
        spnAntall.setDisable(true);
        comboKategori.setDisable(true);
        btnLeggtilKat.setDisable(true);
        btnFjernKat.setDisable(true);
        setKategorier();

        try {
            produktliste = FileOpenerCSV.ListefraCSV();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        lastinn();


    }

    private void setKategorivalg() {
        ArrayList<String> kategorier = new ArrayList<>();
        ArrayList<Produkt> a = fraKomponenttilArray(produktliste);

        kategorier.add("Alle");

        for (Produkt p : a) {
            boolean finnes = false;
            for (String s : kategorier) {
                if (p.getKategori().equalsIgnoreCase(s))
                    finnes = true;
            }
            if (!finnes) {
                kategorier.add(p.getKategori());
            }
        }

        ObservableList<String> os = KonverterListe.fraArraytilObservable(kategorier);
        KategoriValg.setItems(os);
        KategoriValg.getSelectionModel().selectFirst();
    }

    private ObservableList<Produkt> valgtTypeListe() {
        String typestring = KategoriValg.getValue().toString();

        ArrayList<Produkt> alle = KonverterListe.fraKomponenttilArray(produktliste);
        ObservableList<Produkt> ny = FXCollections.observableArrayList();

        for (Produkt p : alle) {
            if (p.getKategori().matches(typestring) || typestring.equalsIgnoreCase("Alle")) {
                ny.add(p);
            }
        }
        return ny;
    }


    public void velgKategori(ActionEvent event) {
        try {
            tableView.setItems(valgtTypeListe());
        } catch (Exception e) {
        }

    }


    public void Søk(KeyEvent keyEvent) {
        if (txtSøk.getText().isBlank()){
            tableView.setItems(valgtTypeListe());
            return;
        }
        ObservableList<Produkt> resultat;
        ObservableList<Produkt> l = valgtTypeListe();

        if (txtSøk.getText() != null){
            resultat = Produktliste.søkEtterString(txtSøk.getText(), l);

            if (resultat == null){
            tableView.setItems(FXCollections.observableArrayList());
            }
            else {
                tableView.setItems(resultat);
            }
        }

    }
    private void lagre() throws IOException {
        LagreCSV.save(fraKomponenttilArray(produktliste));
    }

    public void btnlagre(ActionEvent event) throws IOException {
        LagreCSV.save(fraKomponenttilArray(produktliste));
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Lagre registrering...");
        fileChooser.setInitialFileName("Register"+ LocalDate.now());
        FileChooser.ExtensionFilter filter1 = new FileChooser.ExtensionFilter("CSV files (*.csv", "*.csv");
        FileChooser.ExtensionFilter filter2 = new FileChooser.ExtensionFilter("TXT files (*.txt", "*.txt");
        fileChooser.getExtensionFilters().add(filter1);
        fileChooser.getExtensionFilters().add(filter2);
        File dest = fileChooser.showSaveDialog(null);
        if (dest != null){
            try {
                Files.copy(Paths.get("src/main/java/org/openjfx/Filer/Produkter.csv"), dest.toPath());
            }
            catch (IOException e){
                lblFeilmld.setText("Filen ble ikke lastet ned. Vennligst prøv igjen.");
            }
        }
    }

    public void btnLeggTil(ActionEvent event) throws IOException {
        Produktliste.fjernAlt();
        String navn = txtNavn.getText();
        String egenskap = txtEgenskap.getText();
        int antall = spnAntall.getValue();
        Kategori kategori = LagringKategori.finnKategori(comboKategori.getValue());
        if (navn == null || navn.isEmpty()) {
            lblPrisogNavn.setText("Skriv inn riktig navn");
            lblFeilmld.setText("");
        }
        else  {
            Produkt produkt = new Produkt(navn, egenskap, antall, kategori);
                nullstillTxt();
                produktliste = FileOpenerCSV.ListefraCSV();
                LagringProdukt.leggTilProdukt(produkt, produktliste);
                lblFeilmld.setText("Produkt lagt til!");
                lblPrisogNavn.setText("");
                Produktliste.fjernAlt();
                produktliste = FileOpenerCSV.ListefraCSV();
                oppdater();
                setKategorivalg();
            }
        lagre();
        }


    public void btnSlett(ActionEvent event) throws IOException {
        Produkt slett = tableView.getSelectionModel().getSelectedItem();
        produktliste.fjern(slett);
        lblFeilmld.setText("Produktet er slettet!");
        oppdater();
        lagre();
        lastinn();
    }

    public void btnLeggTilKat(ActionEvent event) throws IOException {

        TextInputDialog td = new TextInputDialog("Nytt navn på kategori...");
        td.setHeaderText("Legg til ny kategori");
        Optional<String> nyttnavn = td.showAndWait();

        LagringKategori.LeggTil(nyttnavn.get());
        setKategorier();
        lblPrisogNavn.setText("Kategori lagt til");
    }


    public void btnFjern(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Advarsel!");
        alert.setHeaderText("Du er i ferd med å slette en kategori og alle tilhørende produkter!");
        alert.setContentText("Vil du avbryte?");
        ButtonType show = new ButtonType("Slett kategori", ButtonBar.ButtonData.RIGHT);
        alert.getButtonTypes().add(show);

        ButtonBar.setButtonUniformSize(alert.getDialogPane().lookupButton(show), false);
        alert.getDialogPane().lookupButton(show).addEventFilter(ActionEvent.ACTION, eevent -> {
            String kategori = comboKategori.getValue();
            try {
                LagringProdukt.slettProdukter(kategori);
                LagringKategori.slettKategori(kategori);
            } catch (IOException e) {
                e.printStackTrace();
            }
            setKategorier();
            comboKategori.getSelectionModel().selectFirst();
            lblPrisogNavn.setText("Kategori slettet");});
            event.consume();

            Optional<ButtonType> option = alert.showAndWait();
            oppdater();


        }



    public void editTvNavn(TableColumn.CellEditEvent<Object, String> cellEditEvent) throws IOException {
        Produkt produkt = tableView.getSelectionModel().getSelectedItem();
        try {
            String navn = cellEditEvent.getNewValue();
            Regex.navnRegex(navn);
            produkt.setNavn(navn);
            lblFeilmld.setText("Navnet er endret!");
        }
        catch (InvalidNavnException e){
            lblFeilmld.setText("Ugyldig navn. Vennligst prøv igjen.");
        }
        tableView.refresh();
        lblFeilmld.setText("Navnet er endret!");
        lagre();
        lastinn();
    }

    public void editTvAntall(TableColumn.CellEditEvent<Object, Integer> cellEditEvent) throws IOException {
        Produkt produkt = tableView.getSelectionModel().getSelectedItem();
        try {
            int antall = cellEditEvent.getNewValue();
            Regex.antallRegex(antall);
            produkt.setAntall(antall);
            lblFeilmld.setText("Antall er endret!");
            if (antall == 0){
                produktliste.fjern(produkt);
                tableView.refresh();
            }
        }
        catch (InvalidAntallException e){
            lblFeilmld.setText("Antall må være over null!");
        }
        tableView.refresh();
        lagre();
        lastinn();
    }
    public void editTvEgenskap(TableColumn.CellEditEvent<Object, String> cellEditEvent) throws IOException {
        Produkt produkt = tableView.getSelectionModel().getSelectedItem();
        try{
            String egenskap = cellEditEvent.getNewValue();
            produkt.setEgenskap(egenskap);
            lblFeilmld.setText("Egenskap er endret!");
        }
        catch (InvalidEgenskapException e){
            lblFeilmld.setText("Ugyldig egenskap. Vennligst prøv igjen.");
        }
        tableView.refresh();
        lagre();
        lastinn();
    }

}
