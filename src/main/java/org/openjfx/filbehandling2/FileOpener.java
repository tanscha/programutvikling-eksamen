package org.openjfx.filbehandling2;

import org.openjfx.komponenter.Komponentliste;

import java.io.IOException;
import java.nio.file.Path;


public interface FileOpener {
    void openFile(Komponentliste komponentliste, Path path) throws IOException, ClassNotFoundException;

    Object read(String string) throws IOException;
}
