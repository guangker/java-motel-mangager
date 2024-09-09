package com.mycompany.service;

import com.mycompany.model.Account;
import com.mycompany.model.DichVu;
import java.util.List;
import ketNoiDB.DichVuDAO;

public class DichVuService {
    private DichVuDAO dichvuDAO = new DichVuDAO();
    
    public List<DichVu> getList(Account user)
    {
        return dichvuDAO.getList(user);
    }
            
    public int createOrUpdate(DichVu dichvu)
    {
        return dichvuDAO.createOrUpdate(dichvu);
    }
    
    public int deleteForID(String id)
    {
        return dichvuDAO.deleteForID(id);
    }
    
    public List<DichVu> getListForIDNhaTro (String idNhaTro)
    {
        return dichvuDAO.getListForIDNhaTro(idNhaTro);
    }
    
    
}
