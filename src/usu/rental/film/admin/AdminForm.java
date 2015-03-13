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

import java.awt.AWTException;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import usu.rental.film.data.AdminChiper;
import usu.rental.film.data.exception.DataNotCompleteException;
import usu.rental.film.data.template.Film;
import usu.rental.film.sql.MySQL;
import usu.rental.film.sql.exception.DataNotFoundException;
import usu.rental.film.widget.exception.UnselectedException;
import usu.rental.film.widget.worker.WorkerAction;
import usu.rental.film.widget.worker.WorkerAktivitasOperator;
import usu.rental.film.widget.worker.WorkerAnggota;
import usu.rental.film.widget.worker.WorkerDirektor;
import usu.rental.film.widget.worker.WorkerFilm;
import usu.rental.film.widget.worker.WorkerJenisFilm;
import usu.rental.film.widget.worker.WorkerOperator;
import usu.rental.film.widget.worker.WorkerPemeranUtama;
import usu.rental.film.widget.worker.WorkerPeminjaman;
import usu.rental.film.widget.worker.WorkerPengembalian;
import usu.rental.film.widget.worker.WorkerRumahProduksi;
import usu.widget.Form;
import usu.widget.util.WidgetUtilities;

/**
 *
 * @author  usu
 */
public class AdminForm extends Form {

    /*
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;
    private Connection connection;
    private AdminChiper admin;
    private String tempString;

    /**
     * 
     * @param connection
     * @param admin
     */
    public AdminForm(Connection connection, AdminChiper admin) {
        this();
        this.connection = connection;
        this.admin = admin;
    }

    /** Creates new form BeanForm */
    public AdminForm() {
        
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/usu/rental/film/resource/icon.png")).getImage());
        
        initComponents();
        initActions();
    }

    /**
     * 
     * @param admin
     */
    public void setAdmin(AdminChiper admin) {
        this.admin = admin;
        panelDatabase.setAdmin(admin);
    }

    /**
     * 
     * @param connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    private void initActions() {
        // Menu Utama
        menuUtama.addActionListenerFilm(new AksiButton_MenuUtama_Film());
        menuUtama.addActionListenerOperator(new AksiButton_MenuUtama_Operator());
        menuUtama.addActionListenerLaporan(new AksiButton_MenuUtama_Laporan());
        menuUtama.addActionListenerPengaturan(new AksiButton_MenuUtama_Pengaturan());
        menuUtama.addActionListenerDatabase(new AksiButton_MenuUtama_Database());
        menuUtama.addActionListenerAnggota(new AksiButton_MenuUtama_Anggota());

        // Menu Film
        menuFilm.addActionListenerDirektor(new AksiButton_MenuFilm_Direktor());
        menuFilm.addActionListenerPemeranUtama(new AksiButton_MenuFilm_PemeranUtama());
        menuFilm.addActionListenerRumahProduksi(new AksiButton_MenuFilm_RumahProduksi());
        menuFilm.addActionListenerJenisFilm(new AksiButton_MenuFilm_JenisFilm());
        menuFilm.addActionListenerFilm(new AksiButton_MenuFilm_Film());

        // Menu Operator
        menuOperator.addActionListenerOperator(new AksiButton_MenuOperator_Operator());
        menuOperator.addActionListenerAktivitas(new AksiButton_MenuOperator_Aktivitas());

        // Menu Laporan
        menuLaporan.addActionListenerPeminjaman(new AksiButton_MenuLaporan_Peminjaman());
        menuLaporan.addActionListenerPengembalian(new AksiButton_MenuLaporan_Pengembalian());

        // Database
        panelDatabase.addActionListenerSimpan(new AksiButton_Database_Simpan());

        // Anggota
        panelAnggota.addActionListenerSaring(new AksiButton_Anggota_Saring());
        panelAnggota.addActionListenerTambah(new AksiButton_Anggota_Tambah());
        panelAnggota.addActionListenerSegarkan(new AksiButton_Anggota_Segarkan());
        panelAnggota.addActionListenerUbah(new AksiButton_Anggota_Ubah());
        panelAnggota.addActionListenerHapus(new AksiButton_Anggota_Hapus());

        // Anggota Saring
        panelSaringAnggota.addActionListenerBatal(new AksiButton_AnggotaSaring_Batal());
        panelSaringAnggota.addActionListenerSaring(new AksiButton_AnggotaSaring_Saring());

        // Operator
        panelOperator.addActionListenerSegarkan(new AksiButton_Operator_Segarkan());
        panelOperator.addActionListenerTambah(new AksiButton_Operator_Tambah());
        panelOperator.addActionListenerUbah(new AksiButton_Operator_Ubah());
        panelOperator.addActionListenerHapus(new AksiButton_Operator_Hapus());
        panelOperator.addActionListenerSaring(new AksiButton_Operator_Saring());

        // Operator Saring
        panelSaringOperator.addActionListenerBatal(new AksiButton_OperatorSaring_Batal());
        panelSaringOperator.addActionListenerSaring(new AksiButton_OperatorSaring_Saring());

        // Aktivitas Operator
        panelAktivitasOperator.addActionListenerHapus(new AksiButton_AktivitasOperator_Hapus());
        panelAktivitasOperator.addActionListenerSegarkan(new AksiButton_AktivitasOperator_Segarkan());

        // Direktor
        panelDirektor.addActionListenerHapus(new AksiButton_Direktor_Hapus());
        panelDirektor.addActionListenerUbah(new AksiButton_Direktor_Ubah());
        panelDirektor.addActionListenerTambah(new AksiButton_Direktor_Tambah());
        panelDirektor.addActionListenerSegarkan(new AksiButton_Direktor_Segarkan());
        panelDirektor.addActionListenerSaring(new AksiButton_Direktor_Saring());

        // Pemeran Utama
        panelPemeranUtama.addActionListenerHapus(new AksiButton_PemeranUtama_Hapus());
        panelPemeranUtama.addActionListenerSegarkan(new AksiButton_PemeranUtama_Segarkan());
        panelPemeranUtama.addActionListenerTambah(new AksiButton_PemeranUtama_Tambah());
        panelPemeranUtama.addActionListenerUbah(new AksiButton_PemeranUtama_Ubah());
        panelPemeranUtama.addActionListenerSaring(new AksiButton_PemeranUtama_Saring());

        // Rumah Produksi
        panelRumahProduksi.addActionListenerHapus(new AksiButton_RumahProduksi_Hapus());
        panelRumahProduksi.addActionListenerSaring(new AksiButton_RumahProduksi_Saring());
        panelRumahProduksi.addActionListenerSegarkan(new AksiButton_RumahProduksi_Segarkan());
        panelRumahProduksi.addActionListenerTambah(new AksiButton_RumahProduksi_Tambah());
        panelRumahProduksi.addActionListenerUbah(new AksiButton_RumahProduksi_Ubah());

        // Jenis Film
        panelJenisFilm.addActionListenerHapus(new AksiButton_JenisFilm_Hapus());
        panelJenisFilm.addActionListenerSaring(new AksiButton_JenisFilm_Saring());
        panelJenisFilm.addActionListenerSegarkan(new AksiButton_JenisFilm_Segarkan());
        panelJenisFilm.addActionListenerTambah(new AksiButton_JenisFilm_Tambah());
        panelJenisFilm.addActionListenerUbah(new AksiButton_JenisFilm_Ubah());

        // Peminjaman
        panelPeminjaman.addActionListenerHapus(new AksiButton_Peminjaman_Hapus());
        panelPeminjaman.addActionListenerSaring(new AksiButton_Peminjaman_Saring());
        panelPeminjaman.addActionListenerSegarkan(new AksiButton_Peminjaman_Segarkan());

        // Peminjaman Saring
        panelSaringPeminjaman.addActionListenerBatal(new AksiButton_PeminjamanSaring_Batal());
        panelSaringPeminjaman.addActionListenerSaring(new AksiButton_PeminjamanSaring_Saring());

        // Pengembalian
        panelPengembalian.addActionListenerSegarkan(new AksiButton_Pengembalian_Segarkan());
        panelPengembalian.addActionListenerSaring(new AksiButton_Pengembalian_Saring());
        panelPengembalian.addActionListenerHapus(new AksiButton_Pengembalian_Hapus());

        // Pengembalian Saring
        panelSaringPengembalian.addActionListenerBatal(new AksiButton_PengembalianSaring_Batal());
        panelSaringPengembalian.addActionListenerSaring(new AksiButton_PengembalianSaring_Saring());

        // Film
        panelFilm.addActionListenerTambah(new AksiButton_Film_Tambah());
        panelFilm.addActionListenerSegarkan(new AksiButton_Film_Segarkan());
        panelFilm.addActionListenerUbah(new AksiButton_Film_Ubah());
        panelFilm.addActionListenerHapus(new AksiButton_Film_Hapus());
        panelFilm.addActionListenerSaring(new AksiButton_Film_Saring());

        // Film tambah
        panelTambahFilm.addActionListenerBatal(new AksiButton_FilmTambah_Batal());
        panelTambahFilm.addActionListenerTambah(new AksiButton_FilmTambah_Tambah());

        // Film Ubah
        panelUbahFilm.addActionListenerBatal(new AksiButton_FilmUbah_Batal());
        panelUbahFilm.addActionListenerUbah(new AksiButton_FilmUbah_Ubah());

        // Film Saring
        panelSaringFilm.addActionListenerBatal(new AksiButton_FilmSaring_Batal());
        panelSaringFilm.addActionListenerSaring(new AksiButton_FilmSaring_Saring());
    }

    void showPanel(Component panel, String card) {
        try {
            if (panel.isVisible()) {
                return;
            }
            glasspane.startTransition(panelGradient);
            ((CardLayout) panelCard.getLayout()).show(panelCard, card);
        } catch (AWTException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void setTitleBody(String title) {
        if (title == null) {
            labelEmovie.setText("e'MovieRental");
        } else {
            labelEmovie.setText(labelEmovie.getText() + " : " + title);
        }
    }

    void runLoading(String message) {
        buttonMenu.setEnabled(false);
        progressBar.setIndeterminate(true);
        labelStatusBar.setText(message);
    }

    void doneLoading() {
        buttonMenu.setEnabled(true);
        progressBar.setIndeterminate(false);
        labelStatusBar.setText("e'MovieRental");
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        glasspane = new usu.widget.GlassPane();
        about = new usu.rental.film.widget.About();
        panelGradient = new usu.rental.film.widget.Panel();
        labelEmovie = new usu.rental.film.widget.Label();
        panelCard = new javax.swing.JPanel();
        menuUtama = new usu.rental.film.admin.MenuUtama();
        menuFilm = new usu.rental.film.admin.MenuFilm();
        menuOperator = new usu.rental.film.admin.MenuOperator();
        menuLaporan = new usu.rental.film.admin.MenuLaporan();
        panelPengaturan = new usu.rental.film.admin.PanelPengaturan();
        panelDatabase = new usu.rental.film.admin.PanelDatabase(admin);
        panelAnggota = new usu.rental.film.admin.PanelAnggota();
        panelSaringAnggota = new usu.rental.film.admin.PanelSaringAnggota();
        panelOperator = new usu.rental.film.admin.PanelOperator();
        panelSaringOperator = new usu.rental.film.admin.PanelSaringOperator();
        panelAktivitasOperator = new usu.rental.film.admin.PanelAktivitasOperator();
        panelDirektor = new usu.rental.film.admin.PanelDirektor();
        panelPemeranUtama = new usu.rental.film.admin.PanelPemeranUtama();
        panelRumahProduksi = new usu.rental.film.admin.PanelRumahProduksi();
        panelJenisFilm = new usu.rental.film.admin.PanelJenisFilm();
        panelFilm = new usu.rental.film.admin.PanelFilm();
        panelPeminjaman = new usu.rental.film.admin.PanelPeminjaman();
        panelSaringPeminjaman = new usu.rental.film.admin.PanelSaringPeminjaman();
        panelPengembalian = new usu.rental.film.admin.PanelPengembalian();
        panelSaringPengembalian = new usu.rental.film.admin.PanelSaringPengembalian();
        panelTambahFilm = new usu.rental.film.admin.PanelTambahFilm();
        panelUbahFilm = new usu.rental.film.admin.PanelUbahFilm();
        panelSaringFilm = new usu.rental.film.admin.PanelSaringFilm();
        usu.rental.film.widget.Toolbar toolBar = new usu.rental.film.widget.Toolbar();
        buttonMenu = new usu.rental.film.widget.Button();
        buttonTentang = new usu.rental.film.widget.Button();
        usu.rental.film.widget.Toolbar statusBar = new usu.rental.film.widget.Toolbar();
        labelStatusBar = new usu.rental.film.widget.Label();
        progressBar = new usu.rental.film.widget.ProgressBar();

        setGlassPane(glasspane);
        getGlassPane().setVisible(true);

        about.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutActionPerformed(evt);
            }
        });

        setTitle("e'MovieRental - Administrator");
        setAnimationHide(usu.widget.constan.Animation.HIDE_TO_BOTTOM);
        setAnimationShow(usu.widget.constan.Animation.SHOW_FROM_BOTTOM);
        setMinimumSize(new java.awt.Dimension(800, 600));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        labelEmovie.setText("e'MovieRental");
        labelEmovie.setFont(new java.awt.Font("Tahoma", 1, 24));

        panelCard.setOpaque(false);
        panelCard.setLayout(new java.awt.CardLayout());
        panelCard.add(menuUtama, "MENU_UTAMA");
        panelCard.add(menuFilm, "MENU_FILM");
        panelCard.add(menuOperator, "MENU_OPERATOR");
        panelCard.add(menuLaporan, "MENU_LAPORAN");
        panelCard.add(panelPengaturan, "PENGATURAN");
        panelCard.add(panelDatabase, "DATABASE");
        panelCard.add(panelAnggota, "ANGGOTA");
        panelCard.add(panelSaringAnggota, "ANGGOTA_SARING");
        panelCard.add(panelOperator, "OPERATOR");
        panelCard.add(panelSaringOperator, "OPERATOR_SARING");
        panelCard.add(panelAktivitasOperator, "AKTIVITAS_OPERATOR");
        panelCard.add(panelDirektor, "DIREKTOR");
        panelCard.add(panelPemeranUtama, "PEMERAN_UTAMA");
        panelCard.add(panelRumahProduksi, "RUMAH_PRODUKSI");
        panelCard.add(panelJenisFilm, "JENIS_FILM");
        panelCard.add(panelFilm, "FILM");
        panelCard.add(panelPeminjaman, "PEMINJAMAN");
        panelCard.add(panelSaringPeminjaman, "SARING_PEMINJAMAN");
        panelCard.add(panelPengembalian, "PENGEMBALIAN");
        panelCard.add(panelSaringPengembalian, "SARING_PENGEMBALIAN");
        panelCard.add(panelTambahFilm, "TAMBAH_FILM");
        panelCard.add(panelUbahFilm, "UBAH_FILM");
        panelCard.add(panelSaringFilm, "SARING_FILM");

        javax.swing.GroupLayout panelGradientLayout = new javax.swing.GroupLayout(panelGradient);
        panelGradient.setLayout(panelGradientLayout);
        panelGradientLayout.setHorizontalGroup(
            panelGradientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGradientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelCard, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
                    .addComponent(labelEmovie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelGradientLayout.setVerticalGroup(
            panelGradientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGradientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelEmovie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelCard, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(panelGradient, java.awt.BorderLayout.CENTER);

        toolBar.setRollover(true);

        buttonMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/rental/film/resource/home.png"))); // NOI18N
        buttonMenu.setMnemonic('M');
        buttonMenu.setText("menu");
        buttonMenu.setToolTipText("");
        buttonMenu.setFocusable(false);
        buttonMenu.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonMenuActionPerformed(evt);
            }
        });
        toolBar.add(buttonMenu);

        buttonTentang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/usu/rental/film/resource/tentang.png"))); // NOI18N
        buttonTentang.setMnemonic('T');
        buttonTentang.setText("tentang");
        buttonTentang.setToolTipText("");
        buttonTentang.setFocusable(false);
        buttonTentang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        buttonTentang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTentangActionPerformed(evt);
            }
        });
        toolBar.add(javax.swing.Box.createHorizontalGlue());
        toolBar.add(buttonTentang);

        getContentPane().add(toolBar, java.awt.BorderLayout.PAGE_START);

        statusBar.setRollover(true);

        labelStatusBar.setText("e'MovieRental");
        statusBar.add(labelStatusBar);

        progressBar.setMaximumSize(new java.awt.Dimension(150, 14));
        statusBar.add(javax.swing.Box.createHorizontalGlue());
        statusBar.add(progressBar);

        getContentPane().add(statusBar, java.awt.BorderLayout.PAGE_END);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-800)/2, (screenSize.height-600)/2, 800, 600);
    }// </editor-fold>//GEN-END:initComponents
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        exit();
    }//GEN-LAST:event_formWindowClosing

    private void buttonMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonMenuActionPerformed
        showPanel(menuUtama, "MENU_UTAMA");
        setTitleBody(null);
    }//GEN-LAST:event_buttonMenuActionPerformed

    private void aboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutActionPerformed
        glasspane.hideComponent();
    }//GEN-LAST:event_aboutActionPerformed

    private void buttonTentangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTentangActionPerformed
        glasspane.showComponent(about);
    }//GEN-LAST:event_buttonTentangActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    usu.rental.film.widget.About about;
    usu.rental.film.widget.Button buttonMenu;
    usu.rental.film.widget.Button buttonTentang;
    usu.widget.GlassPane glasspane;
    usu.rental.film.widget.Label labelEmovie;
    usu.rental.film.widget.Label labelStatusBar;
    usu.rental.film.admin.MenuFilm menuFilm;
    usu.rental.film.admin.MenuLaporan menuLaporan;
    usu.rental.film.admin.MenuOperator menuOperator;
    usu.rental.film.admin.MenuUtama menuUtama;
    usu.rental.film.admin.PanelAktivitasOperator panelAktivitasOperator;
    usu.rental.film.admin.PanelAnggota panelAnggota;
    javax.swing.JPanel panelCard;
    usu.rental.film.admin.PanelDatabase panelDatabase;
    usu.rental.film.admin.PanelDirektor panelDirektor;
    usu.rental.film.admin.PanelFilm panelFilm;
    usu.rental.film.widget.Panel panelGradient;
    usu.rental.film.admin.PanelJenisFilm panelJenisFilm;
    usu.rental.film.admin.PanelOperator panelOperator;
    usu.rental.film.admin.PanelPemeranUtama panelPemeranUtama;
    usu.rental.film.admin.PanelPeminjaman panelPeminjaman;
    usu.rental.film.admin.PanelPengaturan panelPengaturan;
    usu.rental.film.admin.PanelPengembalian panelPengembalian;
    usu.rental.film.admin.PanelRumahProduksi panelRumahProduksi;
    usu.rental.film.admin.PanelSaringAnggota panelSaringAnggota;
    usu.rental.film.admin.PanelSaringFilm panelSaringFilm;
    usu.rental.film.admin.PanelSaringOperator panelSaringOperator;
    usu.rental.film.admin.PanelSaringPeminjaman panelSaringPeminjaman;
    usu.rental.film.admin.PanelSaringPengembalian panelSaringPengembalian;
    usu.rental.film.admin.PanelTambahFilm panelTambahFilm;
    usu.rental.film.admin.PanelUbahFilm panelUbahFilm;
    usu.rental.film.widget.ProgressBar progressBar;
    // End of variables declaration//GEN-END:variables

    
   class AksiButton_AktivitasOperator_Hapus implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            if (WidgetUtilities.showConfirmMessage(AdminForm.this,
                  "anda yakin akan menghapus semua aktivitas operator") != JOptionPane.OK_OPTION) {
               return;
            }
            MySQL.emptyAktivitasOperator(AdminForm.this.connection);
            AdminForm.this.panelAktivitasOperator.getTableModel().removeAllElements();
         } catch (final SQLException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         }
      }
   }

   class AksiButton_AktivitasOperator_Segarkan implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            new WorkerAktivitasOperator(AdminForm.this.panelAktivitasOperator.getTableModel(),
                  new WorkerAction() {

                     public void runFirst() {
                        AdminForm.this.panelAktivitasOperator.setEnabled(false);
                        runLoading("Loading Aktivitas Operator");
                     }

                     public void runLast() {
                        AdminForm.this.panelAktivitasOperator.setEnabled(true);
                        doneLoading();
                     }
                  }, AdminForm.this.connection).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_Anggota_Hapus implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         if (AdminForm.this.panelAnggota.getTable().getSelectedRowCount() == 0) {
            WidgetUtilities.showErrorMessage(AdminForm.this,
                  "anggota yang akan dihapus belum terpilih");
            return;
         }

         if (WidgetUtilities.showConfirmMessage(AdminForm.this, "anda yakin?") != JOptionPane.OK_OPTION) {
            return;
         }
         while (AdminForm.this.panelAnggota.getTable().getSelectedRowCount() > 0) {
            try {
               final int index = AdminForm.this.panelAnggota.getTable().convertRowIndexToModel(
                     AdminForm.this.panelAnggota.getTable().getSelectionModel()
                           .getMaxSelectionIndex());
               MySQL.deleteAnggota(AdminForm.this.connection, AdminForm.this.panelAnggota
                     .getTableModel().get(index).getIdAnggota());
               AdminForm.this.panelAnggota.getTableModel().remove(index);
            } catch (final SQLException ex) {
               WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
            }
         }

      }
   }

   class AksiButton_Anggota_Saring implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         showPanel(AdminForm.this.panelSaringAnggota, "ANGGOTA_SARING");
         setTitleBody("Saring");
      }
   }

   class AksiButton_Anggota_Segarkan implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelAnggota.reset();
            new WorkerAnggota(AdminForm.this.panelAnggota.getTableModel(), new WorkerAction() {

               public void runFirst() {
                  AdminForm.this.panelAnggota.setEnabled(false);
                  runLoading("Loading Anggota");
               }

               public void runLast() {
                  AdminForm.this.panelAnggota.setEnabled(true);
                  doneLoading();
               }
            }, AdminForm.this.connection).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_Anggota_Tambah implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            MySQL
                  .insertAnggota(AdminForm.this.connection, AdminForm.this.panelAnggota
                        .getAnggota());
            AdminForm.this.panelAnggota.getTableModel().add(
                  AdminForm.this.panelAnggota.getAnggota());
            AdminForm.this.panelAnggota.reset();
         } catch (final DataNotCompleteException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         } catch (final SQLException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         }
      }
   }

   class AksiButton_Anggota_Ubah implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.tempString = AdminForm.this.panelAnggota.getSelectedId();
            MySQL.updateAnggota(AdminForm.this.connection,
                  AdminForm.this.panelAnggota.getAnggota(), AdminForm.this.tempString);
            AdminForm.this.panelAnggota.updateSelectedTable(AdminForm.this.panelAnggota
                  .getAnggota());
            AdminForm.this.panelAnggota.reset();
         } catch (final UnselectedException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         } catch (final SQLException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         } catch (final DataNotCompleteException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         }
      }
   }

   class AksiButton_AnggotaSaring_Batal implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         showPanel(AdminForm.this.panelAnggota, "ANGGOTA");
         setTitleBody(null);
         setTitleBody("Anggota");
      }
   }

   class AksiButton_AnggotaSaring_Saring implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelAnggota.reset();
            showPanel(AdminForm.this.panelAnggota, "ANGGOTA");
            setTitleBody(null);
            setTitleBody("Anggota");
            new WorkerAnggota(AdminForm.this.connection, AdminForm.this.panelAnggota
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  AdminForm.this.panelAnggota.setEnabled(false);
                  runLoading("Loading Anggota");
               }

               public void runLast() {
                  AdminForm.this.panelAnggota.setEnabled(true);
                  doneLoading();
               }
            }, AdminForm.this.panelSaringAnggota.getId(), AdminForm.this.panelSaringAnggota
                  .getNama(), AdminForm.this.panelSaringAnggota.getLahirDari(),
                  AdminForm.this.panelSaringAnggota.getLahirSampai(),
                  AdminForm.this.panelSaringAnggota.getKontak(), AdminForm.this.panelSaringAnggota
                        .getAlamat(), AdminForm.this.panelSaringAnggota.getTotalMeminjan())
                  .execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_Database_Simpan implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         if (AdminForm.this.panelDatabase.getConnection() == null) {
            WidgetUtilities.showErrorMessage(AdminForm.this, "koneksi mysql gagal");
         } else {
            setConnection(AdminForm.this.panelDatabase.getConnection());
            JOptionPane.showMessageDialog(AdminForm.this, "pengaturan database berhasil disimpan");
         }
      }
   }

   class AksiButton_Direktor_Hapus implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         if (AdminForm.this.panelDirektor.getTable().getSelectedRowCount() == 0) {
            WidgetUtilities.showErrorMessage(AdminForm.this,
                  "direktor yang akan dihapus belum dipilih");
            return;
         }
         if (WidgetUtilities.showConfirmMessage(AdminForm.this, "anda yakin?") != JOptionPane.OK_OPTION) {
            return;
         }
         while (AdminForm.this.panelDirektor.getTable().getSelectedRowCount() > 0) {
            try {
               final int index = AdminForm.this.panelDirektor.getTable().convertRowIndexToModel(
                     AdminForm.this.panelDirektor.getTable().getSelectionModel()
                           .getMaxSelectionIndex());
               AdminForm.this.tempString = AdminForm.this.panelDirektor.getTableModel().get(index)
                     .getIdDirektor();
               MySQL.deleteDirektor(AdminForm.this.connection, AdminForm.this.tempString);
               AdminForm.this.panelDirektor.getTableModel().remove(index);
               AdminForm.this.panelDirektor.reset();
            } catch (final SQLException ex) {
               WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
            }
         }
         AdminForm.this.panelPemeranUtama.reset();
      }
   }

   class AksiButton_Direktor_Saring implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            new WorkerDirektor(AdminForm.this.connection, AdminForm.this.panelDirektor
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  runLoading("Loading Direktor");
                  AdminForm.this.panelDirektor.setEnabled(false);
               }

               public void runLast() {
                  AdminForm.this.panelDirektor.setEnabled(true);
                  doneLoading();
                  AdminForm.this.panelDirektor.reset();
               }
            }, AdminForm.this.panelDirektor.getId(), AdminForm.this.panelDirektor.getNama())
                  .execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_Direktor_Segarkan implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelDirektor.reset();
            new WorkerDirektor(AdminForm.this.connection, AdminForm.this.panelDirektor
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  runLoading("Loading Direktor");
                  AdminForm.this.panelDirektor.setEnabled(false);
               }

               public void runLast() {
                  doneLoading();
                  AdminForm.this.panelDirektor.setEnabled(true);
               }
            }).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_Direktor_Tambah implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            MySQL.insertDirektor(AdminForm.this.connection, AdminForm.this.panelDirektor
                  .getDirektor());
            AdminForm.this.panelDirektor.getTableModel().add(
                  AdminForm.this.panelDirektor.getDirektor());
            AdminForm.this.panelDirektor.reset();
         } catch (final SQLException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         } catch (final DataNotCompleteException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         }
      }
   }

   class AksiButton_Direktor_Ubah implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            if (AdminForm.this.panelDirektor.getTable().getSelectedRowCount() == 0) {
               WidgetUtilities.showErrorMessage(AdminForm.this,
                     "silahkan pilih direktor yang akan diubah");
               return;
            }
            final int index = AdminForm.this.panelDirektor.getTable().convertRowIndexToModel(
                  AdminForm.this.panelDirektor.getTable().getSelectedRow());
            AdminForm.this.tempString = AdminForm.this.panelDirektor.getTableModel().get(index)
                  .getIdDirektor();
            MySQL.updateDirektor(AdminForm.this.connection, AdminForm.this.panelDirektor
                  .getDirektor(), AdminForm.this.tempString);
            AdminForm.this.panelDirektor.getTableModel().set(index,
                  AdminForm.this.panelDirektor.getDirektor());
            AdminForm.this.panelDirektor.reset();
         } catch (final SQLException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         } catch (final DataNotCompleteException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         }

      }
   }

   class AksiButton_Film_Hapus implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         if (AdminForm.this.panelFilm.getTable().getSelectedRowCount() == 0) {
            WidgetUtilities
                  .showErrorMessage(AdminForm.this, "film yang akan dihapus belum dipilih");
            return;
         }
         if (WidgetUtilities.showConfirmMessage(AdminForm.this, "anda yakin?") != JOptionPane.OK_OPTION) {
            return;
         }
         while (AdminForm.this.panelFilm.getTable().getSelectedRowCount() > 0) {
            try {
               final int index = AdminForm.this.panelFilm.getTable()
                     .convertRowIndexToModel(
                           AdminForm.this.panelFilm.getTable().getSelectionModel()
                                 .getMaxSelectionIndex());
               MySQL.deleteFilm(AdminForm.this.connection, AdminForm.this.panelFilm.getTableModel()
                     .get(index).getIdFilm());
               AdminForm.this.panelFilm.getTableModel().remove(index);
            } catch (final SQLException ex) {
               Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
      }
   }

   class AksiButton_Film_Saring implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         showPanel(AdminForm.this.panelSaringFilm, "SARING_FILM");
         setTitleBody("Saring");
      }
   }

   class AksiButton_Film_Segarkan implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelFilm.reset();
            new WorkerFilm(AdminForm.this.connection, AdminForm.this.panelFilm.getTableModel(),
                  new WorkerAction() {

                     public void runFirst() {
                        runLoading("Loading Film");
                        AdminForm.this.panelFilm.setEnabled(false);
                     }

                     public void runLast() {
                        AdminForm.this.panelFilm.setEnabled(true);
                        doneLoading();
                     }
                  }).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_Film_Tambah implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelTambahFilm.reset();
            AdminForm.this.panelTambahFilm.setConnection(AdminForm.this.connection);
            showPanel(AdminForm.this.panelTambahFilm, "TAMBAH_FILM");
            setTitleBody("Tambah");
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_Film_Ubah implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            if (AdminForm.this.panelFilm.getTable().getSelectedRowCount() == 0) {
               WidgetUtilities.showErrorMessage(AdminForm.this,
                     "film yang akan diubah belum dipilih");
               return;
            }
            AdminForm.this.panelUbahFilm.reset();
            final int index = AdminForm.this.panelFilm.getTable().convertRowIndexToModel(
                  AdminForm.this.panelFilm.getTable().getSelectedRow());
            AdminForm.this.panelUbahFilm.setConnection(AdminForm.this.connection,
                  AdminForm.this.panelFilm.getTableModel().get(index));
            showPanel(AdminForm.this.panelUbahFilm, "UBAH_FILM");
            setTitleBody("Ubah");
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         } catch (final DataNotFoundException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_FilmSaring_Batal implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         showPanel(AdminForm.this.panelFilm, "FILM");
         setTitleBody(null);
         setTitleBody("Film : Film");
      }
   }

   class AksiButton_FilmSaring_Saring implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelFilm.reset();
            showPanel(AdminForm.this.panelFilm, "FILM");
            setTitleBody(null);
            setTitleBody("Film : Film");
            new WorkerFilm(AdminForm.this.connection, AdminForm.this.panelFilm.getTableModel(),
                  new WorkerAction() {

                     public void runFirst() {
                        AdminForm.this.panelFilm.setEnabled(false);
                        runLoading("Loading Film");
                     }

                     public void runLast() {
                        doneLoading();
                        AdminForm.this.panelFilm.setEnabled(true);
                     }
                  }, AdminForm.this.panelSaringFilm.getJudulFilm(), AdminForm.this.panelSaringFilm
                        .getPemeranUtama(), AdminForm.this.panelSaringFilm.getDirektor(),
                  AdminForm.this.panelSaringFilm.getRumahProduksi(), AdminForm.this.panelSaringFilm
                        .getJenisFilm(), AdminForm.this.panelSaringFilm.getStatus()).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_FilmTambah_Batal implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         showPanel(AdminForm.this.panelFilm, "FILM");
         setTitleBody(null);
         setTitleBody("Film : Film");
      }
   }

   class AksiButton_FilmTambah_Tambah implements ActionListener {

      private Film tempFilm;

      public void actionPerformed(final ActionEvent e) {
         try {
            MySQL.insertFilm(AdminForm.this.connection, AdminForm.this.panelTambahFilm.getFilm());
            this.tempFilm = MySQL.getFilm(AdminForm.this.connection, AdminForm.this.panelTambahFilm
                  .getFilm().getIdFilm());
            AdminForm.this.panelFilm.getTableModel().add(this.tempFilm);
            showPanel(AdminForm.this.panelFilm, "FILM");
            setTitleBody(null);
            setTitleBody("Film : Film");
         } catch (final DataNotFoundException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         } catch (final SQLException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         } catch (final DataNotCompleteException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         }
      }
   }

   class AksiButton_FilmUbah_Batal implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         showPanel(AdminForm.this.panelFilm, "FILM");
         setTitleBody(null);
         setTitleBody("Film : Film");
      }
   }

   class AksiButton_FilmUbah_Ubah implements ActionListener {

      private Film tempFilm;

      public void actionPerformed(final ActionEvent e) {
         try {
            final int index = AdminForm.this.panelFilm.getTable().convertRowIndexToModel(
                  AdminForm.this.panelFilm.getTable().getSelectedRow());
            AdminForm.this.tempString = AdminForm.this.panelFilm.getTableModel().get(index)
                  .getIdFilm();
            MySQL.updateFilm(AdminForm.this.connection, AdminForm.this.panelUbahFilm.getFilm(),
                  AdminForm.this.tempString);
            this.tempFilm = MySQL.getFilm(AdminForm.this.connection, AdminForm.this.panelUbahFilm
                  .getFilm().getIdFilm());
            AdminForm.this.panelFilm.getTableModel().set(index, this.tempFilm);
            showPanel(AdminForm.this.panelFilm, "FILM");
            setTitleBody(null);
            setTitleBody("Film : Film");
         } catch (final DataNotFoundException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         } catch (final SQLException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         } catch (final DataNotCompleteException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         }
      }
   }

   class AksiButton_JenisFilm_Hapus implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         if (AdminForm.this.panelJenisFilm.getTable().getSelectedRowCount() == 0) {
            WidgetUtilities.showErrorMessage(AdminForm.this,
                  "jenis film yang akan diubah belum dipilih");
            return;
         }
         if (WidgetUtilities.showConfirmMessage(AdminForm.this, "anda yakin?") != JOptionPane.OK_OPTION) {
            return;
         }
         while (AdminForm.this.panelJenisFilm.getTable().getSelectedRowCount() > 0) {
            try {
               final int index = AdminForm.this.panelJenisFilm.getTable().convertRowIndexToModel(
                     AdminForm.this.panelJenisFilm.getTable().getSelectionModel()
                           .getMaxSelectionIndex());
               AdminForm.this.tempString = AdminForm.this.panelJenisFilm.getTableModel().get(index)
                     .getIdJenisFilm();
               MySQL.deleteJenisFilm(AdminForm.this.connection, AdminForm.this.tempString);
               AdminForm.this.panelJenisFilm.getTableModel().remove(index);
            } catch (final SQLException ex) {
               Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
      }
   }

   class AksiButton_JenisFilm_Saring implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            new WorkerJenisFilm(AdminForm.this.connection, AdminForm.this.panelJenisFilm
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  runLoading("Loading Jenis Film");
                  AdminForm.this.panelJenisFilm.setEnabled(false);
               }

               public void runLast() {
                  AdminForm.this.panelJenisFilm.setEnabled(true);
                  doneLoading();
                  AdminForm.this.panelJenisFilm.reset();
               }
            }, AdminForm.this.panelJenisFilm.getId(), AdminForm.this.panelJenisFilm.getNama())
                  .execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_JenisFilm_Segarkan implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelJenisFilm.reset();
            new WorkerJenisFilm(AdminForm.this.connection, AdminForm.this.panelJenisFilm
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  runLoading("Loading Jenis Film");
                  AdminForm.this.panelJenisFilm.setEnabled(false);
               }

               public void runLast() {
                  AdminForm.this.panelJenisFilm.setEnabled(true);
                  doneLoading();
               }
            }).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_JenisFilm_Tambah implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            MySQL.insertJenisFilm(AdminForm.this.connection, AdminForm.this.panelJenisFilm
                  .getJenisFilm());
            AdminForm.this.panelJenisFilm.getTableModel().add(
                  AdminForm.this.panelJenisFilm.getJenisFilm());
            AdminForm.this.panelJenisFilm.reset();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         } catch (final DataNotCompleteException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_JenisFilm_Ubah implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            if (AdminForm.this.panelJenisFilm.getTable().getSelectedRowCount() == 0) {
               WidgetUtilities.showErrorMessage(AdminForm.this,
                     "jenis film yang akan diubah belum dipilih");
               return;
            }
            final int index = AdminForm.this.panelJenisFilm.getTable().convertRowIndexToModel(
                  AdminForm.this.panelJenisFilm.getTable().getSelectedRow());
            AdminForm.this.tempString = AdminForm.this.panelJenisFilm.getTableModel().get(index)
                  .getIdJenisFilm();
            MySQL.updateJenisFilm(AdminForm.this.connection, AdminForm.this.panelJenisFilm
                  .getJenisFilm(), AdminForm.this.tempString);
            AdminForm.this.panelJenisFilm.getTableModel().set(index,
                  AdminForm.this.panelJenisFilm.getJenisFilm());
            AdminForm.this.panelJenisFilm.reset();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         } catch (final DataNotCompleteException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_MenuFilm_Direktor implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelDirektor.reset();
            showPanel(AdminForm.this.panelDirektor, "DIREKTOR");
            setTitleBody("Direktor");
            new WorkerDirektor(AdminForm.this.connection, AdminForm.this.panelDirektor
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  runLoading("Loading Direktor");
                  AdminForm.this.panelDirektor.setEnabled(false);
               }

               public void runLast() {
                  doneLoading();
                  AdminForm.this.panelDirektor.setEnabled(true);
               }
            }).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_MenuFilm_Film implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelFilm.reset();
            showPanel(AdminForm.this.panelFilm, "FILM");
            setTitleBody("Film");
            new WorkerFilm(AdminForm.this.connection, AdminForm.this.panelFilm.getTableModel(),
                  new WorkerAction() {

                     public void runFirst() {
                        AdminForm.this.panelFilm.setEnabled(false);
                        runLoading("Loading Film");
                     }

                     public void runLast() {
                        AdminForm.this.panelFilm.setEnabled(true);
                        doneLoading();
                     }
                  }).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_MenuFilm_JenisFilm implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelJenisFilm.reset();
            showPanel(AdminForm.this.panelJenisFilm, "JENIS_FILM");
            setTitleBody("Jenis Film");
            new WorkerJenisFilm(AdminForm.this.connection, AdminForm.this.panelJenisFilm
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  runLoading("Loading Jenis Film");
                  AdminForm.this.panelJenisFilm.setEnabled(false);
               }

               public void runLast() {
                  AdminForm.this.panelJenisFilm.setEnabled(true);
                  doneLoading();
               }
            }).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_MenuFilm_PemeranUtama implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelPemeranUtama.reset();
            showPanel(AdminForm.this.panelPemeranUtama, "PEMERAN_UTAMA");
            setTitleBody("Pemeran Utama");
            new WorkerPemeranUtama(AdminForm.this.connection, AdminForm.this.panelPemeranUtama
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  runLoading("Loading Pemeran Utama");
                  AdminForm.this.panelPemeranUtama.setEnabled(false);
               }

               public void runLast() {
                  doneLoading();
                  AdminForm.this.panelPemeranUtama.setEnabled(true);
               }
            }).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_MenuFilm_RumahProduksi implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelRumahProduksi.reset();
            showPanel(AdminForm.this.panelRumahProduksi, "RUMAH_PRODUKSI");
            setTitleBody("Rumah Produksi");
            new WorkerRumahProduksi(AdminForm.this.connection, AdminForm.this.panelRumahProduksi
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  AdminForm.this.panelRumahProduksi.setEnabled(false);
                  runLoading("Loading Rumah Produksi");
               }

               public void runLast() {
                  AdminForm.this.panelRumahProduksi.setEnabled(true);
                  doneLoading();
               }
            }).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_MenuLaporan_Peminjaman implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelPeminjaman.reset();
            showPanel(AdminForm.this.panelPeminjaman, "PEMINJAMAN");
            setTitleBody("Peminjaman");
            new WorkerPeminjaman(AdminForm.this.connection, AdminForm.this.panelPeminjaman
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  AdminForm.this.panelPeminjaman.setEnabled(false);
                  runLoading("Loading Peminjaman");
               }

               public void runLast() {
                  AdminForm.this.panelPeminjaman.setEnabled(true);
                  doneLoading();
               }
            }).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_MenuLaporan_Pengembalian implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelPengembalian.reset();
            showPanel(AdminForm.this.panelPengembalian, "PENGEMBALIAN");
            setTitleBody("Pengembalian");
            new WorkerPengembalian(AdminForm.this.connection, AdminForm.this.panelPengembalian
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  runLoading("Loading Pengembalian");
                  AdminForm.this.panelPengembalian.setEnabled(false);
               }

               public void runLast() {
                  AdminForm.this.panelPengembalian.setEnabled(true);
                  doneLoading();
               }
            }).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_MenuOperator_Aktivitas implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            showPanel(AdminForm.this.panelAktivitasOperator, "AKTIVITAS_OPERATOR");
            setTitleBody("Aktivitas Operator");
            new WorkerAktivitasOperator(AdminForm.this.panelAktivitasOperator.getTableModel(),
                  new WorkerAction() {

                     public void runFirst() {
                        AdminForm.this.panelAktivitasOperator.setEnabled(false);
                        runLoading("Loading Aktivitas Operator");
                     }

                     public void runLast() {
                        AdminForm.this.panelAktivitasOperator.setEnabled(true);
                        doneLoading();
                     }
                  }, AdminForm.this.connection).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_MenuOperator_Operator implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelOperator.reset();
            showPanel(AdminForm.this.panelOperator, "OPERATOR");
            setTitleBody("Operator");
            new WorkerOperator(AdminForm.this.connection, AdminForm.this.panelOperator
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  AdminForm.this.panelOperator.setEnabled(false);
                  runLoading("Loading Operator");
               }

               public void runLast() {
                  AdminForm.this.panelOperator.setEnabled(true);
                  doneLoading();
               }
            }).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_MenuUtama_Anggota implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelAnggota.reset();
            showPanel(AdminForm.this.panelAnggota, "ANGGOTA");
            setTitleBody("Anggota");
            new WorkerAnggota(AdminForm.this.panelAnggota.getTableModel(), new WorkerAction() {

               public void runFirst() {
                  AdminForm.this.panelAnggota.setEnabled(false);
                  runLoading("Loading Anggota");
               }

               public void runLast() {
                  AdminForm.this.panelAnggota.setEnabled(true);
                  doneLoading();
               }
            }, AdminForm.this.connection).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_MenuUtama_Database implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         showPanel(AdminForm.this.panelDatabase, "DATABASE");
         setTitleBody("Database");
      }
   }

   class AksiButton_MenuUtama_Film implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         showPanel(AdminForm.this.menuFilm, "MENU_FILM");
         setTitleBody("Film");
      }
   }

   class AksiButton_MenuUtama_Laporan implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         showPanel(AdminForm.this.menuLaporan, "MENU_LAPORAN");
         setTitleBody("Laporan");
      }
   }

   class AksiButton_MenuUtama_Operator implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         showPanel(AdminForm.this.menuOperator, "MENU_OPERATOR");
         setTitleBody("Operator");
      }
   }

   class AksiButton_MenuUtama_Pengaturan implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         showPanel(AdminForm.this.panelPengaturan, "PENGATURAN");
         setTitleBody("Pengaturan");
      }
   }

   class AksiButton_Operator_Hapus implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         if (AdminForm.this.panelOperator.getTable().getSelectedRowCount() == 0) {
            WidgetUtilities.showErrorMessage(AdminForm.this,
                  "operator yang akan dihapus belum terpilih");
            return;
         }
         if (WidgetUtilities.showConfirmMessage(AdminForm.this, "anda yakin?") != JOptionPane.OK_OPTION) {
            return;
         }
         while (AdminForm.this.panelOperator.getTable().getSelectedRowCount() > 0) {
            try {
               final int index = AdminForm.this.panelOperator.getTable().convertRowIndexToModel(
                     AdminForm.this.panelOperator.getTable().getSelectionModel()
                           .getMaxSelectionIndex());
               MySQL.deleteOperator(AdminForm.this.connection, AdminForm.this.panelOperator
                     .getTableModel().get(index).getIdOperator());
               AdminForm.this.panelOperator.getTableModel().remove(index);
            } catch (final SQLException ex) {
               WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
            }
         }
      }
   }

   class AksiButton_Operator_Saring implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         showPanel(AdminForm.this.panelSaringOperator, "OPERATOR_SARING");
         setTitleBody("Saring");
      }
   }

   class AksiButton_Operator_Segarkan implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelOperator.reset();
            new WorkerOperator(AdminForm.this.connection, AdminForm.this.panelOperator
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  AdminForm.this.panelOperator.setEnabled(false);
                  runLoading("Loading Operator");
               }

               public void runLast() {
                  AdminForm.this.panelOperator.setEnabled(true);
                  doneLoading();
               }
            }).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_Operator_Tambah implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            MySQL.insertOperator(AdminForm.this.connection, AdminForm.this.panelOperator
                  .getOperator());
            AdminForm.this.panelOperator.getTableModel().add(
                  AdminForm.this.panelOperator.getOperator());
            AdminForm.this.panelOperator.reset();
         } catch (final SQLException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         } catch (final DataNotCompleteException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         }
      }
   }

   class AksiButton_Operator_Ubah implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            if (AdminForm.this.panelOperator.getTable().getSelectedRowCount() == 0) {
               WidgetUtilities.showErrorMessage(AdminForm.this,
                     "operator yang akan diubah belum dipilih");
            }
            final int index = AdminForm.this.panelOperator.getTable().convertRowIndexToModel(
                  AdminForm.this.panelOperator.getTable().getSelectedRow());
            AdminForm.this.tempString = AdminForm.this.panelOperator.getTableModel().get(index)
                  .getIdOperator();
            MySQL.updateOperator(AdminForm.this.connection, AdminForm.this.panelOperator
                  .getOperator(), AdminForm.this.tempString);
            AdminForm.this.panelOperator.getTableModel().set(index,
                  AdminForm.this.panelOperator.getOperator());
         } catch (final SQLException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         } catch (final DataNotCompleteException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         }
      }
   }

   class AksiButton_OperatorSaring_Batal implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         showPanel(AdminForm.this.panelOperator, "OPERATOR");
         setTitleBody(null);
         setTitleBody("Operator : Operator");
      }
   }

   class AksiButton_OperatorSaring_Saring implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelOperator.reset();
            showPanel(AdminForm.this.panelOperator, "OPERATOR");
            setTitleBody(null);
            setTitleBody("Operator : Operator");
            new WorkerOperator(AdminForm.this.connection, AdminForm.this.panelOperator
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  AdminForm.this.panelOperator.setEnabled(false);
                  runLoading("Loading Operator");
               }

               public void runLast() {
                  AdminForm.this.panelOperator.setEnabled(true);
                  doneLoading();
               }
            }, AdminForm.this.panelSaringOperator.getId(), AdminForm.this.panelSaringOperator
                  .getNama(), AdminForm.this.panelSaringOperator.getLahirDari(),
                  AdminForm.this.panelSaringOperator.getLahirSampai(),
                  AdminForm.this.panelSaringOperator.getKontak(),
                  AdminForm.this.panelSaringOperator.getAlamat()).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_PemeranUtama_Hapus implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         if (AdminForm.this.panelPemeranUtama.getTable().getSelectedRowCount() == 0) {
            WidgetUtilities.showErrorMessage(AdminForm.this,
                  "pemeran utama yang akan dihapus belum dipilih");
            return;
         }
         if (WidgetUtilities.showConfirmMessage(AdminForm.this, "anda yakin?") != JOptionPane.OK_OPTION) {
            return;
         }
         while (AdminForm.this.panelPemeranUtama.getTable().getSelectedRowCount() > 0) {
            try {
               final int index = AdminForm.this.panelPemeranUtama.getTable()
                     .convertRowIndexToModel(
                           AdminForm.this.panelPemeranUtama.getTable().getSelectionModel()
                                 .getMaxSelectionIndex());
               MySQL.deletePemeranUtama(AdminForm.this.connection, AdminForm.this.panelPemeranUtama
                     .getTableModel().get(index).getIdPemeranUtama());
               AdminForm.this.panelPemeranUtama.getTableModel().remove(index);
            } catch (final SQLException ex) {
               WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
            }
         }
         AdminForm.this.panelPemeranUtama.reset();
      }
   }

   class AksiButton_PemeranUtama_Saring implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            new WorkerPemeranUtama(AdminForm.this.connection, AdminForm.this.panelPemeranUtama
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  runLoading("Loading Pemeran Utama");
                  AdminForm.this.panelPemeranUtama.setEnabled(false);
               }

               public void runLast() {
                  doneLoading();
                  AdminForm.this.panelPemeranUtama.setEnabled(true);
                  AdminForm.this.panelPemeranUtama.reset();
               }
            }, AdminForm.this.panelPemeranUtama.getId(), AdminForm.this.panelPemeranUtama.getNama())
                  .execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_PemeranUtama_Segarkan implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelPemeranUtama.reset();
            new WorkerPemeranUtama(AdminForm.this.connection, AdminForm.this.panelPemeranUtama
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  runLoading("Loading Pemeran Utama");
                  AdminForm.this.panelPemeranUtama.setEnabled(false);
               }

               public void runLast() {
                  doneLoading();
                  AdminForm.this.panelPemeranUtama.setEnabled(true);
               }
            }).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_PemeranUtama_Tambah implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            MySQL.insertPemeranUtama(AdminForm.this.connection, AdminForm.this.panelPemeranUtama
                  .getPemeranUtama());
            AdminForm.this.panelPemeranUtama.getTableModel().add(
                  AdminForm.this.panelPemeranUtama.getPemeranUtama());
            AdminForm.this.panelPemeranUtama.reset();
         } catch (final SQLException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         } catch (final DataNotCompleteException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         }
      }
   }

   class AksiButton_PemeranUtama_Ubah implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            if (AdminForm.this.panelPemeranUtama.getTable().getSelectedRowCount() == 0) {
               WidgetUtilities.showErrorMessage(AdminForm.this,
                     "pemeran utama yang akan diubah belum dipilih");
               return;
            }
            final int index = AdminForm.this.panelPemeranUtama.getTable().convertRowIndexToModel(
                  AdminForm.this.panelPemeranUtama.getTable().getSelectedRow());
            AdminForm.this.tempString = AdminForm.this.panelPemeranUtama.getTableModel().get(index)
                  .getIdPemeranUtama();
            MySQL.updatePemeranUtama(AdminForm.this.connection, AdminForm.this.panelPemeranUtama
                  .getPemeranUtama(), AdminForm.this.tempString);
            AdminForm.this.panelPemeranUtama.getTableModel().set(index,
                  AdminForm.this.panelPemeranUtama.getPemeranUtama());
            AdminForm.this.panelPemeranUtama.reset();
         } catch (final SQLException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         } catch (final DataNotCompleteException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         }
      }
   }

   class AksiButton_Peminjaman_Hapus implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         if (AdminForm.this.panelPeminjaman.getTable().getSelectedRowCount() == 0) {
            WidgetUtilities.showErrorMessage(AdminForm.this,
                  "transaksi peminjaman yang akan dihapus belum dipilih");
            return;
         }
         if (WidgetUtilities.showConfirmMessage(AdminForm.this, "anda yakin?") != JOptionPane.OK_OPTION) {
            return;
         }
         while (AdminForm.this.panelPeminjaman.getTable().getSelectedRowCount() > 0) {
            try {
               final int index = AdminForm.this.panelPeminjaman.getTable().convertRowIndexToModel(
                     AdminForm.this.panelPeminjaman.getTable().getSelectionModel()
                           .getMaxSelectionIndex());
               final int id = AdminForm.this.panelPeminjaman.getTableModel().get(index)
                     .getIdTransaksi();
               MySQL.deletePeminjaman(AdminForm.this.connection, id);
               AdminForm.this.panelPeminjaman.getTableModel().remove(index);
            } catch (final SQLException ex) {
               Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
         AdminForm.this.panelPeminjaman.reset();
      }
   }

   class AksiButton_Peminjaman_Saring implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         showPanel(AdminForm.this.panelSaringPeminjaman, "SARING_PEMINJAMAN");
         setTitleBody("Saring");
      }
   }

   class AksiButton_Peminjaman_Segarkan implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelPeminjaman.reset();
            new WorkerPeminjaman(AdminForm.this.connection, AdminForm.this.panelPeminjaman
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  AdminForm.this.panelPeminjaman.setEnabled(false);
                  runLoading("Loading Peminjaman");
               }

               public void runLast() {
                  AdminForm.this.panelPeminjaman.setEnabled(true);
                  doneLoading();
               }
            }).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_PeminjamanSaring_Batal implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         showPanel(AdminForm.this.panelPeminjaman, "PEMINJAMAN");
         setTitleBody(null);
         setTitleBody("Laporan : Peminjaman");
      }
   }

   class AksiButton_PeminjamanSaring_Saring implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            showPanel(AdminForm.this.panelPeminjaman, "PEMINJAMAN");
            setTitleBody(null);
            setTitleBody("Laporan : Peminjaman");
            AdminForm.this.panelPeminjaman.reset();
            new WorkerPeminjaman(AdminForm.this.connection, AdminForm.this.panelPeminjaman
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  AdminForm.this.panelPeminjaman.setEnabled(false);
                  runLoading("Loading Peminjaman");
               }

               public void runLast() {
                  AdminForm.this.panelPeminjaman.setEnabled(true);
                  doneLoading();
               }
            }, AdminForm.this.panelSaringPeminjaman.getIdAnggota(),
                  AdminForm.this.panelSaringPeminjaman.getIdOperator(),
                  AdminForm.this.panelSaringPeminjaman.getIdFilm(),
                  AdminForm.this.panelSaringPeminjaman.getTanggalDari(),
                  AdminForm.this.panelSaringPeminjaman.getTanggalSampai(),
                  AdminForm.this.panelSaringPeminjaman.getStatus()).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_Pengembalian_Hapus implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         if (AdminForm.this.panelPengembalian.getTable().getSelectedRowCount() == 0) {
            WidgetUtilities.showErrorMessage(AdminForm.this,
                  "transaksi pengembalian yang akan dihapus belum dipilih");
            return;
         }
         if (WidgetUtilities.showConfirmMessage(AdminForm.this, "anda yakin?") != JOptionPane.OK_OPTION) {
            return;
         }
         while (AdminForm.this.panelPengembalian.getTable().getSelectedRowCount() > 0) {
            try {
               final int index = AdminForm.this.panelPengembalian.getTable()
                     .convertRowIndexToModel(
                           AdminForm.this.panelPengembalian.getTable().getSelectionModel()
                                 .getMaxSelectionIndex());
               final int id = AdminForm.this.panelPengembalian.getTableModel().get(index)
                     .getIdTransaksi();
               MySQL.deletePengembalian(AdminForm.this.connection, id);
               AdminForm.this.panelPengembalian.getTableModel().remove(index);
            } catch (final SQLException ex) {
               Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
      }
   }

   class AksiButton_Pengembalian_Saring implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         showPanel(AdminForm.this.panelSaringPengembalian, "SARING_PENGEMBALIAN");
         setTitleBody("Saring");
      }
   }

   class AksiButton_Pengembalian_Segarkan implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelPengembalian.reset();
            new WorkerPengembalian(AdminForm.this.connection, AdminForm.this.panelPengembalian
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  runLoading("Loading Pengembalian");
                  AdminForm.this.panelPengembalian.setEnabled(false);
               }

               public void runLast() {
                  AdminForm.this.panelPengembalian.setEnabled(true);
                  doneLoading();
               }
            }).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_PengembalianSaring_Batal implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         showPanel(AdminForm.this.panelPengembalian, "PENGEMBALIAN");
         setTitleBody(null);
         setTitleBody("Laporan : Pengembalian");
      }
   }

   class AksiButton_PengembalianSaring_Saring implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelPengembalian.reset();
            showPanel(AdminForm.this.panelPengembalian, "PENGEMBALIAN");
            setTitleBody(null);
            setTitleBody("Laporan : Pengembalian");
            new WorkerPengembalian(AdminForm.this.connection, AdminForm.this.panelPengembalian
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  runLoading("Loading Pengembalian");
                  AdminForm.this.panelPengembalian.setEnabled(false);
               }

               public void runLast() {
                  AdminForm.this.panelPengembalian.setEnabled(true);
                  doneLoading();
               }
            }, AdminForm.this.panelSaringPengembalian.getIdOperator(),
                  AdminForm.this.panelSaringPengembalian.getTanggalDari(),
                  AdminForm.this.panelSaringPengembalian.getTanggalSampai(),
                  AdminForm.this.panelSaringPengembalian.getTotalTerlambat(),
                  AdminForm.this.panelSaringPengembalian.getDenda()).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_RumahProduksi_Hapus implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         if (AdminForm.this.panelRumahProduksi.getTable().getSelectedRowCount() == 0) {
            WidgetUtilities.showErrorMessage(AdminForm.this,
                  "rumah produksi yang akan dihapus belum dipilih");
            return;
         }
         if (WidgetUtilities.showConfirmMessage(AdminForm.this, "anda yakin?") != JOptionPane.OK_OPTION) {
            return;
         }
         while (AdminForm.this.panelRumahProduksi.getTable().getSelectedRowCount() > 0) {
            try {
               final int index = AdminForm.this.panelRumahProduksi.getTable()
                     .convertRowIndexToModel(
                           AdminForm.this.panelRumahProduksi.getTable().getSelectionModel()
                                 .getMaxSelectionIndex());
               AdminForm.this.tempString = AdminForm.this.panelRumahProduksi.getTableModel().get(
                     index).getIdRumahProduksi();
               MySQL.deleteRumahProduksi(AdminForm.this.connection, AdminForm.this.tempString);
               AdminForm.this.panelRumahProduksi.getTableModel().remove(index);
            } catch (final SQLException ex) {
               WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
            }
         }
      }
   }

   class AksiButton_RumahProduksi_Saring implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            new WorkerRumahProduksi(AdminForm.this.connection, AdminForm.this.panelRumahProduksi
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  AdminForm.this.panelRumahProduksi.setEnabled(false);
                  runLoading("Loading Rumah Produksi");
               }

               public void runLast() {
                  AdminForm.this.panelRumahProduksi.setEnabled(true);
                  doneLoading();
                  AdminForm.this.panelRumahProduksi.reset();
               }
            }, AdminForm.this.panelRumahProduksi.getId(), AdminForm.this.panelRumahProduksi
                  .getNama()).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_RumahProduksi_Segarkan implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            AdminForm.this.panelRumahProduksi.reset();
            new WorkerRumahProduksi(AdminForm.this.connection, AdminForm.this.panelRumahProduksi
                  .getTableModel(), new WorkerAction() {

               public void runFirst() {
                  AdminForm.this.panelRumahProduksi.setEnabled(false);
                  runLoading("Loading Rumah Produksi");
               }

               public void runLast() {
                  AdminForm.this.panelRumahProduksi.setEnabled(true);
                  doneLoading();
               }
            }).execute();
         } catch (final SQLException ex) {
            Logger.getLogger(AdminForm.class.getName()).log(Level.SEVERE, null, ex);
         }
      }
   }

   class AksiButton_RumahProduksi_Tambah implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            MySQL.insertRumahProduksi(AdminForm.this.connection, AdminForm.this.panelRumahProduksi
                  .getRumahProduksi());
            AdminForm.this.panelRumahProduksi.getTableModel().add(
                  AdminForm.this.panelRumahProduksi.getRumahProduksi());
            AdminForm.this.panelRumahProduksi.reset();
         } catch (final SQLException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         } catch (final DataNotCompleteException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         }
      }
   }

   class AksiButton_RumahProduksi_Ubah implements ActionListener {

      public void actionPerformed(final ActionEvent e) {
         try {
            if (AdminForm.this.panelRumahProduksi.getTable().getSelectedRowCount() == 0) {
               WidgetUtilities.showErrorMessage(AdminForm.this,
                     "rumah produksi yang akan diubah belum dipilih");
               return;
            }
            final int index = AdminForm.this.panelRumahProduksi.getTable().convertRowIndexToModel(
                  AdminForm.this.panelRumahProduksi.getTable().getSelectedRow());
            AdminForm.this.tempString = AdminForm.this.panelRumahProduksi.getTableModel()
                  .get(index).getIdRumahProduksi();
            MySQL.updateRumahProduksi(AdminForm.this.connection, AdminForm.this.panelRumahProduksi
                  .getRumahProduksi(), AdminForm.this.tempString);
            AdminForm.this.panelRumahProduksi.getTableModel().set(index,
                  AdminForm.this.panelRumahProduksi.getRumahProduksi());
            AdminForm.this.panelRumahProduksi.reset();
         } catch (final SQLException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         } catch (final DataNotCompleteException ex) {
            WidgetUtilities.showErrorMessage(AdminForm.this, ex.getMessage());
         }
      }
   }
}
