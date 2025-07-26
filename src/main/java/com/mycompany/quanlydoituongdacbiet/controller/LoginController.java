/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.controller;

import com.mycompany.quanlydoituongdacbiet.action.CheckLogin;
import com.mycompany.quanlydoituongdacbiet.entity.User;
import com.mycompany.quanlydoituongdacbiet.view.LoginView;
import com.mycompany.quanlydoituongdacbiet.view.MainView;
import com.mycompany.quanlydoituongdacbiet.view.PrisonerView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JLabel;


/**
 *
 * @author PC
 */
public class LoginController 
{
    private CheckLogin checkLogin;
    private LoginView loginView;
    private PrisonerView managerView;
    private MainView mainView;
    
    public LoginController(LoginView view) 
    {
        this.loginView = view;
        this.checkLogin = new CheckLogin();
        view.addLoginListener(new LoginListener());
    }
    
    public void showLoginView() 
    {
        loginView.setVisible(true);
    }
    
    /**
     * Lớp LoginListener 
     * chứa cài đặt cho sự kiện click button "Login"
     * 
     * @author viettuts.vn
     */
    class LoginListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent e) 
        {
            User user = loginView.getUser();
            if (checkLogin.checkUser(user)) 
            {
                // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
                mainView = new MainView();
                MainController mainController = new MainController(mainView);
                mainController.showMainView();
                loginView.setVisible(false);
            } 
            else 
            {
                loginView.showMessage("Tên đăng nhập hoặc mật khẩu không đúng.");
            }
        }
    }
}
