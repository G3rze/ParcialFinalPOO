module com.example.sistemadiccionariobd {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.parcialfinalpoo to javafx.fxml;
    exports com.example.parcialfinalpoo;
}