package org.openjfx.Kunde;

import java.io.Serializable;

public class Kunde implements Serializable {

    public String navn;
    public int kundenr;

    public Kunde(String navn, int kundenr) {
        this.navn = navn;
        this.kundenr = kundenr;
    }

    public String getNavn() {
        return navn;
    }

    public int getKundenr() {
        return kundenr;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public void setKundenr(int kundenr) {
        this.kundenr = kundenr;
    }
}
