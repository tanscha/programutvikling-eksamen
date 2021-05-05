package org.openjfx.komponenter;

import java.io.Serializable;


public class Product implements Serializable {

    public String navn;
    public int antall;
    public String type;

    public Product(String navn, int antall, String type) {
        this.navn = navn;
        this.antall = antall;
        this.type = type;
    }

    public String getNavn() {
        return navn;
    }

    public int getAntall() {
        return antall;
    }

    public String getType() {
        return type;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setAntall(int antall) {
        this.antall = antall;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format(navn + ", " + antall + ",-");
    }

    public String getKompType() {
        return type;
    }
}
