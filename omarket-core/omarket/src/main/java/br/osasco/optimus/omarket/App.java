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
    public static long ADMID = 0;
    public static boolean ISADM = false;
    public static boolean ISCAIXA = false;
    public static boolean ISFINANCEIRO = false;
    public static boolean ISSISTEMA = false;

    private static final String dbuser = "omarket";
    private static final String dbpass = "";
    private static final String dbname = "o_market";
    private static final String driver = "mariadb";
    private static final String dbhost = "localhost";
    private static final int dbport = 3306;
    
    public static void main(String[] args) {
        if (dbConnect()) {
            launch(args);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        ROOT = stage;
        Parent root = FXMLLoader.load(this.getClass().getResource("mainframe.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        showLogin();
        if (USUARIOID == 0 && ADMID == 0) {
            System.exit(0);
        }
        stage.show();
    }

    private static boolean dbConnect() {
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