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

import usu.rental.film.data.template.Pesan;
import java.util.Date;

/**
 *
 * @author usu
 */
public class DefaultPesan implements Pesan {

    private int idPesan;
    private String operatorPengirim,  operatorTujuan,  judulPesan,  isiPesan;
    private Date waktuPengiriman;

    public DefaultPesan() {
    }

    public DefaultPesan(int idPesan, String operatorPengirim, String operatorTujuan, String judulPesan, String isiPesan, Date waktuPengiriman) {
        this.idPesan = idPesan;
        this.operatorPengirim = operatorPengirim;
        this.operatorTujuan = operatorTujuan;
        this.judulPesan = judulPesan;
        this.isiPesan = isiPesan;
        this.waktuPengiriman = waktuPengiriman;
    }

    public int getIdPesan() {
        return idPesan;
    }

    public void setIdPesan(int idPesan) {
        this.idPesan = idPesan;
    }

    public String getIsiPesan() {
        return isiPesan;
    }

    public void setIsiPesan(String isiPesan) {
        this.isiPesan = isiPesan;
    }

    public String getJudulPesan() {
        return judulPesan;
    }

    public void setJudulPesan(String judulPesan) {
        this.judulPesan = judulPesan;
    }

    public String getOperatorPengirim() {
        return operatorPengirim;
    }

    public void setOperatorPengirim(String operatorPengirim) {
        this.operatorPengirim = operatorPengirim;
    }

    public String getOperatorTujuan() {
        return operatorTujuan;
    }

    public void setOperatorTujuan(String operatorTujuan) {
        this.operatorTujuan = operatorTujuan;
    }

    public Date getWaktuPengiriman() {
        return waktuPengiriman;
    }

    public void setWaktuPengiriman(Date waktuPengiriman) {
        this.waktuPengiriman = waktuPengiriman;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DefaultPesan other = (DefaultPesan) obj;
        if (this.idPesan != other.idPesan) {
            return false;
        }
        if (this.operatorPengirim != other.operatorPengirim && (this.operatorPengirim == null || !this.operatorPengirim.equals(other.operatorPengirim))) {
            return false;
        }
        if (this.operatorTujuan != other.operatorTujuan && (this.operatorTujuan == null || !this.operatorTujuan.equals(other.operatorTujuan))) {
            return false;
        }
        if (this.judulPesan != other.judulPesan && (this.judulPesan == null || !this.judulPesan.equals(other.judulPesan))) {
            return false;
        }
        if (this.isiPesan != other.isiPesan && (this.isiPesan == null || !this.isiPesan.equals(other.isiPesan))) {
            return false;
        }
        if (this.waktuPengiriman != other.waktuPengiriman && (this.waktuPengiriman == null || !this.waktuPengiriman.equals(other.waktuPengiriman))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.idPesan;
        hash = 97 * hash + (this.operatorPengirim != null ? this.operatorPengirim.hashCode() : 0);
        hash = 97 * hash + (this.operatorTujuan != null ? this.operatorTujuan.hashCode() : 0);
        hash = 97 * hash + (this.judulPesan != null ? this.judulPesan.hashCode() : 0);
        hash = 97 * hash + (this.isiPesan != null ? this.isiPesan.hashCode() : 0);
        hash = 97 * hash + (this.waktuPengiriman != null ? this.waktuPengiriman.hashCode() : 0);
        return hash;
    }
}
