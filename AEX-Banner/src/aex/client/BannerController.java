package aex.client;

import aex.server.MockEffectenBeurs;
import aex.shared.IEffectenBeurs;
import aex.shared.IFonds;
import java.rmi.RemoteException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BannerController {

    private AEXBanner banner;
    private IEffectenBeurs effectenbeurs;
    private Timer pollingTimer;

    public BannerController(AEXBanner banner)
    {
        this.banner = banner;
        try
        {
            effectenbeurs = new MockEffectenBeurs();
        }
        catch (RemoteException ex)
        {
            System.out.println("ERROR: Remote Exception creating MockEfectenBeurs");
        }
        
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
        pollingTimer.scheduleAtFixedRate(tt, 1000,2000);
    }

    // Stop banner controller
    public void stop() {
        pollingTimer.cancel();
        // Stop simulation timer of effectenbeurs
        // TODO
    }
}
