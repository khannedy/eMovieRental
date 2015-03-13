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
import usu.rental.film.data.template.Pesan;
import usu.rental.film.sql.MySQL;
import usu.rental.film.widget.table.TableModelPesan;

/**
 *
 * @author usu
 */
public class WorkerPesan extends SwingWorker<List<Pesan>, String> {

    private TableModelPesan model;
    private WorkerAction worker;
    private ResultSet result;

    public WorkerPesan(Connection connection, TableModelPesan model, WorkerAction worker) throws SQLException {
        this(connection, model, worker, null);
    }

    public WorkerPesan(Connection connection, TableModelPesan model, WorkerAction worker, String idOperator) throws SQLException {
        this.model = model;
        this.worker = worker;
        if (idOperator == null) {
            result = MySQL.resultPesan(connection);
        } else {
            result = MySQL.resultPesanByOperator(connection, idOperator);
        }
        this.worker.runFirst();
        this.model.removeAllElements();
    }

    @Override
    protected List<Pesan> doInBackground() throws Exception {
        return MySQL.getListPesan(result);
    }

    @Override
    protected void done() {
        try {
            model.add(get());
        } catch (InterruptedException ex) {
            Logger.getLogger(WorkerPesan.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(WorkerPesan.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                worker.runLast();
                result.close();
            } catch (SQLException ex) {
                Logger.getLogger(WorkerPesan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
