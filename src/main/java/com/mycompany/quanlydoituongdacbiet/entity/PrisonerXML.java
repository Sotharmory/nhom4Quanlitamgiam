package com.mycompany.quanlydoituongdacbiet.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Prisoners")
@XmlAccessorType(XmlAccessType.FIELD)
public class PrisonerXML {
    private List<Prisoner> prisoners;

    public PrisonerXML() {
        this.prisoners = new java.util.ArrayList<>();  
    }
    
    public List<Prisoner> getPrisoners() {
        return prisoners;
    }

    public void setPrisoners(List<Prisoner> prisoners) {
        this.prisoners = prisoners;
    }
} 