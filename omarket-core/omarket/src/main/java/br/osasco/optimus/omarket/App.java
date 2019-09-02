package br.osasco.optimus.omarket;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application {
    public static Stage ROOT;
    public static Connection DBH;
    public static long USUARIOID = 0;
    public static boolean ISADM = false;
    public static boolean ISCAIXA = false;
    public static boolean ISFINANCEIRO = false;
    public static boolean ISSISTEMA = false;

    private final String dbuser = "omarket";
    private final String dbpass = "";
    private final String dbname = "o_market";
    private final String driver = "mariadb";
    private final String dbhost = "localhost";
    private final int dbport = 3306;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        ROOT = stage;
        Parent root = FXMLLoader.load(this.getClass().getResource("mainframe.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        if (dbConnect()) {
            showLogin();
            stage.show();
        }
    }

    private boolean dbConnect() {
        String url = String.format("jdbc:%s://%s:%d/%s", driver, dbhost, dbport, dbname);
        try {
            DBH = DriverManager.getConnection(url, dbuser, dbpass);
            return true;
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
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