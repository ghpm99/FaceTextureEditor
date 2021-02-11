/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.net.URL;
import java.util.ArrayList;
import model.FaceTexture;

/**
 *
 * @author ghpm9
 */
public class FaceTextureService {

    private ArrayList<FaceTexture> facesTexture = new ArrayList<>();   

    private FaceTexture generateDefaultFaceTexture() {
        
        URL defaultImage = this.getClass().getResource("images/vazio.png");
        
        FaceTexture defaultFaceTexture = new FaceTexture();

        defaultFaceTexture.setName("null");
        defaultFaceTexture.setClasse("Null");
        System.out.println(defaultImage);
        defaultFaceTexture.setImage(defaultImage);

        return defaultFaceTexture;
    }

    public FaceTexture getFaceTexture(int index) {
        return facesTexture.get(index);
    }

    public ArrayList<FaceTexture> getFaceTextures() {
        return facesTexture;
    }

    public void setFaceTexture(ArrayList<FaceTexture> faceTexture) {
        this.facesTexture = faceTexture;
    }

    public void addFaceTexture(FaceTexture faceTexture){
        facesTexture.add(faceTexture);
    }
    
}
