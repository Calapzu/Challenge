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
public class Position implements Props{
    
    private Integer actual;
    private Integer finish;

    public Position(Integer actual, Integer finish) {
        this.actual = actual;
        this.finish = finish;
    }
    
    @Override
    public Integer actual(){
        return actual;
    }
    
    @Override
    public Integer finish(){
        return finish;
    }
    
    public void setActual(Integer actual){
        this.actual = actual;
    }
     
}

interface Props{
    public Integer actual();
    public Integer finish();
}
