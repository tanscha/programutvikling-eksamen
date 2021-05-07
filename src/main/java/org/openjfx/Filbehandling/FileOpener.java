package org.openjfx.Filbehandling;

import org.openjfx.Produkter.Kategoriliste;

import java.io.IOException;
import java.nio.file.Path;


public interface FileOpener {
    void openFile(Kategoriliste kategoriliste, Path path) throws IOException, ClassNotFoundException;

    Object read(String string) throws IOException;
}
