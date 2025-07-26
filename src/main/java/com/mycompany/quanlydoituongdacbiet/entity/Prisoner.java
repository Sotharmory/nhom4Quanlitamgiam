package com.mycompany.quanlydoituongdacbiet.entity;

import java.text.DecimalFormat;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Prisoner")
@XmlAccessorType(XmlAccessType.FIELD)
public class Prisoner extends Person {
    private String jailId; 
    private String prisonerId; 
    private String crime; 
    private Date sentenceStartDate;
    private Date sentenceEndDate;
    private String behavior; 
    private byte[] picture;
    
    public Prisoner() {
        super();
    }
    
    public Prisoner(int id, String name, Date birthday, String address, String jailId,
            String prisonerId, String crime, Date sentenceStartDate, Date sentenceEndDate,
            String cellBlock, String cellNumber, String behavior,
             String status,  byte[] image) {
        super();
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
        this.prisonerId = prisonerId;
        this.jailId= jailId;
        this.crime = crime;
        this.sentenceStartDate = sentenceStartDate;
        this.sentenceEndDate = sentenceEndDate;
        this.behavior = behavior;
        this.picture=image;
    }

    // Getters and Setters
    public String getPrisonerId() {
        return prisonerId;
    }

    public void setPrisonerId(String prisonerId) {
        this.prisonerId = prisonerId;
    }
    public String getJailId() {
        return jailId;
    }

    public void setJailId(String jailId) {
        this.jailId = jailId;
    }
    public String getCrime() {
        return crime;
    }

    public void setCrime(String crime) {
        this.crime = crime;
    }

    public Date getSentenceStartDate() {
        return sentenceStartDate;
    }

    public void setSentenceStartDate(Date sentenceStartDate) {
        this.sentenceStartDate = sentenceStartDate;
    }

    public Date getSentenceEndDate() {
        return sentenceEndDate;
    }

    public void setSentenceEndDate(Date sentenceEndDate) {
        this.sentenceEndDate = sentenceEndDate;
    }

    public String getCellBlock() {
        return jailId;
    }

    public void setCellBlock(String jailId) {
        this.jailId = jailId;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }
    
    public void setImage(byte[] image)
    {
        this.picture=image;
    }
    public byte[] getImage()
    {
        return picture;
    }
    @Override
    public String toString(){
        return prisonerId+"-"+name;
    }
} 