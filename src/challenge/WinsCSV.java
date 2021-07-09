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
public class WinsCSV {

    private int id;
    private String name;
    private int timesFirst;
    private int timesSecond;
    private int timesThird;

    public WinsCSV() {
    }

    public WinsCSV(int id, String name, int timesFirst, int timesSecond, int timesThird) {
        this.id = id;
        this.name = name;
        this.timesFirst = timesFirst;
        this.timesSecond = timesSecond;
        this.timesThird = timesThird;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTimesFirst() {
        return timesFirst;
    }

    public void setTimesFirst(int timesFirst) {
        this.timesFirst = timesFirst;
    }

    public int getTimesSecond() {
        return timesSecond;
    }

    public void setTimesSecond(int timesSecond) {
        this.timesSecond = timesSecond;
    }

    public int getTimesThird() {
        return timesThird;
    }

    public void setTimesThird(int timesThird) {
        this.timesThird = timesThird;
    }
    
    public String[] getArray(){
        String[] datos = {String.valueOf(id), name, String.valueOf(timesFirst), String.valueOf(timesSecond), String.valueOf(timesThird)};
        return datos;
    }
    
    
}
