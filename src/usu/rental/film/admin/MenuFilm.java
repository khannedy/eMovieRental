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
package usu.rental.film.admin;

import java.awt.event.ActionListener;

/**
 *
 * @author  usu
 */
public class MenuFilm extends javax.swing.JPanel {

    /*
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;

    /** Creates new form MenuFilm */
    public MenuFilm() {
        initComponents();
    }

    public void addActionListenerRumahProduksi(ActionListener l) {
        rumahProduksi.addActionListener(l);
    }

    public void addActionListenerDirektor(ActionListener l) {
        direktor.addActionListener(l);
    }

    public void addActionListenerFilm(ActionListener l) {
        film.addActionListener(l);
    }

    public void addActionListenerJenisFilm(ActionListener l) {
        jenisFilm.addActionListener(l);
    }

    public void addActionListenerPemeranUtama(ActionListener l) {
        pemeranUtama.addActionListener(l);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        film = new usu.rental.film.widget.ButtonBig();
        direktor = new usu.rental.film.widget.ButtonBig();
        pemeranUtama = new usu.rental.film.widget.ButtonBig();
        jenisFilm = new usu.rental.film.widget.ButtonBig();
        rumahProduksi = new usu.rental.film.widget.ButtonBig();

        setOpaque(false);
        setLayout(new java.awt.GridBagLayout());

        film.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/rental/film/resource/dvd.png"))); // NOI18N
        film.setMnemonic('F');
        film.setText("film");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        add(film, gridBagConstraints);

        direktor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/rental/film/resource/direktor.png"))); // NOI18N
        direktor.setMnemonic('D');
        direktor.setText("direktor");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        add(direktor, gridBagConstraints);

        pemeranUtama.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/rental/film/resource/artis.png"))); // NOI18N
        pemeranUtama.setMnemonic('P');
        pemeranUtama.setText("pemeran utama");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        add(pemeranUtama, gridBagConstraints);

        jenisFilm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/rental/film/resource/jenis.png"))); // NOI18N
        jenisFilm.setMnemonic('J');
        jenisFilm.setText("jenis film");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        add(jenisFilm, gridBagConstraints);

        rumahProduksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/rental/film/resource/rumah.png"))); // NOI18N
        rumahProduksi.setMnemonic('R');
        rumahProduksi.setText("rumah produksi");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        add(rumahProduksi, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    usu.rental.film.widget.ButtonBig direktor;
    usu.rental.film.widget.ButtonBig film;
    usu.rental.film.widget.ButtonBig jenisFilm;
    usu.rental.film.widget.ButtonBig pemeranUtama;
    usu.rental.film.widget.ButtonBig rumahProduksi;
    // End of variables declaration//GEN-END:variables
}