/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ghpm9
 */
public enum ClassImages {

    NULL("Null"),
    ARCHER("Archer"),
    BERSERKER("Berserker"),
    DARK("Dark Knight"),
    GUARDIAN("Guardian"),
    HASHASHIN("Hashashin"),
    KUNOICHI("Kunoichi"),
    LAHN("Lahn"),
    MAEHWA("Maehwa"),
    MUSA("Musa"),
    MYSTIC("Mystic"),
    NINJA("Ninja"),
    NOVA("Nova"),
    RANGER("Ranger"),
    SHAI("Shai"),
    SORCERESS("Sorceress"),
    STRIKER("Striker"),
    TAMER("Tamer"),
    VALKYRIE("Valkyrie"),
    WARRIOR("Warrior"),
    WITCH("Witch"),
    WIZARD("Wizard"),
    SAGE("Sage"),
    CORSAIR("Corsair");

    private URL path;
    private String name;

    private ClassImages(String name) {

        this.path = ClassImages.class.getResource("Class/" + name + ".png");
        this.name = name;
    }

    public URL getPath() {
        return path;
    }

    public void setPath(URL path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static ClassImages getFromName(String name) {
        for (ClassImages result : values()) {
            if (result.getName().equals(name)) {
                return result;
            }
        }
        return null;
    }

    public BufferedImage getImage() {
        try {
            BufferedImage temp = ImageIO.read(path);
            return temp;
        } catch (IOException ex) {
            Logger.getLogger(ClassImages.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static String[] getAllClass() {
        ClassImages[] allClass = values();
        String[] allNameClass = new String[allClass.length];

        for (int i = 0; i < allClass.length; i++) {
            allNameClass[i] = allClass[i].name;
        }

        return allNameClass;
    }

}
