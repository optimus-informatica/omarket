package br.osasco.optimus.omarket.usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Usuarios {
    private final Connection dbh;

    public Usuarios(Connection dbh) {
        this.dbh = dbh;
    }

    public ObservableList<Object> logon(String usuario, String senha) throws SQLException {
        ObservableList<Object> adm = admLogon(usuario, senha);
        if ((boolean)adm.get(1)) {
            return adm;
        }
        return userLogon(usuario, senha);
    }

    private ObservableList<Object> userLogon(String usuario, String senha) throws SQLException {
        long id = 0;
        ObservableList<Object> ret = FXCollections.observableArrayList(id, false, false, false, false);
        String sql = "select count(*), usuarioid, caixa, sistema, financeiro from usuarios inner join usuarios_perms using(usuarioid) where usuario=? and senha=password(?)";
        PreparedStatement stmt = dbh.prepareStatement(sql);
        stmt.setString(1, usuario);
        stmt.setString(2, senha);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            if (rs.getLong(1) == 1) {
                ret.set(0, rs.getLong(2));
                ret.set(1, false);
                ret.set(2, rs.getBoolean(3)); // Permissao caixa
                ret.set(3, rs.getBoolean(4)); // Permissao sistema
                ret.set(4, rs.getBoolean(5)); // Permissao financeiro
                //ret.set(5, rs.getBoolean(6)); // Permissao RH
            }
        }
        return ret;
    }

    private ObservableList<Object> admLogon(String usuario, String senha) throws SQLException {
        long id = 0;
        ObservableList<Object> ret = FXCollections.observableArrayList(id, false, false, false, false);
        String sql = "select count(*), admid from adms where usuario=? and senha=password(?)";
        PreparedStatement stmt = dbh.prepareStatement(sql);
        stmt.setString(1, usuario);
        stmt.setString(2, senha);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            if (rs.getLong(1) == 1) {
                ret.set(0, rs.getLong(2));
                ret.set(1, true); // Permissao administrador
                ret.set(2, true); // Permissao caixa
                ret.set(3, true); // Permissao sistema
                ret.set(4, true); // Permissao financeiro
                //ret.set(5, true); // Permissao RH
            }
        }
        return ret;
    }
}