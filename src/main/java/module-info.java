module com.example.whiteboard {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires javafx.swing;     // ← ДОДАНО
    requires java.desktop;     // ← ДОДАНО

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.whiteboard to javafx.fxml;
    exports com.example.whiteboard;
}
