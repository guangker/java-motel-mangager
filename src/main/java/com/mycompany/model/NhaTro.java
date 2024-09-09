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
public class NhaTro {
    private String idNhaTro, tenNhaTro, diaChi;
    private int soPhong, soPhongTrong;
    private Date ngayCapNhat;
    private String idQL;

    public NhaTro() {
    }

    public NhaTro(String idNhaTro, String tenNhaTro, String diaChi, int soPhong, int soPhongTrong, Date ngayCapNhat, String idQL) {
        this.idNhaTro = idNhaTro;
        this.tenNhaTro = tenNhaTro;
        this.diaChi = diaChi;
        this.soPhong = soPhong;
        this.soPhongTrong = soPhongTrong;
        this.ngayCapNhat = ngayCapNhat;
        this.idQL = idQL;
    }

    public String getIdNhaTro() {
        return idNhaTro;
    }

    public void setIdNhaTro(String idNhaTro) {
        this.idNhaTro = idNhaTro;
    }

    public String getTenNhaTro() {
        return tenNhaTro;
    }

    public void setTenNhaTro(String tenNhaTro) {
        this.tenNhaTro = tenNhaTro;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getSoPhong() {
        return soPhong;
    }

    public void setSoPhong(int soPhong) {
        this.soPhong = soPhong;
    }

    public int getSoPhongTrong() {
        return soPhongTrong;
    }

    public void setSoPhongTrong(int soPhongTrong) {
        this.soPhongTrong = soPhongTrong;
    }

    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getIdQL() {
        return idQL;
    }

    public void setIdQL(String idQL) {
        this.idQL = idQL;
    }

    @Override
    public String toString() {
        return "NhaTro{" + "idNhaTro=" + idNhaTro + ", tenNhaTro=" + tenNhaTro + ", diaChi=" + diaChi + ", soPhong=" + soPhong + ", soPhongTrong=" + soPhongTrong + ", ngayCapNhat=" + ngayCapNhat + ", idQL=" + idQL + '}';
    }
    
    
}
