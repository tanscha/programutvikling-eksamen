package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import org.openjfx.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class ProdukterController implements Initializable {


    public Button btnÅpne;
    public Button btnLeggtil;
    public Button btnfjern;
    public ComboBox typevalg;
    public Button btnLagre;
    public Label lblPrisogNavn;
    public TextField txtPris;
    public TextField txtNavn;
    //public TableColumn<Object, String> colNavn;
    //public TableColumn<Object, Integer> colPris;
    //public TableColumn<Object, String> colType;
    public ComboBox<String> comboType;
    public Label lblFeilmld;
    public TextField txtSøk;
    public Button btnSlett;
    public ComboBox comboKategori;
    public ComboBox KategoriValg;
    public Spinner spnAntall;
    public TextField txtEgenskap;


    @FXML
    private ChoiceBox<String> kolonnesøk;

    @FXML
    void btnTilbake(ActionEvent event) throws IOException {
        App.setRoot("logginn");
    }

    //Tømmer tekstfelt
    private void nullstillTxt() {
        txtNavn.setText("");
        txtPris.setText("");
    }

    // Metode som aktiver knapper osv
    private void aktiverKnapper() {
        btnÅpne.setDisable(false);
        btnfjern.setDisable(false);
        btnLeggtil.setDisable(false);
        //tableView.setDisable(false);
        txtSøk.setDisable(false);
        kolonnesøk.setDisable(false);
        txtNavn.setDisable(false);
        txtPris.setDisable(false);
        comboType.setDisable(false);
        typevalg.setDisable(false);
        btnLagre.setDisable(false);
    }

    //Metode som hemmer knapper osv
    private void hemKnapper() {
        btnÅpne.setDisable(true);
        btnfjern.setDisable(true);
        btnLeggtil.setDisable(true);
        //tableView.setDisable(true);
        //txtSøk.setDisable(true);
        //kolonnesøk.setDisable(true);
        txtNavn.setDisable(true);
        txtPris.setDisable(true);
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

    public void btnÅpne(ActionEvent event) {
    }

    public void Søk(KeyEvent keyEvent) {
    }

    public void btnlagre(ActionEvent event) {
    }

    public void btnLeggTil(ActionEvent event) {
    }

    public void btnSlett(ActionEvent event) {
    }
}
