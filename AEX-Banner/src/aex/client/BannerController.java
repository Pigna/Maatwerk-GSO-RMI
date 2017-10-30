package aex.client;

import aex.shared.IEffectenBeurs;
import aex.shared.IFonds;
import fontyspublisher.IRemotePropertyListener;
import fontyspublisher.IRemotePublisherForListener;
import java.beans.PropertyChangeEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class BannerController extends UnicastRemoteObject implements IRemotePropertyListener{

    private AEXBanner banner;

    private Timer pollingTimer;

    //RMI stuff
    private static final String bindingName = "AEXBanner";
    private static final String propertyName = "EffectenBeurs";
    private Registry registry = null;
    
    private IRemotePublisherForListener remotePublisherForListener;
    
    public BannerController(AEXBanner banner) throws RemoteException
    {
        ConnectToServer("145.93.134.76",1098);
        this.banner = banner;
        
        // Start polling timer: update banner every two seconds
//        pollingTimer = new Timer();
//        TimerTask tt = new TimerTask()
//            {
//                @Override
//                public void run()
//                {
//                    StringBuilder sb = new StringBuilder();
//                    try
//                    {
//                        for(IFonds fond : effectenbeurs.getKoersen())
//                        {
//                            sb.append(fond);
//                        }
//                    }
//                    catch (RemoteException ex)
//                    {
//                        System.out.println("Error: Remote exception get koersen");
//                    }
//                    banner.setKoersen(sb.toString());
//                }
//            };
        //Timer, Delay, Repeat time
 //       pollingTimer.scheduleAtFixedRate(tt, 2000,2000);
    }

    private void ConnectToServer(String ip, int poort)
    {
        try
        {
            registry = LocateRegistry.getRegistry(ip, poort);
        }
        catch (RemoteException ex)
        {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: RemoteException: " + ex.getMessage());
            registry = null;
        }
        
        // Print result locating registry
        if (registry != null)
        {
            System.out.println("Client: Registry located");
        }
        else
        {
            System.out.println("Client: Cannot locate registry");
            System.out.println("Client: Registry is null pointer");
        }
        // Bind student administration using registry
        if (registry != null)
        {
            try
            {
                this.remotePublisherForListener = (IRemotePublisherForListener) registry.lookup(bindingName);
            }
            catch (RemoteException ex)
            {
                System.out.println("Client: Cannot bind listener");
                System.out.println("Client: RemoteException: " + ex.getMessage());
            }
            catch (NotBoundException ex)
            {
                System.out.println("Client: Cannot bind listener");
                System.out.println("Client: NotBoundException: " + ex.getMessage());
            }
        }
        try
        {
            remotePublisherForListener.subscribeRemoteListener(this, propertyName);
        }
        catch (RemoteException ex)
        {
            System.out.println("Client: Cannot bind property");
            System.out.println("Client: RemoteException: " + ex.getMessage());
        }
        
    }
    // Stop banner controller
    public void stop() {
        //pollingTimer.cancel();
    }
    private String koersToString(List<IFonds> fonds)
    {
        StringBuilder sb = new StringBuilder();
        for(IFonds fond : fonds)
        {
            sb.append(fond);
        }
        return sb.toString();
    }
    @Override
    public void propertyChange(PropertyChangeEvent pce) throws RemoteException {
        banner.setKoersen(koersToString((List<IFonds>) pce.getNewValue()));
    }
}
