package br.osasco.optimus.omarket;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainFrame implements Initializable {

    @Override
	public void initialize(URL url, ResourceBundle rb) {
    }
    
    private void showLogin() {
        try {
            Parent root = FXMLLoader.load(this.getClass().getResource("loginframe.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setScene(scene);
            stage.initOwner(App.ROOT);
            stage.showAndWait();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
}