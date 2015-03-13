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
 * 
 */
package usu.rental.film.admin;

import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import usu.rental.film.data.DefaultAnggota;
import usu.rental.film.data.exception.DataNotCompleteException;
import usu.rental.film.data.template.Anggota;
import usu.rental.film.widget.Table;
import usu.rental.film.widget.exception.UnselectedException;
import usu.rental.film.widget.table.TableModelAnggota;
import usu.rental.film.widget.table.render.DefaultTableCellRender;

/**
 *
 * @author  usu
 */
public class PanelAnggota extends javax.swing.JPanel {

    /*
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;
    private TableModelAnggota tableModel;
    private DefaultAnggota tempAnggota;

    /** Creates new form PanelAnggota */
    public PanelAnggota() {
        initVariables();
        initComponents();
        initFinals();
        initActions();
    }

    public void addActionListenerSaring(ActionListener l) {
        buttonSaring.addActionListener(l);
    }

    public void addActionListenerHapus(ActionListener l) {
        buttonHapus.addActionListener(l);
    }

    public void addActionListenerSegarkan(ActionListener l) {
        buttonSegarkan.addActionListener(l);
    }

    public void addActionListenerTambah(ActionListener l) {
        buttonTambah.addActionListener(l);
    }

    public void addActionListenerUbah(ActionListener l) {
        buttonUbah.addActionListener(l);
    }

    public Anggota getAnggota() throws DataNotCompleteException {
        if (textIdAnggota.getText().trim().equals("")) {
            throw new DataNotCompleteException("id masih kosong");
        }
        if (textNamaAnggota.getText().trim().equals("")) {
            throw new DataNotCompleteException("nama masih kosong");
        }
        Calendar now = Calendar.getInstance();
        Calendar waktu = Calendar.getInstance();
        waktu.setTime(((Date) textTanggalLahirAnggota.getValue()));
        if (waktu.get(Calendar.YEAR) > now.get(Calendar.YEAR)) {
            throw new DataNotCompleteException("tanggal lahir tidak valid");
        } else if (waktu.get(Calendar.YEAR) == now.get(Calendar.YEAR)) {
            if (waktu.get(Calendar.MONTH) > now.get(Calendar.MONTH)) {
                throw new DataNotCompleteException("tanggal lahir tidak valid");
            } else if (waktu.get(Calendar.MONTH) == now.get(Calendar.MONTH)) {
                if (waktu.get(Calendar.DATE) >= now.get(Calendar.DATE)) {
                    throw new DataNotCompleteException("tanggal lahir tidak valid");
                }
            }
        }
        if (textKontakAnggota.getText().trim().equals("")) {
            throw new DataNotCompleteException("kontak masih kosong");
        }
        if (textAlamatAnggota.getText().trim().equals("")) {
            throw new DataNotCompleteException("alamat masih kosong");
        }
        tempAnggota = new DefaultAnggota();
        tempAnggota.setAlamatAnggota(textAlamatAnggota.getText());
        tempAnggota.setIdAnggota(textIdAnggota.getText());
        tempAnggota.setKontakAnggota(textKontakAnggota.getText());
        tempAnggota.setNamaAnggota(textNamaAnggota.getText());
        tempAnggota.setTanggalLahirAnggota(new java.sql.Date(((Date) textTanggalLahirAnggota.getValue()).getTime()));
        tempAnggota.setTotalmeminjam(0);
        return tempAnggota;
    }

    public String getSelectedId() throws UnselectedException {
        if (table.getSelectedRowCount() == 0) {
            throw new UnselectedException("silahkan seleksi salah satu baris pada tabel");
        }
        int index = table.convertRowIndexToModel(table.getSelectedRow());
        return tableModel.get(index).getIdAnggota();
    }

    public TableModelAnggota getTableModel() {
        return tableModel;
    }

    public void updateSelectedTable(Anggota anggota) {
        int index = table.convertRowIndexToModel(table.getSelectedRow());
        tableModel.set(index, anggota);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        table.setEnabled(enabled);
        textAlamatAnggota.setEnabled(enabled);
        textCari.setEnabled(enabled);
        textIdAnggota.setEnabled(enabled);
        textKontakAnggota.setEnabled(enabled);
        textNamaAnggota.setEnabled(enabled);
        textTanggalLahirAnggota.setEnabled(enabled);
        buttonHapus.setEnabled(enabled);
        buttonSaring.setEnabled(enabled);
        buttonSegarkan.setEnabled(enabled);
        buttonTambah.setEnabled(enabled);
        buttonUbah.setEnabled(enabled);
    }

    public void reset() {
        textAlamatAnggota.setText("");
        textCari.setText("");
        textIdAnggota.setText("");
        textKontakAnggota.setText("");
        textNamaAnggota.setText("");
        textTanggalLahirAnggota.setValue(new Date());
    }

    public Table getTable() {
        return table;
    }

    private void initActions() {
        textCari.getDocument().addDocumentListener(new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                filterTable(textCari.getText());
            }

            public void removeUpdate(DocumentEvent e) {
                filterTable(textCari.getText());
            }

            public void changedUpdate(DocumentEvent e) {
                filterTable(textCari.getText());
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            public void valueChanged(ListSelectionEvent e) {
                try {
                    int index = table.convertRowIndexToModel(table.getSelectedRow());
                    textAlamatAnggota.setText(tableModel.get(index).getAlamatAnggota());
                    textIdAnggota.setText(tableModel.get(index).getIdAnggota());
                    textKontakAnggota.setText(tableModel.get(index).getKontakAnggota());
                    textNamaAnggota.setText(tableModel.get(index).getNamaAnggota());
                    textTanggalLahirAnggota.setValue(tableModel.get(index).getTanggalLahirAnggota());
                } catch (IndexOutOfBoundsException ex) {
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    public void filterTable(String text) {
        if (text.trim().equals("")) {
            ((TableRowSorter<TableModel>) table.getRowSorter()).setRowFilter(null);
        } else {
            ((TableRowSorter<TableModel>) table.getRowSorter()).setRowFilter(RowFilter.regexFilter(text));
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usu.rental.film.widget.ScrollPane scrollpane = new usu.rental.film.widget.ScrollPane();
        table = new usu.rental.film.widget.Table();
        usu.rental.film.widget.Label label1 = new usu.rental.film.widget.Label();
        textCari = new usu.rental.film.widget.TextBox();
        buttonSaring = new usu.rental.film.widget.Button();
        buttonHapus = new usu.rental.film.widget.Button();
        buttonUbah = new usu.rental.film.widget.Button();
        buttonTambah = new usu.rental.film.widget.Button();
        buttonSegarkan = new usu.rental.film.widget.Button();
        usu.widget.glass.PanelGlass panelGlass1 = new usu.widget.glass.PanelGlass();
        usu.rental.film.widget.Label label2 = new usu.rental.film.widget.Label();
        usu.rental.film.widget.Label label3 = new usu.rental.film.widget.Label();
        usu.rental.film.widget.Label label4 = new usu.rental.film.widget.Label();
        usu.rental.film.widget.Label label5 = new usu.rental.film.widget.Label();
        usu.rental.film.widget.Label label6 = new usu.rental.film.widget.Label();
        textIdAnggota = new usu.rental.film.widget.TextBox();
        textNamaAnggota = new usu.rental.film.widget.TextBox();
        textKontakAnggota = new usu.rental.film.widget.TextBox();
        textTanggalLahirAnggota = new usu.rental.film.widget.FormatterBox();
        textAlamatAnggota = new usu.rental.film.widget.TextBox();

        setOpaque(false);

        scrollpane.setViewportView(table);

        label1.setDisplayedMnemonic('C');
        label1.setText("cari (case sensitive) :");

        textCari.setFocusAccelerator('C');

        buttonSaring.setMnemonic('G');
        buttonSaring.setText("saring");

        buttonHapus.setMnemonic('P');
        buttonHapus.setText("hapus");

        buttonUbah.setMnemonic('U');
        buttonUbah.setText("ubah");

        buttonTambah.setMnemonic('H');
        buttonTambah.setText("tambah");

        buttonSegarkan.setMnemonic('S');
        buttonSegarkan.setText("segarkan");

        panelGlass1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelGlass1.setOpaqueImage(false);
        panelGlass1.setRound(false);

        label2.setDisplayedMnemonic('I');
        label2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label2.setText("id anggota :");

        label3.setDisplayedMnemonic('N');
        label3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label3.setText("nama anggota :");

        label4.setDisplayedMnemonic('L');
        label4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label4.setText("tangal lahir anggota :");

        label5.setDisplayedMnemonic('K');
        label5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label5.setText("kontak anggota :");

        label6.setDisplayedMnemonic('A');
        label6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label6.setText("alamat anggota :");

        textIdAnggota.setFocusAccelerator('I');
        textIdAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textIdAnggotaActionPerformed(evt);
            }
        });

        textNamaAnggota.setFocusAccelerator('N');
        textNamaAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNamaAnggotaActionPerformed(evt);
            }
        });

        textKontakAnggota.setFocusAccelerator('K');
        textKontakAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textKontakAnggotaActionPerformed(evt);
            }
        });

        textTanggalLahirAnggota.setFocusAccelerator('L');
        textTanggalLahirAnggota.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG))));
        textTanggalLahirAnggota.setValue(new java.util.Date());
        textTanggalLahirAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTanggalLahirAnggotaActionPerformed(evt);
            }
        });

        textAlamatAnggota.setFocusAccelerator('A');
        textAlamatAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAlamatAnggotaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelGlass1Layout = new javax.swing.GroupLayout(panelGlass1);
        panelGlass1.setLayout(panelGlass1Layout);
        panelGlass1Layout.setHorizontalGroup(
            panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGlass1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textAlamatAnggota, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                    .addComponent(textKontakAnggota, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                    .addComponent(textTanggalLahirAnggota, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                    .addComponent(textNamaAnggota, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE)
                    .addComponent(textIdAnggota, javax.swing.GroupLayout.DEFAULT_SIZE, 511, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelGlass1Layout.setVerticalGroup(
            panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGlass1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textIdAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textNamaAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textTanggalLahirAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textKontakAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textAlamatAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelGlass1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollpane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textCari, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(buttonSegarkan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 282, Short.MAX_VALUE)
                        .addComponent(buttonTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonUbah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonHapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSaring, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollpane, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGlass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSegarkan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSaring, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonHapus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonUbah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

private void textIdAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textIdAnggotaActionPerformed
    textNamaAnggota.requestFocusInWindow();
}//GEN-LAST:event_textIdAnggotaActionPerformed

private void textNamaAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNamaAnggotaActionPerformed
    textTanggalLahirAnggota.requestFocusInWindow();
}//GEN-LAST:event_textNamaAnggotaActionPerformed

private void textTanggalLahirAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTanggalLahirAnggotaActionPerformed
    textKontakAnggota.requestFocusInWindow();
}//GEN-LAST:event_textTanggalLahirAnggotaActionPerformed

private void textKontakAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textKontakAnggotaActionPerformed
    textAlamatAnggota.requestFocusInWindow();
}//GEN-LAST:event_textKontakAnggotaActionPerformed

private void textAlamatAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAlamatAnggotaActionPerformed
    buttonTambah.requestFocusInWindow();
}//GEN-LAST:event_textAlamatAnggotaActionPerformed

    private void initFinals() {
        table.setModel(tableModel);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRender());
        }
    }

    private void initVariables() {
        tableModel = new TableModelAnggota();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    usu.rental.film.widget.Button buttonHapus;
    usu.rental.film.widget.Button buttonSaring;
    usu.rental.film.widget.Button buttonSegarkan;
    usu.rental.film.widget.Button buttonTambah;
    usu.rental.film.widget.Button buttonUbah;
    usu.rental.film.widget.Table table;
    usu.rental.film.widget.TextBox textAlamatAnggota;
    usu.rental.film.widget.TextBox textCari;
    usu.rental.film.widget.TextBox textIdAnggota;
    usu.rental.film.widget.TextBox textKontakAnggota;
    usu.rental.film.widget.TextBox textNamaAnggota;
    usu.rental.film.widget.FormatterBox textTanggalLahirAnggota;
    // End of variables declaration//GEN-END:variables
}
