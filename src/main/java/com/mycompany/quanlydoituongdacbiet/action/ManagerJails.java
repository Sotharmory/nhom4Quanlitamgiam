/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.action;

import com.mycompany.quanlydoituongdacbiet.entity.Jail;
import com.mycompany.quanlydoituongdacbiet.entity.JailXML;
import com.mycompany.quanlydoituongdacbiet.entity.Prisoner;
import com.mycompany.quanlydoituongdacbiet.utils.FileUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
/**
 *
 * @author atsm1
 */
public class ManagerJails {
    private static final String Jail_FILE_NAME = "Jails.xml";
    private List<Jail> listJails;
    private ManagerPrisoners prisonerManager;

    public ManagerJails() {
        prisonerManager = new ManagerPrisoners();
        this.listJails = readListJails();
        if (this.listJails == null) {
            this.listJails = new ArrayList<>();
        }
        this.updateAllJailsCurrentInmates(prisonerManager.getListPrisoners());
    }

    @SuppressWarnings("unchecked")
    public List<Jail> readListJails() {
        JailXML wrapper = (JailXML) FileUtils.readXMLFile(Jail_FILE_NAME, JailXML.class);
        return (wrapper != null) ? wrapper.getJails() : new ArrayList<>();
    }

    private void writeListJails() {
        JailXML wrapper = new JailXML();
        wrapper.setJails(this.listJails);
        FileUtils.writeXMLtoFile(Jail_FILE_NAME, wrapper);
    }

    public void add(Jail jail) {
        int maxId = listJails.stream()
                              .mapToInt(Jail::getId)
                              .max()
                              .orElse(0);
        jail.setId(maxId + 1);
        jail.updateCurrentInmates(prisonerManager.getListPrisoners());
        listJails.add(jail);
        writeListJails();
    }

    public void edit(Jail jail) throws ParseException {
        for (int i = 0; i < listJails.size(); i++) {
            if (listJails.get(i).getId() == jail.getId()) {
                listJails.set(i, jail);
                break;
            }
        }
        writeListJails();
    }

    public boolean delete(Jail jail) {
        boolean removed = listJails.removeIf(v -> v.getId() == jail.getId());
        if (removed) {
            // update lại ID cho thứ tự
            Collections.sort(listJails, Comparator.comparingInt(Jail::getId)); 
            for (int i = 0; i < listJails.size(); i++) {
                listJails.get(i).setId(i + 1);
            }
            writeListJails();
        }
        return removed;
    }

    public List<Jail> getListJails() {
        return this.listJails;
    }

    public List<Jail> searchByJailId(String keyword) {
        String key = keyword.toLowerCase();
        List<Jail> res = new ArrayList<>();
        for (Jail v : listJails) {
            if (v.getJailId().toLowerCase().contains(key)) {
                res.add(v);
            }
        }
        return res;
    }

    public List<Jail> searchByMax(Integer[] range) {
        List<Jail> result = new ArrayList<>();
        if (range != null){
            int from = range[0];
            int to = range[1];
            for (Jail j : listJails) {
                int cap = j.getCapacity();
                if (cap>=from && cap<=to) {
                    result.add(j);
                }
            }
        }
        return result;
    }

    public List<Jail> searchByCurrent(Integer[] range) {
        List<Jail> result = new ArrayList<>();
        if (range != null){
            int from = range[0];
            int to = range[1];
            for (Jail j : listJails) {
                int cur = j.getCurrentInmates();
                if (cur>=from && cur<=to) {
                    result.add(j);
                }
            }
        }
        return result;
    }

    public void sortById() {
        Collections.sort(listJails, Comparator.comparingInt(Jail::getId));
    }

    public void sortByCurrent() {
        Collections.sort(listJails, Comparator.comparingInt(Jail::getCurrentInmates));
    }
    
    public void sortByMax() {
        Collections.sort(listJails, Comparator.comparingInt(Jail::getCapacity));
    }
    
    public void updateAllJailsCurrentInmates(List<Prisoner> prisoners) {
        for (Jail jail : listJails) {
            jail.updateCurrentInmates(prisoners);
        }
    }
}
