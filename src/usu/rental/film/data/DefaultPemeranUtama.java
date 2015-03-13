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

import usu.rental.film.data.template.PemeranUtama;

/**
 *
 * @author usu
 */
public class DefaultPemeranUtama implements PemeranUtama {

    private String idPemeranUtama;
    private String namaPemeranUtama;

    public DefaultPemeranUtama(String idPemeranUtama, String namaPemeranUtama) {
        this.idPemeranUtama = idPemeranUtama;
        this.namaPemeranUtama = namaPemeranUtama;
    }

    public DefaultPemeranUtama() {
        this(null, null);
    }

    public String getIdPemeranUtama() {
        return idPemeranUtama;
    }

    public void setIdPemeranUtama(String idPemeranUtama) {
        this.idPemeranUtama = idPemeranUtama;
    }

    public String getNamaPemeranUtama() {
        return namaPemeranUtama;
    }

    public void setNamaPemeranUtama(String namaPemeranUtama) {
        this.namaPemeranUtama = namaPemeranUtama;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultPemeranUtama other = (DefaultPemeranUtama) obj;
        if (this.idPemeranUtama != other.idPemeranUtama && (this.idPemeranUtama == null || !this.idPemeranUtama.equals(other.idPemeranUtama))) {
            return false;
        }
        if (this.namaPemeranUtama != other.namaPemeranUtama && (this.namaPemeranUtama == null || !this.namaPemeranUtama.equals(other.namaPemeranUtama))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + (this.idPemeranUtama != null ? this.idPemeranUtama.hashCode() : 0);
        hash = 89 * hash + (this.namaPemeranUtama != null ? this.namaPemeranUtama.hashCode() : 0);
        return hash;
    }
}
