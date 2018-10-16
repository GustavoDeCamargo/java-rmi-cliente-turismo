/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.rmi;

import sample.Retorno;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author a1834240
 */
public interface InterfaceServ extends Remote  {


    public void registrarInteresse(String string,  InterfaceCli cli) throws RemoteException, AlreadyBoundException, NotBoundException;

    public Retorno consultar() throws RemoteException;
}
