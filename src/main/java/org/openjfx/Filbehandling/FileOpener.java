package org.openjfx.Filbehandling;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;


public interface FileOpener {
    // dette er vår:
    // void openFile(Produktliste komponentliste, Path path) throws IOException, ClassNotFoundException;
    // Object read(String string) throws IOException;

    // erik sin:
    ArrayList<? extends Serializable> read(String path) throws IOException, ClassNotFoundException;

}
