package org.openjfx.Controller;

import org.openjfx.Bruker.Bruker;
import org.openjfx.Bruker.BrukerRegister;
import org.openjfx.Bruker.Brukere;
import org.openjfx.Validering.Regex;

import javax.xml.validation.Validator;
import java.awt.event.ActionEvent;

public class NyBrukerController {

    private String hentNavn() {return txtNavn.getText();}
    private String hentPassord() {return txtPassord.getText();}

    private int brukerNR(){
        int brukernr = 1 + Brukere.HentBrukere().size();
        return brukernr;
    }

    public void Registrer(ActionEvent event){
        String navn = hentNavn();
        if (Regex.godkjentnavnRegex(navn)){
            Bruker nyBruker = new Bruker(hentNavn(), hentPassord(), brukerNR());
            Brukere.LeggTil(nyBruker);
            lblMld.setText("Du er nå registrert! Logg inn for å registrere produkter.")
        } else {
            lblMld.setText("Skriv gyldig brukernavn og passord");
            txtNavn.clear();
            txtPassord.clear();
        }
    }
}
