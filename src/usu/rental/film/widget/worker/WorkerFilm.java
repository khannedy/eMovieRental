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
package usu.rental.film.widget.worker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import usu.rental.film.data.template.Film;
import usu.rental.film.sql.MySQL;
import usu.rental.film.widget.table.TableModelFilm;

/**
 *
 * @author usu
 */
public class WorkerFilm extends SwingWorker<List<Film>, String> {

    private Connection connection;
    private TableModelFilm model;
    private WorkerAction worker;
    private ResultSet result;

    /**
     * 
     * @param connection
     * @param model
     * @param worker
     * @throws java.sql.SQLException
     */
    public WorkerFilm(Connection connection, TableModelFilm model, WorkerAction worker) throws SQLException {
        this.connection = connection;
        this.model = model;
        this.worker = worker;
        result = MySQL.resultFilm(connection);
        this.model.removeAllElements();
        worker.runFirst();
    }

    /**
     * 
     * @param connection
     * @param model
     * @param worker
     * @param judulFilm
     * @param pemeranUtama
     * @param direktor
     * @param rumahProduksi
     * @param jenisFilm
     * @param status
     * @throws java.sql.SQLException
     */
    public WorkerFilm(Connection connection, TableModelFilm model, WorkerAction worker,
            String judulFilm, String pemeranUtama, String direktor, String rumahProduksi, 
            String jenisFilm, Boolean status) throws SQLException {
        this.connection = connection;
        this.model = model;
        this.worker = worker;
        result = MySQL.resultFilm(connection, judulFilm, pemeranUtama, direktor, rumahProduksi, jenisFilm, status);
        this.model.removeAllElements();
        worker.runFirst();
    }

    @Override
    protected List<Film> doInBackground() throws Exception {
        return MySQL.getListFilm(connection, result);
    }

    @Override
    protected void done() {
        try {
            model.add(get());
        } catch (InterruptedException ex) {
            Logger.getLogger(WorkerFilm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(WorkerFilm.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                worker.runLast();
                result.close();
            } catch (SQLException ex) {
                Logger.getLogger(WorkerFilm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
