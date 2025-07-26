/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.controller;
import com.mycompany.quanlydoituongdacbiet.action.ManagerJails;
import com.mycompany.quanlydoituongdacbiet.entity.Jail;
import com.mycompany.quanlydoituongdacbiet.view.MainView;
import com.mycompany.quanlydoituongdacbiet.view.JailView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
/**
 *
 * @author atsm1
 */
public class JailController {
    private JailView view;
    private ManagerJails manager;
    private MainView mainView;
    
    public JailController(JailView view) {
        this.view = view;
        this.manager = new ManagerJails();

        initListeners();
        // Hiển thị danh sách ngay khi khởi động
        showJailView();
    }
    
    private void initListeners() {
        //Listener cho các nút ở pannel 1 và 2
        view.addSearchListener(new ShowSearchDialogListener());
        view.addStatisticListener(new ShowStatisticViewListener());
        view.addUndoListener(new BackToMMListener());
        //CRUD
        view.addAddListener(new AddJailListener());
        view.addEditListener(new EditJailListener());
        view.addDeleteListener(new DeleteJailListener());
        view.addClearListener(e -> view.clearInfo());
        //select
        view.addListSelectionListener(new TableSelectionListener());
        
        //search
        view.addSearchDiaLogListener(new SearchJailListener());
        view.addCancelSearchListener(new CancelSearchListener());
        view.addCancelDialogListener(new HideSearchDialogListener());
        
        //sort
        view.addSortByIDListener(new SortByIDListener());
        view.addSortByMaxListener(new SortByMaxListener());
        view.addSortByCurrentListener(new SortByCurrentListener());
        //static
        view.addStatisticByDistributionListener(new StatisticByDistributionListener());
        view.addStatisticUnderListener(new HideStatisticViewListener());
    }
        
    public void showJailView() {
        List<Jail> jailList = manager.getListJails();
        view.setVisible(true);
        view.showListJails(jailList);
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
    
        
    
    class AddJailListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) { 
            Jail j = view.getJailInfo();
            if (j != null) {
                if (managerJailExists(j.getJailId())) {
                    view.showMessage("Mã nhà tù đã tồn tại!");
                    return;
                }
                manager.add(j);
                view.showListJails(manager.getListJails());
                view.showMessage("Thêm nhà tù thành công");
            }
        }
        private boolean managerJailExists(String jailId) {
            return manager.getListJails().stream()
                    .anyMatch(j -> j.getJailId().equals(jailId));
        }
    }

    class EditJailListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Jail j = view.getJailInfo();
                if (j != null) {
                    manager.edit(j);
                    view.showListJails(manager.getListJails());
                    view.showMessage("Cập nhật thành công");
                }
            } catch (ParseException ex) {
                view.showMessage("Lỗi định dạng ngày: " + ex.getMessage());
            }
        }
    }

    class DeleteJailListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Jail j = view.getJailInfo();
            if (j != null && manager.delete(j)) {
                view.showListJails(manager.getListJails());
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
    
    
       
    class SearchJailListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int type = view.getChooseSelectSearch();
            List<Jail> result;
            switch (type) {
                case 1:
                    result = manager.searchByJailId(view.validateSearch());
                    break;
                case 2:
                    result = manager.searchByMax(view.validateSearchRange());
                    break;
                case 3:
                    result = manager.searchByCurrent(view.validateSearchRange());
                    break;
                default:
                    result = manager.getListJails();
            }
            view.showListJails(result);
        }
    }


    class CancelSearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showListJails(manager.getListJails());
            view.cancelSearch();
        }
    }
    
    
    
    
    // about Sort
    
    
    class SortByIDListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            manager.sortById();
            view.showListJails(manager.getListJails());
        }
    }
    
    class SortByMaxListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            manager.sortByMax();
            view.showListJails(manager.getListJails());
        }
    }
        
    class SortByCurrentListener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            manager.sortByCurrent();
            view.showListJails(manager.getListJails());
        }
    }
    
    
    
    // about Static
    
    
    
    
    class HideSearchDialogListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.hideSearchView();
        }
    }

    class StatisticByDistributionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.showStatisticByDistribution(manager.getListJails());
        }
    }

    class HideStatisticViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.HideStatisticView();
        }
    }

}
