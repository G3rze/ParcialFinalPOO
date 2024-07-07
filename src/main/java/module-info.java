module org.example.parcialfinalpoo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.parcialfinalpoo to javafx.fxml;
    exports org.example.parcialfinalpoo;
    exports org.example.parcialfinalpoo.DB;
    opens org.example.parcialfinalpoo.DB to javafx.fxml;
}