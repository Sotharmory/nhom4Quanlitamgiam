package com.mycompany.quanlydoituongdacbiet.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.List;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.mycompany.quanlydoituongdacbiet.action.ManagerPrisoners;
import com.mycompany.quanlydoituongdacbiet.entity.Prisoner;
import com.mycompany.quanlydoituongdacbiet.view.MainView;
import com.mycompany.quanlydoituongdacbiet.view.PrisonerView;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrisonerController {
    private PrisonerView prisonerView;
    private ManagerPrisoners managerPrisoners;
    private MainView mainView;

    public PrisonerController(PrisonerView view) {
        this.prisonerView = view;
        this.managerPrisoners = new ManagerPrisoners();

        // Khởi tạo các listener
        initListeners();
        
        // Hiển thị dữ liệu ban đầu
        showPrisonerView();
    }

    private void initListeners() {
        // Listener cho chọn hàng trong bảng
        prisonerView.addListSelectionListener(new ListPrisonerSelectionListener());
        //Listener cho các nút ở pannel 1 và 2
        prisonerView.addImageListener(new ImageAddListener());
        prisonerView.addSearchListener(new ShowSearchDialogListener());
        prisonerView.addStatisticListener(new ShowStatisticViewListener());
        prisonerView.addUndoListener(new BackToMMListener());
        
        
        // Listener cho các nút chức năng
        prisonerView.addAddListener(new AddPrisonerListener());
        prisonerView.addEditListener(new EditPrisonerListener());
        prisonerView.addDeleteListener(new DeletePrisonerListener());
        prisonerView.addClearListener(new ClearPrisonerListener());
        
        // Listener cho tìm kiếm
        prisonerView.addSearchDiaLogListener(new SearchPrisonerListener());
        prisonerView.addCancelSearchListener(new CancelSearchListener());
        prisonerView.addCancelDialogListener(new HideSearchDialogListener());
        
        // Listener cho sắp xếp
        prisonerView.addSortByYearListener(new SortByYearListener());
        prisonerView.addSortByNameListener(new SortByNameListener());
        prisonerView.addSortByIDListener(new SortByPrisonerIdListener());
        prisonerView.addSortByInDateListener(new SortBySentenceStartListener());
        
        // Listener cho thống kê
        prisonerView.addStatisticUnderListener(new HideStatisticViewListener());
        prisonerView.addStatisticBehaviorListener(new StatisticBehaviorListener());
        prisonerView.addStatisticTimeListener(new StatisticSentenceListener());
    }

    public void showPrisonerView() {
        List<Prisoner> prisonerList = managerPrisoners.getListPrisoners();
        prisonerView.setVisible(true);
        prisonerView.showListPrisoners(prisonerList);
    }
    // button
    class ShowSearchDialogListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            prisonerView.displaySearchView();
        }
    }
    
    class ShowStatisticViewListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            prisonerView.displayStatisticView();
        }
    }
    
    class ImageAddListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            prisonerView.PrisonerImage();
        }
    }
    
    class BackToMMListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            mainView = new MainView();
            MainController mainController = new MainController(mainView);
            mainController.showMainView();
            prisonerView.setVisible(false);
        }
    }
    
    class ListPrisonerSelectionListener implements ListSelectionListener 
    {
        @Override
        public void valueChanged(ListSelectionEvent e) 
        {
            try {
                prisonerView.fillPrisonerFromSelectedRow();
            } catch (ParseException ex) {
                Logger.getLogger(PrisonerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
    
    // CRUD
    class AddPrisonerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Prisoner prisoner = prisonerView.getPrisonerInfo();
            if (prisoner != null) {
                if (managerPrisonerExists(prisoner.getPrisonerId())) {
                    prisonerView.showMessage("Mã phạm nhân đã tồn tại!");
                    return;
                }
                managerPrisoners.add(prisoner);
                prisonerView.showListPrisoners(managerPrisoners.getListPrisoners());
                prisonerView.showMessage("Thêm phạm nhân thành công!");
                prisonerView.clearPrisonerInfo();
            }
        }
        
        private boolean managerPrisonerExists(String prisonerId) {
            return managerPrisoners.getListPrisoners().stream()
                    .anyMatch(p -> p.getPrisonerId().equals(prisonerId));
        }
    }

    class EditPrisonerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Prisoner prisoner = prisonerView.getPrisonerInfo();
            if (prisoner != null) {
                try {
                    managerPrisoners.edit(prisoner);
                    prisonerView.showListPrisoners(managerPrisoners.getListPrisoners());
                    prisonerView.showMessage("Cập nhật thông tin thành công!");
                } catch (Exception ex) {
                    prisonerView.showMessage("Lỗi: " + ex.getMessage());
                }
            }
        }
    }

    class DeletePrisonerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Prisoner prisoner = prisonerView.getPrisonerInfo();
            managerPrisoners.delete(prisoner);
            prisonerView.showListPrisoners(managerPrisoners.getListPrisoners());
            prisonerView.showMessage("Xóa phạm nhân thành công!");
            prisonerView.clearPrisonerInfo();

        }
    }

    class ClearPrisonerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            prisonerView.clearPrisonerInfo();
        }
    }

    
    
    // About Search
    
    
    class SearchPrisonerListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String keyword = prisonerView.validateSearch();
            int searchType = prisonerView.getChooseSelectSearch();
            
            List<Prisoner> results;
            switch (searchType) {
                case 1: // Tìm theo tên
                    results = managerPrisoners.searchByName(keyword);
                    break;
                case 2: // Tìm theo mã phạm nhân
                    results = managerPrisoners.searchById(keyword);
                    break;
                case 3: // Tìm theo mã nhà tù
                    results = managerPrisoners.searchByOutYear(keyword);
                    break;
                default:
                    results = managerPrisoners.getListPrisoners();
                    break;
            }
            
            prisonerView.showListPrisoners(results);
        }
    }

    class CancelSearchListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            prisonerView.showListPrisoners(managerPrisoners.getListPrisoners());
            prisonerView.cancelSearch();
        }
    }
    
    class HideSearchDialogListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            prisonerView.hideSearchView();
        }
    }
    
    

    // About sort
    
    
    class SortByNameListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            managerPrisoners.sortByName();
            prisonerView.showListPrisoners(managerPrisoners.getListPrisoners());
        }
    }

    class SortByPrisonerIdListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            managerPrisoners.sortById();
            prisonerView.showListPrisoners(managerPrisoners.getListPrisoners());
        }
    }

    class SortBySentenceStartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            managerPrisoners.sortBySentenceStartDate();
            prisonerView.showListPrisoners(managerPrisoners.getListPrisoners());
        }
    }
    class SortByYearListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            managerPrisoners.sortByBirth();
            prisonerView.showListPrisoners(managerPrisoners.getListPrisoners());
        }
    }


    
    
    // About Static
    class HideStatisticViewListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            prisonerView.HideStatisticView();
        }
    }
    
    class StatisticBehaviorListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Prisoner> prisoners = managerPrisoners.getListPrisoners();
            prisonerView.showStatisticBehaviorPrisoners(prisoners);
        }
    }

    class StatisticSentenceListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Prisoner> prisoners = managerPrisoners.getListPrisoners();
            prisonerView.showStatisticSentenceDuration(prisoners);
        }
    }
}