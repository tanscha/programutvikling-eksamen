package org.openjfx.Produkter;

import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.control.TableView;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.Collectors;

public class Produktliste implements Serializable {

    private static ObservableList<Produkt> register = FXCollections.observableArrayList();

    public void attachTableView(TableView<Produkt> tableView) {
            tableView.setItems(register);
        }

        public void addObjekt(Produkt p) {
            register.add(p);
        }

        public ObservableList<Produkt> getRegister() {
            return register;
        }

        public void fjernAlt() {
            register.clear();
        }

        public void fjern(Produkt p) {
            register.remove(p);
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
        public static ObservableList<Produkt> filtrerEtterNavn(String navn, ObservableList<Produkt> a) {
            return a.stream().filter(objekt -> objekt.getNavn().
                    toLowerCase().matches(String.format("%s%s%s", ".*", navn.
                    toLowerCase(), ".*"))).collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

    public static ObservableList<Produkt> filtrerEtterEgenskap(String egenskap, ObservableList<Produkt> a) {
        return a.stream().filter(objekt -> objekt.getEgenskap().
                toLowerCase().matches(String.format("%s%s%s", ".*", egenskap.
                toLowerCase(), ".*"))).collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

        public static ObservableList<Produkt> filtrerEtterAntall(int antall, ObservableList<Produkt> a) {
            return a.stream().filter(objekt -> objekt.getAntall() == antall).
                    collect(Collectors.toCollection(FXCollections::observableArrayList));
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (Produkt p : register) {
                stringBuilder.append(p.toString());
                stringBuilder.append(System.lineSeparator());
            }
            return stringBuilder.toString();
        }

    }
