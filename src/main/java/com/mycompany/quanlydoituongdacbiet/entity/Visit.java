package com.mycompany.quanlydoituongdacbiet.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Visit")
@XmlAccessorType(XmlAccessType.FIELD)
public class Visit {
    private int id;
    private String prisonerId;
    private String visitorName;
    private String relationship; // Family, Legal, Other
    private String visitorId; // ID card/passport number
    private String visitorPhone;
    private Date visitDate;
    private String visitTimeSlot; // Time slot
    private String visitPurpose;
    private String status; // Scheduled, Completed, Cancelled
    
    public Visit() {
    }
    
    public Visit(int id, String prisonerId, String visitorName, String relationship,
            String visitorId, String visitorPhone, Date visitDate, String visitTimeSlot,
            String visitPurpose, String status, String notes) {
        this.id = id;
        this.prisonerId = prisonerId;
        this.visitorName = visitorName;
        this.relationship = relationship;
        this.visitorId = visitorId;
        this.visitorPhone = visitorPhone;
        this.visitDate = visitDate;
        this.visitTimeSlot = visitTimeSlot;
        this.visitPurpose = visitPurpose;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrisonerId() {
        return prisonerId;
    }

    public void setPrisoner(String prisonerId) {
        this.prisonerId = prisonerId;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public void setVisitorName(String visitorName) {
        this.visitorName = visitorName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(String visitorId) {
        this.visitorId = visitorId;
    }

    public String getVisitorPhone() {
        return visitorPhone;
    }

    public void setVisitorPhone(String visitorPhone) {
        this.visitorPhone = visitorPhone;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getVisitTimeSlot() {
        return visitTimeSlot;
    }

    public void setVisitTimeSlot(String visitTime) {
        this.visitTimeSlot = visitTime;
    }

    public String getVisitPurpose() {
        return visitPurpose;
    }

    public void setVisitPurpose(String visitPurpose) {
        this.visitPurpose = visitPurpose;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
} 