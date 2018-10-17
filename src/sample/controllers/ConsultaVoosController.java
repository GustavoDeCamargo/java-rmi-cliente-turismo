package sample.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.Voo;

import java.util.List;

import static sample.Main.IDA;
import static sample.Main.VOLTA;


public class ConsultaVoosController {

    @FXML
    public TableView tabelaIda, tabelaVolta;

    @FXML
    public TableColumn volta_nomeColum, volta_origemColum, volta_destinoColum, volta_precoColum, volta_capacidadeColum,
            volta_dataVoltaColum, volta_dataIdaColum, volta_vendidosColum, ida_nomeColum, ida_origemColum, ida_dataVoltaColum,
            ida_dataIdaColum, ida_destinoColum, ida_capacidadeColum, ida_precoColum, ida_vendidosColum;

    @FXML
    JFXButton botaoComprarPassagem;

    public void comprarPassagem(ActionEvent actionEvent) {
    }

    public void voltarTela(ActionEvent actionEvent) {
        Main.changeScreen("Back");
    }

    public void preencheTabelaIda(List<Voo> voos){
        ida_nomeColum.setCellValueFactory(new PropertyValueFactory<>("nome"));
        ida_origemColum.setCellValueFactory(new PropertyValueFactory<>("origem"));
        ida_destinoColum.setCellValueFactory(new PropertyValueFactory<>("destino"));
        ida_dataIdaColum.setCellValueFactory(new PropertyValueFactory<>("data_ida"));
        ida_dataVoltaColum.setCellValueFactory(new PropertyValueFactory<>("data_volta"));
        ida_capacidadeColum.setCellValueFactory(new PropertyValueFactory<>("capacidade"));
        ida_vendidosColum.setCellValueFactory(new PropertyValueFactory<>("vendidos"));
        ida_precoColum.setCellValueFactory(new PropertyValueFactory<>("preco"));

        tabelaIda.getItems().addAll(FXCollections.observableArrayList(voos));
    }

    public void preencheTabelaVolta(List<Voo> voos){
        volta_nomeColum.setCellValueFactory(new PropertyValueFactory<>("nome"));
        volta_origemColum.setCellValueFactory(new PropertyValueFactory<>("origem"));
        volta_destinoColum.setCellValueFactory(new PropertyValueFactory<>("destino"));
        volta_dataIdaColum.setCellValueFactory(new PropertyValueFactory<>("data_ida"));
        volta_dataVoltaColum.setCellValueFactory(new PropertyValueFactory<>("data_volta"));
        volta_capacidadeColum.setCellValueFactory(new PropertyValueFactory<>("capacidade"));
        volta_vendidosColum.setCellValueFactory(new PropertyValueFactory<>("vendidos"));
        volta_precoColum.setCellValueFactory(new PropertyValueFactory<>("preco"));

        tabelaVolta.getItems().addAll(FXCollections.observableArrayList(voos));
    }

    public void realizarConsulta(ActionEvent actionEvent) {
        preencheTabelaIda(IDA);
        preencheTabelaVolta(VOLTA);
    }
}
