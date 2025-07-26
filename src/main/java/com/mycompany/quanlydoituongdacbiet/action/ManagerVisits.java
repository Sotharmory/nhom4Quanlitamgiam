package com.mycompany.quanlydoituongdacbiet.action;

import com.mycompany.quanlydoituongdacbiet.entity.Visit;
import com.mycompany.quanlydoituongdacbiet.entity.VisitXML;
import com.mycompany.quanlydoituongdacbiet.utils.FileUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ManagerVisits {
    private static final String VISIT_FILE_NAME = "Visits.xml";
    private List<Visit> listVisits;
    private SimpleDateFormat fDate = new SimpleDateFormat("dd/MM/yyyy");

    public ManagerVisits() {
        this.listVisits = readListVisits();
        if (this.listVisits == null) {
            this.listVisits = new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Visit> readListVisits() {
        VisitXML wrapper = (VisitXML) FileUtils.readXMLFile(VISIT_FILE_NAME, VisitXML.class);
        return (wrapper != null) ? wrapper.getVisits() : new ArrayList<>();
    }

    private void writeListVisits() {
        VisitXML wrapper = new VisitXML();
        wrapper.setVisits(this.listVisits);
        FileUtils.writeXMLtoFile(VISIT_FILE_NAME, wrapper);
    }

    public void add(Visit visit) {
        // Tự động tăng ID
        int maxId = listVisits.stream()
                              .mapToInt(Visit::getId)
                              .max()
                              .orElse(0);
        visit.setId(maxId + 1);
        listVisits.add(visit);
        writeListVisits();
    }

    public void edit(Visit visit) throws ParseException {
        for (int i = 0; i < listVisits.size(); i++) {
            if (listVisits.get(i).getId() == visit.getId()) {
                listVisits.set(i, visit);
                break;
            }
        }
        writeListVisits();
    }

    public boolean delete(Visit visit) {
        boolean removed = listVisits.removeIf(v -> v.getId() == visit.getId());
        if (removed) {
            // update lại ID cho thứ tự
            Collections.sort(listVisits, Comparator.comparingInt(Visit::getId));
            for (int i = 0; i < listVisits.size(); i++) {
                listVisits.get(i).setId(i + 1);
            }
            writeListVisits();
        }
        return removed;
    }

    public List<Visit> getListVisits() {
        return this.listVisits;
    }

    public List<Visit> searchByVisitorId(String keyword) {
        String key = keyword.toLowerCase();
        List<Visit> res = new ArrayList<>();
        for (Visit v : listVisits) {
            if (v.getVisitorId().toLowerCase().contains(key)) {
                res.add(v);
            }
        }
        return res;
    }

    public List<Visit> searchByPrisonerId(String pid) {
        String key = pid.toLowerCase();
        List<Visit> res = new ArrayList<>();
        for (Visit v : listVisits) {
            if (v.getPrisonerId().toLowerCase().contains(key)) {
                res.add(v);
            }
        }
        return res;
    }

    public List<Visit> searchByDate(Date[] range) {
        List<Visit> result = new ArrayList<>();
        if (range != null){
            Date from = range[0];
            Date to = range[1];
            for (Visit v : listVisits) {
                Date d = v.getVisitDate();
                if (!d.before(from) && !d.after(to)) {
                    result.add(v);
                }
            }
        }
        return result;
    }

    public void sortById() {
        Collections.sort(listVisits, Comparator.comparingInt(Visit::getId));
    }

    public void sortByVisitDate() {
        Collections.sort(listVisits, Comparator.comparing(Visit::getVisitDate));
    }
}
