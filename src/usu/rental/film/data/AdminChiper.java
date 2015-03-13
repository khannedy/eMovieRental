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

import java.io.Serializable;

/**
 *
 * @author usu
 */
public class AdminChiper implements Serializable {

    private static final long serialVersionUID = -1;
    private char[] username;
    private char[] password;
    private String encript;

    /**
     * 
     */
    public AdminChiper() {
        this(null, null, null);
    }

    /**
     * 
     * @param username
     * @param password
     */
    public AdminChiper(char[] username, char[] password) {
        this(username, password, null);
    }

    /**
     * 
     * @param username
     * @param password
     * @param encript 
     */
    public AdminChiper(char[] username, char[] password, String encript) {
        setEncript(encript);
        setPassword(password);
        setUsername(username);
        setEncript(null);
    }

    /**
     * 
     * @return
     */
    public char[] getPassword() {
        return password;
    }

    /**
     * 
     * @param password
     */
    private void setPassword(char[] password) {
        if (getEncript() == null) {
            this.password = password;
        } else {
            this.password = encript(password);
        }
    }

    /**
     * 
     * @return
     */
    public char[] getUsername() {
        return username;
    }

    /**
     * 
     * @param encript
     * @return
     */
    public char[] getUsername(String encript) {
        setEncript(encript);
        return decript(username);
    }

    /**
     * 
     * @param encript
     * @return
     */
    public char[] getPassword(String encript) {
        setEncript(encript);
        return decript(password);
    }

    /**
     * 
     * @param c
     * @return
     */
    private char[] encript(char[] c) {
        char[] a = new char[c.length];
        char[] e = getEncript().toCharArray();
        for (int i = 0; i < c.length; i++) {
            char x = c[i];
            for (char j : e) {
                x = (char) (x + j);
            }
            a[i] = x;
        }
        return a;
    }

    /**
     * 
     * @param c
     * @return
     */
    private char[] decript(char[] c) {
        char[] a = new char[c.length];
        char[] e = getEncript().toCharArray();
        for (int i = 0; i < c.length; i++) {
            char x = c[i];
            for (char j : e) {
                x = (char) (x - j);
            }
            a[i] = x;
        }
        return a;
    }

    /**
     * 
     * @return
     */
    private String getEncript() {
        return encript;
    }

    /**
     * 
     * @param encript
     */
    private void setEncript(String encript) {
        this.encript = encript;
    }

    /**
     * 
     * @param username
     */
    private void setUsername(char[] username) {
        if (getEncript() == null) {
            this.username = username;
        } else {
            this.username = encript(username);
        }
    }
}
