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
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import usu.rental.film.data.template.Operator;
import usu.rental.film.sql.MySQL;
import usu.rental.film.widget.table.TableModelOperatorNoPassword;

/**
 *
 * @author usu
 */
public class WorkerOperatorNoPassword extends SwingWorker<List<Operator>, String> {

    private TableModelOperatorNoPassword model;
    private WorkerAction worker;
    private ResultSet result;

    public WorkerOperatorNoPassword(Connection connection, TableModelOperatorNoPassword model, WorkerAction worker) throws SQLException {
        this.model = model;
        this.worker = worker;
        result = MySQL.resultOperator(connection);
        this.worker.runFirst();
        this.model.removeAllElements();
    }

    public WorkerOperatorNoPassword(Connection connection, TableModelOperatorNoPassword model, WorkerAction worker,
            String idOperator, String namaOperator, Date lahirDari, Date lahirSampai,
            String kontakOperator, String alamatOperator) throws SQLException {
        this.model = model;
        this.worker = worker;
        result = MySQL.resultOperator(connection, idOperator, namaOperator, lahirDari, lahirSampai, kontakOperator, alamatOperator);
        this.worker.runFirst();
        this.model.removeAllElements();
    }

    @Override
    protected List<Operator> doInBackground() throws Exception {
        return MySQL.getListOperator(result);
    }

    @Override
    protected void done() {
        try {
            model.add(get());
        } catch (InterruptedException ex) {
            Logger.getLogger(WorkerOperatorNoPassword.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(WorkerOperatorNoPassword.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                worker.runLast();
                result.close();
            } catch (SQLException ex) {
                Logger.getLogger(WorkerOperatorNoPassword.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
