/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aex.shared;

import java.io.Serializable;

/**
 *
 * @author myron
 */
public interface IFonds extends Serializable{
    public String getNaam();
    public double getKoers();
    public void setKoers(Double nieuweKoers);
}
