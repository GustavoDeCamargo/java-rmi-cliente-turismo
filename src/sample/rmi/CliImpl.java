/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Assinado a dupla: Brenno, Gustavo e Mateus.
package sample.rmi;

import sample.*;

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
    public static String ID_NOME;

    public CliImpl(InterfaceServ serv)throws RemoteException {
        this.serv = serv;
    }
    
    
    public Retorno consultarServidor(String tipoConsulta,Passagem p, Hospedagem hospedagem) throws RemoteException, AlreadyBoundException, NotBoundException {
        return serv.consultar(tipoConsulta,p,hospedagem);
    }

    public void registrarInteresse(Interesse i) throws RemoteException, NotBoundException, AlreadyBoundException {
        ID_NOME = serv.registrarInteresse(i,this,ID_NOME);
    }

    @Override
    public void notificar(String str) throws RemoteException {
        // Você implementa esse método gus
        System.out.println("RECEBIDO NO CLIENTE " + str);
    }

    public List<String> getCidadesFromServer() throws RemoteException {
        return serv.GetCidades();
    }

    public void comprarPassagem(Passagem p) throws RemoteException {
        serv.comprarPassagem(p);
    }

    public void comprarHospedagem(Hospedagem h) throws RemoteException
    {
        serv.comprarHospedagem(h);
    }

    public List<Interesse> getInteressesCliente(String cliente) throws RemoteException {
        return serv.getInteresses(cliente);
    }

    public void deleteInteresse(Integer id) throws RemoteException
    {
        serv.deletarInteresse(id);
    }

    public List<String> getAllHoteis() throws RemoteException {
        return serv.getHoteis();
    }
}
