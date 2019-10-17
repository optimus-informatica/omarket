module br.osasco.optimus.omarket {
    requires transitive java.sql;
    requires org.mariadb.jdbc;
    requires br.osasco.optimus.omarket.usuarios;
    requires java.desktop;
    requires java.logging;

    opens br.osasco.optimus.omarket to javafx.fxml;
    exports br.osasco.optimus.omarket;
}