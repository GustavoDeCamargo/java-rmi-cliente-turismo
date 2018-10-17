package sample.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import sample.Main;

public class ConsultaHospedagemController {

    @FXML
    TableView tabelaHospedagem;

    @FXML
    JFXButton botaoComprarHospedagem;

    public void comprarHospedagem(ActionEvent actionEvent) {
    }

    public void voltarTela(ActionEvent actionEvent) {
        Main.changeScreen("Back");
    }
}
