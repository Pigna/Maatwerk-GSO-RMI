/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.shared;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 *
 * @author myron
 */
public class Fonds implements IFonds {
    String naam;
    Double koers;

    public Fonds (String naam, Double koers)
    {
        this.naam = naam;
        this.koers = koers;
    }

    @Override
    public String getNaam()
    {
        return naam;
    }

    @Override
    public double getKoers()
    {
        return koers;
    }

    @Override
    public void setKoers(Double nieuweKoers)
    {
        this.koers = nieuweKoers;
    } 

    @Override
    public String toString() {
        return naam + " " + round(koers) + "  ";
        //return naam + " " + String.format("%.2f", round(koers)) + "  ";
    }
    public static String round(double value)
    {
        DecimalFormat df = new DecimalFormat("#.00"); 
        return df.format(value);
        //return Math.round(value * 100D) / 100D;
        
    }
    
    public static double round(double value, int places)
    {
        if (places < 0) throw new IllegalArgumentException();
        
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
}
