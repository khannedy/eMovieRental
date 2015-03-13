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
import usu.rental.film.data.template.JenisFilm;

/**
 *
 * @author usu
 */
public class TableModelJenisFilm extends AbstractTableModel {

    /*
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;
    private Vector<String> column;
    private Vector<JenisFilm> row;

    public TableModelJenisFilm() {
        super();
        column = new Vector<String>();
        column.add("Id Jenis Film");
        column.add("Nama Jenis Film");
        row = new Vector<JenisFilm>();
    }

    public synchronized void add(List<JenisFilm> list) {
        for (JenisFilm j : list) {
            row.add(j);
        }
        fireTableDataChanged();
    }

    public synchronized JenisFilm set(int index, JenisFilm element) {
        JenisFilm j = row.set(index, element);
        fireTableRowsUpdated(index, index);
        return j;
    }

    public synchronized void removeAllElements() {
        row.removeAllElements();
        fireTableDataChanged();
    }

    public synchronized JenisFilm remove(int index) {
        JenisFilm j = row.remove(index);
        fireTableRowsDeleted(index, index);
        return j;

    }

    public synchronized JenisFilm get(int index) {
        return row.get(index);
    }

    public synchronized boolean add(JenisFilm e) {
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
            return row.get(rowIndex).getIdJenisFilm();
        } else if (columnIndex == 1) {
            return row.get(rowIndex).getNamaJenisFilm();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return this.column.get(column);
    }
}
