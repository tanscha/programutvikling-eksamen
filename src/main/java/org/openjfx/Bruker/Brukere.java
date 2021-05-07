package org.openjfx.Bruker;

import org.openjfx.Exceptions.InvalidBrukerException;
import org.openjfx.Filbehandling.FileOpener;
import org.openjfx.Filbehandling.FileOpenerJOBJ;
import org.openjfx.Filbehandling.LagreJOBJ;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

public class Brukere implements Serializable {

    public static void LeggTil(Bruker ny) throws FileNotFoundException{
        ArrayList<Bruker> list = HentBrukere();
        list.add(ny);

        PrintWriter writer = new PrintWriter("src/main/java/org/openjfx/Filer/Brukere.jobj");
        writer.print("");
        writer.close();

        LagreJOBJ.lagreBrukerTilFil("Brukere.jobj", list);

    }
    //Brukere fra liste
    public static ArrayList<Bruker> HentBrukere(){
        ArrayList<Bruker> list = new ArrayList<>();
        try{
            FileOpener les = new FileOpenerJOBJ();
            list = (ArrayList<Bruker>) les.read("src/main/java/org/openjfx/Filer/Brukere.jobj");
        } catch (InvalidBrukerException | IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
