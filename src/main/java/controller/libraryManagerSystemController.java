package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.toedter.calendar.JCalendar;

import dao.danhGiaDAO;
import dao.loaiSachDAO;
import dao.phieuMuonSachDAO;
import dao.sachDAO;
import dao.thongTinCaNhanDAO;
import dao.userDAO;
import dao.yeuCauDAO;
import model.danhGia;
import model.loaiSach;
import model.phieuMuonSach;
import model.sach;
import model.thongTinCaNhan;
import model.user;
import model.yeuCau;
import view.libraryManagerSystemView;
import view.loginView;

public class libraryManagerSystemController {
	private libraryManagerSystemView view;
	private List<phieuMuonSach> lpms = new ArrayList<phieuMuonSach>();
	private phieuMuonSachDAO pmsDAO = new phieuMuonSachDAO();
	private Vector<String> lpmsBorrowed = new Vector<String>();
	private Vector<String> lpmsReturned = new Vector<String>();
	private sachDAO sDAO = new sachDAO();
	private List<sach> lSach = sDAO.selectAll();
	private danhGiaDAO dgDAO = new danhGiaDAO();
	private List<danhGia> lDG = dgDAO.selectAll(); 

	public libraryManagerSystemController(libraryManagerSystemView view) {
		this.view = view;
	}
	
	public void setAvataUser() {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png"));
		int i = fc.showOpenDialog(this.view);
		if(i == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			this.view.imgAvata_panelMain.setImage(new javax.swing.ImageIcon(
					Paths.get(file.getAbsolutePath().toString()).toAbsolutePath().toString()));
			this.view.fileClone = file;
		}
	}
	
	public void saveUser() {
		int i = JOptionPane.showConfirmDialog(this.view, "Bạn có chắc chắn với quyết định thay đổi này?", "Confirm", JOptionPane.YES_NO_OPTION);
		if(i == JOptionPane.YES_OPTION) {
			// luu hinh anh vao file
			String duoi = this.view.fileClone.getName().substring(this.view.fileClone.getName().indexOf(".")+1);
			String name = this.view.fileClone.getName().trim();
			try {
				BufferedImage bi = ImageIO.read(this.view.fileClone);
				ImageIO.write(bi, duoi, new File(Paths.get("src\\main\\java\\icon\\" + name).toAbsolutePath().toString()));
				ImageIO.write(bi, duoi, new File(Paths.get("icon\\" + name).toAbsolutePath().toString()));
			} catch (Exception e) {
//			e.printStackTrace();
			}
			
			user userLoginClone = new user();
			userLoginClone.setUsername(this.view.txtEmail_panelTTCN.getText());
			user user = userDAO.getuserDAO().selectG(userLoginClone);
			
			user.setUsername(this.view.txtEmail_panelTTCN.getText());
			user.setPassword(this.view.txtMatKhau_panelTTCN.getText());
			if(!name.equals("")) {
				user.getTtcn().setHinh(name);
			}
			String sdt_test = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
			if(validateName(this.view.txtHoTen_panelTTCN.getText())) {
				if(this.view.txtSDT_panelTTCN.getText().matches(sdt_test)) {
					this.updateTTCN_Of_user(user);
				} else {
					if(this.view.txtSDT_panelTTCN.getText().equals("")) {
						this.updateTTCN_Of_user(user);
					} else {
						JOptionPane.showMessageDialog(this.view, "Số điện thoại không đúng định dạng");
					}
				}
			} else {
				JOptionPane.showMessageDialog(this.view, "Tên không hợp lệ");
			}
		}
	}
	
	public void updateTTCN_Of_user(user user) {
		user.getTtcn().setSoDienThoai(this.view.txtSDT_panelTTCN.getText());
		user.getTtcn().setDiaChi(this.view.txtDiaChi_panelTTCN.getText());
		user.getTtcn().setTen(this.view.txtHoTen_panelTTCN.getText());
		if(!user.getPassword().equals(userDAO.getuserDAO().selectG(user).getPassword())) {
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
				mess.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.view.txtEmail_panelTTCN.getText()));
				mess.setSubject("Mã bảo vệ");
				mess.setText("Mã bảo vệ của bạn là: " + maDk);

				Transport.send(mess);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String ma = JOptionPane.showInputDialog(this.view, "Vui lòng nhập mã bảo vệ thông qua Email");

			try {
				if (Long.valueOf(ma) == maDk) {
					userDAO.getuserDAO().updateX(user);
					List<thongTinCaNhan> listTTCN = thongTinCaNhanDAO.getthongTinCaNhanDAO().selectAll();
					for (thongTinCaNhan thongTinCaNhan : listTTCN) {
						if(thongTinCaNhan.getEmail().getUsername().equals(user.getUsername())) {
							thongTinCaNhan.setDiaChi(user.getTtcn().getDiaChi());
							thongTinCaNhan.setHinh(user.getTtcn().getHinh());
							thongTinCaNhan.setSoDienThoai(user.getTtcn().getSoDienThoai());
							thongTinCaNhan.setTen(user.getTtcn().getTen());
							thongTinCaNhanDAO.getthongTinCaNhanDAO().updateX(thongTinCaNhan);
							break;
						}
					}
					JOptionPane.showMessageDialog(this.view, "Lưu thành công");
				} else {
					JOptionPane.showMessageDialog(this.view, "Mã bảo vệ không đúng\nĐổi mật khẩu thất bại");
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			userDAO.getuserDAO().updateX(user);
			List<thongTinCaNhan> listTTCN = thongTinCaNhanDAO.getthongTinCaNhanDAO().selectAll();
			for (thongTinCaNhan thongTinCaNhan : listTTCN) {
				if(thongTinCaNhan.getEmail().getUsername().equals(user.getUsername())) {
					thongTinCaNhan.setDiaChi(user.getTtcn().getDiaChi());
					thongTinCaNhan.setHinh(user.getTtcn().getHinh());
					thongTinCaNhan.setSoDienThoai(user.getTtcn().getSoDienThoai());
					thongTinCaNhan.setTen(user.getTtcn().getTen());
					thongTinCaNhanDAO.getthongTinCaNhanDAO().updateX(thongTinCaNhan);
					break;
				}
			}
			JOptionPane.showMessageDialog(this.view, "Lưu thành công");
		}
	}

	public void choseTTCN() {
		this.view.imgHome_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgHome_panelTop.setOpaque(false);
		
		this.view.imgMyBook_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgMyBook_panelTop.setOpaque(false);
		
		this.view.imgBookManager_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgBookManager_panelTop.setOpaque(false);
		
		this.view.imgUserManager_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgUserManager_panelTop.setOpaque(false);
		
		this.view.imgThongKe_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgThongKe_panelTop.setOpaque(false);
		
		this.view.contentPane.removeAll();
		this.view.contentPane.add(this.view.panel_top);
		this.view.contentPane.add(this.view.panel_TTCN());
		this.view.contentPane.repaint();
		this.view.contentPane.revalidate();
	}

	public void choseQLUser() {
		this.view.imgHome_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgHome_panelTop.setOpaque(false);
		
		this.view.imgMyBook_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgMyBook_panelTop.setOpaque(false);
		
		this.view.imgBookManager_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgBookManager_panelTop.setOpaque(false);
		
		this.view.imgUserManager_panelTop.setBackground(new Color(255, 255, 255));
		this.view.imgUserManager_panelTop.setOpaque(true);
		
		this.view.imgThongKe_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgThongKe_panelTop.setOpaque(false);
		
		this.view.contentPane.removeAll();
		this.view.contentPane.add(this.view.panel_top);
		this.view.contentPane.add(this.view.panel_QuanLyUser());
		this.view.contentPane.repaint();
		this.view.contentPane.revalidate();
	}
	
	public void choseQLSach() {
		this.view.imgHome_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgHome_panelTop.setOpaque(false);
		
		this.view.imgMyBook_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgMyBook_panelTop.setOpaque(false);
		
		this.view.imgBookManager_panelTop.setBackground(new Color(255, 255, 255));
		this.view.imgBookManager_panelTop.setOpaque(true);
		
		this.view.imgUserManager_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgUserManager_panelTop.setOpaque(false);
		
		this.view.imgThongKe_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgThongKe_panelTop.setOpaque(false);
		
		this.view.contentPane.removeAll();
		this.view.contentPane.add(this.view.panel_top);
		this.view.contentPane.add(this.view.panel_QuanLySach());
		this.view.contentPane.repaint();
		this.view.contentPane.revalidate();
	}
	
	public void choseHome() {
		this.view.imgHome_panelTop.setBackground(new Color(255, 255, 255));
		this.view.imgHome_panelTop.setOpaque(true);
		
		this.view.imgMyBook_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgMyBook_panelTop.setOpaque(false);
		
		this.view.imgBookManager_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgBookManager_panelTop.setOpaque(false);
		
		this.view.imgUserManager_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgUserManager_panelTop.setOpaque(false);
		
		this.view.imgThongKe_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgThongKe_panelTop.setOpaque(false);
		
		this.view.contentPane.removeAll();
		this.view.contentPane.add(this.view.panel_top);
		this.view.contentPane.add(this.view.panel_Home());
		this.view.contentPane.repaint();
		this.view.contentPane.revalidate();
	}
	
	public void choseThongKe() {
		this.view.imgHome_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgHome_panelTop.setOpaque(false);
		
		this.view.imgMyBook_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgMyBook_panelTop.setOpaque(false);
		
		this.view.imgBookManager_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgBookManager_panelTop.setOpaque(false);
		
		this.view.imgUserManager_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgUserManager_panelTop.setOpaque(false);
		
		this.view.imgThongKe_panelTop.setBackground(new Color(255, 255, 255));
		this.view.imgThongKe_panelTop.setOpaque(true);
		
		this.view.contentPane.removeAll();
		this.view.contentPane.add(this.view.panel_top);
		this.view.contentPane.add(this.view.panel_ThongKe());
		this.view.contentPane.repaint();
		this.view.contentPane.revalidate();
	}
	
	public void choseTTCTS(String tenSach) {
		this.view.imgHome_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgHome_panelTop.setOpaque(false);
		
		this.view.imgMyBook_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgMyBook_panelTop.setOpaque(false);
		
		this.view.imgBookManager_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgBookManager_panelTop.setOpaque(false);
		
		this.view.imgUserManager_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgUserManager_panelTop.setOpaque(false);
		
		this.view.imgThongKe_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgThongKe_panelTop.setOpaque(false);
		
		this.view.contentPane.removeAll();
		this.view.contentPane.add(this.view.panel_top);
		this.view.contentPane.add(this.view.panel_TTS(tenSach));
		this.view.contentPane.repaint();
		this.view.contentPane.revalidate();
	}

	public void logOut() {
		loginView viewLogin = new loginView();
		this.view.setVisible(false);
		viewLogin.setVisible(true);
	}

	public void datSach() {
		JPanel panelCalendar = new JPanel();
		panelCalendar.setLayout(null);
		panelCalendar.setPreferredSize(new Dimension(400,180));
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(0, 0, 400, 180);
		calendar.getDayChooser().setDayBordersVisible(false);
		calendar.setWeekOfYearVisible(false);
		panelCalendar.add(calendar);
		
		int i = JOptionPane.showConfirmDialog(this.view, panelCalendar, "Chọn lịch", JOptionPane.YES_NO_OPTION, JOptionPane.DEFAULT_OPTION);
		if(i == JOptionPane.YES_OPTION) {
			int day = calendar.getDayChooser().getDay();
			int month = calendar.getMonthChooser().getMonth();
			int year = calendar.getYearChooser().getYear();
			
			Date ngayMuon = new Date(year - 1900, month, day);
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			String ngayMuonSach = sdf.format(ngayMuon);
			Date ngayHienTai = Calendar.getInstance().getTime();
			String ngayBayGio = sdf.format(ngayHienTai);
			if(ngayMuonSach.compareTo(ngayBayGio) < 0) {
				JOptionPane.showMessageDialog(this.view, "Ngày đặt sách không hợp lệ");
			} else {
				
				Date ngayTra = new Date(year - 1900, month + 1, day);
				
				user u = new user();
				u.setUsername(this.view.emailLogin);
				user uDAO = userDAO.getuserDAO().selectG(u);
				
				sach s = sachDAO.getsachDAO().selectTheoTenSach(this.view.lblTitle_panelGT.getText());
				Boolean b = false;
				
				for (phieuMuonSach pms : uDAO.getListPM()) {
					if(pms.getMaSach().getMaSach().equals(s.getMaSach())) {
						if(!pms.getTrangThaiPhieu().equals("Đã trả")) {
							b = true; 
							break;
						}
					}
				}
				
				if(b == false) {
					if(s.getSoLuong() <= 0) {
						JOptionPane.showMessageDialog(this.view, "Sách này đang tạm hết. Vui lòng quay lại sau");
					} else {
						phieuMuonSach pms = new phieuMuonSach(null, ngayMuon, ngayTra, u, s, "Đang đặt", "Tồn tại");
						phieuMuonSachDAO.getphieuMuonSachDAO().insertX(pms);
						s.setSoLuong(s.getSoLuong() - 1);
						sachDAO.getsachDAO().updateX(s);
						
						JOptionPane.showMessageDialog(this.view, "Đặt sách thành công");
						this.view.txtValue_panelTTCT_1.setText(" " + s.getNamXB() + "\r\n Tái bản lần thứ " + s.getSoLanTaiBan() + "\r\n " + s.getSoLuong() + " cuốn");
					}
				} else {
					JOptionPane.showMessageDialog(this.view, "Bạn đã mượn sách này trước đây hoặc đã đặt sách này rồi. Vui lòng trả lại sách để tiến hành đặt lại");
				}
			}
		}
	}

	public void createdChart() {
		this.view.contentPane.removeAll();
		this.view.contentPane.add(this.view.panel_top);
		this.view.contentPane.add(this.view.panel_CreatedChart());
		this.view.contentPane.repaint();
		this.view.contentPane.revalidate();
	}

	public void changeToLS() {
		this.view.panelQLS.removeAll();
		this.view.lblLoaiSach_panelNut.setOpaque(true);
		this.view.lblLoaiSach_panelNut.setForeground(Color.WHITE);
		this.view.lblSach_panelNut.setOpaque(false);
		this.view.lblSach_panelNut.setForeground(Color.BLACK);
		this.view.panelQLS.add(this.view.lblSach_panelNut);
		this.view.panelQLS.add(this.view.lblLoaiSach_panelNut);
		
		this.view.panel_QLS.removeAll();
		this.view.panel_QLS.add(this.view.panelQLS);
		this.view.panel_QLS.add(this.view.tblQuanLyLoaiSach());
		this.view.panel_QLS.add(this.view.dieuKhienLoaiSach());
		this.view.panel_QLS.repaint();
		this.view.panel_QLS.validate();
	}
	
	public void changeToSach() {
		this.view.panelQLS.removeAll();
		this.view.lblLoaiSach_panelNut.setOpaque(false);
		this.view.lblSach_panelNut.setForeground(Color.WHITE);
		this.view.lblSach_panelNut.setOpaque(true);
		this.view.lblLoaiSach_panelNut.setForeground(Color.BLACK);
		this.view.panelQLS.add(this.view.lblSach_panelNut);
		this.view.panelQLS.add(this.view.lblLoaiSach_panelNut);
		
		this.view.panel_QLS.removeAll();
		this.view.panel_QLS.add(this.view.panelQLS);
		this.view.panel_QLS.add(this.view.tblQuanLySach());
		this.view.panel_QLS.add(this.view.dieuKhienSach());
		this.view.panel_QLS.add(this.view.timKiemSach());
		this.view.panel_QLS.repaint();
		this.view.panel_QLS.validate();
	}

	public void changeToDatSach() {
		this.view.panelNutUser.removeAll();
		this.view.lblUser_panelNutUser.setOpaque(false);
		this.view.lblUser_panelNutUser.setForeground(Color.BLACK);
		
		this.view.lblYeuCau_panelNutUser.setOpaque(false);
		this.view.lblYeuCau_panelNutUser.setForeground(Color.BLACK);
		
		this.view.lblDangMuon_panelNutUser.setOpaque(false);
		this.view.lblDangMuon_panelNutUser.setForeground(Color.BLACK);
		
		this.view.lblQuaHan_panelNutUser.setOpaque(false);
		this.view.lblQuaHan_panelNutUser.setForeground(Color.BLACK);
		
		this.view.lblDatSach_panelNutUser.setOpaque(true);
		this.view.lblDatSach_panelNutUser.setForeground(Color.WHITE);
		
		this.view.panelNutUser.add(this.view.lblUser_panelNutUser);
		this.view.panelNutUser.add(this.view.lblYeuCau_panelNutUser);
		this.view.panelNutUser.add(this.view.lblDangMuon_panelNutUser);
		this.view.panelNutUser.add(this.view.lblQuaHan_panelNutUser);
		this.view.panelNutUser.add(this.view.lblDatSach_panelNutUser);
		
		this.view.panel_QLUS.removeAll();
		this.view.panel_QLUS.add(this.view.panelNutUser);
		this.view.panel_QLUS.add(this.view.tblQuanLyDatSach());
		this.view.panel_QLUS.add(this.view.panel_dieukhienDatSach());
		this.view.panel_QLUS.repaint();
		this.view.panel_QLUS.validate();
	}
	
	public void changeToUser() {
		this.view.panelNutUser.removeAll();
		this.view.lblUser_panelNutUser.setOpaque(true);
		this.view.lblUser_panelNutUser.setForeground(Color.WHITE);
		
		this.view.lblYeuCau_panelNutUser.setOpaque(false);
		this.view.lblYeuCau_panelNutUser.setForeground(Color.BLACK);
		
		this.view.lblDangMuon_panelNutUser.setOpaque(false);
		this.view.lblDangMuon_panelNutUser.setForeground(Color.BLACK);
		
		this.view.lblQuaHan_panelNutUser.setOpaque(false);
		this.view.lblQuaHan_panelNutUser.setForeground(Color.BLACK);
		
		this.view.lblDatSach_panelNutUser.setOpaque(false);
		this.view.lblDatSach_panelNutUser.setForeground(Color.BLACK);
		
		this.view.panelNutUser.add(this.view.lblUser_panelNutUser);
		this.view.panelNutUser.add(this.view.lblYeuCau_panelNutUser);
		this.view.panelNutUser.add(this.view.lblDangMuon_panelNutUser);
		this.view.panelNutUser.add(this.view.lblQuaHan_panelNutUser);
		this.view.panelNutUser.add(this.view.lblDatSach_panelNutUser);
		
		this.view.panel_QLUS.removeAll();
		this.view.panel_QLUS.add(this.view.panelNutUser);
		this.view.panel_QLUS.add(this.view.tblQuanLyUser());
		this.view.panel_QLUS.add(this.view.panel_dieukhienUser());
		this.view.panel_QLUS.repaint();
		this.view.panel_QLUS.validate();
	}

	public void changeToYeuCau() {
		this.view.panelNutUser.removeAll();
		this.view.lblUser_panelNutUser.setOpaque(false);
		this.view.lblUser_panelNutUser.setForeground(Color.BLACK);
		
		this.view.lblYeuCau_panelNutUser.setOpaque(true);
		this.view.lblYeuCau_panelNutUser.setForeground(Color.WHITE);
		
		this.view.lblDangMuon_panelNutUser.setOpaque(false);
		this.view.lblDangMuon_panelNutUser.setForeground(Color.BLACK);
		
		this.view.lblQuaHan_panelNutUser.setOpaque(false);
		this.view.lblQuaHan_panelNutUser.setForeground(Color.BLACK);
		
		this.view.lblDatSach_panelNutUser.setOpaque(false);
		this.view.lblDatSach_panelNutUser.setForeground(Color.BLACK);
		
		this.view.panelNutUser.add(this.view.lblUser_panelNutUser);
		this.view.panelNutUser.add(this.view.lblYeuCau_panelNutUser);
		this.view.panelNutUser.add(this.view.lblDangMuon_panelNutUser);
		this.view.panelNutUser.add(this.view.lblQuaHan_panelNutUser);
		this.view.panelNutUser.add(this.view.lblDatSach_panelNutUser);
		
		this.view.panel_QLUS.removeAll();
		this.view.panel_QLUS.add(this.view.panelNutUser);
		this.view.panel_QLUS.add(this.view.tblQuanLyYeuCau());
		this.view.panel_QLUS.add(this.view.panel_dieukhienYeuCau());
		this.view.panel_QLUS.repaint();
		this.view.panel_QLUS.validate();
	}

	public void changeToDangMuon() {
		this.view.panelNutUser.removeAll();
		this.view.lblUser_panelNutUser.setOpaque(false);
		this.view.lblUser_panelNutUser.setForeground(Color.BLACK);
		
		this.view.lblYeuCau_panelNutUser.setOpaque(false);
		this.view.lblYeuCau_panelNutUser.setForeground(Color.BLACK);
		
		this.view.lblDangMuon_panelNutUser.setOpaque(true);
		this.view.lblDangMuon_panelNutUser.setForeground(Color.WHITE);
		
		this.view.lblQuaHan_panelNutUser.setOpaque(false);
		this.view.lblQuaHan_panelNutUser.setForeground(Color.BLACK);
		
		this.view.lblDatSach_panelNutUser.setOpaque(false);
		this.view.lblDatSach_panelNutUser.setForeground(Color.BLACK);
		
		this.view.panelNutUser.add(this.view.lblUser_panelNutUser);
		this.view.panelNutUser.add(this.view.lblYeuCau_panelNutUser);
		this.view.panelNutUser.add(this.view.lblDangMuon_panelNutUser);
		this.view.panelNutUser.add(this.view.lblQuaHan_panelNutUser);
		this.view.panelNutUser.add(this.view.lblDatSach_panelNutUser);
		
		this.view.panel_QLUS.removeAll();
		this.view.panel_QLUS.add(this.view.panelNutUser);
		this.view.panel_QLUS.add(this.view.tblQuanLyDangMuon());
		this.view.panel_QLUS.add(this.view.panel_dieukhienDangMuon());
		this.view.panel_QLUS.repaint();
		this.view.panel_QLUS.validate();
	}

	public void changeToQuaHan() {
		this.view.panelNutUser.removeAll();
		this.view.lblUser_panelNutUser.setOpaque(false);
		this.view.lblUser_panelNutUser.setForeground(Color.BLACK);
		
		this.view.lblYeuCau_panelNutUser.setOpaque(false);
		this.view.lblYeuCau_panelNutUser.setForeground(Color.BLACK);
		
		this.view.lblDangMuon_panelNutUser.setOpaque(false);
		this.view.lblDangMuon_panelNutUser.setForeground(Color.BLACK);
		
		this.view.lblQuaHan_panelNutUser.setOpaque(true);
		this.view.lblQuaHan_panelNutUser.setForeground(Color.WHITE);
		
		this.view.lblDatSach_panelNutUser.setOpaque(false);
		this.view.lblDatSach_panelNutUser.setForeground(Color.BLACK);
		
		this.view.panelNutUser.add(this.view.lblUser_panelNutUser);
		this.view.panelNutUser.add(this.view.lblYeuCau_panelNutUser);
		this.view.panelNutUser.add(this.view.lblDangMuon_panelNutUser);
		this.view.panelNutUser.add(this.view.lblQuaHan_panelNutUser);
		this.view.panelNutUser.add(this.view.lblDatSach_panelNutUser);
		
		this.view.panel_QLUS.removeAll();
		this.view.panel_QLUS.add(this.view.panelNutUser);
		this.view.panel_QLUS.add(this.view.tblQuanLyQuaHan());
		this.view.panel_QLUS.add(this.view.panel_dieukhienQuaHan());
		this.view.panel_QLUS.repaint();
		this.view.panel_QLUS.validate();
	}
	
	public void timSachTheoTen() {
		DefaultTableModel tableModel_qlSach = (DefaultTableModel) this.view.tblQuanLySach.getModel();
		tableModel_qlSach.setRowCount(0);
		List<sach> list = sachDAO.getsachDAO().selectTheoString(new String("tenSach"), this.view.txtTimKiem_panelDieuKhien.getText());
		for (sach sach : list) {
			tableModel_qlSach.addRow(new Object[]{
                    sach.getMaSach(),
                    sach.getTenSach(),
                    sach.getTacGia(),
                    sach.getNhaXuatBan(),
                    sach.getSoLanTaiBan(),
                    sach.getNamXB(),
                    sach.getSoLuong(),
                    sach.getMaLoaiSach().getTenLoaiSach()
                    
            });
		}
	}
	
	public void sachMoi() {
		this.view.txtTenSach_panelCTS.setText("");
		this.view.txtTacGia_panelCTS.setText("");
		this.view.txtNhaXuatBan_panelCTS.setText("");
		this.view.txtMaSach_panelCTS.setText("");
		this.view.txtTaiBan_panelCTS.setText("");
		this.view.txtSoLuong_panelCTS.setText("");
		this.view.txtNhaXuatBan_panelCTS.setText("");
		this.view.txtNamXuatBan_panelCTS.setText("");
		this.view.imgSach_panelCTS.setIcon(null);
		this.view.imgSach_panelCTS.setText("Nhấn để tải hình ảnh");
		this.view.cbxMaLS_panelCTS.setSelectedIndex(0);
		this.view.btnMoTa_panelCTS.setEnabled(false);
		this.view.moTaSach = "";
		this.view.tblQuanLySach.clearSelection();
	}
	
	public void changeHinhSach() {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png"));
		int i = fc.showOpenDialog(this.view);
		if(i == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			
			ImageIcon imgI_Sach = new ImageIcon(Paths.get(file.getAbsolutePath().toString()).toAbsolutePath().toString());
			Image img_Sach = imgI_Sach.getImage();
			Image image_Sach = img_Sach.getScaledInstance(this.view.imgSach_panelCTS.getWidth(), this.view.imgSach_panelCTS.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imgIcon_Sach = new ImageIcon(image_Sach);
			this.view.imgSach_panelCTS.setIcon(imgIcon_Sach);
			this.view.imgSach_panelCTS.setText("");
			this.view.fileClone = file;
		}
	}
	
	public void luuSach() {
		int i = JOptionPane.showConfirmDialog(this.view, "Bạn có chắc chắn với quyết định này?", "Confirm", JOptionPane.YES_NO_OPTION);
		if(i == JOptionPane.YES_OPTION) {
			// luu hinh anh vao file
			String duoi = this.view.fileClone.getName().substring(this.view.fileClone.getName().indexOf(".")+1);
			String name = this.view.fileClone.getName().trim();
			
			String maSach = this.view.txtMaSach_panelCTS.getText();
			
			loaiSach ls = loaiSachDAO.getloaiSachDAO().selectTheoTen("tenLoaiSach", this.view.cbxMaLS_panelCTS.getSelectedItem()+"");

			String tacGia = this.view.txtTacGia_panelCTS.getText();
			
			String nhaXB = this.view.txtNhaXuatBan_panelCTS.getText();
			
			String tenSach = this.view.txtTenSach_panelCTS.getText();
			
			sach ss = new sach();
			ss.setMaSach(maSach);
			sach sclone = sachDAO.getsachDAO().selectG(ss);
			
			String mota = this.view.moTaSach;
			
			String hinhSach = "";
			if(!name.equals("")) {
				hinhSach = name;
			}
			
			if(sclone != null) {
				mota = sclone.getMoTa();
				hinhSach = sclone.getHinhSach();
			}
			
			try {
				int taiBan = Integer.valueOf(this.view.txtTaiBan_panelCTS.getText());
				int soLuong = Integer.valueOf(this.view.txtSoLuong_panelCTS.getText());
				int namXB = Integer.valueOf(this.view.txtNamXuatBan_panelCTS.getText());
				sach sach = new sach(maSach, tenSach, tacGia, hinhSach, mota, nhaXB, namXB, soLuong, taiBan, ls, "Tồn tại");
				if(hinhSach.equals("") || mota.equals("") || this.view.txtTenSach_panelCTS.getText().equals("") 
						|| this.view.txtNhaXuatBan_panelCTS.getText().equals("") || this.view.txtTacGia_panelCTS.getText().equals("") 
						|| this.view.txtNamXuatBan_panelCTS.getText().equals("") || this.view.txtSoLuong_panelCTS.getText().equals("") 
						|| this.view.txtTaiBan_panelCTS.getText().equals("")) 
				{				
					JOptionPane.showMessageDialog(this.view, "Vùi lòng nhập đầy đủ thông tin");
				} else {
					if(soLuong < 0) {
						JOptionPane.showMessageDialog(this.view, "Số lượng không thể là số âm");
					}  else 
					if(taiBan < 1) {
						JOptionPane.showMessageDialog(this.view, "Số lần tái bản không thể nhỏ hơn 1");
					} else 
					if(namXB < 1800) {
						if(namXB < 0) {
							JOptionPane.showMessageDialog(this.view, "Năm xuất bản không thể là số âm");
						} else {
							JOptionPane.showMessageDialog(this.view, "Năm xuất bản phải từ năm 1800 trở đi");
						}
					} else 
					if(validateName(tacGia)) {
						if(validatePublisherName(nhaXB)) {
							if(tenSach.matches("^[a-zA-Z].*$")) {
								Boolean b = sclone == null;
								if(b) {
									sachDAO.getsachDAO().insertX(sach);
									JOptionPane.showMessageDialog(this.view, "Thêm mới thành công");
									try {
										BufferedImage bi = ImageIO.read(this.view.fileClone);
										ImageIO.write(bi, duoi, new File(Paths.get("src\\main\\java\\icon\\" + name).toAbsolutePath().toString()));
										ImageIO.write(bi, duoi, new File(Paths.get("icon\\" + name).toAbsolutePath().toString()));
									} catch (Exception e) {
										e.printStackTrace();
									}
									this.loadSach();
									this.view.tblQuanLySach.setRowSelectionInterval(this.view.tblQuanLySach.getRowCount() - 1, this.view.tblQuanLySach.getRowCount() - 1);
								} else {
									sachDAO.getsachDAO().updateX(sach);
									JOptionPane.showMessageDialog(this.view, "Cập nhật thành công");
									this.loadSach();
									for (int j = 0; j < this.view.tblQuanLySach.getRowCount(); j++) {
										if(this.view.tblQuanLySach.getValueAt(j, 0).equals(sclone.getMaSach())) {
											this.view.tblQuanLySach.setRowSelectionInterval(j,j);
										}
									}
								}
							} else {
								JOptionPane.showMessageDialog(this.view, "Tên sách không hợp lệ");
							}
						} else {
							JOptionPane.showMessageDialog(this.view, "Nhà Xuất Bản không hợp lệ");
						}
					} else {
						JOptionPane.showMessageDialog(this.view, "Tên tác giả không hợp lệ");
					}
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this.view, "Số lương, năm xuất bản và tái bản phải là số");
			}
			
		}
	}
	
	public void xoaSach() {
		int i = JOptionPane.showConfirmDialog(this.view, "Bạn có chắc chắn muốn xóa sách này", "ConfirmDialog", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(i == JOptionPane.YES_OPTION) {
			if(this.view.txtMaSach_panelCTS.getText().equals("")) {
				JOptionPane.showMessageDialog(this.view, "Vui lòng chọn dòng cần xóa");
			} else {
				sach sach = new sach();
				sach.setMaSach(this.view.txtMaSach_panelCTS.getText());
				
				sach sachDelete = sachDAO.getsachDAO().selectG(sach);
				
				sach.setTenSach(sachDelete.getTenSach());
				sach.setTacGia(sachDelete.getTacGia());
				sach.setNhaXuatBan(sachDelete.getNhaXuatBan());
				sach.setNamXB(sachDelete.getNamXB());
				sach.setSoLuong(sachDelete.getSoLuong());
				sach.setSoLanTaiBan(sachDelete.getSoLanTaiBan());
				sach.setMaLoaiSach(sachDelete.getMaLoaiSach());
				sach.setHinhSach(sachDelete.getHinhSach());
				sach.setMoTa(sachDelete.getMoTa());
				int selectRow = this.view.tblQuanLySach.getSelectedRow();
				sachDAO.getsachDAO().deletaX(sach);
				JOptionPane.showMessageDialog(this.view, "Xóa thành công");
				
				if(this.view.tblQuanLySach.getRowCount() == 0) {
					
				} else {
					if(this.view.tblQuanLySach.getRowCount() - 1 == this.view.tblQuanLySach.getSelectedRow()) {
						loadSach();
						if(this.view.tblQuanLySach.getRowCount() != 0) {
							this.view.tblQuanLySach.setRowSelectionInterval(this.view.tblQuanLySach.getRowCount() - 1, this.view.tblQuanLySach.getRowCount() - 1);
						}
					} else {
						if(this.view.tblQuanLySach.getSelectedRow() == 0) {
							loadSach();
							this.view.tblQuanLySach.setRowSelectionInterval(0, 0);
						} else {
							loadSach();
							this.view.tblQuanLySach.setRowSelectionInterval(selectRow, selectRow);
						}
					}
					if(this.view.tblQuanLySach.getRowCount() != 0) {
						clickTableSach();
					}
				}
			}
		}
	}
	
	public void xemMoTaSach() {
		sach sach = new sach();
		sach.setMaSach(this.view.txtMaSach_panelCTS.getText());
		sach sachdao = sachDAO.getsachDAO().selectG(sach);
		if(this.view.moTaSach.equals("")) {
			this.view.moTaSach = sachdao.getMoTa();
		}
		
		JPanel panelXemMoTa = new JPanel();
		panelXemMoTa.setLayout(null);
		panelXemMoTa.setPreferredSize(new Dimension(800, 500));
		
		JLabel lblMoTa = new JLabel("Mô Tả:");
		lblMoTa.setFont(new Font("Arial", Font.BOLD, 28));
		lblMoTa.setBounds(4,4, 100, 25);
		panelXemMoTa.add(lblMoTa);
			
		JTextArea txtMoTa = new JTextArea();
		txtMoTa.setFont(new Font("Arial", Font.PLAIN, 14));
		txtMoTa.setBounds(10, 37, 780, 458);
		txtMoTa.setLineWrap(true);
		txtMoTa.setEditable(false);
		txtMoTa.setText(this.view.moTaSach);
		panelXemMoTa.add(txtMoTa);
			
		JOptionPane.showConfirmDialog(this.view, panelXemMoTa, "Mô tả", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION);
		
	}
	
	public void themMoTa() {
		sach sach = new sach();
		sach.setMaSach(this.view.txtMaSach_panelCTS.getText());
		sach sachdao = sachDAO.getsachDAO().selectG(sach);
		
		if(sachdao != null && sachdao.getMoTa() != null) {
			this.view.moTaSach = sachdao.getMoTa();
		}
		
		JPanel panelXemMoTa = new JPanel();
		panelXemMoTa.setLayout(null);
		panelXemMoTa.setPreferredSize(new Dimension(800, 500));
		
		JLabel lblMoTa = new JLabel("Mô Tả:");
		lblMoTa.setFont(new Font("Arial", Font.BOLD, 28));
		lblMoTa.setBounds(4,4, 100, 25);
		panelXemMoTa.add(lblMoTa);
		
		JTextArea txtMoTa = new JTextArea();
		txtMoTa.setFont(new Font("Arial", Font.PLAIN, 14));
		txtMoTa.setBounds(10, 37, 780, 458);
		txtMoTa.setLineWrap(true);
		txtMoTa.setText(this.view.moTaSach);
		panelXemMoTa.add(txtMoTa);
		
		int i = JOptionPane.showConfirmDialog(this.view, panelXemMoTa, "Mô tả", JOptionPane.OK_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION);
		
		if(i == JOptionPane.OK_OPTION) {
			this.view.moTaSach = txtMoTa.getText();
			JOptionPane.showMessageDialog(this.view, "Thêm mô tả thành công");
			this.view.btnMoTa_panelCTS.setEnabled(true);
		}
	}
	
	public void timLoaiSachTheoCBX() {
		String maLoaiSach = this.view.txtMaLS_panelCTLS.getText();
		String tenLoaiSach = this.view.txtTenLS_panelCTLS.getText();
		String moTa = this.view.txtMoTa_panelCTLS.getText();
		
		loaiSach ls = new loaiSach(maLoaiSach, tenLoaiSach, moTa, "Tồn tại");
		String cbx = this.view.cbxLoc_panelDieuKhienLS.getSelectedItem() + "";
		String vietTac = cbx.equals("Mã loại sách") ? "maLoaiSach" : "tenLoaiSach";
		List<loaiSach> list = loaiSachDAO.getloaiSachDAO().selectTheoString(vietTac, "%" + this.view.txtTimKiem_panelDieuKhienLS.getText() + "%");
		DefaultTableModel tableModel_loaiSach = (DefaultTableModel) this.view.tblQuanLyLoaiSach.getModel();
		tableModel_loaiSach.setRowCount(0);
		for (loaiSach loaiSach : list) {
			tableModel_loaiSach.addRow(new Object[]{
					loaiSach.getMaLoaiSach(),
					loaiSach.getTenLoaiSach(),
					loaiSach.getMoTa()
        });
		}
	}
	
	public void lamMoiLoaiSach() {
		this.view.txtMaLS_panelCTLS.setText("");
		this.view.txtTenLS_panelCTLS.setText("");
		this.view.txtMoTa_panelCTLS.setText("");
		this.view.tblQuanLyLoaiSach.clearSelection();
	}
	
	public void luuLoaiSach() {
		loaiSach loaiSach = new loaiSach();
		loaiSach.setMaLoaiSach(this.view.txtMaLS_panelCTLS.getText());
		loaiSach.setTenLoaiSach(this.view.txtTenLS_panelCTLS.getText());
		loaiSach.setMoTa(this.view.txtMoTa_panelCTLS.getText());
		loaiSach.setTrangThai("Tồn tại");

		Boolean b = loaiSachDAO.getloaiSachDAO().selectG(loaiSach) == null;
		if(this.view.txtMoTa_panelCTLS.getText().equals("") || this.view.txtTenLS_panelCTLS.getText().equals("")) {
			JOptionPane.showMessageDialog(this.view, "Vui lòng nhập đầy đủ thông tin");
		} else {
			if(validatePublisherName(loaiSach.getTenLoaiSach())) {
				if(b) {
					//INSERT
					loaiSachDAO.getloaiSachDAO().insertX(loaiSach);
					JOptionPane.showMessageDialog(this.view, "Thêm thành công");
					loadLoaiSach();
					this.view.tblQuanLyLoaiSach.setRowSelectionInterval(this.view.tblQuanLyLoaiSach.getRowCount() - 1, this.view.tblQuanLyLoaiSach.getRowCount() - 1);
				} else {
					//UPDATE
					loaiSach ls = loaiSachDAO.getloaiSachDAO().selectG(loaiSach);
					loaiSachDAO.getloaiSachDAO().updateX(loaiSach);
					JOptionPane.showMessageDialog(this.view, "Cập nhật thành công");
					loadLoaiSach();
					for (int j = 0; j < this.view.tblQuanLyLoaiSach.getRowCount(); j++) {
						if(this.view.tblQuanLyLoaiSach.getValueAt(j, 0).equals(ls.getMaLoaiSach())) {
							this.view.tblQuanLyLoaiSach.setRowSelectionInterval(j,j);
						}
					}
				}
			} else {
				JOptionPane.showMessageDialog(this.view, "Tên loại sách không đúng");
			}
		}
	}
	
	public void xoaLoaiSach() {
		int i = JOptionPane.showConfirmDialog(this.view, "Bạn có chắc chắn muốn xóa sách này", "ConfirmDialog", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(i == JOptionPane.YES_OPTION) {
			if(this.view.txtMaLS_panelCTLS.getText().equals("")) {
				JOptionPane.showMessageDialog(this.view, "Vui lòng chọn dòng cần xóa");
			} else {
				loaiSach ls = new loaiSach();
				ls.setMaLoaiSach(this.view.txtMaLS_panelCTLS.getText());
				ls.setTenLoaiSach(this.view.txtTenLS_panelCTLS.getText());
				ls.setMoTa(this.view.txtMoTa_panelCTLS.getText());
				
				int selectRow = this.view.tblQuanLyLoaiSach.getSelectedRow();
				
				loaiSachDAO.getloaiSachDAO().deletaX(ls);
				JOptionPane.showMessageDialog(this.view, "Xóa thành công");
				
				if(this.view.tblQuanLyLoaiSach.getRowCount() == 0) {
					
				} else {
					System.out.println(this.view.tblQuanLyLoaiSach.getRowCount() - 1);
					System.out.println(this.view.tblQuanLyLoaiSach.getSelectedRow());
					if(this.view.tblQuanLyLoaiSach.getRowCount() - 1 == this.view.tblQuanLyLoaiSach.getSelectedRow()) {
						loadLoaiSach();
						if(this.view.tblQuanLyLoaiSach.getRowCount() != 0) {
							this.view.tblQuanLyLoaiSach.setRowSelectionInterval(this.view.tblQuanLyLoaiSach.getRowCount() - 1, this.view.tblQuanLyLoaiSach.getRowCount() - 1);
						}
					} else {
						if(this.view.tblQuanLyLoaiSach.getSelectedRow() == 0) {
							loadLoaiSach();
							this.view.tblQuanLyLoaiSach.setRowSelectionInterval(0, 0);
						} else {
							loadLoaiSach();
							this.view.tblQuanLyLoaiSach.setRowSelectionInterval(selectRow, selectRow);
						}
					}
					if(this.view.tblQuanLyLoaiSach.getRowCount() != 0) {
						clickTableLoaiSach();
					}
					
				}
			}
		}
	}
	
	public void clickTableSach() {
		this.view.btnMoTa_panelCTS.setEnabled(true);
		this.view.moTaSach = "";
		
		this.view.tblQuanLySach.getSelectedRow();
		this.view.txtMaSach_panelCTS.setText((String) this.view.tblQuanLySach.getValueAt(this.view.tblQuanLySach.getSelectedRow(), 0));
		this.view.txtTenSach_panelCTS.setText((String) this.view.tblQuanLySach.getValueAt(this.view.tblQuanLySach.getSelectedRow(), 1));
		this.view.txtTacGia_panelCTS.setText((String) this.view.tblQuanLySach.getValueAt(this.view.tblQuanLySach.getSelectedRow(), 2));
		this.view.txtNhaXuatBan_panelCTS.setText((String) this.view.tblQuanLySach.getValueAt(this.view.tblQuanLySach.getSelectedRow(), 3));
		this.view.txtTaiBan_panelCTS.setText("" + this.view.tblQuanLySach.getValueAt(this.view.tblQuanLySach.getSelectedRow(), 4));
		this.view.txtNamXuatBan_panelCTS.setText("" + this.view.tblQuanLySach.getValueAt(this.view.tblQuanLySach.getSelectedRow(), 5));
		this.view.txtSoLuong_panelCTS.setText("" + this.view.tblQuanLySach.getValueAt(this.view.tblQuanLySach.getSelectedRow(), 6));
		this.view.cbxMaLS_panelCTS.setSelectedItem(this.view.tblQuanLySach.getValueAt(this.view.tblQuanLySach.getSelectedRow(), 7));
		
		// lay sach tu database qua maSach
		sach sach = new sach();
		sach.setMaSach(this.view.tblQuanLySach.getValueAt(this.view.tblQuanLySach.getSelectedRow(), 0) + "");
		sach s = sachDAO.getsachDAO().selectG(sach);
		
		ImageIcon imgIconSach = new ImageIcon(Paths.get("icon\\" + s.getHinhSach()).toAbsolutePath().toString());
		Image img = imgIconSach.getImage();
		Image image = img.getScaledInstance(this.view.imgSach_panelCTS.getWidth(), this.view.imgSach_panelCTS.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIconSach = new ImageIcon(image);
		this.view.imgSach_panelCTS.setText("");
		
		//them hinh
		this.view.imgSach_panelCTS.setIcon(imageIconSach);
		
	}
	
	public void clickTableLoaiSach() {
		this.view.tblQuanLyLoaiSach.getSelectedRow();
		this.view.txtMaLS_panelCTLS.setText((String) this.view.tblQuanLyLoaiSach.getValueAt(this.view.tblQuanLyLoaiSach.getSelectedRow(), 0));
		this.view.txtTenLS_panelCTLS.setText((String) this.view.tblQuanLyLoaiSach.getValueAt(this.view.tblQuanLyLoaiSach.getSelectedRow(), 1));
		this.view.txtMoTa_panelCTLS.setText((String) this.view.tblQuanLyLoaiSach.getValueAt(this.view.tblQuanLyLoaiSach.getSelectedRow(), 2));
	}
	
	public void loadSach() {
		DefaultTableModel tableModel_sach = (DefaultTableModel) this.view.tblQuanLySach.getModel();
		tableModel_sach.setRowCount(0);
		List<sach> laysach = sachDAO.getsachDAO().selectAll();
		for (sach sach : laysach) {
			
			tableModel_sach.addRow(new Object[]{
	                    sach.getMaSach(),
	                    sach.getTenSach(),
	                    sach.getTacGia(),
	                    sach.getNhaXuatBan(),
	                    sach.getSoLanTaiBan(),
	                    sach.getNamXB(),
	                    sach.getSoLuong(),
	                    sach.getMaLoaiSach().getTenLoaiSach()
	                    
	            });
		}
	}
	
	public void loadLoaiSach() {
		DefaultTableModel tableModel_loaisach = (DefaultTableModel) this.view.tblQuanLyLoaiSach.getModel();
		tableModel_loaisach.setRowCount(0);
		List<loaiSach> layloaisach = loaiSachDAO.getloaiSachDAO().selectAll();
		for (loaiSach loaisach : layloaisach) {
			tableModel_loaisach.addRow(new Object[]{
	                   loaisach.getMaLoaiSach(),
	                   loaisach.getTenLoaiSach(),
	                   loaisach.getMoTa()
            });
		}
	}

	public void xuatFileSach() {
		String[] listBTN = {"EXCEL", "PDF"};
		int option = JOptionPane.showOptionDialog(this.view, "Bạn muốn xuất ra gì?", "Lựa Chọn", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, listBTN, listBTN[0]);
		if (option == 0) {
			exportToExcel(this.view.tblQuanLySach, "Quản lý sách");
		} else if(option == 1) {
			exportToPDF(this.view.tblQuanLySach, "Quản lý sách");
		}
	}
	
	public void exportToExcel(JTable table, String title) {
		JFileChooser fc = new JFileChooser();
		int option = fc.showSaveDialog(this.view);
		if(option == JFileChooser.APPROVE_OPTION) {
			String file = fc.getSelectedFile().getAbsolutePath();
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet(title);
			
			CellStyle styleHead = workbook.createCellStyle();
			styleHead.setAlignment(HorizontalAlignment.CENTER);
			styleHead.setBorderBottom(BorderStyle.THIN);
			styleHead.setBorderTop(BorderStyle.THIN);
			styleHead.setBorderLeft(BorderStyle.THIN);
			styleHead.setBorderRight(BorderStyle.THIN);
			org.apache.poi.ss.usermodel.Font font = workbook.createFont();
			font.setBold(true);
			styleHead.setFont(font);
			
			Row headerRow = sheet.createRow(0);
			for(int i = 0; i < table.getColumnCount(); i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(table.getColumnName(i));
				cell.setCellStyle(styleHead);
			}
			
			CellStyle styleValue = workbook.createCellStyle();
			styleValue.setBorderBottom(BorderStyle.THIN);
			styleValue.setBorderTop(BorderStyle.THIN);
			styleValue.setBorderLeft(BorderStyle.THIN);
			styleValue.setBorderRight(BorderStyle.THIN);
			for (int i = 0; i < table.getRowCount(); i++) {
				Row rowFrs = sheet.createRow(i + 1);
				for (int j = 0; j < table.getColumnCount(); j++) {
					Cell cell = rowFrs.createCell(j);
					cell.setCellValue(table.getValueAt(i, j) + "");
					cell.setCellStyle(styleValue);
				}
			}
			
			try {
				FileOutputStream fos = new FileOutputStream(file + ".xlsx");
				workbook.write(fos);
				fos.close();
				
				JOptionPane.showMessageDialog(this.view, "Xuất File thành công");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void exportToPDF(JTable table, String title) {
		JFileChooser fc = new JFileChooser();
		int option = fc.showSaveDialog(this.view);
		if(option == JFileChooser.APPROVE_OPTION) {
			String file = fc.getSelectedFile().getAbsolutePath();
			Document document = new Document();
			
			try {
				FileOutputStream fos = new FileOutputStream(file + ".pdf");
				PdfWriter.getInstance(document, fos);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			document.open();
			
			BaseFont baseFontHead = null;
			com.itextpdf.text.Font fontHead = null;
			
			BaseFont baseFontValue = null;
			com.itextpdf.text.Font fontValue = null;
			
			try {
				baseFontHead = BaseFont.createFont("C:\\Users\\Admin\\eclipse-workspace\\librarySys\\src\\main\\java\\font\\Roboto-Bold.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
				fontHead = new com.itextpdf.text.Font(baseFontHead, 16);
				
				baseFontValue = BaseFont.createFont("C:\\Users\\Admin\\eclipse-workspace\\librarySys\\src\\main\\java\\font\\Roboto-Light.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
				fontValue = new com.itextpdf.text.Font(baseFontValue, 14);
				fontValue.setColor(0, 0, 0);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Add title as a paragraph
	        Paragraph titleParagraph = new Paragraph(title, fontHead);
	        titleParagraph.setAlignment(Element.ALIGN_CENTER);
	        titleParagraph.setSpacingAfter(20); // Add some spacing after the title
	        try {
	            document.add(titleParagraph);
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        }
			
			PdfPTable tablePDF = new PdfPTable(table.getColumnCount());
			tablePDF.setWidthPercentage(100);
			
			for(int i = 0; i < table.getColumnCount(); i++) {
				PdfPCell cellHead = new PdfPCell();
				Phrase element = new Phrase(table.getColumnName(i), fontHead);
				cellHead.addElement(element);
				tablePDF.addCell(cellHead);
			}
			
			for(int i = 0; i < table.getRowCount(); i++) {
				for(int j = 0; j < table.getColumnCount(); j++) {
					PdfPCell cellValue = new PdfPCell();
					Phrase element = new Phrase(table.getValueAt(i, j) + "", fontValue);
					cellValue.addElement(element);
					tablePDF.addCell(cellValue);
				}
			}
			
			
			try {
				document.add(tablePDF);
			} catch (DocumentException e) {
				e.printStackTrace();
			}
			document.close();
			JOptionPane.showMessageDialog(this.view, "Xuất File thành công");
		}
	}

	public void xuatFileLoaiSach() {
		String[] listBTN = {"EXCEL", "PDF"};
		int option = JOptionPane.showOptionDialog(this.view, "Bạn muốn xuất ra gì?", "Lựa Chọn", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, listBTN, listBTN[0]);
		if (option == 0) {
			exportToExcel(this.view.tblQuanLyLoaiSach, "Quản lý loại sách");
		} else if(option == 1) {
			exportToPDF(this.view.tblQuanLyLoaiSach, "Quản lý loại sách");
		}
	}
	
	public void chooseQLUS() {
		int row_s = this.view.tblQuanLyUser.getSelectedRow();
		this.view.txtEmail_panelCTUS.setText(view.tblQuanLyUser.getValueAt(row_s, 0)+"");
		view.txtPassword_panelCTUS.setText(view.tblQuanLyUser.getValueAt(row_s, 1)+"");
		view.txtHoten_panelCTUS.setText(view.tblQuanLyUser.getValueAt(row_s, 2)+"");
		view.txtSDT_panelCTUS.setText(view.tblQuanLyUser.getValueAt(row_s, 3)+"");
		
		user u = new user();
		u.setUsername(view.tblQuanLyUser.getValueAt(row_s, 0)+"");
		user uDAO = userDAO.getuserDAO().selectG(u);
		ImageIcon imgI_AvatarUs = new ImageIcon(Paths.get("icon\\" + uDAO.getTtcn().getHinh()).toAbsolutePath().toString());
		Image img_AvatarUs = imgI_AvatarUs.getImage();
		Image image_AvatarUs = img_AvatarUs.getScaledInstance(this.view.imgUser_panelCTUS.getWidth(), this.view.imgUser_panelCTUS.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIcon_AvatarUs = new ImageIcon(image_AvatarUs);
		this.view.imgUser_panelCTUS.setText("");
		this.view.imgUser_panelCTUS.setIcon(imageIcon_AvatarUs);
		this.view.txtEmail_panelCTUS.setEditable(false);
	}
	
	public void chooseQLDatsach() {
		int row_s = view.tblQuanLyDatSach.getSelectedRow();
		view.txtEmail_panelCTUS_dkDS.setText((String) view.tblQuanLyDatSach.getValueAt(row_s, 0));
		view.txtMaSach_panelCTUS_dkDS.setText((String) view.tblQuanLyDatSach.getValueAt(row_s, 1));
		view.txtNgayMuon_panelCTUS_dkDS.setText((view.tblQuanLyDatSach.getValueAt(row_s, 2)+"").substring(0, 10));
		view.txtTrangThai_panelCTUS_dkDS.setText( view.tblQuanLyDatSach.getValueAt(row_s, 3)+"");
	}
	
	public void chooseQLYeuCau() {
		int row_s = view.tblQuanLyYeuCau.getSelectedRow();
		view.txtEmail_panelCTUS_dkYC.setText(view.tblQuanLyYeuCau.getValueAt(row_s, 0)+"");
		view.txtLoaiSach_panelCTUS_dkYC.setText(view.tblQuanLyYeuCau.getValueAt(row_s, 1)+"");
		view.txtTenSach_panelCTUS_dkYC.setText(view.tblQuanLyYeuCau.getValueAt(row_s, 2)+"");
		view.txtMoTa_panelCTUS_dkYC.setText(view.tblQuanLyYeuCau.getValueAt(row_s, 3)+"");
		
	}
	
	public void chooseQlDangMuon() {
		this.view.txtMaSach_panelCTUS_dkDM.setEditable(false);
		this.view.txtEmail_panelCTUS_dkDM.setEditable(false);
		int row_s = view.tblQuanLyDangMuon.getSelectedRow();
		view.txtEmail_panelCTUS_dkDM.setText(view.tblQuanLyDangMuon.getValueAt(row_s, 0)+"");
		view.txtMaSach_panelCTUS_dkDM.setText(view.tblQuanLyDangMuon.getValueAt(row_s, 1)+"");
		view.txtNgayMuon_panelCTUS_dkDM.setText((view.tblQuanLyDangMuon.getValueAt(row_s, 2)+"").substring(0, 10));
		view.txtNgayTra_panelCTUS_dkDM.setText((view.tblQuanLyDangMuon.getValueAt(row_s, 3)+"").substring(0, 10));

	}
	
	public void chooseQLQuaHan() {
		int row_s = view.tblQuanLyQuaHan.getSelectedRow();
		view.txtEmail_panelCTUS_dkQH.setText(view.tblQuanLyQuaHan.getValueAt(row_s, 0)+"");
		view.txtMaSach_panelCTUS_dkQH.setText(view.tblQuanLyQuaHan.getValueAt(row_s, 1)+"");
		view.txtNgayMuon_panelCTUS_dkQH.setText((view.tblQuanLyQuaHan.getValueAt(row_s, 2)+"").substring(0, 10));
		view.txtNgayTra_panelCTUS_dkQH.setText((view.tblQuanLyQuaHan.getValueAt(row_s, 3)+"").substring(0, 10));
		
	}
	
	public void btnNewuser() {
		view.txtEmail_panelCTUS.setText("");
		view.txtPassword_panelCTUS.setText("");
		view.txtHoten_panelCTUS.setText("");
		view.txtSDT_panelCTUS.setText("");
		this.view.imgUser_panelCTUS.setIcon(null);
		this.view.imgUser_panelCTUS.setText("Nhấn để tải hình ảnh");
		this.view.txtEmail_panelCTUS.setEditable(true);
		this.view.tblQuanLyUser.clearSelection();
	}
	
	public void btnNewYeuCau() {
		view.txtEmail_panelCTUS_dkYC.setText("");
		view.txtLoaiSach_panelCTUS_dkYC.setText("");
		view.txtTenSach_panelCTUS_dkYC.setText("");
		view.txtMoTa_panelCTUS_dkYC.setText("");
	}
	
	public void btnNewDangMuon() {
		this.view.txtEmail_panelCTUS_dkDM.setText("");
		this.view.txtEmail_panelCTUS_dkDM.setEditable(true);
		this.view.txtMaSach_panelCTUS_dkDM.setText("");
		this.view.txtMaSach_panelCTUS_dkDM.setEditable(true);
		Date d = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ngayMuon = sdf.format(d);
		this.view.txtNgayMuon_panelCTUS_dkDM.setText(ngayMuon);
		d.setMonth(d.getMonth() + 1);
		String ngayTra = sdf.format(d);
		this.view.txtNgayTra_panelCTUS_dkDM.setText(ngayTra);
		this.view.tblQuanLyDangMuon.clearSelection();
	}
	
	public void btnNewQuaHan() {
		view.txtEmail_panelCTUS_dkQH.setText("");
		view.txtMaSach_panelCTUS_dkQH.setText("");
		view.txtNgayMuon_panelCTUS_dkQH.setText("");
		view.txtNgayTra_panelCTUS_dkQH.setText("");
	}

	public void deleteUser() {
		int i = JOptionPane.showConfirmDialog(this.view, "Bạn có chắc chắn muốn xóa người dùng này", "ConfirmDialog", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		if(i == JOptionPane.YES_OPTION) {
			if(this.view.txtEmail_panelCTUS.getText().equals("")) {
				JOptionPane.showMessageDialog(this.view, "Vui lòng chọn dòng cần xóa");
			} else {
				String email = view.txtEmail_panelCTUS.getText();
				String password = view.txtPassword_panelCTUS.getText();
				String hoten = view.txtHoten_panelCTUS.getText();
				String sdt = view.txtSDT_panelCTUS.getText();
				user us = new user();
				us.setUsername(email);
				us.setPassword(password);
				us.setRole("Đọc giả");
				
				int selectRow = this.view.tblQuanLyUser.getSelectedRow();
				userDAO.getuserDAO().deletaX(us);
				JOptionPane.showMessageDialog(this.view, "Xóa thành công");
				
				if(this.view.tblQuanLyUser.getRowCount() == 0) {
					
				} else {
					if(this.view.tblQuanLyUser.getRowCount() - 1 == this.view.tblQuanLyUser.getSelectedRow()) {
						this.loadUser();
						if(this.view.tblQuanLyUser.getRowCount() != 0) {
							this.view.tblQuanLyUser.setRowSelectionInterval(this.view.tblQuanLyUser.getRowCount() - 1, this.view.tblQuanLyUser.getRowCount() - 1);
						}
					} else {
						if(this.view.tblQuanLyUser.getSelectedRow() == 0) {
							this.loadUser();
							this.view.tblQuanLyUser.setRowSelectionInterval(0, 0);
						} else {
							this.loadUser();
							this.view.tblQuanLyUser.setRowSelectionInterval(selectRow, selectRow);
						}
					}
					if(this.view.tblQuanLyUser.getRowCount() != 0) {
						this.choseQLUser();
					}
				}
			}
		}
	}
	
	public void changeAvatar_panelCTUS() {
		JFileChooser fc = new JFileChooser();
		fc.setFileFilter(new FileNameExtensionFilter("JPG & PNG Images", "jpg", "png"));
		int i = fc.showOpenDialog(this.view);
		if(i == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			ImageIcon imgI_AvatarUs = new ImageIcon(Paths.get(file.getAbsolutePath().toString()).toAbsolutePath().toString());
			Image img_AvatarUs = imgI_AvatarUs.getImage();
			Image image_AvatarUs = img_AvatarUs.getScaledInstance(this.view.imgUser_panelCTUS.getWidth(), this.view.imgUser_panelCTUS.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imageIcon_AvatarUs = new ImageIcon(image_AvatarUs);
			this.view.imgUser_panelCTUS.setText("");
			this.view.imgUser_panelCTUS.setIcon(imageIcon_AvatarUs);
			this.view.fileClone = file;
		}
	}
	
	public void addOrUpdateUser() {
		String sdt_test = "^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$";
		String chkEmail = "^[A-Za-z0-9-\\+]+([A-Za-z0-9-]+)*@" + "[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		
		int i = JOptionPane.showConfirmDialog(this.view, "Bạn có chắc chắn với quyết định này?", "Confirm", JOptionPane.YES_NO_OPTION);
		if(i == JOptionPane.YES_OPTION) {
			// luu hinh anh vao file
			String duoi = this.view.fileClone.getName().substring(this.view.fileClone.getName().indexOf(".")+1);
			String name = this.view.fileClone.getName().trim();
			
			String userName = this.view.txtEmail_panelCTUS.getText();
			String passWord = this.view.txtPassword_panelCTUS.getText();
			
			thongTinCaNhan ttcn = new thongTinCaNhan();
			ttcn.setHinh(name);
			ttcn.setSoDienThoai(this.view.txtSDT_panelCTUS.getText());
			ttcn.setTen(this.view.txtHoten_panelCTUS.getText());
			
			try {
				user u = new user(userName, passWord, "Đọc giả", "Tồn tại");
				if(this.view.txtEmail_panelCTUS.getText().equals("") || this.view.txtPassword_panelCTUS.getText().equals("") 
						|| this.view.txtSDT_panelCTUS.getText().equals("") || this.view.txtHoten_panelCTUS.getText().equals("") 
						|| this.view.imgUser_panelCTUS.getText().equals("Nhấn để tải hình ảnh")) 
				{				
					JOptionPane.showMessageDialog(this.view, "Vùi lòng nhập đầy đủ thông tin");
				} else {
					if(this.view.txtEmail_panelCTUS.getText().matches(chkEmail)) {
						if(this.view.txtSDT_panelCTUS.getText().matches(sdt_test)) {
							if(validateName(this.view.txtHoten_panelCTUS.getText())) {
								user uclone = userDAO.getuserDAO().selectG(u);
								Boolean b = uclone == null;
								if(b) {
									userDAO.getuserDAO().insertX(u);
									ttcn.setDiaChi("...");
									ttcn.setEmail(u);
									ttcn.setTrangThai("Tồn tại");
									thongTinCaNhanDAO.getthongTinCaNhanDAO().insertX(ttcn);
									JOptionPane.showMessageDialog(this.view, "Thêm mới thành công");
									try {
										BufferedImage bi = ImageIO.read(this.view.fileClone);
										ImageIO.write(bi, duoi, new File(Paths.get("src\\main\\java\\icon\\" + name).toAbsolutePath().toString()));
										ImageIO.write(bi, duoi, new File(Paths.get("icon\\" + name).toAbsolutePath().toString()));
									} catch (Exception e) {
										e.printStackTrace();
									}
									this.loadUser();
									for (int j = 0; j < this.view.tblQuanLyUser.getRowCount(); j++) {
										if(this.view.tblQuanLyUser.getValueAt(j, 0).equals(u.getUsername())) {
											this.view.tblQuanLyUser.setRowSelectionInterval(j,j);
										}
									}
								} else {
									userDAO.getuserDAO().updateX(u);
									
									ttcn.setMaTTCN(uclone.getTtcn().getMaTTCN());
									ttcn.setDiaChi(uclone.getTtcn().getDiaChi());
									ttcn.setEmail(uclone);
									ttcn.setTrangThai(uclone.getTrangThai());
									
									thongTinCaNhanDAO.getthongTinCaNhanDAO().updateX(ttcn);
									
									JOptionPane.showMessageDialog(this.view, "Cập nhật thành công");
									this.loadUser();
									for (int j = 0; j < this.view.tblQuanLyUser.getRowCount(); j++) {
										if(this.view.tblQuanLyUser.getValueAt(j, 0).equals(uclone.getUsername())) {
											this.view.tblQuanLyUser.setRowSelectionInterval(j,j);
										}
									}
								}
							} else {
								JOptionPane.showMessageDialog(this.view, "Tên không hợp lệ");
							}
						} else {
							JOptionPane.showMessageDialog(this.view, "Số điện thoại không hợp lệ");
						}
					} else {
						JOptionPane.showMessageDialog(this.view, "Email không hợp lê");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void loadUser(){
		DefaultTableModel tableModel_User = (DefaultTableModel)this.view.tblQuanLyUser.getModel();
		tableModel_User.setRowCount(0);
		List<user> luser =  userDAO.getuserDAO().selectAll();
		for (user user : luser) {
			tableModel_User.addRow(new Object[] {
					user.getUsername(),
					user.getPassword(),
					user.getTtcn().getTen(),
					user.getTtcn().getSoDienThoai()
					
			});
		}
	}
	
	public void chuyenTrangThaiPhieuThanhDangMuon() {
		int i = JOptionPane.showConfirmDialog(this.view, "Bạn có chắc chắn với quyết định này?", "Confirm", JOptionPane.YES_NO_OPTION);
		if(i == JOptionPane.YES_OPTION) {
			String userName = this.view.txtEmail_panelCTUS_dkDS.getText();
			user u = new user(); u.setUsername(userName);
			user uDAO = userDAO.getuserDAO().selectG(u);
			
			String maSach = this.view.txtMaSach_panelCTUS_dkDS.getText();
			sach s = new sach(); s.setMaSach(maSach);
			sach sDAO = sachDAO.getsachDAO().selectG(s);
			
			try {
				phieuMuonSach pms = phieuMuonSachDAO.getphieuMuonSachDAO().selectDangDat(sDAO, uDAO);
				pms.setNgayTra(new Date(pms.getNgayMuon().getYear(), pms.getNgayMuon().getMonth() + 1, pms.getNgayMuon().getDay() - 1));
				pms.setTrangThaiPhieu("Đang mượn");
				phieuMuonSachDAO.getphieuMuonSachDAO().updateX(pms);
				JOptionPane.showMessageDialog(this.view, "Cập nhật thành công");
				loadTableDatSach();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void loadTableDatSach() {
		DefaultTableModel tableModel_DatSach = (DefaultTableModel)this.view.tblQuanLyDatSach.getModel();
		tableModel_DatSach.setRowCount(0);
		List<phieuMuonSach> lpms = phieuMuonSachDAO.getphieuMuonSachDAO().selectAllDangDat();
		for (phieuMuonSach phieuMuonSach : lpms) {
			tableModel_DatSach.addRow(new Object[] {
					phieuMuonSach.getEmail().getUsername(),
					phieuMuonSach.getMaSach().getMaSach(),
					phieuMuonSach.getNgayMuon().toString().subSequence(0, 10),
					phieuMuonSach.getTrangThaiPhieu()
			});
		}
	}

	public void deleteDatSach() {
		int i = JOptionPane.showConfirmDialog(this.view, "Bạn có chắc chắn với quyết định này?", "Confirm", JOptionPane.YES_NO_OPTION);
		if(i == JOptionPane.YES_OPTION) {
			String userName = this.view.txtEmail_panelCTUS_dkDS.getText();
			user u = new user(); u.setUsername(userName);
			user uDAO = userDAO.getuserDAO().selectG(u);
			
			String maSach = this.view.txtMaSach_panelCTUS_dkDS.getText();
			sach s = new sach(); s.setMaSach(maSach);
			sach sDAO = sachDAO.getsachDAO().selectG(s);
			
			try {
				phieuMuonSach pms = phieuMuonSachDAO.getphieuMuonSachDAO().selectDangDat(sDAO, uDAO);
				pms.setNgayTra(new Date(pms.getNgayMuon().getYear(), pms.getNgayMuon().getMonth(), pms.getNgayMuon().getDay() - 1));
				pms.setTrangThaiPhieu("Đang đặt");
				phieuMuonSachDAO.getphieuMuonSachDAO().deletaX(pms);
				JOptionPane.showMessageDialog(this.view, "Xóa thành công");
				loadTableDatSach();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	public void chuyenTrangThaiPhieuThanhDaTra_DangMuon() {
		int i = JOptionPane.showConfirmDialog(this.view, "Bạn có chắc chắn với quyết định này?", "Confirm", JOptionPane.YES_NO_OPTION);
		if(i == JOptionPane.YES_OPTION) {
			String userName = this.view.txtEmail_panelCTUS_dkDM.getText();
			user u = new user(); u.setUsername(userName);
			user uDAO = userDAO.getuserDAO().selectG(u);
			
			String maSach = this.view.txtMaSach_panelCTUS_dkDM.getText();
			sach s = new sach(); s.setMaSach(maSach);
			sach sDAO = sachDAO.getsachDAO().selectG(s);
			
			try {
				phieuMuonSach pms = phieuMuonSachDAO.getphieuMuonSachDAO().selectDangMuon(sDAO, uDAO);
				pms.setNgayTra(Calendar.getInstance().getTime());
				pms.setTrangThaiPhieu("Đã trả");
				phieuMuonSachDAO.getphieuMuonSachDAO().updateX(pms);
				JOptionPane.showMessageDialog(this.view, "Cập nhật thành công");
				loadTableDangMuon();
				sDAO.setSoLuong(sDAO.getSoLuong() + 1);
				sachDAO.getsachDAO().updateX(sDAO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
	}
	
	public void loadTableDangMuon() {
		DefaultTableModel tableModel_DangMuon = (DefaultTableModel)this.view.tblQuanLyDangMuon.getModel();
		tableModel_DangMuon.setRowCount(0);
		List<phieuMuonSach> lpms = phieuMuonSachDAO.getphieuMuonSachDAO().selectAllDangMuon();
		for (phieuMuonSach phieuMuonSach : lpms) {
			tableModel_DangMuon.addRow(new Object[] {
					phieuMuonSach.getEmail().getUsername(),
					phieuMuonSach.getMaSach().getMaSach(),
					phieuMuonSach.getNgayMuon().toString().substring(0, 10),
					phieuMuonSach.getNgayTra().toString().substring(0, 10),
					phieuMuonSach.getTrangThaiPhieu()
			});
		}
	}

	public void chuyenTrangThaiPhieuThanhDaTra_QuaHan() {
		int i = JOptionPane.showConfirmDialog(this.view, "Bạn có chắc chắn với quyết định này?", "Confirm", JOptionPane.YES_NO_OPTION);
		if(i == JOptionPane.YES_OPTION) {
			String userName = this.view.txtEmail_panelCTUS_dkQH.getText();
			user u = new user(); u.setUsername(userName);
			user uDAO = userDAO.getuserDAO().selectG(u);
			
			String maSach = this.view.txtMaSach_panelCTUS_dkQH.getText();
			sach s = new sach(); s.setMaSach(maSach);
			sach sDAO = sachDAO.getsachDAO().selectG(s);
			
			try {
				phieuMuonSach pms = phieuMuonSachDAO.getphieuMuonSachDAO().selectQuaHan(sDAO, uDAO);
				pms.setNgayTra(Calendar.getInstance().getTime());
				pms.setTrangThaiPhieu("Đã trả");
				phieuMuonSachDAO.getphieuMuonSachDAO().updateX(pms);
				JOptionPane.showMessageDialog(this.view, "Cập nhật thành công");
				loadTableQuaHan();
				sDAO.setSoLuong(sDAO.getSoLuong() + 1);
				sachDAO.getsachDAO().updateX(sDAO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	
	public void loadTableQuaHan() {
		DefaultTableModel tableModel_QuaHan = (DefaultTableModel)this.view.tblQuanLyQuaHan.getModel();
		tableModel_QuaHan.setRowCount(0);
		List<phieuMuonSach> lpms = phieuMuonSachDAO.getphieuMuonSachDAO().selectAllQuaHan();
		for (phieuMuonSach phieuMuonSach : lpms) {
			tableModel_QuaHan.addRow(new Object[] {
					phieuMuonSach.getEmail().getUsername(),
					phieuMuonSach.getMaSach().getMaSach(),
					phieuMuonSach.getNgayMuon().toString().substring(0, 10),
					phieuMuonSach.getNgayTra().toString().substring(0, 10),
					phieuMuonSach.getTrangThaiPhieu()
					
			});
		}
	}
	
	public void saveDangMuon(){
		String email = this.view.txtEmail_panelCTUS_dkDM.getText();
		user u = new user();
		u.setUsername(email);
		user uDAO = userDAO.getuserDAO().selectG(u);
		if(uDAO == null) {
			JOptionPane.showMessageDialog(this.view, "Email không tồn tại");
		} else {
			String maSach = this.view.txtMaSach_panelCTUS_dkDM.getText();
			sach s = new sach();
			s.setMaSach(maSach);
			sach sDAO = sachDAO.getsachDAO().selectG(s);
			if(sDAO == null) {
				JOptionPane.showMessageDialog(this.view, "Mã sách không đúng");
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				
				try {
					Date ngayMuon = sdf.parse(this.view.txtNgayMuon_panelCTUS_dkDM.getText());
					Date ngayTra = sdf.parse(this.view.txtNgayTra_panelCTUS_dkDM.getText());
					
					Boolean b = false;
					
					for (phieuMuonSach pms : uDAO.getListPM()) {
						if(pms.getMaSach().getMaSach().equals(s.getMaSach())) {
							if(!pms.getTrangThaiPhieu().equals("Đã trả")) {
								b = true; 
								break;
							}
						}
					}
					
					if(b == false) {
						if(sDAO.getSoLuong() <= 0) {
							JOptionPane.showMessageDialog(this.view, "Sách này đang tạm hết. Vui lòng quay lại sau");
						} else {
							phieuMuonSach pms = new phieuMuonSach(null, ngayMuon, ngayTra, uDAO, sDAO, "Đang mượn", "Tồn tại");
							phieuMuonSachDAO.getphieuMuonSachDAO().insertX(pms);
							JOptionPane.showMessageDialog(this.view, "Thêm mới thành công");
							this.loadTableDangMuon();
							this.view.tblQuanLyDangMuon.setRowSelectionInterval(this.view.tblQuanLyDangMuon.getRowCount() - 1, this.view.tblQuanLyDangMuon.getRowCount() - 1);
							this.chooseQlDangMuon();
							sDAO.setSoLuong(sDAO.getSoLuong() - 1);
							sachDAO.getsachDAO().updateX(sDAO);
						}
					} else {
						JOptionPane.showMessageDialog(this.view, "Bạn đã mượn sách này trước đây hoặc đã đặt sách này rồi. Vui lòng trả lại sách để tiến hành đặt lại");
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}

	public void timUserTheoCBX() {
		String email = this.view.txtTimKiem_panelDieuKhienUser.getText();
		user u = new user(); u.setUsername(email);
		
		String cbx = this.view.cbxLoc_panelDieuKhienUser.getSelectedItem() + "";
		String vietTac = cbx.equals("Email") ? "email" : (cbx.equals("Họ và tên") ? "ten" : "soDienThoai");
		List<thongTinCaNhan> list = null;
		
		if(vietTac.equals("email")) {
			list = thongTinCaNhanDAO.getthongTinCaNhanDAO().selectTheoObj(u);
			System.out.println(list.size());
		} else {
			list = thongTinCaNhanDAO.getthongTinCaNhanDAO().selectTheoString(vietTac, this.view.txtTimKiem_panelDieuKhienUser.getText());
		}
		
		DefaultTableModel tableModel_User = (DefaultTableModel)this.view.tblQuanLyUser.getModel();
		tableModel_User.setRowCount(0);
		for (thongTinCaNhan ttcn : list) {
			tableModel_User.addRow(new Object[] {
					ttcn.getEmail().getUsername(),
					ttcn.getEmail().getPassword(),
					ttcn.getTen(),
					ttcn.getSoDienThoai()
			});
		}
	}
	
	public void timPMSTheoCBX_panelDangDat() {
		String email = this.view.txtTimKiem_panelDieuKhienUser_dkDS.getText();
		user u = new user(); u.setUsername(email);
		
		String cbx = this.view.cbxLoc_panelDieuKhienUser_dkDS.getSelectedItem() + "";
		String vietTac = cbx.equals("Email") ? "email.username" : "maSach.maSach";
		List<phieuMuonSach> list = null;
		
		if(vietTac.equals("email.username")) {
			list = phieuMuonSachDAO.getphieuMuonSachDAO().selectTheoObj(u, "Đang đặt");
		} else {
			list = phieuMuonSachDAO.getphieuMuonSachDAO().selectTheoString(vietTac, this.view.txtTimKiem_panelDieuKhienUser_dkDS.getText(), "Đang đặt");
		}
		
		DefaultTableModel tableModel_DatSach = (DefaultTableModel)this.view.tblQuanLyDatSach.getModel();
		tableModel_DatSach.setRowCount(0);
		for (phieuMuonSach phieuMuonSach : list) {
			tableModel_DatSach.addRow(new Object[] {
					phieuMuonSach.getEmail().getUsername(),
					phieuMuonSach.getMaSach().getMaSach(),
					phieuMuonSach.getNgayMuon().toString().subSequence(0, 10),
					phieuMuonSach.getTrangThaiPhieu()
			});
		}
	}
	
	public void timPMSTheoCBX_panelDangMuon() {
		String email = this.view.txtTimKiem_panelDieuKhienUser_dkDM.getText();
		user u = new user(); u.setUsername(email);
		
		String cbx = this.view.cbxLoc_panelDieuKhienUser_dkDM.getSelectedItem() + "";
		String vietTac = cbx.equals("Email") ? "email.username" : "maSach.maSach";
		List<phieuMuonSach> list = null;
		
		if(vietTac.equals("email.username")) {
			list = phieuMuonSachDAO.getphieuMuonSachDAO().selectTheoObj(u, "Đang mượn");
		} else {
			list = phieuMuonSachDAO.getphieuMuonSachDAO().selectTheoString(vietTac, this.view.txtTimKiem_panelDieuKhienUser_dkDM.getText(), "Đang mượn");
		}
		
		DefaultTableModel tableModel_DangMuon = (DefaultTableModel)this.view.tblQuanLyDangMuon.getModel();
		tableModel_DangMuon.setRowCount(0);
		for (phieuMuonSach phieuMuonSach : list) {
			tableModel_DangMuon.addRow(new Object[] {
					phieuMuonSach.getEmail().getUsername(),
					phieuMuonSach.getMaSach().getMaSach(),
					phieuMuonSach.getNgayMuon().toString().substring(0, 10),
					phieuMuonSach.getNgayTra().toString().substring(0, 10),
					phieuMuonSach.getTrangThaiPhieu()
			});
		}
	}

	public void timPMSTheoCBX_panelQuaHan() {
		String email = this.view.txtTimKiem_panelDieuKhienUser_dkQH.getText();
		user u = new user(); u.setUsername(email);
		
		String cbx = this.view.cbxLoc_panelDieuKhienUser_dkQH.getSelectedItem() + "";
		String vietTac = cbx.equals("Email") ? "email.username" : "maSach.maSach";
		List<phieuMuonSach> list = null;
		
		if(vietTac.equals("email.username")) {
			list = phieuMuonSachDAO.getphieuMuonSachDAO().selectTheoObj(u, "Đã quá hạn");
		} else {
			list = phieuMuonSachDAO.getphieuMuonSachDAO().selectTheoString(vietTac, this.view.txtTimKiem_panelDieuKhienUser_dkQH.getText(), "Đã quá hạn");
		}
		
		DefaultTableModel tableModel_QuaHan = (DefaultTableModel)this.view.tblQuanLyQuaHan.getModel();
		tableModel_QuaHan.setRowCount(0);
		for (phieuMuonSach phieuMuonSach : list) {
			tableModel_QuaHan.addRow(new Object[] {
					phieuMuonSach.getEmail().getUsername(),
					phieuMuonSach.getMaSach().getMaSach(),
					phieuMuonSach.getNgayMuon().toString().substring(0, 10),
					phieuMuonSach.getNgayTra().toString().substring(0, 10),
					phieuMuonSach.getTrangThaiPhieu()
			});
		}
	}

	public void timYCTheoCBX_panelYC() {
		String email = this.view.txtTimKiem_panelDieuKhienUser_dkYC.getText();
		user u = new user(); u.setUsername(email);
		
		String cbx = this.view.cbxLoc_panelDieuKhienUser_dkYC.getSelectedItem() + "";
		String vietTac = cbx.equals("Email") ? "email.username" : "";
		List<yeuCau> list = null;
		
		if(vietTac.equals("email.username")) {
			list = yeuCauDAO.getyeuCauDAO().selectTheoObj(u);
		} else {
			
		}
		
		DefaultTableModel tableModel_YeuCau = (DefaultTableModel)this.view.tblQuanLyYeuCau.getModel();
		tableModel_YeuCau.setRowCount(0);
		for (yeuCau yeuCau : list) {
			tableModel_YeuCau.addRow(new Object[] {
					yeuCau.getEmail().getUsername(),
					yeuCau.getLoaiSach(),
					yeuCau.getSach(),
					yeuCau.getMoTa()				
			});
		}
	}

	public void addOrUpdateYeuCau() {
		
	}

	public void changeToChiTietUser() {
		if(this.view.txtEmail_panelCTUS.getText().equals("")) {
			JOptionPane.showMessageDialog(this.view, "Vui lòng chọn user");
		} else {
			this.view.emailClone = this.view.txtEmail_panelCTUS.getText();
			this.view.imgHome_panelTop.setBackground(new Color(244, 244, 244));
			this.view.imgHome_panelTop.setOpaque(false);
			
			this.view.imgMyBook_panelTop.setBackground(new Color(244, 244, 244));
			this.view.imgMyBook_panelTop.setOpaque(false);
			
			this.view.imgBookManager_panelTop.setBackground(new Color(244, 244, 244));
			this.view.imgBookManager_panelTop.setOpaque(false);
			
			this.view.imgUserManager_panelTop.setBackground(new Color(255, 255, 255));
			this.view.imgUserManager_panelTop.setOpaque(true);
			
			this.view.imgThongKe_panelTop.setBackground(new Color(244, 244, 244));
			this.view.imgThongKe_panelTop.setOpaque(false);
			
			this.view.contentPane.removeAll();
			this.view.contentPane.add(this.view.panel_top);
			this.view.contentPane.add(this.view.panel_TTCTUser());
			this.view.contentPane.repaint();
			this.view.contentPane.revalidate();
		}
	}

	public void updatePassWord_panelTTCTUser() {
		String email = this.view.lblEmailUser_panelTop.getText();
		String passowrd = this.view.txtMatKhauUser_panelTop.getText();
		String role = "Đọc giả";
		user u = new user(email, passowrd, role, "Tồn tại");
		userDAO.getuserDAO().updateX(u);
		JOptionPane.showMessageDialog(this.view, "Cập nhật thành công");
	}

	public void xuatFileUser() {
		String[] listBTN = {"EXCEL", "PDF"};
		int option = JOptionPane.showOptionDialog(this.view, "Bạn muốn xuất ra gì?", "Lựa Chọn", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, listBTN, listBTN[0]);
		if (option == 0) {
			exportToExcel(this.view.tblQuanLyUser, "Quản lý User");
		} else if(option == 1) {
			exportToPDF(this.view.tblQuanLyUser, "Quản lý User");
		}
	}

	public void xuatFileDatSach() {
		String[] listBTN = {"EXCEL", "PDF"};
		int option = JOptionPane.showOptionDialog(this.view, "Bạn muốn xuất ra gì?", "Lựa Chọn", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, listBTN, listBTN[0]);
		if (option == 0) {
			exportToExcel(this.view.tblQuanLyDatSach, "Quản lý đặt sách");
		} else if(option == 1) {
			exportToPDF(this.view.tblQuanLyDatSach, "Quản lý đặt sách");
		}
	}

	public void xuatFileYeuCau() {
		String[] listBTN = {"EXCEL", "PDF"};
		int option = JOptionPane.showOptionDialog(this.view, "Bạn muốn xuất ra gì?", "Lựa Chọn", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, listBTN, listBTN[0]);
		if (option == 0) {
			exportToExcel(this.view.tblQuanLyYeuCau, "Quản lý yêu cầu");
		} else if(option == 1) {
			exportToPDF(this.view.tblQuanLyYeuCau, "Quản lý yêu cầu");
		}
	}

	public void xuatFileDangMuon() {
		String[] listBTN = {"EXCEL", "PDF"};
		int option = JOptionPane.showOptionDialog(this.view, "Bạn muốn xuất ra gì?", "Lựa Chọn", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, listBTN, listBTN[0]);
		if (option == 0) {
			exportToExcel(this.view.tblQuanLyDangMuon, "Quản lý sách đang mượn");
		} else if(option == 1) {
			exportToPDF(this.view.tblQuanLyDangMuon, "Quản lý sách đang mượn");
		}
	}

	public void xuatFileQuaHan() {
		String[] listBTN = {"EXCEL", "PDF"};
		int option = JOptionPane.showOptionDialog(this.view, "Bạn muốn xuất ra gì?", "Lựa Chọn", JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION, null, listBTN, listBTN[0]);
		if (option == 0) {
			exportToExcel(this.view.tblQuanLyQuaHan, "Quản lý sách đã quá hạn");
		} else if(option == 1) {
			exportToPDF(this.view.tblQuanLyQuaHan, "Quản lý sách đã quá hạn");
		}
	}
	
	public boolean validateName(String name) {
	    // Check if the name is empty
	    if (name.isEmpty()) {
	        return false;
	    }

	    // Check if the name contains any invalid characters
	    for (char c : name.toCharArray()) {
	        if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
	            return false;
	        }
	    }

	    // Check if the name is too long
	    if (name.length() > 100) {
	        return false;
	    }

	    // The name is valid
	    return true;
	}
	
	public boolean validatePublisherName(String publisherName) {
	    // Check if the publisher name is empty
	    if (publisherName.isEmpty()) {
	        return false;
	    }

	    // Check if the publisher name contains any invalid characters
	    for (char c : publisherName.toCharArray()) {
	        if (!Character.isLetter(c) && !Character.isWhitespace(c) && c != '-') {
	            return false;
	        }
	    }

	    // Check if the publisher name is too long
	    if (publisherName.length() > 100) {
	        return false;
	    }

	    // The publisher name is valid
	    return true;
	}

	public void choseSachCT(String tenSach) {
		this.view.imgHome_panelTop.setBackground(new Color(255, 255, 255));
		this.view.imgHome_panelTop.setOpaque(true);
		
		this.view.imgMyBook_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgMyBook_panelTop.setOpaque(false);
		
		this.view.imgBookManager_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgBookManager_panelTop.setOpaque(false);
		
		this.view.imgUserManager_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgUserManager_panelTop.setOpaque(false);
		
		this.view.imgThongKe_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgThongKe_panelTop.setOpaque(false);
		
		this.view.contentPane.removeAll();
		this.view.contentPane.add(this.view.panel_top);
		this.view.contentPane.add(this.view.panel_TTS(tenSach));
		this.view.contentPane.repaint();
		this.view.contentPane.revalidate();
	}

	public void clickFillterLS_Home(String theLoai) {
		this.view.panel_home.removeAll();
		this.view.panel_mainHome.removeAll();

		int temp = -1;
		int xx = 0;
		
		List<sach> listSach = sachDAO.getsachDAO().selectAll();
		loaiSach lss = loaiSachDAO.getloaiSachDAO().selectTheoTenLS(theLoai);
		List<sach> listS = sachDAO.getsachDAO().selectTheoLS(lss);

		for (sach ds : listSach) {
			if (ds.getMaLoaiSach().getMaLoaiSach().equals(lss.getMaLoaiSach())) {
				temp++;
				if (temp > 8 && temp % 3 == 0) {
					xx++;
					this.view.panel_mainHome.setPreferredSize(new Dimension(this.view.scrollPane_mainHome.getWidth() - 22,
							this.view.scrollPane_mainHome.getHeight() + 730 * xx));
				} else if (temp <= 8 && temp >= 6) {
					this.view.panel_mainHome.setPreferredSize(new Dimension(this.view.scrollPane_mainHome.getWidth() - 22,
							this.view.scrollPane_mainHome.getHeight() + 450));
				} else if (temp <= 5) {
					this.view.panel_mainHome.setPreferredSize(new Dimension(this.view.scrollPane_mainHome.getWidth() - 22,
							this.view.scrollPane_mainHome.getHeight() + 120));
				}

				JPanel panel_sach = new JPanel();
				panel_sach.setName(ds.getTenSach());
				panel_sach.setBackground(new Color(255, 255, 255));
				panel_sach.setForeground(new Color(255, 255, 255));
				panel_sach.setLayout(null);

				JLabel lbltenSach_panelsach = new JLabel(ds.getTenSach());
				lbltenSach_panelsach.setHorizontalAlignment(SwingConstants.CENTER);
				lbltenSach_panelsach.setFont(new Font("Arial", Font.BOLD, 16));
				lbltenSach_panelsach.setBounds(20, 215, 289, 33);
				panel_sach.add(lbltenSach_panelsach);

				JButton btnxemThem_panelsach = new JButton("Xem thêm");
				btnxemThem_panelsach.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnxemThem_panelsach.setBackground(new Color(255, 255, 255));
				btnxemThem_panelsach.setFont(new Font("Arial", Font.BOLD, 14));
				btnxemThem_panelsach.setForeground(Color.blue);
				btnxemThem_panelsach.setBounds(115, 255, 110, 27);
				btnxemThem_panelsach.setFocusPainted(false);
				btnxemThem_panelsach.setOpaque(false);
				btnxemThem_panelsach.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						String tenSach = ((Component)e.getSource()).getParent().getName();
						choseSachCT(tenSach);
					}
				});
				panel_sach.add(btnxemThem_panelsach);

				JLabel imghinhSach_panelsach = new JLabel("");
				imghinhSach_panelsach.setBounds(0, 0, 360, 205);

				ImageIcon imgiconSach = new ImageIcon(
						(Paths.get("icon\\" + ds.getHinhSach())).toString());
				Image imgsach = imgiconSach.getImage();
				Image img = imgsach.getScaledInstance(imghinhSach_panelsach.getWidth(),
						imghinhSach_panelsach.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon imgIcon = new ImageIcon(img);
				imghinhSach_panelsach.setIcon(imgIcon);
				panel_sach.add(imghinhSach_panelsach);
				this.view.panel_mainHome.add(panel_sach);

			}
		}
		
		if(temp < 4) {
			int cell = 4 - temp;
			for(int i = 0; i < cell; i++) {
				JPanel pa = new JPanel();
				this.view.panel_mainHome.add(pa);
				pa.setVisible(false);
			}
		}
		
		this.view.scrollPane_mainHome.setViewportView(this.view.panel_mainHome);
		this.view.scrollPane_theLoai.setVisible(false);
		this.view.panel_home.add(this.view.scrollPane_theLoai);
		this.view.panel_home.add(this.view.panel_sapXep);
		this.view.panel_home.add(this.view.panel_topHome);
		this.view.panel_home.add(this.view.scrollPane_mainHome);
		this.view.panel_home.validate();
		this.view.panel_home.repaint();
	}

	public void searchSach() {
		String duLieu = this.view.txttimKiem_paneltopHome.getText();
		List<sach> listSach = sachDAO.getsachDAO().selectTheoString("tenSach", duLieu);
		this.view.panel_mainHome.removeAll();

		int temp = -1;
		int xx = 0;
		
		for (sach ds : listSach) {
			temp++;
			if (temp > 8 && temp % 3 == 0) {
				xx++;
				this.view.panel_mainHome.setPreferredSize(new Dimension(this.view.scrollPane_mainHome.getWidth() - 22,
						this.view.scrollPane_mainHome.getHeight() + 730 * xx));
			} else if (temp <= 8 && temp >= 6) {
				this.view.panel_mainHome.setPreferredSize(new Dimension(this.view.scrollPane_mainHome.getWidth() - 22,
						this.view.scrollPane_mainHome.getHeight() + 450));
			} else if (temp <= 5) {
				this.view.panel_mainHome.setPreferredSize(new Dimension(this.view.scrollPane_mainHome.getWidth() - 22,
						this.view.scrollPane_mainHome.getHeight() + 120));
			}
			
			JPanel panel_sach = new JPanel();
			panel_sach.setName(ds.getTenSach());
			panel_sach.setBackground(new Color(255, 255, 255));
			panel_sach.setForeground(new Color(255, 255, 255));
			panel_sach.setLayout(null);
			
			JLabel lbltenSach_panelsach = new JLabel(ds.getTenSach());
			lbltenSach_panelsach.setHorizontalAlignment(SwingConstants.CENTER);
			lbltenSach_panelsach.setFont(new Font("Arial", Font.BOLD, 16));
			lbltenSach_panelsach.setBounds(20, 215, 289, 33);
			panel_sach.add(lbltenSach_panelsach);
			
			JButton btnxemThem_panelsach = new JButton("Xem thêm");
			btnxemThem_panelsach.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			btnxemThem_panelsach.setBackground(new Color(255, 255, 255));
			btnxemThem_panelsach.setFont(new Font("Arial", Font.BOLD, 14));
			btnxemThem_panelsach.setForeground(Color.blue);
			btnxemThem_panelsach.setBounds(115, 255, 110, 27);
			btnxemThem_panelsach.setFocusPainted(false);
			btnxemThem_panelsach.setOpaque(false);
			btnxemThem_panelsach.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					String tenSach = ((Component)e.getSource()).getParent().getName();
					choseSachCT(tenSach);
				}
			});
			panel_sach.add(btnxemThem_panelsach);
			
			JLabel imghinhSach_panelsach = new JLabel("");
			imghinhSach_panelsach.setBounds(0, 0, 360, 205);
			
			ImageIcon imgiconSach = new ImageIcon(
					(Paths.get("icon\\" + ds.getHinhSach())).toString());
			Image imgsach = imgiconSach.getImage();
			Image img = imgsach.getScaledInstance(imghinhSach_panelsach.getWidth(),
					imghinhSach_panelsach.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon imgIcon = new ImageIcon(img);
			imghinhSach_panelsach.setIcon(imgIcon);
			panel_sach.add(imghinhSach_panelsach);
			this.view.panel_mainHome.add(panel_sach);
		}
		
		if(temp < 4) {
			int cell = 4 - temp;
			for(int i = 0; i < cell; i++) {
				JPanel pa = new JPanel();
				this.view.panel_mainHome.add(pa);
				pa.setVisible(false);
			}
		}
		
		this.view.scrollPane_mainHome.setViewportView(this.view.panel_mainHome);
		this.view.scrollPane_theLoai.setVisible(false);
		this.view.panel_home.validate();
		this.view.panel_home.repaint();
	}
	
	public void listSach()
	{
		
		this.lpms = this.pmsDAO.selectAll();
		this.lpmsBorrowed.removeAllElements();
		this.lpmsReturned.removeAllElements();
		for(phieuMuonSach pms : lpms)
		{
			if(pms.getTrangThaiPhieu().equals("Đang mượn") && pms.getEmail().getUsername().equals(this.view.emailLogin))
			{
				this.lpmsBorrowed.add(pms.getMaSach().getTenSach());
			}
			if(pms.getTrangThaiPhieu().equals("Đã trả")  && pms.getEmail().getUsername().equals(this.view.emailLogin))
			{
				this.lpmsReturned.add(pms.getMaSach().getTenSach());
			}
		}
		System.err.println(this.lpmsBorrowed.size());
		this.view.listBorrowed_pnlBorrowed.setListData(this.lpmsBorrowed);
		
		this.view.listReturned_pnlReturned.setListData(this.lpmsReturned);
	}
	
	public void searchSach(String str)
	{
		this.lpmsBorrowed.removeAllElements();
		this.lpmsReturned.removeAllElements();
		for(phieuMuonSach pms : lpms)
		{
			if(pms.getTrangThaiPhieu().equals("Đang mượn") && pms.getEmail().getUsername().equals(this.view.emailLogin) && pms.getMaSach().getTenSach().toLowerCase().matches(str))
			{
				this.lpmsBorrowed.add(pms.getMaSach().getTenSach());
			}
			if(pms.getTrangThaiPhieu().equals("Đã trả") && pms.getEmail().getUsername().equals(this.view.emailLogin) && pms.getMaSach().getTenSach().toLowerCase().matches(str))
			{
				this.lpmsReturned.add(pms.getMaSach().getTenSach());
			}
		}
		this.view.listBorrowed_pnlBorrowed.setListData(this.lpmsBorrowed);
		
		this.view.listReturned_pnlReturned.setListData(this.lpmsReturned);

	}
	
	public void loaiSach()
	{
		loaiSachDAO lsDAO = new loaiSachDAO();
		List<loaiSach> ls = new ArrayList<loaiSach>();
		
		ls = lsDAO.selectAll();
		for (loaiSach lsach : ls)
		{
			this.view.cbxTheLoai_function.addItem(lsach.getTenLoaiSach());
		}
	}
	
	public void filterSach(String theloai)
	{
		
		this.lpms = this.pmsDAO.selectAll();
		this.lpmsBorrowed.removeAllElements();
		this.lpmsReturned.removeAllElements();
		for(phieuMuonSach pms : lpms)
		{
			if(pms.getTrangThaiPhieu().equals("Đang mượn") && pms.getMaSach().getMaLoaiSach().getTenLoaiSach().equals(theloai))
			{
				this.lpmsBorrowed.add(pms.getMaSach().getTenSach());
			}
			if(pms.getTrangThaiPhieu().equals("Đã trả") && pms.getMaSach().getMaLoaiSach().getTenLoaiSach().equals(theloai))
			{
				this.lpmsReturned.add(pms.getMaSach().getTenSach());
			}
		}
		this.view.listBorrowed_pnlBorrowed.setListData(this.lpmsBorrowed);
		
		this.view.listReturned_pnlReturned.setListData(this.lpmsReturned);
	}
	
	public void getTTS(String tenSach)
	{
		sach sachClone = new sach();
		for(int i = 0 ; i < lSach.size(); i++)
		{
			if(lSach.get(i).getTenSach().equals(tenSach))
			{
				sachClone = lSach.get(i);
				break;
			}
			
		}
		this.view.imgIBG_pnlTTS = new ImageIcon("icon\\" + sachClone.getHinhSach());
				Image imBG_pnlTTS = this.view.imgIBG_pnlTTS.getImage();
				Image imageBG_pnlTTS = imBG_pnlTTS.getScaledInstance(this.view.imgSach_pnlGT.getWidth(),
						this.view.imgSach_pnlGT.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon imageIconBG_pnlTTS = new ImageIcon(imageBG_pnlTTS);
				this.view.imgSach_pnlGT.setIcon(imageIconBG_pnlTTS);
				this.view.pnl_GT.add(this.view.imgSach_pnlGT);
		this.view.lblTitle_pnlGT.setText(sachClone.getTenSach());
		this.view.txtValue_pnlTTCT.setText(sachClone.getNhaXuatBan()+"\r\n\r\n" + sachClone.getTacGia());
		this.view.txtValue_pnlTTCT_1.setText(sachClone.getNamXB()+"\r\nTái bản lần thứ "+sachClone.getSoLanTaiBan()+"\r\n"+sachClone.getSoLuong());
		this.view.txtMTSP_pnlMTSP.setText(sachClone.getMoTa());
		this.view.txtDG_pnlDG.setText("Đang phát triển!!!");
//		for(int i = 0 ; i < lDG.size(); i++)
//		{
//			if(lDG.get(i).getMaPhieuMuon().getMaSach().getMaSach().equals(sachClone.getMaSach()))
//			{
//				this.view.txtDG_pnlDG.setText(this.view.txtDG_pnlDG.getText().equals("") ? lDG.get(i).getDanhGia() : this.view.txtDG_pnlDG.getText() + lDG.get(i).getDanhGia() + "\n");
//			}
//			
//		}
		
//		System.out.println(sachClone.getTenSach());
		
	}
	
	public void listBorrowedView()
	{
		int brCount = this.lpmsBorrowed.size();

		
		if(brCount < 4)
		{
			this.view.pnlScrBorrowed_scrBorrowed.setBounds(0, 0 , this.view.borrowed_home.getWidth() , (this.view.borrowed_home.getHeight() - 40));
			this.view.pnlScrBorrowed_scrBorrowed.setLayout(new FlowLayout(FlowLayout.LEFT , 10 , 10));
		}
		else
		{
			this.view.pnlScrBorrowed_scrBorrowed.setBounds(0, 0 , this.view.borrowed_home.getWidth()/3*brCount , (this.view.borrowed_home.getHeight() - 40));
			this.view.pnlScrBorrowed_scrBorrowed.setLayout(new GridLayout(1, brCount, 10, 10));
		}

		
		for(phieuMuonSach pms : lpms)
		{
			if(pms.getTrangThaiPhieu().equals("Đang mượn") && pms.getEmail().getUsername().equals(this.view.emailLogin))
			{
				JPanel pnlTemp = new JPanel(null);
				pnlTemp.setPreferredSize(new Dimension(310, 215));
				JLabel imglblBr = new JLabel();
				imglblBr.setBounds(0,0, 310 , 155);
				ImageIcon imgBr = new ImageIcon(Paths.get("icon\\"+pms.getMaSach().getHinhSach()).toAbsolutePath().toString());
				Image imBr = imgBr.getImage();
				Image imageBr = imBr.getScaledInstance(imglblBr.getWidth(), imglblBr.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon imgBr_lblBr = new ImageIcon(imageBr);
				imglblBr.setIcon(imgBr_lblBr);
				pnlTemp.add(imglblBr);
				
				JTextPane tslblBr = new JTextPane();
				StyledDocument doc = tslblBr.getStyledDocument();
				try {
					doc.insertString(doc.getLength(), pms.getMaSach().getTenSach(), null);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SimpleAttributeSet center = new SimpleAttributeSet();
				StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
				doc.setParagraphAttributes(0, doc.getLength(), center, false);
				tslblBr.setBounds(0 , 155 , 310, 60);
				tslblBr.setFont(new Font("Calibri Light" , 0 , 18));
				tslblBr.setEditable(false);
				pnlTemp.add(tslblBr);
				pnlTemp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				pnlTemp.addMouseListener(new MouseAdapter() {
					
					public void mousePressed(MouseEvent e)
					{
						getTTS(pms.getMaSach().getTenSach());
						view.scrPane_TTS.setVisible(true);
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								view.scrPane_TTS.getViewport().setViewPosition(new Point(-1, -1));
							}
						});
					}
				});
				this.view.pnlScrBorrowed_scrBorrowed.add(pnlTemp);
				
				
			}
		}
	}
	
	public void listReturnedView()
	{
		int rtCount = this.lpmsReturned.size();
		
		if(rtCount < 4)
		{
			this.view.pnlScrReturned_scrReturned.setBounds(0, 0 , this.view.returned_home.getWidth() , (this.view.returned_home.getHeight() - 40));
			this.view.pnlScrReturned_scrReturned.setLayout(new FlowLayout(FlowLayout.LEFT , 10 , 40));
		}
		else
		{
			this.view.pnlScrReturned_scrReturned.setBounds(0, 0 , this.view.returned_home.getWidth()/3*rtCount , this.view.returned_home.getHeight() - 40);
			this.view.pnlScrReturned_scrReturned.setLayout(new GridLayout(1, rtCount, 10, 10));
		}
		for(phieuMuonSach pms : lpms)
		{
			
			if(pms.getTrangThaiPhieu().equals("Đã trả") && pms.getEmail().getUsername().equals(this.view.emailLogin))
			{
				JPanel pnlTemp = new JPanel(null);
				pnlTemp.setPreferredSize(new Dimension(310, 215));
				JLabel imglblBr = new JLabel();
				imglblBr.setBounds(0,0, 310 , 155);
				ImageIcon imgBr = new ImageIcon(Paths.get("icon\\"+pms.getMaSach().getHinhSach()).toAbsolutePath().toString());
				Image imBr = imgBr.getImage();
				Image imageBr = imBr.getScaledInstance(imglblBr.getWidth(), imglblBr.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon imgBr_lblBr = new ImageIcon(imageBr);
				imglblBr.setIcon(imgBr_lblBr);
				pnlTemp.add(imglblBr);
				
				JTextPane tslblBr = new JTextPane();
				StyledDocument doc = tslblBr.getStyledDocument();
				try {
					doc.insertString(doc.getLength(), pms.getMaSach().getTenSach(), null);
				} catch (BadLocationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				SimpleAttributeSet center = new SimpleAttributeSet();
				StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
				doc.setParagraphAttributes(0, doc.getLength(), center, false);
				tslblBr.setBounds(0 , 155 , 310, 60);
				tslblBr.setFont(new Font("Calibri Light" , 0 , 18));
				tslblBr.setEditable(false);
				pnlTemp.add(tslblBr);
				pnlTemp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				pnlTemp.addMouseListener(new MouseAdapter() {
					
					public void mousePressed(MouseEvent e)
					{
						getTTS(pms.getMaSach().getTenSach());
						view.scrPane_TTS.setVisible(true);
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								view.scrPane_TTS.getViewport().setViewPosition(new Point(-1, -1));
							}
						});
					}
				});
				this.view.pnlScrReturned_scrReturned.add(pnlTemp);
			}
		}
	}

	public void choseSachCuaToi() {
		this.view.imgHome_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgHome_panelTop.setOpaque(false);
		
		this.view.imgMyBook_panelTop.setBackground(new Color(255, 255, 255));
		this.view.imgMyBook_panelTop.setOpaque(true);
		
		this.view.imgBookManager_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgBookManager_panelTop.setOpaque(false);
		
		this.view.imgUserManager_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgUserManager_panelTop.setOpaque(false);
		
		this.view.imgThongKe_panelTop.setBackground(new Color(244, 244, 244));
		this.view.imgThongKe_panelTop.setOpaque(false);
		
		this.view.contentPane.removeAll();
		this.view.contentPane.add(this.view.panel_top);
		this.view.contentPane.add(this.view.panel_sachCuaToi());
		this.view.contentPane.repaint();
		this.view.contentPane.revalidate();
	}
}
