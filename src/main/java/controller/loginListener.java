package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.userDAO;
import model.user;
import view.loginView;
import view.quenMK;
import view.libraryManagerSystemView;
import view.registerView;

public class loginListener {
	private loginView loginView;
	private String userName;
	private String passWord;
	private String role = "";
	
	public loginListener (loginView loginView)
	{
		this.loginView = loginView;
	}

	public void forgottenPassword()
	{
		quenMK view = new quenMK();
		this.loginView.setVisible(false);
		view.setVisible(true);
	}

	public void logIn()
	{
		userName = this.loginView.txtUsername_pnlFormLogin_contentPane.getText();
		char[] pw = this.loginView.txtPassword_pnlFormLogin_contentPane.getPassword();
		passWord = new String(pw);
		if(passWord.equals("") || userName.equals("")) {
			JOptionPane.showMessageDialog(this.loginView, "Vui lòng nhập đày đủ thông tin");
		} else {
			user u = new user(userName, passWord , "", "Tồn tại");
			user user = userDAO.getuserDAO().selectG(u);
			System.out.println(user.getUsername());
			if(user != null)
			{
				if(user.getPassword().equals(passWord)) {
					libraryManagerSystemView view = new libraryManagerSystemView(user.getUsername());
					this.loginView.setVisible(false);
					view.setVisible(true);
					view.emailLogin = user.getUsername();
				} else {
					JOptionPane.showMessageDialog(this.loginView, "Sai tài khoản hoặc mật khẩu");
				}
			} else {
				JOptionPane.showMessageDialog(this.loginView, "Sai tài khoản hoặc mật khẩu");
			}
		}
	}
	
	public void creatAccount()
	{
		registerView view = new registerView();
		this.loginView.setVisible(false);
		view.setVisible(true);
	}
}