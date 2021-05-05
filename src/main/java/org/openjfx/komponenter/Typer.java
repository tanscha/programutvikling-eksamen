package org.openjfx.komponenter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum Typer {

    Harddisk("Harddisk"), Minne("Minne"), Mus("Mus"),
    Prosessor("Prosessor"), Skjerm("Skjerm"),
    Skjermkort("Skjermkort"), Tastatur("Tastatur");

    Typer(String Objekt) {
    }


    public static ObservableList<String> typeListe() {
        ObservableList<String> typer = FXCollections.observableArrayList();
        for (Typer tk : Typer.values()) {
            typer.add(tk.toString());
        }
        return typer;
    }
}
