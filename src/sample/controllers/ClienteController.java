package sample.controllers;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import sample.*;
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
import static sample.Main.IDA;
import static sample.Main.VOLTA;
import static sample.Main.HOTEIS;
import static sample.Main.HOSPEDAGEM;
import static sample.rmi.CliImpl.ID_NOME;

public class ClienteController {

    @FXML
    JFXTextField p_num_pessoas,i_precoMaximo,h_num_quartos,h_num_pessoas;

    @FXML
    JFXDatePicker p_data_volta,p_data_ida,h_data_entrada,h_data_saida;

    @FXML
    ComboBox<String> p_origem,p_destino,h_cidade,i_origem,i_destino,h_hotel;

    @FXML
    CheckBox p_somente_ida,i_passagem,i_hospedagem,i_pacote;

    @FXML
    TableColumn i_origemColum,i_destinoColum,i_precoColum;

    @FXML
    TableView<Interesse> tabelaInteresse;

    public static CliImpl COM;

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


        Retorno r = COM.consultarServidor("Passagem",p,null);

        List<Voo> listaIda = new ArrayList<>();
        List<Voo> listaIdaVolta = new ArrayList<>();

        for (Voo voo:r.getVoos()) {
            // todos os voos retornados na consulta - 2 listas, voo de ida e de volta - printar na tabela
            // TODO LOGIC HERE TO DISPLAY

            if(p_somente_ida.isSelected() && data_volta == null) {
                listaIda.add(voo);
            }
            else{
                if(voo.getData_ida().equals(data_volta) || voo.getData_volta().equals(data_volta))
                {
                    listaIdaVolta.add(voo);
                }
                else if(voo.getData_ida().equals(v.getData_ida()) || voo.getData_volta().equals(v.getData_volta())){
                    listaIda.add(voo);
                }

            }
        }
        IDA = listaIda;
        VOLTA = listaIdaVolta;

    }
    public void consultarHospedagem() throws RemoteException, NotBoundException, AlreadyBoundException {
        String hotel = null;
        if(h_hotel.getValue().equals("Hotel"))
            hotel = "";
        else
            hotel = h_hotel.getValue();
        Hospedagem hospedagem = new Hospedagem(hotel,h_cidade.getValue(),Integer.parseInt(h_num_quartos.getText()),Integer.parseInt(h_num_pessoas.getText()));
        hospedagem.setData_entrada(h_data_entrada.getValue().format(ISO_LOCAL_DATE));
        hospedagem.setData_saida(h_data_saida.getValue().format(ISO_LOCAL_DATE));
        HOSPEDAGEM = hospedagem;
        HOTEIS = COM.consultarServidor("Hospedagem",null,hospedagem).getHoteis();
        Main.changeScreen("ConsultaHospedagem");
    }
    public void consultarPacote() throws RemoteException, NotBoundException, AlreadyBoundException{
        Hospedagem hospedagem = new Hospedagem("",h_cidade.getValue(),Integer.parseInt(h_num_quartos.getText()),Integer.parseInt(h_num_pessoas.getText()));
        hospedagem.setData_entrada(h_data_entrada.getValue().format(ISO_LOCAL_DATE));
        hospedagem.setData_saida(h_data_saida.getValue().format(ISO_LOCAL_DATE));
        HOSPEDAGEM = hospedagem;
        HOTEIS = COM.consultarServidor("Hospedagem",null,hospedagem).getHoteis();

        // Origem,Destino e data ida do Voo
        // Voo e numeroPessoas da Passagem
        Passagem p = new Passagem();
        String data_volta = null;
        if(!p_somente_ida.isSelected())
            data_volta = p_data_volta.getValue().format(ISO_LOCAL_DATE);

        Voo v = new Voo(null, p_origem.getValue(), p_destino.getValue(), null, p_data_ida.getValue().format(ISO_LOCAL_DATE), data_volta);
        p.setVoo(v);
        p.setNumero_pessoas(Integer.parseInt(p_num_pessoas.getText()));


        Retorno r = COM.consultarServidor("Passagem",p,null);

        List<Voo> listaIda = new ArrayList<>();
        List<Voo> listaIdaVolta = new ArrayList<>();

        for (Voo voo:r.getVoos()) {
            // todos os voos retornados na consulta - 2 listas, voo de ida e de volta - printar na tabela
            // TODO LOGIC HERE TO DISPLAY

            if(p_somente_ida.isSelected() && data_volta == null) {
                listaIda.add(voo);
            }
            else{
                if(voo.getData_ida().equals(data_volta) || voo.getData_volta().equals(data_volta))
                {
                    listaIdaVolta.add(voo);
                }
                else if(voo.getData_ida().equals(v.getData_ida()) || voo.getData_volta().equals(v.getData_volta())){
                    listaIda.add(voo);
                }

            }
        }
        IDA = listaIda;
        VOLTA = listaIdaVolta;

        Main.changeScreen("ConsultaPacote");
    }
    public void demonstrarInteresse() throws RemoteException, NotBoundException, AlreadyBoundException {
        Integer tipo_interesse;
        if (i_passagem.isSelected())
            tipo_interesse = 1;
        else if(i_hospedagem.isSelected())
            tipo_interesse = 2;
        else if(i_pacote.isSelected())
            tipo_interesse = 3;
        else
            tipo_interesse = 4;
        Interesse i = new Interesse(null,null,tipo_interesse,
                i_origem.getValue(),i_destino.getValue(),Double.parseDouble(i_precoMaximo.getText()));
        COM.registrarInteresse(i);

        atualizarInteresses();
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws RemoteException, NotBoundException {
        Registry rg = LocateRegistry.getRegistry(1099);
        COM = new CliImpl((InterfaceServ)rg.lookup("Servidor"));
        List<String> cidades = COM.getCidadesFromServer();
        p_origem.setItems(FXCollections.observableArrayList((cidades)));
        p_destino.setItems(FXCollections.observableArrayList((cidades)));
        h_cidade.setItems(FXCollections.observableArrayList((cidades)));
        i_origem.setItems(FXCollections.observableArrayList((cidades)));
        i_destino.setItems(FXCollections.observableArrayList((cidades)));
        h_hotel.setItems(FXCollections.observableArrayList(COM.getAllHoteis()));
    }

    public void setConsultaVoosController(ConsultaVoosController consultaVoosController) {
        this.consultaVoosController = consultaVoosController;
    }

    public void retirarInteresse(ActionEvent actionEvent) throws RemoteException {
       Interesse interesse =  tabelaInteresse.getSelectionModel().getSelectedItem();
       COM.deleteInteresse(interesse.getId());
       atualizarInteresses();
    }

    public void atualizarInteresses() throws RemoteException {
        tabelaInteresse.getItems().clear();
        List<Interesse> interesses = COM.getInteressesCliente(ID_NOME);
        i_origemColum.setCellValueFactory(new PropertyValueFactory<>("origem"));
        i_destinoColum.setCellValueFactory(new PropertyValueFactory<>("destino"));
        i_precoColum.setCellValueFactory(new PropertyValueFactory<>("preco_maximo"));
        tabelaInteresse.getItems().addAll(FXCollections.observableArrayList(interesses));
    }
}
