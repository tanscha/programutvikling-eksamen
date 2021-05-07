package org.openjfx.Bruker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class BrukerRegister {
    private static Bruker aktivBruker;

    public static Bruker getAktivBruker() {
        return aktivBruker;
    }

    public static void setAktivBruker(Bruker aktivBruker) {
        BrukerRegister.aktivBruker = aktivBruker;
    }
    private static ArrayList<Bruker> brukerArrayList = new ArrayList<>();

    private static ObservableList<Bruker> brukerObservableList = FXCollections.observableArrayList(brukerArrayList);

    private static ObservableList<Bruker> aktivBrukerListe = FXCollections.observableArrayList();
     public static void oppdater(){
         brukerObservableList.setAll(brukerArrayList);
     }
}
