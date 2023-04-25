module com.example.tpcheck {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.example.tpcheck to javafx.fxml;
    exports com.example.tpcheck;
}