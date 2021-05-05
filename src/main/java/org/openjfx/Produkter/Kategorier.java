package org.openjfx.Produkter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum Kategorier {
    Gjodsel("Gjødsel"),
    Arbeidskler("Arbeidsklær"),
    Traktor("Traktor"),
    Korn("Korn");
    Kategorier(String objekt){
    }

    public static ObservableList<String> kategoriListe(){
        ObservableList<String> kategorier = FXCollections.observableArrayList();
        for(Kategorier k: Kategorier.values()){
            kategorier.add(k.toString());
        }
        return kategorier;
    }
}
