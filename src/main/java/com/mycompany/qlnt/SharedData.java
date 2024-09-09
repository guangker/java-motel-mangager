package com.mycompany.qlnt;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SharedData {
    private static SharedData instance;
    private String idNT;
    private String tenTro;
    private int soPhongTro;
    private String idQL;
    private String idPhong;
    private String idRole;
    private String idAccount;
    private String idp;
    private String td;
    private String tn;

    public String getTn() {
        return tn;
    }

    public void setTn(String tn) {
        this.tn = tn;
    }

    public String getTd() {
        return td;
    }

    public void setTd(String td) {
        this.td = td;
    }

    public String getIdp() {
        return idp;
    }

    public void setIdp(String idp) {
        this.idp = idp;
    }

    private SharedData() {}

    public static SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

   

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    } 
    public String getIdAccount() {
        return idAccount;
    }
    public String getIdNT() {
        return idNT;
    }

    public void setIdNT(String idNT) {
        this.idNT = idNT;
    }
    
    public String getTenTro(){
        return tenTro;
    }
    
    public void setTenTro(String tenTro){
        this.tenTro = tenTro;
    }
    
    public int getSoPhongTro() {
        return soPhongTro;
    }

    public void setSoPhongTro(int soPhongTro) {
        this.soPhongTro = soPhongTro;
    }
    
    public String getIdQL() {
        return idQL;
    }

    public void setIdQL(String idQL) {
        this.idQL = idQL;
    }
    
    public String getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }

    public String getIdRole() {
        return idRole;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }
    
    
    
    public void updateDB(){
        try {
            Connection conn = ketNoiDB.ketNoiDB.layKetNoi();
            String update = "UPDATE Phong\n" +
            "SET Phong.ghichu = LoaiPhong.ghichu\n" +
            "FROM Phong\n" +
            "JOIN LoaiPhong ON Phong.idLoaiPhong = LoaiPhong.idLoaiPhong;";
            PreparedStatement ps = conn.prepareStatement(update);
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}