package org.openjfx.Produkter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.Serializable;
import java.util.stream.Collectors;

public class Produktliste implements Serializable {

    private static ObservableList<Produkt> produktliste = FXCollections.observableArrayList();

    public void attachTableView(TableView<Produkt> tableView) {
        tableView.setItems(produktliste);
    }

    public void addObjekt(Produkt p) {
        produktliste.add(p);
    }

    public ObservableList<Produkt> getRegister() {
        return produktliste;
    }

    public static void fjernAlt() {
        produktliste.clear();
    }

    public void fjern(Produkt p) {
        produktliste.remove(p);
    }

    public boolean isEmpty() {
        if (produktliste.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return produktliste.size();
    }


    //Filtrerer ut fra Produkt og returnerer en ObservableList for å søke
    public static ObservableList<Produkt> søkEtterString(String input, ObservableList<Produkt> a) {
        return a.stream().filter(objekt ->
                objekt.toString().toLowerCase().matches(String.format("%s%s%s", ".*", input.toLowerCase(), ".*"))).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Produkt p : produktliste) {
            stringBuilder.append(p.toString());
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

}
