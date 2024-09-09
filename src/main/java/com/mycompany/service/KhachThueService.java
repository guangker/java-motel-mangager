/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.service;

import com.mycompany.model.Account;
import com.mycompany.model.KhachThue;
import java.util.List;
import java.util.Vector;
import ketNoiDB.KhachThueDAO;

/**
 *
 * @author HUNG
 */
public class KhachThueService {

    private KhachThueDAO khachthueDAO = new KhachThueDAO();

    public List<KhachThue> getList(Account user) {
        return khachthueDAO.getList(user);
    }

//    public static void main(String[] args) {
//        KhachThueService kt = new KhachThueService();
//        System.out.println(kt.getList());
//    }
}
