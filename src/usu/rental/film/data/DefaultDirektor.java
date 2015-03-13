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

import usu.rental.film.data.template.Direktor;

/**
 *
 * @author usu
 */
public class DefaultDirektor implements Direktor {

    private String idDirektor;
    private String namaDirektor;

    public DefaultDirektor() {
        this(null, null);
    }

    public DefaultDirektor(String idDirektor, String namaDirektor) {
        this.idDirektor = idDirektor;
        this.namaDirektor = namaDirektor;
    }

    public String getIdDirektor() {
        return idDirektor;
    }

    public void setIdDirektor(String idDirektor) {
        this.idDirektor = idDirektor;
    }

    public String getNamaDirektor() {
        return namaDirektor;
    }

    public void setNamaDirektor(String namaDirektor) {
        this.namaDirektor = namaDirektor;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultDirektor other = (DefaultDirektor) obj;
        if (this.idDirektor != other.idDirektor && (this.idDirektor == null || !this.idDirektor.equals(other.idDirektor))) {
            return false;
        }
        if (this.namaDirektor != other.namaDirektor && (this.namaDirektor == null || !this.namaDirektor.equals(other.namaDirektor))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + (this.idDirektor != null ? this.idDirektor.hashCode() : 0);
        hash = 13 * hash + (this.namaDirektor != null ? this.namaDirektor.hashCode() : 0);
        return hash;
    }
}
