package org.openjfx.Filbehandling;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileSaverJOBJ implements FilesSaver {
    @Override
    public void save(ArrayList<? extends Serializable> liste, Path path) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(String.valueOf(path), true);
             ObjectOutputStream oos = new ObjectOutputStream(fos))
        {
            oos.writeObject(liste);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new IOException("Filen kunne ikke lagres");
        }
    }
}
