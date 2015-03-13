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

import usu.rental.film.data.template.Peminjaman;
import java.sql.Date;

/**
 *
 * @author usu
 */
public class DefaultPeminjaman implements Peminjaman {

    private int idTransaksi;
    private String operator,  anggota,  film;
    private Date tanggalPeminjaman,  tanggalPengembalian;
    private boolean telahDikembalikan;

    public DefaultPeminjaman() {
    }

    public DefaultPeminjaman(int idTransaksi, String operator, String anggota, String film, Date tanggalPeminjaman, Date tanggalPengembalian, boolean telahDikembalikan) {
        this.idTransaksi = idTransaksi;
        this.operator = operator;
        this.anggota = anggota;
        this.film = film;
        this.tanggalPeminjaman = tanggalPeminjaman;
        this.tanggalPengembalian = tanggalPengembalian;
        this.telahDikembalikan = telahDikembalikan;
    }

    public String getAnggota() {
        return anggota;
    }

    public void setAnggota(String anggota) {
        this.anggota = anggota;
    }

    public String getFilm() {
        return film;
    }

    public void setFilm(String film) {
        this.film = film;
    }

    public int getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(int idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Date getTanggalPeminjaman() {
        return tanggalPeminjaman;
    }

    public void setTanggalPeminjaman(Date tanggalPeminjaman) {
        this.tanggalPeminjaman = tanggalPeminjaman;
    }

    public Date getTanggalPengembalian() {
        return tanggalPengembalian;
    }

    public void setTanggalPengembalian(Date tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }

    public boolean isTelahDikembalikan() {
        return telahDikembalikan;
    }

    public void setTelahDikembalikan(boolean telahDikembalikan) {
        this.telahDikembalikan = telahDikembalikan;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultPeminjaman other = (DefaultPeminjaman) obj;
        if (this.idTransaksi != other.idTransaksi) {
            return false;
        }
        if (this.operator != other.operator && (this.operator == null || !this.operator.equals(other.operator))) {
            return false;
        }
        if (this.anggota != other.anggota && (this.anggota == null || !this.anggota.equals(other.anggota))) {
            return false;
        }
        if (this.film != other.film && (this.film == null || !this.film.equals(other.film))) {
            return false;
        }
        if (this.tanggalPeminjaman != other.tanggalPeminjaman && (this.tanggalPeminjaman == null || !this.tanggalPeminjaman.equals(other.tanggalPeminjaman))) {
            return false;
        }
        if (this.tanggalPengembalian != other.tanggalPengembalian && (this.tanggalPengembalian == null || !this.tanggalPengembalian.equals(other.tanggalPengembalian))) {
            return false;
        }
        if (this.telahDikembalikan != other.telahDikembalikan) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.idTransaksi;
        hash = 71 * hash + (this.operator != null ? this.operator.hashCode() : 0);
        hash = 71 * hash + (this.anggota != null ? this.anggota.hashCode() : 0);
        hash = 71 * hash + (this.film != null ? this.film.hashCode() : 0);
        hash = 71 * hash + (this.tanggalPeminjaman != null ? this.tanggalPeminjaman.hashCode() : 0);
        hash = 71 * hash + (this.tanggalPengembalian != null ? this.tanggalPengembalian.hashCode() : 0);
        hash = 71 * hash + (this.telahDikembalikan ? 1 : 0);
        return hash;
    }
}
