/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DIP;

/**
 *
 * @author ghpm9
 */
public class ViewPort {

    private int x, y;
    //tamanho do view port
    private int width, height;
    //tamanho maximo do view port = tamanho imagem
    private int maxWidth, maxHeight;

    private float zoom;

    public ViewPort(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    private void setX(int x) {

        if ((x + width) > maxWidth) {
            this.x = maxWidth - width;
        } else {
            this.x = x;
        }
        if (this.x < 0) {
            this.x = 0;
        }
    }

    public int getY() {
        return y;
    }

    private void setY(int y) {
        if ((y + height) > maxHeight) {
            this.y = maxHeight - height;
        } else {
            this.y = y;
        }
        if (this.y < 0) {
            this.y = 0;
        }
    }

    public void moveX(int x) {
        int newX = getX() + x;

        setX(newX);

    }

    public void moveY(int y) {
        int newY = getY() + y;

        setY(newY);

    }

    public int getWidth() {
        return width;
    }

    private void setWidth(int width) {
        System.out.println("width: " + width);
        if (width > maxWidth) {
            width = maxWidth;
        }
        if (width > 100) {
            this.width = width;
        }

    }

    public int getHeight() {
        return height;
    }

    private void setHeight(int height) {
        System.out.println("height: " + height);
        if (height > maxHeight) {
            height = maxHeight;
        }
        if (height > 100) {
            this.height = height;
        }

    }

    public int getMaxWidth() {
        return maxWidth;
    }

    private void setMaxWidth(int maxWidth) {
        this.maxWidth = maxWidth;
        System.out.println("Max Width:" + maxWidth);
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    private void setMaxHeight(int maxHeight) {
        this.maxHeight = maxHeight;
        System.out.println("Max Heigh:" + maxHeight);
    }

    public void reset(int maxWidth, int maxHeight,int width,int height) {
        setMaxWidth(maxWidth);
        setMaxHeight(maxHeight);
        setX(0);
        setY(0);
        setZoom(1.0f);
        setWidth(width);
        setHeight(height);
    }

    public float getZoom() {
        return zoom;
    }

    private void setZoom(float zoom) {
        System.out.println("Zoom:" + zoom);
        if (zoom <= 0.1f) {
            zoom = 0.1f;
        } else if (zoom >= 10) {
            zoom = 10;
        }
        System.out.println("Zoom:" + zoom);
        this.zoom = zoom;
        applyZoom();
    }

    public void applyZoom() {
        setWidth((int) (getWidth() * getZoom()));
        setHeight((int) (getHeight() * getZoom()));
    }

    public void increaseZoom() {
        if (getZoom() < 1.0f) {
            setZoom(1.0f);
        } else {
            setZoom(1.1f);
        }

    }

    public void decreaseZoom() {
        if (getZoom() > 1.0f) {
            setZoom(1.0f);
        } else {
            setZoom(0.9f);
        }
    }

}
