/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Acer
 */
public class Car {
    
    protected Driver driver;
    protected Integer distance;
    protected Color colour;
    protected PlayId playId;
    private final Map<CarId, Driver> cars = new HashMap<>();

    public Car() {
    }

    public Car(Driver driver, Integer distance, Color colour, PlayId playId) {
        this.driver = driver;
        this.distance = distance;
        this.colour = colour;
        this.playId = playId;
    }
    
    public void addDriver(CarId carId, Driver driver){
        cars.put(carId, driver);
    }
    
    public Map<CarId, Driver> cars(){
        return cars;
    }
    
    public void setDistance(Integer distance){
        this.distance = distance;
    }
    
    public Integer numbersCars(){
        return cars.size();
    }
    
    public Driver driver(){
        return driver;
    }
    
    public Color colour(){
        return colour;
    }
    
    public Integer distance() {
        return distance;
    }

   
    
}
