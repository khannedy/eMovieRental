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

/**
 *
 * @author usu
 */
public class Setting {

    private int maksimalPeminjaman;
    private int dendaKeterlambatan;
    private int hargaPeminjaman;

    public Setting() {
        this(0, 0, 0);
    }

    public Setting(int maksimalPeminjaman, int dendaKeterlambatan, int hargaPeminjaman) {
        this.maksimalPeminjaman = maksimalPeminjaman;
        this.dendaKeterlambatan = dendaKeterlambatan;
        this.hargaPeminjaman = hargaPeminjaman;
    }

    public int getDendaKeterlambatan() {
        return dendaKeterlambatan;
    }

    public void setDendaKeterlambatan(int dendaKeterlambatan) {
        this.dendaKeterlambatan = dendaKeterlambatan;
    }

    public int getHargaPeminjaman() {
        return hargaPeminjaman;
    }

    public void setHargaPeminjaman(int hargaPeminjaman) {
        this.hargaPeminjaman = hargaPeminjaman;
    }

    public int getMaksimalPeminjaman() {
        return maksimalPeminjaman;
    }

    public void setMaksimalPeminjaman(int maksimalPeminjaman) {
        this.maksimalPeminjaman = maksimalPeminjaman;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Setting other = (Setting) obj;
        if (this.maksimalPeminjaman != other.maksimalPeminjaman) {
            return false;
        }
        if (this.dendaKeterlambatan != other.dendaKeterlambatan) {
            return false;
        }
        if (this.hargaPeminjaman != other.hargaPeminjaman) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.maksimalPeminjaman;
        hash = 67 * hash + this.dendaKeterlambatan;
        hash = 67 * hash + this.hargaPeminjaman;
        return hash;
    }
}
