/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finstere.flure;

import javax.swing.ImageIcon;

/**
 *
 * @author nadim
 */
public class MenuGUI extends javax.swing.JFrame {

    /**
     * Creates new form MenuGUI
     */
    public MenuGUI() {
        initComponents();
        this.logo.setIcon(new ImageIcon(chemin + "logo_finstere.gif"));
        this.back.setIcon(new ImageIcon(chemin + "background.jpg"));

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
        jLabel3 = new javax.swing.JLabel();
        nomJoueur1 = new javax.swing.JTextField();
        nomJoueur2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        errorText = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Veuillez entrer le nom du premier joueur : ");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 79, 190, 28));

        jLabel3.setText("Veuillez entrer le nom du deuxiem joueur : ");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 147, 193, 28));

        nomJoueur1.setText("jTextField1");
        nomJoueur1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nomJoueur1ActionPerformed(evt);
            }
        });
        getContentPane().add(nomJoueur1, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 80, 182, 28));

        nomJoueur2.setText("jTextField1");
        getContentPane().add(nomJoueur2, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 148, 180, 28));

        jButton1.setText("Commencer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 217, -1, -1));

        errorText.setText("Appuyer sur le bouton pour commencer");
        getContentPane().add(errorText, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 187, 380, 24));
        getContentPane().add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 135, 61));
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(3, 0, 400, 260));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if("".equals(this.nomJoueur1.getText()) || "\n".equals(this.nomJoueur1.getText()) || "".equals(this.nomJoueur2.getText()) || "\n".equals(this.nomJoueur2.getText())){
            this.errorText.setText("Veuillez verifier vos nom !");
        } else {
            this.nom1 = this.nomJoueur1.getText();
            this.nom2 = this.nomJoueur2.getText();
            FinFlureGUI newgame = new FinFlureGUI();
            newgame.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void nomJoueur1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nomJoueur1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nomJoueur1ActionPerformed

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
            java.util.logging.Logger.getLogger(MenuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back;
    private javax.swing.JLabel errorText;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel logo;
    private javax.swing.JTextField nomJoueur1;
    private javax.swing.JTextField nomJoueur2;
    // End of variables declaration//GEN-END:variables
    public static String nom1;
    public static String nom2;
    private final static String chemin = "C:\\Users\\nadim\\Documents\\GitHub\\FINSTERE-FLURE\\src\\img\\";
}
