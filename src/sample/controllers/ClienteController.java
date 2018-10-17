package sample.controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Main;
import sample.Passagem;
import sample.Retorno;
import sample.Voo;
import sample.rmi.CliImpl;
import sample.rmi.InterfaceServ;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class ClienteController {

    @FXML
    JFXTextField p_num_pessoas;

    @FXML
    JFXDatePicker p_data_volta,p_data_ida;

    @FXML
    ComboBox<String> p_origem,p_destino,h_cidade,i_origem,i_destino;

    @FXML
    CheckBox p_somente_ida;

    CliImpl com;

    @FXML
    ConsultaVoosController consultaVoosController;

    public void consultarPassagem() throws RemoteException, NotBoundException, AlreadyBoundException {
        Main.changeScreen("ConsultaVoos");

        // Origem,Destino e data ida do Voo
        // Voo e numeroPessoas da Passagem
        Passagem p = new Passagem();
        String data_volta = null;
        if(!p_somente_ida.isSelected())
            data_volta = p_data_volta.getValue().format(ISO_LOCAL_DATE);

        Voo v = new Voo(null, p_origem.getValue(), p_destino.getValue(), null, p_data_ida.getValue().format(ISO_LOCAL_DATE), data_volta);
        p.setVoo(v);
        p.setNumero_pessoas(Integer.parseInt(p_num_pessoas.getText()));


        Retorno r = com.consultarServidor("Passagem",p);

        List<Voo> listaIda = new ArrayList<>();
        List<Voo> listaIdaVolta = new ArrayList<>();

        for (Voo voo:r.getVoos()) {
            // todos os voos retornados na consulta - 2 listas, voo de ida e de volta - printar na tabela
            // TODO LOGIC HERE TO DISPLAY

            if(p_somente_ida.isSelected() && data_volta == null) {
                listaIda.add(voo);
            }
            else{
                listaIdaVolta.add(voo);
            }

            System.out.println(voo.getNome());
            System.out.println(voo.getOrigem());
            System.out.println(voo.getDestino());
            System.out.println(voo.getData_ida());
            System.out.println(voo.getData_volta());
            System.out.println(voo.getCapacidade());
        }
        consultaVoosController.preencheTabelaIda(listaIda);
        consultaVoosController.preencheTabelaVolta(listaIdaVolta);

    }
    public void consultarHospedagem(){
        Main.changeScreen("ConsultaHospedagem");
    }
    public void consultarPacote(){

    }
    public void demonstrarInteresse(){

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws RemoteException, NotBoundException {
        Registry rg = LocateRegistry.getRegistry(1099);
        com = new CliImpl((InterfaceServ)rg.lookup("Servidor"));
        List<String> cidades = com.getCidadesFromServer();
        p_origem.setItems(FXCollections.observableArrayList((cidades)));
        p_destino.setItems(FXCollections.observableArrayList((cidades)));
        h_cidade.setItems(FXCollections.observableArrayList((cidades)));
        i_origem.setItems(FXCollections.observableArrayList((cidades)));
        i_destino.setItems(FXCollections.observableArrayList((cidades)));
    }
}
