package org.openjfx.komponenter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.Serializable;
import java.util.stream.Collectors;

public class Komponentliste implements Serializable {

    private static final long serialVersionUID = 1;
    private static ObservableList<Product> register = FXCollections.observableArrayList();


    public void attachTableView(TableView<Product> tableView) {
        tableView.setItems(register);
    }

    public void addObjekt(Product k) {
        register.add(k);
    }

    public ObservableList<Product> getRegister() {
        return register;
    }

    public void fjernAlt() {
        register.clear();
    }

    public void fjern(Product k) {
        register.remove(k);
    }

    public boolean isEmpty() {
        if (register.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return register.size();
    }


    //Metoder som filtrer ut komponenter etter gitt navn, antall eller type
    //Returner en observablelist med disse komponentene
    public static ObservableList<Product> filtrerEtterNavn(String navn, ObservableList<Product> a) {
        return a.stream().filter(objekt -> objekt.getNavn().
                toLowerCase().matches(String.format("%s%s%s", ".*", navn.
                toLowerCase(), ".*"))).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public static ObservableList<Product> filtrerEtterPris(int pris, ObservableList<Product> a) {
        return a.stream().filter(objekt -> objekt.getAntall() == pris).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Product k : register) {
            stringBuilder.append(k.toString());
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }


}
