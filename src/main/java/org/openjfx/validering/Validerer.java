package org.openjfx.validering;

import org.openjfx.avvik.InvalidPrisException;

public class Validerer {


    public static int gyldigPris(int pris) throws InvalidPrisException {
        if (pris <= 0) {
            throw new InvalidPrisException("Ugyldig pris");
        }
        return pris;
    }
}
