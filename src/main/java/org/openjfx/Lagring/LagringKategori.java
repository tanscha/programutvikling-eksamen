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
        ArrayList<Kategori> ferdigliste = new ArrayList<>();
        LagreJOBJ.lagreKategoriTilListe("Kategorier.jobj", ferdigliste);


    }

    public static void slettKategori(String navn) throws FileNotFoundException {
        Kategoriliste kl = hentKategorier();
        Kategori k = finnKategori(navn);
        ArrayList<Kategori> akl = KonverterListe.fraKategoritilArray(kl);

        kl.fjern(k);
        LagreJOBJ.lagreKategoriTilListe("Kategorier.jobj", akl);

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
        ArrayList<Kategori> ferdigliste = KonverterListe.fraKategoritilArray(kategoriliste);
        LagreJOBJ.lagreKategoriTilListe("Kategorier.jobj", ferdigliste);
    }

    public static Kategoriliste hentKategorier(){
        lastNed();
        return fil;
    }

    public static void LeggTil(String navn) throws FileNotFoundException {
        if (!(sjekkKategori(navn) && navn!=null)){
            lastNed();
            Kategori k = new Kategori(navn);
            fil.addObjekt(k);
            lagre(fil);}
    }

    public static boolean sjekkKategori(String navn){
        ArrayList<Kategori> ArrayKategorier = KonverterListe.fraKategoritilArray(hentKategorier());
        boolean finnes = false;

        for (Kategori k : ArrayKategorier){
            if (k.getNavn().equalsIgnoreCase(navn)){
                finnes = true;
            }
        }
        return finnes;
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
