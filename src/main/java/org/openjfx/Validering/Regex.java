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
    public static int antallRegex(String string){
        if (string.matches("[0-9.]+")){
            return Integer.parseInt(string);
        }
        else throw new InvalidAntallException("Ugyldig antall, vennligst prøv igjen");
    }

}
