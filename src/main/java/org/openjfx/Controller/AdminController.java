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
import org.openjfx.Sleep;
import org.openjfx.avvik.InvalidPrisException;
import org.openjfx.filbehandling2.LagreJOBJ;
import org.openjfx.komponenter.Product;
import org.openjfx.komponenter.Komponentliste;
import org.openjfx.validering.Validerer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static org.openjfx.komponenter.ListeKonverterer.*;
import static org.openjfx.komponenter.Typer.typeListe;


public class AdminController implements Initializable {


    public Button btnÅpne;
    public Button btnLeggtil;
    public Button btnfjern;
    public ComboBox typevalg;
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

    private Sleep task;


    @FXML
    private ChoiceBox<String> kolonnesøk;
    @FXML
    private TableView<Product> tableView;


    @FXML
    void btnTilbake(ActionEvent event) throws IOException {
        App.setRoot("adminlogginn");
    }


    //Oppretter komponentliste som innhold vises i tableview
    private Komponentliste dataliste = new Komponentliste();

    //Tømmer tekstfelt
    private void nullstillTxt() {
        txtNavn.setText("");
        txtPris.setText("");
    }

    //Oppdaterer tableview
    private void oppdater() {
        tableView.getItems().removeAll();
        tableView.setItems(valgtTypeListe());
        txtSøk.clear();
    }

    //Metode som lagrer alt fra tableview til fil
    private void lagre() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("src/main/java/org/openjfx/Filer/Komponenter.jobj");
        writer.print("");
        writer.close();

        ArrayList<Product> ferdigliste = fraKomponenttilArray(dataliste);
        LagreJOBJ.lagreKompTilListe("Komponenter.jobj", ferdigliste);
        lblFeilmld.setText("Komponentene er lagret");
        lblPrisogNavn.setText("");

    }

    //Legger til ny komponent til tableview
    @FXML
    void btnLeggTil(ActionEvent event){
        String navn = txtNavn.getText();
        if (navn == null || navn.trim().isEmpty()) {
            lblPrisogNavn.setText("Skriv inn riktig name");
            lblFeilmld.setText("");
            try {
                Integer.parseInt(txtPris.getText());
            } catch (IllegalArgumentException e) {
                lblPrisogNavn.setText("Skriv inn riktig name og quantity");
                lblFeilmld.setText("");
            }
        } else {
            try {
                int pris = Integer.parseInt(txtPris.getText());
                if (pris > 0) {
                    var komponent = new Product(navn, pris, comboType.getValue());
                    nullstillTxt();
                    dataliste.addObjekt(komponent);
                    lblFeilmld.setText("Komponent er lagt til. Husk å lagre!");
                    lblPrisogNavn.setText("");
                    oppdater();
                } else {
                    lblPrisogNavn.setText("Prisen må være mer enn 0");
                    lblFeilmld.setText("");
                    txtPris.setText("");
                }

            } catch (IllegalArgumentException e) {
                lblPrisogNavn.setText("Skriv inn riktig quantity");
                lblFeilmld.setText("");
                txtPris.setText("");
            }
        }
    }

    //Endre komponentene (name/quantity) i tableview
    @FXML
    void editTvNavn(TableColumn.CellEditEvent<Product, String> cellEditEvent) {
        Product product = tableView.getSelectionModel().getSelectedItem();
        String navn = cellEditEvent.getNewValue();
        product.setName(navn);
        tableView.refresh();
        lblFeilmld.setText("Navnet er endret. Husk å lagre!");
        lblPrisogNavn.setText("");
    }

    @FXML
    void editTvPris(TableColumn.CellEditEvent<Product, Integer> cellEditEvent) {
        Product product = tableView.getSelectionModel().getSelectedItem();

        try {
            int pris = cellEditEvent.getNewValue();
            Validerer.gyldigPris(pris);
            product.setQuantity(pris);
            lblFeilmld.setText("Prisen er endret. Husk å lagre!");
            lblPrisogNavn.setText("");

        } catch (InvalidPrisException e) {
            lblFeilmld.setText("Prisen må være mer enn 0");
            lblPrisogNavn.setText("");
        }
        tableView.refresh();


    }

    //Knapp som sletter et valgt objekt fra tableview
    @FXML
    void btnSlett(ActionEvent event){
        Product selectedItem = tableView.getSelectionModel().getSelectedItem();
        dataliste.fjern(selectedItem);
        lblFeilmld.setText("Valgt produkt er slettet. Husk å lagre!");
        lblPrisogNavn.setText("");
        oppdater();

    }

    // Knapp som lagrer alle endringer som er gjort
    public void btnlagre(ActionEvent event) throws FileNotFoundException {
        lagre();
    }


    // Knapp som åpner komponentfil og viser det i tableview
    // Mens kompoentene lastes blir alt av knapper osv disablet, disse blir aktivert igjen når filen er lastet opp
    @FXML
    private void btnÅpne(ActionEvent event) throws IOException {
        dataliste.fjernAlt();
        hemKnapper();
        lblFeilmld.setText("Komponentene lastes inn...");
        lblPrisogNavn.setText("");

        task = new Sleep();
        task.setOnSucceeded(this::threadDone);
        task.setOnFailed(this::threadFailed);
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
    }

    private void threadDone(WorkerStateEvent e) {
        dataliste = task.getValue();
        dataliste.attachTableView(tableView);
        if (dataliste.isEmpty()) {
            lblFeilmld.setText("Fant ingen komponenter");
        } else {
            lblFeilmld.setText("Viser alle lagrede komponenter");
        }
        aktiverKnapper();
        setTypevalg();
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
        btnfjern.setDisable(false);
        btnLeggtil.setDisable(false);
        tableView.setDisable(false);
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
        tableView.setDisable(true);
        txtSøk.setDisable(true);
        kolonnesøk.setDisable(true);
        txtNavn.setDisable(true);
        txtPris.setDisable(true);
        comboType.setDisable(true);
        typevalg.setDisable(true);
        btnLagre.setDisable(true);
    }

    //Metode som finner alle category komponenter og lager en Stringliste med typene som så blir vist i combobox
    private void setTypevalg() {
        ArrayList<String> typer = new ArrayList<>();
        ArrayList<Product> a = fraKomponenttilArray(dataliste);

        typer.add("Alle");

        for (Product k : a) {
            boolean ikkefrafør = true;
            for (String s : typer) {
                if (k.category.equals(s)) {
                    ikkefrafør = false;
                }
            }
            if (ikkefrafør) {
                typer.add(k.category);
            }
        }

        ObservableList<String> os = fraArraytilObservable(typer);
        typevalg.setItems(os);
        typevalg.getSelectionModel().selectFirst();
    }

    // Metode som finner valgt category fra combobox og lager en liste med disse komponentene
    private ObservableList<Product> valgtTypeListe() {
        String typestring = typevalg.getValue().toString();

        ArrayList<Product> alle = fraKomponenttilArray(dataliste);
        ObservableList<Product> ny = FXCollections.observableArrayList();

        for (Product k : alle) {
            if (k.category.matches(typestring) || typestring.equals("Alle")) {
                ny.add(k);
            }
        }

        return ny;


    }

    // Event metode som oppdaterer tableview til å vise kun valgte komponenter når det velges
    public void endretype(ActionEvent event) {
        try {
            tableView.setItems(valgtTypeListe());
        } catch (Exception e) {
        }
    }


    //Søkefelt som filtrerer dataliste
    @FXML
    void SøkeValg(ActionEvent event) {
    }

    // Metode som finner elementer som matches søkefeltet og oppdaterer tv til å kun vise disse
    @FXML
    void Søk(KeyEvent keyEvent) {
        if (txtSøk.getText().isBlank()) {
            tableView.setItems(valgtTypeListe());
            return;
        }
        ObservableList<Product> søkeresultat = null;
        ObservableList<Product> liste = valgtTypeListe();

        switch (kolonnesøk.getValue().toLowerCase()) {
            case "name":
                søkeresultat = Komponentliste.filtrerEtterNavn(txtSøk.getText(), liste);
                break;
            case "quantity":
                try {
                    søkeresultat = Komponentliste.filtrerEtterPris((Integer.parseInt(txtSøk.getText())), liste);
                } catch (NumberFormatException e) {
                }
        }

        if (søkeresultat == null) {
            tableView.setItems(FXCollections.observableArrayList());
        } else {
            tableView.setItems(søkeresultat);

        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableView.setEditable(true);
        colType.setCellFactory(TextFieldTableCell.forTableColumn());
        colNavn.setCellFactory(TextFieldTableCell.forTableColumn());
        colPris.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        comboType.setItems(typeListe());
        comboType.getSelectionModel().selectFirst();
        kolonnesøk.setValue("Navn");
        btnLagre.setDisable(true);
        btnLeggtil.setDisable(true);
        btnfjern.setDisable(true);

    }


}
