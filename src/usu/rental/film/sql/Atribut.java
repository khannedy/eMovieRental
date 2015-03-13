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
package usu.rental.film.sql;

/**
 * @author usu
 */
public interface Atribut {

    static interface AKTIVITAS_OPERATOR {

        static String AKTIVITAS_OPERATOR = Entitas.AKTIVITAS_OPERATOR + ".AKTIVITAS_OPERATOR";
        static String ID_OPERATOR = Entitas.AKTIVITAS_OPERATOR + ".ID_OPERATOR";
        static String WAKTU = Entitas.AKTIVITAS_OPERATOR + ".WAKTU";
    }

    static interface ANGGOTA {

        static String ALAMAT_ANGGOTA = Entitas.ANGGOTA + ".ALAMAT_ANGGOTA";
        static String ID_ANGGOTA = Entitas.ANGGOTA + ".ID_ANGGOTA";
        static String KONTAK_ANGGOTA = Entitas.ANGGOTA + ".KONTAK_ANGGOTA";
        static String NAMA_ANGGOTA = Entitas.ANGGOTA + ".NAMA_ANGGOTA";
        static String TANGGAL_LAHIR_ANGGOTA = Entitas.ANGGOTA + ".TANGGAL_LAHIR_ANGGOTA";
        static String TOTAL_MEMINJAM = Entitas.ANGGOTA + ".TOTAL_MEMINJAM";
    }

    static interface DIREKTOR {

        static String ID_DIREKTOR = Entitas.DIREKTOR + ".ID_DIREKTOR";
        static String NAMA_DIREKTOR = Entitas.DIREKTOR + ".NAMA_DIREKTOR";
    }

    static interface FILM {

        static String DIPINJAM = Entitas.FILM + ".DIPINJAM";
        static String ID_DIREKTOR = Entitas.FILM + ".ID_DIREKTOR";
        static String ID_FILM = Entitas.FILM + ".ID_FILM";
        static String ID_JENIS_FILM = Entitas.FILM + ".ID_JENIS_FILM";
        static String ID_PEMERAN_UTAMA = Entitas.FILM + ".ID_PEMERAN_UTAMA";
        static String ID_RUMAH_PRODUKSI = Entitas.FILM + ".ID_RUMAH_PRODUKSI";
        static String JUDUL_FILM = Entitas.FILM + ".JUDUL_FILM";
        static String TOTAL_DIPINJAM = Entitas.FILM + ".TOTAL_DIPINJAM";
    }

    static interface JENIS_FILM {

        static String ID_JENIS_FILM = Entitas.JENIS_FILM + ".ID_JENIS_FILM";
        static String NAMA_JENIS_FILM = Entitas.JENIS_FILM + ".NAMA_JENIS_FILM";
    }

    static interface OPERATOR {

        static String ALAMAT_OPERATOR = Entitas.OPERATOR + ".ALAMAT_OPERATOR";
        static String ID_OPERATOR = Entitas.OPERATOR + ".ID_OPERATOR";
        static String KONTAK_OPERATOR = Entitas.OPERATOR + ".KONTAK_OPERATOR";
        static String NAMA_OPERATOR = Entitas.OPERATOR + ".NAMA_OPERATOR";
        static String PASSWORD_OPERATOR = Entitas.OPERATOR + ".PASSWORD_OPERATOR";
        static String TANGGAL_LAHIR_OPERATOR = Entitas.OPERATOR + ".TANGGAL_LAHIR_OPERATOR";
    }

    static interface PEMERAN_UTAMA {

        static String ID_PEMERAN_UTAMA = Entitas.PEMERAN_UTAMA + ".ID_PEMERAN_UTAMA";
        static String NAMA_PEMERAN_UTAMA = Entitas.PEMERAN_UTAMA + ".NAMA_PEMERAN_UTAMA";
    }

    static interface PEMINJAMAN {

        static String ID_ANGGOTA = Entitas.PEMINJAMAN + ".ID_ANGGOTA";
        static String ID_FILM = Entitas.PEMINJAMAN + ".ID_FILM";
        static String ID_OPERATOR = Entitas.PEMINJAMAN + ".ID_OPERATOR";
        static String ID_TRANSAKSI = Entitas.PEMINJAMAN + ".ID_TRANSAKSI";
        static String TANGGAL_PEMINJAMAN = Entitas.PEMINJAMAN + ".TANGGAL_PEMINJAMAN";
        static String TANGGAL_PENGEMBALIAN = Entitas.PEMINJAMAN + ".TANGGAL_PENGEMBALIAN";
        static String TELAH_DIKEMBALIKAN = Entitas.PEMINJAMAN + ".TELAH_DIKEMBALIKAN";
    }

    static interface PENDAFTARAN {

        static String ID_ANGGOTA = Entitas.PENDAFTARAN + ".ID_ANGGOTA";
        static String ID_OPERATOR = Entitas.PENDAFTARAN + ".ID_OPERATOR";
        static String TANGGAL_PENDAFTARAN = Entitas.PENDAFTARAN + ".TANGGAL_PENDAFTARAN";
    }

    static interface PENGEMBALIAN {

        static String DENDA_KETERLAMBATAN = Entitas.PENGEMBALIAN + ".DENDA_KETERLAMBATAN";
        static String ID_OPERATOR = Entitas.PENGEMBALIAN + ".ID_OPERATOR";
        static String ID_TRANSAKSI = Entitas.PENGEMBALIAN + ".ID_TRANSAKSI";
        static String TANGGAL_PENGEMBALIAN = Entitas.PENGEMBALIAN + ".TANGGAL_PENGEMBALIAN";
        static String TOTAL_KETERLAMBATAN = Entitas.PENGEMBALIAN + ".TOTAL_KETERLAMBATAN";
    }

    static interface PESAN {

        static String ID_OPERATOR_PENGIRIM = Entitas.PESAN + ".ID_OPERATOR_PENGIRIM";
        static String ID_OPERATOR_TUJUAN = Entitas.PESAN + ".ID_OPERATOR_TUJUAN";
        static String ID_PESAN = Entitas.PESAN + ".ID_PESAN";
        static String ISI_PESAN = Entitas.PESAN + ".ISI_PESAN";
        static String JUDUL_PESAN = Entitas.PESAN + ".JUDUL_PESAN";
        static String WAKTU = Entitas.PESAN + ".WAKTU";
    }

    static interface RUMAH_PRODUKSI {

        static String ID_RUMAH_PRODUKSI = Entitas.RUMAH_PRODUKSI + ".ID_RUMAH_PRODUKSI";
        static String NAMA_RUMAH_PRODUKSI = Entitas.RUMAH_PRODUKSI + ".NAMA_RUMAH_PRODUKSI";
    }
}
