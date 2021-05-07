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

    //Metode som gjør liste om til string
    //Skriver name, category og quantity med tabulator mellom slik at filen kan åpnes av excel
    //Velger tabulator som delimiter fordi noen av navnene har komma eller " i seg
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

    public static void blank() throws IOException {
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
