package org.openjfx.Filbehandling;

import org.openjfx.Produkter.Kategori;
import org.openjfx.Produkter.KonverterListe;
import org.openjfx.Produkter.Produkt;
import org.openjfx.Produkter.Produktliste;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LagreCSV {

    public static void save(ArrayList<Produkt> a) throws IOException {
        blank();
        Files.write(Paths.get("src/main/java/org/openjfx/Filer/Produkter.csv"), printString(a));
    }
    private static byte[] printString(ArrayList<Produkt> a) {
        String tekst = "Navn\t Egenskap\t Antall\t Kategori \n";
        for (Produkt p : a) {
            tekst += p.getNavn() + "\t" + p.getEgenskap() + "\t" + p.getAntall() + "\t" + p.getKategori() + "\n";
        }
        return tekst.getBytes();
    }

    public static void save(Produktliste pl) throws IOException {
        save(KonverterListe.fraKomponenttilArray(pl));
    }
    //metode for å cleare en fil
    public static void blank() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("src/main/java/org/openjfx/Filer/Produkter.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("");
        writer.close();

    }

}
