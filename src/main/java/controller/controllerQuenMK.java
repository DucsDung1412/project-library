package controller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import dao.userDAO;
import model.user;
import view.loginView;
import view.quenMK;

public class controllerQuenMK {
	private quenMK quenMk;
	private Long madky = null;

	public controllerQuenMK(quenMK quenMk) {
		this.quenMk = quenMk;
	}

	
	public void guimail() {
		if(this.quenMk.txtEmail.getText().equals("")) {
			JOptionPane.showMessageDialog(this.quenMk, "Vui lòng nhập thông tin");
		} else {
			user us = new user();
			us.setUsername(quenMk.txtEmail.getText());
			user use = userDAO.getuserDAO().selectG(us);
			
			if(use == null) {
				JOptionPane.showMessageDialog(this.quenMk, "Email này không tồn tại");
				
			} else {
				long maDk = Math.round((Math.random() * 8999) + 1000);
				madky = maDk;
				
				Properties p = new Properties();
				p.put("mail.smtp.auth", "true");
				p.put("mail.smtp.starttls.enable", "true");
				p.put("mail.smtp.host", "smtp.gmail.com");
				p.put("mail.smtp.port", "587");
				p.put("mail.smtp.ssl.protocols", "TLSv1.2");
				
				final String emailSever = "nguyenhoahung1007@gmail.com";
				final String passSever = "miyadhnqogmurwqu";
				String email = this.quenMk.txtEmail.getText();
				
				Authenticator auth = new Authenticator() {
					@Override
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(emailSever, passSever);
					}
				};
				
				Session session = Session.getInstance(p, auth);
				Message mess = new MimeMessage(session);
				
				try {
					mess.setFrom(new InternetAddress(emailSever));
					mess.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
					mess.setSubject("Mã đăng ký");
					mess.setText("Mã đăng ký của bạn là: " + maDk);
					
					Transport.send(mess);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(this.quenMk, "Mã xác nhận đã được gửi qua gmail của bạn");
				this.quenMk.txtEmail.setEditable(false);
				this.quenMk.lblSetEnEmail.setVisible(true);
			}
		}
	}


	public void clickbtnOK() {
		try {
			if(this.quenMk.txtMaxacnhan.getText().equals("")) {
				JOptionPane.showMessageDialog(this.quenMk, "Vui lòng nhập mã trước khi xác nhận");
			} else {
				if(madky == null) {
					JOptionPane.showMessageDialog(this.quenMk, "Vui lòng nhấn gửi mã và nhập mã trước khi xác nhận");
				} else {
					if(madky.compareTo(Long.valueOf(quenMk.txtMaxacnhan.getText())) == 0) {
						user us = new user();
						us.setUsername(this.quenMk.txtEmail.getText());
						user use =  userDAO.getuserDAO().selectG(us);
						
						JOptionPane.showMessageDialog(this.quenMk,"Mật khẩu của bạn là: "+ use.getPassword());
						this.quenMk.txtEmail.setEditable(true);
						this.quenMk.lblSetEnEmail.setVisible(false);
						this.quenMk.txtMaxacnhan.setText("");
						this.madky = null;
					} else {
						JOptionPane.showMessageDialog(this.quenMk, "Mã xác nhận không chính xác");
					}
				}
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this.quenMk, "Mã xác nhận không chính xác");
		}
	}


	public void enableEmail() {
		this.madky = null;
		this.quenMk.txtEmail.setEditable(true);
		this.quenMk.lblSetEnEmail.setVisible(false);
		this.quenMk.txtMaxacnhan.setText("");
	}

	public void clickHuy() {
		loginView view = new loginView();
		this.quenMk.setVisible(false);
		view.setVisible(true);
	}

}