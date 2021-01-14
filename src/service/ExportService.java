/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Util.ClassImages;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.Background;
import model.FaceTexture;

/**
 *
 * @author ghpm9
 */
public class ExportService {

    private URL pathFundo = getClass().getResource("images/fundo.png");

    private BufferedImage[] facesTexture = new BufferedImage[28];

    public void generateExport(FaceTexture[] faceTextures) {
        for (int i = 0; i < faceTextures.length; i++) {
            BufferedImage copy = new BufferedImage(faceTextures[i].getImage().getWidth(), faceTextures[i].getImage().getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = copy.createGraphics();
            g2d.drawImage(faceTextures[i].getImage(), 0, 0, null);
            g2d.dispose();
            this.facesTexture[i] = copy;
        }
        drawClass(faceTextures, 312);
    }

    public void generateExport(Background background, FaceTexture[] faceTextures) {
        splitBackground(background);
        drawClass(faceTextures, 62);
    }

    private void splitBackground(Background background) {
        BufferedImage backgroundImage = background.getBackground();
        int width = 125;
        int height = 160;

        int contX = 0;
        int contY = 0;

        for (int i = 0; i < facesTexture.length; i++) {

            int x = contX * (width + 5);
            int y = contY * (height + 5);

            if (i % 7 == 6) {
                contX = 0;
                contY++;
            } else {
                contX++;
            }
            facesTexture[i] = backgroundImage.getSubimage(x, y, width, height);

        }
    }

    public BufferedImage getPreview() {
        BufferedImage temp;
        try {
            temp = ImageIO.read(pathFundo);

            Graphics2D g2d = temp.createGraphics();

            int width = 125;
            int height = 160;

            int contX = 0;
            int contY = 0;

            for (int i = 0; i < facesTexture.length; i++) {

                int x = contX * (width + 5) + 11;
                int y = contY * (height + 5) + 11;

                if (i % 7 == 6) {
                    contX = 0;
                    contY++;
                } else {
                    contX++;
                }

                g2d.drawImage(resizeImage(facesTexture[i], width, height), null, x, y);

            }

            g2d.dispose();

        } catch (IOException ex) {
            Logger.getLogger(ExportService.class.getName()).log(Level.SEVERE, null, ex);
            temp = getImageError();
        }

        return temp;
    }

    public BufferedImage getImageError() {
        BufferedImage error = new BufferedImage(930, 736, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = error.createGraphics();

        g2d.drawString("ERROR", 0, 0);
        g2d.dispose();

        return error;
    }

    private BufferedImage resizeImage(BufferedImage image, int width, int height) {

        BufferedImage after = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = after.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();

        return after;
    }

    private void drawClass(FaceTexture[] faceTextures, int dimension) {
        for (int i = 0; i < faceTextures.length; i++) {
            if (faceTextures[i].isShowClasse()) {

                ClassImages classImage = ClassImages.getFromName(faceTextures[i].getClasse());

                if (classImage == ClassImages.NULL) {
                    continue;
                }

                BufferedImage image = resizeImage(classImage.getImage(), dimension, dimension);

                Graphics2D g2d = facesTexture[i].createGraphics();
                g2d.drawImage(image, null, 0, 0);
                g2d.dispose();

            }
        }
    }

    public void export(FaceTexture[] faceTextures, File path) {
        for (int i = 0; i < faceTextures.length; i++) {
            if(faceTextures[i].getName().equals("null"))continue;
            try {
                File outputfile = new File(path.getAbsolutePath() + "/" + faceTextures[i].getName());
                if (!outputfile.exists()) {
                    outputfile.createNewFile();
                }
                ImageIO.write(facesTexture[i], "bmp", outputfile);
            } catch (IOException ex) {
                Logger.getLogger(ExportService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
