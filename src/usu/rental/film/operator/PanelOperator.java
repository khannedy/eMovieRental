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
import java.util.Calendar;
import java.util.Date;
import usu.rental.film.data.DefaultOperator;
import usu.rental.film.data.exception.DataNotCompleteException;
import usu.rental.film.data.template.Operator;
import usu.util.StringUtil;

/**
 *
 * @author  usu
 */
public class PanelOperator extends javax.swing.JPanel {

    private static final long serialVersionUID = -1;
    private DefaultOperator tempAnggota;

    /** Creates new form PanelOperator */
    public PanelOperator() {
        initComponents();
    }

    public void addActionListenerBatal(ActionListener l) {
        batal.addActionListener(l);
    }

    public void addActionListenerSimpan(ActionListener l) {
        simpan.addActionListener(l);
    }

    public void setOperator(Operator operator) {
        textAlamat.setText(operator.getAlamatOperator());
        textId.setText(operator.getIdOperator());
        textKontak.setText(operator.getKontakOperator());
        textNama.setText(operator.getNamaOperator());
        textPassword.setText(operator.getPassword());
        textTanggalLahir.setValue(operator.getTanggalLahirOperator());
    }

    public Operator getOperator() throws DataNotCompleteException {
        if (textId.getText().trim().equals("")) {
            throw new DataNotCompleteException("id masih kosong");
        }
        if (textPassword.getPassword().length == 0) {
            throw new DataNotCompleteException("password masih kosong");
        }
        if (textNama.getText().trim().equals("")) {
            throw new DataNotCompleteException("nama masih kosong");
        }
        Calendar now = Calendar.getInstance();
        Calendar waktu = Calendar.getInstance();
        waktu.setTime(((Date) textTanggalLahir.getValue()));
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
        if (textKontak.getText().trim().equals("")) {
            throw new DataNotCompleteException("kontak masih kosong");
        }
        if (textAlamat.getText().trim().equals("")) {
            throw new DataNotCompleteException("alamat masih kosong");
        }
        tempAnggota = new DefaultOperator();
        tempAnggota.setAlamatOperator(textAlamat.getText());
        tempAnggota.setIdOperator(textId.getText());
        tempAnggota.setKontakOperator(textKontak.getText());
        tempAnggota.setNamaOperator(textNama.getText());
        tempAnggota.setTanggalLahirOperator(new java.sql.Date(((Date) textTanggalLahir.getValue()).getTime()));
        tempAnggota.setPassword(StringUtil.convertToString(textPassword.getPassword()));
        return tempAnggota;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usu.widget.glass.PanelGlass panelGlass1 = new usu.widget.glass.PanelGlass();
        usu.rental.film.widget.Label label1 = new usu.rental.film.widget.Label();
        usu.rental.film.widget.Label label2 = new usu.rental.film.widget.Label();
        usu.rental.film.widget.Label label3 = new usu.rental.film.widget.Label();
        usu.rental.film.widget.Label label4 = new usu.rental.film.widget.Label();
        usu.rental.film.widget.Label label5 = new usu.rental.film.widget.Label();
        usu.rental.film.widget.Label label6 = new usu.rental.film.widget.Label();
        textId = new usu.rental.film.widget.TextBox();
        textPassword = new usu.rental.film.widget.PasswordBox();
        textNama = new usu.rental.film.widget.TextBox();
        textTanggalLahir = new usu.rental.film.widget.FormatterBox();
        textKontak = new usu.rental.film.widget.TextBox();
        usu.rental.film.widget.ScrollPane scrollPane1 = new usu.rental.film.widget.ScrollPane();
        textAlamat = new usu.rental.film.widget.TextArea();
        simpan = new usu.rental.film.widget.Button();
        batal = new usu.rental.film.widget.Button();

        setOpaque(false);

        panelGlass1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        panelGlass1.setOpaqueImage(false);
        panelGlass1.setRound(false);

        label1.setDisplayedMnemonic('I');
        label1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label1.setText("id :");

        label2.setDisplayedMnemonic('P');
        label2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label2.setText("password :");

        label3.setDisplayedMnemonic('N');
        label3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label3.setText("nama :");

        label4.setDisplayedMnemonic('L');
        label4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label4.setText("tangal lahir :");

        label5.setDisplayedMnemonic('K');
        label5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label5.setText("kontak :");

        label6.setDisplayedMnemonic('A');
        label6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label6.setText("alamat :");

        textId.setText("id");
        textId.setFocusAccelerator('I');
        textId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textIdActionPerformed(evt);
            }
        });

        textPassword.setText("password");
        textPassword.setFocusAccelerator('P');
        textPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPasswordActionPerformed(evt);
            }
        });

        textNama.setText("nama");
        textNama.setFocusAccelerator('N');
        textNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textNamaActionPerformed(evt);
            }
        });

        textTanggalLahir.setFocusAccelerator('L');
        textTanggalLahir.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.LONG))));
        textTanggalLahir.setValue(new java.util.Date());
        textTanggalLahir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTanggalLahirActionPerformed(evt);
            }
        });

        textKontak.setText("kontak");
        textKontak.setFocusAccelerator('K');
        textKontak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textKontakActionPerformed(evt);
            }
        });

        textAlamat.setColumns(20);
        textAlamat.setRows(5);
        textAlamat.setText("alamat");
        textAlamat.setFocusAccelerator('A');
        scrollPane1.setViewportView(textAlamat);

        javax.swing.GroupLayout panelGlass1Layout = new javax.swing.GroupLayout(panelGlass1);
        panelGlass1.setLayout(panelGlass1Layout);
        panelGlass1Layout.setHorizontalGroup(
            panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGlass1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addComponent(textId, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addComponent(textNama, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addComponent(textTanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addComponent(textKontak, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelGlass1Layout.setVerticalGroup(
            panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGlass1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textKontak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(label6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        simpan.setMnemonic('S');
        simpan.setText("simpan");

        batal.setMnemonic('B');
        batal.setText("batal");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelGlass1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGlass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(simpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(batal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(143, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void textIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textIdActionPerformed
    textPassword.requestFocusInWindow();
}//GEN-LAST:event_textIdActionPerformed

private void textPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPasswordActionPerformed
    textNama.requestFocusInWindow();
}//GEN-LAST:event_textPasswordActionPerformed

private void textNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textNamaActionPerformed
    textTanggalLahir.requestFocusInWindow();
}//GEN-LAST:event_textNamaActionPerformed

private void textTanggalLahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTanggalLahirActionPerformed
    textKontak.requestFocusInWindow();
}//GEN-LAST:event_textTanggalLahirActionPerformed

private void textKontakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textKontakActionPerformed
    textAlamat.requestFocusInWindow();
}//GEN-LAST:event_textKontakActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    usu.rental.film.widget.Button batal;
    usu.rental.film.widget.Button simpan;
    usu.rental.film.widget.TextArea textAlamat;
    usu.rental.film.widget.TextBox textId;
    usu.rental.film.widget.TextBox textKontak;
    usu.rental.film.widget.TextBox textNama;
    usu.rental.film.widget.PasswordBox textPassword;
    usu.rental.film.widget.FormatterBox textTanggalLahir;
    // End of variables declaration//GEN-END:variables
}
