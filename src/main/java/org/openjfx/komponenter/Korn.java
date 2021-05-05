package org.openjfx.komponenter;

import java.io.Serializable;

public class Korn extends Product implements Serializable {

    public Korn(String navn, int antall, String type) {
        super(navn, antall, type);
    }
}
