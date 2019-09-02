package br.osasco.optimus.omarket;

import java.net.URL;
import java.util.ResourceBundle;

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


    @Override
	public void initialize(URL url, ResourceBundle rb) {
		
    }
    
    @FXML
    void login(ActionEvent e) {

    }

    @FXML
    void close(ActionEvent e) {
        Window window = ((Button) e.getSource()).getScene().getWindow();
        ((Stage) window).close();
    }
    
}