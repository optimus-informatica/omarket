package br.osasco.optimus.omarket;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import br.osasco.optimus.omarket.usuarios.Usuarios;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

public class LoginFrame implements Initializable {
    @FXML
    TextField fieldUser;
    @FXML
    PasswordField fieldPass;
    @FXML
    Label labelLog;

    private final Usuarios usuarios = new Usuarios(App.DBH);


    @Override
	public void initialize(URL url, ResourceBundle rb) {
		
    }
    
    @FXML
    void login(ActionEvent e) {
        String usuario = fieldUser.getText();
        String senha = fieldPass.getText();
        try {
            ObservableList<Object> data = usuarios.logon(usuario, senha);
            System.out.println(usuario);
            System.out.println(senha);
            if ((long)data.get(0) != 0) {
                if ((boolean) data.get(1)) {
                    App.ADMID = (long)data.get(0);
                }
                else {
                    App.USUARIOID = (long)data.get(0);
                }
                App.ISADM = (boolean)data.get(1);
                App.ISCAIXA = (boolean)data.get(2);
                App.ISSISTEMA = (boolean)data.get(3);
                App.ISFINANCEIRO = (boolean)data.get(4);
                close(e);
            }
            else {
                labelLog.setText("Usuário ou senha inválidos.");
            }
        }
        catch (SQLException ex) {
            labelLog.setText(ex.getMessage());
        }

    }

    @FXML
    void close(ActionEvent e) {
        Window window = ((Button) e.getSource()).getScene().getWindow();
        ((Stage) window).close();
    }
    
}