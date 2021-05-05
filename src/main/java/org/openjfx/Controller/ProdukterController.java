package org.openjfx.Controller;

import javafx.concurrent.WorkerStateEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.IntegerStringConverter;
import org.openjfx.App;
import org.openjfx.Produkter.Kategori;
import org.openjfx.Produkter.Produkt;
import org.openjfx.Produkter.Produktliste;
import org.openjfx.Sleep;

import org.openjfx.Filbehandling.LagreJOBJ;
import org.openjfx.Produkter.Kategori;
import org.openjfx.Produkter.Kategorier;
import org.openjfx.Produkter.Produkt;
import org.openjfx.Lagring.Lagring;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static org.openjfx.Lagring.Lagring.*;


public class ProdukterController implements Initializable {






    public ComboBox comboKategori;
    public TextField txtNavn;
    public TextField txtEgenskap;
    public Spinner spnAntall;
    public Label lblPrisogNavn;

    public TableColumn<Object, Integer> colAntall;
    public TableColumn<Object, String> colNavn;
    public TableColumn<Object, Kategori> colKategori;
    public TableColumn<Object, String> colEgenskap;

    public TextField txtSøk;
    public ComboBox typevalg;
    public ComboBox<String> comboType;
    public ComboBox KategoriValg;

    public Button btnÅpne;
    public Button btnLeggtil;
    public Button btnSlett;
    public Button btnLagre;
    public Label lblPrisogNavn;
    public TextField txtPris;
    public TextField txtNavn;
    public TableColumn<Object, String> colNavn;
    public TableColumn<Object, Integer> colPris;
    public TableColumn<Object, String> colType;
    public ComboBox<String> comboType;
    public Label lblFeilmld;
    public TextField txtSøk;
    public Button btnSlett;
    public ComboBox comboKategori;
    public ComboBox KategoriValg;
    public Spinner spnAntall;
    public TextField txtEgenskap;
    public TextField txtKategori;
    public Button btnLeggtilKat;
    public TableView tableView;

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

    //Tømmer tekstfelt
    private void nullstillTxt() {
        txtNavn.setText("");
        txtEgenskap.setText("");
        txtKategori.setText("");
    }

    private void oppdater() {
        tableView.getItems().removeAll();
        //tableView.setItems();
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
        System.out.println(produktliste);
        produktliste.attachTableView(tableView);
        if (produktliste.isEmpty()){
            lblFeilmld.setText("Fant ingen produkter");
        } else {
            lblFeilmld.setText("Viser alle lagrede produkter");
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
        //tableView.setDisable(true);
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
        //tableView.setEditable(true);
        //colType.setCellFactory(TextFieldTableCell.forTableColumn());
        //colNavn.setCellFactory(TextFieldTableCell.forTableColumn());
        //colPris.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        //comboType.setItems(typeListe());
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

    public void btnLeggTil(ActionEvent event) {
    }

    public void btnSlett(ActionEvent event) {
    }

    public void editTvNavn(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void editTvPris(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void editTvAntall(TableColumn.CellEditEvent cellEditEvent) {
    }

    public void btnLeggTilKat(ActionEvent event) throws FileNotFoundException {
        LeggTil(txtKategori.getText());
    }

    public void editTvNavn(TableColumn.CellEditEvent<Object, String> objectStringCellEditEvent) {
    }

    public void editTvPris(TableColumn.CellEditEvent<Object, Integer> objectIntegerCellEditEvent) {
    }
}
