/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author myron
 */
public interface IEffectenBeurs extends Remote
{
    ArrayList<IFonds> getKoersen() throws RemoteException;
}
