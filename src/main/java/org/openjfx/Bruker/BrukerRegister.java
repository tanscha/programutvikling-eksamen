package org.openjfx.Bruker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Locale;
import java.util.stream.Collectors;

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

    public static ArrayList<Bruker> getBrukerArrayList() {
        return brukerArrayList;
    }

    public static void setBrukerArrayList(ArrayList<Bruker> brukerArrayList) {
        BrukerRegister.brukerArrayList = brukerArrayList;
        oppdater();
    }

    public static ObservableList<Bruker> getBrukerObservableList() {
        return brukerObservableList;
    }

    public static void setBrukerObservableList(ObservableList<Bruker> brukerObservableList) {
        BrukerRegister.brukerObservableList = brukerObservableList;
    }

    public static ObservableList<Bruker> getAktivBrukerListe() {
        return aktivBrukerListe;
    }

    public static void setAktivBrukerListe(ObservableList<Bruker> aktivBrukerListe) {
        BrukerRegister.aktivBrukerListe = aktivBrukerListe;
    }
}
