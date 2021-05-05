package org.openjfx.Filbehandling;

import org.openjfx.Produkter.Kategori;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;


public class LagreJOBJ {
    /*

    //Metode som skriver en komponent-arrayliste til gitt jobj-fil
    public static void lagreKompTilListe(String filnavn, ArrayList<Product> listeinn) {
        try {
            FilesSaver saver = new FileSaverJOBJ();
            saver.save(listeinn, Paths.get("src/main/java/org/openjfx/Filer/" + filnavn));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static void lagreKategoriTilListe(String filnavn, ArrayList<Kategori> listeinn) {
        try {
            FilesSaver saver = new FileSaverJOBJ();
            saver.save(listeinn, Paths.get("src/main/java/org/openjfx/Filer/" + filnavn));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
/*
    //Metode som skriver en bestilling-arrayliste til gitt jobj-fil
    public static void lagreBestillingTilListe(String filnavn, ArrayList<Bestilling> listeinn) {
        try {
            FilesSaver saver = new FileSaverJOBJ();
            saver.save(listeinn, Paths.get("src/main/java/org/openjfx/Filer/" + filnavn));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
