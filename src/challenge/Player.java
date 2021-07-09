/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge;

import java.awt.Color;

/**
 *
 * @author Acer
 */
public class Player {
    
    private Name name;
    private Color colour;
    private Integer points;

    public Player(Name name, Color colour, Integer points) {
        this.name = name;
        this.colour = colour;
        this.points = points;
    }
    
    public Name name(){
        return name;
    }
    
    public Color colour(){
        return colour;
    }
    
    public void addPoints(Integer points){
        points= points;
    }
    
    
}
