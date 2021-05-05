package org.openjfx.Lagring;

import org.openjfx.Filbehandling.FileOpener;
import org.openjfx.Filbehandling.FileOpenerJOBJ;
import org.openjfx.Filbehandling.LagreJOBJ;
import org.openjfx.Produkter.Kategori;
import org.openjfx.Produkter.Kategorier;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Lagring {

    private static Kategorier fil = new Kategorier();

    private static Kategorier fraArraytilKategorier(ArrayList<Kategori> a) {
        Kategorier ny = new Kategorier();

        for (Kategori element : a) {
            ny.addObjekt(element);
        }
        return ny;
    }

    private static ArrayList<Kategori> fraKategoritilArray(Kategorier a) {
        var aa = a.getKategorier();
        ArrayList<Kategori> ny = new ArrayList<>(aa);
        return ny;
    }

    public static void lastNed(){
        try{
            FileOpener les = new FileOpenerJOBJ();
            var liste = (ArrayList<Kategori>) les.read("src/main/java/org/openjfx/Filer/Kategorier.jobj");
            Kategorier alle = fraArraytilKategorier(liste);
            fil = alle;}
        catch (Exception e){}

    }

    public static ArrayList<Kategori> alleKategorier(){
        lastNed();
        return fraKategoritilArray(fil);
    }

    public static Kategori finnKategori(String navn) throws FileNotFoundException {
        ArrayList<Kategori> ak = alleKategorier();
        System.out.println(alleKategorier());
        Kategori kategori = null;
        for (Kategori k : ak) {
            if (k.navn.equalsIgnoreCase(navn)) {
                kategori = k;
            } else {
                lastNed();
                Kategori nyk = new Kategori(navn);
                fil.addObjekt(nyk);
                lagre();
                kategori = nyk;
            }
        }
        return kategori;
    }

    public static void LeggTil(String navn) throws FileNotFoundException {
        lastNed();
        Kategori k = new Kategori(navn);
        fil.addObjekt(k);
        lagre();
    }

    public static void lagre() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("src/main/java/org/openjfx/Filer/Kategorier.jobj");
        writer.print("");
        writer.close();

        ArrayList<Kategori> ferdigliste = fraKategoritilArray(fil);
        LagreJOBJ.lagreKategoriTilListe("Kategorier.jobj", ferdigliste);

    }
}
