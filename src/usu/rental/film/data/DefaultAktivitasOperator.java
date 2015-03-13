/*
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

import java.sql.Timestamp;
import usu.rental.film.data.template.AktivitasOperator;

/**
 *
 * @author usu
 */
public class DefaultAktivitasOperator implements AktivitasOperator {

    private String operator,  aktivitas;
    private Timestamp waktu;

    public DefaultAktivitasOperator() {
    }

    public DefaultAktivitasOperator(String operator, String aktivitas) {
        this.operator = operator;
        this.aktivitas = aktivitas;
    }

    public String getAktivitas() {
        return aktivitas;
    }

    public void setAktivitas(String aktivitas) {
        this.aktivitas = aktivitas;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultAktivitasOperator other = (DefaultAktivitasOperator) obj;
        if (this.operator != other.operator && (this.operator == null || !this.operator.equals(other.operator))) {
            return false;
        }
        if (this.aktivitas != other.aktivitas && (this.aktivitas == null || !this.aktivitas.equals(other.aktivitas))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (this.operator != null ? this.operator.hashCode() : 0);
        hash = 11 * hash + (this.aktivitas != null ? this.aktivitas.hashCode() : 0);
        return hash;
    }

    public void setWaktu(Timestamp waktu) {
        this.waktu = waktu;
    }

    public Timestamp getWaktu() {
        return waktu;
    }
}
