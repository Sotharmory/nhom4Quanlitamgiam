package com.mycompany.quanlydoituongdacbiet.action;

import com.mycompany.quanlydoituongdacbiet.entity.Prisoner;
import com.mycompany.quanlydoituongdacbiet.entity.PrisonerXML;
import com.mycompany.quanlydoituongdacbiet.utils.FileUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.text.Collator;

public class ManagerPrisoners {
    private static final String PRISONER_FILE_NAME = "Prisoners.xml";
    private List<Prisoner> listPrisoners;
    
    public ManagerPrisoners() {
        this.listPrisoners = readListPrisoners();
        if (listPrisoners == null) {
            listPrisoners = new ArrayList<Prisoner>();
        }
    }
    
    public List<Prisoner> readListPrisoners() {
        List<Prisoner> list = new ArrayList<Prisoner>();
        PrisonerXML prisonerXML = (PrisonerXML) FileUtils.readXMLFile(
                PRISONER_FILE_NAME, PrisonerXML.class);
        if (prisonerXML != null) {
            list = prisonerXML.getPrisoners();
        }
        return list;
    }
    
    public void writeListPrisoners(List<Prisoner> prisoners) {
        PrisonerXML prisonerXML = new PrisonerXML();
        prisonerXML.setPrisoners(prisoners);
        FileUtils.writeXMLtoFile(PRISONER_FILE_NAME, prisonerXML);
    }
    
    // Search functions
    public List<Prisoner> searchByName(String search) {
        List<Prisoner> result = new ArrayList<Prisoner>();
        for (Prisoner prisoner : listPrisoners) {
            if (prisoner.getName().toLowerCase().contains(search.toLowerCase())) {
                result.add(prisoner);
            }
        }
        return result;
    }
    
    public List<Prisoner> searchById(String search) {
        List<Prisoner> result = new ArrayList<Prisoner>();
        for (Prisoner prisoner : listPrisoners) {
            if (prisoner.getPrisonerId().contains(search)) {
                result.add(prisoner);
            }
        }
        return result;
    }
    
    public List<Prisoner> searchByOutYear(String search) {
        List<Prisoner> result = new ArrayList<Prisoner>();
        for (Prisoner prisoner : listPrisoners) {
            String endYearStr = String.valueOf(prisoner.getSentenceEndDate().getYear() + 1900);
            if (endYearStr.toLowerCase().contains(search.toLowerCase())){
                result.add(prisoner);
            }
        }
        return result;
    }
    
    // CRUD operations
    public void add(Prisoner prisoner) {
        int max = 0;
        for (Prisoner p : listPrisoners) {
            if (p.getId() > max) max = p.getId();
        }
        prisoner.setId(max + 1);
        listPrisoners.add(prisoner);
        writeListPrisoners(listPrisoners);
    }
    
    public void edit(Prisoner prisoner) throws ParseException {
        for (int i = 0; i < listPrisoners.size(); i++) {
            if (listPrisoners.get(i).getId() == prisoner.getId()) {
                listPrisoners.set(i, prisoner);
                break;
            }
        }
        writeListPrisoners(listPrisoners);
    }
    
    public boolean delete(Prisoner prisoner) {
        boolean isFound = false;
        int size = listPrisoners.size();
        for (int i = 0; i < size; i++) {
            if (listPrisoners.get(i).getId() == prisoner.getId()) {
                listPrisoners.remove(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            // Update IDs of remaining prisoners
            for (int i = 0; i < listPrisoners.size(); i++) {
                if (listPrisoners.get(i).getId() > prisoner.getId()) {
                    listPrisoners.get(i).setId(listPrisoners.get(i).getId() - 1);
                }
            }
            writeListPrisoners(listPrisoners);
            return true;
        }
        return false;
    }
    
    // Sorting functions
    public void sortByName() {
        Collections.sort(listPrisoners, new Comparator<Prisoner>() {
            public int compare(Prisoner p1, Prisoner p2) {
                Collator collator = Collator.getInstance(new Locale("vi", "VN"));
                return collator.compare(p1.getName(), p2.getName());
            }
        });
    }
    
    public void sortById() {
        Collections.sort(listPrisoners, new Comparator<Prisoner>() {
            public int compare(Prisoner p1, Prisoner p2) {
                return p1.getPrisonerId().compareTo(p2.getPrisonerId());
            }
        });
    }
    public void sortBySentenceStartDate() {
        Collections.sort(listPrisoners, new Comparator<Prisoner>() {
            public int compare(Prisoner p1, Prisoner p2) {
                return p1.getSentenceStartDate().compareTo(p2.getSentenceStartDate());
            }
        });
    }
    public void sortByBirth() {
        Collections.sort(listPrisoners, new Comparator<Prisoner>() {
            public int compare(Prisoner p1, Prisoner p2) {
                return p1.getBirthday().compareTo(p2.getBirthday());
            }
        });
    }
    // Statistics functions
    public int getTotalPrisoners() {
        return listPrisoners.size();
    }
    
    
    public List<Prisoner> getListPrisoners() {
        return this.listPrisoners;
    }
} 
