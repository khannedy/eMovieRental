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
import usu.rental.film.data.template.Direktor;

/**
 *
 * @author usu
 */
public class TableModelDirektor extends AbstractTableModel {

    /*
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;
    private Vector<String> column;
    private Vector<Direktor> row;

    public TableModelDirektor() {
        super();
        column = new Vector<String>();
        column.add("Id Direktor");
        column.add("Nama Direktor");
        row = new Vector<Direktor>();
    }

    public synchronized void add(List<Direktor> list) {
        for (Direktor d : list) {
            row.add(d);
        }
        fireTableDataChanged();
    }

    public synchronized Direktor set(int index, Direktor element) {
        Direktor d = row.set(index, element);
        fireTableRowsUpdated(index, index);
        return d;
    }

    public synchronized void removeAllElements() {
        row.removeAllElements();
        fireTableDataChanged();
    }

    public synchronized Direktor remove(int index) {
        Direktor d = row.remove(index);
        fireTableRowsDeleted(index, index);
        return d;
    }

    public synchronized Direktor get(int index) {
        return row.get(index);
    }

    public synchronized boolean add(Direktor e) {
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0) {
            return row.get(rowIndex).getIdDirektor();
        } else if (columnIndex == 1) {
            return row.get(rowIndex).getNamaDirektor();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return this.column.get(column);
    }
}
