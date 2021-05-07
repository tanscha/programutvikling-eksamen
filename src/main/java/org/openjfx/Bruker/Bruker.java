package org.openjfx.Bruker;

import org.openjfx.Produkter.Produktliste;

import java.io.Serializable;

public class Bruker implements Serializable {
    public String brukernavn;
    public String passord;
    public int brukerId;

    public Bruker(String brukernavn, String passord, int brukerId) {
        this.brukernavn = brukernavn;
        this.passord = passord;
        this.brukerId = brukerId;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }

    @Override
    public String toString() {
        return "Bruker{" +
                "brukernavn='" + brukernavn + '\'' +
                ", passord='" + passord + '\'' +
                '}';
    }
}
