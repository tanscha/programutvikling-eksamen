package org.openjfx.Validering;

import org.openjfx.Filbehandling.FileOpener;
import org.openjfx.Filbehandling.FileOpenerCSV;
import org.openjfx.Filbehandling.LagreCSV;
import org.openjfx.Lagring.LagringKategori;
import org.openjfx.Lagring.LagringProdukt;
import org.openjfx.Produkter.*;

import java.io.IOException;
import java.util.ArrayList;

public class FeilFil {

    public static void kategorifraProdukt() throws IOException {
        Produktliste ps = FileOpenerCSV.ListefraCSV();
        ArrayList<Produkt> ap = KonverterListe.fraKomponenttilArray(ps);
        ArrayList<Kategori> ny = new ArrayList<>();

        for (Produkt p : ap){
            Kategori k = new Kategori(p.getKategori());
            boolean finnes = false;
            for (Kategori kny : ny){
                if (kny.getNavn().equalsIgnoreCase(k.getNavn())){
                    finnes = true;
                }
            } if (!finnes){ny.add(k);}

        }

        LagringKategori.fjernAlt();
        LagringKategori.lagre(KonverterListe.fraArraytilKategorier(ny));

    }
}
