/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

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

    public BufferedImage getBackground() {
        return background;
    }

    private void setBackground(BufferedImage background) {
        this.background = background;

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

    public void setImage(URL path) {
        try {
            BufferedImage temp = ImageIO.read(path);
            setBackground(temp);
        } catch (IOException ex) {
            Logger.getLogger(FaceTexture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
