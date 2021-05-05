package org.openjfx.komponenter;

import java.io.Serializable;

public class Korn extends Product implements Serializable {
    String sakorn;
    public Korn(String name, int quantity, String category, String sakorn) {
        super(name, quantity, category);
        this.sakorn = sakorn;
    }

    public String getSakorn() {
        return sakorn;
    }

    public void setSakorn(String sakorn) {
        this.sakorn = sakorn;
    }

    @Override
    public String toString() {
        return "Korn{" +
                "såkorn='" + sakorn + '\'' +
                ", navn='" + name + '\'' +
                ", antall=" + quantity +
                ", kategori='" + category + '\'' +
                '}';
    }
}
