/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.model.Account;
import com.mycompany.model.NhaTro;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author HUNG
 */
public class NhaTroService {
    private NhaTroDAO nhatroDAO = new NhaTroDAO();
    
    public List<String> getNameList(Account user)
    {
        return nhatroDAO.getNameList(user);
    }
    
    
    public List<NhaTro> getList(Account user)
    {
        return nhatroDAO.getList(user);
    }
    
    public HashMap<String,String> getIdAndName(Account user)
    {
        return nhatroDAO.getIdAndName(user);
    }
}
