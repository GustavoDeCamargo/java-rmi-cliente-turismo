package sample.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import sample.Main;


public class ConsultaVoosController {

    @FXML
    TableView tabelaIda;

    @FXML
    TableView tabelaVolta;

    @FXML
    JFXButton botaoComprarPassagem;

    public void comprarPassagem(ActionEvent actionEvent) {
    }

    public void voltarTela(ActionEvent actionEvent) {
        Main.changeScreen("Back");
    }
}
