module br.osasco.optimus.omarket {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires transitive java.sql;
    requires org.mariadb.jdbc;
    requires br.osasco.optimus.omarket.usuarios;

    opens br.osasco.optimus.omarket to javafx.fxml;
    exports br.osasco.optimus.omarket;
}