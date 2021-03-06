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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author juanc
 */
public class Productos extends javax.swing.JFrame {

    static ProductRepository productR;
    static ProviderRepository providerR;
    static CategoryRepository categoryR;

    /**
     * Creates new form Productos
     */
    public Productos(ProductRepository pr, CategoryRepository cr, ProviderRepository pR) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.productR = pr;
        this.categoryR = cr;
        this.providerR = pR;
        modeloTabla();
        llenarTabla();
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void llenarTabla() {
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        List<Product> lp = productR.findProductEntities();
        Object o[] = null;

        for (int i = 0; i < lp.size(); i++) {
            modelo.addRow(o);
            Provider p = providerR.findProvider(lp.get(i).getProviderID().getId());
            Category c = categoryR.findCategory(lp.get(i).getCategoryID().getId());

            modelo.setValueAt(lp.get(i).getId(), i, 0);
            modelo.setValueAt(p.getName(), i, 1);
            modelo.setValueAt(c.getName(), i, 2);
            modelo.setValueAt(lp.get(i).getName(), i, 3);
            modelo.setValueAt(lp.get(i).getPrice(), i, 4);
            modelo.setValueAt(lp.get(i).getStock(), i, 5);
        }

        for (int i = 0; i < tProductos.getColumnCount(); i++) {
            tProductos.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    DefaultTableModel modelo;
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

    private void modeloTabla() {
        try {
            modelo = (new DefaultTableModel(
                    null, new String[]{
                        "ID", "Provider",
                        "Category", "Name", "Price", "Stock"}) {
                        Class[] types = new Class[]{
                            java.lang.Integer.class,
                            java.lang.String.class,
                            java.lang.String.class,
                            java.lang.String.class,
                            java.lang.Float.class,
                            java.lang.Integer.class
                        };
                        boolean[] canEdit = new boolean[]{
                            false, false, false, false, false, false
                        };

                        @Override
                        public Class getColumnClass(int columnIndex) {
                            return types[columnIndex];
                        }

                        @Override
                        public boolean isCellEditable(int rowIndex, int colIndex) {
                            return canEdit[colIndex];
                        }
                    });
            tProductos.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.toString() + "error2");
        }
        tProductos.getColumnModel().getColumn(0).setPreferredWidth(80);
        tProductos.getColumnModel().getColumn(0).setMaxWidth(80);
        tProductos.getColumnModel().getColumn(4).setPreferredWidth(80);
        tProductos.getColumnModel().getColumn(4).setMaxWidth(80);
        tProductos.getColumnModel().getColumn(5).setPreferredWidth(80);
        tProductos.getColumnModel().getColumn(5).setMaxWidth(80);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tProductos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        bAgregarProducto = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(900, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        tProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tProductos);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(0, 50, 900, 460);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Products");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(400, 20, 70, 16);

        bAgregarProducto.setText("Agregar Producto");
        bAgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAgregarProductoActionPerformed(evt);
            }
        });
        getContentPane().add(bAgregarProducto);
        bAgregarProducto.setBounds(50, 520, 140, 25);

        jButton1.setText("Categorías");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(749, 520, 100, 25);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Categorias c = new Categorias(categoryR);
        c.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void bAgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAgregarProductoActionPerformed
        agregarProducto ap = new agregarProducto(productR, categoryR, providerR);
        ap.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_bAgregarProductoActionPerformed

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
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Productos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Productos(productR, categoryR, providerR).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAgregarProducto;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tProductos;
    // End of variables declaration//GEN-END:variables
}
