package org.openjfx.Produkter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class KonverterListe {

    //Metode som gjør om arraylist til ObservableList
    public static ObservableList<String> fraArraytilObservable(ArrayList<String> a) {
        ObservableList<String> ny = FXCollections.observableArrayList();

        ny.addAll(a);
        return ny;
    }

    //Metode som omgjør Komponentliste til ArrayList
    public static ArrayList<Produkt> fraKomponenttilArray(Produktliste a) {
        var aa = a.getRegister();
        ArrayList<Produkt> ny = new ArrayList<>();
        for (Produkt e : aa) {
            ny.add(e);
        }
        return ny;
    }
    //Fra array til kategorierliste
    public static Kategoriliste fraArraytilKategorier(ArrayList<Kategori> a) {
        Kategoriliste ny = new Kategoriliste();

        for (Kategori element : a) {
            ny.addObjekt(element);
        }
        return ny;
    }
    //Fra Kategoriliste til Array
    public static ArrayList<Kategori> fraKategoritilArray(Kategoriliste a) {
        var aa = a.getKategorier();
        ArrayList<Kategori> ny = new ArrayList<>(aa);
        return ny;
    }
}
