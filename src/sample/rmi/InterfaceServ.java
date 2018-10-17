/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.rmi;

import sample.Interesse;
import sample.Retorno;
import sample.Passagem;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 *
 * @author a1834240
 */
public interface InterfaceServ extends Remote  {


    void registrarInteresse(Interesse interesse, InterfaceCli cli) throws RemoteException, AlreadyBoundException, NotBoundException;

    public Retorno consultar(String tipoConsulta,Passagem passagem) throws RemoteException;

    List<String> GetCidades() throws RemoteException;

}
