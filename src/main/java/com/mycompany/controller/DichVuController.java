package com.mycompany.controller;

import com.mycompany.component.AddDichVu;
import com.mycompany.model.Account;
import com.mycompany.model.DichVu;
import com.mycompany.service.DichVuService;
import com.mycompany.service.NhaTroService;
import com.mycompany.swing.ClassTableModule;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class DichVuController {
    
    private TableRowSorter<TableModel> rowSorter = null;
    private DichVu dichvu ;
    private Account user;
    private JTable jtbView;
    private JButton btnThem;
    private JComboBox cpBoxListNhaTro; 
    private DichVuService dichVuService = null;
    private String[] listColumn = {"ID", "Tên", "Giá", "Ghi Chú"};
    private HashMap<String,String> listNhaTro ;
    private HashMap<String,String> selectedNhaTro = new HashMap<>();
    private AddDichVu add = null;

    public DichVuController(Account user, JTable jtbView, JButton btnThem, JComboBox cpBoxListNhaTro) {
        this.dichvu = new DichVu();
        this.user = user;
        this.jtbView = jtbView;
        this.btnThem = btnThem;
        this.cpBoxListNhaTro = cpBoxListNhaTro;
        this.dichVuService = new DichVuService();
        listNhaTro = new NhaTroService().getIdAndName(user);

    }
    
    
    public void setDataToTable(String idNhaTro)
    {
        
        List<DichVu> listItem = new ArrayList<>();
        if(idNhaTro.equals("All"))
        {
            listItem = dichVuService.getList(user);
        }else
        {
            listItem = dichVuService.getListForIDNhaTro(idNhaTro);
        }
        DefaultTableModel module = new ClassTableModule().setTableDichVu(listItem, listColumn);
        jtbView.setModel(module);
        rowSorter = new TableRowSorter<>(jtbView.getModel());
        jtbView.setRowSorter(rowSorter);
        
        
        jtbView.removeAll();
        jtbView.setLayout(new BorderLayout());
//        jtbView.add(scrollPane);
        jtbView.validate();
        jtbView.repaint();
    }
    
    public void addListNhaTro()
    {
//        listNhaTro.put("All", "All");
        for(String item:listNhaTro.keySet())
        {
            cpBoxListNhaTro.addItem(item);
        }
//        cpBoxListNhaTro.addItem("All");
        
    }
    
    public void setEvent()
    {
        btnThem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                add = new AddDichVu(new DichVu(),listNhaTro);
                add.setTitle("Thêm thông tin dịch vụ");
                add.setResizable(false);
                add.setLocationRelativeTo(null);
                add.setVisible(true);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnThem.setBackground(new Color(0,200,83));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnThem.setBackground(new Color(100,221,23));
            }
            
            
        });
        
        
        jtbView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2 && jtbView.getSelectedRow() != -1)
                {
                    DefaultTableModel model = (DefaultTableModel) jtbView.getModel();
                    int selectedRowIndex = jtbView.getSelectedRow();
                    selectedRowIndex = jtbView.convertRowIndexToModel(selectedRowIndex);
                    System.out.println(selectedRowIndex);
                    
                    DichVu dv = new DichVu();
                    dv.setIdDichVu(model.getValueAt(selectedRowIndex, 0) != null ? model.getValueAt(selectedRowIndex, 0).toString():"");
                    dv.setTenDichVu(model.getValueAt(selectedRowIndex, 1) != null ?model.getValueAt(selectedRowIndex, 1).toString():"");
                    dv.setGia( model.getValueAt(selectedRowIndex, 2) != null ? (float)model.getValueAt(selectedRowIndex, 2): null);
                    dv.setLoaiDichVu((String) model.getValueAt(selectedRowIndex, 3));

//                    selectedNhaTro.put((String) cpBoxListNhaTro.getSelectedItem(),(String) listNhaTro.get(cpBoxListNhaTro.getSelectedItem()));
//                    AddDichVu add = new AddDichVu(dv, selectedNhaTro);
                    selectedNhaTro.put((String) cpBoxListNhaTro.getSelectedItem(), listNhaTro.get(cpBoxListNhaTro.getSelectedItem()));
                    add = new AddDichVu(dv,selectedNhaTro );
                    selectedNhaTro.clear();
                    add.setTitle("Sữa thông tin dịch vụ");
                    add.setResizable(false);
                    add.setLocationRelativeTo(null);
                    add.setVisible(true);
                }

            }
        });
        addListNhaTro();
        cpBoxListNhaTro.setSelectedIndex(0);
        setDataToTable(listNhaTro.get(cpBoxListNhaTro.getItemAt(0)));
        cpBoxListNhaTro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedItem = (String) cpBoxListNhaTro.getSelectedItem();
                String idNhaTro = listNhaTro.get(selectedItem);
                if(selectedItem.equals("All"))
                {
                    setDataToTable("All");
                }else
                {
                    setDataToTable(idNhaTro);
                }

            }
        });
    }
    
    
    
}
