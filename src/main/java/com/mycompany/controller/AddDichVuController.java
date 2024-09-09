/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.controller;

import com.mycompany.model.Account;
import com.mycompany.model.DichVu;
import com.mycompany.service.DichVuService;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author HUNG
 */
public class AddDichVuController {
    private JButton btnSubmit, btnDelete;
    private JTextField jtfTenDichVu, jtfGiaDichVu, jtfNote;
    private JLabel jlbMsg;
    private JComboBox cpBoxListNhaTro =null; 
    private HashMap<String,String> listNhaTro = null;
    private DichVu dichvu = null;
    private JFrame __this ;
    private DichVuService service = null;

    public AddDichVuController(JFrame __this, DichVu dichvu ,JButton btnSubmit, JButton btnDelete, JTextField jtfTenDichVu, JTextField jtfGiaDichVu, JTextField jtfNote, JLabel jlbMsg,JComboBox cpBoxListNhaTro,HashMap<String,String> listNhaTro) {
        this.__this = __this;
        this.dichvu = dichvu;
        this.btnSubmit = btnSubmit;
        this.btnDelete = btnDelete;
        this.jtfTenDichVu = jtfTenDichVu;
        this.jtfGiaDichVu = jtfGiaDichVu;
        this.jtfNote = jtfNote;
        this.jlbMsg = jlbMsg;
        this.service = new DichVuService();
        this.cpBoxListNhaTro = cpBoxListNhaTro;
        this.listNhaTro = listNhaTro;
    }
    
    public void setView(DichVu dichvu)
    {
        this.dichvu = dichvu;
        
        jtfTenDichVu.setText(dichvu.getTenDichVu());
        jtfGiaDichVu.setText( dichvu.getGia() != 0f ?dichvu.getGia()+ "":null);
        jtfNote.setText(dichvu.getLoaiDichVu());
        addListNhaTro();
        cpBoxListNhaTro.setSelectedIndex(0);
        if(dichvu.getIdDichVu().equals(""))
        {
            btnDelete.setVisible(false);
        }
    }
    
    public void setEvent()
    {
        btnDelete.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                int status = service.deleteForID(dichvu.getIdDichVu());
                if(status == 1)
                {
                    __this.dispose();
                }else
                {
                    jlbMsg.setText("Xoá thất bại !");
                }
              
            }
            
        });
        
        btnSubmit.addMouseListener(new MouseAdapter() 
        {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jtfTenDichVu.getText().length() == 0 || jtfGiaDichVu.getText().length() == 0)
                {
                    jlbMsg.setText("Vui lòng nhập dữ liệu!");
                }
                else 
                {
//                    dichvu.setIdDichVu("DV053");
                    dichvu.setTenDichVu(jtfTenDichVu.getText().trim());
                    dichvu.setGia(Float.parseFloat(jtfGiaDichVu.getText().trim()));
                    dichvu.setLoaiDichVu(jtfNote.getText().trim());
                    
                    dichvu.setIdNhaTro(listNhaTro.get(cpBoxListNhaTro.getSelectedItem()));
                    int isSuccess = service.createOrUpdate(dichvu);
                    if(isSuccess>0)
                    {
                        jlbMsg.setText("Thành công!!!");
                        
                    }
                }
            }
            
            

            @Override
            public void mouseEntered(MouseEvent e) {
                btnSubmit.setBackground(new Color(0,200,83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnSubmit.setBackground(new Color(100,221,23));
            }
            
            
        });
    }
    
    public void addListNhaTro()
    {
        cpBoxListNhaTro.removeAllItems();
        for(String item:listNhaTro.keySet())
        {
            cpBoxListNhaTro.addItem(item);
        }
        
        if(cpBoxListNhaTro.getItemCount()  <= 1)
        {
            cpBoxListNhaTro.setEnabled(false);
        }
    }
}
