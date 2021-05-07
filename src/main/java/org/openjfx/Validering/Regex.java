package org.openjfx.Validering;

import org.openjfx.Exceptions.InvalidAntallException;
import org.openjfx.Exceptions.InvalidNavnException;

public class Regex {

    public static String navnRegex (String string){
        if (string.matches("[A-Z][a-zøæåA-ZÆØÅ \\-0-9./,]+")){
            return string;
        }
        else throw new InvalidNavnException("Ugyldig navn, vennligst prøv igjen");
    }
    public static int antallRegex(int i){
        if (i >= 0){
            return i;
        }
        else throw new InvalidAntallException("Ugyldig antall, vennligst prøv igjen");
    }


}
