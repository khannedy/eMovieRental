/*
 * 
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
package usu.rental.film;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import usu.rental.film.guest.GuestForm;
import usu.widget.util.WidgetUtilities;

/**
 *
 * @author usu
 */
public class RunGuest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        WidgetUtilities.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(RunGuest.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(RunGuest.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(RunGuest.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(RunGuest.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        GuestForm form = new GuestForm();
                        form.setUndecorated(true);
                        form.setVisible(true);
                    } catch (FileNotFoundException ex) {
                        WidgetUtilities.showErrorMessage(null, ex.getMessage());
                        System.exit(1);
                    } catch (IOException ex) {
                        WidgetUtilities.showErrorMessage(null, ex.getMessage());
                        System.exit(1);
                    } catch (ClassNotFoundException ex) {
                        WidgetUtilities.showErrorMessage(null, ex.getMessage());
                        System.exit(1);
                    } catch (SQLException ex) {
                        WidgetUtilities.showErrorMessage(null, ex.getMessage());
                        System.exit(1);
                    }
                }
            }
        });
    }
}
