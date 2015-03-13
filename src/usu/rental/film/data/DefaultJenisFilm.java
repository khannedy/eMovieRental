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

import usu.rental.film.data.template.JenisFilm;

/**
 *
 * @author usu
 */
public class DefaultJenisFilm implements JenisFilm {

    private String idJenisFilm;
    private String namaJenisFilm;

    public DefaultJenisFilm(String idJenisFilm, String namaJenisFilm) {
        this.idJenisFilm = idJenisFilm;
        this.namaJenisFilm = namaJenisFilm;
    }

    public DefaultJenisFilm() {
        this(null, null);
    }

    public String getIdJenisFilm() {
        return idJenisFilm;
    }

    public void setIdJenisFilm(String idJenisFilm) {
        this.idJenisFilm = idJenisFilm;
    }

    public String getNamaJenisFilm() {
        return namaJenisFilm;
    }

    public void setNamaJenisFilm(String namaJenisFilm) {
        this.namaJenisFilm = namaJenisFilm;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultJenisFilm other = (DefaultJenisFilm) obj;
        if (this.idJenisFilm != other.idJenisFilm && (this.idJenisFilm == null || !this.idJenisFilm.equals(other.idJenisFilm))) {
            return false;
        }
        if (this.namaJenisFilm != other.namaJenisFilm && (this.namaJenisFilm == null || !this.namaJenisFilm.equals(other.namaJenisFilm))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + (this.idJenisFilm != null ? this.idJenisFilm.hashCode() : 0);
        hash = 31 * hash + (this.namaJenisFilm != null ? this.namaJenisFilm.hashCode() : 0);
        return hash;
    }
}
