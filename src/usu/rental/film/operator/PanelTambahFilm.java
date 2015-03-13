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
package usu.rental.film.operator;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import usu.rental.film.data.DefaultFilm;
import usu.rental.film.data.exception.DataNotCompleteException;
import usu.rental.film.data.template.Direktor;
import usu.rental.film.data.template.Film;
import usu.rental.film.data.template.JenisFilm;
import usu.rental.film.data.template.PemeranUtama;
import usu.rental.film.data.template.RumahProduksi;
import usu.rental.film.widget.table.TableModelDirektor;
import usu.rental.film.widget.table.TableModelJenisFilm;
import usu.rental.film.widget.table.TableModelPemeranUtama;
import usu.rental.film.widget.table.TableModelRumahProduksi;
import usu.rental.film.widget.table.render.DefaultTableCellRender;
import usu.rental.film.widget.worker.WorkerAction;
import usu.rental.film.widget.worker.WorkerDirektor;
import usu.rental.film.widget.worker.WorkerJenisFilm;
import usu.rental.film.widget.worker.WorkerPemeranUtama;
import usu.rental.film.widget.worker.WorkerRumahProduksi;

/**
 *
 * @author  usu
 */
public class PanelTambahFilm extends javax.swing.JPanel {

    private static final long serialVersionUID = -1;
    private TableModelDirektor modelDirektor;
    private TableModelJenisFilm modelJenisFilm;
    private TableModelRumahProduksi modelRumahProduksi;
    private TableModelPemeranUtama modelPemeranUtama;
    private DefaultFilm tempFilm;
    private WorkerAction worker;

    /** Creates new form PanelTambahFilm */
    public PanelTambahFilm() {
        initVariables();
        initComponents();
        initFinals();
        initActions();
    }

    public void setConnection(Connection connection) throws SQLException {

        new WorkerDirektor(connection, modelDirektor, worker).execute();
        new WorkerPemeranUtama(connection, modelPemeranUtama, worker).execute();
        new WorkerJenisFilm(connection, modelJenisFilm, worker).execute();
        new WorkerRumahProduksi(connection, modelRumahProduksi, worker).execute();

    }

    public void addActionListenerBatal(ActionListener l) {
        buttonBatal.addActionListener(l);
    }

    public void addActionListenerTambah(ActionListener l) {
        buttonTambah.addActionListener(l);
    }

    public Film getFilm() throws DataNotCompleteException {
        if (textIdFilm.getText().trim().equals("")) {
            throw new DataNotCompleteException("id film masih kosong");
        }
        if (textJudulFilm.getText().trim().equals("")) {
            throw new DataNotCompleteException("judul film masih kosong");
        }
        if (textPemeran.getText().trim().equals("")) {
            throw new DataNotCompleteException("pemeran utama masih kosong");
        }
        if (textDirektor.getText().trim().equals("")) {
            throw new DataNotCompleteException("direktor masih kosong");
        }
        if (textRumahProduksi.getText().trim().equals("")) {
            throw new DataNotCompleteException("rumah produksi masih kosong");
        }
        if (textJenisFilm.getText().trim().equals("")) {
            throw new DataNotCompleteException("jenis film masih kosong");
        }
        tempFilm = new DefaultFilm();
        tempFilm.setDipinjam(false);
        tempFilm.setDirektor(textDirektor.getText());
        tempFilm.setIdFilm(textIdFilm.getText());
        tempFilm.setJenisFilm(textJenisFilm.getText());
        tempFilm.setJudulFilm(textJudulFilm.getText());
        tempFilm.setPemeranUtama(textPemeran.getText());
        tempFilm.setRumahProduksi(textRumahProduksi.getText());
        tempFilm.setTotalDipinjam(0);
        return tempFilm;
    }

    public void reset() {
        textDirektor.setText("");
        textIdFilm.setText("");
        textJenisFilm.setText("");
        textJudulFilm.setText("");
        textPemeran.setText("");
        textRumahProduksi.setText("");
        textCari.setText("");
        scrollPane.getViewport().setView(null);
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

        tableDirektor.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            private Direktor temp;

            public void valueChanged(ListSelectionEvent e) {
                try {
                    int index = tableDirektor.convertRowIndexToModel(tableDirektor.getSelectedRow());
                    temp = modelDirektor.get(index);
                    textDirektor.setText(temp.getIdDirektor());
                } catch (IndexOutOfBoundsException ex) {
                }
            }
        });

        tableJenisFilm.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            private JenisFilm temp;

            public void valueChanged(ListSelectionEvent e) {
                try {
                    int index = tableJenisFilm.convertRowIndexToModel(tableJenisFilm.getSelectedRow());
                    temp = modelJenisFilm.get(index);
                    textJenisFilm.setText(temp.getIdJenisFilm());
                } catch (IndexOutOfBoundsException ex) {
                }
            }
        });

        tablePemeranUtama.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            private PemeranUtama temp;

            public void valueChanged(ListSelectionEvent e) {
                try {
                    int index = tablePemeranUtama.convertRowIndexToModel(tablePemeranUtama.getSelectedRow());
                    temp = modelPemeranUtama.get(index);
                    textPemeran.setText(temp.getIdPemeranUtama());
                } catch (IndexOutOfBoundsException ex) {
                }
            }
        });

        tableRumahProduksi.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

            private RumahProduksi temp;

            public void valueChanged(ListSelectionEvent e) {
                try {
                    int index = tableRumahProduksi.convertRowIndexToModel(tableRumahProduksi.getSelectedRow());
                    temp = modelRumahProduksi.get(index);
                    textRumahProduksi.setText(temp.getIdRumahProduksi());
                } catch (IndexOutOfBoundsException ex) {
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    public void filterTable(String text) {
        if (text.trim().equals("")) {
            ((TableRowSorter<TableModel>) tableDirektor.getRowSorter()).setRowFilter(null);
            ((TableRowSorter<TableModel>) tableJenisFilm.getRowSorter()).setRowFilter(null);
            ((TableRowSorter<TableModel>) tablePemeranUtama.getRowSorter()).setRowFilter(null);
            ((TableRowSorter<TableModel>) tableRumahProduksi.getRowSorter()).setRowFilter(null);
        } else {
            ((TableRowSorter<TableModel>) tableDirektor.getRowSorter()).setRowFilter(RowFilter.regexFilter(text));
            ((TableRowSorter<TableModel>) tableJenisFilm.getRowSorter()).setRowFilter(RowFilter.regexFilter(text));
            ((TableRowSorter<TableModel>) tablePemeranUtama.getRowSorter()).setRowFilter(RowFilter.regexFilter(text));
            ((TableRowSorter<TableModel>) tableRumahProduksi.getRowSorter()).setRowFilter(RowFilter.regexFilter(text));
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tablePemeranUtama = new usu.rental.film.widget.Table();
        tableDirektor = new usu.rental.film.widget.Table();
        tableJenisFilm = new usu.rental.film.widget.Table();
        tableRumahProduksi = new usu.rental.film.widget.Table();
        usu.widget.glass.PanelGlass panelGlass1 = new usu.widget.glass.PanelGlass();
        usu.rental.film.widget.Label label1 = new usu.rental.film.widget.Label();
        usu.rental.film.widget.Label label2 = new usu.rental.film.widget.Label();
        usu.rental.film.widget.Label label3 = new usu.rental.film.widget.Label();
        usu.rental.film.widget.Label label4 = new usu.rental.film.widget.Label();
        usu.rental.film.widget.Label label5 = new usu.rental.film.widget.Label();
        usu.rental.film.widget.Label label6 = new usu.rental.film.widget.Label();
        textIdFilm = new usu.rental.film.widget.TextBox();
        textJudulFilm = new usu.rental.film.widget.TextBox();
        textPemeran = new usu.rental.film.widget.TextBox();
        textDirektor = new usu.rental.film.widget.TextBox();
        textRumahProduksi = new usu.rental.film.widget.TextBox();
        textJenisFilm = new usu.rental.film.widget.TextBox();
        scrollPane = new usu.rental.film.widget.ScrollPane();
        buttonTambah = new usu.rental.film.widget.Button();
        buttonBatal = new usu.rental.film.widget.Button();
        usu.rental.film.widget.Label label7 = new usu.rental.film.widget.Label();
        textCari = new usu.rental.film.widget.TextBox();

        setOpaque(false);

        panelGlass1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        panelGlass1.setOpaqueImage(false);
        panelGlass1.setRound(false);

        label1.setDisplayedMnemonic('I');
        label1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label1.setText("id film :");

        label2.setDisplayedMnemonic('J');
        label2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label2.setText("judul film :");

        label3.setDisplayedMnemonic('P');
        label3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label3.setText("pemeran utama :");

        label4.setDisplayedMnemonic('D');
        label4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label4.setText("direktor :");

        label5.setDisplayedMnemonic('R');
        label5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label5.setText("rumah produksi :");

        label6.setDisplayedMnemonic('S');
        label6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label6.setText("jenis film :");

        textIdFilm.setFocusAccelerator('I');
        textIdFilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textIdFilmActionPerformed(evt);
            }
        });
        textIdFilm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textIdFilmFocusGained(evt);
            }
        });

        textJudulFilm.setFocusAccelerator('J');
        textJudulFilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textJudulFilmActionPerformed(evt);
            }
        });
        textJudulFilm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textJudulFilmFocusGained(evt);
            }
        });

        textPemeran.setFocusAccelerator('P');
        textPemeran.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPemeranActionPerformed(evt);
            }
        });
        textPemeran.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textPemeranFocusGained(evt);
            }
        });

        textDirektor.setFocusAccelerator('D');
        textDirektor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textDirektorActionPerformed(evt);
            }
        });
        textDirektor.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textDirektorFocusGained(evt);
            }
        });

        textRumahProduksi.setFocusAccelerator('R');
        textRumahProduksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textRumahProduksiActionPerformed(evt);
            }
        });
        textRumahProduksi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textRumahProduksiFocusGained(evt);
            }
        });

        textJenisFilm.setFocusAccelerator('S');
        textJenisFilm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textJenisFilmActionPerformed(evt);
            }
        });
        textJenisFilm.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textJenisFilmFocusGained(evt);
            }
        });

        javax.swing.GroupLayout panelGlass1Layout = new javax.swing.GroupLayout(panelGlass1);
        panelGlass1.setLayout(panelGlass1Layout);
        panelGlass1Layout.setHorizontalGroup(
            panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGlass1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(label1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textJudulFilm, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addComponent(textIdFilm, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addComponent(textPemeran, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addComponent(textDirektor, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addComponent(textRumahProduksi, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE)
                    .addComponent(textJenisFilm, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelGlass1Layout.setVerticalGroup(
            panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGlass1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textIdFilm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textJudulFilm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textPemeran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textDirektor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textRumahProduksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textJenisFilm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));

        buttonTambah.setMnemonic('H');
        buttonTambah.setText("tambah");

        buttonBatal.setMnemonic('B');
        buttonBatal.setText("batal");

        label7.setDisplayedMnemonic('C');
        label7.setText("cari (case sensitive) :");

        textCari.setFocusAccelerator('C');

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
                    .addComponent(panelGlass1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buttonBatal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textCari, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGlass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBatal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textCari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void initFinals() {
        tableDirektor.setModel(modelDirektor);
        tableJenisFilm.setModel(modelJenisFilm);
        tablePemeranUtama.setModel(modelPemeranUtama);
        tableRumahProduksi.setModel(modelRumahProduksi);

        for (int i = 0; i < tableDirektor.getColumnCount(); i++) {
            tableDirektor.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRender());
        }

        for (int i = 0; i < tableJenisFilm.getColumnCount(); i++) {
            tableJenisFilm.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRender());
        }

        for (int i = 0; i < tablePemeranUtama.getColumnCount(); i++) {
            tablePemeranUtama.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRender());
        }

        for (int i = 0; i < tableRumahProduksi.getColumnCount(); i++) {
            tableRumahProduksi.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRender());
        }
    }

    private void initVariables() {
        modelDirektor = new TableModelDirektor();
        modelJenisFilm = new TableModelJenisFilm();
        modelPemeranUtama = new TableModelPemeranUtama();
        modelRumahProduksi = new TableModelRumahProduksi();
        worker = new WorkerAction() {

            public void runFirst() {
                tableDirektor.setEnabled(false);
                tableRumahProduksi.setEnabled(false);
                tablePemeranUtama.setEnabled(false);
                tableJenisFilm.setEnabled(false);
            }

            public void runLast() {
                tableDirektor.setEnabled(true);
                tableRumahProduksi.setEnabled(true);
                tablePemeranUtama.setEnabled(true);
                tableJenisFilm.setEnabled(true);
            }
        };
    }

private void textPemeranFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textPemeranFocusGained
    textCari.setText("");
    scrollPane.getViewport().setView(tablePemeranUtama);
}//GEN-LAST:event_textPemeranFocusGained

private void textDirektorFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textDirektorFocusGained
    textCari.setText("");
    scrollPane.getViewport().setView(tableDirektor);
}//GEN-LAST:event_textDirektorFocusGained

private void textRumahProduksiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textRumahProduksiFocusGained
    textCari.setText("");
    scrollPane.getViewport().setView(tableRumahProduksi);
}//GEN-LAST:event_textRumahProduksiFocusGained

private void textJenisFilmFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textJenisFilmFocusGained
    textCari.setText("");
    scrollPane.getViewport().setView(tableJenisFilm);
}//GEN-LAST:event_textJenisFilmFocusGained

private void textIdFilmFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textIdFilmFocusGained
    textCari.setText("");
    scrollPane.getViewport().setView(null);
}//GEN-LAST:event_textIdFilmFocusGained

private void textJudulFilmFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textJudulFilmFocusGained
    textCari.setText("");
    scrollPane.getViewport().setView(null);
}//GEN-LAST:event_textJudulFilmFocusGained

private void textIdFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textIdFilmActionPerformed
    textJudulFilm.requestFocusInWindow();
}//GEN-LAST:event_textIdFilmActionPerformed

private void textJudulFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textJudulFilmActionPerformed
    textPemeran.requestFocusInWindow();
}//GEN-LAST:event_textJudulFilmActionPerformed

private void textPemeranActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPemeranActionPerformed
    textDirektor.requestFocusInWindow();
}//GEN-LAST:event_textPemeranActionPerformed

private void textDirektorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textDirektorActionPerformed
    textRumahProduksi.requestFocusInWindow();
}//GEN-LAST:event_textDirektorActionPerformed

private void textRumahProduksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textRumahProduksiActionPerformed
    textJenisFilm.requestFocusInWindow();
}//GEN-LAST:event_textRumahProduksiActionPerformed

private void textJenisFilmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textJenisFilmActionPerformed
    buttonTambah.requestFocusInWindow();
}//GEN-LAST:event_textJenisFilmActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    usu.rental.film.widget.Button buttonBatal;
    usu.rental.film.widget.Button buttonTambah;
    usu.rental.film.widget.ScrollPane scrollPane;
    usu.rental.film.widget.Table tableDirektor;
    usu.rental.film.widget.Table tableJenisFilm;
    usu.rental.film.widget.Table tablePemeranUtama;
    usu.rental.film.widget.Table tableRumahProduksi;
    usu.rental.film.widget.TextBox textCari;
    usu.rental.film.widget.TextBox textDirektor;
    usu.rental.film.widget.TextBox textIdFilm;
    usu.rental.film.widget.TextBox textJenisFilm;
    usu.rental.film.widget.TextBox textJudulFilm;
    usu.rental.film.widget.TextBox textPemeran;
    usu.rental.film.widget.TextBox textRumahProduksi;
    // End of variables declaration//GEN-END:variables
}
