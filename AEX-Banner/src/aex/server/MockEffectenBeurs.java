/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.server;

import aex.client.BannerController;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import aex.shared.Fonds;
import aex.shared.IEffectenBeurs;
import aex.shared.IFonds;

/**
 *
 * @author myron
 */
public class MockEffectenBeurs extends UnicastRemoteObject implements IEffectenBeurs
{
    ArrayList<IFonds> koersen;
    
    private Registry registry = null;

    public MockEffectenBeurs() throws RemoteException
    {
        koersen = new ArrayList<>();
        koersen.add(new Fonds("Unilever", 91.88));
        koersen.add(new Fonds("Shell", 7.81));
    }

    @Override
    public ArrayList<IFonds> getKoersen() throws RemoteException
    {
        genereerKoersen();
        return koersen;
    }

    public void genereerKoersen()
    {
        Random r = new Random();
        for (IFonds f : koersen)
        {
            double nieuweKoers = f.getKoers() * (0.95 + (1.05 - 0.95) * r.nextDouble());
            f.setKoers(nieuweKoers);
        }
    }
}
