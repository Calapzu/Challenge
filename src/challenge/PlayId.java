/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package challenge;

import java.util.UUID;

/**
 *
 * @author Acer
 */
public class PlayId {
    
    private UUID id;

    public PlayId(UUID id) {
        this.id = id;
    }

    public String getId() {
        return id.toString();
    }

    public void setId(UUID id) {
        this.id = id;
    }
    
    
    
    
}
