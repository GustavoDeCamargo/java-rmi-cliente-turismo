package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.rmi.CliImpl;
import sample.rmi.InterfaceServ;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("Cliente.fxml"));
        primaryStage.setTitle("RMI CLIENT World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        Registry rg = LocateRegistry.getRegistry(1099);

        CliImpl com = new CliImpl((InterfaceServ)rg.lookup("Servidor"));


        com.hello();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
