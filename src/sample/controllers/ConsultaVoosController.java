package sample.controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;


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
}
