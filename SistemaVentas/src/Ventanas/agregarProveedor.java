/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Entity.Provider;
import Repository.ProviderRepository;

/**
 *
 * @author juanc
 */
public class agregarProveedor extends javax.swing.JFrame {

    static ProviderRepository providerR;
    private Provider p = new Provider();
    /**
     * Creates new form agregarProveedor
     */
    public agregarProveedor(ProviderRepository pr) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.providerR = pr;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        areaName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        areaAddress = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        areaPhone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        areaWebsite = new javax.swing.JTextField();
        bGuardarProveedor = new javax.swing.JButton();
        bCanelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(900, 600));
        setMinimumSize(new java.awt.Dimension(900, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Agregar Proveedor");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(330, 30, 240, 50);

        areaName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaNameActionPerformed(evt);
            }
        });
        getContentPane().add(areaName);
        areaName.setBounds(110, 120, 710, 22);

        jLabel2.setText("Name");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 120, 60, 16);

        jLabel3.setText("Address");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(41, 180, 50, 16);

        areaAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaAddressActionPerformed(evt);
            }
        });
        getContentPane().add(areaAddress);
        areaAddress.setBounds(110, 180, 710, 22);

        jLabel4.setText("Phone");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 240, 60, 16);

        areaPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaPhoneActionPerformed(evt);
            }
        });
        getContentPane().add(areaPhone);
        areaPhone.setBounds(110, 240, 710, 22);

        jLabel5.setText("Website");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(35, 300, 50, 16);

        areaWebsite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaWebsiteActionPerformed(evt);
            }
        });
        getContentPane().add(areaWebsite);
        areaWebsite.setBounds(110, 300, 710, 22);

        bGuardarProveedor.setText("Guardar");
        bGuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarProveedorActionPerformed(evt);
            }
        });
        getContentPane().add(bGuardarProveedor);
        bGuardarProveedor.setBounds(60, 430, 90, 25);

        bCanelar.setText("Cancelar");
        bCanelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCanelarActionPerformed(evt);
            }
        });
        getContentPane().add(bCanelar);
        bCanelar.setBounds(689, 430, 100, 25);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void areaNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_areaNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_areaNameActionPerformed

    private void areaAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_areaAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_areaAddressActionPerformed

    private void areaPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_areaPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_areaPhoneActionPerformed

    private void areaWebsiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_areaWebsiteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_areaWebsiteActionPerformed

    private void bCanelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCanelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_bCanelarActionPerformed

    private void bGuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGuardarProveedorActionPerformed
        p.setName(areaName.getText());
        p.setAddress(areaAddress.getText());
        p.setPhone(areaPhone.getText());
        p.setWebsite(areaWebsite.getText());
        
        providerR.create(p);
        
        Proveedores vp = new Proveedores(providerR);
        vp.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_bGuardarProveedorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(agregarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(agregarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(agregarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(agregarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new agregarProveedor(providerR).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField areaAddress;
    private javax.swing.JTextField areaName;
    private javax.swing.JTextField areaPhone;
    private javax.swing.JTextField areaWebsite;
    private javax.swing.JButton bCanelar;
    private javax.swing.JButton bGuardarProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
