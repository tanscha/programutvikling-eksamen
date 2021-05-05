package org.openjfx.filbehandling2;

import org.openjfx.komponenter.Komponent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SkrivTXT {

    //Metode som skriver liste fra parameter til Handlevogn.txt
    public static void save(ArrayList<Komponent> a) throws IOException {
        Files.write(Paths.get("src/main/java/org/openjfx/Filer/Handlevogn.csv"), printString(a));
    }

    //Metode som gjør liste om til string
    //Skriver navn, type og pris med tabulator mellom slik at filen kan åpnes av excel
    //Velger tabulator som delimiter fordi noen av navnene har komma eller " i seg
    private static byte[] printString(ArrayList<Komponent> a) {
        String tekst = "Komponent\t Navn\t Pris \n";
        for (Komponent k : a) {
            tekst += k.type + "\t" + k.navn + "\t" + k.pris + "\n";
        }
        return tekst.getBytes();
    }

}
