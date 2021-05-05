package org.openjfx.Kunde;

import org.openjfx.komponenter.Product;

import java.io.Serializable;
import java.util.ArrayList;

public class Bestilling implements Serializable {

    public int kundenr;
    public int ordrenr;
    public ArrayList<Product> handlevogn2;

    public Bestilling(int kundenr, int ordrenr, ArrayList<Product> a) {
        this.kundenr = kundenr;
        this.ordrenr = ordrenr;
        handlevogn2 = a;
    }

    public int getKundenr() {
        return kundenr;
    }

    public int getOrdrenr() {
        return ordrenr;
    }

    public void setKundenr(int kundenr) {
        this.kundenr = kundenr;
    }

    public void setOrdrenr(int ordrenr) {
        this.ordrenr = ordrenr;
    }

    public ArrayList<Product> getHandlevogn2() {
        return handlevogn2;
    }

    public void setHandlevogn2(ArrayList<Product> handlevogn2) {
        this.handlevogn2 = handlevogn2;
    }

    public String toString() {
        return "kundenr=" + kundenr +
                ", ordrenr=" + ordrenr +
                ", " + handlevogn2;
    }
}



