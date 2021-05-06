package org.openjfx.Produkter;

import java.io.Serializable;

public class Produkt implements Serializable {
    String navn;
    String egenskap;
    int antall;
    Kategori kategori;
    String kategorinavn;

    public Produkt(String navn, String egenskap, int antall, Kategori kategori) {
        this.navn = navn;
        this.egenskap = egenskap;
        this.antall = antall;
        this.kategori = kategori;
        this.kategorinavn = kategori.getNavn();
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

    public Kategori getKategori() {
        return kategori;
    }

    public void setKategori(Kategori kategori) {
        this.kategori = kategori;
    }

    public String getKategorinavn() {
        return kategorinavn;
    }

    public void setKategorinavn(String kategorinavn) {
        this.kategorinavn = kategorinavn;
    }

    @Override
    public String toString() {
        return "Produkt{" +
                "navn='" + navn + '\'' +
                ", egenskap='" + egenskap + '\'' +
                ", antall=" + antall +
                ", kategori='" + kategorinavn + '\'' +
                '}';
    }
}
