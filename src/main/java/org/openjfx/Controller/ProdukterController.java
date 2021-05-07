package org.openjfx.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.IntegerStringConverter;
import org.openjfx.App;
import org.openjfx.Filbehandling.FileOpenerCSV;
import org.openjfx.Lagring.LagringKategori;
import org.openjfx.Lagring.LagringProdukt;
import org.openjfx.Produkter.*;
import org.openjfx.Sleep;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;


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

    public Button btnÅpne;
    public Button btnLeggtil;
    public Button btnSlett;
    public Button btnLagre;
    public Label lblFeilmld;
    public TextField txtKategori;
    public Button btnLeggtilKat;
    public Button btnFjernKat;

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

    @FXML
    private void btnÅpne(ActionEvent event) {
        produktliste.fjernAlt();
        hemKnapper();
        lblFeilmld.setText("Produkter lastes inn...");
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
        btnÅpne.setDisable(false);
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
    }

    //Metode som hemmer knapper osv
    private void hemKnapper() {
        btnÅpne.setDisable(true);
        btnSlett.setDisable(true);
        btnLeggtil.setDisable(true);
        tableView.setDisable(true);
        //txtSøk.setDisable(true);
        txtNavn.setDisable(true);
        //comboType.setDisable(true);
        //typevalg.setDisable(true);
        btnLagre.setDisable(true);
        btnLeggtilKat.setDisable(true);
        btnFjernKat.setDisable(true);
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


    }

    private void setKategorivalg() {
        ArrayList<String> kategorier = new ArrayList<>();
        ArrayList<Produkt> a = KonverterListe.fraKomponenttilArray(produktliste);

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
    }

    public void btnlagre(ActionEvent event) {
    }

    public void btnLeggTil(ActionEvent event) {
        produktliste.fjernAlt();
        String navn = txtNavn.getText();
        String egenskap = txtEgenskap.getText();
        Kategori kategori = LagringKategori.finnKategori(comboKategori.getValue());
        if (navn == null || navn.isEmpty()) {
            lblPrisogNavn.setText("Skriv inn riktig navn");
            lblFeilmld.setText("");
            try {
                Integer.parseInt(spnAntall.getPromptText());
            } catch (IllegalArgumentException e) {
                lblPrisogNavn.setText("Skriv inn riktig navn og pris");
                lblFeilmld.setText("");
            }
        } else if (egenskap == null || egenskap.isEmpty()) {
            lblPrisogNavn.setText("Skriv inn riktig egenskap");
            lblFeilmld.setText("");
            try {
                Integer.parseInt(spnAntall.getPromptText());
            } catch (IllegalArgumentException e) {
                lblPrisogNavn.setText("Skriv inn riktig egenskap og pris");
                lblFeilmld.setText("");
            }
        } else {
            try {
                int antall = spnAntall.getValue();
                System.out.println(antall);
                if (antall > 0) {
                    Produkt produkt = new Produkt(navn, egenskap, antall, kategori);
                    nullstillTxt();
                    produktliste = FileOpenerCSV.ListefraCSV();
                    LagringProdukt.LeggTilProdukt(produkt, produktliste);
                    lblFeilmld.setText("Produkt lagt til. Husk å lagre!");
                    lblPrisogNavn.setText("");
                    produktliste.fjernAlt();
                    produktliste = FileOpenerCSV.ListefraCSV();
                    oppdater();
                    setKategorivalg();
                    setKategorier();
                } else {
                    lblPrisogNavn.setText("Prisen må være med enn 0");
                    lblFeilmld.setText("");
                }
            } catch (IllegalArgumentException | IOException e) {
                lblPrisogNavn.setText("Skriv inn riktig antall");
                lblFeilmld.setText("");
                txtNavn.setText("");
                txtEgenskap.setText("");
            }
        }

    }

    public void btnSlett(ActionEvent event) {
    }

    public void btnLeggTilKat(ActionEvent event) {

        TextInputDialog td = new TextInputDialog("Nytt navn på kategori...");
        td.setHeaderText("Legg til ny kategori");
        Optional<String> nyttnavn = td.showAndWait();
        Button d = new Button("Legg til");

        LagringKategori.LeggTil(nyttnavn.get());
        setKategorier();
        lblPrisogNavn.setText("Kategori lagt til");
    }


    public void btnFjern(ActionEvent event) {
        String kategori = comboKategori.getValue();
        LagringKategori.slettKategori(kategori);
        setKategorier();
        comboKategori.getSelectionModel().selectFirst();
        lblPrisogNavn.setText("Kategori slettet");
    }

    public void editTvNavn(TableColumn.CellEditEvent<Object, String> objectStringCellEditEvent) {
    }

    public void editTvAntall(TableColumn.CellEditEvent<Object, Integer> objectIntegerCellEditEvent) {
    }

}
