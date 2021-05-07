package org.openjfx.Lagring;

import org.openjfx.Filbehandling.LagreCSV;
import org.openjfx.Produkter.KonverterListe;
import org.openjfx.Produkter.Produkt;
import org.openjfx.Produkter.Produktliste;

import java.io.IOException;
import java.util.ArrayList;

public class LagringProdukt {

    public static void LeggTilProdukt(Produkt produkt, Produktliste produktliste) throws IOException {
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
}
