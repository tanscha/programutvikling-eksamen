package org.openjfx.komponenter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;


public class ListeKonverterer {

    //Metode som omgjør ArrayList til Komponentliste
    public static Komponentliste fraArraytilKomponent(ArrayList<Product> a) {
        Komponentliste ny = new Komponentliste();

        for (Product element : a) {
            ny.addObjekt(element);
        }
        return ny;
    }

    //Metode som omgjør Arraylist til Observable-StringListe
    public static ObservableList<String> fraArraytilObservable(ArrayList<String> a) {
        ObservableList<String> ny = FXCollections.observableArrayList();

        ny.addAll(a);
        return ny;
    }

    //Metode som omgjør Komponentliste til ArrayList
    public static ArrayList<Product> fraKomponenttilArray(Komponentliste a) {
        var aa = a.getRegister();
        ArrayList<Product> ny = new ArrayList<>();
        for (Product e : aa) {
            ny.add(e);
        }
        return ny;
    }


}
