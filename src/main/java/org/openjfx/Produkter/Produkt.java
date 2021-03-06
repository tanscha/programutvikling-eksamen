package org.openjfx.Produkter;

import java.io.Serializable;

public class Produkt implements Serializable {
    String navn;
    String egenskap;
    int antall;
    Kategori kategori;

    public Produkt(String navn, String egenskap, int antall, Kategori kategori) {
        this.navn = navn;
        this.egenskap = egenskap;
        this.antall = antall;
        this.kategori = kategori;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getEgenskap() {
        return egenskap;
    }

    public void setEgenskap(String egenskap) {
        this.egenskap = egenskap;
    }

    public int getAntall() {
        return antall;
    }

    public void setAntall(int antall) {
        this.antall = antall;
    }


    public String getKategori() {
        return kategori.getNavn();
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    @Override
    public String toString() {
        return  navn + egenskap + antall + kategori.getNavn();
    }
}
