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

import usu.rental.film.data.template.Anggota;
import java.sql.Date;

/**
 *
 * @author usu
 */
public class DefaultAnggota implements Anggota {

    private String idAnggota,  namaAnggota,  kontakAnggota,  alamatAnggota;
    private Date tanggalLahirAnggota;
    private int totalmeminjam;

    public DefaultAnggota() {
    }

    public DefaultAnggota(String idAnggota, String namaAnggota, String kontakAnggota, String alamatAnggota, Date tanggalLahirAnggota, int totalmeminjam) {
        this.idAnggota = idAnggota;
        this.namaAnggota = namaAnggota;
        this.kontakAnggota = kontakAnggota;
        this.alamatAnggota = alamatAnggota;
        this.tanggalLahirAnggota = tanggalLahirAnggota;
        this.totalmeminjam = totalmeminjam;
    }

    public int getTotalmeminjam() {
        return totalmeminjam;
    }

    public void setTotalmeminjam(int totalmeminjam) {
        this.totalmeminjam = totalmeminjam;
    }

    public String getAlamatAnggota() {
        return alamatAnggota;
    }

    public void setAlamatAnggota(String alamatAnggota) {
        this.alamatAnggota = alamatAnggota;
    }

    public String getIdAnggota() {
        return idAnggota;
    }

    public void setIdAnggota(String idAnggota) {
        this.idAnggota = idAnggota;
    }

    public String getKontakAnggota() {
        return kontakAnggota;
    }

    public void setKontakAnggota(String kontakAnggota) {
        this.kontakAnggota = kontakAnggota;
    }

    public String getNamaAnggota() {
        return namaAnggota;
    }

    public void setNamaAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
    }

    public Date getTanggalLahirAnggota() {
        return tanggalLahirAnggota;
    }

    public void setTanggalLahirAnggota(Date tanggalLahirAnggota) {
        this.tanggalLahirAnggota = tanggalLahirAnggota;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultAnggota other = (DefaultAnggota) obj;
        if (this.idAnggota != other.idAnggota && (this.idAnggota == null || !this.idAnggota.equals(other.idAnggota))) {
            return false;
        }
        if (this.namaAnggota != other.namaAnggota && (this.namaAnggota == null || !this.namaAnggota.equals(other.namaAnggota))) {
            return false;
        }
        if (this.kontakAnggota != other.kontakAnggota && (this.kontakAnggota == null || !this.kontakAnggota.equals(other.kontakAnggota))) {
            return false;
        }
        if (this.alamatAnggota != other.alamatAnggota && (this.alamatAnggota == null || !this.alamatAnggota.equals(other.alamatAnggota))) {
            return false;
        }
        if (this.tanggalLahirAnggota != other.tanggalLahirAnggota && (this.tanggalLahirAnggota == null || !this.tanggalLahirAnggota.equals(other.tanggalLahirAnggota))) {
            return false;
        }
        if (this.totalmeminjam != other.totalmeminjam) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + (this.idAnggota != null ? this.idAnggota.hashCode() : 0);
        hash = 89 * hash + (this.namaAnggota != null ? this.namaAnggota.hashCode() : 0);
        hash = 89 * hash + (this.kontakAnggota != null ? this.kontakAnggota.hashCode() : 0);
        hash = 89 * hash + (this.alamatAnggota != null ? this.alamatAnggota.hashCode() : 0);
        hash = 89 * hash + (this.tanggalLahirAnggota != null ? this.tanggalLahirAnggota.hashCode() : 0);
        hash = 89 * hash + this.totalmeminjam;
        return hash;
    }
}
