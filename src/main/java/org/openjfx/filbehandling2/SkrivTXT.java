package org.openjfx.filbehandling2;

import org.openjfx.komponenter.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SkrivTXT {

    //Metode som skriver liste fra parameter til Handlevogn.txt
    public static void save(ArrayList<Product> a) throws IOException {
        Files.write(Paths.get("src/main/java/org/openjfx/Filer/Handlevogn.csv"), printString(a));
    }

    //Metode som gjør liste om til string
    //Skriver name, category og quantity med tabulator mellom slik at filen kan åpnes av excel
    //Velger tabulator som delimiter fordi noen av navnene har komma eller " i seg
    private static byte[] printString(ArrayList<Product> a) {
        String tekst = "Komponent\t Navn\t Pris \n";
        for (Product k : a) {
            tekst += k.category + "\t" + k.name + "\t" + k.quantity + "\n";
        }
        return tekst.getBytes();
    }

}
