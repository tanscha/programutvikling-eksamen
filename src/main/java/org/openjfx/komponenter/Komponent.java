package org.openjfx.komponenter;

import java.io.Serializable;


public class Komponent implements Serializable {

    public String navn;
    public int pris;
    public String type;

    public Komponent(String navn, int pris, String type) {
        this.navn = navn;
        this.pris = pris;
        this.type = type;
    }

    public String getNavn() {
        return navn;
    }

    public int getPris() {
        return pris;
    }

    public String getType() {
        return type;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format(navn + ", " + pris + ",-");
    }

    public String getKompType() {
        return type;
    }
}
