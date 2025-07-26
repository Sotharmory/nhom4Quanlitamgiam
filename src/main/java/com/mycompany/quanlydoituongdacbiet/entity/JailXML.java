/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.entity;


import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Jails")
@XmlAccessorType(XmlAccessType.FIELD)
public class JailXML {
    private List<Jail> jails;
    
    public JailXML() {
        this.jails = new java.util.ArrayList<>();  
    }
    
    public List<Jail> getJails() {
        return jails;
    }

    public void setJails(List<Jail> jails) {
        this.jails = jails;
    }
} 
