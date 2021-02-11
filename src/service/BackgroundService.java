/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DIP.DigitalImageProcessing;
import java.awt.image.BufferedImage;
import java.net.URL;
import model.Background;

/**
 *
 * @author ghpm9
 */
public class BackgroundService {

    private Background background;
    private DigitalImageProcessing digitalImageProcessing;

    public BackgroundService(String defaultImage,int width, int height) {
        background = new Background();
        digitalImageProcessing = new DigitalImageProcessing(width, height);
        URL pathDefault = this.getClass().getResource(defaultImage);
        System.out.println("background:" + pathDefault);
        setImage(pathDefault);
        reset();
    }

    public void setImage(String path) {
        this.background.setImage(path);
        reset();
    }

    public void setImage(URL path) {
        this.background.setImage(path);
        reset();
    }

    public void moveX(int x) {
        digitalImageProcessing.moveX(x);
    }

    public void moveY(int y) {
        digitalImageProcessing.moveY(y);
    }

    public void setZoom(double zoom) {
        digitalImageProcessing.setZoom(zoom);
    }

    public BufferedImage getBackground() {
        return digitalImageProcessing.getImageProcessing(background.getBackground());
    }

    private void reset() {

        digitalImageProcessing.reset(this.background.getBackground().getWidth(), this.background.getBackground().getHeight(), 905, 655);
    }

}
