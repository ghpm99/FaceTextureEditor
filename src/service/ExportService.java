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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import model.FaceTexture;

/**
 *
 * @author ghpm9
 */
public class ExportService {

    private URL pathFundo = getClass().getResource("images/fundo.png");

    private ArrayList<BufferedImage> facesTexture = new ArrayList<>();

    public void generateExport(ArrayList<FaceTexture> faceTextures) {

        ArrayList<BufferedImage> newArray = new ArrayList<>();
        
        faceTextures.forEach((face) -> {
            BufferedImage copy = new BufferedImage(face.getImage().getWidth(), face.getImage().getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = copy.createGraphics();
            g2d.drawImage(face.getImage(), 0, 0, null);
            g2d.dispose();
            newArray.add(copy);
        });

        facesTexture = newArray;
        
        drawClass(faceTextures, 312);
    }

    public void generateExport(BufferedImage background, ArrayList<FaceTexture> faceTextures) {
        splitBackground(background);
        drawClass(faceTextures, 62);
    }

    private void splitBackground(BufferedImage background) {

        ArrayList<BufferedImage> newArray = new ArrayList<>();
        
        int width = 125;
        int height = 160;

        int contX = 0;
        int contY = 0;

        Iterator ite = facesTexture.iterator();

        while (ite.hasNext()) {
            BufferedImage temp = (BufferedImage) ite.next();
            int index = facesTexture.indexOf(temp);
            int x = contX * (width + 5);
            int y = contY * (height + 5);

            if (index % 7 == 6) {
                contX = 0;
                contY++;
            } else {
                contX++;
            }
            newArray.add(background.getSubimage(x, y, width, height));
            
        }
        
        facesTexture = newArray;

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

            Iterator ite = facesTexture.iterator();

            while (ite.hasNext()) {
                BufferedImage tempImage = (BufferedImage) ite.next();
                int index = facesTexture.indexOf(tempImage);

                int x = contX * (width + 5) + 11;
                int y = contY * (height + 5) + 11;

                if (index % 7 == 6) {
                    contX = 0;
                    contY++;
                } else {
                    contX++;
                }
                g2d.drawImage(resizeImage(tempImage, width, height), null, x, y);
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

    private void drawClass(ArrayList<FaceTexture> faceTextures, int dimension) {
        faceTextures.forEach((s) -> {
            if (s.isShowClasse()) {

                ClassImages classImage = ClassImages.getFromName(s.getClasse());

                if (classImage == ClassImages.NULL) {
                    return;
                }

                BufferedImage image = resizeImage(classImage.getImage(), dimension, dimension);

                Graphics2D g2d = facesTexture.get(faceTextures.indexOf(s)).createGraphics();
                g2d.drawImage(image, null, 0, 0);
                g2d.dispose();

            }
        });

    }

    public void export(ArrayList<FaceTexture> faceTextures, File path) {
        faceTextures.forEach((s) -> {
            if (s.getName().equals("null")) {
                return;
            }
            try {
                File outputfile = new File(path.getAbsolutePath() + "/" + s.getName());
                if (!outputfile.exists()) {
                    outputfile.createNewFile();
                }
                ImageIO.write(facesTexture.get(faceTextures.indexOf(s)), "bmp", outputfile);
            } catch (IOException ex) {
                Logger.getLogger(ExportService.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }
}
