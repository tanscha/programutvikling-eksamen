package org.openjfx.Filbehandling;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;

public interface FilesSaver {
    void save(ArrayList<? extends Serializable> list, Path path) throws IOException;
}
