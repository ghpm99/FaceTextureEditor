/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.net.URL;
import model.FaceTexture;

/**
 *
 * @author ghpm9
 */
public class FaceTextureService {

    private FaceTexture[] facesTexture;

    public FaceTexture[] generateDefault() {
        FaceTexture[] defaultFaceTexture = new FaceTexture[28];
        for (int i = 0; i < defaultFaceTexture.length; i++) {
            defaultFaceTexture[i] = generateDefaultFaceTexture();
        }
        return defaultFaceTexture;
    }

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
        return facesTexture[index];
    }

    public FaceTexture[] getFaceTextures() {
        return facesTexture;
    }

    public void setFaceTexture(FaceTexture[] faceTexture) {
        this.facesTexture = faceTexture;
    }

}
