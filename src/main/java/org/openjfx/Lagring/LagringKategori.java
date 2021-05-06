package org.openjfx.Lagring;

import org.openjfx.Filbehandling.FileOpener;
import org.openjfx.Filbehandling.FileOpenerJOBJ;
import org.openjfx.Filbehandling.LagreJOBJ;
import org.openjfx.Produkter.Kategori;
import org.openjfx.Produkter.Kategoriliste;
import org.openjfx.Produkter.KonverterListe;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LagringKategori {

    private static Kategoriliste fil = new Kategoriliste();

    public static void fjernAlt() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("src/main/java/org/openjfx/Filer/Kategorier.jobj");
        writer.print("");
        writer.close();
    }

    public static void lastNed(){
        try{
            FileOpener les = new FileOpenerJOBJ();
            var liste = (ArrayList<Kategori>) les.read("src/main/java/org/openjfx/Filer/Kategorier.jobj");
            Kategoriliste alle = KonverterListe.fraArraytilKategorier(liste);
            fil = alle;}
        catch (Exception e){}

    }

    public static void lagre(Kategoriliste kategoriliste) throws FileNotFoundException {
        fjernAlt();

        ArrayList<Kategori> ferdigliste = KonverterListe.fraKategoritilArray(kategoriliste);
        LagreJOBJ.lagreKategoriTilListe("Kategorier.jobj", ferdigliste);
    }

    public static Kategoriliste hentKategorier(){
        lastNed();
        return fil;
    }

    public static void LeggTil(String navn) throws FileNotFoundException {
        lastNed();
        Kategori k = new Kategori(navn);
        fil.addObjekt(k);
        lagre(fil);
    }

    public static Kategori finnKategori(String navn) throws FileNotFoundException {
        ArrayList<Kategori> ArrayKategorier = KonverterListe.fraKategoritilArray(hentKategorier());

        for (Kategori k : ArrayKategorier){
            if (k.getNavn().equalsIgnoreCase(navn)){
                return k;
            }
        }
        return null;
    }
}
