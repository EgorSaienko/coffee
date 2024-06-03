module com.edu.coffee {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires json.simple;

    requires org.apache.poi.ooxml;
    opens com.edu.coffee to javafx.fxml;
    exports com.edu.coffee;
}