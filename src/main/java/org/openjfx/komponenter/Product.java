package org.openjfx.komponenter;

import java.io.Serializable;


public class Product implements Serializable {

    public String name;
    public int quantity;
    public String category;

    public Product(String name, int quantity, String category) {
        this.name = name;
        this.quantity = quantity;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format(name + ", " + quantity + ",-");
    }

    public String getKompType() {
        return category;
    }
}
