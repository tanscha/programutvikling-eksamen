package org.openjfx.Filbehandling;

import org.openjfx.Lagring.LagringKategori;
import org.openjfx.Produkter.Kategori;
import org.openjfx.Produkter.Produkt;
import org.openjfx.Produkter.Produktliste;
import org.openjfx.Validering.FeilFil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileOpenerCSV {
    //leser CSV og splitter string
    public static ArrayList<String[]> readCSV() throws IOException {
        ArrayList<String[]> liste = new ArrayList<>();
            String line;
            String splitBy = "\t";
            try
            {
                BufferedReader br = new BufferedReader(new FileReader("src/main/java/org/openjfx/Filer/Produkter.csv"));
                while ((line = br.readLine()) != null)
                {
                    String[] produkt = line.split(splitBy);
                    liste.add(produkt);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        return liste;
    }

    //metode for å hente en produktliste fra csv-fil
    public static Produktliste ListefraCSV() throws IOException {
        ArrayList<String[]> liste = readCSV();
        Produktliste produktliste = new Produktliste();
        if (!liste.isEmpty()) {
            liste.remove(0);
            for (String[] x : liste) {
                try {
                Kategori k = LagringKategori.finnKategori(x[3]);
                Produkt produkt = new Produkt(x[0], x[1], Integer.parseInt(x[2]), k);
                produktliste.addObjekt(produkt);
                }
                catch (Exception e) {
                    e.printStackTrace();}
            }
        }
        return produktliste;
    }
}

