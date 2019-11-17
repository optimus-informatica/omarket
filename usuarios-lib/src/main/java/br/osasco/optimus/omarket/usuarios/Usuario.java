/*
 * Copyright 2019 brasilio.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.osasco.optimus.omarket.usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author brasilio
 */
public class Usuario {
    private int usuarioid;
    private String nome;
    private String usuario;
    private boolean isCaixa;
    private boolean isSystem;
    private boolean isFinanceiro;
    private boolean isAdmin;
    private final Connection dbh;
    
    
    public Usuario(Connection dbh, ResultSet rs) throws SQLException {
        this.dbh = dbh;
        usuarioid = rs.getInt("usuarioid");
        nome = rs.getString("nome");
        usuario = rs.getString("usuario");
        isCaixa = rs.getBoolean("is_caixa");
        isSystem = rs.getBoolean("is_system");
        isFinanceiro = rs.getBoolean("is_financeiro");
        isAdmin = rs.getBoolean("is_admin");
    }
    

    public int getUsuarioid() {
        return usuarioid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid = usuarioid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws SQLException {
        String sql = "update usuarios set nome=? where usuarioid=?";
        PreparedStatement stmt = dbh.prepareStatement(sql);
        stmt.setString(1, nome);
        stmt.setInt(2, usuarioid);
        stmt.execute();
        this.nome = nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) throws SQLException {
        Usuarios usuarios = new Usuarios(dbh);
        if (!usuarios.exists(usuario)) {
            String sql = "update usuarios set usuario=? where usuarioid=?";
            PreparedStatement stmt = dbh.prepareStatement(sql);
            stmt.setString(1, usuario);
            stmt.setInt(2, usuarioid);
            stmt.execute();
            this.usuario = usuario;
        }
    }

    public boolean isIsCaixa() {
        return isCaixa;
    }

    public void setIsCaixa(boolean isCaixa) throws SQLException {
        String sql = "update usuarios set is_caixa=? where usuarioid=?";
        PreparedStatement stmt = dbh.prepareStatement(sql);
        stmt.setBoolean(1, isCaixa);
        stmt.setInt(2, usuarioid);
        stmt.execute();
        this.isCaixa = isCaixa;
    }

    public boolean isIsSystem() {
        return isSystem;
    }

    public void setIsSystem(boolean isSystem) throws SQLException {
        String sql = "update usuarios set is_system=? where usuarioid=?";
        PreparedStatement stmt = dbh.prepareStatement(sql);
        stmt.setBoolean(1, isSystem);
        stmt.setInt(2, usuarioid);
        stmt.execute();
        this.isSystem = isSystem;
    }

    public boolean isIsFinanceiro() {
        return isFinanceiro;
    }

    public void setIsFinanceiro(boolean isFinanceiro) throws SQLException {
        String sql = "update usuarios set is_financeiro=? where usuarioid=?";
        PreparedStatement stmt = dbh.prepareStatement(sql);
        stmt.setBoolean(1, isFinanceiro);
        stmt.setInt(2, usuarioid);
        stmt.execute();
        this.isFinanceiro = isFinanceiro;
    }

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
}
