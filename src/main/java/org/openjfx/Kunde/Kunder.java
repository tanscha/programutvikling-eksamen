package org.openjfx.Kunde;

import org.openjfx.filbehandling2.FileOpener;
import org.openjfx.filbehandling2.FileOpenerJOBJ;
import org.openjfx.filbehandling2.LagreJOBJ;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class Kunder implements Serializable {

    //Metode som legger til ny kunde
    public static void LeggTil(Kunde ny) throws IOException {
        ArrayList<Kunde> liste = HentKunder();
        liste.add(ny);

        PrintWriter writer = new PrintWriter("src/main/java/org/openjfx/Filer/Kunde.jobj");
        writer.print("");
        writer.close();

        LagreJOBJ.lagreKundeTilListe("Kunde.jobj", liste);
    }

    //Metode som henter alle kunder fra fil
    public static ArrayList<Kunde> HentKunder() throws IOException {
        ArrayList<Kunde> liste = new ArrayList<>();
        try {
            FileOpener les = new FileOpenerJOBJ();
            liste = (ArrayList<Kunde>) les.read("src/main/java/org/openjfx/Filer/Kunde.jobj");
        } catch (Exception e) {
        }

        return liste;

    }


}
