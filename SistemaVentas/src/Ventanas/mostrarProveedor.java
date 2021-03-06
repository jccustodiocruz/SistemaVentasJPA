/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Entity.Provider;
import Repository.ProviderRepository;
import Repository.exceptions.NonexistentEntityException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanc
 */
public class mostrarProveedor extends javax.swing.JFrame {

    static private Provider p;
    static ProviderRepository providerR;

    /**
     * Creates new form mostrarProveedor
     */
    public mostrarProveedor(ProviderRepository pr, Provider p) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.p = p;
        this.providerR = pr;
        bGuardarProveedor.setVisible(false);
        llenarInfo();
    }

    public void llenarInfo() {
        areaName.setText(p.getName());
        areaName.setEditable(false);

        areaAddress.setText(p.getAddress());
        areaAddress.setEditable(false);

        areaPhone.setText(p.getPhone());
        areaPhone.setEditable(false);

        areaWebsite.setText(p.getWebsite());
        areaWebsite.setEditable(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        areaName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        areaAddress = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        areaPhone = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        areaWebsite = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        bEditarProveedor = new javax.swing.JButton();
        bGuardarProveedor = new javax.swing.JButton();
        bEliminarProveedor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel2.setText("Name");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 110, 50, 16);

        areaName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaNameActionPerformed(evt);
            }
        });
        getContentPane().add(areaName);
        areaName.setBounds(110, 110, 730, 22);

        jLabel3.setText("Address");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(40, 180, 50, 16);

        areaAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaAddressActionPerformed(evt);
            }
        });
        getContentPane().add(areaAddress);
        areaAddress.setBounds(110, 180, 730, 22);

        jLabel4.setText("Phone");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 250, 60, 16);

        areaPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaPhoneActionPerformed(evt);
            }
        });
        getContentPane().add(areaPhone);
        areaPhone.setBounds(110, 250, 730, 22);

        jLabel5.setText("Website");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(41, 320, 50, 16);

        areaWebsite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaWebsiteActionPerformed(evt);
            }
        });
        getContentPane().add(areaWebsite);
        areaWebsite.setBounds(110, 320, 730, 22);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Provider");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(380, 20, 120, 30);

        bEditarProveedor.setText("Editar");
        bEditarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditarProveedorActionPerformed(evt);
            }
        });
        getContentPane().add(bEditarProveedor);
        bEditarProveedor.setBounds(50, 470, 100, 25);

        bGuardarProveedor.setText("Guardar");
        bGuardarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarProveedorActionPerformed(evt);
            }
        });
        getContentPane().add(bGuardarProveedor);
        bGuardarProveedor.setBounds(180, 470, 110, 25);

        bEliminarProveedor.setText("Eliminar");
        bEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarProveedorActionPerformed(evt);
            }
        });
        getContentPane().add(bEliminarProveedor);
        bEliminarProveedor.setBounds(700, 470, 110, 25);

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

    private void bEditarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditarProveedorActionPerformed
        areaName.setEditable(true);

        areaAddress.setEditable(true);

        areaPhone.setEditable(true);

        areaWebsite.setEditable(true);

        bGuardarProveedor.setVisible(true);
    }//GEN-LAST:event_bEditarProveedorActionPerformed

    private void bGuardarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGuardarProveedorActionPerformed
                
        p.setName(areaName.getText());
        p.setAddress(areaAddress.getText());
        p.setPhone(areaPhone.getText());
        p.setWebsite(areaWebsite.getText());
        
        try {
            providerR.edit(p);
        } catch (Exception ex) {
            Logger.getLogger(mostrarProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Proveedores p = new Proveedores(providerR);
        p.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_bGuardarProveedorActionPerformed

    private void bEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarProveedorActionPerformed
        try {
            providerR.destroy(p.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(mostrarProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Proveedores vp = new Proveedores(providerR);
        vp.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_bEliminarProveedorActionPerformed

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
            java.util.logging.Logger.getLogger(mostrarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mostrarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mostrarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mostrarProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new mostrarProveedor(providerR, p).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField areaAddress;
    private javax.swing.JTextField areaName;
    private javax.swing.JTextField areaPhone;
    private javax.swing.JTextField areaWebsite;
    private javax.swing.JButton bEditarProveedor;
    private javax.swing.JButton bEliminarProveedor;
    private javax.swing.JButton bGuardarProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
