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
 */
package usu.rental.film.sql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import usu.rental.film.data.DefaultAktivitasOperator;
import usu.rental.film.data.DefaultAnggota;
import usu.rental.film.data.DefaultDirektor;
import usu.rental.film.data.DefaultFilm;
import usu.rental.film.data.DefaultJenisFilm;
import usu.rental.film.data.DefaultOperator;
import usu.rental.film.data.DefaultPemeranUtama;
import usu.rental.film.data.DefaultPeminjaman;
import usu.rental.film.data.DefaultPengembalian;
import usu.rental.film.data.DefaultPesan;
import usu.rental.film.data.DefaultRumahProduksi;
import usu.rental.film.data.template.AktivitasOperator;
import usu.rental.film.data.template.Anggota;
import usu.rental.film.data.template.Direktor;
import usu.rental.film.data.template.Film;
import usu.rental.film.data.template.JenisFilm;
import usu.rental.film.data.template.Operator;
import usu.rental.film.data.template.PemeranUtama;
import usu.rental.film.data.template.Peminjaman;
import usu.rental.film.data.template.Pengembalian;
import usu.rental.film.data.template.Pesan;
import usu.rental.film.data.template.RumahProduksi;
import usu.rental.film.sql.exception.DataNotFoundException;
import usu.widget.util.WidgetUtilities;

/**
 * Class ini merupakan class yang berisikan instruksi - instruksi yang berguna sebagai eksekusi
 * database MySQL yang berupa INSERT, UPDATE, DELETE dan lainnya yang berhubungan dengan aplikasi
 * e'MovieRental
 * 
 * @author usu
 * @version 1.0.0
 * @since e'MovieRental 1.0.0
 */
public class MySQL {

    private static DefaultAnggota anggotaTemp;
    private static DefaultDirektor direktorTemp;
    private static DefaultFilm filmTemp;
    private static DefaultJenisFilm jenisFilmTemp;
    private static DefaultOperator operatorTemp;
    private static DefaultPemeranUtama pemeranUtamaTemp;
    private static PreparedStatement prepare;
    private static ResultSet result;
    private static DefaultRumahProduksi rumahProduksiTemp;
    private static String stringTemp;

    /**
     * @param connection
     * @param username
     * @param password
     * @return
     * @throws java.sql.SQLException
     */
    public static Operator containOperator(final Connection connection, final String username,
            final String password) throws SQLException {
        final String SQL = "SELECT * FROM " + Entitas.OPERATOR + " WHERE " + Atribut.OPERATOR.ID_OPERATOR + " =? AND " + Atribut.OPERATOR.PASSWORD_OPERATOR + " =? ";
        MySQL.prepare = connection.prepareStatement(SQL);
        MySQL.prepare.setString(1, username);
        MySQL.prepare.setString(2, password);
        MySQL.result = MySQL.prepare.executeQuery();
        while (MySQL.result.next()) {
            MySQL.operatorTemp = new DefaultOperator();
            MySQL.operatorTemp.setIdOperator(MySQL.result.getString(Atribut.OPERATOR.ID_OPERATOR));
            MySQL.operatorTemp.setPassword(MySQL.result.getString(Atribut.OPERATOR.PASSWORD_OPERATOR));
            if (username.equals(MySQL.operatorTemp.getIdOperator()) && password.equals(MySQL.operatorTemp.getPassword())) {
                MySQL.operatorTemp.setAlamatOperator(MySQL.result.getString(Atribut.OPERATOR.ALAMAT_OPERATOR));
                MySQL.operatorTemp.setKontakOperator(MySQL.result.getString(Atribut.OPERATOR.KONTAK_OPERATOR));
                MySQL.operatorTemp.setNamaOperator(MySQL.result.getString(Atribut.OPERATOR.NAMA_OPERATOR));
                MySQL.operatorTemp.setTanggalLahirOperator(MySQL.result.getDate(Atribut.OPERATOR.TANGGAL_LAHIR_OPERATOR));
                MySQL.result.close();
                MySQL.prepare.close();
                return MySQL.operatorTemp;
            }
        }
        MySQL.result.close();
        MySQL.prepare.close();
        return null;
    }

    /**
     * @param connection
     * @param idAnggota
     * @throws java.sql.SQLException
     */
    public static void deleteAnggota(final Connection connection, final String idAnggota)
            throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.ANGGOTA + " WHERE " + Atribut.ANGGOTA.ID_ANGGOTA + "=?");
        MySQL.prepare.setString(1, idAnggota);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param idDirektor
     * @throws java.sql.SQLException
     */
    public static void deleteDirektor(final Connection connection, final String idDirektor)
            throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.DIREKTOR + " WHERE " + Atribut.DIREKTOR.ID_DIREKTOR + "=?");
        MySQL.prepare.setString(1, idDirektor);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param idFilm
     * @throws java.sql.SQLException
     */
    public static void deleteFilm(final Connection connection, final String idFilm)
            throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.FILM + " WHERE " + Atribut.FILM.ID_FILM + "=?");
        MySQL.prepare.setString(1, idFilm);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param idJenisFilm
     * @throws java.sql.SQLException
     */
    public static void deleteJenisFilm(final Connection connection, final String idJenisFilm)
            throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.JENIS_FILM + " WHERE " + Atribut.JENIS_FILM.ID_JENIS_FILM + "=?");
        MySQL.prepare.setString(1, idJenisFilm);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param idOperator
     * @throws java.sql.SQLException
     */
    public static void deleteOperator(final Connection connection, final String idOperator)
            throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.OPERATOR + " WHERE " + Atribut.OPERATOR.ID_OPERATOR + "=?");
        MySQL.prepare.setString(1, idOperator);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param idPemeranUtama
     * @throws java.sql.SQLException
     */
    public static void deletePemeranUtama(final Connection connection, final String idPemeranUtama)
            throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.PEMERAN_UTAMA + " WHERE " + Atribut.PEMERAN_UTAMA.ID_PEMERAN_UTAMA + "=?");
        MySQL.prepare.setString(1, idPemeranUtama);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param idTransaksi
     * @throws java.sql.SQLException
     */
    public static void deletePeminjaman(final Connection connection, final int idTransaksi)
            throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.PEMINJAMAN + " WHERE " + Atribut.PEMINJAMAN.ID_TRANSAKSI + "=?");
        MySQL.prepare.setInt(1, idTransaksi);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param idTransaksi
     * @throws java.sql.SQLException
     */
    public static void deletePengembalian(final Connection connection, final int idTransaksi)
            throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.PENGEMBALIAN + " WHERE " + Atribut.PENGEMBALIAN.ID_TRANSAKSI + "=?");
        MySQL.prepare.setInt(1, idTransaksi);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param idPesan
     * @throws java.sql.SQLException
     */
    public static void deletePesan(final Connection connection, final int idPesan)
            throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.PESAN + " WHERE " + Atribut.PESAN.ID_PESAN + "=?");
        MySQL.prepare.setInt(1, idPesan);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param idRumahproduksi
     * @throws java.sql.SQLException
     */
    public static void deleteRumahProduksi(final Connection connection, final String idRumahproduksi)
            throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.RUMAH_PRODUKSI + " WHERE " + Atribut.RUMAH_PRODUKSI.ID_RUMAH_PRODUKSI + "=?");
        MySQL.prepare.setString(1, idRumahproduksi);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @throws java.sql.SQLException
     */
    public static void emptyAktivitasOperator(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.AKTIVITAS_OPERATOR);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @throws java.sql.SQLException
     */
    public static void emptyAnggota(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM" + Entitas.ANGGOTA);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @throws java.sql.SQLException
     */
    public static void emptyDirektor(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.DIREKTOR);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @throws java.sql.SQLException
     */
    public static void emptyFilm(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.FILM);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @throws java.sql.SQLException
     */
    public static void emptyJenisFilm(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.JENIS_FILM);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @throws java.sql.SQLException
     */
    public static void emptyOperator(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.OPERATOR);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @throws java.sql.SQLException
     */
    public static void emptyPemeranUtama(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.PEMERAN_UTAMA);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @throws java.sql.SQLException
     */
    public static void emptyPeminjaman(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.PEMINJAMAN);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @throws java.sql.SQLException
     */
    public static void emptyPengembalian(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.PENGEMBALIAN);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @throws java.sql.SQLException
     */
    public static void emptyPesan(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.PESAN);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @throws java.sql.SQLException
     */
    public static void emptyRumahProduksi(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("DELETE FROM " + Entitas.RUMAH_PRODUKSI);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param idAnggota
     * @return
     * @throws usu.rental.film.sql.exception.DataNotFoundException
     * @throws java.sql.SQLException
     */
    public static Anggota getAnggota(final Connection connection, final String idAnggota)
            throws DataNotFoundException, SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.ANGGOTA + " WHERE " + Atribut.ANGGOTA.ID_ANGGOTA + "=?");
        MySQL.prepare.setString(1, idAnggota);
        MySQL.result = MySQL.prepare.executeQuery();
        if (MySQL.result.next()) {
            MySQL.anggotaTemp = new DefaultAnggota();
            MySQL.anggotaTemp.setAlamatAnggota(MySQL.result.getString(Atribut.ANGGOTA.ALAMAT_ANGGOTA));
            MySQL.anggotaTemp.setIdAnggota(MySQL.result.getString(Atribut.ANGGOTA.ID_ANGGOTA));
            MySQL.anggotaTemp.setKontakAnggota(MySQL.result.getString(Atribut.ANGGOTA.KONTAK_ANGGOTA));
            MySQL.anggotaTemp.setNamaAnggota(MySQL.result.getString(Atribut.ANGGOTA.NAMA_ANGGOTA));
            MySQL.anggotaTemp.setTanggalLahirAnggota(MySQL.result.getDate(Atribut.ANGGOTA.TANGGAL_LAHIR_ANGGOTA));
            MySQL.anggotaTemp.setTotalmeminjam(MySQL.result.getInt(Atribut.ANGGOTA.TOTAL_MEMINJAM));
            MySQL.prepare.close();
            MySQL.result.close();
            return MySQL.anggotaTemp;
        }
        MySQL.prepare.close();
        MySQL.result.close();
        throw new DataNotFoundException("anggota dengan id " + idAnggota + " tidak ditemukan dalam database");
    }

    /**
     * @param connection
     * @param idDirektor
     * @return
     * @throws usu.rental.film.sql.exception.DataNotFoundException
     * @throws java.sql.SQLException
     */
    public static Direktor getDirektor(final Connection connection, final String idDirektor)
            throws DataNotFoundException, SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.DIREKTOR + " WHERE " + Atribut.DIREKTOR.ID_DIREKTOR + "=?");
        MySQL.prepare.setString(1, idDirektor);
        MySQL.result = MySQL.prepare.executeQuery();
        if (MySQL.result.next()) {
            MySQL.direktorTemp = new DefaultDirektor();
            MySQL.direktorTemp.setIdDirektor(MySQL.result.getString(Atribut.DIREKTOR.ID_DIREKTOR));
            MySQL.direktorTemp.setNamaDirektor(MySQL.result.getString(Atribut.DIREKTOR.NAMA_DIREKTOR));
            MySQL.prepare.close();
            MySQL.result.close();
            return MySQL.direktorTemp;
        }
        MySQL.prepare.close();
        MySQL.result.close();
        throw new DataNotFoundException("direktor dengan id " + idDirektor + " tidak ditemukan dalam database");
    }

    /**
     * @param connection
     * @param idFilm
     * @return
     * @throws usu.rental.film.sql.exception.DataNotFoundException
     * @throws java.sql.SQLException
     */
    public static Film getFilm(final Connection connection, final String idFilm)
            throws DataNotFoundException, SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.FILM + " WHERE " + Atribut.FILM.ID_FILM + "=?");
        MySQL.prepare.setString(1, idFilm);
        MySQL.result = MySQL.prepare.executeQuery();
        if (MySQL.result.next()) {
            MySQL.filmTemp = new DefaultFilm();
            MySQL.filmTemp.setDipinjam(MySQL.result.getBoolean(Atribut.FILM.DIPINJAM));
            MySQL.filmTemp.setDirektor(MySQL.getNamaDirektor(connection, MySQL.result.getString(Atribut.FILM.ID_DIREKTOR)));
            MySQL.filmTemp.setIdFilm(MySQL.result.getString(Atribut.FILM.ID_FILM));
            MySQL.filmTemp.setJenisFilm(MySQL.getNamaJenisFilm(connection, MySQL.result.getString(Atribut.FILM.ID_JENIS_FILM)));
            MySQL.filmTemp.setJudulFilm(MySQL.result.getString(Atribut.FILM.JUDUL_FILM));
            MySQL.filmTemp.setPemeranUtama(MySQL.getNamaPemeranUtama(connection, MySQL.result.getString(Atribut.FILM.ID_PEMERAN_UTAMA)));
            MySQL.filmTemp.setRumahProduksi(MySQL.getNamaRumahProduksi(connection, MySQL.result.getString(Atribut.FILM.ID_RUMAH_PRODUKSI)));
            MySQL.filmTemp.setTotalDipinjam(MySQL.result.getInt(Atribut.FILM.TOTAL_DIPINJAM));
            MySQL.prepare.close();
            MySQL.result.close();
            return MySQL.filmTemp;
        }
        MySQL.prepare.close();
        MySQL.result.close();
        throw new DataNotFoundException("film dengan id " + idFilm + " tidak ditemukan dalam database");
    }

    /**
     * @param connection
     * @param idFilm
     * @return
     * @throws java.sql.SQLException
     * @throws usu.rental.film.sql.exception.DataNotFoundException
     */
    public static Film getFilmOriginal(final Connection connection, final String idFilm)
            throws SQLException, DataNotFoundException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.FILM + " WHERE " + Atribut.FILM.ID_FILM + "=?");
        MySQL.prepare.setString(1, idFilm);
        MySQL.result = MySQL.prepare.executeQuery();
        if (MySQL.result.next()) {
            MySQL.filmTemp = new DefaultFilm();
            MySQL.filmTemp.setDipinjam(MySQL.result.getBoolean(Atribut.FILM.DIPINJAM));
            MySQL.filmTemp.setDirektor(MySQL.result.getString(Atribut.FILM.ID_DIREKTOR));
            MySQL.filmTemp.setIdFilm(MySQL.result.getString(Atribut.FILM.ID_FILM));
            MySQL.filmTemp.setJenisFilm(MySQL.result.getString(Atribut.FILM.ID_JENIS_FILM));
            MySQL.filmTemp.setJudulFilm(MySQL.result.getString(Atribut.FILM.JUDUL_FILM));
            MySQL.filmTemp.setPemeranUtama(MySQL.result.getString(Atribut.FILM.ID_PEMERAN_UTAMA));
            MySQL.filmTemp.setRumahProduksi(MySQL.result.getString(Atribut.FILM.ID_RUMAH_PRODUKSI));
            MySQL.filmTemp.setTotalDipinjam(MySQL.result.getInt(Atribut.FILM.TOTAL_DIPINJAM));
            return MySQL.filmTemp;
        }
        throw new DataNotFoundException("film dengan id " + idFilm + " tidak ditemukan dalam database");
    }

    /**
     * @param connection
     * @param idJenisFilm
     * @return
     * @throws usu.rental.film.sql.exception.DataNotFoundException
     * @throws java.sql.SQLException
     */
    public static JenisFilm getJenisFilm(final Connection connection, final String idJenisFilm)
            throws DataNotFoundException, SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.JENIS_FILM + " WHERE " + Atribut.JENIS_FILM.ID_JENIS_FILM + "=?");
        MySQL.prepare.setString(1, idJenisFilm);
        MySQL.result = MySQL.prepare.executeQuery();
        if (MySQL.result.next()) {
            MySQL.jenisFilmTemp = new DefaultJenisFilm();
            MySQL.jenisFilmTemp.setIdJenisFilm(MySQL.result.getString(Atribut.JENIS_FILM.ID_JENIS_FILM));
            MySQL.jenisFilmTemp.setNamaJenisFilm(MySQL.result.getString(Atribut.JENIS_FILM.NAMA_JENIS_FILM));
            MySQL.prepare.close();
            MySQL.result.close();
            return MySQL.jenisFilmTemp;
        }
        MySQL.prepare.close();
        MySQL.result.close();
        throw new DataNotFoundException("jenis film dengan id " + idJenisFilm + " tak ditemukan dalam database");
    }

    /**
     * @param result
     * @return
     * @throws java.sql.SQLException
     */
    public static List<AktivitasOperator> getListAktivitasAnggota(final ResultSet result)
            throws SQLException {
        final ArrayList<AktivitasOperator> list = new ArrayList<AktivitasOperator>();
        while (result.next()) {
            final DefaultAktivitasOperator data = new DefaultAktivitasOperator();
            data.setOperator(result.getString(Atribut.AKTIVITAS_OPERATOR.ID_OPERATOR));
            data.setAktivitas(result.getString(Atribut.AKTIVITAS_OPERATOR.AKTIVITAS_OPERATOR));
            data.setWaktu(result.getTimestamp(Atribut.AKTIVITAS_OPERATOR.WAKTU));
            list.add(data);
        }
        return list;
    }

    /**
     * @param result
     * @return
     * @throws java.sql.SQLException
     */
    public static List<Anggota> getListAnggota(final ResultSet result) throws SQLException {
        final ArrayList<Anggota> list = new ArrayList<Anggota>();
        while (result.next()) {
            final DefaultAnggota data = new DefaultAnggota();
            data.setAlamatAnggota(result.getString(Atribut.ANGGOTA.ALAMAT_ANGGOTA));
            data.setIdAnggota(result.getString(Atribut.ANGGOTA.ID_ANGGOTA));
            data.setKontakAnggota(result.getString(Atribut.ANGGOTA.KONTAK_ANGGOTA));
            data.setNamaAnggota(result.getString(Atribut.ANGGOTA.NAMA_ANGGOTA));
            data.setTanggalLahirAnggota(result.getDate(Atribut.ANGGOTA.TANGGAL_LAHIR_ANGGOTA));
            data.setTotalmeminjam(result.getInt(Atribut.ANGGOTA.TOTAL_MEMINJAM));
            list.add(data);
        }
        return list;
    }

    /**
     * @param result
     * @return
     * @throws java.sql.SQLException
     */
    public static List<Direktor> getListDirektor(final ResultSet result) throws SQLException {
        final ArrayList<Direktor> list = new ArrayList<Direktor>();
        while (result.next()) {
            final Direktor data = new DefaultDirektor();
            data.setIdDirektor(result.getString(Atribut.DIREKTOR.ID_DIREKTOR));
            data.setNamaDirektor(result.getString(Atribut.DIREKTOR.NAMA_DIREKTOR));
            list.add(data);
        }
        return list;
    }

    /**
     * @param connection
     * @param result
     * @return
     * @throws java.sql.SQLException
     * @throws usu.rental.film.sql.exception.DataNotFoundException
     */
    public static List<Film> getListFilm(final Connection connection, final ResultSet result)
            throws SQLException, DataNotFoundException {
        final ArrayList<Film> list = new ArrayList<Film>();
        while (result.next()) {
            final Film data = new DefaultFilm();
            data.setDipinjam(result.getBoolean(Atribut.FILM.DIPINJAM));
            data.setDirektor(MySQL.getNamaDirektor(connection, result.getString(Atribut.FILM.ID_DIREKTOR)));
            data.setIdFilm(result.getString(Atribut.FILM.ID_FILM));
            data.setJenisFilm(MySQL.getNamaJenisFilm(connection, result.getString(Atribut.FILM.ID_JENIS_FILM)));
            data.setJudulFilm(result.getString(Atribut.FILM.JUDUL_FILM));
            data.setPemeranUtama(MySQL.getNamaPemeranUtama(connection, result.getString(Atribut.FILM.ID_PEMERAN_UTAMA)));
            data.setRumahProduksi(MySQL.getNamaRumahProduksi(connection, result.getString(Atribut.FILM.ID_RUMAH_PRODUKSI)));
            data.setTotalDipinjam(result.getInt(Atribut.FILM.TOTAL_DIPINJAM));
            list.add(data);
        }
        return list;
    }

    /**
     * @param result
     * @return
     * @throws java.sql.SQLException
     */
    public static List<JenisFilm> getListJenisFilm(final ResultSet result) throws SQLException {
        final ArrayList<JenisFilm> list = new ArrayList<JenisFilm>();
        while (result.next()) {
            final JenisFilm data = new DefaultJenisFilm();
            data.setIdJenisFilm(result.getString(Atribut.JENIS_FILM.ID_JENIS_FILM));
            data.setNamaJenisFilm(result.getString(Atribut.JENIS_FILM.NAMA_JENIS_FILM));
            list.add(data);
        }
        return list;
    }

    /**
     * @param result
     * @return
     * @throws java.sql.SQLException
     */
    public static List<Operator> getListOperator(final ResultSet result) throws SQLException {
        final ArrayList<Operator> list = new ArrayList<Operator>();
        while (result.next()) {
            Operator data = new DefaultOperator();
            data = new DefaultOperator();
            data.setAlamatOperator(result.getString(Atribut.OPERATOR.ALAMAT_OPERATOR));
            data.setIdOperator(result.getString(Atribut.OPERATOR.ID_OPERATOR));
            data.setKontakOperator(result.getString(Atribut.OPERATOR.KONTAK_OPERATOR));
            data.setNamaOperator(result.getString(Atribut.OPERATOR.NAMA_OPERATOR));
            data.setTanggalLahirOperator(result.getDate(Atribut.OPERATOR.TANGGAL_LAHIR_OPERATOR));
            data.setPassword(result.getString(Atribut.OPERATOR.PASSWORD_OPERATOR));
            list.add(data);
        }
        return list;
    }

    /**
     * @param result
     * @return
     * @throws java.sql.SQLException
     */
    public static List<PemeranUtama> getListpemeranUtama(final ResultSet result) throws SQLException {
        final ArrayList<PemeranUtama> list = new ArrayList<PemeranUtama>();
        while (result.next()) {
            final PemeranUtama data = new DefaultPemeranUtama();
            data.setIdPemeranUtama(result.getString(Atribut.PEMERAN_UTAMA.ID_PEMERAN_UTAMA));
            data.setNamaPemeranUtama(result.getString(Atribut.PEMERAN_UTAMA.NAMA_PEMERAN_UTAMA));
            list.add(data);
        }
        return list;
    }

    /**
     * @param connection
     * @param idAnggota
     * @param dikembalikan
     * @return
     * @throws java.sql.SQLException
     */
    public static List<Peminjaman> getListPeminjaman(final Connection connection,
            final String idAnggota, final boolean dikembalikan) throws SQLException {
        final String SQL = "SELECT * FROM " + Entitas.PEMINJAMAN + " WHERE " + Atribut.PEMINJAMAN.ID_ANGGOTA + "=? AND " + Atribut.PEMINJAMAN.TELAH_DIKEMBALIKAN + "=?";
        MySQL.prepare = connection.prepareStatement(SQL);
        MySQL.prepare.setString(1, idAnggota);
        MySQL.prepare.setBoolean(2, dikembalikan);
        MySQL.result = MySQL.prepare.executeQuery();
        final ArrayList<Peminjaman> list = new ArrayList<Peminjaman>();
        while (MySQL.result.next()) {
            final Peminjaman data = new DefaultPeminjaman();
            data.setAnggota(MySQL.result.getString(Atribut.PEMINJAMAN.ID_ANGGOTA));
            data.setFilm(MySQL.result.getString(Atribut.PEMINJAMAN.ID_FILM));
            data.setIdTransaksi(MySQL.result.getInt(Atribut.PEMINJAMAN.ID_TRANSAKSI));
            data.setOperator(MySQL.result.getString(Atribut.PEMINJAMAN.ID_OPERATOR));
            data.setTanggalPeminjaman(MySQL.result.getDate(Atribut.PEMINJAMAN.TANGGAL_PEMINJAMAN));
            data.setTanggalPengembalian(MySQL.result.getDate(Atribut.PEMINJAMAN.TANGGAL_PENGEMBALIAN));
            data.setTelahDikembalikan(MySQL.result.getBoolean(Atribut.PEMINJAMAN.TELAH_DIKEMBALIKAN));
            list.add(data);
        }
        return list;
    }

    /**
     * @param result
     * @return
     * @throws java.sql.SQLException
     */
    public static List<Peminjaman> getListPeminjaman(final ResultSet result) throws SQLException {
        final ArrayList<Peminjaman> list = new ArrayList<Peminjaman>();
        while (result.next()) {
            final Peminjaman data = new DefaultPeminjaman();
            data.setAnggota(result.getString(Atribut.PEMINJAMAN.ID_ANGGOTA));
            data.setFilm(result.getString(Atribut.PEMINJAMAN.ID_FILM));
            data.setIdTransaksi(result.getInt(Atribut.PEMINJAMAN.ID_TRANSAKSI));
            data.setOperator(result.getString(Atribut.PEMINJAMAN.ID_OPERATOR));
            data.setTanggalPeminjaman(result.getDate(Atribut.PEMINJAMAN.TANGGAL_PEMINJAMAN));
            data.setTanggalPengembalian(result.getDate(Atribut.PEMINJAMAN.TANGGAL_PENGEMBALIAN));
            data.setTelahDikembalikan(result.getBoolean(Atribut.PEMINJAMAN.TELAH_DIKEMBALIKAN));
            list.add(data);
        }
        return list;
    }

    /**
     * @param result
     * @return
     * @throws java.sql.SQLException
     */
    public static List<Pengembalian> getListPengembalian(final ResultSet result) throws SQLException {
        final ArrayList<Pengembalian> list = new ArrayList<Pengembalian>();
        while (result.next()) {
            final Pengembalian data = new DefaultPengembalian();
            data.setDendaKeterlambatan(result.getInt(Atribut.PENGEMBALIAN.DENDA_KETERLAMBATAN));
            data.setIdTransaksi(result.getInt(Atribut.PENGEMBALIAN.ID_TRANSAKSI));
            data.setOperator(result.getString(Atribut.PENGEMBALIAN.ID_OPERATOR));
            data.setTanggalPengembalian(result.getDate(Atribut.PENGEMBALIAN.TANGGAL_PENGEMBALIAN));
            data.setTotalHariTerlambat(result.getInt(Atribut.PENGEMBALIAN.TOTAL_KETERLAMBATAN));
            list.add(data);
        }
        return list;
    }

    /**
     * @param result
     * @return
     * @throws java.sql.SQLException
     */
    public static List<Pesan> getListPesan(final ResultSet result) throws SQLException {
        final ArrayList<Pesan> list = new ArrayList<Pesan>();
        while (result.next()) {
            final Pesan data = new DefaultPesan();
            data.setIdPesan(result.getInt(Atribut.PESAN.ID_PESAN));
            data.setIsiPesan(result.getString(Atribut.PESAN.ISI_PESAN));
            data.setJudulPesan(result.getString(Atribut.PESAN.JUDUL_PESAN));
            data.setOperatorPengirim(result.getString(Atribut.PESAN.ID_OPERATOR_PENGIRIM));
            data.setOperatorTujuan(result.getString(Atribut.PESAN.ID_OPERATOR_TUJUAN));
            data.setWaktuPengiriman(result.getDate(Atribut.PESAN.WAKTU));
            list.add(data);
        }
        return list;
    }

    /**
     * @param result
     * @return
     * @throws java.sql.SQLException
     */
    public static List<RumahProduksi> getListRumahProduksi(final ResultSet result)
            throws SQLException {
        final ArrayList<RumahProduksi> list = new ArrayList<RumahProduksi>();
        while (result.next()) {
            final RumahProduksi data = new DefaultRumahProduksi();
            data.setIdRumahProduksi(result.getString(Atribut.RUMAH_PRODUKSI.ID_RUMAH_PRODUKSI));
            data.setNamaRumahProduksi(result.getString(Atribut.RUMAH_PRODUKSI.NAMA_RUMAH_PRODUKSI));
            list.add(data);
        }
        return list;
    }

    /**
     * @param connection
     * @param idDirektor
     * @return
     * @throws usu.rental.film.sql.exception.DataNotFoundException
     * @throws java.sql.SQLException
     */
    public static String getNamaDirektor(final Connection connection, final String idDirektor)
            throws DataNotFoundException, SQLException {
        final PreparedStatement p = connection.prepareStatement("SELECT " + Atribut.DIREKTOR.NAMA_DIREKTOR + " FROM " + Entitas.DIREKTOR + " WHERE " + Atribut.DIREKTOR.ID_DIREKTOR + "=?");
        p.setString(1, idDirektor);
        final ResultSet r = p.executeQuery();
        if (r.next()) {
            MySQL.stringTemp = r.getString(Atribut.DIREKTOR.NAMA_DIREKTOR);
            p.close();
            r.close();
            return MySQL.stringTemp;
        }
        p.close();
        r.close();
        throw new DataNotFoundException();
    }

    /**
     * @param connection
     * @param idJenisFilm
     * @return
     * @throws usu.rental.film.sql.exception.DataNotFoundException
     * @throws java.sql.SQLException
     */
    public static String getNamaJenisFilm(final Connection connection, final String idJenisFilm)
            throws DataNotFoundException, SQLException {
        final PreparedStatement p = connection.prepareStatement("SELECT " + Atribut.JENIS_FILM.NAMA_JENIS_FILM + " FROM " + Entitas.JENIS_FILM + " WHERE " + Atribut.JENIS_FILM.ID_JENIS_FILM + "=?");
        p.setString(1, idJenisFilm);
        final ResultSet r = p.executeQuery();
        if (r.next()) {
            MySQL.stringTemp = r.getString(Atribut.JENIS_FILM.NAMA_JENIS_FILM);
            p.close();
            r.close();
            return MySQL.stringTemp;
        }
        p.close();
        r.close();
        throw new DataNotFoundException("Id " + idJenisFilm + " tak ditemukan dalam database jenis buku");
    }

    /**
     * @param connection
     * @param idPemeranUtama
     * @return
     * @throws usu.rental.film.sql.exception.DataNotFoundException
     * @throws java.sql.SQLException
     */
    public static String getNamaPemeranUtama(final Connection connection, final String idPemeranUtama)
            throws DataNotFoundException, SQLException {
        final PreparedStatement p = connection.prepareStatement("SELECT * FROM " + Entitas.PEMERAN_UTAMA + " WHERE " + Atribut.PEMERAN_UTAMA.ID_PEMERAN_UTAMA + "=?");
        p.setString(1, idPemeranUtama);
        final ResultSet r = p.executeQuery();
        if (r.next()) {
            MySQL.stringTemp = r.getString(Atribut.PEMERAN_UTAMA.NAMA_PEMERAN_UTAMA);
            p.close();
            r.close();
            return MySQL.stringTemp;
        }
        r.close();
        p.close();
        throw new DataNotFoundException("Id " + idPemeranUtama + " tak ditemukan dalam database pemeran utama");
    }

    /**
     * @param connection
     * @param idRumahProduksi
     * @return
     * @throws usu.rental.film.sql.exception.DataNotFoundException
     * @throws java.sql.SQLException
     */
    public static String getNamaRumahProduksi(final Connection connection,
            final String idRumahProduksi) throws DataNotFoundException, SQLException {
        final PreparedStatement p = connection.prepareStatement("SELECT " + Atribut.RUMAH_PRODUKSI.NAMA_RUMAH_PRODUKSI + " FROM " + Entitas.RUMAH_PRODUKSI + " WHERE " + Atribut.RUMAH_PRODUKSI.ID_RUMAH_PRODUKSI + "=?");
        p.setString(1, idRumahProduksi);
        final ResultSet r = p.executeQuery();
        if (r.next()) {
            MySQL.stringTemp = r.getString(Atribut.RUMAH_PRODUKSI.NAMA_RUMAH_PRODUKSI);
            p.close();
            r.close();
            return MySQL.stringTemp;
        }
        p.close();
        r.close();
        throw new DataNotFoundException("Id " + idRumahProduksi + " tak ditemukan dalam database rumah produksi");
    }

    /**
     * @param connection
     * @param idOperator
     * @return
     * @throws usu.rental.film.sql.exception.DataNotFoundException
     * @throws java.sql.SQLException
     */
    public static Operator getOperator(final Connection connection, final String idOperator)
            throws DataNotFoundException, SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.OPERATOR + " WHERE " + Atribut.OPERATOR.ID_OPERATOR + "=?");
        MySQL.prepare.setString(1, idOperator);
        MySQL.result = MySQL.prepare.executeQuery();
        if (MySQL.result.next()) {
            MySQL.operatorTemp = new DefaultOperator();
            MySQL.operatorTemp.setAlamatOperator(MySQL.result.getString(Atribut.OPERATOR.ALAMAT_OPERATOR));
            MySQL.operatorTemp.setIdOperator(MySQL.result.getString(Atribut.OPERATOR.ID_OPERATOR));
            MySQL.operatorTemp.setKontakOperator(MySQL.result.getString(Atribut.OPERATOR.KONTAK_OPERATOR));
            MySQL.operatorTemp.setNamaOperator(MySQL.result.getString(Atribut.OPERATOR.NAMA_OPERATOR));
            MySQL.operatorTemp.setTanggalLahirOperator(MySQL.result.getDate(Atribut.OPERATOR.TANGGAL_LAHIR_OPERATOR));
            MySQL.operatorTemp.setPassword(MySQL.result.getString(Atribut.OPERATOR.PASSWORD_OPERATOR));
            MySQL.prepare.close();
            MySQL.result.close();
            return MySQL.operatorTemp;
        }
        MySQL.prepare.close();
        MySQL.result.close();
        throw new DataNotFoundException("Id " + idOperator + " tak ditemukan dalam database operator");
    }

    /**
     * @param connection
     * @param idpemeranUtama
     * @return
     * @throws usu.rental.film.sql.exception.DataNotFoundException
     * @throws java.sql.SQLException
     */
    public static PemeranUtama getPemeranUtama(final Connection connection,
            final String idpemeranUtama) throws DataNotFoundException, SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.PEMERAN_UTAMA + " WHERE " + Atribut.PEMERAN_UTAMA.ID_PEMERAN_UTAMA + "=?");
        MySQL.prepare.setString(1, idpemeranUtama);
        MySQL.result = MySQL.prepare.executeQuery();
        if (MySQL.result.next()) {
            MySQL.pemeranUtamaTemp = new DefaultPemeranUtama();
            MySQL.pemeranUtamaTemp.setIdPemeranUtama(MySQL.result.getString(Atribut.PEMERAN_UTAMA.ID_PEMERAN_UTAMA));
            MySQL.pemeranUtamaTemp.setNamaPemeranUtama(MySQL.result.getString(Atribut.PEMERAN_UTAMA.NAMA_PEMERAN_UTAMA));
            MySQL.prepare.close();
            MySQL.result.close();
            return MySQL.pemeranUtamaTemp;
        }
        MySQL.prepare.close();
        MySQL.result.close();
        throw new DataNotFoundException("Id " + idpemeranUtama + " tak ditemukan dalam database pemeran utama");
    }

    /**
     * @param connection
     * @param idRumahProduksi
     * @return
     * @throws usu.rental.film.sql.exception.DataNotFoundException
     * @throws java.sql.SQLException
     */
    public static RumahProduksi getRumahProduksi(final Connection connection,
            final String idRumahProduksi) throws DataNotFoundException, SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.RUMAH_PRODUKSI + " WHERE " + Atribut.RUMAH_PRODUKSI.ID_RUMAH_PRODUKSI + "=?");
        MySQL.prepare.setString(1, idRumahProduksi);
        MySQL.result = MySQL.prepare.executeQuery();
        if (MySQL.result.next()) {
            MySQL.rumahProduksiTemp = new DefaultRumahProduksi();
            MySQL.rumahProduksiTemp.setIdRumahProduksi(MySQL.result.getString(Atribut.RUMAH_PRODUKSI.ID_RUMAH_PRODUKSI));
            MySQL.rumahProduksiTemp.setNamaRumahProduksi(MySQL.result.getString(Atribut.RUMAH_PRODUKSI.NAMA_RUMAH_PRODUKSI));
            MySQL.prepare.close();
            MySQL.result.close();
            return MySQL.rumahProduksiTemp;
        }
        MySQL.prepare.close();
        MySQL.result.close();
        throw new DataNotFoundException("Id " + idRumahProduksi + " tak ditemukan dalam database rumah produksi");
    }

    /**
     * @param connection
     * @param idTransaksi
     * @return
     * @throws java.sql.SQLException
     */
    public static int getTerlambatPengembalian(final Connection connection, final int idTransaksi)
            throws SQLException {
        PreparedStatement p = connection.prepareStatement("SELECT " + Atribut.PEMINJAMAN.TANGGAL_PENGEMBALIAN + " FROM " + Entitas.PEMINJAMAN + " WHERE " + Atribut.PEMINJAMAN.ID_TRANSAKSI + "=?");
        p.setInt(1, idTransaksi);
        MySQL.result = p.executeQuery();
        MySQL.result.next();
        p = connection.prepareStatement("SELECT DATEDIFF(CURDATE(),?) AS DIFF");
        p.setDate(1, MySQL.result.getDate(Atribut.PEMINJAMAN.TANGGAL_PENGEMBALIAN));
        MySQL.result = p.executeQuery();
        MySQL.result.next();
        final int temp = MySQL.result.getInt("DIFF");
        p.close();
        MySQL.result.close();
        return temp;
    }

    /**
     * @param connection
     * @param anggota
     * @return
     * @throws java.sql.SQLException
     */
    public static Integer getTotalMeminjam(final Connection connection, final String anggota)
            throws SQLException {
        final String SQL = "SELECT COUNT(" + Atribut.PEMINJAMAN.ID_ANGGOTA + ") AS TOTAL FROM " + Entitas.PEMINJAMAN + " WHERE " + Atribut.PEMINJAMAN.TELAH_DIKEMBALIKAN + " =? AND " + Atribut.PEMINJAMAN.ID_ANGGOTA + "=?";
        MySQL.prepare = connection.prepareStatement(SQL);
        MySQL.prepare.setBoolean(1, false);
        MySQL.prepare.setString(2, anggota);
        MySQL.result = MySQL.prepare.executeQuery();
        if (MySQL.result.next()) {
            return MySQL.result.getInt("TOTAL");
        }
        return 0;
    }

    /**
     * @param connection
     * @param aktivitasOperator
     * @throws java.sql.SQLException
     */
    public static void insertAktivitasOperator(final Connection connection,
            final AktivitasOperator aktivitasOperator) throws SQLException {
        MySQL.prepare = connection.prepareStatement("INSERT INTO " + Entitas.AKTIVITAS_OPERATOR + " SET " + Atribut.AKTIVITAS_OPERATOR.ID_OPERATOR + "=?," + Atribut.AKTIVITAS_OPERATOR.AKTIVITAS_OPERATOR + "=?");
        MySQL.prepare.setString(1, aktivitasOperator.getOperator());
        MySQL.prepare.setString(2, aktivitasOperator.getAktivitas());
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param anggota
     * @throws java.sql.SQLException
     */
    public static void insertAnggota(final Connection connection, final Anggota anggota)
            throws SQLException {
        MySQL.prepare = connection.prepareStatement("INSERT INTO " + Entitas.ANGGOTA + " SET " + Atribut.ANGGOTA.ALAMAT_ANGGOTA + "=?," + Atribut.ANGGOTA.ID_ANGGOTA + "=?," + Atribut.ANGGOTA.KONTAK_ANGGOTA + "=?," + Atribut.ANGGOTA.NAMA_ANGGOTA + "=?," + Atribut.ANGGOTA.TANGGAL_LAHIR_ANGGOTA + "=?," + Atribut.ANGGOTA.TOTAL_MEMINJAM + "=?");
        MySQL.prepare.setString(1, anggota.getAlamatAnggota());
        MySQL.prepare.setString(2, anggota.getIdAnggota());
        MySQL.prepare.setString(3, anggota.getKontakAnggota());
        MySQL.prepare.setString(4, anggota.getNamaAnggota());
        MySQL.prepare.setDate(5, anggota.getTanggalLahirAnggota());
        MySQL.prepare.setInt(6, anggota.getTotalmeminjam());
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param direktor
     * @throws java.sql.SQLException
     */
    public static void insertDirektor(final Connection connection, final Direktor direktor)
            throws SQLException {
        MySQL.prepare = connection.prepareStatement("INSERT INTO " + Entitas.DIREKTOR + " SET " + Atribut.DIREKTOR.ID_DIREKTOR + "=?," + Atribut.DIREKTOR.NAMA_DIREKTOR + "=?");
        MySQL.prepare.setString(1, direktor.getIdDirektor());
        MySQL.prepare.setString(2, direktor.getNamaDirektor());
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param film
     * @throws java.sql.SQLException
     */
    public static void insertFilm(final Connection connection, final Film film) throws SQLException {
        MySQL.prepare = connection.prepareStatement("INSERT INTO " + Entitas.FILM + " SET " + Atribut.FILM.DIPINJAM + "=?," + Atribut.FILM.ID_DIREKTOR + "=?," + Atribut.FILM.ID_FILM + "=?," + Atribut.FILM.ID_JENIS_FILM + "=?," + Atribut.FILM.ID_PEMERAN_UTAMA + "=?," + Atribut.FILM.ID_RUMAH_PRODUKSI + "=?," + Atribut.FILM.JUDUL_FILM + "=?," + Atribut.FILM.TOTAL_DIPINJAM + "=?");
        MySQL.prepare.setBoolean(1, film.isDipinjam());
        MySQL.prepare.setString(2, film.getDirektor());
        MySQL.prepare.setString(3, film.getIdFilm());
        MySQL.prepare.setString(4, film.getJenisFilm());
        MySQL.prepare.setString(5, film.getPemeranUtama());
        MySQL.prepare.setString(6, film.getRumahProduksi());
        MySQL.prepare.setString(7, film.getJudulFilm());
        MySQL.prepare.setInt(8, 0);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param jenisfilm
     * @throws java.sql.SQLException
     */
    public static void insertJenisFilm(final Connection connection, final JenisFilm jenisfilm)
            throws SQLException {
        MySQL.prepare = connection.prepareStatement("INSERT INTO " + Entitas.JENIS_FILM + " SET " + Atribut.JENIS_FILM.ID_JENIS_FILM + "=?," + Atribut.JENIS_FILM.NAMA_JENIS_FILM + "=?");
        MySQL.prepare.setString(1, jenisfilm.getIdJenisFilm());
        MySQL.prepare.setString(2, jenisfilm.getNamaJenisFilm());
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param operator
     * @throws java.sql.SQLException
     */
    public static void insertOperator(final Connection connection, final Operator operator)
            throws SQLException {
        MySQL.prepare = connection.prepareStatement("INSERT INTO " + Entitas.OPERATOR + " SET " + Atribut.OPERATOR.ALAMAT_OPERATOR + "=?," + Atribut.OPERATOR.ID_OPERATOR + "=?," + Atribut.OPERATOR.KONTAK_OPERATOR + "=?," + Atribut.OPERATOR.NAMA_OPERATOR + "=?," + Atribut.OPERATOR.TANGGAL_LAHIR_OPERATOR + "=?," + Atribut.OPERATOR.PASSWORD_OPERATOR + "=?");
        MySQL.prepare.setString(1, operator.getAlamatOperator());
        MySQL.prepare.setString(2, operator.getIdOperator());
        MySQL.prepare.setString(3, operator.getKontakOperator());
        MySQL.prepare.setString(4, operator.getNamaOperator());
        MySQL.prepare.setDate(5, operator.getTanggalLahirOperator());
        MySQL.prepare.setString(6, operator.getPassword());
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param pemeranUtama
     * @throws java.sql.SQLException
     */
    public static void insertPemeranUtama(final Connection connection,
            final PemeranUtama pemeranUtama) throws SQLException {
        MySQL.prepare = connection.prepareStatement("INSERT INTO " + Entitas.PEMERAN_UTAMA + " SET " + Atribut.PEMERAN_UTAMA.ID_PEMERAN_UTAMA + "=?," + Atribut.PEMERAN_UTAMA.NAMA_PEMERAN_UTAMA + "=?");
        MySQL.prepare.setString(1, pemeranUtama.getIdPemeranUtama());
        MySQL.prepare.setString(2, pemeranUtama.getNamaPemeranUtama());
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param peminjaman
     * @param intervalDay
     * @throws java.sql.SQLException
     */
    public static void insertPeminjaman(final Connection connection, final Peminjaman peminjaman,
            final int intervalDay) throws SQLException {
        MySQL.prepare = connection.prepareStatement("INSERT INTO " + Entitas.PEMINJAMAN + " SET " + Atribut.PEMINJAMAN.ID_ANGGOTA + "=?," + Atribut.PEMINJAMAN.ID_FILM + "=?," + Atribut.PEMINJAMAN.ID_OPERATOR + "=?," + Atribut.PEMINJAMAN.ID_TRANSAKSI + "=null," + Atribut.PEMINJAMAN.TANGGAL_PEMINJAMAN + "=CURDATE()," + Atribut.PEMINJAMAN.TANGGAL_PENGEMBALIAN + "=DATE_ADD(CURDATE(), INTERVAL " + intervalDay + " DAY)," + Atribut.PEMINJAMAN.TELAH_DIKEMBALIKAN + "=?");
        MySQL.prepare.setString(1, peminjaman.getAnggota());
        MySQL.prepare.setString(2, peminjaman.getFilm());
        MySQL.prepare.setString(3, peminjaman.getOperator());
        MySQL.prepare.setBoolean(4, false);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param pengembalian
     * @param dendaPerHari
     * @throws java.sql.SQLException
     */
    public static void insertPengembalian(final Connection connection,
            final Pengembalian pengembalian, final int dendaPerHari) throws SQLException {
        MySQL.prepare = connection.prepareStatement("INSERT INTO " + Entitas.PENGEMBALIAN + " SET " + Atribut.PENGEMBALIAN.ID_OPERATOR + "=?," + Atribut.PENGEMBALIAN.ID_TRANSAKSI + "=?," + Atribut.PENGEMBALIAN.TANGGAL_PENGEMBALIAN + "=CURDATE()," + Atribut.PENGEMBALIAN.TOTAL_KETERLAMBATAN + "=?," + Atribut.PENGEMBALIAN.DENDA_KETERLAMBATAN + "=?");
        MySQL.prepare.setString(1, pengembalian.getOperator());
        MySQL.prepare.setInt(2, pengembalian.getIdTransaksi());
        final int temp = MySQL.getTerlambatPengembalian(connection, pengembalian.getIdTransaksi());
        if (temp > 0) {
            MySQL.prepare.setInt(3, temp);
        } else {
            MySQL.prepare.setInt(3, 0);
        }
        if (temp > 0) {
            WidgetUtilities.showErrorMessage(null, "Anggota Terlambat " + temp + " Hari Mengembalikan Film  dan Mendapat Denda Rp." + (temp * dendaPerHari));
            MySQL.prepare.setInt(4, temp * dendaPerHari);
        } else {
            MySQL.prepare.setInt(4, 0);
        }
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param pesan
     * @throws java.sql.SQLException
     */
    public static void insertPesan(final Connection connection, final Pesan pesan)
            throws SQLException {
        MySQL.prepare = connection.prepareStatement("INSERT INTO " + Entitas.PESAN + " SET " + Atribut.PESAN.ID_OPERATOR_PENGIRIM + "=?," + Atribut.PESAN.ID_OPERATOR_TUJUAN + "=?," + Atribut.PESAN.ID_PESAN + "=null," + Atribut.PESAN.ISI_PESAN + "=?," + Atribut.PESAN.JUDUL_PESAN + "=?," + Atribut.PESAN.WAKTU + "=NOW()");
        MySQL.prepare.setString(1, pesan.getOperatorPengirim());
        MySQL.prepare.setString(2, pesan.getOperatorTujuan());
        MySQL.prepare.setString(3, pesan.getIsiPesan());
        MySQL.prepare.setString(4, pesan.getJudulPesan());
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param rumahProduksi
     * @throws java.sql.SQLException
     */
    public static void insertRumahProduksi(final Connection connection,
            final RumahProduksi rumahProduksi) throws SQLException {
        MySQL.prepare = connection.prepareStatement("INSERT INTO " + Entitas.RUMAH_PRODUKSI + " SET " + Atribut.RUMAH_PRODUKSI.ID_RUMAH_PRODUKSI + "=?," + Atribut.RUMAH_PRODUKSI.NAMA_RUMAH_PRODUKSI + "=?");
        MySQL.prepare.setString(1, rumahProduksi.getIdRumahProduksi());
        MySQL.prepare.setString(2, rumahProduksi.getNamaRumahProduksi());
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultAktivitasOperator(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.AKTIVITAS_OPERATOR);
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultAnggota(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.ANGGOTA);
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @param idAnggota
     * @param namaAnggota
     * @param lahirDari
     * @param lahirSampai
     * @param kontakAnggota
     * @param alamatAnggota
     * @param totalPinjam
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultAnggota(final Connection connection, final String idAnggota,
            final String namaAnggota, final Date lahirDari, final Date lahirSampai,
            final String kontakAnggota, final String alamatAnggota, final Integer totalPinjam)
            throws SQLException {
        String SQL = "SELECT * FROM " + Entitas.ANGGOTA;
        if ((idAnggota != null) || (namaAnggota != null) || (lahirDari != null) || (lahirSampai != null) || (kontakAnggota != null) || (alamatAnggota != null) || (totalPinjam != null)) {
            SQL += " WHERE ";
        } else {
            return MySQL.resultAnggota(connection);
        }
        if (idAnggota != null) {
            SQL += Atribut.ANGGOTA.ID_ANGGOTA + " LIKE ? ";
            if ((namaAnggota != null) || (lahirDari != null) || (lahirSampai != null) || (kontakAnggota != null) || (alamatAnggota != null) || (totalPinjam != null)) {
                SQL += " AND ";
            }
        }
        if (namaAnggota != null) {
            SQL += Atribut.ANGGOTA.NAMA_ANGGOTA + " LIKE ? ";
            if ((lahirDari != null) || (lahirSampai != null) || (kontakAnggota != null) || (alamatAnggota != null) || (totalPinjam != null)) {
                SQL += " AND ";
            }
        }
        if (lahirDari != null) {
            SQL += Atribut.ANGGOTA.TANGGAL_LAHIR_ANGGOTA + " >= ?";
            if ((lahirSampai != null) || (kontakAnggota != null) || (alamatAnggota != null) || (totalPinjam != null)) {
                SQL += " AND ";
            }
        }
        if (lahirSampai != null) {
            SQL += Atribut.ANGGOTA.TANGGAL_LAHIR_ANGGOTA + " <= ? ";
            if ((kontakAnggota != null) || (alamatAnggota != null) || (totalPinjam != null)) {
                SQL += " AND ";
            }
        }
        if (kontakAnggota != null) {
            SQL += Atribut.ANGGOTA.KONTAK_ANGGOTA + " LIKE ? ";
            if ((alamatAnggota != null) || (totalPinjam != null)) {
                SQL += " AND ";
            }
        }
        if (alamatAnggota != null) {
            SQL += Atribut.ANGGOTA.ALAMAT_ANGGOTA + " LIKE ? ";
            if (totalPinjam != null) {
                SQL += " AND ";
            }
        }
        if (totalPinjam != null) {
            SQL += Atribut.ANGGOTA.TOTAL_MEMINJAM + " =? ";
        }
        MySQL.prepare = connection.prepareStatement(SQL);
        int index = 0;
        if (idAnggota != null) {
            index++;
            MySQL.prepare.setString(index, "%" + idAnggota + "%");
        }
        if (namaAnggota != null) {
            index++;
            MySQL.prepare.setString(index, "%" + namaAnggota + "%");
        }
        if (lahirDari != null) {
            index++;
            MySQL.prepare.setDate(index, lahirDari);
        }
        if (lahirSampai != null) {
            index++;
            MySQL.prepare.setDate(index, lahirSampai);
        }
        if (kontakAnggota != null) {
            index++;
            MySQL.prepare.setString(index, "%" + kontakAnggota + "%");
        }
        if (alamatAnggota != null) {
            index++;
            MySQL.prepare.setString(index, "%" + alamatAnggota + "%");
        }
        if (totalPinjam != null) {
            index++;
            MySQL.prepare.setInt(index, totalPinjam.intValue());
        }
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultDirektor(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.DIREKTOR);
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @param idDirektor
     * @param namaDirektor
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultDirektor(final Connection connection, final String idDirektor,
            final String namaDirektor) throws SQLException {
        String SQL = "SELECT * FROM " + Entitas.DIREKTOR;
        if ((idDirektor != null) || (namaDirektor != null)) {
            SQL += " WHERE ";
        } else {
            return MySQL.resultDirektor(connection);
        }
        if (idDirektor != null) {
            SQL += Atribut.DIREKTOR.ID_DIREKTOR + " LIKE ? ";
            if (namaDirektor != null) {
                SQL += " AND ";
            }
        }
        if (namaDirektor != null) {
            SQL += Atribut.DIREKTOR.NAMA_DIREKTOR + " LIKE ? ";
        }
        MySQL.prepare = connection.prepareStatement(SQL);
        int index = 0;
        if (idDirektor != null) {
            index++;
            MySQL.prepare.setString(index, "%" + idDirektor + "%");
        }
        if (namaDirektor != null) {
            index++;
            MySQL.prepare.setString(index, "%" + namaDirektor + "%");
        }
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultFilm(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.FILM);
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @param judulFilm
     * @param pemeranUtama
     * @param direktor
     * @param rumahProduksi
     * @param jenisFilm
     * @param status
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultFilm(final Connection connection, final String judulFilm,
            final String pemeranUtama, final String direktor, final String rumahProduksi,
            final String jenisFilm, final Boolean status) throws SQLException {
        String SQL = "SELECT * FROM " + Entitas.FILM + ", " + Entitas.PEMERAN_UTAMA + "," + Entitas.DIREKTOR + "," + Entitas.RUMAH_PRODUKSI + "," + Entitas.JENIS_FILM + " WHERE " + Atribut.FILM.ID_PEMERAN_UTAMA + "=" + Atribut.PEMERAN_UTAMA.ID_PEMERAN_UTAMA + " AND " + Atribut.FILM.ID_DIREKTOR + "=" + Atribut.DIREKTOR.ID_DIREKTOR + " AND " + Atribut.FILM.ID_RUMAH_PRODUKSI + "=" + Atribut.RUMAH_PRODUKSI.ID_RUMAH_PRODUKSI + " AND " + Atribut.FILM.ID_JENIS_FILM + "=" + Atribut.JENIS_FILM.ID_JENIS_FILM;
        if ((judulFilm != null) || (pemeranUtama != null) || (direktor != null) || (rumahProduksi != null) || (jenisFilm != null) || (status != null)) {
            SQL += " AND ";
        } else {
            return MySQL.resultFilm(connection);
        }
        if (judulFilm != null) {
            SQL += Atribut.FILM.JUDUL_FILM + " LIKE ? ";
            if ((pemeranUtama != null) || (direktor != null) || (rumahProduksi != null) || (jenisFilm != null) || (status != null)) {
                SQL += " AND ";
            }
        }
        if (pemeranUtama != null) {
            SQL += Atribut.PEMERAN_UTAMA.NAMA_PEMERAN_UTAMA + " LIKE ? ";
            if ((direktor != null) || (rumahProduksi != null) || (jenisFilm != null) || (status != null)) {
                SQL += " AND ";
            }
        }
        if (direktor != null) {
            SQL += Atribut.DIREKTOR.NAMA_DIREKTOR + " LIKE ? ";
            if ((rumahProduksi != null) || (jenisFilm != null) || (status != null)) {
                SQL += " AND ";
            }
        }
        if (rumahProduksi != null) {
            SQL += Atribut.RUMAH_PRODUKSI.NAMA_RUMAH_PRODUKSI + " LIKE ? ";
            if ((jenisFilm != null) || (status != null)) {
                SQL += " AND ";
            }
        }
        if (jenisFilm != null) {
            SQL += Atribut.JENIS_FILM.NAMA_JENIS_FILM + " LIKE ? ";
            if (status != null) {
                SQL += " AND ";
            }
        }
        if (status != null) {
            SQL += Atribut.FILM.DIPINJAM + "=?";
        }
        MySQL.prepare = connection.prepareStatement(SQL);
        int index = 0;
        if (judulFilm != null) {
            index++;
            MySQL.prepare.setString(index, "%" + judulFilm + "%");
        }
        if (pemeranUtama != null) {
            index++;
            MySQL.prepare.setString(index, "%" + pemeranUtama + "%");
        }
        if (direktor != null) {
            index++;
            MySQL.prepare.setString(index, "%" + direktor + "%");
        }
        if (rumahProduksi != null) {
            index++;
            MySQL.prepare.setString(index, "%" + rumahProduksi + "%");
        }
        if (jenisFilm != null) {
            index++;
            MySQL.prepare.setString(index, "%" + jenisFilm + "%");
        }
        if (status != null) {
            index++;
            MySQL.prepare.setBoolean(index, status);
        }
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultJenisFilm(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.JENIS_FILM);
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @param idJenisFilm
     * @param namaJenisFilm
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultJenisFilm(final Connection connection, final String idJenisFilm,
            final String namaJenisFilm) throws SQLException {
        String SQL = "SELECT * FROM " + Entitas.JENIS_FILM;
        if ((idJenisFilm != null) || (namaJenisFilm != null)) {
            SQL += " WHERE ";
        } else {
            return MySQL.resultJenisFilm(connection);
        }
        if (idJenisFilm != null) {
            SQL += Atribut.JENIS_FILM.ID_JENIS_FILM + " LIKE ? ";
            if (namaJenisFilm != null) {
                SQL += " AND ";
            }
        }
        if (namaJenisFilm != null) {
            SQL += Atribut.JENIS_FILM.NAMA_JENIS_FILM + " LIKE ? ";
        }
        MySQL.prepare = connection.prepareStatement(SQL);
        int index = 0;
        if (idJenisFilm != null) {
            index++;
            MySQL.prepare.setString(index, "%" + idJenisFilm + "%");
        }
        if (namaJenisFilm != null) {
            index++;
            MySQL.prepare.setString(index, "%" + namaJenisFilm + "%");
        }
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultOperator(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.OPERATOR);
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @param idOperator
     * @param namaOperator
     * @param lahirDari
     * @param lahirSampai
     * @param kontakOperator
     * @param alamatOperator
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultOperator(final Connection connection, final String idOperator,
            final String namaOperator, final Date lahirDari, final Date lahirSampai,
            final String kontakOperator, final String alamatOperator) throws SQLException {
        String SQL = "SELECT * FROM " + Entitas.OPERATOR;
        if ((idOperator != null) || (namaOperator != null) || (lahirDari != null) || (lahirSampai != null) || (kontakOperator != null) || (alamatOperator != null)) {
            SQL += " WHERE ";
        }
        if (idOperator != null) {
            SQL += Atribut.OPERATOR.ID_OPERATOR + " LIKE ? ";
            if ((namaOperator != null) || (lahirDari != null) || (lahirSampai != null) || (kontakOperator != null) || (alamatOperator != null)) {
                SQL += " WHERE ";
            }
        }
        if (namaOperator != null) {
            SQL += Atribut.OPERATOR.NAMA_OPERATOR + " LIKE ? ";
            if ((lahirDari != null) || (lahirSampai != null) || (kontakOperator != null) || (alamatOperator != null)) {
                SQL += " WHERE ";
            }
        }
        if (lahirDari != null) {
            SQL += Atribut.OPERATOR.TANGGAL_LAHIR_OPERATOR + " >= ? ";
            if ((lahirSampai != null) || (kontakOperator != null) || (alamatOperator != null)) {
                SQL += " WHERE ";
            }
        }
        if (lahirSampai != null) {
            SQL += Atribut.OPERATOR.TANGGAL_LAHIR_OPERATOR + " <= ";
            if ((kontakOperator != null) || (alamatOperator != null)) {
                SQL += " WHERE ";
            }
        }
        if (kontakOperator != null) {
            SQL += Atribut.OPERATOR.KONTAK_OPERATOR + " LIKE ? ";
            if (alamatOperator != null) {
                SQL += " WHERE ";
            }
        }
        if (alamatOperator != null) {
            SQL += Atribut.OPERATOR.ALAMAT_OPERATOR + " LIKE ? ";
        }
        MySQL.prepare = connection.prepareStatement(SQL);
        int index = 0;
        if (idOperator != null) {
            index++;
            MySQL.prepare.setString(index, "%" + idOperator + "%");
        }
        if (namaOperator != null) {
            index++;
            MySQL.prepare.setString(index, "%" + namaOperator + "%");
        }
        if (lahirDari != null) {
            index++;
            MySQL.prepare.setDate(index, lahirDari);
        }
        if (lahirSampai != null) {
            index++;
            MySQL.prepare.setDate(index, lahirSampai);
        }
        if (kontakOperator != null) {
            index++;
            MySQL.prepare.setString(index, "%" + kontakOperator + "%");
        }
        if (alamatOperator != null) {
            index++;
            MySQL.prepare.setString(index, "%" + alamatOperator + "%");
        }
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultPemeranUtama(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.PEMERAN_UTAMA);
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @param idPemeranUtama
     * @param namapemeranUtama
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultPemeranUtama(final Connection connection,
            final String idPemeranUtama, final String namapemeranUtama) throws SQLException {
        String SQL = "SELECT * FROM " + Entitas.PEMERAN_UTAMA;
        if ((idPemeranUtama != null) || (namapemeranUtama != null)) {
            SQL += " WHERE ";
        } else {
            return MySQL.resultPemeranUtama(connection);
        }
        if (idPemeranUtama != null) {
            SQL += Atribut.PEMERAN_UTAMA.ID_PEMERAN_UTAMA + " LIKE ? ";
            if (namapemeranUtama != null) {
                SQL += " AND ";
            }
        }
        if (namapemeranUtama != null) {
            SQL += Atribut.PEMERAN_UTAMA.NAMA_PEMERAN_UTAMA + " LIKE ? ";
        }
        MySQL.prepare = connection.prepareStatement(SQL);
        int index = 0;
        if (idPemeranUtama != null) {
            index++;
            MySQL.prepare.setString(index, "%" + idPemeranUtama + "%");
        }
        if (namapemeranUtama != null) {
            index++;
            MySQL.prepare.setString(index, "%" + namapemeranUtama + "%");
        }
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultPeminjaman(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.PEMINJAMAN);
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @param idAnggota
     * @param idOperator
     * @param idFilm
     * @param tanggalDari
     * @param tanggalSampai
     * @param status
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultPeminjaman(final Connection connection, final String idAnggota,
            final String idOperator, final String idFilm, final Date tanggalDari,
            final Date tanggalSampai, final Boolean status) throws SQLException {
        String SQL = "SELECT * FROM " + Entitas.PEMINJAMAN;
        if ((idAnggota != null) || (idOperator != null) || (idFilm != null) || (tanggalDari != null) || (tanggalSampai != null) || (status != null)) {
            SQL += " WHERE ";
        } else {
            return MySQL.resultPeminjaman(connection);
        }
        if (idAnggota != null) {
            SQL += Atribut.PEMINJAMAN.ID_ANGGOTA + " LIKE ? ";
            if ((idOperator != null) || (idFilm != null) || (tanggalDari != null) || (tanggalSampai != null) || (status != null)) {
                SQL += " AND ";
            }
        }
        if (idOperator != null) {
            SQL += Atribut.PEMINJAMAN.ID_OPERATOR + " LIKE ? ";
            if ((idFilm != null) || (tanggalDari != null) || (tanggalSampai != null) || (status != null)) {
                SQL += " AND ";
            }
        }
        if (idFilm != null) {
            SQL += Atribut.PEMINJAMAN.ID_FILM + " LIKE ? ";
            if ((tanggalDari != null) || (tanggalSampai != null) || (status != null)) {
                SQL += " AND ";
            }
        }
        if (tanggalDari != null) {
            SQL += Atribut.PEMINJAMAN.TANGGAL_PEMINJAMAN + " >=? ";
            if ((tanggalSampai != null) || (status != null)) {
                SQL += " AND ";
            }
        }
        if (tanggalSampai != null) {
            SQL += Atribut.PEMINJAMAN.TANGGAL_PEMINJAMAN + " <=? ";
            if (status != null) {
                SQL += " AND ";
            }
        }
        if (status != null) {
            SQL += Atribut.PEMINJAMAN.TELAH_DIKEMBALIKAN + " =? ";
        }
        MySQL.prepare = connection.prepareStatement(SQL);
        int index = 0;
        if (idAnggota != null) {
            index++;
            MySQL.prepare.setString(index, "%" + idAnggota + "%");
        }
        if (idOperator != null) {
            index++;
            MySQL.prepare.setString(index, "%" + idOperator + "%");
        }
        if (idFilm != null) {
            index++;
            MySQL.prepare.setString(index, "%" + idFilm + "%");
        }
        if (tanggalDari != null) {
            index++;
            MySQL.prepare.setDate(index, tanggalDari);
        }
        if (tanggalSampai != null) {
            index++;
            MySQL.prepare.setDate(index, tanggalSampai);
        }
        if (status != null) {
            index++;
            MySQL.prepare.setBoolean(index, status);
        }
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultPengembalian(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.PENGEMBALIAN);
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @param idOperator
     * @param tanggalDari
     * @param tanggalSampai
     * @param totalTerlambat
     * @param denda
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultPengembalian(final Connection connection, final String idOperator,
            final Date tanggalDari, final Date tanggalSampai, final Integer totalTerlambat,
            final Integer denda) throws SQLException {
        String SQL = "SELECT * FROM " + Entitas.PENGEMBALIAN;
        if ((idOperator != null) || (tanggalDari != null) || (tanggalSampai != null) || (totalTerlambat != null) || (denda != null)) {
            SQL += " WHERE ";
        } else {
            return MySQL.resultPengembalian(connection);
        }
        if (idOperator != null) {
            SQL += Atribut.PENGEMBALIAN.ID_OPERATOR + " LIKE ? ";
            if ((tanggalDari != null) || (tanggalSampai != null) || (totalTerlambat != null) || (denda != null)) {
                SQL += " AND ";
            }
        }
        if (tanggalDari != null) {
            SQL += Atribut.PENGEMBALIAN.TANGGAL_PENGEMBALIAN + " >=? ";
            if ((tanggalSampai != null) || (totalTerlambat != null) || (denda != null)) {
                SQL += " AND ";
            }
        }
        if (tanggalSampai != null) {
            SQL += Atribut.PENGEMBALIAN.TANGGAL_PENGEMBALIAN + " <=? ";
            if ((totalTerlambat != null) || (denda != null)) {
                SQL += " AND ";
            }
        }
        if (totalTerlambat != null) {
            SQL += Atribut.PENGEMBALIAN.TOTAL_KETERLAMBATAN + " =? ";
            if (denda != null) {
                SQL += " AND ";
            }
        }
        if (denda != null) {
            SQL += Atribut.PENGEMBALIAN.DENDA_KETERLAMBATAN + " =? ";
        }
        MySQL.prepare = connection.prepareStatement(SQL);
        int index = 0;
        if (idOperator != null) {
            index++;
            MySQL.prepare.setString(index, "%" + idOperator + "%");
        }
        if (tanggalDari != null) {
            index++;
            MySQL.prepare.setDate(index, tanggalDari);
        }
        if (tanggalSampai != null) {
            index++;
            MySQL.prepare.setDate(index, tanggalSampai);
        }
        if (totalTerlambat != null) {
            index++;
            MySQL.prepare.setInt(index, totalTerlambat);
        }
        if (denda != null) {
            index++;
            MySQL.prepare.setInt(index, denda);
        }
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultPesan(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.PESAN);
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @param idOperatorTo
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultPesanByOperator(final Connection connection,
            final String idOperatorTo) throws SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.PESAN + " WHERE " + Atribut.PESAN.ID_OPERATOR_TUJUAN + "=?");
        MySQL.prepare.setString(1, idOperatorTo);
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultRumahProduksi(final Connection connection) throws SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT * FROM " + Entitas.RUMAH_PRODUKSI);
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connectin
     * @param idRumahProduksi
     * @param namaRumahProduksi
     * @return
     * @throws java.sql.SQLException
     */
    public static ResultSet resultRumahProduksi(final Connection connectin,
            final String idRumahProduksi, final String namaRumahProduksi) throws SQLException {
        String SQL = "SELECT * FROM " + Entitas.RUMAH_PRODUKSI;
        if ((idRumahProduksi != null) || (namaRumahProduksi != null)) {
            SQL += " WHERE ";
        } else {
            return MySQL.resultRumahProduksi(connectin);
        }
        if (idRumahProduksi != null) {
            SQL += Atribut.RUMAH_PRODUKSI.ID_RUMAH_PRODUKSI + " LIKE ? ";
            if (namaRumahProduksi != null) {
                SQL += " AND ";
            }
        }
        if (namaRumahProduksi != null) {
            SQL += Atribut.RUMAH_PRODUKSI.NAMA_RUMAH_PRODUKSI + " LIKE ? ";
        }
        MySQL.prepare = connectin.prepareStatement(SQL);
        int index = 0;
        if (idRumahProduksi != null) {
            index++;
            MySQL.prepare.setString(index, "%" + idRumahProduksi + "%");
        }
        if (namaRumahProduksi != null) {
            index++;
            MySQL.prepare.setString(index, "%" + namaRumahProduksi + "%");
        }
        return MySQL.prepare.executeQuery();
    }

    /**
     * @param connection
     * @param anggota
     * @param idAnggota
     * @throws java.sql.SQLException
     */
    public static void updateAnggota(final Connection connection, final Anggota anggota,
            final String idAnggota) throws SQLException {
        MySQL.prepare = connection.prepareStatement("UPDATE " + Entitas.ANGGOTA + " SET " + Atribut.ANGGOTA.ALAMAT_ANGGOTA + "=?," + Atribut.ANGGOTA.ID_ANGGOTA + "=?," + Atribut.ANGGOTA.KONTAK_ANGGOTA + "=?," + Atribut.ANGGOTA.NAMA_ANGGOTA + "=?," + Atribut.ANGGOTA.TANGGAL_LAHIR_ANGGOTA + "=?," + Atribut.ANGGOTA.TOTAL_MEMINJAM + "=? WHERE " + Atribut.ANGGOTA.ID_ANGGOTA + "=?");
        MySQL.prepare.setString(1, anggota.getAlamatAnggota());
        MySQL.prepare.setString(2, anggota.getIdAnggota());
        MySQL.prepare.setString(3, anggota.getKontakAnggota());
        MySQL.prepare.setString(4, anggota.getNamaAnggota());
        MySQL.prepare.setDate(5, new Date(anggota.getTanggalLahirAnggota().getTime()));
        MySQL.prepare.setInt(6, anggota.getTotalmeminjam());
        MySQL.prepare.setString(7, idAnggota);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    public static void updateAnggotaTotalPeminjamn(Connection connection, String idAnggota) throws SQLException {
        prepare = connection.prepareStatement("SELECT " + Atribut.ANGGOTA.TOTAL_MEMINJAM + " FROM " +
                Entitas.ANGGOTA + " WHERE " + Atribut.ANGGOTA.ID_ANGGOTA + "=?");
        prepare.setString(1, idAnggota);
        result = prepare.executeQuery();
        result.next();
        int temp = result.getInt(Atribut.ANGGOTA.TOTAL_MEMINJAM);
        temp++;
        prepare.close();
        result.close();
        prepare = connection.prepareStatement("UPDATE " + Entitas.ANGGOTA + " SET " +
                Atribut.ANGGOTA.TOTAL_MEMINJAM + "=? WHERE " + Atribut.ANGGOTA.ID_ANGGOTA + "=?");
        prepare.setInt(1, temp);
        prepare.setString(2, idAnggota);
        prepare.executeUpdate();
        prepare.close();
    }

    /**
     * @param connection
     * @param direktor
     * @param idDirektor
     * @throws java.sql.SQLException
     */
    public static void updateDirektor(final Connection connection, final Direktor direktor,
            final String idDirektor) throws SQLException {
        MySQL.prepare = connection.prepareStatement("UPDATE " + Entitas.DIREKTOR + " SET " + Atribut.DIREKTOR.ID_DIREKTOR + "=?," + Atribut.DIREKTOR.NAMA_DIREKTOR + "=? WHERE " + Atribut.DIREKTOR.ID_DIREKTOR + "=?");
        MySQL.prepare.setString(1, direktor.getIdDirektor());
        MySQL.prepare.setString(2, direktor.getNamaDirektor());
        MySQL.prepare.setString(3, idDirektor);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param film
     * @param idFilm
     * @throws java.sql.SQLException
     */
    public static void updateFilm(final Connection connection, final Film film, final String idFilm)
            throws SQLException {
        MySQL.prepare = connection.prepareStatement("UPDATE " + Entitas.FILM + " SET " + Atribut.FILM.DIPINJAM + "=?," + Atribut.FILM.ID_DIREKTOR + "=?," + Atribut.FILM.ID_FILM + "=?," + Atribut.FILM.ID_JENIS_FILM + "=?," + Atribut.FILM.ID_PEMERAN_UTAMA + "=?," + Atribut.FILM.ID_RUMAH_PRODUKSI + "=?," + Atribut.FILM.JUDUL_FILM + "=? WHERE " + Atribut.FILM.ID_FILM + "=?");
        MySQL.prepare.setBoolean(1, film.isDipinjam());
        MySQL.prepare.setString(2, film.getDirektor());
        MySQL.prepare.setString(3, film.getIdFilm());
        MySQL.prepare.setString(4, film.getJenisFilm());
        MySQL.prepare.setString(5, film.getPemeranUtama());
        MySQL.prepare.setString(6, film.getRumahProduksi());
        MySQL.prepare.setString(7, film.getJudulFilm());
        MySQL.prepare.setString(8, idFilm);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param idFilm
     * @param dipinjam
     * @throws java.sql.SQLException
     */
    public static void updateFilmSedangDipinjam(final Connection connection, final String idFilm,
            final boolean dipinjam) throws SQLException {
        MySQL.prepare = connection.prepareStatement("UPDATE " + Entitas.FILM + " SET " + Atribut.FILM.DIPINJAM + "=? WHERE " + Atribut.FILM.ID_FILM + "=?");
        MySQL.prepare.setBoolean(1, dipinjam);
        MySQL.prepare.setString(2, idFilm);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param idFilm
     * @throws java.sql.SQLException
     */
    public static void updateFilmTotalDipinjam(final Connection connection, final String idFilm)
            throws SQLException {
        MySQL.prepare = connection.prepareStatement("SELECT " + Atribut.FILM.TOTAL_DIPINJAM + " FROM " + Entitas.FILM + " WHERE " + Atribut.FILM.ID_FILM + "=?");
        MySQL.prepare.setString(1, idFilm);
        MySQL.result = MySQL.prepare.executeQuery();
        if (MySQL.result.next()) {
            final int temp = MySQL.result.getInt(Atribut.FILM.TOTAL_DIPINJAM);
            MySQL.prepare = connection.prepareStatement("UPDATE " + Entitas.FILM + " SET " + Atribut.FILM.TOTAL_DIPINJAM + "=? WHERE " + Atribut.FILM.ID_FILM + "=?");
            MySQL.prepare.setInt(1, temp + 1);
            MySQL.prepare.setString(2, idFilm);
            MySQL.prepare.executeUpdate();
        }
        MySQL.prepare.close();
        MySQL.result.close();
    }

    /**
     * @param connection
     * @param jenisFilm
     * @param idJenisFilm
     * @throws java.sql.SQLException
     */
    public static void updateJenisFilm(final Connection connection, final JenisFilm jenisFilm,
            final String idJenisFilm) throws SQLException {
        MySQL.prepare = connection.prepareStatement("UPDATE " + Entitas.JENIS_FILM + " SEt " + Atribut.JENIS_FILM.ID_JENIS_FILM + "=?," + Atribut.JENIS_FILM.NAMA_JENIS_FILM + "=? WHERE " + Atribut.JENIS_FILM.ID_JENIS_FILM + "=?");
        MySQL.prepare.setString(1, jenisFilm.getIdJenisFilm());
        MySQL.prepare.setString(2, jenisFilm.getNamaJenisFilm());
        MySQL.prepare.setString(3, idJenisFilm);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param operator
     * @param idOperator
     * @throws java.sql.SQLException
     */
    public static void updateOperator(final Connection connection, final Operator operator,
            final String idOperator) throws SQLException {
        MySQL.prepare = connection.prepareStatement("UPDATE " + Entitas.OPERATOR + " SET " + Atribut.OPERATOR.ALAMAT_OPERATOR + "=?, " + Atribut.OPERATOR.ID_OPERATOR + "=?, " + Atribut.OPERATOR.KONTAK_OPERATOR + "=?, " + Atribut.OPERATOR.NAMA_OPERATOR + "=?, " + Atribut.OPERATOR.TANGGAL_LAHIR_OPERATOR + "=?, " + Atribut.OPERATOR.PASSWORD_OPERATOR + "=? WHERE " + Atribut.OPERATOR.ID_OPERATOR + "=?");
        MySQL.prepare.setString(1, operator.getAlamatOperator());
        MySQL.prepare.setString(2, operator.getIdOperator());
        MySQL.prepare.setString(3, operator.getKontakOperator());
        MySQL.prepare.setString(4, operator.getNamaOperator());
        MySQL.prepare.setDate(5, new Date(operator.getTanggalLahirOperator().getTime()));
        MySQL.prepare.setString(6, operator.getPassword());
        MySQL.prepare.setString(7, idOperator);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param pemeranUtama
     * @param idPemeranUtama
     * @throws java.sql.SQLException
     */
    public static void updatePemeranUtama(final Connection connection,
            final PemeranUtama pemeranUtama, final String idPemeranUtama) throws SQLException {
        MySQL.prepare = connection.prepareStatement("UPDATE " + Entitas.PEMERAN_UTAMA + " SET " + Atribut.PEMERAN_UTAMA.ID_PEMERAN_UTAMA + "=?," + Atribut.PEMERAN_UTAMA.NAMA_PEMERAN_UTAMA + "=? WHERE " + Atribut.PEMERAN_UTAMA.ID_PEMERAN_UTAMA + "=?");
        MySQL.prepare.setString(1, pemeranUtama.getIdPemeranUtama());
        MySQL.prepare.setString(2, pemeranUtama.getNamaPemeranUtama());
        MySQL.prepare.setString(3, idPemeranUtama);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param idTransaksi
     * @throws java.sql.SQLException
     */
    public static void updatePeminjaman(final Connection connection, final int idTransaksi)
            throws SQLException {
        MySQL.prepare = connection.prepareStatement("UPDATE " + Entitas.PEMINJAMAN + " SET " + Atribut.PEMINJAMAN.TELAH_DIKEMBALIKAN + "=? WHERE " + Atribut.PEMINJAMAN.ID_TRANSAKSI + "=?");
        MySQL.prepare.setBoolean(1, true);
        MySQL.prepare.setInt(2, idTransaksi);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }

    /**
     * @param connection
     * @param rumahProduksi
     * @param idRumahProduksi
     * @throws java.sql.SQLException
     */
    public static void updateRumahProduksi(final Connection connection,
            final RumahProduksi rumahProduksi, final String idRumahProduksi) throws SQLException {
        MySQL.prepare = connection.prepareStatement("UPDATE " + Entitas.RUMAH_PRODUKSI + " SET " + Atribut.RUMAH_PRODUKSI.ID_RUMAH_PRODUKSI + "=?," + Atribut.RUMAH_PRODUKSI.NAMA_RUMAH_PRODUKSI + "=? WHERE " + Atribut.RUMAH_PRODUKSI.ID_RUMAH_PRODUKSI + "=?");
        MySQL.prepare.setString(1, rumahProduksi.getIdRumahProduksi());
        MySQL.prepare.setString(2, rumahProduksi.getNamaRumahProduksi());
        MySQL.prepare.setString(3, idRumahProduksi);
        MySQL.prepare.executeUpdate();
        MySQL.prepare.close();
    }
}
