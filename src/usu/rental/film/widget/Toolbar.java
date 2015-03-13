/*
 * DILARANG MENGHAPUS ATAU MENGEDIT COPYRIGHT INI.
 * 
 * Copyright 2008 echo.khannedy@gmail.com. 
 * All rights reserved.
 * 
 * Semua isi dalam file ini adalah hak milik dari echo.khannedy@gmail.com
 * Anda tak diperkenankan untuk menggunakan file atau mengubah file
 * ini kecuali anda tidak menghapus atau merubah lisence ini.
 * 
 * File ini dibuat menggunakan :
 * IDE        : NetBeans
 * NoteBook   : Acer Aspire 5920G
 * OS         : Windows Vista
 * Java       : Java 1.6
 * 
 */
package usu.rental.film.widget;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.JToolBar;

/**
 *
 * @author usu
 */
public class Toolbar extends JToolBar {

    private static final long serialVersionUID = 1L;
    private BufferedImage gradientImage;
    private BufferedImage ligthImage;
    private Color light = new Color(1F, 1F, 1F, 0.5F);
    private Color dark = new Color(1F, 1F, 1F, 0.0F);
    private Color black = Color.BLACK;
    private Color warna = Color.BLUE;

    /**
     * 
     */
    public Toolbar() {
        super();
        setFloatable(false);
        setBorder(BorderFactory.createEmptyBorder(3, 3, 4, 3));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setUpGradientImage();
        setUpLigthImage();
        if (isOpaque()) {
            g.drawImage(gradientImage, 0, 0, getWidth(), getHeight(), null);
            g.drawImage(ligthImage, 0, 0, getWidth(), getHeight() / 2, null);
        }
    }

    /**
     * membuat gambar gradient background
     */
    private void setUpGradientImage() {
        gradientImage = new BufferedImage(1, getHeight(), BufferedImage.TYPE_INT_ARGB);

        GradientPaint paint = new GradientPaint(0, 0, warna, 0, getHeight(), black);

        Graphics2D g = (Graphics2D) gradientImage.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setPaint(paint);
        g.fillRect(0, 0, 1, getHeight());
        g.dispose();
    }

    /**
     * membuat gambar glass
     */
    private void setUpLigthImage() {
        ligthImage = new BufferedImage(1, getHeight() / 2, BufferedImage.TYPE_INT_ARGB);

        GradientPaint paint = new GradientPaint(0, 0, light, 0, getHeight(), dark);

        Graphics2D g = (Graphics2D) ligthImage.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setPaint(paint);
        g.fillRect(0, 0, 1, getHeight() / 2);
        g.dispose();
    }
}
