package org.openjfx.komponenter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.Serializable;
import java.util.stream.Collectors;

public class Komponentliste implements Serializable {

    private static final long serialVersionUID = 1;
    private static ObservableList<Komponent> register = FXCollections.observableArrayList();


    public void attachTableView(TableView<Komponent> tableView) {
        tableView.setItems(register);
    }

    public void addObjekt(Komponent k) {
        register.add(k);
    }

    public ObservableList<Komponent> getRegister() {
        return register;
    }

    public void fjernAlt() {
        register.clear();
    }

    public void fjern(Komponent k) {
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


    //Metoder som filtrer ut komponenter etter gitt navn, pris eller type
    //Returner en observablelist med disse komponentene
    public static ObservableList<Komponent> filtrerEtterNavn(String navn, ObservableList<Komponent> a) {
        return a.stream().filter(objekt -> objekt.getNavn().
                toLowerCase().matches(String.format("%s%s%s", ".*", navn.
                toLowerCase(), ".*"))).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    public static ObservableList<Komponent> filtrerEtterPris(int pris, ObservableList<Komponent> a) {
        return a.stream().filter(objekt -> objekt.getPris() == pris).
                collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Komponent k : register) {
            stringBuilder.append(k.toString());
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }


}
