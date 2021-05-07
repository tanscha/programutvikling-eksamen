package org.openjfx.Lagring;

import org.openjfx.Filbehandling.*;
import org.openjfx.Produkter.Kategori;
import org.openjfx.Produkter.Kategoriliste;
import org.openjfx.Produkter.KonverterListe;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class LagringKategori {

    public static void fjernAlt(){
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("src/main/java/org/openjfx/Filer/Kat.jobj");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("");
        writer.close();
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

    public static void lagre(Kategoriliste kategoriliste) throws IOException {
        fjernAlt();
        ArrayList<Kategori> ferdigliste = KonverterListe.fraKategoritilArray(kategoriliste);
        LagreJOBJ.lagreKategoriTilFil("Kat.jobj", ferdigliste);
        Kategoriliste.fjernAlt();
    }

    public static void LeggTil(String navn) throws IOException {
        Kategoriliste.fjernAlt();
        Kategoriliste fil = lastNed();

        if (!(sjekkKategori(navn) && (navn != null))){
            Kategori k = new Kategori(navn);
            fil.addObjekt(k);
            lagre(fil);
        }
    }

    public static boolean sjekkKategori(String navn)  {
        ArrayList<Kategori> ArrayKategorier = hentFraFil();
        boolean finnes = false;

        for (Kategori k : ArrayKategorier){
            if (k.getNavn().equalsIgnoreCase(navn)) {
                finnes = true;
                break;
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
