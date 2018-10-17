/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Assinado a dupla: Brenno, Gustavo e Mateus.
package sample.rmi;

import sample.Interesse;
import sample.Passagem;
import sample.Retorno;
import sample.Voo;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 *
 * @author a1834240
 */
public class CliImpl extends UnicastRemoteObject implements InterfaceCli {
    InterfaceServ serv;

    public CliImpl(InterfaceServ serv)throws RemoteException {
        this.serv = serv;
    }
    
    
    public Retorno consultarServidor(String tipoConsulta,Passagem p) throws RemoteException, AlreadyBoundException, NotBoundException {
        return serv.consultar(tipoConsulta,p);
    }

    public void registrarInteresse(Interesse i) throws RemoteException, NotBoundException, AlreadyBoundException {
        serv.registrarInteresse(i,this);
    }

    @Override
    public void notificar(String str) throws RemoteException {
        // Você implementa esse método gus
        System.out.println("RECEBIDO NO CLIENTE " + str);
    }

    public List<String> getCidadesFromServer() throws RemoteException {
        return serv.GetCidades();
    }
}
