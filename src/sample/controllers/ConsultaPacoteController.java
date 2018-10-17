package sample.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.*;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import static sample.Main.*;
import static sample.Main.HOSPEDAGEM;
import static sample.controllers.ClienteController.COM;

public class ConsultaPacoteController {

    @FXML
    TableView<Hotel> tabelaHospedagem;

    @FXML
    public TableColumn nomeColum, localColum, vagasColum, precoColum, ocupacaoColum;


    public TableView<Voo> tabelaIda, tabelaVolta;

    @FXML
    public TableColumn volta_nomeColum, volta_origemColum, volta_destinoColum, volta_precoColum, volta_capacidadeColum,
            volta_dataVoltaColum, volta_dataIdaColum, volta_vendidosColum, ida_nomeColum, ida_origemColum, ida_dataVoltaColum,
            ida_dataIdaColum, ida_destinoColum, ida_capacidadeColum, ida_precoColum, ida_vendidosColum;


    public void atualizar(ActionEvent actionEvent) {
        tabelaHospedagem.getItems().clear();
        nomeColum.setCellValueFactory(new PropertyValueFactory<>("nome"));
        localColum.setCellValueFactory(new PropertyValueFactory<>("local"));
        vagasColum.setCellValueFactory(new PropertyValueFactory<>("vagas"));
        precoColum.setCellValueFactory(new PropertyValueFactory<>("preco"));
        ocupacaoColum.setCellValueFactory(new PropertyValueFactory<>("ocupacao"));
        if (HOTEIS != null)
            tabelaHospedagem.getItems().addAll(HOTEIS);

        tabelaIda.getItems().clear();
        tabelaVolta.getItems().clear();
        preencheTabelaIda(IDA);
        preencheTabelaVolta(VOLTA);
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

    public void voltarTela(ActionEvent actionEvent) {
        Main.changeScreen("Back");
    }

    public void comprarPacote(ActionEvent actionEvent) throws RemoteException {
        Hotel hotel = tabelaHospedagem.getSelectionModel().getSelectedItem();
        Hospedagem hospedagem = new Hospedagem(hotel.getNome(),hotel.getLocal(),HOSPEDAGEM.getNumero_quartos(),HOSPEDAGEM.getNumero_pessoas());
        hospedagem.setData_entrada(HOSPEDAGEM.getData_entrada());
        hospedagem.setData_saida(HOSPEDAGEM.getData_saida());
        COM.comprarHospedagem(hospedagem);

        Voo v = tabelaIda.getSelectionModel().getSelectedItem();
        Voo v2 = tabelaVolta.getSelectionModel().getSelectedItem();
        List<Voo> voos = new ArrayList<>();
        voos.add(v);
        voos.add(v2);
        for (Voo voo:voos) {
            if(voo != null)
            {
                Passagem p = new Passagem();
                p.setVoo(voo);
                COM.comprarPassagem(p);
            }
        }

    }
}
