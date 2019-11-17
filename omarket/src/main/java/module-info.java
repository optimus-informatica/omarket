module br.osasco.optimus.omarket {
    requires transitive java.sql;
    requires br.osasco.optimus.omarket.usuarios;
    requires br.osasco.optimus.omarket.Produtos;
    requires br.osasco.omarket.optimus;
    requires java.desktop;
    requires java.logging;

    exports br.osasco.optimus.omarket;
}