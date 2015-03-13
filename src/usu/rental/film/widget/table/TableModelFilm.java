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
import usu.rental.film.data.template.Film;

/**
 *
 * @author usu
 */
public class TableModelFilm extends AbstractTableModel {

    /*
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;
    private Vector<String> column;
    private Vector<Film> row;

    public TableModelFilm() {
        super();
        column = new Vector<String>();
        column.add("Id Film");
        column.add("Judul Film");
        column.add("Pemeran Utama");
        column.add("Direktor");
        column.add("Rumah Produksi");
        column.add("Jenis Film");
        column.add("Total Dipinjam");
        column.add("Status");
        row = new Vector<Film>();
    }

    public synchronized void add(List<Film> list) {
        for (Film f : list) {
            row.add(f);
        }
        fireTableDataChanged();
    }

    public synchronized Film set(int index, Film element) {
        Film f = row.set(index, element);
        fireTableRowsUpdated(index, index);
        return f;
    }

    public synchronized void removeAllElements() {
        row.removeAllElements();
        fireTableDataChanged();
    }

    public synchronized Film remove(int index) {
        Film f = row.remove(index);
        fireTableRowsDeleted(index, index);
        return f;
    }

    public synchronized Film get(int index) {
        return row.get(index);
    }

    public synchronized boolean add(Film e) {
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
            return row.get(rowIndex).getIdFilm();
        } else if (columnIndex == 1) {
            return row.get(rowIndex).getJudulFilm();
        } else if (columnIndex == 2) {
            return row.get(rowIndex).getPemeranUtama();
        } else if (columnIndex == 3) {
            return row.get(rowIndex).getDirektor();
        } else if (columnIndex == 4) {
            return row.get(rowIndex).getRumahProduksi();
        } else if (columnIndex == 5) {
            return row.get(rowIndex).getJenisFilm();
        } else if (columnIndex == 6) {
            return row.get(rowIndex).getTotalDipinjam();
        } else if (columnIndex == 7) {
            if (row.get(rowIndex).isDipinjam()) {
                return "Sedang Dipinjam";
            } else {
                return "Tidak Dipinjam";
            }
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return this.column.get(column);
    }
}
