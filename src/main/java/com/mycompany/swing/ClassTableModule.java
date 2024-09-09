package com.mycompany.swing;

import com.mycompany.model.DichVu;
import com.mycompany.model.KhachThue;
import java.util.List;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HUNG
 */
public class ClassTableModule {
    
    public DefaultTableModel setTableKhachThue(List<KhachThue> listItem, String[] listColumn)
    {
        DefaultTableModel dtm = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Vector vt = null;
        for(KhachThue item: listItem)
        {
            vt = new Vector();
            vt.add(item.getIdKhachThue()) ;
            vt.add(item.getFullname());
            vt.add(item.getNumberPhone());
            vt.add(item.getEmail());
            vt.add(item.getCmnd());
            vt.add(item.getNgaySinh());
            vt.add(item.getNgayCapNhat());
            vt.add(item.getIdAccount());
            dtm.addRow(vt);
        }           
        return dtm;
    }
    
    public DefaultTableModel setTableDichVu(List<DichVu> listItem, String[] listColumn)
    {
        DefaultTableModel dtm = new DefaultTableModel()
        {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Vector vt = null;
        for(DichVu item: listItem)
        {
            vt = new Vector();
            vt.add(item.getIdDichVu()) ;
            vt.add(item.getTenDichVu());
            vt.add(item.getGia());
            vt.add(item.getLoaiDichVu());
            dtm.addRow(vt);
        }           
        return dtm;
    }
}
