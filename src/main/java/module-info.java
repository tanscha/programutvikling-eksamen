module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires java.desktop;

    opens org.openjfx.Controller to javafx.fxml;
    opens org.openjfx.Produkter to javafx.base;
    exports org.openjfx;
    exports org.openjfx.Bruker;

}