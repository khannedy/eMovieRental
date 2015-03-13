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
import usu.rental.film.data.template.Anggota;

/**
 *
 * @author usu
 */
public class TableModelAnggota extends AbstractTableModel {

    /*
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;
    private Vector<String> column;
    private Vector<Anggota> row;

    public TableModelAnggota() {
        super();
        column = new Vector<String>();
        column.add("Id Anggota");
        column.add("Nama Anggota");
        column.add("Tanggal Lahir Anggota");
        column.add("Kontak Anggota");
        column.add("Alamat Anggota");
        column.add("Total Meminjam");
        row = new Vector<Anggota>();
    }

    public synchronized void add(List<Anggota> list) {
        for (Anggota o : list) {
            row.add(o);
        }
        fireTableDataChanged();
    }

    public synchronized Anggota set(int index, Anggota element) {
        Anggota o = row.set(index, element);
        fireTableRowsUpdated(index, index);
        return o;
    }

    public synchronized void removeAllElements() {
        row.removeAllElements();
        fireTableDataChanged();
    }

    public synchronized Anggota remove(int index) {
        Anggota o = row.remove(index);
        fireTableRowsDeleted(index, index);
        return o;
    }

    public synchronized Anggota get(int index) {
        return row.get(index);
    }

    public synchronized boolean add(Anggota e) {
        int index = row.size();
        boolean b = row.add(e);
        fireTableRowsInserted(index, index);
        return b;
    }

    @Override
    public String getColumnName(int column) {
        return this.column.get(column);
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
            return row.get(rowIndex).getIdAnggota();
        } else if (columnIndex == 1) {
            return row.get(rowIndex).getNamaAnggota();
        } else if (columnIndex == 2) {
            return row.get(rowIndex).getTanggalLahirAnggota();
        } else if (columnIndex == 3) {
            return row.get(rowIndex).getKontakAnggota();
        } else if (columnIndex == 4) {
            return row.get(rowIndex).getAlamatAnggota();
        } else if (columnIndex == 5) {
            return row.get(rowIndex).getTotalmeminjam();
        }
        return null;
    }
}
