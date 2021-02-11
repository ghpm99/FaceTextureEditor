/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DIP;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

/**
 *
 * @author ghpm9
 */
public class DigitalImageProcessing {

    private ViewPort viewPort;
    private int width,height;

    public DigitalImageProcessing(int width, int height) {
        viewPort = new ViewPort(width, height);
        this.width = width;
        this.height = height;
    }

    public void moveX(int x) {
        this.viewPort.moveX(x);
    }

    public void moveY(int y) {
        this.viewPort.moveY(y);
    }

    public BufferedImage getImageProcessing(BufferedImage preImage) {
        BufferedImage subImage = getSubImage(preImage, viewPort.getX(), viewPort.getY(), viewPort.getWidth(), viewPort.getHeight());
        BufferedImage posImage = resizeImage(subImage, width, height);
        return posImage;
    }

    public void setZoom(double zoom) {
        if (zoom > 0) {
            this.viewPort.increaseZoom();
        } else {
            this.viewPort.decreaseZoom();
        }
    }

    public void reset(int maxWidth, int maxHeight,int width,int height) {
        this.viewPort.reset(maxWidth,maxHeight,width, height);
    }

    private BufferedImage resizeImage(BufferedImage image, int width, int height) {

        BufferedImage after = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = after.createGraphics();
        g2d.drawImage(image, 0, 0, width, height, null);
        g2d.dispose();

        return after;
    }

    private BufferedImage getSubImage(BufferedImage image, int x, int y, int width, int height) {
        System.out.println("x:" + x + "/y:" + y + " width:" + width + " - image width:" + image.getWidth() + " height:" + height + " - image height:" + image.getHeight());
        if (width > image.getWidth()) {
            width = image.getWidth();
        }
        if (height > image.getHeight()) {
            height = image.getHeight();
        }
        if((x + width) > image.getWidth()){
            x = image.getWidth() - width;
        }
        if((y + height) > image.getHeight()){
            y = image.getHeight() - height;
        }
        System.out.println("x:" + x + "/y:" + y + " width:" + width + " - image width:" + image.getWidth() + " height:" + height + " - image height:" + image.getHeight());
        return image.getSubimage(x, y, width, height);
    }

}
