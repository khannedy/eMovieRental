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
import usu.rental.film.data.template.RumahProduksi;

/**
 *
 * @author usu
 */
public class TableModelRumahProduksi extends AbstractTableModel {

    /*
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;
    private Vector<String> column;
    private Vector<RumahProduksi> row;

    public TableModelRumahProduksi() {
        super();
        column = new Vector<String>();
        column.add("Id Rumah Produksi");
        column.add("Nama Rumah Produksi");
        row = new Vector<RumahProduksi>();
    }

    public synchronized void add(List<RumahProduksi> list) {
        for (RumahProduksi r : list) {
            row.add(r);
        }
        fireTableDataChanged();
    }

    public synchronized RumahProduksi set(int index, RumahProduksi element) {
        RumahProduksi p = row.set(index, element);
        fireTableRowsUpdated(index, index);
        return p;
    }

    public synchronized void removeAllElements() {
        row.removeAllElements();
        fireTableDataChanged();
    }

    public synchronized RumahProduksi remove(int index) {
        RumahProduksi p = row.remove(index);
        fireTableRowsDeleted(index, index);
        return p;
    }

    public synchronized RumahProduksi get(int index) {
        return row.get(index);
    }

    public synchronized boolean add(RumahProduksi e) {
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
            return row.get(rowIndex).getIdRumahProduksi();
        } else if (columnIndex == 1) {
            return row.get(rowIndex).getNamaRumahProduksi();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return this.column.get(column);
    }
}
