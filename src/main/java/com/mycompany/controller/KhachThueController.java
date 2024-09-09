
package com.mycompany.controller;

import com.mycompany.model.Account;
import com.mycompany.model.KhachThue;
import com.mycompany.service.KhachThueService;
import com.mycompany.swing.ClassTableModule;
import com.mycompany.swing.Table;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView.TableRow;

/**
 *
 * @author HUNG
 */
public class KhachThueController {
    private Account user ;
    private JTable jtbView;
    private JButton jbtnAdd, jbtnRemove;
    private JTextField jtfSearch;
    
    private KhachThueService khachThueService= null;
    private String[] listColumn  = {"ID", "Fullname","Số điện thoại", "Email", "ID Card", "BirthDay", "Update", "ID Account"};
     private TableRowSorter<TableModel> rowSorter = null;

    public KhachThueController(Account user, JTable jtbView, JButton jbtnAdd, JButton jbtnRemove, JTextField jtfSearch) {
        this.user = user;
        this.jtbView = jtbView;
        this.jbtnAdd = jbtnAdd;
        this.jbtnRemove = jbtnRemove;
        this.jtfSearch = jtfSearch;
        this.khachThueService = new KhachThueService();
    }
    
    public void setDataToTable()
    {
        List<KhachThue> listItem = khachThueService.getList(user);
        DefaultTableModel module = new ClassTableModule().setTableKhachThue(listItem, listColumn);
        jtbView.setModel(module);
        
        rowSorter = new TableRowSorter<>(jtbView.getModel());
        jtbView.setRowSorter(rowSorter);
        
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String  text = jtfSearch.getText();
                if(text.trim().length() == 0)
                {
                    rowSorter.setRowFilter(null);
                }else{
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+ text));
                }
            }   

            @Override
            public void removeUpdate(DocumentEvent e) {
                String  text = jtfSearch.getText();
                if(text.trim().length() == 0)
                {
                    rowSorter.setRowFilter(null);
                }else{
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)"+ text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        
//        jtbView.getColumnModel().getColumn(0).setMaxWidth(80);
//        jtbView.getColumnModel().getColumn(0).setMinWidth(80);
//        jtbView.getColumnModel().getColumn(0).setPreferredWidth(80);

        
        
//        JScrollPane scrollPane = new JScrollPane();
//        scrollPane.getViewport().add(jtbView);
        
    //        jtbView.removeAll();
    //        jtbView.setLayout(new BorderLayout());
    ////        jtbView.add(scrollPane);
    //        jtbView.validate();
    //        jtbView.repaint();
    }

}
