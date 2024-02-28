package controller;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import view.loginView;
import view.registerView;
import model.*;
import dao.*;

public class controllerRegister implements ActionListener {
	private registerView v_reg;

	public controllerRegister(registerView v_reg) {
		this.v_reg = v_reg;
	}

	public void hienPass() {
		this.v_reg.btnAnPass_panelRegister.setVisible(true);
		this.v_reg.btnHienPass_panelRegister.setVisible(false);
		this.v_reg.txtPass_panelRegister.setEchoChar((char) 0);
	}

	public void anPass() {
		this.v_reg.btnHienPass_panelRegister.setVisible(true);
		this.v_reg.btnAnPass_panelRegister.setVisible(false);
		this.v_reg.txtPass_panelRegister.setEchoChar('*');
	}

	public void hienConfirm() {
		this.v_reg.btnAnConfirm_panelRegister.setVisible(true);
		this.v_reg.btnHienConfirm_panelRegister.setVisible(false);
		this.v_reg.txtConfirm_panelRegister.setEchoChar((char) 0);
	}

	public void anConfirm() {
		this.v_reg.btnHienConfirm_panelRegister.setVisible(true);
		this.v_reg.btnAnConfirm_panelRegister.setVisible(false);
		this.v_reg.txtConfirm_panelRegister.setEchoChar('*');

	}

	public void actionPerformed(ActionEvent e) {
		String src = e.getActionCommand();

		if (src.equals("CANCEL")) {
			this.v_reg.setVisible(false);
			return;
		} else {

			String chkName = "^[^0-9].*$";
			String chkEmail = "^[A-Za-z0-9-\\+]+([A-Za-z0-9-]+)*@" + "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

			// Ten
			if (this.v_reg.txtName_panelRegister.getText().equals("")) {
				JOptionPane.showMessageDialog(v_reg, "Vui lòng nhập tên");
				return;
			}
			if (!this.v_reg.txtName_panelRegister.getText().matches(chkName)) {
				JOptionPane.showMessageDialog(v_reg, "Tên phải là chữ");
				return;
			}

			// Email

			if (this.v_reg.txtEmail_panelRegister.getText().equals("")) {
				JOptionPane.showMessageDialog(v_reg, "Vui lòng nhập Email");
				return;
			}
			if (!this.v_reg.txtEmail_panelRegister.getText().matches(chkEmail)) {
				JOptionPane.showMessageDialog(v_reg, "Email không hợp lệ");
				return;
			}

			// Pass
			if (this.v_reg.txtPass_panelRegister.getText().equals("")) {
				JOptionPane.showMessageDialog(v_reg, "Vui lòng nhập Password");
				return;
			}

			// Confirm
			if (this.v_reg.txtConfirm_panelRegister.getText().equals("")) {
				JOptionPane.showMessageDialog(v_reg, "Vui lòng nhập Confirm Password");
				return;
			}
			if (!new String(this.v_reg.txtPass_panelRegister.getPassword())
					.equals(new String(this.v_reg.txtConfirm_panelRegister.getPassword()))) {
				JOptionPane.showMessageDialog(v_reg, "Password và Confirm Password không trùng khớp");
				return;
			}

			// chkAgree
			if (!this.v_reg.ckbAgree_panelRegister.isSelected()) {
				JOptionPane.showMessageDialog(v_reg, "Vui lòng đọc qua đều khoản");
				return;
			}

			// chkEmail
			String user = this.v_reg.txtEmail_panelRegister.getText();
			String pass = this.v_reg.txtPass_panelRegister.getText();
			String role = "Đọc giả";
			user u = new user(user, pass, role, "Tồn tại");
			if (userDAO.getuserDAO().selectG(u) != null) {
				JOptionPane.showMessageDialog(this.v_reg, "Email này đã tồn tại");
			} else {

				long maDk = Math.round((Math.random() * 8999) + 1000);

				Properties p = new Properties();
				p.put("mail.smtp.auth", "true");
				p.put("mail.smtp.starttls.enable", "true");
				p.put("mail.smtp.host", "smtp.gmail.com");
				p.put("mail.smtp.port", "587");
				p.put("mail.smtp.ssl.protocols", "TLSv1.2");

				final String emailSever = "nguyenthikieutrang171116@gmail.com";
				final String passSever = "bbhrlzwcyylhdetc";

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
					mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(u.getUsername()));
					mess.setSubject("Mã đăng ký");
					mess.setText("Mã đăng ký của bạn là: " + maDk);

					Transport.send(mess);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				String ma = JOptionPane.showInputDialog(this.v_reg, "Vui lòng nhập mã đăng ký thông qua Email");

				try {

					if (Long.valueOf(ma) == maDk) {
						userDAO.getuserDAO().insertX(u);
						
						thongTinCaNhan ttcnClone = new thongTinCaNhan();
						ttcnClone.setMaTTCN(null);
						ttcnClone.setHinh("employee.png");
						ttcnClone.setTen(this.v_reg.txtName_panelRegister.getText());
						ttcnClone.setDiaChi("");
						ttcnClone.setSoDienThoai("");
						ttcnClone.setEmail(u);
						ttcnClone.setTrangThai("Tồn tại");
						thongTinCaNhanDAO.getthongTinCaNhanDAO().insertX(ttcnClone);
						JOptionPane.showMessageDialog(this.v_reg, "Đăng ký thành công");
					} else {
						JOptionPane.showMessageDialog(this.v_reg, "Mã đăng ký không đúng\nĐăng ký thất bại");
					}
				} catch (NumberFormatException e1) {
					if (ma.equals("")) {
						JOptionPane.showMessageDialog(this.v_reg, "Vui lòng nhập mã đăng ký");
					} else {
						JOptionPane.showMessageDialog(this.v_reg, "Mã đăng kí gồm 4 số");
						e1.printStackTrace();
					}

				} catch (HeadlessException e1) {
					e1.printStackTrace();
				}

			}

		}
	}

	public void backLogin() {
		loginView view = new loginView();
		this.v_reg.setVisible(false);
		view.setVisible(true);
	}

}