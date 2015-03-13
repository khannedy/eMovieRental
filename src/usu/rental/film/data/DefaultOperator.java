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

import usu.rental.film.data.template.Operator;
import java.sql.Date;

/**
 *
 * @author usu
 */
public class DefaultOperator implements Operator {

    private String idOperator,  namaOperator,  kontakOperator,  alamatOperator;
    private Date tanggalLahirOperator;
    private String password;

    public DefaultOperator() {
    }

    public DefaultOperator(String idOperator, String namaOperator, String kontakOperator, String alamatOperator, Date tanggalLahirOperator, String password) {
        this.idOperator = idOperator;
        this.namaOperator = namaOperator;
        this.kontakOperator = kontakOperator;
        this.alamatOperator = alamatOperator;
        this.tanggalLahirOperator = tanggalLahirOperator;
        this.password = password;
    }

    public String getAlamatOperator() {
        return alamatOperator;
    }

    public void setAlamatOperator(String alamatOperator) {
        this.alamatOperator = alamatOperator;
    }

    public String getIdOperator() {
        return idOperator;
    }

    public void setIdOperator(String idOperator) {
        this.idOperator = idOperator;
    }

    public String getKontakOperator() {
        return kontakOperator;
    }

    public void setKontakOperator(String kontakOperator) {
        this.kontakOperator = kontakOperator;
    }

    public String getNamaOperator() {
        return namaOperator;
    }

    public void setNamaOperator(String namaOperator) {
        this.namaOperator = namaOperator;
    }

    public Date getTanggalLahirOperator() {
        return tanggalLahirOperator;
    }

    public void setTanggalLahirOperator(Date tanggalLahirOperator) {
        this.tanggalLahirOperator = tanggalLahirOperator;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultOperator other = (DefaultOperator) obj;
        if (this.idOperator != other.idOperator && (this.idOperator == null || !this.idOperator.equals(other.idOperator))) {
            return false;
        }
        if (this.namaOperator != other.namaOperator && (this.namaOperator == null || !this.namaOperator.equals(other.namaOperator))) {
            return false;
        }
        if (this.kontakOperator != other.kontakOperator && (this.kontakOperator == null || !this.kontakOperator.equals(other.kontakOperator))) {
            return false;
        }
        if (this.alamatOperator != other.alamatOperator && (this.alamatOperator == null || !this.alamatOperator.equals(other.alamatOperator))) {
            return false;
        }
        if (this.tanggalLahirOperator != other.tanggalLahirOperator && (this.tanggalLahirOperator == null || !this.tanggalLahirOperator.equals(other.tanggalLahirOperator))) {
            return false;
        }
        if (this.password != other.password && (this.password == null || !this.password.equals(other.password))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.idOperator != null ? this.idOperator.hashCode() : 0);
        hash = 59 * hash + (this.namaOperator != null ? this.namaOperator.hashCode() : 0);
        hash = 59 * hash + (this.kontakOperator != null ? this.kontakOperator.hashCode() : 0);
        hash = 59 * hash + (this.alamatOperator != null ? this.alamatOperator.hashCode() : 0);
        hash = 59 * hash + (this.tanggalLahirOperator != null ? this.tanggalLahirOperator.hashCode() : 0);
        hash = 59 * hash + (this.password != null ? this.password.hashCode() : 0);
        return hash;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
