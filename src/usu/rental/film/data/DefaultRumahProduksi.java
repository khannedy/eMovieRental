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

import usu.rental.film.data.template.RumahProduksi;

/**
 *
 * @author usu
 */
public class DefaultRumahProduksi implements RumahProduksi {

    private String idRumahProduksi;
    private String namaRumahProduksi;

    public DefaultRumahProduksi() {
        this(null, null);
    }

    public DefaultRumahProduksi(String idRumahProduksi, String namaRumahProduksi) {
        this.idRumahProduksi = idRumahProduksi;
        this.namaRumahProduksi = namaRumahProduksi;
    }

    public String getIdRumahProduksi() {
        return idRumahProduksi;
    }

    public void setIdRumahProduksi(String idRumahProduksi) {
        this.idRumahProduksi = idRumahProduksi;
    }

    public String getNamaRumahProduksi() {
        return namaRumahProduksi;
    }

    public void setNamaRumahProduksi(String namaRumahProduksi) {
        this.namaRumahProduksi = namaRumahProduksi;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultRumahProduksi other = (DefaultRumahProduksi) obj;
        if (this.idRumahProduksi != other.idRumahProduksi && (this.idRumahProduksi == null || !this.idRumahProduksi.equals(other.idRumahProduksi))) {
            return false;
        }
        if (this.namaRumahProduksi != other.namaRumahProduksi && (this.namaRumahProduksi == null || !this.namaRumahProduksi.equals(other.namaRumahProduksi))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + (this.idRumahProduksi != null ? this.idRumahProduksi.hashCode() : 0);
        hash = 97 * hash + (this.namaRumahProduksi != null ? this.namaRumahProduksi.hashCode() : 0);
        return hash;
    }
}
