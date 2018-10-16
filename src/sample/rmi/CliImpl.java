/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Assinado a dupla: Brenno, Gustavo e Mateus.
package sample.rmi;

import sample.Passagem;
import sample.Retorno;
import sample.Voo;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author a1834240
 */
public class CliImpl extends UnicastRemoteObject implements InterfaceCli {
    InterfaceServ serv;

    public CliImpl(InterfaceServ serv)throws RemoteException {
        this.serv = serv;
    }
    
    
    public void consultarServidor(String tipoConsulta) throws RemoteException, AlreadyBoundException, NotBoundException {
        Passagem p = new Passagem();
        Voo v = new Voo(null, "São Paulo", "Curitiba", null, "2018-10-31", null);

        Retorno r =  serv.consultar(tipoConsulta,null);
        System.out.println(r.getVoos().get(1).getNome());
    }

    @Override
    public void notificar(String str) throws RemoteException {
        // Você implementa esse método gus
        System.out.println("RECEBIDO NO CLIENTE " + str);
    }
}
