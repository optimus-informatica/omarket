/*
 * Copyright 2019 Optimus.
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
package br.osasco.optimus.omarket.usuario;

import br.osasco.optimus.omarket.usuarios.Usuario;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;

/**
 *
 * @author brasilio
 */
public class ContextMenu extends JPopupMenu {
    private final JPanel panel = new JPanel();
    private final JLabel label = new JLabel("Senha:");
    private final JPasswordField pass = new JPasswordField(25);
    private final String[] options = {"Salvar", "Cancelar"};
    
    JMenuItem menu = new JMenuItem("Definir senha");
    
    public ContextMenu(Connection dbh, Usuario usuario) {
        panel.add(label);
        panel.add(pass);
        
        menu.addActionListener((ActionEvent ae) -> {
            int option = JOptionPane.showOptionDialog(null, panel, "Defina a senha", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);
            if (option == 0) {
                String senha = String.valueOf(pass.getPassword());
                if (senha.length() >= 4) {
                    String sql = "update usuarios set senha=? where usuarioid=?";
                    try {
                        PreparedStatement stmt = dbh.prepareStatement(sql);
                        stmt.setString(1, senha);
                        stmt.setInt(2, usuario.getUsuarioid());
                    }
                    catch (SQLException ex) {
                        JOptionPane.showInternalMessageDialog(panel, ex, "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        this.add(menu);
    }
}
