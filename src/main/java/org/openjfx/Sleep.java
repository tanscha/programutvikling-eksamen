package org.openjfx;

import javafx.concurrent.Task;
import org.openjfx.Filbehandling.FileOpener;
import org.openjfx.Filbehandling.FileOpenerJOBJ;
import org.openjfx.komponenter.Product;
import org.openjfx.komponenter.Komponentliste;

import java.util.ArrayList;

import static org.openjfx.komponenter.ListeKonverterer.fraArraytilKomponent;

public class Sleep extends Task<Komponentliste> {

    private Komponentliste fil = null;


    public Sleep(){
        try{FileOpener les = new FileOpenerJOBJ();
        var liste = (ArrayList<Product>) les.read("src/main/java/org/openjfx/Filer/Komponenter.jobj");
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
