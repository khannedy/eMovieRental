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
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import usu.rental.film.data.DefaultPemeranUtama;
import usu.rental.film.data.exception.DataNotCompleteException;
import usu.rental.film.data.template.PemeranUtama;
import usu.rental.film.widget.Table;
import usu.rental.film.widget.table.TableModelPemeranUtama;
import usu.rental.film.widget.table.render.DefaultTableCellRender;

/**
 *
 * @author  usu
 */
public class PanelPemeranUtama extends javax.swing.JPanel {

    /*
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;
    private TableModelPemeranUtama tableModel;
    private DefaultPemeranUtama tempPemeranUtama;

    /** Creates new form PanelDirektor */
    public PanelPemeranUtama() {
        initVariables();
        initComponents();
        initFinals();
        initActions();
    }

    public void addActionListenerSegarkan(ActionListener l) {
        buttonSegarkan.addActionListener(l);
    }

    public void addActionListenerSaring(ActionListener l) {
        buttonSaring.addActionListener(l);
    }

    public void addActionListenerTambah(ActionListener l) {
        buttonTambah.addActionListener(l);
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
                    textId.setText(tableModel.get(index).getIdPemeranUtama());
                    textNama.setText(tableModel.get(index).getNamaPemeranUtama());
                } catch (IndexOutOfBoundsException ex) {
                }
            }
        });
    }

    public String getId() {
        if (textId.isEnabled()) {
            return textId.getText();
        }
        return null;
    }

    public String getNama() {
        if (textNama.isEnabled()) {
            return textNama.getText();
        }
        return null;
    }

    public PemeranUtama getPemeranUtama() throws DataNotCompleteException {
        if (!textId.isEnabled()) {
            throw new DataNotCompleteException("textbox id ter-'disable'");
        }
        if (textId.getText().equals("")) {
            throw new DataNotCompleteException("id pemeran utama masih kosong");
        }
        if (!textNama.isEnabled()) {
            throw new DataNotCompleteException("textbox nama ter-'disable'");
        }
        if (textNama.getText().equals("")) {
            throw new DataNotCompleteException("nama pemeran utama masih kosong");
        }
        tempPemeranUtama = new DefaultPemeranUtama();
        tempPemeranUtama.setIdPemeranUtama(textId.getText());
        tempPemeranUtama.setNamaPemeranUtama(textNama.getText());
        return tempPemeranUtama;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        textCari.setEnabled(enabled);
        textId.setEnabled(enabled);
        textNama.setEnabled(enabled);
        table.setEnabled(enabled);
        buttonSaring.setEnabled(enabled);
        buttonSegarkan.setEnabled(enabled);
        buttonTambah.setEnabled(enabled);
    }

    public void reset() {
        textCari.setText("");
        textId.setText("");
        textNama.setText("");
    }

    private void initFinals() {
        table.setModel(tableModel);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(new DefaultTableCellRender());
        }
    }

    public Table getTable() {
        return table;
    }

    public TableModelPemeranUtama getTableModel() {
        return tableModel;
    }

    private void initVariables() {
        tableModel = new TableModelPemeranUtama();
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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        usu.rental.film.widget.Label label1 = new usu.rental.film.widget.Label();
        textCari = new usu.rental.film.widget.TextBox();
        usu.rental.film.widget.ScrollPane scrollPane1 = new usu.rental.film.widget.ScrollPane();
        table = new usu.rental.film.widget.Table();
        usu.widget.glass.PanelGlass panelGlass1 = new usu.widget.glass.PanelGlass();
        usu.rental.film.widget.Label label2 = new usu.rental.film.widget.Label();
        usu.rental.film.widget.Label label3 = new usu.rental.film.widget.Label();
        textNama = new usu.rental.film.widget.TextBox();
        textId = new usu.rental.film.widget.TextBox();
        javax.swing.JCheckBox jCheckBox1 = new javax.swing.JCheckBox();
        javax.swing.JCheckBox jCheckBox2 = new javax.swing.JCheckBox();
        buttonSegarkan = new usu.rental.film.widget.Button();
        buttonSaring = new usu.rental.film.widget.Button();
        buttonTambah = new usu.rental.film.widget.Button();

        setOpaque(false);

        label1.setDisplayedMnemonic('C');
        label1.setText("cari (case sensitive) : ");

        textCari.setFocusAccelerator('C');

        scrollPane1.setViewportView(table);

        panelGlass1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelGlass1.setOpaqueImage(false);
        panelGlass1.setRound(false);

        label2.setDisplayedMnemonic('I');
        label2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label2.setText("id pemeran utama :");

        label3.setDisplayedMnemonic('N');
        label3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label3.setText("nama pemeran utama :");

        textNama.setFocusAccelerator('N');
        textNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNamaActionPerformed(evt);
            }
        });

        textId.setFocusAccelerator('I');
        textId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textIdActionPerformed(evt);
            }
        });

        jCheckBox1.setFocusable(false);
        jCheckBox1.setOpaque(false);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, textId, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jCheckBox1, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        jCheckBox2.setFocusable(false);
        jCheckBox2.setOpaque(false);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, textNama, org.jdesktop.beansbinding.ELProperty.create("${enabled}"), jCheckBox2, org.jdesktop.beansbinding.BeanProperty.create("selected"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout panelGlass1Layout = new javax.swing.GroupLayout(panelGlass1);
        panelGlass1.setLayout(panelGlass1Layout);
        panelGlass1Layout.setHorizontalGroup(
            panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGlass1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(label2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textId, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)
                    .addComponent(textNama, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBox1)
                    .addComponent(jCheckBox2))
                .addContainerGap())
        );
        panelGlass1Layout.setVerticalGroup(
            panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGlass1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonSegarkan.setMnemonic('S');
        buttonSegarkan.setText("segarkan");

        buttonSaring.setMnemonic('G');
        buttonSaring.setText("saring");

        buttonTambah.setMnemonic('H');
        buttonTambah.setText("tambah");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textCari, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
                    .addComponent(panelGlass1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonSegarkan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 383, Short.MAX_VALUE)
                        .addComponent(buttonTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelGlass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSegarkan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSaring, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonTambah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        bindingGroup.bind();
    }// </editor-fold>//GEN-END:initComponents

private void textIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textIdActionPerformed
    textNama.requestFocusInWindow();
}//GEN-LAST:event_textIdActionPerformed

private void textNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNamaActionPerformed
    buttonTambah.requestFocusInWindow();
}//GEN-LAST:event_textNamaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    usu.rental.film.widget.Button buttonSaring;
    usu.rental.film.widget.Button buttonSegarkan;
    usu.rental.film.widget.Button buttonTambah;
    usu.rental.film.widget.Table table;
    usu.rental.film.widget.TextBox textCari;
    usu.rental.film.widget.TextBox textId;
    usu.rental.film.widget.TextBox textNama;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
