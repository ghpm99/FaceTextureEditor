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
public class Background {

    private BufferedImage background;
    private int x = 0, y = 0,widthScaled,heightScale;
    private double scale = 1.0, minScale;
    private final int MAXWIDTH = 905;
    private final int MAXHEIGHT = 655;
    

    public BufferedImage getBackground() {

        widthScaled = (int) (((double) background.getWidth()) * scale);
        heightScale = (int) (((double) background.getHeight()) * scale);
        
        BufferedImage backgroundScaled;

        if (widthScaled > MAXWIDTH && heightScale > MAXHEIGHT) {
            backgroundScaled = resizeImage(background, widthScaled, heightScale);            
        } else {
            backgroundScaled = resizeImage(background, MAXWIDTH, MAXHEIGHT);
        }       

        BufferedImage image =getSubImage(backgroundScaled, x, y, MAXWIDTH, MAXHEIGHT); 

        return image;

    }

    private void setBackground(BufferedImage background) {
        this.x = 0;
        this.y = 0;
        this.scale = 1.0;        
        this.minScale = getMinScale(background.getWidth(), background.getHeight());
        this.background = background;
        
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setImage(String path) {
        try {
            File filePath = new File(path);
            BufferedImage temp = ImageIO.read(filePath);
            setBackground(temp);

        } catch (IOException ex) {
            Logger.getLogger(FaceTexture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setImage(URL path){
        try {            
            BufferedImage temp = ImageIO.read(path);
            setBackground(temp);
        } catch (IOException ex) {
            Logger.getLogger(FaceTexture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private BufferedImage resizeImage(BufferedImage image, int width, int height) {

        BufferedImage after = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = after.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();

        return after;
    }

    public void moveX(int x) {
        int newX = this.x + x;
        
        if ((newX + MAXWIDTH) < widthScaled && newX > 0) {
            this.x = newX;
        }
    }

    public void moveY(int y) {
        int newY = this.y + y;
        
        if ((newY + MAXHEIGHT) < heightScale && newY > 0) {
            this.y = newY;
        }
    }

    public void setScale(double scale) {
        double newScale = scale / 10;
        double soma = this.scale + newScale;
        
        if(soma < minScale){
            this.scale = minScale;
        }else if(soma > 2.0){
            this.scale = 2.0;
        }else{
            this.scale = soma;
        }         
    }   

    private double getMinScale(int width, int height) {
        double maxScaleWidth = Double.valueOf(MAXWIDTH) / Double.valueOf(width);
        double maxScaleHeight = Double.valueOf(MAXHEIGHT) / Double.valueOf(height);

        double min;
        
        if (maxScaleWidth > maxScaleHeight) {
            min = maxScaleHeight;
        } else {
            min = maxScaleWidth;
        }
        
        if(min < 0.1){
            min = 0.1;
        }
        
        return min;
    }
    
    private BufferedImage getSubImage(BufferedImage image,int x, int y, int width, int height){
        if((x+width) > image.getWidth()){
            x = image.getWidth() - width;
        }
        if((y+height) > image.getHeight()){
            y = image.getHeight() - height;
        }
        
        return image.getSubimage(x, y, width, height);
    }

}
