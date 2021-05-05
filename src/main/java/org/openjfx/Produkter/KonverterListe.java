package org.openjfx.Produkter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class KonverterListe {
    public static Produktliste fraArraytilProdukt(ArrayList<Produkt> a) {
        Produktliste ny = new Produktliste();

        for (Produkt element : a) {
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
    public static ArrayList<Produkt> fraKomponenttilArray(Produktliste a) {
        var aa = a.getRegister();
        ArrayList<Produkt> ny = new ArrayList<>();
        for (Produkt e : aa) {
            ny.add(e);
        }
        return ny;
    }
}
