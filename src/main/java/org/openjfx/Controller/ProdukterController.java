package org.openjfx.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.TilePane;
import javafx.util.converter.IntegerStringConverter;
import org.openjfx.App;
import org.openjfx.Filbehandling.FileOpenerCSV;
import org.openjfx.Filbehandling.LagreCSV;
import org.openjfx.Lagring.LagringKategori;
import org.openjfx.Produkter.*;
import org.openjfx.Sleep;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import static org.openjfx.Lagring.LagringKategori.*;


public class ProdukterController implements Initializable {






    public ComboBox<String> comboKategori;

    public TextField txtNavn;
    public TextField txtEgenskap;
    public Spinner <Integer> spnAntall;
    public Label lblPrisogNavn;

    public TableColumn<Object, Integer> colAntall;
    public TableColumn<Object, String> colNavn;
    public TableColumn<Object, String> colKategori;
    public TableColumn<Object, String> colEgenskap;

    public TextField txtSøk;
    public ComboBox typevalg;
    public ComboBox<String> comboType;
    public ComboBox KategoriValg;

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
    private ChoiceBox<String> kolonnesøk;
    @FXML
    private TableView<Produkt> tableView;

    @FXML
    void btnTilbake(ActionEvent event) throws IOException {
        App.setRoot("logginn");
    }

    private Produktliste produktliste = new Produktliste();

    private ObservableList<String> finnKategorier() {
        ObservableList<String> kategorier = FXCollections.observableArrayList();

        ArrayList<Kategori> liste = KonverterListe.fraKategoritilArray(LagringKategori.hentKategorier());
        for (Kategori k : liste){
            kategorier.add(k.getNavn());
        }
        return kategorier;
    }

    //Tømmer tekstfelt
    private void nullstillTxt() {
        txtNavn.setText("");
        txtEgenskap.setText("");
    }

    private void oppdater() {
        tableView.getItems().removeAll();
        tableView.setItems(produktliste.getRegister());
        txtSøk.clear();


    }

    @FXML
    private void btnÅpne(ActionEvent event) throws IOException {
        produktliste.fjernAlt();
        hemKnapper();
        lblFeilmld.setText("Produkter lastes inn...");
        lblPrisogNavn.setText("");

        task = new Sleep();
        task.setOnSucceeded(this::threadDone);
        task.setOnFailed(this::threadFailed);
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    private void threadDone(WorkerStateEvent workerStateEvent) {
        produktliste = task.getValue();
        if (produktliste == null){
            lblFeilmld.setText("Fant ingen produkter");
        }
        else {
        produktliste.attachTableView(tableView);
        if (produktliste.isEmpty()){
            lblFeilmld.setText("Fant ingen produkter");
        }
        else {
            //lblFeilmld.setText("Viser alle lagrede produkter");
            lblFeilmld.setText(produktliste.getRegister().get(1).getKategori().navn);
        }}
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
        kolonnesøk.setDisable(false);
        txtNavn.setDisable(false);
        //comboType.setDisable(false);
        //typevalg.setDisable(false);
        btnLagre.setDisable(false);
        btnLeggtilKat.setDisable(false);
    }

    //Metode som hemmer knapper osv
    private void hemKnapper() {
        btnÅpne.setDisable(true);
        btnSlett.setDisable(true);
        btnLeggtil.setDisable(true);
        tableView.setDisable(true);
        //txtSøk.setDisable(true);
        //kolonnesøk.setDisable(true);
        txtNavn.setDisable(true);
        //comboType.setDisable(true);
        //typevalg.setDisable(true);
        btnLagre.setDisable(true);
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
        comboKategori.setItems(finnKategorier());
        comboKategori.getSelectionModel().selectFirst();

        //comboType.getSelectionModel().selectFirst();
        //kolonnesøk.setValue("Navn");
        //btnLagre.setDisable(true);
        //btnLeggtil.setDisable(true);
        //btnfjern.setDisable(true);

    }



    public void velgKategori(ActionEvent event) {
    }


    public void Søk(KeyEvent keyEvent) {
    }

    public void btnlagre(ActionEvent event) {
    }

    public void btnLeggTil(ActionEvent event) throws FileNotFoundException {
        String navn = txtNavn.getText();
        String egenskap = txtEgenskap.getText();
        Kategori kategori = LagringKategori.finnKategori(comboKategori.getValue());
        if (navn == null || navn.isEmpty()) {
            lblPrisogNavn.setText("Skriv inn riktig navn");
            lblFeilmld.setText("");
            try{
                Integer.parseInt(spnAntall.getPromptText());
            } catch (IllegalArgumentException e){
                lblPrisogNavn.setText("Skriv inn riktig navn og pris");
                lblFeilmld.setText("");
            }
        } else if (egenskap == null || egenskap.isEmpty()){
            lblPrisogNavn.setText("Skriv inn riktig egenskap");
            lblFeilmld.setText("");
            try{
                Integer.parseInt(spnAntall.getPromptText());
            } catch (IllegalArgumentException e){
                lblPrisogNavn.setText("Skriv inn riktig egenskap og pris");
                lblFeilmld.setText("");
            }
        }
        else {
            try {
                int antall = spnAntall.getValue();
                System.out.println(antall);
                if (antall > 0) {
                    Produkt produkt = new Produkt(navn, egenskap, antall, kategori);
                    nullstillTxt();
                    produktliste = FileOpenerCSV.ListefraCSV();
                    produktliste.addObjekt(produkt);
                    LagreCSV.save(produktliste);
                    produktliste.fjernAlt();
                    lblFeilmld.setText("Produkt lagt til. Husk å lagre!");
                    lblPrisogNavn.setText("");
                    oppdater();
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

    public void btnLeggTilKat(ActionEvent event) throws FileNotFoundException {

        TextInputDialog td = new TextInputDialog("Nytt navn på kategori...");
        td.setHeaderText("Legg til ny kategori");
        Optional<String> nyttnavn = td.showAndWait();
        Button d = new Button("Legg til");

        LagringKategori.LeggTil(nyttnavn.get());
        comboKategori.setItems(finnKategorier());
        lblPrisogNavn.setText("Kategori lagt til");
    }


    public void btnFjern(ActionEvent event) throws FileNotFoundException {
        String kategori = comboKategori.getValue();
        LagringKategori.slettKategori(kategori);
        comboKategori.setItems(finnKategorier());
        comboKategori.getSelectionModel().selectFirst();
        lblPrisogNavn.setText("Kategori slettet");
    }

    public void editTvNavn(TableColumn.CellEditEvent<Object, String> objectStringCellEditEvent) {
    }

    public void editTvAntall(TableColumn.CellEditEvent<Object, Integer> objectIntegerCellEditEvent) {
    }

}
