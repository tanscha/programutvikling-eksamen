package org.openjfx.Bruker;

import java.io.Serializable;

public class Bruker implements Serializable {
    private String brukernavn;
    private String passord;
    private int brukerId;

    public Bruker(String brukernavn, String passord) {
        this.brukernavn = brukernavn;
        this.passord = passord;
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
