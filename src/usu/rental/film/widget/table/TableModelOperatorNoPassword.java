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
import usu.rental.film.data.template.Operator;

/**
 *
 * @author usu
 */
public class TableModelOperatorNoPassword extends AbstractTableModel {

    /*
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;
    private Vector<String> column;
    private Vector<Operator> row;

    public TableModelOperatorNoPassword() {
        super();
        column = new Vector<String>();
        column.add("Id Operator");
        column.add("Nama Operator");
        column.add("Tanggal Lahir Operator");
        column.add("Kontak Operator");
        column.add("Alamat Operator");
        row = new Vector<Operator>();
    }

    public synchronized void add(List<Operator> list) {
        for (Operator o : list) {
            row.add(o);
        }
        fireTableDataChanged();
    }

    public synchronized Operator set(int index, Operator element) {
        Operator o = row.set(index, element);
        fireTableRowsUpdated(index, index);
        return o;
    }

    public synchronized void removeAllElements() {
        row.removeAllElements();
        fireTableDataChanged();
    }

    public synchronized Operator remove(int index) {
        Operator o = row.remove(index);
        fireTableRowsDeleted(index, index);
        return o;
    }

    public synchronized Operator get(int index) {
        return row.get(index);
    }

    public synchronized boolean add(Operator e) {
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
            return row.get(rowIndex).getIdOperator();
        } else if (columnIndex == 1) {
            return row.get(rowIndex).getNamaOperator();
        } else if (columnIndex == 2) {
            return row.get(rowIndex).getTanggalLahirOperator();
        } else if (columnIndex == 3) {
            return row.get(rowIndex).getKontakOperator();
        } else if (columnIndex == 4) {
            return row.get(rowIndex).getAlamatOperator();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return this.column.get(column);
    }
}
