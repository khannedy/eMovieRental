/*
 * 
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

package usu.rental.film.data.template;

/**
 *
 * @author usu
 */
public interface Film {

    String getDirektor();

    String getIdFilm();

    String getJenisFilm();

    String getJudulFilm();

    String getPemeranUtama();

    String getRumahProduksi();

    int getTotalDipinjam();

    boolean isDipinjam();

    void setDipinjam(boolean dipinjam);

    void setDirektor(String direktor);

    void setIdFilm(String idFilm);

    void setJenisFilm(String jenisFilm);

    void setJudulFilm(String judulFilm);

    void setPemeranUtama(String pemeranUtama);

    void setRumahProduksi(String rumahProduksi);

    void setTotalDipinjam(int totalDipinjam);

}
