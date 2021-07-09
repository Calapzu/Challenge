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
public class Driver {
    
    private String name;

    public Driver() {
    }

    public Driver(String name) {
        this.name = name;
    }
    
    public String name(){
        return name;
    }
    
    public Integer trowDice(){
        int randomDice = (int)(Math.random()* 6 + 1);
        return randomDice;
    }
}
