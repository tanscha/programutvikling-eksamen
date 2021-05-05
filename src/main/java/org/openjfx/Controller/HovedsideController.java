package org.openjfx.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import org.openjfx.App;

import java.io.IOException;

public class HovedsideController {

    // Admin-knapp
    @FXML
    void btnAdmin(ActionEvent event) throws IOException {
        App.setRoot("adminlogginn");
    }

    // Kunde-knapp
    @FXML
    void btnKunde(ActionEvent event) throws IOException {
        App.setRoot("kundelogginn");
    }
}
