/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.net.URL;
import model.Background;

/**
 *
 * @author ghpm9
 */
public class BackgroundService {
    
    private Background background; 
    
    public BackgroundService(){
        background = new Background();
        URL pathDefault = this.getClass().getResource("images/backgroundDefault.jpg");
        System.out.println("background:" + pathDefault);
        background.setImage(pathDefault);
    }
    
    public Background getBackground(){
        return background;
    }
    
    
    
}
