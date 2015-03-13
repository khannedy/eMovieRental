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

import usu.rental.film.data.template.Pengembalian;
import java.sql.Date;

/**
 *
 * @author usu
 */
public class DefaultPengembalian implements Pengembalian {

    private int idTransaksi;
    private String operator;
    private Date tanggalPengembalian;
    private int totalHariTerlambat,  dendaKeterlambatan;

    public DefaultPengembalian() {
    }

    public DefaultPengembalian(int idTransaksi, String operator, Date tanggalPengembalian, int totalHariTerlambat, int dendaKeterlambatan) {
        this.idTransaksi = idTransaksi;
        this.operator = operator;
        this.tanggalPengembalian = tanggalPengembalian;
        this.totalHariTerlambat = totalHariTerlambat;
        this.dendaKeterlambatan = dendaKeterlambatan;
    }

    public int getDendaKeterlambatan() {
        return dendaKeterlambatan;
    }

    public void setDendaKeterlambatan(int dendaKeterlambatan) {
        this.dendaKeterlambatan = dendaKeterlambatan;
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

    public Date getTanggalPengembalian() {
        return tanggalPengembalian;
    }

    public void setTanggalPengembalian(Date tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }

    public int getTotalHariTerlambat() {
        return totalHariTerlambat;
    }

    public void setTotalHariTerlambat(int totalHariTerlambat) {
        this.totalHariTerlambat = totalHariTerlambat;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultPengembalian other = (DefaultPengembalian) obj;
        if (this.idTransaksi != other.idTransaksi) {
            return false;
        }
        if (this.operator != other.operator && (this.operator == null || !this.operator.equals(other.operator))) {
            return false;
        }
        if (this.tanggalPengembalian != other.tanggalPengembalian && (this.tanggalPengembalian == null || !this.tanggalPengembalian.equals(other.tanggalPengembalian))) {
            return false;
        }
        if (this.totalHariTerlambat != other.totalHariTerlambat) {
            return false;
        }
        if (this.dendaKeterlambatan != other.dendaKeterlambatan) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.idTransaksi;
        hash = 23 * hash + (this.operator != null ? this.operator.hashCode() : 0);
        hash = 23 * hash + (this.tanggalPengembalian != null ? this.tanggalPengembalian.hashCode() : 0);
        hash = 23 * hash + this.totalHariTerlambat;
        hash = 23 * hash + this.dendaKeterlambatan;
        return hash;
    }
}
