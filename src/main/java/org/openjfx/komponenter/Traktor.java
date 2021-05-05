package org.openjfx.komponenter;

import java.io.Serializable;

public class Traktor extends Product implements Serializable {
    private String hestekrefter;

    public Traktor(String name, int quantity, String category, String hestekrefter) {
        super(name, quantity, category);
        this.hestekrefter = hestekrefter;
    }

    public String getHestekrefter() {
        return hestekrefter;
    }

    public void setHestekrefter(String hestekrefter) {
        this.hestekrefter = hestekrefter;
    }

    @Override
    public String toString() {
        return "Traktor{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", hestekrefter='" + hestekrefter + '\'' +
                '}';
    }
}
