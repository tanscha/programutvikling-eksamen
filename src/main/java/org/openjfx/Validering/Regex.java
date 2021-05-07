package org.openjfx.Validering;

import org.openjfx.Exceptions.InvalidAntallException;
import org.openjfx.Exceptions.InvalidNavnException;

public class Regex {

    public static String navnRegex (String string){
        if (string.matches("(?:\\s*[a-zøæåA-ZÆØÅ0-9,_\\.\\077\\0100\\*\\+\\&\\#\\'\\~\\;\\-\\!\\@\\;]{2,}\\s*)*")){
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
