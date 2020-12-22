/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.FaceTexture;

/**
 *
 * @author ghpm9
 */
public class FaceTextureService {
    
    private FaceTexture[] facesTexture = new FaceTexture[28];
    
    public FaceTextureService(){
        for(int i =0; i < facesTexture.length; i++){
            facesTexture[i] = generateDefaultFaceTexture();
        }
        
        
    }      
    
    private FaceTexture generateDefaultFaceTexture(){
        FaceTexture defaultFaceTexture = new FaceTexture();
        defaultFaceTexture.setName("null");
        defaultFaceTexture.setImage(FaceTexture.DEFAULTIMAGE);
        defaultFaceTexture.setClasse("null");
        return defaultFaceTexture;
    }
    
    public FaceTexture getFaceTexture(int index){
        return facesTexture[index];
    }
    
    
    
}
