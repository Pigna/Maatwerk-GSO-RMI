/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.client;

import aex.shared.IFonds;
import java.beans.PropertyChangeEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author myron
 */
interface IBanner {
    public void setKoersen(ArrayList<IFonds> fondsen);
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) throws RemoteException ;
}
