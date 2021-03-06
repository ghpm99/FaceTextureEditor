/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ghpm9
 */
public class FaceTexture {    
    
    private String name;
    private BufferedImage image;
    private int order;
    private String classe;
    private boolean showClasse;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getImage() {
        return image;
    }
    
    public BufferedImage getImage(int width, int height){
        return resizeImage(image, width, height);
    }

    public void setImage(BufferedImage image) {        
        this.image = resizeImage(image, 624, 804);
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public boolean isShowClasse() {
        return showClasse;
    }

    public void setShowClasse(boolean showClasse) {
        this.showClasse = showClasse;
    }
    
    
    
    public void setImage(String path){
        try {
            File filePath = new File(path);
            System.out.println("filepath: " + filePath.getAbsolutePath());
            BufferedImage temp = ImageIO.read(filePath);            
            setImage(temp);
            
        } catch (IOException ex) {
            Logger.getLogger(FaceTexture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setImage(URL path){
        try {
            
            System.out.println("filepath: " + path.getFile());
            BufferedImage temp = ImageIO.read(path);            
            setImage(temp);
            
        } catch (IOException ex) {
            Logger.getLogger(FaceTexture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private BufferedImage resizeImage(BufferedImage image, int width, int height){
                
        BufferedImage after = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        
        Graphics2D g2d = after.createGraphics();
        g2d.drawImage(image, 0, 0,width,height,null);
        g2d.dispose();
        
        return after;
    }
    
}
