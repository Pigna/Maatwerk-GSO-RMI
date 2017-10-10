package aex.client;

import aex.shared.IEffectenBeurs;
import aex.shared.IFonds;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;
import java.util.TimerTask;

public class BannerController {

    private AEXBanner banner;
    private IEffectenBeurs effectenbeurs;
    private Timer pollingTimer;

    //RMI stuff
    private static final String bindingName = "AEXBanner";
    private Registry registry = null;
    
    public BannerController(AEXBanner banner)
    {
        ConnectToServer("145.93.133.133",1098);
        this.banner = banner;
        
        // Start polling timer: update banner every two seconds
        pollingTimer = new Timer();
        // TODO
        TimerTask tt = new TimerTask()
            {
                @Override
                public void run()
                {
                    StringBuilder sb = new StringBuilder();
                    try
                    {
                        for(IFonds fond : effectenbeurs.getKoersen())
                        {
                            sb.append(fond);
                        }
                    }
                    catch (RemoteException ex)
                    {
                        System.out.println("Error: Remote exception get koersen");
                    }
                    banner.setKoersen(sb.toString());
                }
            };
        //Timer, Delay, Repeat time
        pollingTimer.scheduleAtFixedRate(tt, 2000,2000);
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
                effectenbeurs = (IEffectenBeurs) registry.lookup(bindingName);
            }
            catch (RemoteException ex)
            {
                System.out.println("Client: Cannot bind Beurs");
                System.out.println("Client: RemoteException: " + ex.getMessage());
                effectenbeurs = null;
            }
            catch (NotBoundException ex)
            {
                System.out.println("Client: Cannot bind Beurs");
                System.out.println("Client: NotBoundException: " + ex.getMessage());
                effectenbeurs = null;
            }
        }
        if (effectenbeurs != null)
        {
            //testStudentAdministration();
            System.out.println("Effecten beurs is ready!");
        }
        else
        {
            System.out.println("Error: 'effectenbeurs' is null");
        }
    }
    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO
    }
}
