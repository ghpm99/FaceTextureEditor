/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.FaceTexture;

/**
 *
 * @author ghpm9
 */
public class SaveService {

    private String saveFile;
    private String path = System.getProperty("java.io.tmpdir") + "/facetexture/";
    private Properties prop;

    public SaveService() {
        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File fileSave = new File(path + "facetexture.save");
        System.out.println("save exists: " + fileSave.exists() + " path:" + fileSave.getAbsolutePath());
        if (!fileSave.exists()) {
            try {
                fileSave.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(SaveService.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        saveFile = fileSave.getAbsolutePath();
        prop = new Properties();
    }

    public void save(ArrayList<FaceTexture> faceTexture) {
        try {
            prop.setProperty("length", String.valueOf(faceTexture.size()));
            Iterator ite = faceTexture.iterator();
            
            while(ite.hasNext()){
                FaceTexture temp = (FaceTexture) ite.next();
                int index = faceTexture.indexOf(temp);
                
                String pathImage = path + "save/" + index + ".bmp";
                saveImage(temp.getImage(), pathImage);

                prop.setProperty("name" + index, temp.getName());
                prop.setProperty("image" + index, pathImage);
                prop.setProperty("class" + index, temp.getClasse());
                prop.setProperty("show" + index, temp.isShowClasse() ? "0" : "1");
            }            
            

            prop.store(new FileOutputStream(saveFile), new Date().toString());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(SaveService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(SaveService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<FaceTexture> load() {        
        ArrayList<FaceTexture> faceTextures = new ArrayList<>();
        try {
            prop.load(new FileInputStream(saveFile));
            int length = Integer.valueOf(prop.getProperty("length"));
            

            for (int i = 0; i < length; i++) {
                FaceTexture temp = new FaceTexture();
                temp.setName(prop.getProperty("name" + i));
                temp.setClasse(prop.getProperty("class" + i));
                temp.setShowClasse(prop.getProperty("show" + i).equals("0"));

                temp.setImage(prop.getProperty("image" + i));

                faceTextures.add(temp);
            }

        } catch (Exception ex) {
            Logger.getLogger(SaveService.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }
        return faceTextures;
    }

    private void saveImage(BufferedImage image, String path) {
        try {
            File outputfile = new File(path);
            File parentFile = outputfile.getParentFile();
            if(!parentFile.exists()){
                parentFile.mkdirs();
            }
            if (!outputfile.exists()) {                
                outputfile.createNewFile();
            }
            ImageIO.write(image, "bmp", outputfile);

        } catch (IOException ex) {
            Logger.getLogger(SaveService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
