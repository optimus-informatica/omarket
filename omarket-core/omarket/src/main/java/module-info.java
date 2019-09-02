module br.osasco.optimus.omarket {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires org.mariadb.jdbc;

    opens br.osasco.optimus.omarket to javafx.fxml;
    exports br.osasco.optimus.omarket;
}