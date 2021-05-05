package org.openjfx.Kunde;

import org.openjfx.filbehandling2.FileOpener;
import org.openjfx.filbehandling2.FileOpenerJOBJ;
import org.openjfx.filbehandling2.LagreJOBJ;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class Bestillinger implements Serializable {

    //Metode som henter bestillinger fra fil
    public static ArrayList<Bestilling> hentFraFil() throws IOException {
        ArrayList<Bestilling> liste = new ArrayList<>();
        try {
            FileOpener les = new FileOpenerJOBJ();
            liste = (ArrayList<Bestilling>) les.read("src/main/java/org/openjfx/Filer/Bestillinger.jobj");
        } catch (Exception e) {
        }
        return liste;
    }

    //Metode som legger til ny gitt bestilling
    public static ArrayList<Bestilling> leggtilNy(Bestilling ny) throws IOException {
        ArrayList<Bestilling> a = hentFraFil();
        a.add(ny);
        return a;
    }

    //Metode som lagrer bestillingene til fil
    public static void lagreBestillinger(ArrayList<Bestilling> a) throws IOException, ClassNotFoundException {
        PrintWriter writer = new PrintWriter("src/main/java/org/openjfx/Filer/Bestillinger.jobj");
        writer.print("");
        writer.close();

        LagreJOBJ.lagreBestillingTilListe("Bestillinger.jobj", a);

    }

    //Metode som finner antall bestillinger
    public static int finnAntall() throws IOException, ClassNotFoundException {
        ArrayList<Bestilling> a = hentFraFil();
        return a.size();
    }

    //Metode som finner bestillingere fra gitt kunde
    public static ArrayList<Bestilling> kundensBestillinger(int kundenr) throws IOException {
        ArrayList<Bestilling> a = hentFraFil();
        ArrayList<Bestilling> kundens = new ArrayList<>();

        for (Bestilling b : a) {
            if (b.kundenr == kundenr) {
                kundens.add(b);
            }
        }
        return kundens;

    }

}

