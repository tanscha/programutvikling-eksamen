package org.openjfx.Filbehandling;

import org.openjfx.Bruker.Bruker;
import org.openjfx.Produkter.Kategori;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;


public class LagreJOBJ {

    public static void lagreKategoriTilFil(String filnavn, ArrayList<Kategori> listeinn) {
        try {
            FilesSaver saver = new FileSaverJOBJ();
            saver.save(listeinn, Paths.get("src/main/java/org/openjfx/Filer/" + filnavn));
        } catch (IOException e) {
            e.printStackTrace();
        }}

        public static void lagreBrukerTilFil (String filnavn, ArrayList <Bruker> listeinn){
            try {
                FilesSaver saver = new FileSaverJOBJ();
                saver.save(listeinn, Paths.get("src/main/java/org/openjfx/Filer/" + filnavn));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

