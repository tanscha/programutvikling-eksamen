package org.openjfx.Hjelpeklasser;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import org.openjfx.Produkter.Produkt;
import org.openjfx.Produkter.Produktliste;

public class Thread extends Task<ObservableList<Produkt>> {

    @Override
    protected ObservableList<Produkt> call() throws Exception {
        return null;
    }
}
