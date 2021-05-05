package org.openjfx.komponenter;

import java.io.Serializable;

public class Gjodsel extends Product implements Serializable {
    String typeOfGjodsel;
    public Gjodsel(String name, int quantity, String category, String typeOfGjodsel) {
        super(name, quantity, category);
        this.typeOfGjodsel = typeOfGjodsel;
    }

    public String getTypeOfGjodsel() {
        return typeOfGjodsel;
    }

    public void setTypeOfGjodsel(String typeOfGjodsel) {
        this.typeOfGjodsel = typeOfGjodsel;
    }

    @Override
    public String toString() {
        return "Gjødsel{" +
                "typeKler Gjødsel='" + typeOfGjodsel + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                '}';
    }
}
