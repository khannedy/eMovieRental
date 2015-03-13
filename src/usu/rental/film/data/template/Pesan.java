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

package usu.rental.film.data.template;

import java.util.Date;

/**
 *
 * @author usu
 */
public interface Pesan {

    int getIdPesan();

    String getIsiPesan();

    String getJudulPesan();

    String getOperatorPengirim();

    String getOperatorTujuan();

    Date getWaktuPengiriman();

    void setIdPesan(int idPesan);

    void setIsiPesan(String isiPesan);

    void setJudulPesan(String judulPesan);

    void setOperatorPengirim(String operatorPengirim);

    void setOperatorTujuan(String operatorTujuan);

    void setWaktuPengiriman(Date waktuPengiriman);

}
