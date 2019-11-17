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
package br.osasco.optimus.omarket.produtos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author darkx
 */
public class Nomes {
    private final Connection dbh;
    
    public Nomes(Connection dbh) {
        this.dbh = dbh;
    }
    
    public List<String> getAll() throws SQLException {
        List<String> list = new ArrayList<>();
        
        String sql = "select nome from produtos_nome order by nome";
        Statement stmt = dbh.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            list.add(rs.getString(1));
        }
        return list;
    }
    
    public String[] getArray() throws SQLException {
        List<String> list = this.getAll();
        String[] items = new String[list.size()];
        items = list.toArray(items);
        return items;
    }
}
