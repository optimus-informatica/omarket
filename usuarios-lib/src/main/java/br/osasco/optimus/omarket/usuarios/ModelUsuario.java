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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author brasilio
 */
public class ModelUsuario extends AbstractTableModel {
    
    private final List<Usuario> data = new ArrayList<>();
    private final String[] columns = {"ID", "Nome", "Usuário", "Administrador", "Caixa", "Sistema", "Financeiro"};
    
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int line, int column) {
        switch (column) {
            case 0:
                return data.get(line).getUsuarioid();
            case 1:
                return data.get(line).getNome();
            case 2:
                return data.get(line).getUsuario();
            case 3:
                return data.get(line).isIsAdmin();
            case 4:
                return data.get(line).isIsCaixa();
            case 5:
                return data.get(line).isIsSystem();
            case 6:
                return data.get(line).isIsFinanceiro();
        }
        return null;
    }
    
    @Override
    public Class<?> getColumnClass(int col) {
        switch (col) {
            case 0:
                return Integer.class;
            case 3:
            case 4:
            case 5:
            case 6:
                return Boolean.class;
            default:
                return String.class;
        }
    }
    
    @Override
    public boolean isCellEditable(int row, int col) {
        switch (col) {
            case 0:
            case 3:
                return false;
            default:
                return true;
        }
    }
    
    @Override
    public void setValueAt(Object v, int row, int col) {
        try {
            switch (col) {
                case 1:
                    data.get(row).setNome((String) v);
                    break;
                case 2:
                    data.get(row).setUsuario((String) v);
                    break;
                case 4:
                    data.get(row).setIsCaixa((boolean) v);
                    break;
                case 5:
                    data.get(row).setIsSystem((boolean) v);
                    break;
                case 6:
                    data.get(row).setIsFinanceiro((boolean) v);
                    break;

            }
        }
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    public void addRow(Usuario usuario) {
        data.add(usuario);
        int last = getRowCount() == 0 ? 0 : getRowCount() - 1;
        this.fireTableRowsInserted(last, last);
    }
    
    public Usuario getRow(int row) {
        return data.get(row);
    }
    
}
