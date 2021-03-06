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

import com.mysql.jdbc.Driver;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import usu.rental.film.data.AdminChiper;
import usu.util.StringUtil;
import usu.widget.util.WidgetUtilities;

/**
 *
 * @author  usu
 */
public class PanelDatabase extends javax.swing.JPanel {

    /*
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;
    private Properties prop;
    private AdminChiper admin;
    private Connection c;
    private StringBuilder url;

    /** Creates new form PanelDatabase
     */
    public PanelDatabase() {
        this(null);
    }

    public PanelDatabase(AdminChiper admin) {
        initVariables();
        initComponents();
        initFinals();
        this.admin = admin;
    }

    public void addActionListenerSimpan(ActionListener l) {
        buttonSimpan.addActionListener(l);
        textDatabase.addActionListener(l);
    }

    public void setAdmin(AdminChiper admin) {
        this.admin = admin;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usu.widget.glass.PanelGlass panelGlass1 = new usu.widget.glass.PanelGlass();
        usu.rental.film.widget.Label label1 = new usu.rental.film.widget.Label();
        usu.rental.film.widget.Label label2 = new usu.rental.film.widget.Label();
        usu.rental.film.widget.Label label3 = new usu.rental.film.widget.Label();
        textHost = new usu.rental.film.widget.TextBox();
        textDatabase = new usu.rental.film.widget.TextBox();
        textPort = new usu.rental.film.widget.TextBox();
        buttonSimpan = new usu.rental.film.widget.Button();
        usu.rental.film.widget.Button buttonReset = new usu.rental.film.widget.Button();

        setOpaque(false);

        panelGlass1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        panelGlass1.setOpaqueImage(false);
        panelGlass1.setRound(false);

        label1.setDisplayedMnemonic('H');
        label1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label1.setText("nama host :");

        label2.setDisplayedMnemonic('N');
        label2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label2.setText("nombor port :");

        label3.setDisplayedMnemonic('D');
        label3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        label3.setText("nama database :");

        textHost.setFocusAccelerator('H');
        textHost.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textHostActionPerformed(evt);
            }
        });

        textDatabase.setFocusAccelerator('D');
        textDatabase.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textDatabaseActionPerformed(evt);
            }
        });

        textPort.setFocusAccelerator('N');
        textPort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textPortActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelGlass1Layout = new javax.swing.GroupLayout(panelGlass1);
        panelGlass1.setLayout(panelGlass1Layout);
        panelGlass1Layout.setHorizontalGroup(
            panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGlass1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(label3, javax.swing.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
                    .addComponent(label2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textHost, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                    .addComponent(textDatabase, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                    .addComponent(textPort, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelGlass1Layout.setVerticalGroup(
            panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGlass1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textHost, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGlass1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(label3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textDatabase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        buttonSimpan.setMnemonic('S');
        buttonSimpan.setText("simpan");
        buttonSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSimpanActionPerformed(evt);
            }
        });

        buttonReset.setMnemonic('R');
        buttonReset.setText("reset");
        buttonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelGlass1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelGlass1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSimpan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(286, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private void textHostActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textHostActionPerformed
        textPort.requestFocusInWindow();
    }//GEN-LAST:event_textHostActionPerformed

    private void buttonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonResetActionPerformed
        initVariables();
        initFinals();
}//GEN-LAST:event_buttonResetActionPerformed

    private void textDatabaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textDatabaseActionPerformed
        buttonSimpanActionPerformed(evt);
    }//GEN-LAST:event_textDatabaseActionPerformed

    private void buttonSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSimpanActionPerformed
        if (getConnection() != null) {
            try {
                prop.setProperty("HOST", textHost.getText());
                prop.setProperty("PORT", textPort.getText());
                prop.setProperty("DATABASE", textDatabase.getText());
                prop.storeToXML(new FileOutputStream("setting\\database.xml"), "e'MovieRental");
            } catch (IOException ex) {
                WidgetUtilities.showErrorMessage(this, ex.getMessage());
            }
        }
    }//GEN-LAST:event_buttonSimpanActionPerformed

private void textPortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textPortActionPerformed
    textDatabase.requestFocusInWindow();
}//GEN-LAST:event_textPortActionPerformed

    public Connection getConnection() {
        try {
            new Driver();
            url = new StringBuilder();
            url.append("jdbc:mysql://");
            url.append(textHost.getText());
            url.append(":");
            url.append(textPort.getText());
            url.append("/");
            url.append(textDatabase.getText());
            c = DriverManager.getConnection(url.toString(),
                    StringUtil.convertToString(admin.getUsername("NESIAOKTIANA")),
                    StringUtil.convertToString(admin.getPassword("NESIAOKTIANA")));
        } catch (SQLException ex) {
            c = null;
        }
        return c;
    }

    private void initFinals() {
        textDatabase.setText(prop.getProperty("DATABASE"));
        textHost.setText(prop.getProperty("HOST"));
        textPort.setText(prop.getProperty("PORT"));
    }

    private void initVariables() {
        prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("setting\\database.xml"));
        } catch (IOException ex) {
            prop.setProperty("HOST", "localhost");
            prop.setProperty("PORT", "3306");
            prop.setProperty("DATABASE", "movierental");
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    usu.rental.film.widget.Button buttonSimpan;
    usu.rental.film.widget.TextBox textDatabase;
    usu.rental.film.widget.TextBox textHost;
    usu.rental.film.widget.TextBox textPort;
    // End of variables declaration//GEN-END:variables
}
