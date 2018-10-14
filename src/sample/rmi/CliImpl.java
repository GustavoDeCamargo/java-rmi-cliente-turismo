/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Assinado a dupla: Brenno, Gustavo e Mateus.
package sample.rmi;

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
    
    
    public void hello() throws RemoteException, AlreadyBoundException, NotBoundException {
        serv.registrarInteresse("São Paulo", this);
    }

    @Override
    public void notificar(String str) throws RemoteException {
        // Você implementa esse método gus
        System.out.println("RECEBIDO NO CLIENTE " + str);
    }
}
