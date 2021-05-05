module org.openjfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.datatransfer;
    requires java.desktop;

    opens org.openjfx.Controller to javafx.fxml;
    exports org.openjfx;

}