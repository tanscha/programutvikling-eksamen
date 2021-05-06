package org.openjfx.Hjelpeklasser;

import org.openjfx.Filbehandling.LagreCSV;
import org.openjfx.Lagring.LagringKategori;
import org.openjfx.Produkter.Kategori;
import org.openjfx.Produkter.Produkt;
import org.openjfx.Produkter.Produktliste;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Oppstart {

    public static void leggTilKategorier() throws FileNotFoundException {

        LagringKategori.LeggTil("Traktor");
        LagringKategori.LeggTil("Arbeidsklær");
        LagringKategori.LeggTil("Korn/Frø");
    }

    public static void leggTilProdukter() throws IOException {

        Kategori traktor = LagringKategori.finnKategori("Traktor");

        Produkt produkt = new Produkt("Rød traktor", "45 hK", 2, traktor);
        Produkt produkt1 = new Produkt("Blå traktor", "50hK", 1, traktor);

        Produktliste nyliste = new Produktliste();
        nyliste.addObjekt(produkt);
        nyliste.addObjekt(produkt1);

        LagreCSV.save(nyliste);
    }
}
