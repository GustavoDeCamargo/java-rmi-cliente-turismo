package sample.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Hospedagem;
import sample.Hotel;
import sample.Main;

import java.rmi.RemoteException;

import static sample.Main.HOSPEDAGEM;
import static sample.Main.HOTEIS;
import static sample.controllers.ClienteController.COM;

public class ConsultaHospedagemController {

    @FXML
    TableView<Hotel> tabelaHospedagem;

    @FXML
    public TableColumn nomeColum, localColum, vagasColum, precoColum, ocupacaoColum;

    @FXML
    JFXButton botaoComprarHospedagem;

    public void comprarHospedagem(ActionEvent actionEvent) throws RemoteException {
        Hotel hotel = tabelaHospedagem.getSelectionModel().getSelectedItem();
        Hospedagem hospedagem = new Hospedagem(hotel.getNome(),hotel.getLocal(),HOSPEDAGEM.getNumero_quartos(),HOSPEDAGEM.getNumero_pessoas());
        hospedagem.setData_entrada(HOSPEDAGEM.getData_entrada());
        hospedagem.setData_saida(HOSPEDAGEM.getData_saida());
        COM.comprarHospedagem(hospedagem);

    }

    public void voltarTela(ActionEvent actionEvent) {
        Main.changeScreen("Back");
    }

    public void atualizar(ActionEvent actionEvent) {
        tabelaHospedagem.getItems().clear();
        nomeColum.setCellValueFactory(new PropertyValueFactory<>("nome"));
        localColum.setCellValueFactory(new PropertyValueFactory<>("local"));
        vagasColum.setCellValueFactory(new PropertyValueFactory<>("vagas"));
        precoColum.setCellValueFactory(new PropertyValueFactory<>("preco"));
        ocupacaoColum.setCellValueFactory(new PropertyValueFactory<>("ocupacao"));
        if (HOTEIS != null)
        tabelaHospedagem.getItems().addAll(HOTEIS);
    }
}
