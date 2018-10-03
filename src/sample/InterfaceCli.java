/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sample;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author a1834240
 */
public interface InterfaceCli extends Remote {
    
    public void echo(String str)throws RemoteException;
    
}

