package org.openjfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.openjfx.Filbehandling.LagreCSV;
import org.openjfx.Lagring.Lagring;
import org.openjfx.Produkter.Kategori;
import org.openjfx.Produkter.Produkt;
import org.openjfx.Produkter.Produktliste;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("logginn"));
        stage.setScene(scene);
        stage.show();
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws IOException {
        launch();
        //nyttProdukt();
    }

    public static void nyttProdukt() throws IOException {
        Lagring.LeggTil("Traktor");
        Lagring.LeggTil("Klær");

        Kategori k = Lagring.finnKategori("Traktor");

        Produkt p = new Produkt("Rød", "Rask", 1, k);
        Produktliste pl = new Produktliste();
        pl.addObjekt(p);

        LagreCSV.save(pl);


    }
}