package org.openjfx.Filbehandling;

import org.openjfx.Produkter.Kategori;
import org.openjfx.Produkter.KonverterListe;
import org.openjfx.Produkter.Produkt;
import org.openjfx.Produkter.Produktliste;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LagreCSV {

    public static void save(ArrayList<Produkt> a) throws IOException {
        Files.write(Paths.get("src/main/java/org/openjfx/Filer/Produkter.csv"), printString(a));
    }

    //Metode som gjør liste om til string
    //Skriver name, category og quantity med tabulator mellom slik at filen kan åpnes av excel
    //Velger tabulator som delimiter fordi noen av navnene har komma eller " i seg
    private static byte[] printString(ArrayList<Produkt> a) {
        String tekst = "Navn\t Egenskap\t Antall\t Kategori \n";
        for (Produkt p : a) {
            Kategori k = p.getKategori();
            tekst += p.getNavn() + "\t" + p.getEgenskap() + "\t" + p.getAntall() + "\t" + k.getNavn() + "\n";
        }
        return tekst.getBytes();
    }

    public static void save(Produktliste pl) throws IOException {
        save(KonverterListe.fraKomponenttilArray(pl));
    }

}
