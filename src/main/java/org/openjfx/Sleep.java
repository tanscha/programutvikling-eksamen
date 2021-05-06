package org.openjfx;

import javafx.concurrent.Task;
import org.openjfx.Produkter.Produktliste;

import static org.openjfx.Filbehandling.FileOpenerCSV.*;


public class Sleep extends Task<Produktliste> {

    private Produktliste fil = null;

    public Sleep(){
        try {
            fil = ListefraCSV(); }
        catch (Exception e){}
    }

    @Override
    protected Produktliste call()  {
        try{
            Thread.sleep(2000);
        }
        catch (InterruptedException e){

        }
        return fil;
    }
}
