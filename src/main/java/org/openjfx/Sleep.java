package org.openjfx;

import javafx.concurrent.Task;
import org.openjfx.filbehandling2.FileOpener;
import org.openjfx.filbehandling2.FileOpenerJOBJ;
import org.openjfx.komponenter.Komponent;
import org.openjfx.komponenter.Komponentliste;

import java.util.ArrayList;

import static org.openjfx.komponenter.ListeKonverterer.fraArraytilKomponent;

public class Sleep extends Task<Komponentliste> {

    private Komponentliste fil = null;


    public Sleep(){
        try{FileOpener les = new FileOpenerJOBJ();
        var liste = (ArrayList<Komponent>) les.read("src/main/java/org/openjfx/Filer/Komponenter.jobj");
        Komponentliste alle = fraArraytilKomponent(liste);
        fil = alle;}
        catch (Exception e){
        }
    }

    @Override
    protected Komponentliste call()  {
        try{
            Thread.sleep(3000);
        }
        catch (InterruptedException e){

        }
        return fil;
    }
}
