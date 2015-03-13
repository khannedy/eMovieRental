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
import usu.rental.film.data.template.PemeranUtama;

/**
 *
 * @author usu
 */
public class TableModelPemeranUtama extends AbstractTableModel {

    /*
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;
    private Vector<String> column;
    private Vector<PemeranUtama> row;

    public TableModelPemeranUtama() {
        super();
        column = new Vector<String>();
        column.add("Id Pemeran Utama");
        column.add("Nama Pemeran Utama");
        row = new Vector<PemeranUtama>();
    }

    public synchronized void add(List<PemeranUtama> list) {
        for (PemeranUtama p : list) {
            row.add(p);
        }
        fireTableDataChanged();
    }

    public synchronized PemeranUtama set(int index, PemeranUtama element) {
        PemeranUtama p = row.set(index, element);
        fireTableRowsUpdated(index, index);
        return p;
    }

    public synchronized void removeAllElements() {
        row.removeAllElements();
        fireTableDataChanged();
    }

    public synchronized PemeranUtama remove(int index) {
        PemeranUtama p = row.remove(index);
        fireTableRowsDeleted(index, index);
        return p;
    }

    public synchronized PemeranUtama get(int index) {
        return row.get(index);
    }

    public synchronized boolean add(PemeranUtama e) {
        int index = row.size();
        boolean b = row.add(e);
        fireTableRowsInserted(index, index);
        return b;

    }

    public int getRowCount() {
        return row.size();
    }

    public int getColumnCount() {
        return column.size();
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return row.get(rowIndex).getIdPemeranUtama();
        } else if (columnIndex == 1) {
            return row.get(rowIndex).getNamaPemeranUtama();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return this.column.get(column);
    }
}
