package org.openjfx.Lagring;

import org.openjfx.Filbehandling.FileOpenerCSV;
import org.openjfx.Filbehandling.LagreCSV;
import org.openjfx.Produkter.KonverterListe;
import org.openjfx.Produkter.Produkt;
import org.openjfx.Produkter.Produktliste;

import java.io.IOException;
import java.util.ArrayList;

public class LagringProdukt {

    public static void leggTilProdukt(Produkt produkt, Produktliste produktliste) throws IOException {
        ArrayList<Produkt> liste = KonverterListe.fraKomponenttilArray(produktliste);
        boolean finnes = false;

        for (Produkt p : liste){
            if (p.getNavn().equalsIgnoreCase(produkt.getNavn()) && p.getEgenskap().equalsIgnoreCase(produkt.getEgenskap())){
                int antall = p.getAntall() + produkt.getAntall();
                p.setAntall(antall);
                finnes = true;
            }
        }
        if (!finnes){
            liste.add(produkt);
        }

        LagreCSV.save(liste);
    }

    public  static void slettProdukter(String navn) throws IOException {
        Produktliste.fjernAlt();
        Produktliste alle = FileOpenerCSV.ListefraCSV();
        ArrayList<Produkt> arrayList = KonverterListe.fraKomponenttilArray(alle);
        Produktliste.fjernAlt();
        Produktliste ny = new Produktliste();

        for (Produkt p : arrayList) {
            if (!p.getKategori().matches(navn)) {
                ny.addObjekt(p);
            }
        }
        LagreCSV.blank();
        LagreCSV.save(ny);
    }
}
