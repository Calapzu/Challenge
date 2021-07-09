package challenge;


import challenge.Player;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Acer
 */
public class Podium {

    private Player firstPlace;
    private Player secondPlace;
    private Player thirdPlace;

    public Podium() {
    }

    public void addFirstPlace(Player player) {
        firstPlace = player;
    }

    public void addSecondPlace(Player player) {
        secondPlace = player;
    }
    
    public void addThirdPlace(Player player){
        thirdPlace = player;
    }
    
      public Player firstPlace() {
        return firstPlace;
    }

    public Player secondPlace() {
        return secondPlace;
    }

    public Player thirdPlace() {
        return thirdPlace;
    }
    
    public Boolean isFull(){
        Boolean full = false;
        if (firstPlace !=  null && secondPlace != null && thirdPlace != null) {
            full = true;
        }
        return full;
    }

}
