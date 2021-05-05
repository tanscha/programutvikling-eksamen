package org.openjfx.validering;

public class Regex {
    public static boolean regexNavn(String navn) {
        String regexNavn = "^([ \\u00c0-\\u01ffa-zA-Z'\\-])+$";
        return navn.matches(regexNavn);
    }
}

