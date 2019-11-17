package br.osasco.optimus.omarket.usuarios;

import br.osasco.optimus.omarket.optimus.Results;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Usuarios {
    private final Connection dbh;

    public Usuarios(Connection dbh) {
        this.dbh = dbh;
    }
    
    public Results add(String nome, String usuario, String senha) throws SQLException {
        return add(nome, usuario, senha, true, false, false);
    }
    
    public Results add(String nome, String usuario, String senha, boolean caixa, boolean financeiro, boolean sistema) throws SQLException {
        if (exists(usuario)) {
            return new Results(true, "Usuário já registrado.");
        }
        
        String sql = "insert into usuarios (nome, usuario, senha, is_caixa, is_financeiro, is_system) values (?, ?, crypt(?, gen_salt('bf')), ?, ?, ?)";
        PreparedStatement stmt = dbh.prepareStatement(sql);
        stmt.setObject(1, nome);
        stmt.setObject(2, usuario);
        stmt.setObject(3, senha);
        stmt.setObject(4, caixa);
        stmt.setObject(5, financeiro);
        stmt.setObject(6, sistema);
        
        if (stmt.executeUpdate() < 1) {
            return new Results(true, "Houve um erro ao tentar registrar o novo usuário.");
        }
        
        return new Results(false, "");
    }
    
    
    public boolean exists(String usuario) throws SQLException {
        String sql = "select usuario=? from usuarios";
        PreparedStatement stmt = dbh.prepareStatement(sql);
        stmt.setString(1, usuario);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            return rs.getBoolean(1);
        }
        return false;
    }
    
    

    public List<Object> logon(String usuario, String senha) throws SQLException {
        List<Object> adm = admLogon(usuario, senha);
        if ((boolean)adm.get(1)) {
            return adm;
        }
        return userLogon(usuario, senha);
    }

    private List<Object> userLogon(String usuario, String senha) throws SQLException {
        long id = 0;
        List<Object> ret = new ArrayList<>();
        ret.add(id);
        ret.add(false);
        ret.add(false);
        ret.add(false);
        ret.add(false);
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

    private List<Object> admLogon(String usuario, String senha) throws SQLException {
        long id = 0;
        List<Object> ret = new ArrayList<>();
        ret.add(id);
        ret.add(false);
        ret.add(false);
        ret.add(false);
        ret.add(false);
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