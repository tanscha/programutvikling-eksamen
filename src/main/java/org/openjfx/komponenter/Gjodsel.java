package org.openjfx.komponenter;

import java.io.Serializable;

public class Gjodsel extends Product implements Serializable {
    public Gjodsel(String navn, int antall, String type) {
        super(navn, antall, type);
    }
}
