/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.model;

import java.sql.Date;

/**
 *
 * @author HUNG
 */
public class CT_DichVu {
    private String idCT_DichVu;
    private Date ngayThue;
    private int soMoi, soLuong;
    private String idHoaDon, idDichVu;

    public CT_DichVu() {
    }

    public CT_DichVu(String idCT_DichVu, Date ngayThue, int soMoi, int soLuong, String idHoaDon, String idDichVu) {
        this.idCT_DichVu = idCT_DichVu;
        this.ngayThue = ngayThue;
        this.soMoi = soMoi;
        this.soLuong = soLuong;
        this.idHoaDon = idHoaDon;
        this.idDichVu = idDichVu;
    }

    public String getIdCT_DichVu() {
        return idCT_DichVu;
    }

    public void setIdCT_DichVu(String idCT_DichVu) {
        this.idCT_DichVu = idCT_DichVu;
    }

    public Date getNgayThue() {
        return ngayThue;
    }

    public void setNgayThue(Date ngayThue) {
        this.ngayThue = ngayThue;
    }

    public int getSoMoi() {
        return soMoi;
    }

    public void setSoMoi(int soMoi) {
        this.soMoi = soMoi;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(String idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public String getIdDichVu() {
        return idDichVu;
    }

    public void setIdDichVu(String idDichVu) {
        this.idDichVu = idDichVu;
    }
    
    
    
}
