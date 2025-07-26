package com.mycompany.quanlydoituongdacbiet.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Visits")
@XmlAccessorType(XmlAccessType.FIELD)
public class VisitXML {
    private List<Visit> visits;
    
    public VisitXML() {
        this.visits = new java.util.ArrayList<>();  
    }
    
    public List<Visit> getVisits() {
        return visits;
    }

    public void setVisits(List<Visit> visits) {
        this.visits = visits;
    }
} 