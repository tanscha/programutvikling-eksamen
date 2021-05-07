package org.openjfx.Lagring;

import org.openjfx.Filbehandling.*;
import org.openjfx.Produkter.Kategori;
import org.openjfx.Produkter.Kategoriliste;
import org.openjfx.Produkter.KonverterListe;
import java.util.ArrayList;

public class LagringKategori {

    public static void fjernAlt() {
        ArrayList<Kategori> ferdigliste = new ArrayList<>();
        LagreJOBJ.lagreKategoriTilFil("Kat.jobj", ferdigliste);
        
    }

    public static void slettKategori(String navn) {
        Kategoriliste kl = lastNed();
        Kategori k = finnKategori(navn);
        ArrayList<Kategori> akl = KonverterListe.fraKategoritilArray(kl);

        kl.fjern(k);
        LagreJOBJ.lagreKategoriTilFil("Kat.jobj", akl);

    }

    public static ArrayList<Kategori> hentFraFil() {
        ArrayList<Kategori> liste = new ArrayList<>();
        try {
            FileOpener les = new FileOpenerJOBJ();
            liste = (ArrayList<Kategori>) les.read("src/main/java/org/openjfx/Filer/Kat.jobj");
        } catch (Exception e) {
        }
        return liste;
    }

    public static Kategoriliste lastNed() {
        return KonverterListe.fraArraytilKategorier(hentFraFil());
    }

    public static void lagre(Kategoriliste kategoriliste) {
        ArrayList<Kategori> ferdigliste = KonverterListe.fraKategoritilArray(kategoriliste);
        LagreJOBJ.lagreKategoriTilFil("Kat.jobj", ferdigliste);
    }

    public static void LeggTil(String navn) {
        Kategoriliste fil = lastNed();
        
        if (!(sjekkKategori(navn) && navn!=null)){
            Kategori k = new Kategori(navn);
            fil.addObjekt(k);
            lagre(fil);}
    }

    public static boolean sjekkKategori(String navn)  {
        ArrayList<Kategori> ArrayKategorier = KonverterListe.fraKategoritilArray(lastNed());
        boolean finnes = false;

        for (Kategori k : ArrayKategorier){
            if (k.getNavn().equalsIgnoreCase(navn)){
                finnes = true;
            }
        }
        return finnes;
    }

    public static Kategori finnKategori(String navn) {
        ArrayList<Kategori> ArrayKategorier = hentFraFil();
        boolean finnes = false;
        Kategori kategori = null;

        
        for (Kategori k : ArrayKategorier){
            if (k.getNavn().equalsIgnoreCase(navn)){
                kategori = k;
                finnes = true;
            }
        }
        if (!finnes){
            kategori = new Kategori(navn);
        }
        return kategori;
    }
}
