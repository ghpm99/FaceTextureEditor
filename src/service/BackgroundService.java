/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.Background;

/**
 *
 * @author ghpm9
 */
public class BackgroundService {
    
    private Background background;
    
    private String pathDefault = "E:\\Programacao\\FaceTextureEditor\\backgroundDefault.jpg";
    
    public BackgroundService(){
        background = new Background();
        background.setImage(pathDefault);
    }
    
    public Background getBackground(){
        return background;
    }
    
    
    
}
