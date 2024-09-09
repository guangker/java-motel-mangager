/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.form;

import com.mycompany.controller.KhachThueController;
import com.mycompany.model.Account;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


/**
 *
 * @author quang
 */
public class KhachThueForm extends javax.swing.JPanel {

    private KhachThueController controller= null;
    public KhachThueForm(Account user) {
        initComponents();
        controller = new KhachThueController(user, jtbView, jbtnAdd, jbtnRemove, jtfSearch);
        controller.setDataToTable();
    }
      
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        classTableModule1 = new com.mycompany.swing.ClassTableModule();
        jbrRoot = new com.mycompany.swing.PanelBorder();
        jbtnPrint = new javax.swing.JButton();
        jbtnRemove = new javax.swing.JButton();
        jbtnAdd = new javax.swing.JButton();
        jtfSearch = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbView = new com.mycompany.swing.TableFix();

        setBackground(new java.awt.Color(204, 204, 204));
        setPreferredSize(new java.awt.Dimension(984, 584));

        jbtnPrint.setBackground(new java.awt.Color(0, 11, 172));
        jbtnPrint.setForeground(new java.awt.Color(255, 255, 255));
        jbtnPrint.setText("In ra file Excel");

        jbtnRemove.setBackground(new java.awt.Color(0, 11, 172));
        jbtnRemove.setForeground(new java.awt.Color(255, 255, 255));
        jbtnRemove.setText("Xoá");

        jbtnAdd.setBackground(new java.awt.Color(0, 11, 172));
        jbtnAdd.setForeground(new java.awt.Color(255, 255, 255));
        jbtnAdd.setText("Thêm");
        jbtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbtnAddActionPerformed(evt);
            }
        });

        jtbView.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jtbView);

        javax.swing.GroupLayout jbrRootLayout = new javax.swing.GroupLayout(jbrRoot);
        jbrRoot.setLayout(jbrRootLayout);
        jbrRootLayout.setHorizontalGroup(
            jbrRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jbrRootLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jbtnPrint)
                .addGap(18, 18, 18)
                .addComponent(jbtnAdd)
                .addGap(18, 18, 18)
                .addComponent(jbtnRemove)
                .addGap(35, 35, 35))
            .addGroup(jbrRootLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1)
                .addGap(35, 35, 35))
        );
        jbrRootLayout.setVerticalGroup(
            jbrRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jbrRootLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jbrRootLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbtnPrint)
                    .addComponent(jbtnRemove)
                    .addComponent(jbtnAdd)
                    .addComponent(jtfSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
                .addGap(35, 35, 35))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jbrRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jbrRoot, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(27, 27, 27))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jbtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbtnAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jbtnAddActionPerformed
    protected void paintChildren(Graphics grphcs){
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint g = new GradientPaint(0, 0, Color.decode("#1CB5E0"), 0, getHeight(), Color.decode("#000046"));
        g2.setPaint(g);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
        g2.fillRect(getWidth(),0,getWidth(),getHeight());
        super.paintChildren(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.mycompany.swing.ClassTableModule classTableModule1;
    private javax.swing.JScrollPane jScrollPane1;
    private com.mycompany.swing.PanelBorder jbrRoot;
    private javax.swing.JButton jbtnAdd;
    private javax.swing.JButton jbtnPrint;
    private javax.swing.JButton jbtnRemove;
    private com.mycompany.swing.TableFix jtbView;
    private javax.swing.JTextField jtfSearch;
    // End of variables declaration//GEN-END:variables
}
