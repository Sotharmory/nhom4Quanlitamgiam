/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.controller;


import com.mycompany.quanlydoituongdacbiet.action.ManagerVisits;
import com.mycompany.quanlydoituongdacbiet.entity.Visit;
import com.mycompany.quanlydoituongdacbiet.view.MainView;
import com.mycompany.quanlydoituongdacbiet.view.VisitView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class VisitController {
    private VisitView view;
    private ManagerVisits manager;
    private MainView mainView;

    public VisitController(VisitView view) {
        this.view = view;
        this.manager = new ManagerVisits();

        initListeners();
        // Hiển thị danh sách ngay khi khởi động
        showVisitView();
    }

    private void initListeners() {
        //Listener cho các nút ở pannel 1 và 2
        view.addSearchListener(new ShowSearchDialogListener());
        view.addStatisticListener(new ShowStatisticViewListener());
        view.addUndoListener(new BackToMMListener());
        //CRUD
        view.addAddListener(new AddVisitListener());
        view.addEditListener(new EditVisitListener());
        view.addDeleteListener(new DeleteVisitListener());
        view.addClearListener(e -> view.clearInfo());
        //select
        view.addListSelectionListener(new TableSelectionListener());
        
        //search
        view.addSearchDiaLogListener(new SearchVisitListener());
        view.addCancelSearchListener(new CancelSearchListener());
        view.addCancelDialogListener(new HideSearchDialogListener());
        
        //sort
        view.addSortByIDListener(new SortByIDListener());
        view.addSortByVisitDateListener(new SortByVisitDateListener());
        //static
        view.addStatisticByMonthListener(new StatisticByMonthListener());
        view.addStatisticUnderListener(new HideStatisticViewListener());
    }
    
    public void showVisitView() {
        List<Visit> prisonerList = manager.getListVisits();
        view.setVisible(true);
        view.showListVisits(prisonerList);
    }
    // about Button
    class ShowSearchDialogListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            view.displaySearchView();
        }
    }
    
    class ShowStatisticViewListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            view.displayStatisticView();
        }
    }
    
    
    class BackToMMListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            mainView = new MainView();
            MainController mainController = new MainController(mainView);
            mainController.showMainView();
            view.setVisible(false);
        }
    }
    
    
    //about CRUD
    
    
    class AddVisitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Visit v = view.getVisitInfo();
            if (v != null) {
                manager.add(v);
                view.showListVisits(manager.getListVisits());
                view.showMessage("Thêm lượt thăm thành công");
            }
        }
    }

    class EditVisitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Visit v = view.getVisitInfo();
                if (v != null) {
                    manager.edit(v);
                    view.showListVisits(manager.getListVisits());
                    view.showMessage("Cập nhật thành công");
                }
            } catch (ParseException ex) {
                view.showMessage("Lỗi định dạng ngày: " + ex.getMessage());
            }
        }
    }

    class DeleteVisitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Visit v = view.getVisitInfo();
            if (v != null && manager.delete(v)) {
                view.showListVisits(manager.getListVisits());
                view.clearInfo();
                view.showMessage("Xóa thành công");
            } else {
                view.showMessage("Xóa không thành công");
            }
        }
    }

    class TableSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                try {
                    view.fillFromSelectedRow();
                } catch (ParseException ex) {
                    view.showMessage("Lỗi đọc dữ liệu: " + ex.getMessage());
                }
            }
        }
    }

    
    
       // About Search
    
    
     class SearchVisitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int type = view.getChooseSelectSearch();
            List<Visit> result;
            switch (type) {
                case 1:
                    result = manager.searchByPrisonerId(view.validateSearch());
                    break;
                case 2:
                    result = manager.searchByVisitorId(view.validateSearch());
                    break;
                case 3:
                    result = manager.searchByDate(view.validateSearchRange());
                    break;
                default:
                    result = manager.getListVisits();
            }
            view.showListVisits(result);
        }
    }


    class CancelSearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showListVisits(manager.getListVisits());
            view.cancelSearch();
        }
    }
    
    
    
    
    // about Sort
    
    
    class SortByIDListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            manager.sortById();
            view.showListVisits(manager.getListVisits());
        }
    }
    
    class SortByVisitDateListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            manager.sortByVisitDate();
            view.showListVisits(manager.getListVisits());
        }
    }
    
    
    
    
    // about Static
    
    
    
    
    class HideSearchDialogListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.hideSearchView();
        }
    }

    class StatisticByMonthListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showStatisticByMonth(manager.getListVisits());
        }
    }

    class HideStatisticViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.HideStatisticView();
        }
    }
}

