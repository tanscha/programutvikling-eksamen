package org.openjfx.Produkter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.io.Serializable;

public class Kategoriliste implements Serializable {

    private static ObservableList<Kategori> kategorier = FXCollections.observableArrayList();

    public void addObjekt(Kategori k) {
        kategorier.add(k);
    }

    public ObservableList<Kategori> getKategorier() {
        return kategorier;
    }

    public static void fjernAlt() {
        kategorier.clear();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Kategori k : kategorier) {
            stringBuilder.append(k.toString());
            stringBuilder.append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }
}

