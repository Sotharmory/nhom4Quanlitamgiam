/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.entity;

import java.util.List;

/**
 *
 * @author atsm1
 */
public class Jail {
    private int id;
    private String jailId;
    private String name;
    private String location;
    private int capacity;
    private int currentInmates; 

    public Jail() {
    }

    public Jail(int id ,String jailId, String name, String location, int capacity) {
        this.id = id;
        this.jailId = jailId;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
        this.currentInmates = 0; 
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getJailId() {
        return jailId;
    }

    public void setJailId(String jailId) {
        this.jailId = jailId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentInmates() {
        return currentInmates;
    }

    public void setCurrentInmates(int currentInmates) {
        this.currentInmates = currentInmates;
    }

    public void incrementInmates() {
        this.currentInmates++;
    }

    public void decrementInmates() {
        if (currentInmates > 0)
            this.currentInmates--;
    }

    public boolean isOverCapacity() {
        return currentInmates > capacity;
    }
    
    public void updateCurrentInmates(List<Prisoner> prisoners) {
    this.currentInmates = (int) prisoners.stream()
        .filter(p -> p.getJailId().equals(this.jailId))
        .count();
    }
}
