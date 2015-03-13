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
package usu.rental.film.data;

import usu.rental.film.data.template.Film;

/**
 *
 * @author usu
 */
public class DefaultFilm implements Film {

    private String idFilm,  judulFilm,  rumahProduksi,  direktor,  pemeranUtama,  jenisFilm;
    private boolean dipinjam;
    private int totalDipinjam;

    public DefaultFilm() {
    }

    public DefaultFilm(String idFilm, String judulFilm, String rumahProduksi, String direktor, String pemeranUtama, String jenisFilm, boolean dipinjam, int totalDipinjam) {
        this.idFilm = idFilm;
        this.judulFilm = judulFilm;
        this.rumahProduksi = rumahProduksi;
        this.direktor = direktor;
        this.pemeranUtama = pemeranUtama;
        this.jenisFilm = jenisFilm;
        this.dipinjam = dipinjam;
        this.totalDipinjam = totalDipinjam;
    }

    public boolean isDipinjam() {
        return dipinjam;
    }

    public void setDipinjam(boolean dipinjam) {
        this.dipinjam = dipinjam;
    }

    public String getDirektor() {
        return direktor;
    }

    public void setDirektor(String direktor) {
        this.direktor = direktor;
    }

    public String getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(String idFilm) {
        this.idFilm = idFilm;
    }

    public String getJenisFilm() {
        return jenisFilm;
    }

    public void setJenisFilm(String jenisFilm) {
        this.jenisFilm = jenisFilm;
    }

    public String getJudulFilm() {
        return judulFilm;
    }

    public void setJudulFilm(String judulFilm) {
        this.judulFilm = judulFilm;
    }

    public String getPemeranUtama() {
        return pemeranUtama;
    }

    public void setPemeranUtama(String pemeranUtama) {
        this.pemeranUtama = pemeranUtama;
    }

    public String getRumahProduksi() {
        return rumahProduksi;
    }

    public void setRumahProduksi(String rumahProduksi) {
        this.rumahProduksi = rumahProduksi;
    }

    public int getTotalDipinjam() {
        return totalDipinjam;
    }

    public void setTotalDipinjam(int totalDipinjam) {
        this.totalDipinjam = totalDipinjam;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultFilm other = (DefaultFilm) obj;
        if (this.idFilm != other.idFilm && (this.idFilm == null || !this.idFilm.equals(other.idFilm))) {
            return false;
        }
        if (this.judulFilm != other.judulFilm && (this.judulFilm == null || !this.judulFilm.equals(other.judulFilm))) {
            return false;
        }
        if (this.rumahProduksi != other.rumahProduksi && (this.rumahProduksi == null || !this.rumahProduksi.equals(other.rumahProduksi))) {
            return false;
        }
        if (this.direktor != other.direktor && (this.direktor == null || !this.direktor.equals(other.direktor))) {
            return false;
        }
        if (this.pemeranUtama != other.pemeranUtama && (this.pemeranUtama == null || !this.pemeranUtama.equals(other.pemeranUtama))) {
            return false;
        }
        if (this.jenisFilm != other.jenisFilm && (this.jenisFilm == null || !this.jenisFilm.equals(other.jenisFilm))) {
            return false;
        }
        if (this.dipinjam != other.dipinjam) {
            return false;
        }
        if (this.totalDipinjam != other.totalDipinjam) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.idFilm != null ? this.idFilm.hashCode() : 0);
        hash = 53 * hash + (this.judulFilm != null ? this.judulFilm.hashCode() : 0);
        hash = 53 * hash + (this.rumahProduksi != null ? this.rumahProduksi.hashCode() : 0);
        hash = 53 * hash + (this.direktor != null ? this.direktor.hashCode() : 0);
        hash = 53 * hash + (this.pemeranUtama != null ? this.pemeranUtama.hashCode() : 0);
        hash = 53 * hash + (this.jenisFilm != null ? this.jenisFilm.hashCode() : 0);
        hash = 53 * hash + (this.dipinjam ? 1 : 0);
        hash = 53 * hash + this.totalDipinjam;
        return hash;
    }
}
