package org.openjfx.Produkter;

import java.io.Serializable;

public class Kategori implements Serializable {

    public String navn;

    public Kategori(String navn) {
        this.navn = navn;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
}
