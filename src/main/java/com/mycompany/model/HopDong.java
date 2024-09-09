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
public class HopDong {
    
    private String idHopDong;
    private Date effectiveFrom, effectiveTo;
    private boolean trangThaiThue;
    private float tienCoc;
    private String idKhachThue, idPhong;

    public HopDong() {
    }

    public HopDong(String idHopDong, Date effectiveFrom, Date effectiveTo, boolean trangThaiThue, float tienCoc, String idKhachThue, String idPhong) {
        this.idHopDong = idHopDong;
        this.effectiveFrom = effectiveFrom;
        this.effectiveTo = effectiveTo;
        this.trangThaiThue = trangThaiThue;
        this.tienCoc = tienCoc;
        this.idKhachThue = idKhachThue;
        this.idPhong = idPhong;
    }

    public String getIdHopDong() {
        return idHopDong;
    }

    public void setIdHopDong(String idHopDong) {
        this.idHopDong = idHopDong;
    }

    public Date getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(Date effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public Date getEffectiveTo() {
        return effectiveTo;
    }

    public void setEffectiveTo(Date effectiveTo) {
        this.effectiveTo = effectiveTo;
    }

    public boolean getTrangThaiThue() {
        return trangThaiThue;
    }

    public void setTrangThaiThue(boolean trangThaiThue) {
        this.trangThaiThue = trangThaiThue;
    }

    public float getTienCoc() {
        return tienCoc;
    }

    public void setTienCoc(float tienCoc) {
        this.tienCoc = tienCoc;
    }

    public String getIdKhachThue() {
        return idKhachThue;
    }

    public void setIdKhachThue(String idKhachThue) {
        this.idKhachThue = idKhachThue;
    }

    public String getIdPhong() {
        return idPhong;
    }

    public void setIdPhong(String idPhong) {
        this.idPhong = idPhong;
    }
    
    
    
    
}
