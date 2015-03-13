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
import usu.rental.film.data.template.Pengembalian;

/**
 *
 * @author usu
 */
public class TableModelPengembalian extends AbstractTableModel {

    /*
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;
    private Vector<String> column;
    private Vector<Pengembalian> row;

    public TableModelPengembalian() {
        super();
        column = new Vector<String>();
        column.add("id Transaksi");
        column.add("id Operator");
        column.add("Tanggal Pengembalian");
        column.add("Total Hari Terlambat");
        column.add("Denda Keterlambatan");
        row = new Vector<Pengembalian>();
    }

    public synchronized void add(List<Pengembalian> list) {
        for (Pengembalian p : list) {
            row.add(p);
        }
        fireTableDataChanged();
    }

    public synchronized Pengembalian set(int index, Pengembalian element) {
        Pengembalian p = row.set(index, element);
        fireTableRowsUpdated(index, index);
        return p;
    }

    public synchronized void removeAllElements() {
        row.removeAllElements();
        fireTableDataChanged();
    }

    public synchronized Pengembalian remove(int index) {
        Pengembalian p = row.remove(index);
        fireTableRowsDeleted(index, index);
        return p;
    }

    public synchronized Pengembalian get(int index) {
        return row.get(index);
    }

    public synchronized boolean add(Pengembalian e) {
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
            return row.get(rowIndex).getIdTransaksi();
        } else if (columnIndex == 1) {
            return row.get(rowIndex).getOperator();
        } else if (columnIndex == 2) {
            return row.get(rowIndex).getTanggalPengembalian();
        } else if (columnIndex == 3) {
            return row.get(rowIndex).getTotalHariTerlambat();
        } else if (columnIndex == 4) {
            return row.get(rowIndex).getDendaKeterlambatan();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return this.column.get(column);
    }
}
