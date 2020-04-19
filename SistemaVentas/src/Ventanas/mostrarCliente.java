/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Entity.Customer;
import Repository.CustomerRepository;
import Repository.exceptions.NonexistentEntityException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juanc
 */
public class mostrarCliente extends javax.swing.JFrame {

    static private Customer c;
    static CustomerRepository cr = new CustomerRepository();

    /**
     * Creates new form mostrarCliente
     */
    public mostrarCliente(CustomerRepository cr, Customer c) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.c = c;
        this.cr = cr;
        bGuardarCliente.setVisible(false);
        llenarInfo();
    }

    public void llenarInfo() {
        areaName.setText(c.getName());
        areaName.setEditable(false);

        areaRFC.setText(c.getRfc());
        areaRFC.setEditable(false);

        areaAddress.setText(c.getAddress());
        areaAddress.setEditable(false);

        areaPhone.setText(c.getPhone());
        areaPhone.setEditable(false);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        areaPhone = new javax.swing.JTextField();
        areaName = new javax.swing.JTextField();
        areaRFC = new javax.swing.JTextField();
        areaAddress = new javax.swing.JTextField();
        bEditarCliente = new javax.swing.JButton();
        bGuardarCliente = new javax.swing.JButton();
        bEliminarCliente1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Customer");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(400, 20, 120, 30);

        jLabel2.setText("Name");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 110, 50, 16);

        jLabel3.setText("RFC");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 180, 40, 16);

        jLabel4.setText("Address");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 250, 60, 16);

        jLabel5.setText("Phone");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(50, 320, 41, 16);

        areaPhone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaPhoneActionPerformed(evt);
            }
        });
        getContentPane().add(areaPhone);
        areaPhone.setBounds(110, 320, 730, 22);

        areaName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaNameActionPerformed(evt);
            }
        });
        getContentPane().add(areaName);
        areaName.setBounds(110, 110, 730, 22);

        areaRFC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaRFCActionPerformed(evt);
            }
        });
        getContentPane().add(areaRFC);
        areaRFC.setBounds(110, 180, 730, 22);

        areaAddress.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                areaAddressActionPerformed(evt);
            }
        });
        getContentPane().add(areaAddress);
        areaAddress.setBounds(110, 250, 730, 22);

        bEditarCliente.setText("Editar");
        bEditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditarClienteActionPerformed(evt);
            }
        });
        getContentPane().add(bEditarCliente);
        bEditarCliente.setBounds(50, 470, 100, 25);

        bGuardarCliente.setText("Guardar");
        bGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarClienteActionPerformed(evt);
            }
        });
        getContentPane().add(bGuardarCliente);
        bGuardarCliente.setBounds(180, 470, 100, 25);

        bEliminarCliente1.setText("Eliminar");
        bEliminarCliente1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEliminarCliente1ActionPerformed(evt);
            }
        });
        getContentPane().add(bEliminarCliente1);
        bEliminarCliente1.setBounds(700, 470, 110, 25);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void areaPhoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_areaPhoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_areaPhoneActionPerformed

    private void areaNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_areaNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_areaNameActionPerformed

    private void areaRFCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_areaRFCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_areaRFCActionPerformed

    private void areaAddressActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_areaAddressActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_areaAddressActionPerformed

    private void bEditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditarClienteActionPerformed
        areaName.setEditable(true);

        areaRFC.setEditable(true);

        areaAddress.setEditable(true);

        areaPhone.setEditable(true);

        bGuardarCliente.setVisible(true);
    }//GEN-LAST:event_bEditarClienteActionPerformed

    private void bGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGuardarClienteActionPerformed

        c.setRfc(areaRFC.getText());
        c.setName(areaName.getText());
        c.setAddress(areaAddress.getText());
        c.setPhone(areaPhone.getText());

        try {
            cr.edit(c);
        } catch (Exception ex) {
            Logger.getLogger(mostrarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        Clientes vc = new Clientes(cr);
        vc.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_bGuardarClienteActionPerformed

    private void bEliminarCliente1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEliminarCliente1ActionPerformed
        try {
            cr.destroy(c.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(mostrarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Clientes vc = new Clientes(cr);
        vc.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_bEliminarCliente1ActionPerformed

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
            java.util.logging.Logger.getLogger(mostrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(mostrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(mostrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(mostrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                new mostrarCliente(cr, c).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField areaAddress;
    private javax.swing.JTextField areaName;
    private javax.swing.JTextField areaPhone;
    private javax.swing.JTextField areaRFC;
    private javax.swing.JButton bEditarCliente;
    private javax.swing.JButton bEliminarCliente1;
    private javax.swing.JButton bGuardarCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}