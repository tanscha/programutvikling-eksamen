package org.openjfx.komponenter;

import java.io.Serializable;

public class NewCategory extends Product implements Serializable {
    private String extra;

    public NewCategory(String name, int quantity, String category, String extra) {
        super(name, quantity, category);
        this.extra = extra;
    }


    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "Nykategori{" +
                "extra='" + extra + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                '}';
    }
}
