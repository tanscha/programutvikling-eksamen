package org.openjfx.Filbehandling;

import org.openjfx.Produkter.Kategoriliste;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.openjfx.Produkter.Kategoriliste.fjernAlt;


public class FileOpenerJOBJ implements FileOpener {
    public void openFile(Kategoriliste kategoriliste, Path path) throws IOException {
        fjernAlt();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toFile()))) {
        }
    }

    @Override
    public ArrayList<? extends Serializable> read(String string) throws IOException {
        ArrayList<? extends Serializable> ArrayList = null;
        try (InputStream is = Files.newInputStream(Paths.get(string));
             ObjectInputStream ois = new ObjectInputStream(is)) {
            ArrayList = (ArrayList<? extends Serializable>) ois.readObject();
        } catch (ClassNotFoundException e) {
        } return ArrayList;
    }
}
