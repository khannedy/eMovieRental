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

import java.awt.AWTException;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import usu.rental.film.data.DefaultAktivitasOperator;
import usu.rental.film.data.DefaultPeminjaman;
import usu.rental.film.data.DefaultPengembalian;
import usu.rental.film.data.DefaultPesan;
import usu.rental.film.data.Setting;
import usu.rental.film.data.exception.DataNotCompleteException;
import usu.rental.film.data.template.Anggota;
import usu.rental.film.data.template.Film;
import usu.rental.film.data.template.Operator;
import usu.rental.film.data.template.Peminjaman;
import usu.rental.film.data.template.Pesan;
import usu.rental.film.sql.MySQL;
import usu.rental.film.sql.exception.DataNotFoundException;
import usu.rental.film.widget.worker.WorkerAction;
import usu.rental.film.widget.worker.WorkerAnggota;
import usu.rental.film.widget.worker.WorkerDirektor;
import usu.rental.film.widget.worker.WorkerFilm;
import usu.rental.film.widget.worker.WorkerJenisFilm;
import usu.rental.film.widget.worker.WorkerPemeranUtama;
import usu.rental.film.widget.worker.WorkerPesan;
import usu.rental.film.widget.worker.WorkerRumahProduksi;
import usu.widget.Form;
import usu.widget.util.WidgetUtilities;

/**
 *
 * @author  usu
 */
public class OperatorForm extends Form {

    /*
     * Serial version UID
     */
    private static final long serialVersionUID = 1L;
    private Connection connection;
    private usu.rental.film.data.template.Operator operator;
    private Setting setting;

    /** Creates new form BeanForm */
    public OperatorForm() {
        super();

        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/usu/rental/film/resource/icon.png")).getImage());

        initComponents();
        initActions();
    }

    /**
     * 
     * @param operator
     */
    public void setOperator(usu.rental.film.data.template.Operator operator) {
        this.operator = operator;
    }

    /**
     * 
     * @param setting
     */
    public void setSetting(Setting setting) {
        this.setting = setting;
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
        menuUtama.addActionListenerAnggota(new AksiButton_MenuUtama_Anggota());
        menuUtama.addActionListenerPribadi(new AksiButton_MenuUtama_Pribadi());
        menuUtama.addActionListenerPeminjaman(new AksiButton_MenuUtama_Peminjaman());
        menuUtama.addActionListenerPengembalian(new AksiButton_MenuUtama_Pengembalian());
        menuUtama.addActionListenerPesan(new AksiButton_MenuUtama_Pesan());

        // Menu Film
        menuFilm.addActionListenerDirektor(new AksiButton_MenuFilm_Direktor());
        menuFilm.addActionListenerPemeranUtama(new AksiButton_MenuFilm_PemeranUtama());
        menuFilm.addActionListenerRumahProduksi(new AksiButton_MenuFilm_RumahProduksi());
        menuFilm.addActionListenerJenisFilm(new AksiButton_MenuFilm_JenisFilm());
        menuFilm.addActionListenerFilm(new AksiButton_MenuFilm_Film());

        // Anggota
        panelAnggota.addActionListenerSaring(new AksiButton_Anggota_Saring());
        panelAnggota.addActionListenerTambah(new AksiButton_Anggota_Tambah());
        panelAnggota.addActionListenerSegarkan(new AksiButton_Anggota_Segarkan());

        // Anggota Saring
        panelSaringAnggota.addActionListenerBatal(new AksiButton_AnggotaSaring_Batal());
        panelSaringAnggota.addActionListenerSaring(new AksiButton_AnggotaSaring_Saring());

        // Direktor
        panelDirektor.addActionListenerTambah(new AksiButton_Direktor_Tambah());
        panelDirektor.addActionListenerSegarkan(new AksiButton_Direktor_Segarkan());
        panelDirektor.addActionListenerSaring(new AksiButton_Direktor_Saring());

        // Pemeran Utama
        panelPemeranUtama.addActionListenerSegarkan(new AksiButton_PemeranUtama_Segarkan());
        panelPemeranUtama.addActionListenerTambah(new AksiButton_PemeranUtama_Tambah());
        panelPemeranUtama.addActionListenerSaring(new AksiButton_PemeranUtama_Saring());

        // Rumah Produksi
        panelRumahProduksi.addActionListenerSaring(new AksiButton_RumahProduksi_Saring());
        panelRumahProduksi.addActionListenerSegarkan(new AksiButton_RumahProduksi_Segarkan());
        panelRumahProduksi.addActionListenerTambah(new AksiButton_RumahProduksi_Tambah());

        // Jenis Film
        panelJenisFilm.addActionListenerSaring(new AksiButton_JenisFilm_Saring());
        panelJenisFilm.addActionListenerSegarkan(new AksiButton_JenisFilm_Segarkan());
        panelJenisFilm.addActionListenerTambah(new AksiButton_JenisFilm_Tambah());

        // Film
        panelFilm.addActionListenerTambah(new AksiButton_Film_Tambah());
        panelFilm.addActionListenerSegarkan(new AksiButton_Film_Segarkan());
        panelFilm.addActionListenerSaring(new AksiButton_Film_Saring());

        // Film tambah
        panelTambahFilm.addActionListenerBatal(new AksiButton_FilmTambah_Batal());
        panelTambahFilm.addActionListenerTambah(new AksiButton_FilmTambah_Tambah());

        // Film Saring
        panelSaringFilm.addActionListenerBatal(new AksiButton_FilmSaring_Batal());
        panelSaringFilm.addActionListenerSaring(new AksiButton_FilmSaring_Saring());

        // Pribadi
        panelOperator.addActionListenerBatal(new AksiButton_Pribadi_Batal());
        panelOperator.addActionListenerSimpan(new AksiButton_Pribadi_Simpan());

        // Peminjaman
        panelPeminjaman.addActionListenerBatal(new AksiButton_Peminjaman_Batal());
        panelPeminjaman.addActionListenerLihat(new AksiButton_Peminjaman_Lihat());
        panelPeminjaman.addActionListenerTambah(new AksiButton_Peminjaman_Tambah());
        panelPeminjaman.addActionListenerHapus(new AksiButton_Peminjaman_Hapus());
        panelPeminjaman.addActionListenerPinjam(new AksiButton_Peminjaman_Pinjam());

        // Pengembalian
        panelPengembalian.addActionListenerCekPeminjaman(new AksiButton_Pengembalian_Cek());
        panelPengembalian.addActionListenerKembalikan(new AksiButton_Pengembalian_Kembalikan());
        panelPengembalian.addActionListenerKembalikanSemua(new AksiButton_Pengembalian_KembalikanSemua());

        // Pesan
        panelPesan.addActionListenerRincian(new AksiButton_Pesan_Rincian());
        panelPesan.addActionListenerSegarkan(new AksiButton_Pesan_Segarkan());
        panelPesan.addActionListenerHapus(new AksiButton_Pesan_Hapus());
        panelPesan.addActionListenerKirim(new AksiButton_Pesan_Kirim());
        panelPesan.addActionListenerBalas(new AksiButton_Pesan_Balas());

        // Kirim Pesan
        kirimPesan.addActionListenerBatal(new AksiButton_KirimPesan_Batal());
        kirimPesan.addActionListenerKirim(new AksiButton_KirimPesan_Kirim());
    }

    void showPanel(Component panel, String card) {
        try {
            if (panel.isVisible()) {
                return;
            }
            glasspane.startTransition(panelGradient);
            ((CardLayout) panelCard.getLayout()).show(panelCard, card);
        } catch (AWTException ex) {
            Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
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
        detailAnggota = new usu.rental.film.operator.DetailAnggota();
        detailPeminjaman = new usu.rental.film.operator.DetailPeminjaman();
        detailOperator = new usu.rental.film.operator.DetailOperator();
        panelGradient = new usu.rental.film.widget.Panel();
        labelEmovie = new usu.rental.film.widget.Label();
        panelCard = new javax.swing.JPanel();
        menuUtama = new usu.rental.film.operator.MenuUtama();
        panelSaringAnggota = new usu.rental.film.admin.PanelSaringAnggota();
        panelTambahFilm = new usu.rental.film.admin.PanelTambahFilm();
        panelSaringFilm = new usu.rental.film.admin.PanelSaringFilm();
        panelOperator = new usu.rental.film.operator.PanelOperator();
        menuFilm = new usu.rental.film.operator.MenuFilm();
        panelAnggota = new usu.rental.film.operator.PanelAnggota();
        panelDirektor = new usu.rental.film.operator.PanelDirektor();
        panelPemeranUtama = new usu.rental.film.operator.PanelPemeranUtama();
        panelRumahProduksi = new usu.rental.film.operator.PanelRumahProduksi();
        panelJenisFilm = new usu.rental.film.operator.PanelJenisFilm();
        panelFilm = new usu.rental.film.operator.PanelFilm();
        panelPeminjaman = new usu.rental.film.operator.PanelPeminjaman();
        panelPengembalian = new usu.rental.film.operator.PanelPengembalian();
        panelPesan = new usu.rental.film.operator.PanelPesan();
        kirimPesan = new usu.rental.film.operator.KirimPesan();
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

        detailAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailAnggotaActionPerformed(evt);
            }
        });

        detailOperator.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detailOperatorActionPerformed(evt);
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
        panelCard.add(panelSaringAnggota, "ANGGOTA_SARING");
        panelCard.add(panelTambahFilm, "TAMBAH_FILM");
        panelCard.add(panelSaringFilm, "SARING_FILM");
        panelCard.add(panelOperator, "PRIBADI");
        panelCard.add(menuFilm, "MENU_FILM");
        panelCard.add(panelAnggota, "ANGGOTA");
        panelCard.add(panelDirektor, "DIREKTOR");
        panelCard.add(panelPemeranUtama, "PEMERAN_UTAMA");
        panelCard.add(panelRumahProduksi, "RUMAH_PRODUKSI");
        panelCard.add(panelJenisFilm, "JENIS_FILM");
        panelCard.add(panelFilm, "FILM");
        panelCard.add(panelPeminjaman, "PEMINJAMAN");
        panelCard.add(panelPengembalian, "PENGEMBALIAN");
        panelCard.add(panelPesan, "PESAN");
        panelCard.add(kirimPesan, "KIRIM_PESAN");

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

private void detailAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailAnggotaActionPerformed
    glasspane.hideComponent();
}//GEN-LAST:event_detailAnggotaActionPerformed

private void detailOperatorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detailOperatorActionPerformed
    glasspane.hideComponent();
}//GEN-LAST:event_detailOperatorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    usu.rental.film.widget.About about;
    usu.rental.film.widget.Button buttonMenu;
    usu.rental.film.widget.Button buttonTentang;
    usu.rental.film.operator.DetailAnggota detailAnggota;
    private usu.rental.film.operator.DetailOperator detailOperator;
    usu.rental.film.operator.DetailPeminjaman detailPeminjaman;
    usu.widget.GlassPane glasspane;
    usu.rental.film.operator.KirimPesan kirimPesan;
    usu.rental.film.widget.Label labelEmovie;
    usu.rental.film.widget.Label labelStatusBar;
    usu.rental.film.operator.MenuFilm menuFilm;
    usu.rental.film.operator.MenuUtama menuUtama;
    usu.rental.film.operator.PanelAnggota panelAnggota;
    javax.swing.JPanel panelCard;
    usu.rental.film.operator.PanelDirektor panelDirektor;
    usu.rental.film.operator.PanelFilm panelFilm;
    usu.rental.film.widget.Panel panelGradient;
    usu.rental.film.operator.PanelJenisFilm panelJenisFilm;
    usu.rental.film.operator.PanelOperator panelOperator;
    usu.rental.film.operator.PanelPemeranUtama panelPemeranUtama;
    usu.rental.film.operator.PanelPeminjaman panelPeminjaman;
    usu.rental.film.operator.PanelPengembalian panelPengembalian;
    private usu.rental.film.operator.PanelPesan panelPesan;
    usu.rental.film.operator.PanelRumahProduksi panelRumahProduksi;
    usu.rental.film.admin.PanelSaringAnggota panelSaringAnggota;
    usu.rental.film.admin.PanelSaringFilm panelSaringFilm;
    usu.rental.film.admin.PanelTambahFilm panelTambahFilm;
    usu.rental.film.widget.ProgressBar progressBar;
    // End of variables declaration//GEN-END:variables

    class AksiButton_Anggota_Saring implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            showPanel(OperatorForm.this.panelSaringAnggota, "ANGGOTA_SARING");
            setTitleBody("Saring");
        }
    }

    class AksiButton_Anggota_Segarkan implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                OperatorForm.this.panelAnggota.reset();
                new WorkerAnggota(OperatorForm.this.panelAnggota.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        OperatorForm.this.panelAnggota.setEnabled(false);
                        runLoading("Loading Anggota");
                    }

                    public void runLast() {
                        OperatorForm.this.panelAnggota.setEnabled(true);
                        doneLoading();
                    }
                }, OperatorForm.this.connection).execute();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_Anggota_Tambah implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                MySQL.insertAnggota(OperatorForm.this.connection, OperatorForm.this.panelAnggota.getAnggota());
                MySQL.insertAktivitasOperator(connection, new DefaultAktivitasOperator(operator.getIdOperator(),
                        "menambah anggota baru dengan id = " + OperatorForm.this.panelAnggota.getAnggota().getIdAnggota()));
                OperatorForm.this.panelAnggota.getTableModel().add(
                        OperatorForm.this.panelAnggota.getAnggota());
                OperatorForm.this.panelAnggota.reset();
            } catch (final DataNotCompleteException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            } catch (final SQLException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            }
        }
    }

    class AksiButton_AnggotaSaring_Batal implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            showPanel(OperatorForm.this.panelAnggota, "ANGGOTA");
            setTitleBody(null);
            setTitleBody("Anggota");
        }
    }

    class AksiButton_AnggotaSaring_Saring implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                OperatorForm.this.panelAnggota.reset();
                showPanel(OperatorForm.this.panelAnggota, "ANGGOTA");
                setTitleBody(null);
                setTitleBody("Anggota");
                new WorkerAnggota(OperatorForm.this.connection, OperatorForm.this.panelAnggota.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        OperatorForm.this.panelAnggota.setEnabled(false);
                        runLoading("Loading Anggota");
                    }

                    public void runLast() {
                        OperatorForm.this.panelAnggota.setEnabled(true);
                        doneLoading();
                    }
                }, OperatorForm.this.panelSaringAnggota.getId(), OperatorForm.this.panelSaringAnggota.getNama(), OperatorForm.this.panelSaringAnggota.getLahirDari(),
                        OperatorForm.this.panelSaringAnggota.getLahirSampai(),
                        OperatorForm.this.panelSaringAnggota.getKontak(),
                        OperatorForm.this.panelSaringAnggota.getAlamat(),
                        OperatorForm.this.panelSaringAnggota.getTotalMeminjan()).execute();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_Direktor_Saring implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                new WorkerDirektor(OperatorForm.this.connection, OperatorForm.this.panelDirektor.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        runLoading("Loading Direktor");
                        OperatorForm.this.panelDirektor.setEnabled(false);
                    }

                    public void runLast() {
                        OperatorForm.this.panelDirektor.setEnabled(true);
                        doneLoading();
                        OperatorForm.this.panelDirektor.reset();
                    }
                }, OperatorForm.this.panelDirektor.getId(), OperatorForm.this.panelDirektor.getNama()).execute();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_Direktor_Segarkan implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                OperatorForm.this.panelDirektor.reset();
                new WorkerDirektor(OperatorForm.this.connection, OperatorForm.this.panelDirektor.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        runLoading("Loading Direktor");
                        OperatorForm.this.panelDirektor.setEnabled(false);
                    }

                    public void runLast() {
                        doneLoading();
                        OperatorForm.this.panelDirektor.setEnabled(true);
                    }
                }).execute();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_Direktor_Tambah implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                MySQL.insertDirektor(OperatorForm.this.connection, OperatorForm.this.panelDirektor.getDirektor());
                MySQL.insertAktivitasOperator(connection, new DefaultAktivitasOperator(operator.getIdOperator(),
                        "menambah direktor baru dengan id = " + panelDirektor.getDirektor().getIdDirektor()));
                OperatorForm.this.panelDirektor.getTableModel().add(
                        OperatorForm.this.panelDirektor.getDirektor());
                OperatorForm.this.panelDirektor.reset();
            } catch (final SQLException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            } catch (final DataNotCompleteException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            }
        }
    }

    class AksiButton_Film_Saring implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            showPanel(OperatorForm.this.panelSaringFilm, "SARING_FILM");
            setTitleBody("Saring");
        }
    }

    class AksiButton_Film_Segarkan implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                OperatorForm.this.panelFilm.reset();
                new WorkerFilm(OperatorForm.this.connection, OperatorForm.this.panelFilm.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        runLoading("Loading Film");
                        OperatorForm.this.panelFilm.setEnabled(false);
                    }

                    public void runLast() {
                        OperatorForm.this.panelFilm.setEnabled(true);
                        doneLoading();
                    }
                }).execute();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_Film_Tambah implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                OperatorForm.this.panelTambahFilm.reset();
                OperatorForm.this.panelTambahFilm.setConnection(OperatorForm.this.connection);
                showPanel(OperatorForm.this.panelTambahFilm, "TAMBAH_FILM");
                setTitleBody("Tambah");
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_FilmSaring_Batal implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            showPanel(OperatorForm.this.panelFilm, "FILM");
            setTitleBody(null);
            setTitleBody("Film : Film");
        }
    }

    class AksiButton_FilmSaring_Saring implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                OperatorForm.this.panelFilm.reset();
                showPanel(OperatorForm.this.panelFilm, "FILM");
                setTitleBody(null);
                setTitleBody("Film : Film");
                new WorkerFilm(OperatorForm.this.connection, OperatorForm.this.panelFilm.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        OperatorForm.this.panelFilm.setEnabled(false);
                        runLoading("Loading Film");
                    }

                    public void runLast() {
                        doneLoading();
                        OperatorForm.this.panelFilm.setEnabled(true);
                    }
                }, OperatorForm.this.panelSaringFilm.getJudulFilm(), OperatorForm.this.panelSaringFilm.getPemeranUtama(), OperatorForm.this.panelSaringFilm.getDirektor(),
                        OperatorForm.this.panelSaringFilm.getRumahProduksi(),
                        OperatorForm.this.panelSaringFilm.getJenisFilm(),
                        OperatorForm.this.panelSaringFilm.getStatus()).execute();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_FilmTambah_Batal implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            showPanel(OperatorForm.this.panelFilm, "FILM");
            setTitleBody(null);
            setTitleBody("Film : Film");
        }
    }

    class AksiButton_FilmTambah_Tambah implements ActionListener {

        private Film tempFilm;

        public void actionPerformed(final ActionEvent e) {
            try {
                MySQL.insertFilm(OperatorForm.this.connection, OperatorForm.this.panelTambahFilm.getFilm());
                MySQL.insertAktivitasOperator(connection, new DefaultAktivitasOperator(operator.getIdOperator(),
                        "menambah film baru dengan id = " + panelTambahFilm.getFilm().getIdFilm()));
                this.tempFilm = MySQL.getFilm(OperatorForm.this.connection,
                        OperatorForm.this.panelTambahFilm.getFilm().getIdFilm());
                OperatorForm.this.panelFilm.getTableModel().add(this.tempFilm);
                showPanel(OperatorForm.this.panelFilm, "FILM");
                setTitleBody(null);
                setTitleBody("Film : Film");
            } catch (final DataNotFoundException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            } catch (final SQLException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            } catch (final DataNotCompleteException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            }
        }
    }

    class AksiButton_JenisFilm_Saring implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                new WorkerJenisFilm(OperatorForm.this.connection, OperatorForm.this.panelJenisFilm.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        runLoading("Loading Jenis Film");
                        OperatorForm.this.panelJenisFilm.setEnabled(false);
                    }

                    public void runLast() {
                        OperatorForm.this.panelJenisFilm.setEnabled(true);
                        doneLoading();
                        OperatorForm.this.panelJenisFilm.reset();
                    }
                }, OperatorForm.this.panelJenisFilm.getId(), OperatorForm.this.panelJenisFilm.getNama()).execute();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_JenisFilm_Segarkan implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                OperatorForm.this.panelJenisFilm.reset();
                new WorkerJenisFilm(OperatorForm.this.connection, OperatorForm.this.panelJenisFilm.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        runLoading("Loading Jenis Film");
                        OperatorForm.this.panelJenisFilm.setEnabled(false);
                    }

                    public void runLast() {
                        OperatorForm.this.panelJenisFilm.setEnabled(true);
                        doneLoading();
                    }
                }).execute();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_JenisFilm_Tambah implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                MySQL.insertJenisFilm(OperatorForm.this.connection, OperatorForm.this.panelJenisFilm.getJenisFilm());
                MySQL.insertAktivitasOperator(connection, new DefaultAktivitasOperator(operator.getIdOperator(),
                        "menambah jenis film baru dengan id = " + panelJenisFilm.getJenisFilm().getIdJenisFilm()));
                OperatorForm.this.panelJenisFilm.getTableModel().add(
                        OperatorForm.this.panelJenisFilm.getJenisFilm());
                OperatorForm.this.panelJenisFilm.reset();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            } catch (final DataNotCompleteException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_KirimPesan_Batal implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            showPanel(OperatorForm.this.panelPesan, "PESAN");
            setTitleBody(null);
            setTitleBody("Pesan");
        }
    }

    class AksiButton_KirimPesan_Kirim implements ActionListener {

        private String isiPesan;
        private String judul;
        private Operator temp;
        private DefaultPesan tempPesan;

        public void actionPerformed(final ActionEvent e) {
            try {
                if (OperatorForm.this.kirimPesan.getTable().getSelectedRowCount() == 0) {
                    WidgetUtilities.showErrorMessage(OperatorForm.this,
                            "operator tujuan belum ditentukan");
                    return;
                }
                this.judul = OperatorForm.this.kirimPesan.getJudul();
                this.isiPesan = OperatorForm.this.kirimPesan.getIsi();
                final int[] row = OperatorForm.this.kirimPesan.getTable().getSelectedRows();
                for (final int i : row) {
                    try {
                        final int index = OperatorForm.this.kirimPesan.getTable().convertRowIndexToModel(
                                i);
                        this.temp = OperatorForm.this.kirimPesan.getTableModel().get(index);
                        this.tempPesan = new DefaultPesan();
                        this.tempPesan.setIsiPesan(this.isiPesan);
                        this.tempPesan.setJudulPesan(this.judul);
                        this.tempPesan.setOperatorPengirim(OperatorForm.this.operator.getIdOperator());
                        this.tempPesan.setOperatorTujuan(this.temp.getIdOperator());
                        MySQL.insertPesan(OperatorForm.this.connection, this.tempPesan);
                        MySQL.insertAktivitasOperator(connection, new DefaultAktivitasOperator(operator.getIdOperator(),
                                "mengirim pesan ke operator " + this.tempPesan.getOperatorTujuan()));
                    } catch (final SQLException ex) {
                        WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
                    }
                }
                showPanel(OperatorForm.this.panelPesan, "PESAN");
                setTitleBody(null);
                setTitleBody("Pesan");
            } catch (final DataNotCompleteException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            }
        }
    }

    class AksiButton_MenuFilm_Direktor implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                OperatorForm.this.panelDirektor.reset();
                showPanel(OperatorForm.this.panelDirektor, "DIREKTOR");
                setTitleBody("Direktor");
                new WorkerDirektor(OperatorForm.this.connection, OperatorForm.this.panelDirektor.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        runLoading("Loading Direktor");
                        OperatorForm.this.panelDirektor.setEnabled(false);
                    }

                    public void runLast() {
                        doneLoading();
                        OperatorForm.this.panelDirektor.setEnabled(true);
                    }
                }).execute();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_MenuFilm_Film implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                OperatorForm.this.panelFilm.reset();
                showPanel(OperatorForm.this.panelFilm, "FILM");
                setTitleBody("Film");
                new WorkerFilm(OperatorForm.this.connection, OperatorForm.this.panelFilm.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        OperatorForm.this.panelFilm.setEnabled(false);
                        runLoading("Loading Film");
                    }

                    public void runLast() {
                        OperatorForm.this.panelFilm.setEnabled(true);
                        doneLoading();
                    }
                }).execute();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_MenuFilm_JenisFilm implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                OperatorForm.this.panelJenisFilm.reset();
                showPanel(OperatorForm.this.panelJenisFilm, "JENIS_FILM");
                setTitleBody("Jenis Film");
                new WorkerJenisFilm(OperatorForm.this.connection, OperatorForm.this.panelJenisFilm.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        runLoading("Loading Jenis Film");
                        OperatorForm.this.panelJenisFilm.setEnabled(false);
                    }

                    public void runLast() {
                        OperatorForm.this.panelJenisFilm.setEnabled(true);
                        doneLoading();
                    }
                }).execute();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_MenuFilm_PemeranUtama implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                OperatorForm.this.panelPemeranUtama.reset();
                showPanel(OperatorForm.this.panelPemeranUtama, "PEMERAN_UTAMA");
                setTitleBody("Pemeran Utama");
                new WorkerPemeranUtama(OperatorForm.this.connection,
                        OperatorForm.this.panelPemeranUtama.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        runLoading("Loading Pemeran Utama");
                        OperatorForm.this.panelPemeranUtama.setEnabled(false);
                    }

                    public void runLast() {
                        doneLoading();
                        OperatorForm.this.panelPemeranUtama.setEnabled(true);
                    }
                }).execute();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_MenuFilm_RumahProduksi implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                OperatorForm.this.panelRumahProduksi.reset();
                showPanel(OperatorForm.this.panelRumahProduksi, "RUMAH_PRODUKSI");
                setTitleBody("Rumah Produksi");
                new WorkerRumahProduksi(OperatorForm.this.connection,
                        OperatorForm.this.panelRumahProduksi.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        OperatorForm.this.panelRumahProduksi.setEnabled(false);
                        runLoading("Loading Rumah Produksi");
                    }

                    public void runLast() {
                        OperatorForm.this.panelRumahProduksi.setEnabled(true);
                        doneLoading();
                    }
                }).execute();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_MenuUtama_Anggota implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                OperatorForm.this.panelAnggota.reset();
                showPanel(OperatorForm.this.panelAnggota, "ANGGOTA");
                setTitleBody("Anggota");
                new WorkerAnggota(OperatorForm.this.panelAnggota.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        OperatorForm.this.panelAnggota.setEnabled(false);
                        runLoading("Loading Anggota");
                    }

                    public void runLast() {
                        OperatorForm.this.panelAnggota.setEnabled(true);
                        doneLoading();
                    }
                }, OperatorForm.this.connection).execute();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_MenuUtama_Film implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            showPanel(OperatorForm.this.menuFilm, "MENU_FILM");
            setTitleBody("Film");
        }
    }

    class AksiButton_MenuUtama_Peminjaman implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            OperatorForm.this.panelPeminjaman.reset();
            OperatorForm.this.panelPeminjaman.setSetting(OperatorForm.this.setting);
            showPanel(OperatorForm.this.panelPeminjaman, "PEMINJAMAN");
            setTitleBody("Peminjaman");
        }
    }

    class AksiButton_MenuUtama_Pengembalian implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            OperatorForm.this.panelPengembalian.reset();
            showPanel(OperatorForm.this.panelPengembalian, "PENGEMBALIAN");
            setTitleBody("Pengembalian");
        }
    }

    class AksiButton_MenuUtama_Pesan implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                showPanel(OperatorForm.this.panelPesan, "PESAN");
                setTitleBody("Pesan");
                OperatorForm.this.panelPesan.reset();
                new WorkerPesan(OperatorForm.this.connection, OperatorForm.this.panelPesan.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        runLoading("Loading Pesan");
                        OperatorForm.this.panelPesan.setEnabled(false);
                    }

                    public void runLast() {
                        doneLoading();
                        OperatorForm.this.panelPesan.setEnabled(true);
                    }
                }, OperatorForm.this.operator.getIdOperator()).execute();
            } catch (final SQLException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            }
        }
    }

    class AksiButton_MenuUtama_Pribadi implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            OperatorForm.this.panelOperator.setOperator(OperatorForm.this.operator);
            showPanel(OperatorForm.this.panelOperator, "PRIBADI");
            setTitleBody("Pribadi");
        }
    }

    class AksiButton_PemeranUtama_Saring implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                new WorkerPemeranUtama(OperatorForm.this.connection,
                        OperatorForm.this.panelPemeranUtama.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        runLoading("Loading Pemeran Utama");
                        OperatorForm.this.panelPemeranUtama.setEnabled(false);
                    }

                    public void runLast() {
                        doneLoading();
                        OperatorForm.this.panelPemeranUtama.setEnabled(true);
                        OperatorForm.this.panelPemeranUtama.reset();
                    }
                }, OperatorForm.this.panelPemeranUtama.getId(),
                        OperatorForm.this.panelPemeranUtama.getNama()).execute();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_PemeranUtama_Segarkan implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                OperatorForm.this.panelPemeranUtama.reset();
                new WorkerPemeranUtama(OperatorForm.this.connection,
                        OperatorForm.this.panelPemeranUtama.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        runLoading("Loading Pemeran Utama");
                        OperatorForm.this.panelPemeranUtama.setEnabled(false);
                    }

                    public void runLast() {
                        doneLoading();
                        OperatorForm.this.panelPemeranUtama.setEnabled(true);
                    }
                }).execute();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_PemeranUtama_Tambah implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                MySQL.insertPemeranUtama(OperatorForm.this.connection,
                        OperatorForm.this.panelPemeranUtama.getPemeranUtama());
                MySQL.insertAktivitasOperator(connection, new DefaultAktivitasOperator(operator.getIdOperator(),
                        "menambah pemeran utama baru dengan id " + panelPemeranUtama.getPemeranUtama().getIdPemeranUtama()));
                OperatorForm.this.panelPemeranUtama.getTableModel().add(
                        OperatorForm.this.panelPemeranUtama.getPemeranUtama());
                OperatorForm.this.panelPemeranUtama.reset();
            } catch (final SQLException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            } catch (final DataNotCompleteException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            }
        }
    }

    class AksiButton_Peminjaman_Batal implements ActionListener {

        private Film filmTemp;

        public void actionPerformed(final ActionEvent e) {
            while (OperatorForm.this.panelPeminjaman.getTableModel().getRowCount() > 0) {
                try {
                    this.filmTemp = OperatorForm.this.panelPeminjaman.getTableModel().remove(0);
                    MySQL.updateFilmSedangDipinjam(OperatorForm.this.connection, this.filmTemp.getIdFilm(), false);
                } catch (final SQLException ex) {
                    Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    showPanel(OperatorForm.this.menuUtama, "MENU_UTAMA");
                    setTitleBody(null);
                }
            }
        }
    }

    class AksiButton_Peminjaman_Hapus implements ActionListener {

        private Film filmTemp;

        public void actionPerformed(final ActionEvent e) {
            while (OperatorForm.this.panelPeminjaman.getTable().getSelectedRowCount() > 0) {
                try {
                    final int index = OperatorForm.this.panelPeminjaman.getTable().convertRowIndexToModel(
                            OperatorForm.this.panelPeminjaman.getTable().getSelectionModel().getMaxSelectionIndex());
                    this.filmTemp = OperatorForm.this.panelPeminjaman.getTableModel().remove(index);
                    OperatorForm.this.panelPeminjaman.removeFilm();
                    MySQL.updateFilmSedangDipinjam(OperatorForm.this.connection, this.filmTemp.getIdFilm(), false);
                } catch (final SQLException ex) {
                    WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
                }
            }
        }
    }

    class AksiButton_Peminjaman_Lihat implements ActionListener {

        private Anggota anggotaTemp;

        public void actionPerformed(final ActionEvent e) {
            try {
                this.anggotaTemp = MySQL.getAnggota(OperatorForm.this.connection,
                        OperatorForm.this.panelPeminjaman.getAnggota());
                OperatorForm.this.detailAnggota.setAnggota(this.anggotaTemp);
                OperatorForm.this.glasspane.showComponent(OperatorForm.this.detailAnggota);
            } catch (final DataNotFoundException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            } catch (final SQLException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            } catch (final DataNotCompleteException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            }
        }
    }

    class AksiButton_Peminjaman_Pinjam implements ActionListener {

        private Anggota anggota;
        private Film filmTemp;
        private int intervalDay;
        private DefaultPeminjaman peminjaman;

        public void actionPerformed(final ActionEvent e) {
            if (OperatorForm.this.panelPeminjaman.getTableModel().getRowCount() < 1) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, "belum ada film yang dipinjam");
                return;
            }
            try {
                this.anggota = MySQL.getAnggota(OperatorForm.this.connection,
                        OperatorForm.this.panelPeminjaman.getAnggota());
            } catch (final DataNotCompleteException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
                return;
            } catch (final DataNotFoundException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
                return;
            } catch (final SQLException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            }
            this.intervalDay = OperatorForm.this.panelPeminjaman.getTableModel().getRowCount() + 1;
            for (int i = 0; i < OperatorForm.this.panelPeminjaman.getTableModel().getRowCount(); i++) {
                try {
                    this.filmTemp = OperatorForm.this.panelPeminjaman.getTableModel().get(i);
                    this.peminjaman = new DefaultPeminjaman();
                    this.peminjaman.setAnggota(this.anggota.getIdAnggota());
                    this.peminjaman.setFilm(this.filmTemp.getIdFilm());
                    this.peminjaman.setOperator(OperatorForm.this.operator.getIdOperator());
                    MySQL.insertPeminjaman(OperatorForm.this.connection, this.peminjaman,
                            this.intervalDay);
                    MySQL.updateFilmTotalDipinjam(OperatorForm.this.connection, this.filmTemp.getIdFilm());
                    MySQL.updateAnggotaTotalPeminjamn(OperatorForm.this.connection, this.anggota.getIdAnggota());
                    MySQL.insertAktivitasOperator(connection, new DefaultAktivitasOperator(operator.getIdOperator(),
                            "menambah peminjaman film " + peminjaman.getFilm() + " oleh anggota " + peminjaman.getAnggota()));
                } catch (final SQLException ex) {
                    WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
                }
            }
            try {
                OperatorForm.this.detailPeminjaman.reset();
                final List<Peminjaman> list = MySQL.getListPeminjaman(OperatorForm.this.connection,
                        this.anggota.getIdAnggota(), false);
                OperatorForm.this.detailPeminjaman.getTableModel().add(list);
                OperatorForm.this.detailPeminjaman.setAnggota(this.anggota);
                showPanel(OperatorForm.this.menuUtama, "MENU_UTAMA");
                setTitleBody(null);
                OperatorForm.this.detailPeminjaman.setVisible(true);
            } catch (final SQLException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            }
        }
    }

    class AksiButton_Peminjaman_Tambah implements ActionListener {

        private Film filmTemp;
        private Integer totalMeminjam;

        public void actionPerformed(final ActionEvent e) {
            try {
                MySQL.getAnggota(OperatorForm.this.connection, OperatorForm.this.panelPeminjaman.getAnggota());
                this.filmTemp = MySQL.getFilm(OperatorForm.this.connection,
                        OperatorForm.this.panelPeminjaman.getFilm());
                if (this.filmTemp.isDipinjam()) {
                    WidgetUtilities.showErrorMessage(OperatorForm.this, "film sedang dipinjam");
                    return;
                }
                this.totalMeminjam = MySQL.getTotalMeminjam(OperatorForm.this.connection,
                        OperatorForm.this.panelPeminjaman.getAnggota());
                if (this.totalMeminjam + OperatorForm.this.panelPeminjaman.getTableModel().getRowCount() + 1 > OperatorForm.this.setting.getMaksimalPeminjaman()) {
                    WidgetUtilities.showErrorMessage(OperatorForm.this,
                            "anggota tak bisa meminjam lebih dari " + OperatorForm.this.setting.getMaksimalPeminjaman() + " film");
                    return;
                }
                MySQL.updateFilmSedangDipinjam(OperatorForm.this.connection, this.filmTemp.getIdFilm(),
                        true);
                OperatorForm.this.panelPeminjaman.getTableModel().add(this.filmTemp);
                OperatorForm.this.panelPeminjaman.addFilm();
                OperatorForm.this.panelPeminjaman.getTextFilm().setText("");
            } catch (final DataNotFoundException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            } catch (final SQLException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            } catch (final DataNotCompleteException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            }
        }
    }

    class AksiButton_Pengembalian_Cek implements ActionListener {

        private Anggota anggota;

        public void actionPerformed(final ActionEvent e) {
            try {
                this.anggota = MySQL.getAnggota(OperatorForm.this.connection,
                        OperatorForm.this.panelPengembalian.getAnggota());
                final List<Peminjaman> list = MySQL.getListPeminjaman(OperatorForm.this.connection,
                        this.anggota.getIdAnggota(), false);
                OperatorForm.this.panelPengembalian.getTableModel().removeAllElements();
                OperatorForm.this.panelPengembalian.getTableModel().add(list);
            } catch (final DataNotCompleteException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            } catch (final DataNotFoundException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            } catch (final SQLException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            }
        }
    }

    class AksiButton_Pengembalian_Kembalikan implements ActionListener {

        private Peminjaman peminjaman;
        private DefaultPengembalian pengembalian;

        public void actionPerformed(final ActionEvent e) {
            while (OperatorForm.this.panelPengembalian.getTable().getSelectedRowCount() > 0) {
                try {
                    final int index = OperatorForm.this.panelPengembalian.getTable().convertRowIndexToModel(
                            OperatorForm.this.panelPengembalian.getTable().getSelectionModel().getMaxSelectionIndex());
                    this.peminjaman = OperatorForm.this.panelPengembalian.getTableModel().remove(index);
                    this.pengembalian = new DefaultPengembalian();
                    this.pengembalian.setIdTransaksi(this.peminjaman.getIdTransaksi());
                    this.pengembalian.setOperator(OperatorForm.this.operator.getIdOperator());
                    OperatorForm.this.connection.setAutoCommit(false);
                    MySQL.insertPengembalian(OperatorForm.this.connection, this.pengembalian,
                            OperatorForm.this.setting.getDendaKeterlambatan());
                    MySQL.updatePeminjaman(OperatorForm.this.connection, this.peminjaman.getIdTransaksi());
                    MySQL.updateFilmSedangDipinjam(OperatorForm.this.connection, this.peminjaman.getFilm(), false);
                    MySQL.insertAktivitasOperator(connection, new DefaultAktivitasOperator(operator.getIdOperator(),
                            "menambah pengembalian dengan id transaksi " + pengembalian.getIdTransaksi()));
                    OperatorForm.this.connection.commit();
                    OperatorForm.this.connection.setAutoCommit(true);
                } catch (final SQLException ex) {
                    WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
                }
            }
        }
    }

    class AksiButton_Pengembalian_KembalikanSemua implements ActionListener {

        private Peminjaman peminjaman;
        private DefaultPengembalian pengembalian;

        public void actionPerformed(final ActionEvent e) {
            while (OperatorForm.this.panelPengembalian.getTableModel().getRowCount() > 0) {
                try {
                    this.peminjaman = OperatorForm.this.panelPengembalian.getTableModel().remove(0);
                    this.pengembalian = new DefaultPengembalian();
                    this.pengembalian.setIdTransaksi(this.peminjaman.getIdTransaksi());
                    this.pengembalian.setOperator(OperatorForm.this.operator.getIdOperator());
                    OperatorForm.this.connection.setAutoCommit(false);
                    MySQL.insertPengembalian(OperatorForm.this.connection, this.pengembalian,
                            OperatorForm.this.setting.getDendaKeterlambatan());
                    MySQL.updatePeminjaman(OperatorForm.this.connection, this.peminjaman.getIdTransaksi());
                    MySQL.updateFilmSedangDipinjam(OperatorForm.this.connection, this.peminjaman.getFilm(), false);
                    MySQL.insertAktivitasOperator(connection, new DefaultAktivitasOperator(operator.getIdOperator(),
                            "menambah pengembalian dengan id transaksi " + pengembalian.getIdTransaksi()));
                    OperatorForm.this.connection.commit();
                    OperatorForm.this.connection.setAutoCommit(true);
                } catch (final SQLException ex) {
                    WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
                }
            }
        }
    }

    class AksiButton_Pesan_Balas implements ActionListener {

        private Pesan temp;

        public void actionPerformed(final ActionEvent e) {
            if (OperatorForm.this.panelPesan.getTable().getSelectedRowCount() == 0) {
                WidgetUtilities.showErrorMessage(OperatorForm.this,
                        "pesan yang akan dibalas belum dipilih");
                return;
            }
            try {
                final int index = OperatorForm.this.panelPesan.getTable().convertRowIndexToModel(
                        OperatorForm.this.panelPesan.getTable().getSelectedRow());
                this.temp = OperatorForm.this.panelPesan.getTableModel().get(index);
                OperatorForm.this.kirimPesan.setConnection(OperatorForm.this.connection);
                OperatorForm.this.kirimPesan.getTextJudul().setText("Re : " + this.temp.getJudulPesan());
                OperatorForm.this.kirimPesan.getTextPesan().setText(
                        "Anda menulis : \n" + this.temp.getIsiPesan() + "\n ---------------------------------");
                OperatorForm.this.kirimPesan.getTextCari().setText(this.temp.getOperatorPengirim());
                showPanel(OperatorForm.this.kirimPesan, "KIRIM_PESAN");
                setTitleBody("Kirim");
            } catch (final SQLException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            }
        }
    }

    class AksiButton_Pesan_Hapus implements ActionListener {

        private Pesan temp;

        public void actionPerformed(final ActionEvent e) {
            if (OperatorForm.this.panelPesan.getTable().getSelectedRowCount() == 0) {
                WidgetUtilities.showErrorMessage(OperatorForm.this,
                        "pesan yang akan dihapus belum dipilih");
                return;
            }
            if (WidgetUtilities.showConfirmMessage(OperatorForm.this, "anda yakin") != JOptionPane.OK_OPTION) {
                return;
            }
            while (OperatorForm.this.panelPesan.getTable().getSelectedRowCount() > 0) {
                try {
                    final int index = OperatorForm.this.panelPesan.getTable().convertRowIndexToModel(
                            OperatorForm.this.panelPesan.getTable().getSelectionModel().getMaxSelectionIndex());
                    this.temp = OperatorForm.this.panelPesan.getTableModel().remove(index);
                    MySQL.deletePesan(OperatorForm.this.connection, this.temp.getIdPesan());
                    MySQL.insertAktivitasOperator(connection, new DefaultAktivitasOperator(operator.getIdOperator(),
                            "menghapus pesan dengan judul " + temp.getJudulPesan()));
                } catch (final SQLException ex) {
                    WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
                }
            }
        }
    }

    class AksiButton_Pesan_Kirim implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                OperatorForm.this.kirimPesan.setConnection(OperatorForm.this.connection);
                showPanel(OperatorForm.this.kirimPesan, "KIRIM_PESAN");
                setTitleBody("Kirim");
            } catch (final SQLException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            }
        }
    }

    class AksiButton_Pesan_Rincian implements ActionListener {

        private Operator operatorTemp;

        public void actionPerformed(final ActionEvent e) {
            try {
                this.operatorTemp = MySQL.getOperator(OperatorForm.this.connection,
                        OperatorForm.this.panelPesan.getPengirim());
                OperatorForm.this.detailOperator.setOperator(this.operatorTemp);
                OperatorForm.this.glasspane.showComponent(OperatorForm.this.detailOperator);
            } catch (final DataNotFoundException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            } catch (final SQLException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            }
        }
    }

    class AksiButton_Pesan_Segarkan implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                OperatorForm.this.panelPesan.reset();
                new WorkerPesan(OperatorForm.this.connection, OperatorForm.this.panelPesan.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        runLoading("Loading Pesan");
                        OperatorForm.this.panelPesan.setEnabled(false);
                    }

                    public void runLast() {
                        doneLoading();
                        OperatorForm.this.panelPesan.setEnabled(true);
                    }
                }, OperatorForm.this.operator.getIdOperator()).execute();
            } catch (final SQLException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            }
        }
    }

    class AksiButton_Pribadi_Batal implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            showPanel(OperatorForm.this.menuUtama, "MENU_UTAMA");
            setTitleBody(null);
        }
    }

    class AksiButton_Pribadi_Simpan implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                MySQL.updateOperator(OperatorForm.this.connection, OperatorForm.this.panelOperator.getOperator(), OperatorForm.this.operator.getIdOperator());
                MySQL.insertAktivitasOperator(connection, new DefaultAktivitasOperator(operator.getIdOperator(),
                        "merubah data identitas pribadi"));
                setOperator(OperatorForm.this.panelOperator.getOperator());
                showPanel(OperatorForm.this.menuUtama, "MENU_UTAMA");
                setTitleBody(null);
            } catch (final DataNotCompleteException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            } catch (final SQLException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            }
        }
    }

    class AksiButton_RumahProduksi_Saring implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                new WorkerRumahProduksi(OperatorForm.this.connection,
                        OperatorForm.this.panelRumahProduksi.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        OperatorForm.this.panelRumahProduksi.setEnabled(false);
                        runLoading("Loading Rumah Produksi");
                    }

                    public void runLast() {
                        OperatorForm.this.panelRumahProduksi.setEnabled(true);
                        doneLoading();
                        OperatorForm.this.panelRumahProduksi.reset();
                    }
                }, OperatorForm.this.panelRumahProduksi.getId(),
                        OperatorForm.this.panelRumahProduksi.getNama()).execute();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_RumahProduksi_Segarkan implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                OperatorForm.this.panelRumahProduksi.reset();
                new WorkerRumahProduksi(OperatorForm.this.connection,
                        OperatorForm.this.panelRumahProduksi.getTableModel(), new WorkerAction() {

                    public void runFirst() {
                        OperatorForm.this.panelRumahProduksi.setEnabled(false);
                        runLoading("Loading Rumah Produksi");
                    }

                    public void runLast() {
                        OperatorForm.this.panelRumahProduksi.setEnabled(true);
                        doneLoading();
                    }
                }).execute();
            } catch (final SQLException ex) {
                Logger.getLogger(OperatorForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    class AksiButton_RumahProduksi_Tambah implements ActionListener {

        public void actionPerformed(final ActionEvent e) {
            try {
                MySQL.insertRumahProduksi(OperatorForm.this.connection,
                        OperatorForm.this.panelRumahProduksi.getRumahProduksi());
                MySQL.insertAktivitasOperator(connection, new DefaultAktivitasOperator(operator.getIdOperator(),
                        "menambah rumah produksi baru dengan id " + panelRumahProduksi.getId()));
                OperatorForm.this.panelRumahProduksi.getTableModel().add(
                        OperatorForm.this.panelRumahProduksi.getRumahProduksi());
                OperatorForm.this.panelRumahProduksi.reset();
            } catch (final SQLException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            } catch (final DataNotCompleteException ex) {
                WidgetUtilities.showErrorMessage(OperatorForm.this, ex.getMessage());
            }
        }
    }
}
