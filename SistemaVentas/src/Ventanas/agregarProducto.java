/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ventanas;

import Entity.Category;
import Entity.Product;
import Entity.Provider;
import Repository.CategoryRepository;
import Repository.ProductRepository;
import Repository.ProviderRepository;
import java.util.List;

/**
 *
 * @author juanc
 */
public class agregarProducto extends javax.swing.JFrame {

    static CategoryRepository categoryR;
    static ProviderRepository providerR;
    static ProductRepository productR;
    Product p = new Product();
    /**
     * Creates new form agregarProducto
     */
    public agregarProducto(ProductRepository pr, CategoryRepository cr, ProviderRepository pR) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.productR = pr;
        this.categoryR = cr;
        this.providerR = pR;
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        listar();
    }
    
    public void listar(){
        List<Category> lc = categoryR.findCategoryEntities();
        List<Provider> lp = providerR.findProviderEntities();
        
        for (int i = 0; i < lc.size(); i++) {
            listCategories.addItem(lc.get(i));            
        }
        
        for (int i = 0; i < lp.size(); i++) {
            listProviders.addItem(lp.get(i));
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SistemaVentasPUEntityManager = java.beans.Beans.isDesignTime() ? null : javax.persistence.Persistence.createEntityManagerFactory("SistemaVentasPU").createEntityManager();
        providerQuery = java.beans.Beans.isDesignTime() ? null : SistemaVentasPUEntityManager.createQuery("SELECT p FROM Provider p");
        providerList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : providerQuery.getResultList();
        categoryQuery = java.beans.Beans.isDesignTime() ? null : SistemaVentasPUEntityManager.createQuery("SELECT c FROM Category c");
        categoryList = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : categoryQuery.getResultList();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        areaName = new javax.swing.JTextField();
        areaPrice = new javax.swing.JTextField();
        areaStock = new javax.swing.JTextField();
        listCategories = new javax.swing.JComboBox();
        listProviders = new javax.swing.JComboBox();
        bGuardarCliente = new javax.swing.JButton();
        bCanelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Agregar Producto");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(330, 30, 210, 50);

        jLabel2.setText("Name");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(50, 120, 50, 16);

        jLabel3.setText("Provider");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 180, 60, 16);

        jLabel4.setText("Category");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(50, 240, 70, 16);

        jLabel5.setText("Stock");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(50, 360, 50, 16);

        jLabel6.setText("Price");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(50, 300, 50, 16);
        getContentPane().add(areaName);
        areaName.setBounds(100, 120, 730, 22);
        getContentPane().add(areaPrice);
        areaPrice.setBounds(100, 300, 70, 22);
        getContentPane().add(areaStock);
        areaStock.setBounds(100, 360, 70, 22);

        getContentPane().add(listCategories);
        listCategories.setBounds(110, 240, 720, 22);

        getContentPane().add(listProviders);
        listProviders.setBounds(110, 180, 720, 22);

        bGuardarCliente.setText("Guardar");
        bGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bGuardarClienteActionPerformed(evt);
            }
        });
        getContentPane().add(bGuardarCliente);
        bGuardarCliente.setBounds(60, 460, 90, 25);

        bCanelar.setText("Cancelar");
        bCanelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCanelarActionPerformed(evt);
            }
        });
        getContentPane().add(bCanelar);
        bCanelar.setBounds(690, 460, 100, 25);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bGuardarClienteActionPerformed

        
        p.setName(areaName.getText());
        p.setProviderID((Provider) listProviders.getSelectedItem());
        p.setCategoryID((Category) listCategories.getSelectedItem());
        p.setPrice(Float.parseFloat(areaPrice.getText()));
        p.setStock(Integer.parseInt(areaStock.getText()));
                
        productR.create(p);

        Productos vp = new Productos(productR, categoryR, providerR);
        vp.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_bGuardarClienteActionPerformed

    private void bCanelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCanelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_bCanelarActionPerformed

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
            java.util.logging.Logger.getLogger(agregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(agregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(agregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(agregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new agregarProducto(productR, categoryR, providerR).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.persistence.EntityManager SistemaVentasPUEntityManager;
    private javax.swing.JTextField areaName;
    private javax.swing.JTextField areaPrice;
    private javax.swing.JTextField areaStock;
    private javax.swing.JButton bCanelar;
    private javax.swing.JButton bGuardarCliente;
    private java.util.List<Entity.Category> categoryList;
    private javax.persistence.Query categoryQuery;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JComboBox listCategories;
    private javax.swing.JComboBox listProviders;
    private java.util.List<Entity.Provider> providerList;
    private javax.persistence.Query providerQuery;
    // End of variables declaration//GEN-END:variables
}
