
package com.mycompany.component;

import com.mycompany.swing.ButtonOutLine;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class PanelCover extends javax.swing.JPanel {

    private final DecimalFormat df = new DecimalFormat("##0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private ActionListener event;
    private MigLayout  layout;
    private JLabel title;
    private JLabel description;
    private JLabel description1;
    private ButtonOutLine button;
    private boolean isLogin;
    
    public PanelCover() {
        initComponents();
        setOpaque(false);
        layout = new MigLayout("wrap,fill", "[center]","push[]25[]10[]30[]push");
        setLayout(layout);
        init();
    }
    
    private void init()
    {
        title = new JLabel("Welcome back");
        title.setFont(new Font("sansserif", 1, 30));
        title.setForeground(new Color(245, 245, 245));
        add(title);
        
        
        description = new JLabel("Hỗ trợ quản lý cho ");
        description.setForeground(new Color(245, 245, 245));
        add(description);
        
        description1 = new JLabel("chủ nhà trọ và người dùng");
        description1.setForeground(new Color(245, 245, 245));
        add(description1);
        
        button = new ButtonOutLine();
        button.setBackground(new Color(255, 255, 255));
        button.setForeground(new Color(255, 255, 255));
        button.setText("ĐĂNG KÝ");
        add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                event.actionPerformed(ae);
            }
        });
        add(button, "w 60%, h 40");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 328, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override   
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        GradientPaint gra = new GradientPaint(0, 0, new Color(28,181,224), 0, getHeight(),new Color(0,0,70));
        g2.setPaint(gra);
        g2.fillRect(0, 0, getWidth(), getHeight());
        
        super.paintComponent(g); 
    }
    
    public void addEvent(ActionListener event)
    {
        this.event = event;
    }

    public void registerLeft(double v)
    {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");

    }
    
     public void registerRight(double v)
    {
        v = Double.valueOf(df.format(v));
        login(false);
        layout.setComponentConstraints(title, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description, "pad 0 -" + v + "% 0 0");
        layout.setComponentConstraints(description1, "pad 0 -" + v + "% 0 0");
    }
     
     public void loginLeft(double v)
    {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 " + v + "%");
    }
    
     public void loginRight(double v)
    {
        v = Double.valueOf(df.format(v));
        login(true);
        layout.setComponentConstraints(title, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description, "pad 0 " + v + "% 0 " + v + "%");
        layout.setComponentConstraints(description1, "pad 0 " + v + "% 0 " + v + "%");
    }
    
    private void login(boolean login)
    {
        if (this.isLogin != login) 
        {
            if (login) {
                title.setText("Motel Manager");
                description.setText("Đăng ký tài khoản của bạn");
                description1.setText("và bắt đầu với chúng tôi");
                button.setText("ĐĂNG NHẬP");
            } else {
                title.setText("Welcome back");
                description.setText("Hỗ trợ quản lý cho ");
                description1.setText("chủ nhà trọ và người dùng");
                button.setText("ĐĂNG KÝ");
            }
            this.isLogin = login;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}