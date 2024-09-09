/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.qlnt;

import com.mycompany.form.Login_JFrame;

/**
 *
 * @author HUNG
 */
public class Main extends javax.swing.JFrame{
    
    
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login_JFrame().setVisible(true);
            }
        });
    }
}
