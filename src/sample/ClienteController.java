package sample;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.SplitMenuButton;
import sample.rmi.CliImpl;
import sample.rmi.InterfaceServ;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;

public class ClienteController {

    @FXML
    JFXTextField num_pessoas;

    @FXML
    JFXDatePicker p_data_volta,p_data_ida;

    @FXML
    SplitMenuButton p_origem,p_destino;

    @FXML
    CheckBox p_somente_ida;

    CliImpl com;

    public void consultarPassagem() throws RemoteException, NotBoundException, AlreadyBoundException {
        Passagem p = new Passagem();
        String data_volta = null;
        if(!p_somente_ida.isSelected())
            data_volta = p_data_volta.getValue().format(ISO_LOCAL_DATE);
        Voo v = new Voo(null, p_origem.getText(), p_destino.getText(), null, p_data_ida.getValue().format(ISO_LOCAL_DATE), data_volta);
        p.setVoo(v);
        p.setNumero_pessoas(Integer.parseInt(num_pessoas.getText()));


        com.consultarServidor("Voos",p);

    }
    public void consultarHospedagem(){

    }
    public void consultarPacote(){

    }
    public void demonstrarInteresse(){

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws RemoteException, NotBoundException {
        Registry rg = LocateRegistry.getRegistry(1099);
        com = new CliImpl((InterfaceServ)rg.lookup("Servidor"));
        p_origem.
    }
}
