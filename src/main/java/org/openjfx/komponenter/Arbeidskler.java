package org.openjfx.komponenter;

import java.io.Serializable;

public class Arbeidskler extends Product implements Serializable {
    public Arbeidskler(String navn, int antall, String type) {
        super(navn, antall, type);
    }
}
