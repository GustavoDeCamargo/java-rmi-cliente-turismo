package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controllers.ConsultaHospedagemController;

import java.util.List;

public class Main extends Application {

    private static Stage stage;

    private static Scene Cliente;
    private static Scene ConsultaHospedagem;
    private static Scene ConsultaVoos;
    private static Scene ConsultaPacote;

    private static Scene sample;

    public static List<Voo> IDA;
    public static List<Voo> VOLTA;
    public static List<Hotel> HOTEIS;

    public static Hospedagem HOSPEDAGEM;


    @Override
    public void start(Stage primaryStage) throws Exception{

        stage = primaryStage;

        Parent fxmlConsultaVoos = FXMLLoader.load(getClass().getResource("resources/ConsultaVoos.fxml"));

        ConsultaVoos = new Scene(fxmlConsultaVoos);

        Parent fxmlCliente = FXMLLoader.load(getClass().getResource("resources/Cliente.fxml"));
        Cliente = new Scene(fxmlCliente);

        Parent fxmlConsultaHospedagem = FXMLLoader.load(getClass().getResource("resources/ConsultaHospedagem.fxml"));
        ConsultaHospedagem = new Scene(fxmlConsultaHospedagem);

        Parent fxmlConsultaPacote = FXMLLoader.load(getClass().getResource("resources/ConsultaPacote.fxml"));
        ConsultaPacote = new Scene(fxmlConsultaPacote);

        primaryStage.setTitle("Java RMI Cliente");
        primaryStage.setScene(Cliente);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    public static void changeScreen(String scr) {
        switch (scr) {
            case "ConsultaHospedagem":
                stage.setScene(ConsultaHospedagem);
                break;
            case "ConsultaVoos":
                stage.setScene(ConsultaVoos);
                break;
            case "ConsultaPacote":
                stage.setScene(ConsultaPacote);
                break;
            case "Back":
                stage.setScene(Cliente);
                break;
        }
    }
}
