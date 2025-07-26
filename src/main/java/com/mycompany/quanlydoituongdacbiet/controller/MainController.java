/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.controller;
import com.mycompany.quanlydoituongdacbiet.view.JailView;
import com.mycompany.quanlydoituongdacbiet.view.LoginView;
import com.mycompany.quanlydoituongdacbiet.view.MainView;
import com.mycompany.quanlydoituongdacbiet.view.PrisonerView;
import com.mycompany.quanlydoituongdacbiet.view.VisitView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 *
 * @author PC
 */
public class MainController 
{
    private LoginView loginView;
    private PrisonerView prisonerView;
    private VisitView visitView;
    private JailView jailView;
    private MainView mainView;
    
    public MainController(MainView view)
    {
        this.mainView = view;
        view.addChoosePrisonerListener(new ChoosePrisonerListener());
        view.addChooseVisitListener(new ChooseVisitListener());
        view.addChooseJailListener(new ChooseJailListener());
    }
    public void showMainView() 
    {
        mainView.setVisible(true);
    }
    class ChoosePrisonerListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            prisonerView = new PrisonerView();
            PrisonerController prisonerController = new PrisonerController(prisonerView);
            prisonerController.showPrisonerView();
            mainView.setVisible(false);
        }
    }
    
    class ChooseVisitListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            visitView = new VisitView();
            VisitController visitController = new VisitController(visitView);
            visitController.showVisitView();
            mainView.setVisible(false);
        }
    }
    
    class ChooseJailListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            jailView = new JailView();
            JailController jailController = new JailController(jailView);
            jailController.showJailView();
            mainView.setVisible(false);
        }
    }
}
