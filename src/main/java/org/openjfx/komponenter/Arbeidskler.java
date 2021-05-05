package org.openjfx.komponenter;

import java.io.Serializable;

public class Arbeidskler extends Product implements Serializable {
    String typeKler;
    public Arbeidskler(String name, int quantity, String category, String typeKler) {
        super(name, quantity, category);
        this.typeKler = typeKler;
    }

    @Override
    public String toString() {
        return "Arbeidsklær{" +
                "type klær='" + typeKler + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                '}';
    }
}
