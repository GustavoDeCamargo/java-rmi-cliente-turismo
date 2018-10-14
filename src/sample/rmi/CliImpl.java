/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//Assinado a dupla: Brenno, Gustavo e Mateus.
package sample.rmi;

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
    
    
    public void hello()throws RemoteException{
        // Registrar interesse
        serv.registrarInteresse("FDP", this);
    }
    
    @Override
    public void echo(String str) {
        System.out.println("Recebido: " + str);
    }
    
}
