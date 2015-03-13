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
package usu.rental.film.widget.table;

import java.util.List;
import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import usu.rental.film.data.template.AktivitasOperator;

/**
 *
 * @author usu
 */
public class TableModelAktivitasOperator extends AbstractTableModel {

    /*
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;
    private Vector<String> column;
    private Vector<AktivitasOperator> row;

    public TableModelAktivitasOperator() {
        super();
        column = new Vector<String>();
        column.add("Id Operator");
        column.add("Aktivitas Operator");
        column.add("Waktu");
        row = new Vector<AktivitasOperator>();
    }

    public synchronized void add(List<AktivitasOperator> list) {
        for (AktivitasOperator o : list) {
            row.add(o);
        }
        fireTableDataChanged();
    }

    public synchronized AktivitasOperator set(int index, AktivitasOperator element) {
        AktivitasOperator o = row.set(index, element);
        fireTableRowsUpdated(index, index);
        return o;
    }

    public synchronized void removeAllElements() {
        row.removeAllElements();
        fireTableDataChanged();
    }

    public synchronized AktivitasOperator remove(int index) {
        AktivitasOperator o = row.remove(index);
        fireTableRowsDeleted(index, index);
        return o;
    }

    public synchronized AktivitasOperator get(int index) {
        return row.get(index);
    }

    public synchronized boolean add(AktivitasOperator e) {
        int index = row.size();
        boolean b = row.add(e);
        fireTableRowsInserted(index, index);
        return b;
    }

    @Override
    public int getRowCount() {
        return row.size();
    }

    @Override
    public int getColumnCount() {
        return column.size();
    }

    @Override
    public String getColumnName(int column) {
        return this.column.get(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return row.get(rowIndex).getOperator();
        } else if (columnIndex == 1) {
            return row.get(rowIndex).getAktivitas();
        } else if (columnIndex == 2) {
            return row.get(rowIndex).getWaktu();
        }
        return null;
    }
}
