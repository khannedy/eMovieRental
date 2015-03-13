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
package usu.rental.film;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author usu
 */
public class Informasi {

    /**
     * metode ini digunakan untuk mendapatkan email pemilik aplikasi ini
     * 
     * @return email
     */
    public static final String getAuthorEmail() {
        return "echo.khannedy@gmail.com";
    }

    /**
     * metode ini digunakan untuk mendapatkan nama pemilik aplikasi ini
     * 
     * @return nama
     */
    public static final String getAuthorName() {
        return "Eko Kurniawan Khannedy";
    }

    /**
     * metode ini digunakan untuk mendapatkan website pemilik aplikasi ini
     * 
     * @return website
     * @throws java.net.MalformedURLException
     */
    public static final URL getAuthorWebsite() throws MalformedURLException {
        return new URL("http://eecchhoo.wordpress.com/");
    }

    /**
     * class Informasi merupakan class library , sehingga tak dapat diinstansiasi 
     * secara langsung.
     * @deprecated 
     */
    @Deprecated
    private Informasi() {
        throw new IllegalAccessError("AKSES DITOLAK");
    }
}
