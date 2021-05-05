package org.openjfx;

import javafx.concurrent.Task;
import org.openjfx.Filbehandling.FileOpenerCSV;
import org.openjfx.Produkter.Produktliste;


public class Sleep extends Task<Produktliste> {

    private Produktliste fil = null;

    public Sleep(){
        try {
            Produktliste alle = FileOpenerCSV.ListefraCSV();
            fil = alle; }
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
