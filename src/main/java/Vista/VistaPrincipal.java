/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

/**
 *
 * @author MSI
 */

import javax.swing.JFrame;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import javax.swing.JLabel;

public class VistaPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form VistaPrincipal
     */
    public VistaPrincipal() {
        initComponents();
        setTitle("Vista Principal");                 // Set fixed size of the JFrame
        setResizable(false);                   // Disable window resizing
        setLocationRelativeTo(null);           // Center the window on the screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        headerTextLabel = new javax.swing.JLabel();
        orangeBanner = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();
        welcomeHeader = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        gestionEntradasButton = new javax.swing.JButton();
        gestionEventosButton = new javax.swing.JButton();
        gestionRecintosButton = new javax.swing.JButton();
        exitBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        dynamicPanel = new javax.swing.JPanel();
        seminarImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setPreferredSize(new java.awt.Dimension(1024, 768));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        backPanel.setBackground(new java.awt.Color(255, 255, 255));
        backPanel.setForeground(new java.awt.Color(255, 255, 255));
        backPanel.setMinimumSize(new java.awt.Dimension(1024, 768));
        backPanel.setPreferredSize(new java.awt.Dimension(1024, 768));
        backPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headerPanel.setBackground(new java.awt.Color(0, 0, 0));
        headerPanel.setForeground(new java.awt.Color(51, 51, 51));
        headerPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        headerTextLabel.setBackground(new java.awt.Color(0, 0, 0));
        headerTextLabel.setFont(new java.awt.Font("MS PGothic", 0, 36)); // NOI18N
        headerTextLabel.setForeground(new java.awt.Color(255, 255, 255));
        headerTextLabel.setText("Sistema Gestionador de Eventos");
        headerTextLabel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        headerPanel.add(headerTextLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 6, 500, 88));

        backPanel.add(headerPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, -1, -1));

        orangeBanner.setBackground(new java.awt.Color(255, 102, 51));
        orangeBanner.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        backPanel.add(orangeBanner, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1030, 40));

        menuPanel.setBackground(new java.awt.Color(102, 102, 102));
        menuPanel.setForeground(new java.awt.Color(153, 153, 153));
        menuPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        welcomeHeader.setBackground(new java.awt.Color(0, 0, 0));
        welcomeHeader.setForeground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("MS Gothic", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("BIENVENIDO!");

        javax.swing.GroupLayout welcomeHeaderLayout = new javax.swing.GroupLayout(welcomeHeader);
        welcomeHeader.setLayout(welcomeHeaderLayout);
        welcomeHeaderLayout.setHorizontalGroup(
            welcomeHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, welcomeHeaderLayout.createSequentialGroup()
                .addContainerGap(63, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(61, 61, 61))
        );
        welcomeHeaderLayout.setVerticalGroup(
            welcomeHeaderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, welcomeHeaderLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        menuPanel.add(welcomeHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 256, 40));

        gestionEntradasButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gestionEntradasButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recurso/eventicon3.png"))); // NOI18N
        gestionEntradasButton.setText("Gestion de Eventos");
        gestionEntradasButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionEntradasButtonActionPerformed(evt);
            }
        });
        menuPanel.add(gestionEntradasButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 256, 60));
        gestionEntradasButton.getAccessibleContext().setAccessibleDescription("");

        gestionEventosButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gestionEventosButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recurso/ticketSmall.png"))); // NOI18N
        gestionEventosButton.setText("Gestion de Entradas a Eventos");
        gestionEventosButton.setActionCommand("Gestion de Entradas");
        gestionEventosButton.setPreferredSize(new java.awt.Dimension(256, 28));
        gestionEventosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionEventosButtonActionPerformed(evt);
            }
        });
        menuPanel.add(gestionEventosButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 256, 60));
        gestionEventosButton.getAccessibleContext().setAccessibleDescription("");

        gestionRecintosButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        gestionRecintosButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recurso/buildingicon.png"))); // NOI18N
        gestionRecintosButton.setText("Gestion de Recintos");
        gestionRecintosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gestionRecintosButtonActionPerformed(evt);
            }
        });
        menuPanel.add(gestionRecintosButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 256, 60));
        gestionRecintosButton.getAccessibleContext().setAccessibleDescription("");

        exitBtn.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        exitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recurso/redcross.png"))); // NOI18N
        exitBtn.setText("Salir");
        exitBtn.setToolTipText("");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        menuPanel.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 480, 256, 60));

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recurso/homeicon.png"))); // NOI18N
        jButton1.setText("Pagina Principal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        menuPanel.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 256, 60));

        backPanel.add(menuPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 211, 256, 560));

        dynamicPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        seminarImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recurso/seminar.png"))); // NOI18N
        seminarImage.setText("jLabel2");
        seminarImage.setName(""); // NOI18N
        dynamicPanel.add(seminarImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 760, 560));

        backPanel.add(dynamicPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 210, 760, 560));

        getContentPane().add(backPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gestionEventosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionEventosButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gestionEventosButtonActionPerformed

    private void gestionEntradasButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionEntradasButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gestionEntradasButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void gestionRecintosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gestionRecintosButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_gestionRecintosButtonActionPerformed

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exitBtnActionPerformed

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
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backPanel;
    private javax.swing.JPanel dynamicPanel;
    public javax.swing.JButton exitBtn;
    public javax.swing.JButton gestionEntradasButton;
    public javax.swing.JButton gestionEventosButton;
    public javax.swing.JButton gestionRecintosButton;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel headerTextLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JPanel orangeBanner;
    private javax.swing.JLabel seminarImage;
    private javax.swing.JPanel welcomeHeader;
    // End of variables declaration//GEN-END:variables
}