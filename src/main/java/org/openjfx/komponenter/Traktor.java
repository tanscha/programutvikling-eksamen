package org.openjfx.komponenter;

import java.io.Serializable;

public class Traktor extends Product implements Serializable {
    public Traktor(String navn, int antall, String type) {
        super(navn, antall, type);
    }
}
