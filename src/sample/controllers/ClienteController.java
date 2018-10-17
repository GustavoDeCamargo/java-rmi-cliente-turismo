package sample.controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SplitMenuButton;
import sample.*;
import sample.rmi.CliImpl;
import sample.rmi.InterfaceServ;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class ClienteController {

    @FXML
    JFXTextField p_num_pessoas,i_precoMaximo;

    @FXML
    JFXDatePicker p_data_volta,p_data_ida;

    @FXML
    ComboBox<String> p_origem,p_destino,h_cidade,i_origem,i_destino;

    @FXML
    CheckBox p_somente_ida;

    CliImpl com;

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

        for (Voo voo:r.getVoos()) {
            // todos os voos retornados na consulta
            // TODO LOGIC HERE TO DISPLAY
            System.out.println(voo.getNome());
        }

    }
    public void consultarHospedagem(){
        Main.changeScreen("ConsultaHospedagem");
    }
    public void consultarPacote(){

    }
    public void demonstrarInteresse() throws RemoteException, NotBoundException, AlreadyBoundException {
        Interesse i = new Interesse(null,null,1,
                i_origem.getValue(),i_destino.getValue(),Double.parseDouble(i_precoMaximo.getText()));
         com.registrarInteresse(i);
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
