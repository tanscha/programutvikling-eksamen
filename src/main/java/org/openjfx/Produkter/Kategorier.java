package org.openjfx.Produkter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Serializable;

public class Kategorier implements Serializable {

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

    public void fjern(Kategori k) {
        kategorier.remove(k);
    }

    public boolean isEmpty() {
        if (kategorier.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return kategorier.size();
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

