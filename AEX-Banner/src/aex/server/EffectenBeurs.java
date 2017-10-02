/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.server;

import aex.client.BannerController;
import java.util.ArrayList;
import aex.shared.IEffectenBeurs;
import aex.shared.IFonds;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author myron
 */
public class EffectenBeurs extends UnicastRemoteObject implements IEffectenBeurs
{
    ArrayList<IFonds> koersen;

    // Constructor
    public EffectenBeurs() throws RemoteException {
        
    }
    
    @Override
    public ArrayList<IFonds> getKoersen() throws RemoteException
    {
        return koersen;
        //  Door dit naar return null te veranderen worden de koersgegevens niet dubbel weergegeven.
        //        return null;
    }
}
