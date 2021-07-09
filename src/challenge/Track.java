/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge;

/**
 *
 * @author Acer
 */
public class Track {
    
    private Integer km;
    private Integer laneNumbers;

    public Track(Integer km, Integer laneNumbers) {
        this.km = km;
        this.laneNumbers = laneNumbers;
    }
    
    public Integer km(){
        return km;
    }
    
    public Integer laneNumers(){
        return laneNumbers;
    }
}
