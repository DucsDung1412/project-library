package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import controller.libraryManagerSystemController;
import dao.danhGiaDAO;
import dao.loaiSachDAO;
import dao.phieuMuonSachDAO;
import dao.sachDAO;
import dao.userDAO;
import dao.yeuCauDAO;
import model.danhGia;
import model.loaiSach;
import model.phieuMuonSach;
import model.sach;
import model.user;
import model.yeuCau;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class libraryManagerSystemView extends JFrame {
	// thong tin
	public String emailLogin = "";
	public String emailClone = "";
	public File fileClone = new File("");
	public String maSach = "";
	public String moTaSach = "";
	
	// controller
	private libraryManagerSystemController controller = new libraryManagerSystemController(this);
	
	// components
	public JPanel contentPane;
	public JTextField txtEmail_panelTTCN;
	public JTextField txtHoTen_panelTTCN;
	public JTextField txtSDT_panelTTCN;
	public JPasswordField txtMatKhau_panelTTCN;
	public JLabel lblTitle_panelTTCT;
	public JTextArea txtDiaChi_panelTTCN;
	public ImageRound imgAvata_panelMain;
	public JLabel imgHome_panelTop, imgMyBook_panelTop, imgBookManager_panelTop, imgUserManager_panelTop, imgThongKe_panelTop;
	public JPanel panel_top;
	private JTable tblTTCTUser;
	public JPanel panel_QLS, panelQLS;
	public JLabel lblLoaiSach_panelNut;
	public JLabel lblSach_panelNut;
	public JLabel lblUser_panelNutUser;
	public JLabel lblYeuCau_panelNutUser;
	public JLabel lblDangMuon_panelNutUser;
	public JLabel lblQuaHan_panelNutUser;
	public JLabel lblDatSach_panelNutUser;
	public JPanel panelNutUser;
	public JPanel panel_QLUS;
	public JTextField txtEmail_panelCTUS;
	
	public JTable tblQuanLySach;
	public JTextField txtMaSach_panelCTS;
	public JTextArea txtTenSach_panelCTS;
	public JTextField txtTacGia_panelCTS;
	public JTextField txtTaiBan_panelCTS;
	public JTextField txtSoLuong_panelCTS;
	public JTextField txtNamXuatBan_panelCTS;
	public JTable tblQuanLyLoaiSach;
	public JTextField txtMaLS_panelCTLS;
	public JTextField txtTenLS_panelCTLS;
	public JTextArea txtMoTa_panelCTLS;
	public JComboBox cbxMaLS_panelCTS;
	public JLabel imgSach_panelCTS;
	public JTextField txtTimKiem_panelDieuKhien;
	public JButton btnMoTa_panelCTS;
	public JTextField txtTimKiem_panelDieuKhienLS;
	public JComboBox cbxLoc_panelDieuKhienLS;
	public JTextField txtNhaXuatBan_panelCTS;
	
	public JTextField txtEmail_panelCTUS_dkQH;
	public JTextField txtNgayMuon_panelCTUS_dkQH;
	public JTextField txtNgayTra_panelCTUS_dkQH;
	public JTable tblQuanLyUser;
	public JTable tblQuanLyDatSach;
	public JTable tblQuanLyYeuCau;
	public JTable tblQuanLyDangMuon;
	public JTable tblQuanLyQuaHan;
	public JTextField txtPassword_panelCTUS;
	public JTextField txtHoten_panelCTUS;
	public JTextField txtSDT_panelCTUS;
	public JTextField txtMaSach_panelCTUS_dkQH;
	public JTextField txtEmail_panelCTUS_dkDM;
	public JTextField txtNgayMuon_panelCTUS_dkDM;
	public JTextField txtNgayTra_panelCTUS_dkDM;
	public JTextField txtMaSach_panelCTUS_dkDM;
	public JTextField txtEmail_panelCTUS_dkDS;
	public JTextField txtNgayMuon_panelCTUS_dkDS;
	public JTextField txtTrangThai_panelCTUS_dkDS;
	public JTextField txtEmail_panelCTUS_dkYC;
	public JTextField txtNgayMuon_panelCTUS_dkYC;
	public JTextField txtNgayTra_panelCTUS_dkYC;
	public JTextField txtMaSach_panelCTUS_dkYC;
	public JTextField txtMaSach_panelCTUS_dkDS;
	public JTextField txtTimKiem_panelDieuKhienUser_dkDS;
	public JTextField txtTimKiem_panelDieuKhienUser;
	public JTextField txtTimKiem_panelDieuKhienUser_dkYC;
	public JTextField txtTimKiem_panelDieuKhienUser_dkDM;
	public JTextField txtTimKiem_panelDieuKhienUser_dkQH;
	public JTextField txtMoTa_panelCTUS_dkYC;
	public JTextField txtLoaiSach_panelCTUS_dkYC;
	public JTextField txtTenSach_panelCTUS_dkYC;
	public JLabel imgUser_panelCTUS;
	public JComboBox cbxLoc_panelDieuKhienUser;
	public JComboBox cbxLoc_panelDieuKhienUser_dkDS;
	public JComboBox cbxLoc_panelDieuKhienUser_dkDM;
	public JComboBox cbxLoc_panelDieuKhienUser_dkQH;
	public JComboBox cbxLoc_panelDieuKhienUser_dkYC;
	public JTextArea txtMatKhauUser_panelTop;
	public JLabel lblEmailUser_panelTop;
	public JLabel lblTitle_panelGT;
	public JTextPane txtValue_panelTTCT_1;
	public JPanel panel_home;
	public JPanel panel_mainHome;
	public JScrollPane scrollPane_mainHome;
	public JScrollPane scrollPane_theLoai;
	public JPanel panel_sapXep;
	public JPanel panel_topHome;
	public JTextField txttimKiem_paneltopHome;
	public JScrollPane scrPane_TTS;
	public JPanel pnl_GT;
	public JLabel imgSach_pnlGT;
	public ImageIcon imgIBG_pnlTTS;
	public JLabel lblTitle_pnlGT;
	public JTextPane txtValue_pnlTTCT;
	public JTextPane txtValue_pnlTTCT_1;
	public JTextPane txtMTSP_pnlMTSP;
	public JTextPane txtDG_pnlDG;
	public JComboBox cbxTheLoai_function;
	public JList listBorrowed_pnlBorrowed;
	public JList listReturned_pnlReturned;
	public JPanel borrowed_home;
	public JPanel pnlScrBorrowed_scrBorrowed;
	public JPanel returned_home;
	public JPanel pnlScrReturned_scrReturned;
	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					libraryManagerSystemView frame = new libraryManagerSystemView("admin");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * .setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	 */
	public libraryManagerSystemView(String email) {
		emailLogin = email;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 800);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		user userLoginClone = new user();
		userLoginClone.setUsername(emailLogin);
		user userLogin = userDAO.getuserDAO().selectG(userLoginClone);
		
		panel_top = new JPanel();
		panel_top.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_top.setBackground(Color.WHITE);
		panel_top.setBounds(-1, 0, this.getWidth() - 14, 148);
		contentPane.add(panel_top);
		panel_top.setLayout(null);

		// logo panelTop
		JLabel imgLogo_panelTop = new JLabel("");
		imgLogo_panelTop.setBounds(16, -18, 155, 156);
		ImageIcon imgILogo_panelTop = new ImageIcon(
				Paths.get("icon\\logo.png").toAbsolutePath().toString());
//		ImageIcon imgILogo_panelTop = new ImageIcon("C:\\Users\\Admin\\OneDrive\\Desktop\\New folder (2)\\logo.png");
		Image imLogo_panelTop = imgILogo_panelTop.getImage();
		Image imageLogo_panelTop = imLogo_panelTop.getScaledInstance(imgLogo_panelTop.getWidth(),
				imgLogo_panelTop.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIconLogo_panelTop = new ImageIcon(imageLogo_panelTop);
		imgLogo_panelTop.setIcon(imageIconLogo_panelTop);
		panel_top.add(imgLogo_panelTop);

		// Home
		imgHome_panelTop = new JLabel("Home");
		imgHome_panelTop.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.choseHome();
			}
		});
		imgHome_panelTop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgHome_panelTop.setBackground(new Color(255, 255, 255));
		imgHome_panelTop.setOpaque(true);
		imgHome_panelTop.setHorizontalAlignment(SwingConstants.CENTER);
		imgHome_panelTop.setFont(new Font("Arial", Font.BOLD, 18));
		imgHome_panelTop.setBounds(276, 0, 74, 109);
		imgHome_panelTop.setVerticalTextPosition(JLabel.BOTTOM);
		imgHome_panelTop.setHorizontalTextPosition(JLabel.CENTER);
		ImageIcon imgIHome_panelTop = new ImageIcon(
				Paths.get("icon\\icon_home_setting.png").toAbsolutePath().toString());
//		ImageIcon imgIHome_panelTop = new ImageIcon("C:\\Users\\Admin\\OneDrive\\Desktop\\New folder (2)\\icon_home_setting.png");
		Image imHome_panelTop = imgIHome_panelTop.getImage();
		Image imageHome_panelTop = imHome_panelTop.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon imageIconHome_panelTop = new ImageIcon(imageHome_panelTop);
		imgHome_panelTop.setIcon(imageIconHome_panelTop);
		panel_top.add(imgHome_panelTop);

		// Sach cua toi
		imgMyBook_panelTop = new JLabel("Sách của tôi");
		imgMyBook_panelTop.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.choseSachCuaToi();
			}
		});
		imgMyBook_panelTop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgMyBook_panelTop.setVerticalTextPosition(SwingConstants.BOTTOM);
		imgMyBook_panelTop.setHorizontalTextPosition(SwingConstants.CENTER);
		imgMyBook_panelTop.setHorizontalAlignment(SwingConstants.CENTER);
		imgMyBook_panelTop.setFont(new Font("Arial", Font.BOLD, 18));
		imgMyBook_panelTop.setBounds(370, 0, 118, 109);
		ImageIcon imgIMyBook_panelTop = new ImageIcon(
				Paths.get("icon\\icon_my_setting.png").toAbsolutePath().toString());
//		ImageIcon imgIMyBook_panelTop = new ImageIcon("C:\\Users\\Admin\\OneDrive\\Desktop\\New folder (2)\\icon_my_setting.png");
		Image imMyBook_panelTop = imgIMyBook_panelTop.getImage();
		Image imageMyBook_panelTop = imMyBook_panelTop.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon imageIconMyBook_panelTop = new ImageIcon(imageMyBook_panelTop);
		imgMyBook_panelTop.setIcon(imageIconMyBook_panelTop);
		panel_top.add(imgMyBook_panelTop);

		// Quan ly sach
		imgBookManager_panelTop = new JLabel("Quản lý sách");
		
		if(userLogin.getRole().equals("Đọc giả")) {
			imgBookManager_panelTop.setVisible(false);
		}
		imgBookManager_panelTop.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.choseQLSach();
			}
		});
		imgBookManager_panelTop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgBookManager_panelTop.setVerticalTextPosition(SwingConstants.BOTTOM);
		imgBookManager_panelTop.setHorizontalTextPosition(SwingConstants.CENTER);
		imgBookManager_panelTop.setHorizontalAlignment(SwingConstants.CENTER);
		imgBookManager_panelTop.setFont(new Font("Arial", Font.BOLD, 18));
		imgBookManager_panelTop.setBounds(508, 0, 118, 109);
		ImageIcon imgIBookManager_panelTop = new ImageIcon(
				Paths.get("icon\\icon_book_setting.png").toAbsolutePath().toString());
//		ImageIcon imgIBookManager_panelTop = new ImageIcon("C:\\Users\\Admin\\OneDrive\\Desktop\\New folder (2)\\icon_book_setting.png");
		Image imBookManager_panelTop = imgIBookManager_panelTop.getImage();
		Image imageBookManager_panelTop = imBookManager_panelTop.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon imageIconBookManager_panelTop = new ImageIcon(imageBookManager_panelTop);
		imgBookManager_panelTop.setIcon(imageIconBookManager_panelTop);
		panel_top.add(imgBookManager_panelTop);

		// quan ly user
		imgUserManager_panelTop = new JLabel("Quản lý user");
		if(userLogin.getRole().equals("Đọc giả")) {
			imgUserManager_panelTop.setVisible(false);
		}
		imgUserManager_panelTop.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.choseQLUser();
			}
		});
		imgUserManager_panelTop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgUserManager_panelTop.setVerticalTextPosition(SwingConstants.BOTTOM);
		imgUserManager_panelTop.setHorizontalTextPosition(SwingConstants.CENTER);
		imgUserManager_panelTop.setHorizontalAlignment(SwingConstants.CENTER);
		imgUserManager_panelTop.setFont(new Font("Arial", Font.BOLD, 18));
		imgUserManager_panelTop.setBounds(646, 0, 118, 109);
		ImageIcon imgIUserManager_panelTop = new ImageIcon(
				Paths.get("icon\\icon_user_setting.png").toAbsolutePath().toString());
//		ImageIcon imgIUserManager_panelTop = new ImageIcon("C:\\Users\\Admin\\OneDrive\\Desktop\\New folder (2)\\icon_user_setting.png");
		Image imUserManager_panelTop = imgIUserManager_panelTop.getImage();
		Image imageUserManager_panelTop = imUserManager_panelTop.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon imageIconUserManager_panelTop = new ImageIcon(imageUserManager_panelTop);
		imgUserManager_panelTop.setIcon(imageIconUserManager_panelTop);
		panel_top.add(imgUserManager_panelTop);

		// Thong ke
		imgThongKe_panelTop = new JLabel("Thống kê");
		if(userLogin.getRole().equals("Đọc giả")) {
			imgThongKe_panelTop.setVisible(false);
		}
		imgThongKe_panelTop.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.choseThongKe();
			}
		});
		imgThongKe_panelTop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgThongKe_panelTop.setVerticalTextPosition(SwingConstants.BOTTOM);
		imgThongKe_panelTop.setHorizontalTextPosition(SwingConstants.CENTER);
		imgThongKe_panelTop.setHorizontalAlignment(SwingConstants.CENTER);
		imgThongKe_panelTop.setFont(new Font("Arial", Font.BOLD, 18));
		imgThongKe_panelTop.setBounds(784, 0, 93, 109);
		ImageIcon imgIThongKe_panelTop = new ImageIcon(
				Paths.get("icon\\icon_thongke_setting.png").toAbsolutePath().toString());
//		ImageIcon imgIThongKe_panelTop = new ImageIcon("C:\\Users\\Admin\\OneDrive\\Desktop\\New folder (2)\\icon_thongke_setting.png");
		Image imThongKe_panelTop = imgIThongKe_panelTop.getImage();
		Image imageThongKe_panelTop = imThongKe_panelTop.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon imageIconThongKe_panelTop = new ImageIcon(imageThongKe_panelTop);
		imgThongKe_panelTop.setIcon(imageIconThongKe_panelTop);
		panel_top.add(imgThongKe_panelTop);

		// separator_panelTop
		JSeparator separator_panelTop = new JSeparator();
		separator_panelTop.setForeground(new Color(0, 0, 0));
		separator_panelTop.setOrientation(SwingConstants.VERTICAL);
		separator_panelTop.setBackground(new Color(0, 0, 0));
		separator_panelTop.setBounds(1160, 0, 1, 109);
		panel_top.add(separator_panelTop);

		// user
		JLabel imgUser_panelTop = new JLabel("");
		imgUser_panelTop.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.choseTTCN();
			}
		});
		imgUser_panelTop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgUser_panelTop.setBounds(1176, 14, 36, 36);
		ImageIcon imgIUser_panelTop = new ImageIcon(
				Paths.get("icon\\user.png").toAbsolutePath().toString());
//		ImageIcon imgIUser_panelTop = new ImageIcon("C:\\Users\\Admin\\OneDrive\\Desktop\\New folder (2)\\icon_user_setting.png");
		Image imUser_panelTop = imgIUser_panelTop.getImage();
		Image imageUser_panelTop = imUser_panelTop.getScaledInstance(imgUser_panelTop.getWidth(),
				imgUser_panelTop.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIconUser_panelTop = new ImageIcon(imageUser_panelTop);
		imgUser_panelTop.setIcon(imageIconUser_panelTop);
		panel_top.add(imgUser_panelTop);

		// noti
		JLabel imgNoti_panelTop = new JLabel("");
		imgNoti_panelTop.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				imgHome_panelTop.setBackground(new Color(244, 244, 244));
				imgHome_panelTop.setOpaque(false);
				
				imgMyBook_panelTop.setBackground(new Color(244, 244, 244));
				imgMyBook_panelTop.setOpaque(false);
				
				imgBookManager_panelTop.setBackground(new Color(244, 244, 244));
				imgBookManager_panelTop.setOpaque(false);
				
				imgUserManager_panelTop.setBackground(new Color(244, 244, 244));
				imgUserManager_panelTop.setOpaque(false);
				
				imgThongKe_panelTop.setBackground(new Color(244, 244, 244));
				imgThongKe_panelTop.setOpaque(false);
			}
		});
		imgNoti_panelTop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgNoti_panelTop.setBounds(1236, 14, 36, 36);
		ImageIcon imgINoti_panelTop = new ImageIcon(
				Paths.get("icon\\thongbao.png").toAbsolutePath().toString());
//		ImageIcon imgINoti_panelTop = new ImageIcon("C:\\Users\\Admin\\OneDrive\\Desktop\\New folder (2)\\icon_user_setting.png");
		Image imNoti_panelTop = imgINoti_panelTop.getImage();
		Image imageNoti_panelTop = imNoti_panelTop.getScaledInstance(imgNoti_panelTop.getWidth(),
				imgNoti_panelTop.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIconNoti_panelTop = new ImageIcon(imageNoti_panelTop);
		imgNoti_panelTop.setIcon(imageIconNoti_panelTop);
		panel_top.add(imgNoti_panelTop);

		// setting
		JLabel imgSetting_panelTop = new JLabel("");
		imgSetting_panelTop.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				imgHome_panelTop.setBackground(new Color(244, 244, 244));
				imgHome_panelTop.setOpaque(false);
				
				imgMyBook_panelTop.setBackground(new Color(244, 244, 244));
				imgMyBook_panelTop.setOpaque(false);
				
				imgBookManager_panelTop.setBackground(new Color(244, 244, 244));
				imgBookManager_panelTop.setOpaque(false);
				
				imgUserManager_panelTop.setBackground(new Color(244, 244, 244));
				imgUserManager_panelTop.setOpaque(false);
				
				imgThongKe_panelTop.setBackground(new Color(244, 244, 244));
				imgThongKe_panelTop.setOpaque(false);
			}
		});
		imgSetting_panelTop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgSetting_panelTop.setBounds(1176, 62, 36, 36);
		ImageIcon imgISetting_panelTop = new ImageIcon(
				Paths.get("icon\\setting_banhrang.png").toAbsolutePath().toString());
//		ImageIcon imgISetting_panelTop = new ImageIcon("C:\\Users\\Admin\\OneDrive\\Desktop\\New folder (2)\\icon_user_setting.png");
		Image imSetting_panelTop = imgISetting_panelTop.getImage();
		Image imageSetting_panelTop = imSetting_panelTop.getScaledInstance(imgSetting_panelTop.getWidth(),
				imgSetting_panelTop.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIconSetting_panelTop = new ImageIcon(imageSetting_panelTop);
		imgSetting_panelTop.setIcon(imageIconSetting_panelTop);
		panel_top.add(imgSetting_panelTop);

		// exit
		JLabel imgLogout_panelTop = new JLabel("");
		imgLogout_panelTop.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.logOut();
			}
		});
		imgLogout_panelTop.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgLogout_panelTop.setBounds(1236, 62, 36, 36);
		ImageIcon imgILogout_panelTop = new ImageIcon(
				Paths.get("icon\\dangxuat.png").toAbsolutePath().toString());
//		ImageIcon imgILogout_panelTop = new ImageIcon("C:\\Users\\Admin\\OneDrive\\Desktop\\New folder (2)\\icon_user_setting.png");
		Image imLogout_panelTop = imgILogout_panelTop.getImage();
		Image imageLogout_panelTop = imLogout_panelTop.getScaledInstance(imgLogout_panelTop.getWidth(),
				imgLogout_panelTop.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIconLogout_panelTop = new ImageIcon(imageLogout_panelTop);
		imgLogout_panelTop.setIcon(imageIconLogout_panelTop);
		panel_top.add(imgLogout_panelTop);

		// image background
		JLabel imgBG_panelTop = new JLabel("");
		imgBG_panelTop.setBackground(new Color(0, 0, 0));
		imgBG_panelTop.setBounds(0, 0, panel_top.getWidth(), panel_top.getHeight());
		ImageIcon imgIBG_panelTop = new ImageIcon(Paths.get("icon\\main_BG1.png").toAbsolutePath().toString());
//		ImageIcon imgIBG_panelTop = new ImageIcon(
//				"C:\\Users\\Admin\\eclipse-workspace\\librarySys\\src\\main\\java\\icon\\main_BG1.png");
		Image imBG_panelTop = imgIBG_panelTop.getImage();
		Image imageBG_panelTop = imBG_panelTop.getScaledInstance(imgBG_panelTop.getWidth(), imgBG_panelTop.getHeight(),
				Image.SCALE_SMOOTH);
		ImageIcon imageIconBG_panelTop = new ImageIcon(imageBG_panelTop);
		imgBG_panelTop.setIcon(imageIconBG_panelTop);
		panel_top.add(imgBG_panelTop);

		// Thêm panel tại đây
//		contentPane.add(panel_TTS("Lập trình hướng đối tượng JAVA core"));
		contentPane.add(panel_Home());
		
	}

	public JPanel panel_Home() {
//		panel home
		panel_home = new JPanel();
		panel_home.setBackground(new Color(255, 255, 255));
		panel_home.setBounds(-1, 147, 1286, 616);
		panel_home.setLayout(null);
		contentPane.add(panel_home);

//		panel top home
		panel_topHome = new JPanel();
		panel_topHome.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_topHome.setBackground(new Color(240, 240, 240));
		panel_topHome.setBounds(75, 15, 1136, 60);
		panel_home.add(panel_topHome);
		panel_topHome.setLayout(null);

//		panel sap xep
		panel_sapXep = new JPanel();
		panel_sapXep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_sapXep.setVisible(false);
			}
		});
		panel_sapXep.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_sapXep.setBounds(160, 100, 159, 90);
		panel_home.add(panel_sapXep);
		panel_sapXep.setLayout(null);
		panel_sapXep.setVisible(false);
		panel_sapXep.setBackground(Color.white);

		JLabel lbltang_panelsapXep = new JLabel("Tên sách A-Z");
		lbltang_panelsapXep.setFont(new Font("Arial", Font.PLAIN, 18));
		lbltang_panelsapXep.setBounds(20, 10, 110, 30);
		panel_sapXep.add(lbltang_panelsapXep);

		JLabel lblgiam_panelsapXep = new JLabel("Tên sách Z-A");
		lblgiam_panelsapXep.setBackground(Color.WHITE);
		lblgiam_panelsapXep.setFont(new Font("Arial", Font.PLAIN, 18));
		lblgiam_panelsapXep.setBounds(20, 50, 110, 32);
		panel_sapXep.add(lblgiam_panelsapXep);

//		JScrollPane the loai
		scrollPane_theLoai = new JScrollPane();
		scrollPane_theLoai.setBounds(75, 100, 270, 150);
		scrollPane_theLoai.setBackground(Color.white);
		scrollPane_theLoai.setVisible(false);

//		panel the loai
		JPanel panel_theLoai = new JPanel();
		panel_theLoai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_theLoai.setVisible(false);
			}
		});
		panel_theLoai.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_theLoai.setBackground(new Color(255, 255, 255));
		panel_theLoai.setBounds(75, 90, 270, 150);
		scrollPane_theLoai.setViewportView(panel_theLoai);
		panel_theLoai.setLayout(new GridLayout(0, 1, 10, 10));

//		JScrollPane main home
		scrollPane_mainHome = new JScrollPane();
		scrollPane_mainHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane_theLoai.setVisible(false);
			}
		});
		scrollPane_mainHome.setBounds(75, 100, 1136, 506);

//		panel main home
		panel_mainHome = new JPanel();
		panel_mainHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_sapXep.setVisible(false);
			}
		});
		panel_mainHome.setBackground(new Color(240, 240, 240));
		scrollPane_mainHome.setViewportView(panel_mainHome);
		panel_mainHome.setLayout(new GridLayout(0, 3, 25, 25));

		JLabel lblsapXep_paneltopHome = new JLabel("Sắp xếp");
		lblsapXep_paneltopHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel_sapXep.setVisible(true);
				scrollPane_theLoai.setVisible(false);
			}
		});
		lblsapXep_paneltopHome.setFont(new Font("Arial", Font.BOLD, 17));
		lblsapXep_paneltopHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblsapXep_paneltopHome.setBounds(120, 0, 90, 60);
		panel_topHome.add(lblsapXep_paneltopHome);

		JLabel lbltheLoai_paneltopHome = new JLabel("Thể loại");
		lbltheLoai_paneltopHome.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				scrollPane_theLoai.setVisible(true);
				panel_sapXep.setVisible(false);
			}
		});

		lbltheLoai_paneltopHome.setHorizontalAlignment(SwingConstants.CENTER);
		lbltheLoai_paneltopHome.setFont(new Font("Arial", Font.BOLD, 17));
		lbltheLoai_paneltopHome.setBounds(10, 0, 90, 60);
		panel_topHome.add(lbltheLoai_paneltopHome);

		JLabel lblyeuCau_paneltopHome = new JLabel("Yêu cầu sách mới");
		lblyeuCau_paneltopHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblyeuCau_paneltopHome.setFont(new Font("Arial", Font.BOLD, 17));
		lblyeuCau_paneltopHome.setBounds(230, 0, 165, 60);
		panel_topHome.add(lblyeuCau_paneltopHome);

		JLabel imgtimKiem_paneltopHome = new JLabel("");
		imgtimKiem_paneltopHome.setBounds(1080, 15, 30, 30);
		// imgtimKiem_panelTopHome.setIcon(new ImageIcon(
		// "C:\\Users\\men\\eclipse-workspace\\LibraryManagerSystem\\LibraryManagerSystem\\src\\main\\java\\icon\\iconSearch.png"));
		ImageIcon imgiconTK = new ImageIcon(Paths.get("icon\\iconSearch.png").toAbsolutePath().toString());
		Image imgTK = imgiconTK.getImage();
		Image imgsearch = imgTK.getScaledInstance(imgtimKiem_paneltopHome.getWidth(),
				imgtimKiem_paneltopHome.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgicon = new ImageIcon(imgsearch);
		imgtimKiem_paneltopHome.setIcon(imgicon);
		panel_topHome.add(imgtimKiem_paneltopHome);
		imgtimKiem_paneltopHome.setHorizontalAlignment(SwingConstants.CENTER);
		imgtimKiem_paneltopHome.setFont(new Font("Calibri", Font.PLAIN, 18));
		panel_topHome.add(imgtimKiem_paneltopHome);

		txttimKiem_paneltopHome = new JTextField();
		txttimKiem_paneltopHome.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				controller.searchSach();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				controller.searchSach();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				controller.searchSach();
			}
		});
		txttimKiem_paneltopHome.setBounds(786, 15, 284, 30);
		panel_topHome.add(txttimKiem_paneltopHome);

		JLabel lblTimKiem_paneltopHome = new JLabel("Tìm kiếm");
		lblTimKiem_paneltopHome.setHorizontalAlignment(SwingConstants.CENTER);
		lblTimKiem_paneltopHome.setFont(new Font("Arial", Font.BOLD, 17));
		lblTimKiem_paneltopHome.setBounds(671, 0, 105, 60);
		panel_topHome.add(lblTimKiem_paneltopHome);

		int temp = -1;
		int xx = 0;

		List<sach> listSach = sachDAO.getsachDAO().selectAll();

		for (sach ds : listSach) {
			temp++;
			if (temp > 8 && temp % 3 == 0) {
				xx++;
				panel_mainHome.setPreferredSize(
						new Dimension(scrollPane_mainHome.getWidth() - 22, scrollPane_mainHome.getHeight() + 730 * xx));
			} else if (temp <= 8 && temp >= 6) {
				panel_mainHome.setPreferredSize(
						new Dimension(scrollPane_mainHome.getWidth() - 22, scrollPane_mainHome.getHeight() + 450));
			} else if (temp <= 5) {
				panel_mainHome.setPreferredSize(
						new Dimension(scrollPane_mainHome.getWidth() - 22, scrollPane_mainHome.getHeight() + 120));
			}

			JPanel panel_sach = new JPanel();
			panel_sach.setName(ds.getTenSach());
			panel_sach.setBackground(new Color(255, 255, 255));
			panel_sach.setForeground(new Color(255, 255, 255));
			panel_sach.setLayout(null);
			panel_mainHome.add(panel_sach);

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
					controller.choseSachCT(tenSach);
				}
			});
			panel_sach.add(btnxemThem_panelsach);

			JLabel imghinhSach_panelsach = new JLabel("");
			imghinhSach_panelsach.setBounds(0, 0, 360, 205);

			ImageIcon imgiconSach = new ImageIcon((Paths.get("icon\\" + ds.getHinhSach())).toString());
			Image imgsach = imgiconSach.getImage();
			Image img = imgsach.getScaledInstance(imghinhSach_panelsach.getWidth(), imghinhSach_panelsach.getHeight(),
					Image.SCALE_SMOOTH);
			ImageIcon imgIcon = new ImageIcon(img);
			imghinhSach_panelsach.setIcon(imgIcon);
			panel_sach.add(imghinhSach_panelsach);

		}

		List<loaiSach> listLoai = loaiSachDAO.getloaiSachDAO().selectAll();

		for (loaiSach loai : listLoai) {
			JLabel loaiSach_paneltheLoai = new JLabel(loai.getTenLoaiSach());
			loaiSach_paneltheLoai.setHorizontalAlignment(SwingConstants.CENTER);
			loaiSach_paneltheLoai.setFont(new Font("Arial", Font.PLAIN, 18));
			loaiSach_paneltheLoai.setBounds(0, 30, 100, 30);
			panel_theLoai.add(loaiSach_paneltheLoai);

			loaiSach_paneltheLoai.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					String theLoai = ((JLabel) e.getSource()).getText();
					controller.clickFillterLS_Home(theLoai);
				};
			});
		}

		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {				
				panel_sapXep.setVisible(false);
				scrollPane_theLoai.setVisible(false);
			}
		});
		panel_home.setLayout(null);

		panel_home.add(scrollPane_theLoai);
		panel_home.add(scrollPane_mainHome);

		return panel_home;

	}

	public JLayeredPane panel_sachCuaToi(){
		JLayeredPane panel_Home = new JLayeredPane();
				panel_Home.setBorder(new EmptyBorder(0, 0, 0, 0));
				panel_Home.setBackground(new Color(255, 255, 255));
				panel_Home.setBounds(0, 148, 1286, 616);
				panel_Home.setLayout(null);
		
				//bat dau code
				
				//Panel TTS

				sach sachClone = new sach();
				this.maSach = "MS08";
				sachClone.setMaSach(maSach);
				sach sach = sachDAO.getsachDAO().selectG(sachClone);
				
				scrPane_TTS = new JScrollPane();
				scrPane_TTS.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				scrPane_TTS.setBackground(Color.WHITE);
				scrPane_TTS.setBounds(300, 0, 987, 615);
				scrPane_TTS.setHorizontalScrollBarPolicy(scrPane_TTS.HORIZONTAL_SCROLLBAR_NEVER);

				JPanel pnl_main = new JPanel();
				pnl_main.setBorder(new EmptyBorder(0, 0, 0, 0));
				pnl_main.setBackground(new Color(255, 255, 255));
				scrPane_TTS.setViewportView(pnl_main);
				pnl_main.setPreferredSize(new Dimension(987, 1274));
				pnl_main.setLayout(new BoxLayout(pnl_main, BoxLayout.Y_AXIS));

				JPanel pnl_TTS = new JPanel();
				pnl_TTS.setBorder(new EmptyBorder(0, 0, 0, 0));
				pnl_TTS.setBackground(new Color(255, 255, 255));
				pnl_TTS.setPreferredSize(new Dimension(987, 4)); // Set the preferred size of panel_1
				pnl_main.add(pnl_TTS);
				pnl_TTS.setLayout(null);
				
				
				// panel Gioi Thieu
				pnl_GT = new JPanel();
				pnl_GT.setBorder(new TitledBorder(
						new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				pnl_GT.setBounds(66, 10, 855, 337);
				pnl_TTS.add(pnl_GT);
				pnl_GT.setLayout(null);

				imgSach_pnlGT = new JLabel("");
				imgSach_pnlGT.setBorder(new TitledBorder(
						new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				imgSach_pnlGT.setBounds(0, 0, 855, 268);
//					ImageIcon imgIBG_panelTop = new ImageIcon(Paths.get("icon\\main_BG1.png").toAbsolutePath().toString());
				imgIBG_pnlTTS = new ImageIcon("E:/Eclipse Project/nhatdlps26322/src/main/java/icon/MS01.png");
				Image imBG_pnlTTS = imgIBG_pnlTTS.getImage();
				Image imageBG_pnlTTS = imBG_pnlTTS.getScaledInstance(imgSach_pnlGT.getWidth(),
						imgSach_pnlGT.getHeight(), Image.SCALE_SMOOTH);
				ImageIcon imageIconBG_pnlTTS = new ImageIcon(imageBG_pnlTTS);
				imgSach_pnlGT.setIcon(imageIconBG_pnlTTS);
				pnl_GT.add(imgSach_pnlGT);

				JButton btnDS_pnlGT = new JButton("Gia hạn");
				btnDS_pnlGT.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						controller.datSach();
					}
				});
				btnDS_pnlGT.setFocusPainted(false);
				btnDS_pnlGT.setForeground(new Color(255, 255, 255));
				btnDS_pnlGT.setBackground(new Color(27, 161, 226));
				btnDS_pnlGT.setFont(new Font("Arial", Font.BOLD, 20));
				btnDS_pnlGT.setBounds(10, 278, 130, 49);
				pnl_GT.add(btnDS_pnlGT);

				lblTitle_pnlGT = new JLabel(
						"Lập trình hướng đối tượng JAVA core dành cho người mới bắt đầu học lập trình");
				lblTitle_pnlGT.setFont(new Font("Arial", Font.PLAIN, 18));
				lblTitle_pnlGT.setBounds(164, 278, 931, 49);
				pnl_GT.add(lblTitle_pnlGT);
				
				
				// panel Thong tin chi tiet
				JPanel pnl_TTCT = new JPanel();
				pnl_TTCT.setBorder(new TitledBorder(
						new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				pnl_TTCT.setBackground(new Color(255, 255, 255));
				pnl_TTCT.setBounds(66, 368, 855, 160);
				pnl_TTS.add(pnl_TTCT);
				pnl_TTCT.setLayout(null);
				
				JLabel lblTitle_pnlTTCT = new JLabel("Thông tin chi tiết");
				lblTitle_pnlTTCT.setFont(new Font("Calibri", Font.BOLD, 20));
				lblTitle_pnlTTCT.setBounds(10, 10, 252, 33);
				pnl_TTCT.add(lblTitle_pnlTTCT);
				
				JTextPane txtKey_pnlTTCT = new JTextPane();
				txtKey_pnlTTCT.setEditable(false);
				txtKey_pnlTTCT.setBackground(new Color(238, 238, 238));
				txtKey_pnlTTCT.setFont(new Font("Calibri Light", Font.PLAIN, 18));
				txtKey_pnlTTCT.setText(" Nhà xuất bản\r\n\r\n Tác giả");
				txtKey_pnlTTCT.setBounds(10, 54, 180, 85);
				pnl_TTCT.add(txtKey_pnlTTCT);

				txtValue_pnlTTCT = new JTextPane();
				txtValue_pnlTTCT.setEditable(false);
				txtValue_pnlTTCT.setText(" Nhà Xuất Bản Lao Động\r\n\r\n Đào Đức Dũng");
				txtValue_pnlTTCT.setFont(new Font("Calibri Light", Font.PLAIN, 18));
				txtValue_pnlTTCT.setBackground(new Color(255, 255, 255));
				txtValue_pnlTTCT.setBounds(210, 54, 252, 98);
				pnl_TTCT.add(txtValue_pnlTTCT);
				
				txtValue_pnlTTCT_1 = new JTextPane();
				txtValue_pnlTTCT_1.setText(" 2021\r\n Tái bản lần thứ nhất\r\n 21 cuốn");
				txtValue_pnlTTCT_1.setFont(new Font("Calibri Light", Font.PLAIN, 18));
				txtValue_pnlTTCT_1.setEditable(false);
				txtValue_pnlTTCT_1.setBackground(Color.WHITE);
				txtValue_pnlTTCT_1.setBounds(676, 54, 175, 98);
				pnl_TTCT.add(txtValue_pnlTTCT_1);
				
				JTextPane txtKey_pnlTTCT_1 = new JTextPane();
				txtKey_pnlTTCT_1.setText(" Năm xuất bản\r\n Số lần tái bản\r\n Số lượng còn lại");
				txtKey_pnlTTCT_1.setFont(new Font("Calibri Light", Font.PLAIN, 18));
				txtKey_pnlTTCT_1.setEditable(false);
				txtKey_pnlTTCT_1.setBackground(new Color(238, 238, 238));
				txtKey_pnlTTCT_1.setBounds(486, 54, 180, 85);
				pnl_TTCT.add(txtKey_pnlTTCT_1);
				
				
				// panel Mo ta san pham
				JPanel pnl_MTSP = new JPanel();
				pnl_MTSP.setLayout(null);
				pnl_MTSP.setBorder(new TitledBorder(
						new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				pnl_MTSP.setBackground(Color.WHITE);
				pnl_MTSP.setBounds(66, 557, 855, 460);
				pnl_TTS.add(pnl_MTSP);

				JLabel lblTitle_pnlMTSP = new JLabel("Mô tả sản phẩm");
				lblTitle_pnlMTSP.setFont(new Font("Calibri", Font.BOLD, 20));
				lblTitle_pnlMTSP.setBounds(10, 10, 252, 33);
				pnl_MTSP.add(lblTitle_pnlMTSP);

				txtMTSP_pnlMTSP = new JTextPane();
				txtMTSP_pnlMTSP.setEditable(false);
				txtMTSP_pnlMTSP.setText( //sach.getMoTa());
						"I. Đôi điều về tác giả\r\nTôi là NEOS.THÀNH (Nguyễn Văn Thành) – Một lập trình viên Java-Android, tác giả cuốn sách “Lập trình hướng đối tượng Java Core”, CEO của công ty TNHH MTV DV   Giáo Dục Thành Nguyên, đồng thời là mentor tại trường ĐH trực tuyến FUNiX, giảng viên giảng dạy tại cao đẳng nghề PolyTechnic,  công ty phần mềm Luvina và công ty phần mềm FPT.\r\n\r\nII. Quyển sách này nói về điều gì?\r\n- JAVA là ngôn ngữ lập trình rất phổ biến nhất hiện nay, học Lập trình hướng đối tượng JAVA bạn sẽ có rất nhiều hướng đi, từ lập trình Mobile app, Java web, Desktop\r\n  App, Game, và tất cả đều sử dụng nền tảng của JAVA CORE.\r\n- Quyển sách này gồm 22 bài học từ Tư duy Lập trình hướng đối tượng JAVA(Đa hình, kế thừa) đến các đối tượng #JavaCore (String, Array, File), lập trình giao diện Swing.\r\n- Quyển sách Lập trình hướng đối tượng JAVA này sẽ giúp bạn:\r\n    + Đi vào thế giới lập trình hết sức tự nhiên, thân thiện và dễ hiểu - LẬP TRÌNH HƯỚNG ĐỐI TƯỢNG LÀ TƯ DUY GẮN LIỀN VỚI CUỘC SỐNG HẰNG NGÀY\r\n    + Nắm vững được thế nào là tư duy lập trình hướng đối tượng và cách phân tích một bài toán lập trình\r\n    + Hiểu được các khái niệm lập trình Java cơ bản.\r\n    + Thực hành xây dựng được các giao diện phần mềm desktop bằng ngôn ngữ JAVA\r\n=> Sau khi có được nền tảng kiến thức Lập trình hướng đối tượng JAVA bạn có thể tự học các ngôn ngữ lập trình hướng đối tượng khác như C++/C, Python,\r\n\r\nIII. Quyển sách này dành cho ai?\r\n- Là sách tham khảo, hướng dẫn tự học Lập trình hướng đối tượng JAVA bằng ngôn ngữ JAVA Core\r\n- Dành cho người mới bắt đầu học lập trình, sinh viên chưa vững tư duy LTHĐT, Java core\r\n- Dành cho người mất gốc hoặc trái ngành muốn học Lập trình hướng đối tượng JAVA");
				txtMTSP_pnlMTSP.setFont(new Font("Calibri Light", Font.PLAIN, 16));
				txtMTSP_pnlMTSP.setBackground(Color.WHITE);
				txtMTSP_pnlMTSP.setBounds(20, 40, 815, 404);
				pnl_MTSP.add(txtMTSP_pnlMTSP);

				
				// panel Danh gia
				JPanel pnl_DG = new JPanel();
				pnl_DG.setBorder(new TitledBorder(
						new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				pnl_DG.setBackground(new Color(255, 255, 255));
				pnl_DG.setBounds(66, 1042, 855, 222);
				pnl_TTS.add(pnl_DG);
				pnl_DG.setLayout(null);
				
				JLabel lblTitle_pnlDG = new JLabel("Đánh giá - nhận xét từ đọc giả");
				lblTitle_pnlDG.setFont(new Font("Calibri", Font.BOLD, 20));
				lblTitle_pnlDG.setBounds(10, 10, 400, 33);
				pnl_DG.add(lblTitle_pnlDG);

				JScrollPane scrPane_pnlDG = new JScrollPane();
				scrPane_pnlDG.setBorder(new TitledBorder(
						new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
						TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				scrPane_pnlDG.setBounds(0, 44, 855, 178);
				pnl_DG.add(scrPane_pnlDG);

				txtDG_pnlDG = new JTextPane();
				txtDG_pnlDG.setEditable(false);
				txtDG_pnlDG.setFont(new Font("Calibri Light", Font.PLAIN, 18));
				txtDG_pnlDG.setText("asjdjasd: aksdhlkasd");
				scrPane_pnlDG.setViewportView(txtDG_pnlDG);

				
				// set scrollPane len dau
				SwingUtilities.invokeLater(new Runnable() {
					@Override
					public void run() {
						scrPane_TTS.getViewport().setViewPosition(new Point(-1, -1));
					}
				});

				// ket thuc code


				
				scrPane_TTS.setVisible(false);
		
				//function home
		JPanel function_home = new JPanel(null);
				function_home.setBounds(0,0, 300 , 652);
		
		JPanel pnlLblHome_function = new JPanel(null);
				pnlLblHome_function.setBackground(Color.WHITE);
				pnlLblHome_function.setBounds(0, 0 ,function_home.getWidth() , 40 );
				pnlLblHome_function.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		
				//Home
		ImageIcon imgHome = new ImageIcon(Paths.get("icon\\home_icon.png").toAbsolutePath().toString());
		Image imHome = imgHome.getImage();
		Image imageHome = imHome.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon imgHome_lblHome = new ImageIcon(imageHome);
		
		JLabel lblHome_function = new JLabel("Home");
				lblHome_function.setVerticalAlignment(SwingConstants.CENTER);
				lblHome_function.setBounds(10,0, 125 , 40);
				lblHome_function.setFont(new Font("Arial", Font.BOLD, 27));
				lblHome_function.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				lblHome_function.addMouseListener(new MouseAdapter() {
					public void mouseClicked (MouseEvent e)
					{
						
					}
					
				});
		lblHome_function.setIcon(imgHome_lblHome);
		pnlLblHome_function.add(lblHome_function);
		
		// ComboBox the loai
		cbxTheLoai_function = new JComboBox<>();
				cbxTheLoai_function.setBounds(0 , 40 ,function_home.getWidth() - 50 , 40 );
				cbxTheLoai_function.setFont(new Font("Arial", 0, 20));
		
		controller.loaiSach();
		cbxTheLoai_function.setSelectedIndex(-1);

		cbxTheLoai_function.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.filterSach(String.valueOf(cbxTheLoai_function.getSelectedItem()));
			}
		});
				
		//Up
		JLabel lblUp_function = new JLabel();
				lblUp_function.setBackground(Color.WHITE);
				lblUp_function.setBounds(250 , 39 , 50 , 41);
				lblUp_function.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				
		ImageIcon imgUp = new ImageIcon(Paths.get("icon\\up_icon.png").toAbsolutePath().toString());
		Image imUp = imgUp.getImage();
		Image imageUp = imUp.getScaledInstance(50, 40, Image.SCALE_SMOOTH);
		ImageIcon imgUp_lblUp = new ImageIcon(imageUp);
				
				lblUp_function.setIcon(imgUp_lblUp);
				lblUp_function.setOpaque(true);
				lblUp_function.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				lblUp_function.addMouseListener(new MouseAdapter()
						{
							public void mouseClicked(MouseEvent e)
							{
								cbxTheLoai_function.setSelectedIndex(-1);
								controller.listSach();
								scrPane_TTS.setVisible(false);
							}
						});

		
				//Search
		
		JLayeredPane pnlSearch_function = new JLayeredPane();
				pnlSearch_function.setBackground(Color.WHITE);
				pnlSearch_function.setBounds(0, 79 ,function_home.getWidth() , 40 );
				pnlSearch_function.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		
		ImageIcon imgSearch = new ImageIcon(Paths.get("icon\\search_icon.png").toAbsolutePath().toString());
		Image imSearch = imgSearch.getImage();
		Image imageSearch = imSearch.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		ImageIcon imgSearch_lblSearch = new ImageIcon(imageSearch);

		
		JLabel lblSearch_function = new JLabel();
				lblSearch_function.setVerticalAlignment(SwingConstants.BOTTOM);
				lblSearch_function.setBounds(258 ,-5 , 45 , 40);
				lblSearch_function.setFont(new Font("Arial", Font.BOLD, 27));
				lblSearch_function.setIcon(imgSearch_lblSearch);
				
		JTextField txtSearch_function = new JTextField();
		txtSearch_function.setBounds(0 , 0 ,300 , 40);
		txtSearch_function.setFont(new Font("Calibri" , 0 , 25));
		
		txtSearch_function.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				String str =  txtSearch_function.getText().toLowerCase();
				if(str.equals(""))
				{
					controller.listSach();
				}
				else
				{
					str = "^"+ str +".*";
					controller.searchSach(str);
				}
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				String str =  txtSearch_function.getText().toLowerCase();
				if(str.equals(""))
				{
					controller.listSach();
				}
				else
				{
					str = "^"+ str +".*";
					controller.searchSach(str);
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		pnlSearch_function.add(txtSearch_function , new Integer(1));
		pnlSearch_function.add(lblSearch_function , new Integer(2));

		
		//Panel Borrowed Book function
		JPanel pnlBorrowed_function = new JPanel(null);
				pnlBorrowed_function.setBackground(Color.WHITE);
				pnlBorrowed_function.setBounds(0, 118 ,function_home.getWidth() , 40 );
				pnlBorrowed_function.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		
		JLabel lblBorrowed_pnlBorrowed = new JLabel("Sách đang mượn");
				lblBorrowed_pnlBorrowed.setVerticalAlignment(SwingConstants.BOTTOM);
				lblBorrowed_pnlBorrowed.setBounds(10,0, 200 , 40);
				lblBorrowed_pnlBorrowed.setFont(new Font("Calibri", Font.BOLD, 23));
				pnlBorrowed_function.add(lblBorrowed_pnlBorrowed);
		
		listBorrowed_pnlBorrowed = new JList<>();
				listBorrowed_pnlBorrowed.setFont(new Font("Arial" , 0 , 15));
				listBorrowed_pnlBorrowed.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						controller.getTTS(listBorrowed_pnlBorrowed.getSelectedValue() + "");
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								scrPane_TTS.getViewport().setViewPosition(new Point(-1, -1));
							}
						});
						scrPane_TTS.setVisible(true);
					}
				});
				listBorrowed_pnlBorrowed.addFocusListener(new FocusAdapter() {
					@Override
					public void focusLost(FocusEvent e) {
						listBorrowed_pnlBorrowed.removeSelectionInterval(listBorrowed_pnlBorrowed.getSelectedIndex(), listBorrowed_pnlBorrowed.getSelectedIndex() );
						if(listReturned_pnlReturned.isSelectionEmpty())
						{
							scrPane_TTS.setVisible(false);
						}
						
					}
				});
				listBorrowed_pnlBorrowed.setBounds(0 , 0 , 300 , 225);
				listBorrowed_pnlBorrowed.setBackground(Color.white);
				listBorrowed_pnlBorrowed.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				listBorrowed_pnlBorrowed.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				listBorrowed_pnlBorrowed.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrListBorrowed = new JScrollPane();
				scrListBorrowed.setBounds(0 , 154, 300, 210);
				scrListBorrowed.setBackground(Color.white);
				scrListBorrowed.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				scrListBorrowed.setViewportView(listBorrowed_pnlBorrowed);
				
				//Panel return book function
		JPanel pnlReturned_function = new JPanel(null);
				pnlReturned_function.setBackground(Color.WHITE);
				pnlReturned_function.setBounds(0, 360 ,function_home.getWidth() , 40 );
				pnlReturned_function.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		
		JLabel lblReturned_pnlReturned = new JLabel("Sách đã trả");
				lblReturned_pnlReturned.setVerticalAlignment(SwingConstants.BOTTOM);
				lblReturned_pnlReturned.setBounds(10,0, 200 , 40);
				lblReturned_pnlReturned.setFont(new Font("Calibri", Font.BOLD, 23));
				pnlReturned_function.add(lblReturned_pnlReturned);
		
		listReturned_pnlReturned = new JList<>();
				listReturned_pnlReturned.setFont(new Font("Arial" , 0 , 15));
				listReturned_pnlReturned.addListSelectionListener(new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						controller.getTTS(listReturned_pnlReturned.getSelectedValue() + "");
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								scrPane_TTS.getViewport().setViewPosition(new Point(-1, -1));
							}
						});
						scrPane_TTS.setVisible(true);
					}
				});
				listReturned_pnlReturned.addFocusListener(new FocusAdapter() {
					@Override
					public void focusLost(FocusEvent e) {
						
						listReturned_pnlReturned.removeSelectionInterval(listReturned_pnlReturned.getSelectedIndex(), listReturned_pnlReturned.getSelectedIndex() );
						System.out.println(listBorrowed_pnlBorrowed.isSelectionEmpty());
						if(listBorrowed_pnlBorrowed.isSelectionEmpty())
						{
							scrPane_TTS.setVisible(false);
						}
					}
				});
				listReturned_pnlReturned.setBounds(0 , 0 , 300 , 230);
				listReturned_pnlReturned.setBackground(Color.white);
				listReturned_pnlReturned.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				listReturned_pnlReturned.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				listReturned_pnlReturned.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		
		JScrollPane scrListReturned = new JScrollPane();
				scrListReturned.setBounds(0 , 397 , 300 , 219);
				scrListReturned.setBackground(Color.white);
				scrListReturned.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				scrListReturned.setViewportView(listReturned_pnlReturned);
		
		controller.listSach();

		
		//Borrowed View
		
		borrowed_home = new JPanel(null);
				borrowed_home.setBackground(Color.WHITE);
				borrowed_home.setBounds(300 , 0 , 990 , 296);
				borrowed_home.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		
		
		JPanel pnlBorrowedView_borrowed = new JPanel(null);
				pnlBorrowedView_borrowed.setBackground(Color.WHITE);
				pnlBorrowedView_borrowed.setBounds(0, 1 , 497 , 39);
		
		JLabel lblBorrowed_pnlBorrowedView = new JLabel("Sách đang mượn ");
				lblBorrowed_pnlBorrowedView.setVerticalAlignment(SwingConstants.BOTTOM);
				lblBorrowed_pnlBorrowedView.setBounds(10 , 0 , 200 , 39);
				lblBorrowed_pnlBorrowedView.setFont(new Font("Calibri", Font.BOLD, 23));
		pnlBorrowedView_borrowed.add(lblBorrowed_pnlBorrowedView);
		
		JScrollPane scrBorrowed_borrowedView = new JScrollPane();
				scrBorrowed_borrowedView.setBounds(0 , 40 , 990, 255);
				scrBorrowed_borrowedView.setBackground(Color.WHITE);
				scrBorrowed_borrowedView.setBorder(new EmptyBorder(0,0,0,0));
				scrBorrowed_borrowedView.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
				
		
		
		pnlScrBorrowed_scrBorrowed = new JPanel();
			// pnlScrBorrowed_scrBorrowed.setBounds(0, 0 , borrowed_home.getWidth() , borrowed_home.getHeight() - 40);
				pnlScrBorrowed_scrBorrowed.setBackground(Color.white);
				controller.listBorrowedView();
				
		scrBorrowed_borrowedView.setViewportView(pnlScrBorrowed_scrBorrowed);
		
		borrowed_home.add(pnlBorrowedView_borrowed);
		borrowed_home.add(scrBorrowed_borrowedView);
		
		//Return view
		
		returned_home = new JPanel(null);
				returned_home.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
				returned_home.setBackground(Color.WHITE);
				returned_home.setBounds(300 , 296 , 990 , 315);
		
		JPanel pnlReturnedView_returned = new JPanel(null);
				pnlReturnedView_returned.setBackground(Color.WHITE);
				pnlReturnedView_returned.setBounds(0, 1 , 495 , 39);
		
		JLabel lblReturned_pnlReturnedView = new JLabel("Sách đã trả ");
				lblReturned_pnlReturnedView.setBackground(Color.WHITE);
				lblReturned_pnlReturnedView.setVerticalAlignment(SwingConstants.BOTTOM);
				lblReturned_pnlReturnedView.setBounds(10 , 0 , 200 , 39);
				lblReturned_pnlReturnedView.setFont(new Font("Calibri", Font.BOLD, 23));
		pnlReturnedView_returned.add(lblReturned_pnlReturnedView);
		
		JScrollPane scrReturned_returned = new JScrollPane();
				scrReturned_returned.setBounds(0 , 40 , 990, 275);
				scrReturned_returned.setBackground(Color.white);
				scrReturned_returned.setBorder(new EmptyBorder(0,0,0,0));
				scrReturned_returned.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		pnlScrReturned_scrReturned = new JPanel();
				pnlScrReturned_scrReturned.setBackground(Color.white);
				controller.listReturnedView();
				
		scrReturned_returned.setViewportView(pnlScrReturned_scrReturned);
		
		returned_home.add(pnlReturnedView_returned);
		returned_home.add(scrReturned_returned);
		

		//add panel
		
		function_home.add(pnlLblHome_function);
		function_home.add(cbxTheLoai_function);
		function_home.add(lblUp_function);
		function_home.add(pnlSearch_function);
		function_home.add(pnlBorrowed_function);
		function_home.add(scrListBorrowed);
		function_home.add(pnlReturned_function);
		function_home.add(scrListReturned);
		
		panel_Home.add(function_home , new Integer(1));
		panel_Home.add(borrowed_home , new Integer(1));
		panel_Home.add(returned_home , new Integer(1));
		panel_Home.add(scrPane_TTS , new Integer(2));
		
		//ket thuc code
		
		return panel_Home;
	}
	
	public JPanel panel_TTCN() {
		// Find user login
//		this.emailLogin = this.emailLogin;
		user userLoginClone = new user();
		userLoginClone.setUsername(emailLogin);
		final user userLogin = userDAO.getuserDAO().selectG(userLoginClone);
		
		JPanel panel_TTCN = new JPanel();
		panel_TTCN.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_TTCN.setBackground(new Color(255, 255, 255));
		panel_TTCN.setBounds(-1, 158, 1286, 616);
		panel_TTCN.setLayout(null);

		// bat dau code

		JPanel panel_main = new JPanel();
		panel_main.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				txtHoTen_panelTTCN.setEditable(true);
				txtMatKhau_panelTTCN.setEditable(true);
				txtSDT_panelTTCN.setEditable(true);
				txtDiaChi_panelTTCN.setEditable(true);
			}
		});
		panel_main.setBackground(new Color(255, 255, 255));
		panel_main.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_main.setBounds(60, 16, 1166, 556);
		panel_TTCN.add(panel_main);
		panel_main.setLayout(null);

		imgAvata_panelMain = new ImageRound();
		imgAvata_panelMain.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		imgAvata_panelMain.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.setAvataUser();
			}
		});
		imgAvata_panelMain.setLocation(493, 50);
		imgAvata_panelMain.setSize(180, 160);
		imgAvata_panelMain.setImage(new javax.swing.ImageIcon(
				Paths.get("icon\\" + userLogin.getTtcn().getHinh()).toAbsolutePath().toString()));
//		imgAvata_panelMain.setImage(new javax.swing.ImageIcon("C:\\Users\\Admin\\eclipse-workspace\\librarySys\\src\\main\\java\\icon\\employee.png")); 
		panel_main.add(imgAvata_panelMain);

		JLabel lblTitle_panelTTCN = new JLabel("Thông tin cá nhân");
		lblTitle_panelTTCN.setFont(new Font("Calibri", Font.BOLD, 30));
		lblTitle_panelTTCN.setBounds(10, 10, 233, 39);
		panel_main.add(lblTitle_panelTTCN);

		JLabel lblEmail_panelTTCN = new JLabel("Email");
		lblEmail_panelTTCN.setFont(new Font("Calibri", Font.BOLD, 26));
		lblEmail_panelTTCN.setBounds(50, 243, 136, 39);
		panel_main.add(lblEmail_panelTTCN);

		JLabel lblHoTen__panelTTCN = new JLabel("Họ & Tên");
		lblHoTen__panelTTCN.setFont(new Font("Calibri", Font.BOLD, 26));
		lblHoTen__panelTTCN.setBounds(50, 302, 136, 39);
		panel_main.add(lblHoTen__panelTTCN);

		JLabel lblDiaChi_panelTTCN = new JLabel("Địa chỉ");
		lblDiaChi_panelTTCN.setFont(new Font("Calibri", Font.BOLD, 26));
		lblDiaChi_panelTTCN.setBounds(50, 361, 136, 39);
		panel_main.add(lblDiaChi_panelTTCN);

		txtEmail_panelTTCN = new JTextField();
		txtEmail_panelTTCN.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtEmail_panelTTCN.setBackground(new Color(255,255,255));
		txtEmail_panelTTCN.setEditable(false);
		txtEmail_panelTTCN.setFont(new Font("Calibri Light", Font.PLAIN, 24));
		txtEmail_panelTTCN.setText(userLogin.getUsername());
		txtEmail_panelTTCN.setBounds(195, 243, 390, 39);
		panel_main.add(txtEmail_panelTTCN);
		txtEmail_panelTTCN.setColumns(10);

		txtHoTen_panelTTCN = new JTextField();
		txtHoTen_panelTTCN.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtHoTen_panelTTCN.getText().equals("")) {
					txtHoTen_panelTTCN.setText(userLogin.getUsername());
				}
			}
		});
		txtHoTen_panelTTCN.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtHoTen_panelTTCN.setBackground(new Color(255,255,255));
		txtHoTen_panelTTCN.setEditable(false);
		txtHoTen_panelTTCN.setText(userLogin.getTtcn().getTen());
		txtHoTen_panelTTCN.setFont(new Font("Calibri Light", Font.PLAIN, 24));
		txtHoTen_panelTTCN.setColumns(10);
		txtHoTen_panelTTCN.setBounds(195, 302, 390, 39);
		panel_main.add(txtHoTen_panelTTCN);

		txtDiaChi_panelTTCN = new JTextArea();
		txtDiaChi_panelTTCN.setEditable(false);
		txtDiaChi_panelTTCN.setLineWrap(true);
		txtDiaChi_panelTTCN.setWrapStyleWord(true);
		txtDiaChi_panelTTCN.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtDiaChi_panelTTCN.setText(userLogin.getTtcn().getDiaChi());
		txtDiaChi_panelTTCN.setFont(new Font("Calibri Light", Font.PLAIN, 24));
		txtDiaChi_panelTTCN.setBounds(197, 368, 919, 113);
		panel_main.add(txtDiaChi_panelTTCN);

		JLabel lblMatKhau_panelTTCN = new JLabel("Mật khẩu");
		lblMatKhau_panelTTCN.setFont(new Font("Calibri", Font.BOLD, 26));
		lblMatKhau_panelTTCN.setBounds(622, 243, 141, 39);
		panel_main.add(lblMatKhau_panelTTCN);

		JLabel lblSDT_panelTTCN = new JLabel("Số điện thoại");
		lblSDT_panelTTCN.setFont(new Font("Calibri", Font.BOLD, 26));
		lblSDT_panelTTCN.setBounds(622, 302, 156, 39);
		panel_main.add(lblSDT_panelTTCN);

		txtSDT_panelTTCN = new JTextField();
		txtSDT_panelTTCN.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtSDT_panelTTCN.setBackground(new Color(255,255,255));
		txtSDT_panelTTCN.setEditable(false);
		txtSDT_panelTTCN.setText(userLogin.getTtcn().getSoDienThoai());
		txtSDT_panelTTCN.setFont(new Font("Calibri Light", Font.PLAIN, 24));
		txtSDT_panelTTCN.setColumns(10);
		txtSDT_panelTTCN.setBounds(788, 302, 328, 39);
		panel_main.add(txtSDT_panelTTCN);

		txtMatKhau_panelTTCN = new JPasswordField();
		txtMatKhau_panelTTCN.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(txtMatKhau_panelTTCN.getText().equals("")) {
					txtMatKhau_panelTTCN.setText(userLogin.getPassword());
				}
			}
		});
		txtMatKhau_panelTTCN.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		txtMatKhau_panelTTCN.setBackground(new Color(255,255,255));
		txtMatKhau_panelTTCN.setEditable(false);
		txtMatKhau_panelTTCN.setText(userLogin.getPassword());
		txtMatKhau_panelTTCN.setFont(new Font("Calibri Light", Font.PLAIN, 24));
		txtMatKhau_panelTTCN.setBounds(788, 243, 328, 39);
		panel_main.add(txtMatKhau_panelTTCN);

		JButton btnLuu_panelTTCN = new JButton("Lưu thay đổi");
		btnLuu_panelTTCN.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLuu_panelTTCN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveUser();
			}
		});
		btnLuu_panelTTCN.setBackground(new Color(255,255,255));
		btnLuu_panelTTCN.setBackground(new Color(192, 192, 192));
		btnLuu_panelTTCN.setFocusPainted(false);
		btnLuu_panelTTCN.setVerticalAlignment(SwingConstants.TOP);
		btnLuu_panelTTCN.setFont(new Font("Calibri", Font.BOLD, 30));
		btnLuu_panelTTCN.setBounds(476, 500, 213, 39);
		panel_main.add(btnLuu_panelTTCN);

		// ket thuc code

		return panel_TTCN;
	}
	
	public JScrollPane panel_TTS(String tenSach) {
		// tim sach
		sach sach = sachDAO.getsachDAO().selectTheoTenSach(tenSach);
		
//		System.out.println(sach.getMoTa());
		
		final JScrollPane scrollPane_TTS = new JScrollPane();
		scrollPane_TTS.setBorder(new EmptyBorder(0, 0, 0, 0));
		scrollPane_TTS.setBackground(new Color(255, 255, 255));
		scrollPane_TTS.setBounds(0, 148, 1286, 616);

		JPanel panel_main = new JPanel();
		panel_main.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_main.setBackground(new Color(255, 255, 255));
		scrollPane_TTS.setViewportView(panel_main);
		panel_main.setPreferredSize(new Dimension(1267, 1274));
		panel_main.setLayout(new BoxLayout(panel_main, BoxLayout.Y_AXIS));

		JPanel panel_TTS = new JPanel();
		panel_TTS.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_TTS.setBackground(new Color(255, 255, 255));
		panel_TTS.setPreferredSize(new Dimension(1267, 4)); // Set the preferred size of panel_1
		panel_main.add(panel_TTS);
		panel_TTS.setLayout(null);
		
		
		// panel Gioi Thieu
		JPanel panel_GT = new JPanel();
		panel_GT.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_GT.setBounds(66, 10, 1140, 337);
		panel_TTS.add(panel_GT);
		panel_GT.setLayout(null);

		JLabel imgSach_panelGT = new JLabel("");
		imgSach_panelGT.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		imgSach_panelGT.setBounds(0, 0, 1140, 268);
//			ImageIcon imgIBG_panelTop = new ImageIcon(Paths.get("icon\\main_BG1.png").toAbsolutePath().toString());
		ImageIcon imgIBG_panelTTS = new ImageIcon(Paths.get("icon\\" + sach.getHinhSach()).toAbsolutePath().toString());
		Image imBG_panelTTS = imgIBG_panelTTS.getImage();
		Image imageBG_panelTTS = imBG_panelTTS.getScaledInstance(imgSach_panelGT.getWidth(),
				imgSach_panelGT.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIconBG_panelTTS = new ImageIcon(imageBG_panelTTS);
		imgSach_panelGT.setIcon(imageIconBG_panelTTS);
		panel_GT.add(imgSach_panelGT);

		JButton btnDatSach_panelGT = new JButton("Đặt sách");
		btnDatSach_panelGT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.datSach();
			}
		});
		btnDatSach_panelGT.setFocusPainted(false);
		btnDatSach_panelGT.setForeground(new Color(255, 255, 255));
		btnDatSach_panelGT.setBackground(new Color(27, 161, 226));
		btnDatSach_panelGT.setFont(new Font("Arial", Font.BOLD, 24));
		btnDatSach_panelGT.setBounds(10, 278, 144, 49);
		panel_GT.add(btnDatSach_panelGT);

		lblTitle_panelGT = new JLabel(sach.getTenSach());
		lblTitle_panelGT.setFont(new Font("Arial", Font.PLAIN, 22));
		lblTitle_panelGT.setBounds(164, 278, 931, 49);
		panel_GT.add(lblTitle_panelGT);
		
		
		// panel Thong tin chi tiet
		JPanel panel_TTCT = new JPanel();
		panel_TTCT.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				System.out.println(1);
			}
		});
		panel_TTCT.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_TTCT.setBackground(new Color(255, 255, 255));
		panel_TTCT.setBounds(66, 368, 1140, 166);
		panel_TTS.add(panel_TTCT);
		panel_TTCT.setLayout(null);
		
		lblTitle_panelTTCT = new JLabel("Thông tin chi tiết");
		lblTitle_panelTTCT.setFont(new Font("Calibri", Font.BOLD, 26));
		lblTitle_panelTTCT.setBounds(10, 10, 252, 33);
		panel_TTCT.add(lblTitle_panelTTCT);
		
		JTextPane txtKey_panelTTCT = new JTextPane();
		txtKey_panelTTCT.setEditable(false);
		txtKey_panelTTCT.setBackground(new Color(238, 238, 238));
		txtKey_panelTTCT.setFont(new Font("Calibri Light", Font.PLAIN, 24));
		txtKey_panelTTCT.setText(" Nhà xuất bản\r\n\r\n Tác giả");
		txtKey_panelTTCT.setBounds(20, 54, 202, 98);
		panel_TTCT.add(txtKey_panelTTCT);

		JTextPane txtValue_panelTTCT = new JTextPane();
		txtValue_panelTTCT.setEditable(false);
		txtValue_panelTTCT.setText(" " + sach.getNhaXuatBan() + "\r\n\r\n " + sach.getTacGia());
		txtValue_panelTTCT.setFont(new Font("Calibri Light", Font.PLAIN, 24));
		txtValue_panelTTCT.setBackground(new Color(255, 255, 255));
		txtValue_panelTTCT.setBounds(232, 54, 361, 98);
		panel_TTCT.add(txtValue_panelTTCT);
		
		txtValue_panelTTCT_1 = new JTextPane();
		txtValue_panelTTCT_1.setText(" " + sach.getNamXB() + "\r\n Tái bản lần thứ " + sach.getSoLanTaiBan() + "\r\n " + sach.getSoLuong() + " cuốn");
		txtValue_panelTTCT_1.setFont(new Font("Calibri Light", Font.PLAIN, 24));
		txtValue_panelTTCT_1.setEditable(false);
		txtValue_panelTTCT_1.setBackground(Color.WHITE);
		txtValue_panelTTCT_1.setBounds(868, 54, 252, 98);
		panel_TTCT.add(txtValue_panelTTCT_1);
		
		JTextPane txtKey_panelTTCT_1 = new JTextPane();
		txtKey_panelTTCT_1.setText(" Năm xuất bản\r\n Số lần tái bản\r\n Số lượng còn lại");
		txtKey_panelTTCT_1.setFont(new Font("Calibri Light", Font.PLAIN, 24));
		txtKey_panelTTCT_1.setEditable(false);
		txtKey_panelTTCT_1.setBackground(new Color(238, 238, 238));
		txtKey_panelTTCT_1.setBounds(656, 54, 202, 98);
		panel_TTCT.add(txtKey_panelTTCT_1);
		
		
		// panel Mo ta san pham
		JPanel panel_MTSP = new JPanel();
		panel_MTSP.setLayout(null);
		panel_MTSP.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_MTSP.setBackground(Color.WHITE);
		panel_MTSP.setBounds(66, 557, 1140, 460);
		panel_TTS.add(panel_MTSP);

		JLabel lblTitle_panelMTSP = new JLabel("Mô tả sản phẩm");
		lblTitle_panelMTSP.setFont(new Font("Calibri", Font.BOLD, 26));
		lblTitle_panelMTSP.setBounds(10, 10, 252, 33);
		panel_MTSP.add(lblTitle_panelMTSP);

		JTextPane txtMTSP_panelMTSP = new JTextPane();
		txtMTSP_panelMTSP.setEditable(false);
		txtMTSP_panelMTSP.setText( sach.getMoTa());
//				"I. Đôi điều về tác giả\r\nTôi là NEOS.THÀNH (Nguyễn Văn Thành) – Một lập trình viên Java-Android, tác giả cuốn sách “Lập trình hướng đối tượng Java Core”, CEO của công ty TNHH MTV DV   Giáo Dục Thành Nguyên, đồng thời là mentor tại trường ĐH trực tuyến FUNiX, giảng viên giảng dạy tại cao đẳng nghề PolyTechnic,  công ty phần mềm Luvina và công ty phần mềm FPT.\r\n\r\nII. Quyển sách này nói về điều gì?\r\n- JAVA là ngôn ngữ lập trình rất phổ biến nhất hiện nay, học Lập trình hướng đối tượng JAVA bạn sẽ có rất nhiều hướng đi, từ lập trình Mobile app, Java web, Desktop\r\n  App, Game, và tất cả đều sử dụng nền tảng của JAVA CORE.\r\n- Quyển sách này gồm 22 bài học từ Tư duy Lập trình hướng đối tượng JAVA(Đa hình, kế thừa) đến các đối tượng #JavaCore (String, Array, File), lập trình giao diện Swing.\r\n- Quyển sách Lập trình hướng đối tượng JAVA này sẽ giúp bạn:\r\n    + Đi vào thế giới lập trình hết sức tự nhiên, thân thiện và dễ hiểu - LẬP TRÌNH HƯỚNG ĐỐI TƯỢNG LÀ TƯ DUY GẮN LIỀN VỚI CUỘC SỐNG HẰNG NGÀY\r\n    + Nắm vững được thế nào là tư duy lập trình hướng đối tượng và cách phân tích một bài toán lập trình\r\n    + Hiểu được các khái niệm lập trình Java cơ bản.\r\n    + Thực hành xây dựng được các giao diện phần mềm desktop bằng ngôn ngữ JAVA\r\n=> Sau khi có được nền tảng kiến thức Lập trình hướng đối tượng JAVA bạn có thể tự học các ngôn ngữ lập trình hướng đối tượng khác như C++/C, Python,\r\n\r\nIII. Quyển sách này dành cho ai?\r\n- Là sách tham khảo, hướng dẫn tự học Lập trình hướng đối tượng JAVA bằng ngôn ngữ JAVA Core\r\n- Dành cho người mới bắt đầu học lập trình, sinh viên chưa vững tư duy LTHĐT, Java core\r\n- Dành cho người mất gốc hoặc trái ngành muốn học Lập trình hướng đối tượng JAVA");
		txtMTSP_panelMTSP.setFont(new Font("Calibri Light", Font.PLAIN, 16));
		txtMTSP_panelMTSP.setBackground(Color.WHITE);
		txtMTSP_panelMTSP.setBounds(20, 40, 1092, 404);
		panel_MTSP.add(txtMTSP_panelMTSP);

		
		// panel Danh gia
		JPanel panel_DG = new JPanel();
		panel_DG.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_DG.setBackground(new Color(255, 255, 255));
		panel_DG.setBounds(66, 1042, 1140, 222);
		panel_TTS.add(panel_DG);
		panel_DG.setLayout(null);
		
		JLabel lblTitle_panelDG = new JLabel("Đánh giá - nhận xét từ đọc giả");
		lblTitle_panelDG.setFont(new Font("Calibri", Font.BOLD, 26));
		lblTitle_panelDG.setBounds(10, 10, 400, 33);
		panel_DG.add(lblTitle_panelDG);

		JScrollPane scrollPane_panelDG = new JScrollPane();
		scrollPane_panelDG.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		scrollPane_panelDG.setBounds(0, 44, 1140, 178);
		panel_DG.add(scrollPane_panelDG);

		JTextPane txtDG_panelDG = new JTextPane();
		txtDG_panelDG.setText("");
		txtDG_panelDG.setEditable(false);
		txtDG_panelDG.setFont(new Font("Calibri Light", Font.PLAIN, 24));
		List<danhGia> list = danhGiaDAO.getdanhGiaDAO().selectAll();
		for (danhGia danhGia : list) {
			if(danhGia.getMaPhieuMuon().getMaSach().getMaSach().equals(sach.getMaSach())) {
				if(txtDG_panelDG.getText().equals("")) {
					txtDG_panelDG.setText(danhGia.getEmail().getTtcn().getTen() + ": " + danhGia.getDanhGia());
				} else {
					txtDG_panelDG.setText(txtDG_panelDG.getText() + "\n" + danhGia.getEmail().getTtcn().getTen() + ": " + danhGia.getDanhGia());
				}
			}
		}
		scrollPane_panelDG.setViewportView(txtDG_panelDG);

		
		// set scrollPane len dau
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				scrollPane_TTS.getViewport().setViewPosition(new Point(0, 0));
			}
		});

		// ket thuc code

		return scrollPane_TTS;
	}
		
	public JPanel panel_QuanLySach() {
		panel_QLS = new JPanel();
		panel_QLS.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_QLS.setBackground(new Color(255, 255, 255));
		panel_QLS.setBounds(0, 148, 1286, 616);
		panel_QLS.setLayout(null);
		
		// bat dau code
		
		panelQLS = new JPanel();
		panelQLS.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelQLS.setBounds(69, 36, 740, 61);
		panel_QLS.add(panelQLS);
		panelQLS.setLayout(null);
		
		lblSach_panelNut = new JLabel("Sách");
		lblSach_panelNut.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.changeToSach();
			}
		});
		lblSach_panelNut.setForeground(new Color(255, 255, 255));
		lblSach_panelNut.setBackground(new Color(27, 161, 226));
		lblSach_panelNut.setOpaque(true);
		lblSach_panelNut.setBounds(0, 0, 146, 61);
		panelQLS.add(lblSach_panelNut);
		lblSach_panelNut.setHorizontalAlignment(SwingConstants.CENTER);
		lblSach_panelNut.setFont(new Font("Arial", Font.BOLD, 24));
		
		lblLoaiSach_panelNut = new JLabel("Loại Sách");
		lblLoaiSach_panelNut.setBackground(new Color(27, 161, 226));
		lblLoaiSach_panelNut.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.changeToLS();
			}
		});
		lblLoaiSach_panelNut.setBounds(166, 0, 169, 61);
		panelQLS.add(lblLoaiSach_panelNut);
		lblLoaiSach_panelNut.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoaiSach_panelNut.setFont(new Font("Arial", Font.BOLD, 24));
		panel_QLS.add(dieuKhienSach());
		panel_QLS.add(tblQuanLySach());
		panel_QLS.add(timKiemSach());
		// ket thuc code
		
		return panel_QLS;
	}
		
	public JPanel timKiemSach() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(829, 36, 388, 61);
		panel_QLS.add(panel);
		panel.setLayout(null);
		
		txtTimKiem_panelDieuKhien = new JTextField();
		txtTimKiem_panelDieuKhien.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				controller.timSachTheoTen();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				controller.timSachTheoTen();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				
			}
		});
		txtTimKiem_panelDieuKhien.setBounds(114, 10, 260, 41);
		panel.add(txtTimKiem_panelDieuKhien);
		txtTimKiem_panelDieuKhien.setFont(new Font("Arial", Font.PLAIN, 18));
		txtTimKiem_panelDieuKhien.setColumns(10);
		
		JLabel lblLoaiSach_panelNut_1 = new JLabel("Tìm Kiếm");
		lblLoaiSach_panelNut_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoaiSach_panelNut_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblLoaiSach_panelNut_1.setBackground(new Color(27, 161, 226));
		lblLoaiSach_panelNut_1.setBounds(2, 0, 104, 61);
		panel.add(lblLoaiSach_panelNut_1);
		
		return panel;
	}
		
	public JPanel dieuKhienSach() {
		JPanel panelDieuKhien = new JPanel();
		panelDieuKhien.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDieuKhien.setBounds(69, 398, 1148, 198);
		panelDieuKhien.setLayout(null);
		
		JButton btnSM_panelCTS = new JButton("Sách Mới");
		btnSM_panelCTS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.sachMoi();
			}
		});
		btnSM_panelCTS.setFont(new Font("Arial", Font.BOLD, 14));
		btnSM_panelCTS.setBounds(1023, 94, 105, 27);
		panelDieuKhien.add(btnSM_panelCTS);
		
		imgSach_panelCTS = new JLabel("Nhấn để tải hình ảnh");
		imgSach_panelCTS.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.changeHinhSach();
			}
		});
		imgSach_panelCTS.setFont(new Font("Arial", Font.BOLD, 12));
		imgSach_panelCTS.setHorizontalAlignment(SwingConstants.CENTER);
		imgSach_panelCTS.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		imgSach_panelCTS.setBounds(20, 20, 156, 155);
		panelDieuKhien.add(imgSach_panelCTS);
		
		JButton btnSS_panelCTS = new JButton("Lưu Sách");
		btnSS_panelCTS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.luuSach();
			}
		});
		btnSS_panelCTS.setFont(new Font("Arial", Font.BOLD, 14));
		btnSS_panelCTS.setBounds(1023, 20, 105, 27);
		panelDieuKhien.add(btnSS_panelCTS);
		
		JButton btnXS_panelCTS = new JButton("Xóa Sách");
		btnXS_panelCTS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.xoaSach();
			}
		});
		btnXS_panelCTS.setFont(new Font("Arial", Font.BOLD, 14));
		btnXS_panelCTS.setBounds(1023, 57, 105, 27);
		panelDieuKhien.add(btnXS_panelCTS);
		
		JLabel lblTenSach_panelCTS = new JLabel("Tên Sách");
		lblTenSach_panelCTS.setFont(new Font("Arial", Font.BOLD, 14));
		lblTenSach_panelCTS.setBounds(467, 84, 105, 27);
		panelDieuKhien.add(lblTenSach_panelCTS);
		
		JLabel lblTacGia_panelCTS = new JLabel("Tác Giả");
		lblTacGia_panelCTS.setFont(new Font("Arial", Font.BOLD, 14));
		lblTacGia_panelCTS.setBounds(467, 10, 105, 27);
		panelDieuKhien.add(lblTacGia_panelCTS);
		
		txtMaSach_panelCTS = new JTextField("");
		txtMaSach_panelCTS.setEditable(false);
		txtMaSach_panelCTS.setFont(new Font("Arial", Font.PLAIN, 14));
		txtMaSach_panelCTS.setBounds(330, 10, 109, 27);
		panelDieuKhien.add(txtMaSach_panelCTS);
		txtMaSach_panelCTS.setColumns(10);
		
		cbxMaLS_panelCTS = new JComboBox();
		cbxMaLS_panelCTS.setFont(new Font("Arial", Font.PLAIN, 14));
		cbxMaLS_panelCTS.setModel(new DefaultComboBoxModel(new String[] {}));
		cbxMaLS_panelCTS.setBounds(330, 47, 109, 27);
		
		DefaultComboBoxModel<String> cbxmodelMLS = (DefaultComboBoxModel<String>) cbxMaLS_panelCTS.getModel();
		List<loaiSach> layloaisach = loaiSachDAO.getloaiSachDAO().selectAll();
		for (loaiSach loaiSach : layloaisach) {
			cbxmodelMLS.addElement(loaiSach.getTenLoaiSach());
		}
		
		panelDieuKhien.add(cbxMaLS_panelCTS);
		
		JLabel lblMaLS_panelCTS = new JLabel("Mã Loại Sách");
		lblMaLS_panelCTS.setFont(new Font("Arial", Font.BOLD, 14));
		lblMaLS_panelCTS.setBounds(196, 47, 105, 27);
		panelDieuKhien.add(lblMaLS_panelCTS);
		
		JLabel lblTaiBan_panelCTS = new JLabel("Tái Bản");
		lblTaiBan_panelCTS.setFont(new Font("Arial", Font.BOLD, 14));
		lblTaiBan_panelCTS.setBounds(196, 84, 105, 27);
		panelDieuKhien.add(lblTaiBan_panelCTS);
		
		JLabel lblSoLuong_panelCTS = new JLabel("Số Lượng");
		lblSoLuong_panelCTS.setFont(new Font("Arial", Font.BOLD, 14));
		lblSoLuong_panelCTS.setBounds(196, 121, 105, 27);
		panelDieuKhien.add(lblSoLuong_panelCTS);
		
		JLabel lblNamXuatBan_panelCTS = new JLabel("Năm Xuất Bản");
		lblNamXuatBan_panelCTS.setFont(new Font("Arial", Font.BOLD, 14));
		lblNamXuatBan_panelCTS.setBounds(196, 158, 105, 27);
		panelDieuKhien.add(lblNamXuatBan_panelCTS);
		
		txtTaiBan_panelCTS = new JTextField();
		txtTaiBan_panelCTS.setFont(new Font("Arial", Font.PLAIN, 14));
		txtTaiBan_panelCTS.setBounds(330, 84, 109, 27);
		panelDieuKhien.add(txtTaiBan_panelCTS);
		txtTaiBan_panelCTS.setColumns(10);
		
		txtSoLuong_panelCTS = new JTextField();
		txtSoLuong_panelCTS.setFont(new Font("Arial", Font.PLAIN, 14));
		txtSoLuong_panelCTS.setBounds(330, 121, 109, 27);
		panelDieuKhien.add(txtSoLuong_panelCTS);
		txtSoLuong_panelCTS.setColumns(10);
		
		txtNamXuatBan_panelCTS = new JTextField();
		txtNamXuatBan_panelCTS.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNamXuatBan_panelCTS.setBounds(330, 158, 109, 27);
		panelDieuKhien.add(txtNamXuatBan_panelCTS);
		txtNamXuatBan_panelCTS.setColumns(10);
		
		JLabel lblMaLS_panelCTS_1 = new JLabel("Mã Sách");
		lblMaLS_panelCTS_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblMaLS_panelCTS_1.setBounds(196, 10, 105, 27);
		panelDieuKhien.add(lblMaLS_panelCTS_1);
		
		JLabel lblNhaXuatBan_panelCTS = new JLabel("Nhà Xuất Bản");
		lblNhaXuatBan_panelCTS.setFont(new Font("Arial", Font.BOLD, 14));
		lblNhaXuatBan_panelCTS.setBounds(467, 47, 105, 27);
		panelDieuKhien.add(lblNhaXuatBan_panelCTS);
		
		txtNhaXuatBan_panelCTS = new JTextField();
		txtNhaXuatBan_panelCTS.setFont(new Font("Arial", Font.PLAIN, 14));
		txtNhaXuatBan_panelCTS.setColumns(10);
		txtNhaXuatBan_panelCTS.setBounds(582, 47, 420, 27);
		panelDieuKhien.add(txtNhaXuatBan_panelCTS);
		
		btnMoTa_panelCTS = new JButton("Xem Mô Tả");
		btnMoTa_panelCTS.setEnabled(false);
		btnMoTa_panelCTS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.xemMoTaSach();
			}
		});
		btnMoTa_panelCTS.setFont(new Font("Arial", Font.BOLD, 16));
		btnMoTa_panelCTS.setBounds(582, 158, 131, 27);
		panelDieuKhien.add(btnMoTa_panelCTS);
		
		txtTenSach_panelCTS = new JTextArea();
		txtTenSach_panelCTS.setFont(new Font("Arial", Font.PLAIN, 14));
		txtTenSach_panelCTS.setLineWrap(true);
		txtTenSach_panelCTS.setBounds(582, 84, 420, 55);
		panelDieuKhien.add(txtTenSach_panelCTS);
		
		JLabel lblTenSach_panelCTS_1 = new JLabel("Mô tả");
		lblTenSach_panelCTS_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblTenSach_panelCTS_1.setBounds(467, 158, 105, 27);
		panelDieuKhien.add(lblTenSach_panelCTS_1);
		
		JButton btnMoTa_panelCTS_1 = new JButton("Thêm Mô Tả");
		btnMoTa_panelCTS_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.themMoTa();
			}
		});
		btnMoTa_panelCTS_1.setFont(new Font("Arial", Font.BOLD, 16));
		btnMoTa_panelCTS_1.setBounds(733, 158, 131, 27);
		panelDieuKhien.add(btnMoTa_panelCTS_1);
		
		txtTacGia_panelCTS = new JTextField();
		txtTacGia_panelCTS.setFont(new Font("Arial", Font.PLAIN, 14));
		txtTacGia_panelCTS.setColumns(10);
		txtTacGia_panelCTS.setBounds(582, 10, 420, 27);
		panelDieuKhien.add(txtTacGia_panelCTS);
		
		JButton btnXF_panelCTS = new JButton("Xuất File");
		btnXF_panelCTS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.xuatFileSach();
			}
		});
		btnXF_panelCTS.setFont(new Font("Arial", Font.BOLD, 14));
		btnXF_panelCTS.setBounds(1023, 144, 105, 27);
		panelDieuKhien.add(btnXF_panelCTS);
		
		return panelDieuKhien;
	}
		
	public JPanel dieuKhienLoaiSach () {
		JPanel panelDieuKhienLS = new JPanel();
		panelDieuKhienLS.setBounds(845, 36, 374, 540);
		panelDieuKhienLS.setLayout(null);
		
		JLabel lblTimKiem_panelDieuKhienLS = new JLabel("Tìm kiếm");
		lblTimKiem_panelDieuKhienLS.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTimKiem_panelDieuKhienLS.setBounds(10, 10, 103, 43);
		panelDieuKhienLS.add(lblTimKiem_panelDieuKhienLS);
		
		txtTimKiem_panelDieuKhienLS = new JTextField();
		txtTimKiem_panelDieuKhienLS.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				controller.timLoaiSachTheoCBX();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				controller.timLoaiSachTheoCBX();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		txtTimKiem_panelDieuKhienLS.setFont(new Font("Arial", Font.PLAIN, 18));
		txtTimKiem_panelDieuKhienLS.setBounds(40, 50, 294, 36);
		panelDieuKhienLS.add(txtTimKiem_panelDieuKhienLS);
		txtTimKiem_panelDieuKhienLS.setColumns(10);
		
		JLabel lblDK_panelDieuKhienLS = new JLabel("Điều khiển");
		lblDK_panelDieuKhienLS.setFont(new Font("Calibri", Font.BOLD, 24));
		lblDK_panelDieuKhienLS.setBounds(10, 157, 134, 43);
		panelDieuKhienLS.add(lblDK_panelDieuKhienLS);
		
		cbxLoc_panelDieuKhienLS = new JComboBox();
		cbxLoc_panelDieuKhienLS.setFont(new Font("Arial", Font.PLAIN, 18));
		cbxLoc_panelDieuKhienLS.setModel(new DefaultComboBoxModel(new String[] {"Mã loại sách", "Tên loại sách"}));
		cbxLoc_panelDieuKhienLS.setOpaque(true);
		cbxLoc_panelDieuKhienLS.setBounds(40, 111, 294, 36);
		panelDieuKhienLS.add(cbxLoc_panelDieuKhienLS);
		
		JPanel panelCTLS = new JPanel();
		panelCTLS.setBounds(10, 190, 354, 340);
		panelDieuKhienLS.add(panelCTLS);
		panelCTLS.setLayout(null);
		
		JButton btnNew_panelCTLS = new JButton("Làm mới");
		btnNew_panelCTLS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.lamMoiLoaiSach();
			}
		});
		btnNew_panelCTLS.setFont(new Font("Arial", Font.BOLD, 14));
		btnNew_panelCTLS.setBounds(122, 279, 105, 27);
		panelCTLS.add(btnNew_panelCTLS);
		
		JButton btnSave_panelCTLS = new JButton("Lưu Loại Sách");
		btnSave_panelCTLS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.luuLoaiSach();
			}
		});
		btnSave_panelCTLS.setFont(new Font("Arial", Font.BOLD, 14));
		btnSave_panelCTLS.setBounds(20, 313, 139, 27);
		panelCTLS.add(btnSave_panelCTLS);
		
		JButton btnDelete_panelCTLS = new JButton("Xóa Lọai Sách");
		btnDelete_panelCTLS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.xoaLoaiSach();
			}
		});
		btnDelete_panelCTLS.setFont(new Font("Arial", Font.BOLD, 14));
		btnDelete_panelCTLS.setBounds(188, 313, 139, 27);
		panelCTLS.add(btnDelete_panelCTLS);
		
		JLabel lblMaLS_panelCTLS = new JLabel("Mã Loại Sách");
		lblMaLS_panelCTLS.setFont(new Font("Arial", Font.BOLD, 12));
		lblMaLS_panelCTLS.setBounds(0, 10, 90, 20);
		panelCTLS.add(lblMaLS_panelCTLS);
		
		JLabel lblMoTa_panelCTLS = new JLabel("Mô Tả");
		lblMoTa_panelCTLS.setFont(new Font("Arial", Font.BOLD, 12));
		lblMoTa_panelCTLS.setBounds(0, 150, 60, 20);
		panelCTLS.add(lblMoTa_panelCTLS);
		
		txtMaLS_panelCTLS = new JTextField();
		txtMaLS_panelCTLS.setEditable(false);
		txtMaLS_panelCTLS.setFont(new Font("Arial", Font.PLAIN, 14));
		txtMaLS_panelCTLS.setBounds(20, 40, 307, 30);
		panelCTLS.add(txtMaLS_panelCTLS);
		txtMaLS_panelCTLS.setColumns(10);
		
		JLabel lblTenLS_panelCTLS = new JLabel("Tên Loại Sách");
		lblTenLS_panelCTLS.setFont(new Font("Arial", Font.BOLD, 12));
		lblTenLS_panelCTLS.setBounds(0, 80, 90, 20);
		panelCTLS.add(lblTenLS_panelCTLS);
		
		 txtTenLS_panelCTLS = new JTextField();
		txtTenLS_panelCTLS.setFont(new Font("Arial", Font.PLAIN, 14));
		txtTenLS_panelCTLS.setColumns(10);
		txtTenLS_panelCTLS.setBounds(20, 110, 307, 30);
		panelCTLS.add(txtTenLS_panelCTLS);
		
		txtMoTa_panelCTLS = new JTextArea();
		txtMoTa_panelCTLS.setFont(new Font("Arial", Font.PLAIN, 14));
		txtMoTa_panelCTLS.setLineWrap(true);
		txtMoTa_panelCTLS.setBounds(20, 180, 307, 91);
		panelCTLS.add(txtMoTa_panelCTLS);
		
		JButton btnXuatFileLS_panelDieuKhienLS = new JButton("Xuất File");
		btnXuatFileLS_panelDieuKhienLS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.xuatFileLoaiSach();
			}
		});
		btnXuatFileLS_panelDieuKhienLS.setFocusPainted(false);
		btnXuatFileLS_panelDieuKhienLS.setFont(new Font("Arial", Font.BOLD, 18));
		btnXuatFileLS_panelDieuKhienLS.setBounds(244, 157, 117, 32);
		panelDieuKhienLS.add(btnXuatFileLS_panelDieuKhienLS);
		
		return panelDieuKhienLS;
	}
	
	public JScrollPane tblQuanLySach() {
		 tblQuanLySach = new JTable();
		tblQuanLySach.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.clickTableSach();
			}
		});
		tblQuanLySach.setFont(new Font("Arial", Font.PLAIN, 16));
		tblQuanLySach.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 S\u00E1ch", "T\u00EAn S\u00E1ch", "T\u00E1c Gi\u1EA3", "Nh\u00E0 Xu\u1EA5t B\u1EA3n", "S\u1ED1 L\u1EA7n T\u00E1i B\u1EA3n ", "N\u0103m Xu\u1EA5t B\u1EA3n", "S\u1ED1 L\u01B0\u1EE3ng ", "Tên Loại Sách"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		DefaultTableModel tableModel_sach = (DefaultTableModel) tblQuanLySach.getModel();
		tableModel_sach.setRowCount(0);
		tblQuanLySach.setRowHeight(26);
		tblQuanLySach.getColumnModel().getColumn(0).setPreferredWidth(20);
		tblQuanLySach.getColumnModel().getColumn(1).setPreferredWidth(110);
		tblQuanLySach.getColumnModel().getColumn(2).setPreferredWidth(110);
		tblQuanLySach.getColumnModel().getColumn(3).setPreferredWidth(60);
		tblQuanLySach.getColumnModel().getColumn(4).setPreferredWidth(20);
		tblQuanLySach.getColumnModel().getColumn(5).setPreferredWidth(5);
		tblQuanLySach.getColumnModel().getColumn(6).setPreferredWidth(5);
		tblQuanLySach.getColumnModel().getColumn(7).setPreferredWidth(15);
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
		
		JScrollPane scrollPane_QLS = new JScrollPane();
		scrollPane_QLS.setBounds(69, 117, 1148, 266);
		
		scrollPane_QLS.setViewportView(tblQuanLySach);
		return scrollPane_QLS;
	}
	
	public JScrollPane tblQuanLyLoaiSach() {
		tblQuanLyLoaiSach = new JTable();
		tblQuanLyLoaiSach.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.clickTableLoaiSach();
			}
		});
		tblQuanLyLoaiSach.setFont(new Font("Arial", Font.PLAIN, 16));
		
		tblQuanLyLoaiSach.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mã Loại Sách", "Tên Loại Sách", "Mô Tả"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			
		});
		
		DefaultTableModel tableModel_loaisach = (DefaultTableModel) tblQuanLyLoaiSach.getModel();
		tableModel_loaisach.setRowCount(0);
		List<loaiSach> layloaisach = loaiSachDAO.getloaiSachDAO().selectAll();
		for (loaiSach loaisach : layloaisach) {
			tableModel_loaisach.addRow(new Object[]{
	                   loaisach.getMaLoaiSach(),
	                   loaisach.getTenLoaiSach(),
	                   loaisach.getMoTa()
            });
		}
		tblQuanLyLoaiSach.setRowHeight(26);
		tblQuanLyLoaiSach.getColumnModel().getColumn(0).setPreferredWidth(10);
		tblQuanLyLoaiSach.getColumnModel().getColumn(1).setPreferredWidth(90);
		tblQuanLyLoaiSach.getColumnModel().getColumn(2).setPreferredWidth(350);
		
		JScrollPane scrollPane_QLLS = new JScrollPane();
		scrollPane_QLLS.setBounds(69, 117, 740, 459);
		scrollPane_QLLS.setViewportView(tblQuanLyLoaiSach);
		return scrollPane_QLLS;
	}
		
	public JPanel panel_QuanLyUser() {
		panel_QLUS = new JPanel();
		panel_QLUS.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_QLUS.setBackground(new Color(255, 255, 255));
		panel_QLUS.setBounds(0, 148, 1286, 616);
		panel_QLUS.setLayout(null);
		
		// bat dau code

		panelNutUser = new JPanel();
		panelNutUser.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelNutUser.setBounds(69, 36, 740, 61);
		panel_QLUS.add(panelNutUser);
		panelNutUser.setLayout(null);
		
		lblUser_panelNutUser = new JLabel("User");
		lblUser_panelNutUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.changeToUser();
			}
		});
		lblUser_panelNutUser.setForeground(new Color(255, 255, 255));
		lblUser_panelNutUser.setBackground(new Color(27, 161, 226));
		lblUser_panelNutUser.setOpaque(true);
		lblUser_panelNutUser.setBounds(0, 0, 136, 61);
		panelNutUser.add(lblUser_panelNutUser);
		lblUser_panelNutUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblUser_panelNutUser.setFont(new Font("Arial", Font.BOLD, 24));
		
		lblDatSach_panelNutUser = new JLabel("Đặt sách");
		lblDatSach_panelNutUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.changeToDatSach();
			}
		});
		lblDatSach_panelNutUser.setBackground(new Color(27, 161, 226));
		lblDatSach_panelNutUser.setBounds(151, 0, 136, 61);
		panelNutUser.add(lblDatSach_panelNutUser);
		lblDatSach_panelNutUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblDatSach_panelNutUser.setFont(new Font("Arial", Font.BOLD, 24));
		
		lblYeuCau_panelNutUser = new JLabel("Yêu cầu");
		lblYeuCau_panelNutUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.changeToYeuCau();
			}
		});
		lblYeuCau_panelNutUser.setBackground(new Color(27, 161, 226));
		lblYeuCau_panelNutUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblYeuCau_panelNutUser.setFont(new Font("Arial", Font.BOLD, 24));
		lblYeuCau_panelNutUser.setBounds(302, 0, 136, 61);
		panelNutUser.add(lblYeuCau_panelNutUser);
		
		lblDangMuon_panelNutUser = new JLabel("Đang mượn");
		lblDangMuon_panelNutUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.changeToDangMuon();
			}
		});
		lblDangMuon_panelNutUser.setBackground(new Color(27, 161, 226));
		lblDangMuon_panelNutUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblDangMuon_panelNutUser.setFont(new Font("Arial", Font.BOLD, 24));
		lblDangMuon_panelNutUser.setBounds(453, 0, 136, 61);
		panelNutUser.add(lblDangMuon_panelNutUser);
		
		lblQuaHan_panelNutUser = new JLabel("Quá hạn");
		lblQuaHan_panelNutUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.changeToQuaHan();
			}
		});
		lblQuaHan_panelNutUser.setBackground(new Color(27, 161, 226));
		lblQuaHan_panelNutUser.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuaHan_panelNutUser.setFont(new Font("Arial", Font.BOLD, 24));
		lblQuaHan_panelNutUser.setBounds(604, 0, 136, 61);
		panelNutUser.add(lblQuaHan_panelNutUser);
		
		
		
		panel_QLUS.add(panel_dieukhienUser());
		panel_QLUS.add(tblQuanLyUser());
		
		// ket thuc code
		
		return panel_QLUS;
	}	
	
	public JScrollPane tblQuanLyUser() {
		tblQuanLyUser = new JTable();
		tblQuanLyUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.chooseQLUS();
			}
		});
		tblQuanLyUser.setFont(new Font("Arial", Font.PLAIN, 16));
		tblQuanLyUser.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Email", "M\u1EADt kh\u1EA9u", "H\u1ECD v\u00E0 t\u00EAn", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblQuanLyUser.setRowHeight(25);
		
		DefaultTableModel tableModel_User = (DefaultTableModel)tblQuanLyUser.getModel();
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
		
		JScrollPane scrollPaneUser = new JScrollPane();
		scrollPaneUser.setBounds(69, 117, 740, 459);
		panel_QLUS.add(scrollPaneUser);
		scrollPaneUser.setViewportView(tblQuanLyUser);
		
		return scrollPaneUser;
	}
	
	public JScrollPane tblQuanLyDatSach() {
		tblQuanLyDatSach = new JTable();
		tblQuanLyDatSach.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.chooseQLDatsach();
			}
		});
		tblQuanLyDatSach.setFont(new Font("Arial", Font.PLAIN, 16));
		tblQuanLyDatSach.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Email", "M\u00E3 S\u00E1ch", "Ng\u00E0y \u0110\u1EB7t", "Tr\u1EA1ng Th\u00E1i"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblQuanLyDatSach.setRowHeight(25);
		
		DefaultTableModel tableModel_DatSach = (DefaultTableModel)tblQuanLyDatSach.getModel();
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
		
		JScrollPane scrollPaneUser = new JScrollPane();
		scrollPaneUser.setBounds(69, 117, 740, 459);
		panel_QLUS.add(scrollPaneUser);
		scrollPaneUser.setViewportView(tblQuanLyDatSach);
		
		return scrollPaneUser;
	}

	public JScrollPane tblQuanLyYeuCau() {
		tblQuanLyYeuCau = new JTable();
		tblQuanLyYeuCau.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.chooseQLYeuCau();
			}
		});
		tblQuanLyYeuCau.setFont(new Font("Arial", Font.PLAIN, 16));
		tblQuanLyYeuCau.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Email", "Lo\u1EA1i S\u00E1ch", "T\u00EAn S\u00E1ch", "M\u00F4 T\u1EA3"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblQuanLyYeuCau.setRowHeight(25);
		
		DefaultTableModel tableModel_YeuCau = (DefaultTableModel)tblQuanLyYeuCau.getModel();
		tableModel_YeuCau.setRowCount(0);
		List<yeuCau> lyc = yeuCauDAO.getyeuCauDAO().selectAll();
		for (yeuCau yeuCau : lyc) {
			tableModel_YeuCau.addRow(new Object[] {
					yeuCau.getEmail().getUsername(),
					yeuCau.getLoaiSach(),
					yeuCau.getSach(),
					yeuCau.getMoTa()				
			});
		}
		
		JScrollPane scrollPaneUser = new JScrollPane();
		scrollPaneUser.setBounds(69, 117, 740, 459);
		panel_QLUS.add(scrollPaneUser);
		scrollPaneUser.setViewportView(tblQuanLyYeuCau);
		
		return scrollPaneUser;
	}
	
	public JScrollPane tblQuanLyDangMuon() {
		tblQuanLyDangMuon = new JTable();
		tblQuanLyDangMuon.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.chooseQlDangMuon();
			}
		});
		tblQuanLyDangMuon.setFont(new Font("Arial", Font.PLAIN, 16));
		tblQuanLyDangMuon.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Email", "M\u00E3 S\u00E1ch", "Ng\u00E0y M\u01B0\u1EE3n", "Ng\u00E0y Tr\u1EA3", "Tr\u1EA1ng Th\u00E1i"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblQuanLyDangMuon.setRowHeight(25);
		
		DefaultTableModel tableModel_DangMuon = (DefaultTableModel)tblQuanLyDangMuon.getModel();
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
		
		JScrollPane scrollPaneUser = new JScrollPane();
		scrollPaneUser.setBounds(69, 117, 740, 459);
		panel_QLUS.add(scrollPaneUser);
		scrollPaneUser.setViewportView(tblQuanLyDangMuon);
		
		return scrollPaneUser;
	}
	
	public JScrollPane tblQuanLyQuaHan() {
		tblQuanLyQuaHan = new JTable();
		tblQuanLyQuaHan.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.chooseQLQuaHan();
			}
		});
		tblQuanLyQuaHan.setFont(new Font("Arial", Font.PLAIN, 16));
		tblQuanLyQuaHan.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Email", "M\u00E3 S\u00E1ch", "Ng\u00E0y M\u01B0\u1EE3n", "Ng\u00E0y Tr\u1EA3", "Tr\u1EA1ng Th\u00E1i"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblQuanLyQuaHan.setRowHeight(25);
		
		DefaultTableModel tableModel_QuaHan = (DefaultTableModel)tblQuanLyQuaHan.getModel();
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
		
		JScrollPane scrollPaneUser = new JScrollPane();
		scrollPaneUser.setBounds(69, 117, 740, 459);
		panel_QLUS.add(scrollPaneUser);
		scrollPaneUser.setViewportView(tblQuanLyQuaHan);
		
		return scrollPaneUser;
	}
	
	public JPanel panel_dieukhienUser() {
		JPanel panelDieuKhienUser = new JPanel();
		panelDieuKhienUser.setBounds(845, 36, 374, 540);

		panelDieuKhienUser.setLayout(null);
		
		
		JLabel lblTimKiem_panelDieuKhienUser = new JLabel("Tìm kiếm");
		lblTimKiem_panelDieuKhienUser.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTimKiem_panelDieuKhienUser.setBounds(10, 10, 103, 43);
		panelDieuKhienUser.add(lblTimKiem_panelDieuKhienUser);
		
		
		txtTimKiem_panelDieuKhienUser = new JTextField();
		txtTimKiem_panelDieuKhienUser.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				controller.timUserTheoCBX();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				controller.timUserTheoCBX();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				
			}
		});
		txtTimKiem_panelDieuKhienUser.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTimKiem_panelDieuKhienUser.setBounds(40, 63, 294, 36);
		panelDieuKhienUser.add(txtTimKiem_panelDieuKhienUser);
		txtTimKiem_panelDieuKhienUser.setColumns(10);
		
		JLabel lblDK_panelDieuKhienUser = new JLabel("Điều khiển");
		lblDK_panelDieuKhienUser.setFont(new Font("Calibri", Font.BOLD, 24));
		lblDK_panelDieuKhienUser.setBounds(10, 182, 134, 43);
		panelDieuKhienUser.add(lblDK_panelDieuKhienUser);
		
		cbxLoc_panelDieuKhienUser = new JComboBox();
		cbxLoc_panelDieuKhienUser.setFont(new Font("Arial", Font.PLAIN, 16));
		cbxLoc_panelDieuKhienUser.setModel(new DefaultComboBoxModel(new String[] {"Email", "Họ và tên", "Số điện thoại"}));
		cbxLoc_panelDieuKhienUser.setOpaque(true);
		cbxLoc_panelDieuKhienUser.setBounds(40, 124, 294, 36);
		panelDieuKhienUser.add(cbxLoc_panelDieuKhienUser);
		
		JPanel panelCTUS = new JPanel();
		panelCTUS.setBounds(10, 223, 354, 307);
		panelDieuKhienUser.add(panelCTUS);
		panelCTUS.setLayout(null);
		
		
		JButton btnUserNew_panelCTUS = new JButton("Làm mới");
		btnUserNew_panelCTUS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.btnNewuser();
			}
		});
		btnUserNew_panelCTUS.setFont(new Font("Arial", Font.BOLD, 16));
		btnUserNew_panelCTUS.setBounds(0, 280, 105, 27);
		panelCTUS.add(btnUserNew_panelCTUS);
		
		imgUser_panelCTUS = new JLabel("Nhấn để tải hình ảnh");
		imgUser_panelCTUS.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.changeAvatar_panelCTUS();
			}
		});
		imgUser_panelCTUS.setHorizontalAlignment(SwingConstants.CENTER);
		imgUser_panelCTUS.setFont(new Font("Arial", Font.BOLD, 14));
		imgUser_panelCTUS.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		imgUser_panelCTUS.setBounds(20, 10, 180, 92);
		panelCTUS.add(imgUser_panelCTUS);
		
		JButton btnSuaUser_panelCTUS = new JButton("Lưu User");
		btnSuaUser_panelCTUS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.addOrUpdateUser();
			}
		});
		btnSuaUser_panelCTUS.setFont(new Font("Arial", Font.BOLD, 16));
		btnSuaUser_panelCTUS.setBounds(126, 280, 105, 27);
		panelCTUS.add(btnSuaUser_panelCTUS);
		
		JButton btnDeleteUser_panelCTUS = new JButton("Xóa User");
		btnDeleteUser_panelCTUS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteUser();
			}
		});
		btnDeleteUser_panelCTUS.setFont(new Font("Arial", Font.BOLD, 16));
		btnDeleteUser_panelCTUS.setBounds(249, 280, 105, 27);
		panelCTUS.add(btnDeleteUser_panelCTUS);
		
		JLabel lblEmail_panelCTUS = new JLabel("Email");
		lblEmail_panelCTUS.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblEmail_panelCTUS.setBounds(0, 113, 60, 20);
		panelCTUS.add(lblEmail_panelCTUS);
		
		JLabel lblPassword_panelCTUS = new JLabel("Password");
		lblPassword_panelCTUS.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword_panelCTUS.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblPassword_panelCTUS.setBounds(0, 168, 60, 20);
		panelCTUS.add(lblPassword_panelCTUS);
		
		JLabel lblHoten_panelCTUS = new JLabel("Họ tên");
		lblHoten_panelCTUS.setHorizontalAlignment(SwingConstants.LEFT);
		lblHoten_panelCTUS.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblHoten_panelCTUS.setBounds(0, 225, 60, 20);
		panelCTUS.add(lblHoten_panelCTUS);
		
		txtEmail_panelCTUS = new JTextField();
		txtEmail_panelCTUS.setFont(new Font("Arial", Font.PLAIN, 14));
		txtEmail_panelCTUS.setBounds(20, 133, 314, 30);
		panelCTUS.add(txtEmail_panelCTUS);
		txtEmail_panelCTUS.setColumns(10);
		
		txtHoten_panelCTUS = new JTextField();
		txtHoten_panelCTUS.setFont(new Font("Arial", Font.PLAIN, 14));
		txtHoten_panelCTUS.setBounds(20, 240, 156, 30);
		panelCTUS.add(txtHoten_panelCTUS);
		txtHoten_panelCTUS.setColumns(10);
		
		JLabel lblSDT_panelCTUS = new JLabel("Số diện thoại");
		lblSDT_panelCTUS.setHorizontalAlignment(SwingConstants.LEFT);
		lblSDT_panelCTUS.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblSDT_panelCTUS.setBounds(209, 225, 75, 20);
		panelCTUS.add(lblSDT_panelCTUS);
		
		txtSDT_panelCTUS = new JTextField();
		txtSDT_panelCTUS.setFont(new Font("Arial", Font.PLAIN, 14));
		txtSDT_panelCTUS.setBounds(220, 240, 114, 30);
		panelCTUS.add(txtSDT_panelCTUS);
		txtSDT_panelCTUS.setColumns(10);
		
		JButton btnSuaUser_panelCTUS_1 = new JButton("Xem thêm");
		btnSuaUser_panelCTUS_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.changeToChiTietUser();
			}
		});
		btnSuaUser_panelCTUS_1.setFont(new Font("Arial", Font.BOLD, 16));
		btnSuaUser_panelCTUS_1.setBounds(218, 46, 116, 27);
		panelCTUS.add(btnSuaUser_panelCTUS_1);
		
		txtPassword_panelCTUS = new JPasswordField();
		txtPassword_panelCTUS.setFont(new Font("Arial", Font.PLAIN, 14));
		txtPassword_panelCTUS.setBounds(20, 190, 314, 30);
		panelCTUS.add(txtPassword_panelCTUS);
		
		JButton btnXuatFileLS_panelDieuKhienLS = new JButton("Xuất File");
		btnXuatFileLS_panelDieuKhienLS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.xuatFileUser();
			}
		});
		btnXuatFileLS_panelDieuKhienLS.setFocusPainted(false);
		btnXuatFileLS_panelDieuKhienLS.setFont(new Font("Arial", Font.BOLD, 18));
		btnXuatFileLS_panelDieuKhienLS.setBounds(247, 182, 117, 32);
		panelDieuKhienUser.add(btnXuatFileLS_panelDieuKhienLS);
		
		return panelDieuKhienUser;
	}
	
	public JPanel panel_dieukhienDatSach() {
		JPanel panelDieuKhienDatSach = new JPanel();
		panelDieuKhienDatSach.setBounds(845, 36, 374, 540);

		panelDieuKhienDatSach.setLayout(null);
		
		
		JLabel lblTimKiem_panelDieuKhienUser = new JLabel("Tìm kiếm");
		lblTimKiem_panelDieuKhienUser.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTimKiem_panelDieuKhienUser.setBounds(10, 10, 103, 43);
		panelDieuKhienDatSach.add(lblTimKiem_panelDieuKhienUser);
		
		
		txtTimKiem_panelDieuKhienUser_dkDS = new JTextField();
		txtTimKiem_panelDieuKhienUser_dkDS.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				controller.timPMSTheoCBX_panelDangDat();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				controller.timPMSTheoCBX_panelDangDat();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		txtTimKiem_panelDieuKhienUser_dkDS.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTimKiem_panelDieuKhienUser_dkDS.setBounds(40, 63, 294, 36);
		panelDieuKhienDatSach.add(txtTimKiem_panelDieuKhienUser_dkDS);
		txtTimKiem_panelDieuKhienUser_dkDS.setColumns(10);
		
		JLabel lblDK_panelDieuKhienUser_dkDS = new JLabel("Điều khiển");
		lblDK_panelDieuKhienUser_dkDS.setFont(new Font("Calibri", Font.BOLD, 24));
		lblDK_panelDieuKhienUser_dkDS.setBounds(10, 182, 134, 43);
		panelDieuKhienDatSach.add(lblDK_panelDieuKhienUser_dkDS);
		
		cbxLoc_panelDieuKhienUser_dkDS = new JComboBox();
		cbxLoc_panelDieuKhienUser_dkDS.setFont(new Font("Arial", Font.PLAIN, 16));
		cbxLoc_panelDieuKhienUser_dkDS.setModel(new DefaultComboBoxModel(new String[] {"Email", "Mã sách"}));
		cbxLoc_panelDieuKhienUser_dkDS.setOpaque(true);
		cbxLoc_panelDieuKhienUser_dkDS.setBounds(40, 124, 294, 36);
		panelDieuKhienDatSach.add(cbxLoc_panelDieuKhienUser_dkDS);
		
		JPanel panelCTUS = new JPanel();
		panelCTUS.setBounds(10, 223, 354, 307);
		panelDieuKhienDatSach.add(panelCTUS);
		panelCTUS.setLayout(null);
		
		JButton btnSuaUser_panelCTUS_dkDS = new JButton("Đã lấy sách");
		btnSuaUser_panelCTUS_dkDS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.chuyenTrangThaiPhieuThanhDangMuon();
			}
		});
		btnSuaUser_panelCTUS_dkDS.setFont(new Font("Arial", Font.BOLD, 16));
		btnSuaUser_panelCTUS_dkDS.setBounds(50, 280, 130, 27);
		panelCTUS.add(btnSuaUser_panelCTUS_dkDS);
		
		JButton btnDeleteUser_panelCTUS_dkDS = new JButton("Xóa");
		btnDeleteUser_panelCTUS_dkDS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.deleteDatSach();
			}
		});
		btnDeleteUser_panelCTUS_dkDS.setFont(new Font("Arial", Font.BOLD, 16));
		btnDeleteUser_panelCTUS_dkDS.setBounds(199, 280, 105, 27);
		panelCTUS.add(btnDeleteUser_panelCTUS_dkDS);
		
		JLabel lblEmail_panelCTUS_dkDS = new JLabel("Email");
		lblEmail_panelCTUS_dkDS.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblEmail_panelCTUS_dkDS.setBounds(0, 0, 60, 20);
		panelCTUS.add(lblEmail_panelCTUS_dkDS);
		
		JLabel lblPassword_panelCTUS_dkDS = new JLabel("Mã sách");
		lblPassword_panelCTUS_dkDS.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword_panelCTUS_dkDS.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblPassword_panelCTUS_dkDS.setBounds(0, 64, 60, 20);
		panelCTUS.add(lblPassword_panelCTUS_dkDS);
		
		JLabel lblHoten_panelCTUS_dkDS = new JLabel("Ngày Đặt");
		lblHoten_panelCTUS_dkDS.setHorizontalAlignment(SwingConstants.LEFT);
		lblHoten_panelCTUS_dkDS.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblHoten_panelCTUS_dkDS.setBounds(0, 134, 60, 20);
		panelCTUS.add(lblHoten_panelCTUS_dkDS);
		
		txtEmail_panelCTUS_dkDS = new JTextField();
		txtEmail_panelCTUS_dkDS.setFont(new Font("Arial", Font.PLAIN, 16));
		txtEmail_panelCTUS_dkDS.setEditable(false);
		txtEmail_panelCTUS_dkDS.setBounds(20, 24, 314, 30);
		panelCTUS.add(txtEmail_panelCTUS_dkDS);
		txtEmail_panelCTUS_dkDS.setColumns(10);
		
		txtNgayMuon_panelCTUS_dkDS = new JTextField();
		txtNgayMuon_panelCTUS_dkDS.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNgayMuon_panelCTUS_dkDS.setEditable(false);
		txtNgayMuon_panelCTUS_dkDS.setBounds(20, 164, 314, 30);
		panelCTUS.add(txtNgayMuon_panelCTUS_dkDS);
		txtNgayMuon_panelCTUS_dkDS.setColumns(10);
		
		JLabel lblSDT_panelCTUS_dkDS = new JLabel("Trạng thái");
		lblSDT_panelCTUS_dkDS.setHorizontalAlignment(SwingConstants.LEFT);
		lblSDT_panelCTUS_dkDS.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblSDT_panelCTUS_dkDS.setBounds(0, 204, 75, 20);
		panelCTUS.add(lblSDT_panelCTUS_dkDS);
		
		txtTrangThai_panelCTUS_dkDS = new JTextField();
		txtTrangThai_panelCTUS_dkDS.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTrangThai_panelCTUS_dkDS.setEditable(false);
		txtTrangThai_panelCTUS_dkDS.setBounds(20, 234, 314, 30);
		panelCTUS.add(txtTrangThai_panelCTUS_dkDS);
		txtTrangThai_panelCTUS_dkDS.setColumns(10);
		
		txtMaSach_panelCTUS_dkDS = new JTextField();
		txtMaSach_panelCTUS_dkDS.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMaSach_panelCTUS_dkDS.setEditable(false);
		txtMaSach_panelCTUS_dkDS.setBounds(20, 94, 314, 30);
		panelCTUS.add(txtMaSach_panelCTUS_dkDS);
		
		JButton btnXuatFileLS_panelDieuKhienLS = new JButton("Xuất File");
		btnXuatFileLS_panelDieuKhienLS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.xuatFileDatSach();
			}
		});
		btnXuatFileLS_panelDieuKhienLS.setFocusPainted(false);
		btnXuatFileLS_panelDieuKhienLS.setFont(new Font("Arial", Font.BOLD, 18));
		btnXuatFileLS_panelDieuKhienLS.setBounds(247, 182, 117, 32);
		panelDieuKhienDatSach.add(btnXuatFileLS_panelDieuKhienLS);
		
		return panelDieuKhienDatSach;
		
	}

	public JPanel panel_dieukhienYeuCau() {
		JPanel panelDieuKhienYeuCau = new JPanel();
		panelDieuKhienYeuCau.setBounds(845, 36, 374, 540);

		panelDieuKhienYeuCau.setLayout(null);
		
		
		JLabel lblTimKiem_panelDieuKhienUser = new JLabel("Tìm kiếm");
		lblTimKiem_panelDieuKhienUser.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTimKiem_panelDieuKhienUser.setBounds(10, 10, 103, 43);
		panelDieuKhienYeuCau.add(lblTimKiem_panelDieuKhienUser);
		
		
		txtTimKiem_panelDieuKhienUser_dkYC = new JTextField();
		txtTimKiem_panelDieuKhienUser_dkYC.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				controller.timYCTheoCBX_panelYC();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				controller.timYCTheoCBX_panelYC();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		txtTimKiem_panelDieuKhienUser_dkYC.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTimKiem_panelDieuKhienUser_dkYC.setBounds(40, 63, 294, 36);
		panelDieuKhienYeuCau.add(txtTimKiem_panelDieuKhienUser_dkYC);
		txtTimKiem_panelDieuKhienUser_dkYC.setColumns(10);
		
		JLabel lblDK_panelDieuKhienUser = new JLabel("Điều khiển");
		lblDK_panelDieuKhienUser.setFont(new Font("Calibri", Font.BOLD, 24));
		lblDK_panelDieuKhienUser.setBounds(10, 182, 134, 43);
		panelDieuKhienYeuCau.add(lblDK_panelDieuKhienUser);
		
		cbxLoc_panelDieuKhienUser_dkYC = new JComboBox();
		cbxLoc_panelDieuKhienUser_dkYC.setFont(new Font("Arial", Font.PLAIN, 16));
		cbxLoc_panelDieuKhienUser_dkYC.setModel(new DefaultComboBoxModel(new String[] {"Email"}));
		cbxLoc_panelDieuKhienUser_dkYC.setOpaque(true);
		cbxLoc_panelDieuKhienUser_dkYC.setBounds(40, 124, 294, 36);
		panelDieuKhienYeuCau.add(cbxLoc_panelDieuKhienUser_dkYC);
		
		JPanel panelCTUS = new JPanel();
		panelCTUS.setBounds(10, 223, 354, 307);
		panelDieuKhienYeuCau.add(panelCTUS);
		panelCTUS.setLayout(null);
		
		
		JButton btnUserNew_panelCTUS_dkYC = new JButton("Làm mới");
		btnUserNew_panelCTUS_dkYC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.btnNewYeuCau();
			}
		});
		btnUserNew_panelCTUS_dkYC.setFont(new Font("Arial", Font.BOLD, 10));
		btnUserNew_panelCTUS_dkYC.setBounds(0, 280, 105, 27);
		panelCTUS.add(btnUserNew_panelCTUS_dkYC);
		
		JButton btnSuaUser_panelCTUS_dkYC = new JButton("Lưu Yêu Cầu");
		btnSuaUser_panelCTUS_dkYC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.addOrUpdateYeuCau();
			}
		});
		btnSuaUser_panelCTUS_dkYC.setFont(new Font("Arial", Font.BOLD, 10));
		btnSuaUser_panelCTUS_dkYC.setBounds(126, 280, 105, 27);
		panelCTUS.add(btnSuaUser_panelCTUS_dkYC);
		
		JButton btnDeleteUser_panelCTUS_dkYC = new JButton("Xóa Yêu Cầu");
		btnDeleteUser_panelCTUS_dkYC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDeleteUser_panelCTUS_dkYC.setFont(new Font("Arial", Font.BOLD, 10));
		btnDeleteUser_panelCTUS_dkYC.setBounds(249, 280, 105, 27);
		panelCTUS.add(btnDeleteUser_panelCTUS_dkYC);
		
		JLabel lblEmail_panelCTUS_dkYC = new JLabel("Email");
		lblEmail_panelCTUS_dkYC.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblEmail_panelCTUS_dkYC.setBounds(0, 0, 60, 20);
		panelCTUS.add(lblEmail_panelCTUS_dkYC);
		
		JLabel lblPassword_panelCTUS_dkYC = new JLabel("Loại sách");
		lblPassword_panelCTUS_dkYC.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword_panelCTUS_dkYC.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblPassword_panelCTUS_dkYC.setBounds(0, 65, 60, 20);
		panelCTUS.add(lblPassword_panelCTUS_dkYC);
		
		JLabel lblHoten_panelCTUS_dkYC = new JLabel("Tên Sách");
		lblHoten_panelCTUS_dkYC.setHorizontalAlignment(SwingConstants.LEFT);
		lblHoten_panelCTUS_dkYC.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblHoten_panelCTUS_dkYC.setBounds(0, 135, 60, 20);
		panelCTUS.add(lblHoten_panelCTUS_dkYC);
		
		txtEmail_panelCTUS_dkYC = new JTextField();
		txtEmail_panelCTUS_dkYC.setBounds(20, 25, 314, 30);
		panelCTUS.add(txtEmail_panelCTUS_dkYC);
		txtEmail_panelCTUS_dkYC.setColumns(10);
		
		txtTenSach_panelCTUS_dkYC = new JTextField();
		txtTenSach_panelCTUS_dkYC.setBounds(20, 165, 314, 30);
		panelCTUS.add(txtTenSach_panelCTUS_dkYC);
		txtTenSach_panelCTUS_dkYC.setColumns(10);
		
		JLabel lblSDT_panelCTUS_dkYC = new JLabel("Mô Tả");
		lblSDT_panelCTUS_dkYC.setHorizontalAlignment(SwingConstants.LEFT);
		lblSDT_panelCTUS_dkYC.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblSDT_panelCTUS_dkYC.setBounds(0, 205, 75, 20);
		panelCTUS.add(lblSDT_panelCTUS_dkYC);
		
		txtMoTa_panelCTUS_dkYC = new JTextField();
		txtMoTa_panelCTUS_dkYC.setBounds(20, 235, 314, 30);
		panelCTUS.add(txtMoTa_panelCTUS_dkYC);
		txtMoTa_panelCTUS_dkYC.setColumns(10);
		
		txtLoaiSach_panelCTUS_dkYC = new JTextField();
		txtLoaiSach_panelCTUS_dkYC.setBounds(20, 95, 314, 30);
		panelCTUS.add(txtLoaiSach_panelCTUS_dkYC);
		
		JButton btnXuatFileLS_panelDieuKhienLS = new JButton("Xuất File");
		btnXuatFileLS_panelDieuKhienLS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.xuatFileYeuCau();
			}
		});
		btnXuatFileLS_panelDieuKhienLS.setFocusPainted(false);
		btnXuatFileLS_panelDieuKhienLS.setFont(new Font("Arial", Font.BOLD, 18));
		btnXuatFileLS_panelDieuKhienLS.setBounds(247, 182, 117, 32);
		panelDieuKhienYeuCau.add(btnXuatFileLS_panelDieuKhienLS);
		
		return panelDieuKhienYeuCau;
	}
		
	public JPanel panel_dieukhienDangMuon() {
		JPanel panelDieuKhienDangMuon = new JPanel();
		panelDieuKhienDangMuon.setBounds(845, 36, 374, 540);

		panelDieuKhienDangMuon.setLayout(null);
		
		
		JLabel lblTimKiem_panelDieuKhienUser = new JLabel("Tìm kiếm");
		lblTimKiem_panelDieuKhienUser.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTimKiem_panelDieuKhienUser.setBounds(10, 10, 103, 43);
		panelDieuKhienDangMuon.add(lblTimKiem_panelDieuKhienUser);
		
		
		txtTimKiem_panelDieuKhienUser_dkDM = new JTextField();
		txtTimKiem_panelDieuKhienUser_dkDM.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				controller.timPMSTheoCBX_panelDangMuon();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				controller.timPMSTheoCBX_panelDangMuon();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		txtTimKiem_panelDieuKhienUser_dkDM.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTimKiem_panelDieuKhienUser_dkDM.setBounds(40, 63, 294, 36);
		panelDieuKhienDangMuon.add(txtTimKiem_panelDieuKhienUser_dkDM);
		txtTimKiem_panelDieuKhienUser_dkDM.setColumns(10);
		
		JLabel lblDK_panelDieuKhienUser = new JLabel("Điều khiển");
		lblDK_panelDieuKhienUser.setFont(new Font("Calibri", Font.BOLD, 24));
		lblDK_panelDieuKhienUser.setBounds(10, 182, 134, 43);
		panelDieuKhienDangMuon.add(lblDK_panelDieuKhienUser);
		
		cbxLoc_panelDieuKhienUser_dkDM = new JComboBox();
		cbxLoc_panelDieuKhienUser_dkDM.setFont(new Font("Arial", Font.PLAIN, 16));
		cbxLoc_panelDieuKhienUser_dkDM.setModel(new DefaultComboBoxModel(new String[] {"Email", "Mã sách"}));
		cbxLoc_panelDieuKhienUser_dkDM.setOpaque(true);
		cbxLoc_panelDieuKhienUser_dkDM.setBounds(40, 124, 294, 36);
		panelDieuKhienDangMuon.add(cbxLoc_panelDieuKhienUser_dkDM);
		
		JPanel panelCTUS = new JPanel();
		panelCTUS.setBounds(10, 223, 354, 307);
		panelDieuKhienDangMuon.add(panelCTUS);
		panelCTUS.setLayout(null);
		
		JButton btnSuaUser_panelCTUS_dkDM = new JButton("Đã Trả");
		btnSuaUser_panelCTUS_dkDM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.chuyenTrangThaiPhieuThanhDaTra_DangMuon();
			}
		});
		btnSuaUser_panelCTUS_dkDM.setFont(new Font("Arial", Font.BOLD, 14));
		btnSuaUser_panelCTUS_dkDM.setBounds(126, 280, 105, 27);
		panelCTUS.add(btnSuaUser_panelCTUS_dkDM);
		
		JLabel lblEmail_panelCTUS_dkDM = new JLabel("Email");
		lblEmail_panelCTUS_dkDM.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblEmail_panelCTUS_dkDM.setBounds(0, 0, 60, 20);
		panelCTUS.add(lblEmail_panelCTUS_dkDM);
		
		JLabel lblPassword_panelCTUS_dkDM = new JLabel("Mã Sách");
		lblPassword_panelCTUS_dkDM.setHorizontalAlignment(SwingConstants.LEFT);
		lblPassword_panelCTUS_dkDM.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblPassword_panelCTUS_dkDM.setBounds(0, 65, 60, 20);
		panelCTUS.add(lblPassword_panelCTUS_dkDM);
		
		JLabel lblNgayMuon_panelCTUS_dkDM = new JLabel("Ngày Mượn");
		lblNgayMuon_panelCTUS_dkDM.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayMuon_panelCTUS_dkDM.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNgayMuon_panelCTUS_dkDM.setBounds(0, 135, 60, 20);
		panelCTUS.add(lblNgayMuon_panelCTUS_dkDM);
		
		txtEmail_panelCTUS_dkDM = new JTextField();
		txtEmail_panelCTUS_dkDM.setFont(new Font("Arial", Font.PLAIN, 16));
		txtEmail_panelCTUS_dkDM.setBounds(20, 25, 314, 30);
		panelCTUS.add(txtEmail_panelCTUS_dkDM);
		txtEmail_panelCTUS_dkDM.setColumns(10);
		
		txtNgayMuon_panelCTUS_dkDM = new JTextField();
		txtNgayMuon_panelCTUS_dkDM.setEditable(false);
		txtNgayMuon_panelCTUS_dkDM.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNgayMuon_panelCTUS_dkDM.setBounds(20, 165, 314, 30);
		panelCTUS.add(txtNgayMuon_panelCTUS_dkDM);
		txtNgayMuon_panelCTUS_dkDM.setColumns(10);
		
		JLabel lblNgayTra_panelCTUS_dkDM = new JLabel("Ngày Trả");
		lblNgayTra_panelCTUS_dkDM.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayTra_panelCTUS_dkDM.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNgayTra_panelCTUS_dkDM.setBounds(0, 205, 75, 20);
		panelCTUS.add(lblNgayTra_panelCTUS_dkDM);
		
		txtNgayTra_panelCTUS_dkDM = new JTextField();
		txtNgayTra_panelCTUS_dkDM.setEditable(false);
		txtNgayTra_panelCTUS_dkDM.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNgayTra_panelCTUS_dkDM.setBounds(20, 235, 314, 30);
		panelCTUS.add(txtNgayTra_panelCTUS_dkDM);
		txtNgayTra_panelCTUS_dkDM.setColumns(10);
		
		txtMaSach_panelCTUS_dkDM = new JTextField();
		txtMaSach_panelCTUS_dkDM.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMaSach_panelCTUS_dkDM.setBounds(20, 95, 314, 30);
		panelCTUS.add(txtMaSach_panelCTUS_dkDM);
		
		Date d = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String ngayMuon = sdf.format(d);
		txtNgayMuon_panelCTUS_dkDM.setText(ngayMuon);
		d.setMonth(d.getMonth() + 1);
		String ngayTra = sdf.format(d);
		txtNgayTra_panelCTUS_dkDM.setText(ngayTra);
		
		JButton btnNewDM_panelCTUS_dkDM = new JButton("Làm Mới");
		btnNewDM_panelCTUS_dkDM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.btnNewDangMuon();
			}
		});
		btnNewDM_panelCTUS_dkDM.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewDM_panelCTUS_dkDM.setBounds(0, 280, 105, 27);
		panelCTUS.add(btnNewDM_panelCTUS_dkDM);
		
		JButton btnSaveDM_panelCTUS_dkDM = new JButton("Thêm Mới");
		btnSaveDM_panelCTUS_dkDM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.saveDangMuon();
			}
		});
		btnSaveDM_panelCTUS_dkDM.setFont(new Font("Arial", Font.BOLD, 14));
		btnSaveDM_panelCTUS_dkDM.setBounds(249, 280, 105, 27);
		panelCTUS.add(btnSaveDM_panelCTUS_dkDM);
		
		JButton btnXuatFileLS_panelDieuKhienLS = new JButton("Xuất File");
		btnXuatFileLS_panelDieuKhienLS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.xuatFileDangMuon();
			}
		});
		btnXuatFileLS_panelDieuKhienLS.setFocusPainted(false);
		btnXuatFileLS_panelDieuKhienLS.setFont(new Font("Arial", Font.BOLD, 18));
		btnXuatFileLS_panelDieuKhienLS.setBounds(247, 182, 117, 32);
		panelDieuKhienDangMuon.add(btnXuatFileLS_panelDieuKhienLS);
		
		return panelDieuKhienDangMuon;
	}

	public JPanel panel_dieukhienQuaHan() {
		JPanel panelDieuKhienQuaHan = new JPanel();
		panelDieuKhienQuaHan.setBounds(845, 36, 374, 540);

		panelDieuKhienQuaHan.setLayout(null);
		
		
		JLabel lblTimKiem_panelDieuKhienUser = new JLabel("Tìm kiếm");
		lblTimKiem_panelDieuKhienUser.setFont(new Font("Calibri", Font.BOLD, 24));
		lblTimKiem_panelDieuKhienUser.setBounds(10, 10, 103, 43);
		panelDieuKhienQuaHan.add(lblTimKiem_panelDieuKhienUser);
		
		
		txtTimKiem_panelDieuKhienUser_dkQH = new JTextField();
		txtTimKiem_panelDieuKhienUser_dkQH.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				controller.timPMSTheoCBX_panelQuaHan();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				controller.timPMSTheoCBX_panelQuaHan();
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		txtTimKiem_panelDieuKhienUser_dkQH.setFont(new Font("Arial", Font.PLAIN, 16));
		txtTimKiem_panelDieuKhienUser_dkQH.setBounds(40, 63, 294, 36);
		panelDieuKhienQuaHan.add(txtTimKiem_panelDieuKhienUser_dkQH);
		txtTimKiem_panelDieuKhienUser_dkQH.setColumns(10);
		
		JLabel lblDK_panelDieuKhienUser = new JLabel("Điều khiển");
		lblDK_panelDieuKhienUser.setFont(new Font("Calibri", Font.BOLD, 24));
		lblDK_panelDieuKhienUser.setBounds(10, 182, 134, 43);
		panelDieuKhienQuaHan.add(lblDK_panelDieuKhienUser);
		
		cbxLoc_panelDieuKhienUser_dkQH = new JComboBox();
		cbxLoc_panelDieuKhienUser_dkQH.setFont(new Font("Arial", Font.PLAIN, 16));
		cbxLoc_panelDieuKhienUser_dkQH.setModel(new DefaultComboBoxModel(new String[] {"Email", "Mã sách"}));
		cbxLoc_panelDieuKhienUser_dkQH.setOpaque(true);
		cbxLoc_panelDieuKhienUser_dkQH.setBounds(40, 124, 294, 36);
		panelDieuKhienQuaHan.add(cbxLoc_panelDieuKhienUser_dkQH);
		
		JPanel panelCTUS = new JPanel();
		panelCTUS.setBounds(10, 223, 354, 307);
		panelDieuKhienQuaHan.add(panelCTUS);
		panelCTUS.setLayout(null);
		
		JButton btnSuaUser_panelCTUS_dkQH = new JButton("Đã Trả");
		btnSuaUser_panelCTUS_dkQH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.chuyenTrangThaiPhieuThanhDaTra_QuaHan();
			}
		});
		btnSuaUser_panelCTUS_dkQH.setFont(new Font("Arial", Font.BOLD, 16));
		btnSuaUser_panelCTUS_dkQH.setBounds(126, 280, 105, 27);
		panelCTUS.add(btnSuaUser_panelCTUS_dkQH);
		
		JLabel lblEmail_panelCTUS_dkQH = new JLabel("Email");
		lblEmail_panelCTUS_dkQH.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblEmail_panelCTUS_dkQH.setBounds(0, 0, 60, 20);
		panelCTUS.add(lblEmail_panelCTUS_dkQH);
		
		JLabel lblMaSach_panelCTUS_dkQH = new JLabel("Mã Sách");
		lblMaSach_panelCTUS_dkQH.setHorizontalAlignment(SwingConstants.LEFT);
		lblMaSach_panelCTUS_dkQH.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblMaSach_panelCTUS_dkQH.setBounds(0, 65, 60, 20);
		panelCTUS.add(lblMaSach_panelCTUS_dkQH);
		
		JLabel lblNgayMuon_panelCTUS_dkQH = new JLabel("Ngày Mượn");
		lblNgayMuon_panelCTUS_dkQH.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayMuon_panelCTUS_dkQH.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNgayMuon_panelCTUS_dkQH.setBounds(0, 135, 60, 20);
		panelCTUS.add(lblNgayMuon_panelCTUS_dkQH);
		
		txtEmail_panelCTUS_dkQH = new JTextField();
		txtEmail_panelCTUS_dkQH.setEditable(false);
		txtEmail_panelCTUS_dkQH.setFont(new Font("Arial", Font.PLAIN, 16));
		txtEmail_panelCTUS_dkQH.setBounds(20, 25, 314, 30);
		panelCTUS.add(txtEmail_panelCTUS_dkQH);
		txtEmail_panelCTUS_dkQH.setColumns(10);
		
		txtNgayMuon_panelCTUS_dkQH = new JTextField();
		txtNgayMuon_panelCTUS_dkQH.setEditable(false);
		txtNgayMuon_panelCTUS_dkQH.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNgayMuon_panelCTUS_dkQH.setBounds(20, 165, 314, 30);
		panelCTUS.add(txtNgayMuon_panelCTUS_dkQH);
		txtNgayMuon_panelCTUS_dkQH.setColumns(10);
		
		JLabel lblNgayTra_panelCTUS_dkQH = new JLabel("Ngày Trả");
		lblNgayTra_panelCTUS_dkQH.setHorizontalAlignment(SwingConstants.LEFT);
		lblNgayTra_panelCTUS_dkQH.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNgayTra_panelCTUS_dkQH.setBounds(0, 205, 75, 20);
		panelCTUS.add(lblNgayTra_panelCTUS_dkQH);
		
		txtNgayTra_panelCTUS_dkQH = new JTextField();
		txtNgayTra_panelCTUS_dkQH.setEditable(false);
		txtNgayTra_panelCTUS_dkQH.setFont(new Font("Arial", Font.PLAIN, 16));
		txtNgayTra_panelCTUS_dkQH.setBounds(20, 235, 314, 30);
		panelCTUS.add(txtNgayTra_panelCTUS_dkQH);
		txtNgayTra_panelCTUS_dkQH.setColumns(10);
		
		txtMaSach_panelCTUS_dkQH = new JTextField();
		txtMaSach_panelCTUS_dkQH.setEditable(false);
		txtMaSach_panelCTUS_dkQH.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMaSach_panelCTUS_dkQH.setBounds(20, 95, 314, 30);
		panelCTUS.add(txtMaSach_panelCTUS_dkQH);
		
		JButton btnXuatFileLS_panelDieuKhienLS = new JButton("Xuất File");
		btnXuatFileLS_panelDieuKhienLS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.xuatFileQuaHan();
			}
		});
		btnXuatFileLS_panelDieuKhienLS.setFocusPainted(false);
		btnXuatFileLS_panelDieuKhienLS.setFont(new Font("Arial", Font.BOLD, 18));
		btnXuatFileLS_panelDieuKhienLS.setBounds(247, 182, 117, 32);
		panelDieuKhienQuaHan.add(btnXuatFileLS_panelDieuKhienLS);
		
		return panelDieuKhienQuaHan;
	}
	
	public JPanel panel_ThongKe() {
		JPanel panel_ThongKe = new JPanel();
		panel_ThongKe.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_ThongKe.setBackground(new Color(255, 255, 255));
		panel_ThongKe.setBounds(0, 148, 1286, 616);
		panel_ThongKe.setLayout(null);
		
		// bat dau code
		
		JPanel panel_TongSoNguoiDung = new JPanel();
		panel_TongSoNguoiDung.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_TongSoNguoiDung.setBounds(157, 77, 291, 204);
		panel_ThongKe.add(panel_TongSoNguoiDung);
		panel_TongSoNguoiDung.setLayout(null);
		

		JLabel lblTitle_panelTongSoNguoiDung = new JLabel("Tổng số người dùng");
		lblTitle_panelTongSoNguoiDung.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle_panelTongSoNguoiDung.setFont(new Font("Calibri", Font.BOLD, 25));
		lblTitle_panelTongSoNguoiDung.setForeground(new Color(255, 255, 255));
		lblTitle_panelTongSoNguoiDung.setBounds(36, 26, 218, 44);
		panel_TongSoNguoiDung.add(lblTitle_panelTongSoNguoiDung);
		
		JLabel lblTong__panelTongSoNguoiDung = new JLabel(userDAO.getuserDAO().soNguoiDung() + " người dùng");
		lblTong__panelTongSoNguoiDung.setHorizontalAlignment(SwingConstants.CENTER);
		lblTong__panelTongSoNguoiDung.setForeground(Color.WHITE);
		lblTong__panelTongSoNguoiDung.setFont(new Font("Calibri", Font.BOLD, 23));
		lblTong__panelTongSoNguoiDung.setBounds(36, 70, 218, 44);
		panel_TongSoNguoiDung.add(lblTong__panelTongSoNguoiDung);
		
		JLabel imgBG_panelTongSoNguoiDung = new JLabel("");
		imgBG_panelTongSoNguoiDung.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.choseQLUser();
			}
		});
		imgBG_panelTongSoNguoiDung.setBounds(0, 0, panel_TongSoNguoiDung.getWidth(), panel_TongSoNguoiDung.getHeight());
		ImageIcon imgIBG_panelThongKe = new ImageIcon(
				Paths.get("icon\\tongSoNguoiDung.jpg").toAbsolutePath().toString());
//		ImageIcon imgIBG_panelThongKe = new ImageIcon("C:\\Users\\Admin\\OneDrive\\Desktop\\tongSoNguoiDung.jpg");
		Image imBG_panelThongKe = imgIBG_panelThongKe.getImage();
		Image imageBG_panelThongKe = imBG_panelThongKe.getScaledInstance(imgBG_panelTongSoNguoiDung.getWidth(), imgBG_panelTongSoNguoiDung.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIconBG_panelThongKe = new ImageIcon(imageBG_panelThongKe);
		imgBG_panelTongSoNguoiDung.setIcon(imageIconBG_panelThongKe);
		panel_TongSoNguoiDung.add(imgBG_panelTongSoNguoiDung);
		
		
		JPanel panel_TongSoSach = new JPanel();
		panel_TongSoSach.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_TongSoSach.setBounds(498, 77, 291, 204);
		panel_ThongKe.add(panel_TongSoSach);
		panel_TongSoSach.setLayout(null);
		
		JLabel lblTong_panelTongSoSach = new JLabel(sachDAO.getsachDAO().soLuongSach() + " cuốn sách");
//		JLabel lblTong_panelTongSoSach = new JLabel(11 + " cuốn sách");
		lblTong_panelTongSoSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblTong_panelTongSoSach.setForeground(Color.WHITE);
		lblTong_panelTongSoSach.setFont(new Font("Calibri", Font.BOLD, 23));
		lblTong_panelTongSoSach.setBounds(36, 70, 218, 44);
		panel_TongSoSach.add(lblTong_panelTongSoSach);
		
		JLabel lblTitle_panelTongSoSach = new JLabel("Tổng số sách");
		lblTitle_panelTongSoSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle_panelTongSoSach.setForeground(Color.WHITE);
		lblTitle_panelTongSoSach.setFont(new Font("Calibri", Font.BOLD, 25));
		lblTitle_panelTongSoSach.setBounds(36, 26, 218, 44);
		panel_TongSoSach.add(lblTitle_panelTongSoSach);
		
		JLabel imgBG_panelTongSoSach = new JLabel("");
		imgBG_panelTongSoSach.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.choseQLSach();
			}
		});
		imgBG_panelTongSoSach.setBounds(0, 0, 291, 204);
		ImageIcon imgIBG_panelTongSoSach = new ImageIcon(
				Paths.get("icon\\tongSoSach.jpg").toAbsolutePath().toString());
//		ImageIcon imgIBG_panelTongSoSach = new ImageIcon("C:\\Users\\Admin\\OneDrive\\Desktop\\tongSoSach.jpg");
		Image imBG_panelTongSoSach = imgIBG_panelTongSoSach.getImage();
		Image imageBG_panelTongSoSach = imBG_panelTongSoSach.getScaledInstance(imgBG_panelTongSoSach.getWidth(), imgBG_panelTongSoSach.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIconBG_panelTongSoSach = new ImageIcon(imageBG_panelTongSoSach);
		imgBG_panelTongSoSach.setIcon(imageIconBG_panelTongSoSach);	
		panel_TongSoSach.add(imgBG_panelTongSoSach);
		
		
		JPanel panel_DoPhoBienCuaSach = new JPanel();
		panel_DoPhoBienCuaSach.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_DoPhoBienCuaSach.setBounds(839, 77, 291, 204);
		panel_ThongKe.add(panel_DoPhoBienCuaSach);
		panel_DoPhoBienCuaSach.setLayout(null);
		
		JLabel lblTitle_panelDoPhoBienCuaSach = new JLabel("Độ phổ biến của sách");
		lblTitle_panelDoPhoBienCuaSach.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle_panelDoPhoBienCuaSach.setForeground(Color.WHITE);
		lblTitle_panelDoPhoBienCuaSach.setFont(new Font("Calibri", Font.BOLD, 25));
		lblTitle_panelDoPhoBienCuaSach.setBounds(10, 26, 271, 44);
		panel_DoPhoBienCuaSach.add(lblTitle_panelDoPhoBienCuaSach);
		
		JButton btnXemChiTiet_panelDoPhoBienCuaSach = new JButton("Xem chi tiết");
		btnXemChiTiet_panelDoPhoBienCuaSach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.createdChart();
			}
		});
		btnXemChiTiet_panelDoPhoBienCuaSach.setFocusPainted(false);
		btnXemChiTiet_panelDoPhoBienCuaSach.setFont(new Font("Arial", Font.BOLD, 18));
		btnXemChiTiet_panelDoPhoBienCuaSach.setBounds(74, 70, 143, 44);
		panel_DoPhoBienCuaSach.add(btnXemChiTiet_panelDoPhoBienCuaSach);
		
		JLabel imgBG_panelDoPhoBienCuaSach = new JLabel("");
		imgBG_panelDoPhoBienCuaSach.setBounds(0, 0, 291, 204);
		ImageIcon imgIBG_panelDoPhoBienCuaSach  = new ImageIcon(
				Paths.get("icon\\doPhoBienCuaSachjpg.jpg").toAbsolutePath().toString());
//		ImageIcon imgIBG_panelDoPhoBienCuaSach = new ImageIcon("C:\\Users\\Admin\\OneDrive\\Desktop\\doPhoBienCuaSachjpg.jpg");
		Image imBG_panelDoPhoBienCuaSach = imgIBG_panelDoPhoBienCuaSach.getImage();
		Image imageBG_panelDoPhoBienCuaSach = imBG_panelDoPhoBienCuaSach.getScaledInstance(imgBG_panelDoPhoBienCuaSach.getWidth(), imgBG_panelDoPhoBienCuaSach.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIconBG_panelDoPhoBienCuaSach = new ImageIcon(imageBG_panelDoPhoBienCuaSach);
		imgBG_panelDoPhoBienCuaSach.setIcon(imageIconBG_panelDoPhoBienCuaSach);	
		panel_DoPhoBienCuaSach.add(imgBG_panelDoPhoBienCuaSach);
		
		
		JPanel panel_SachQuaHan = new JPanel();
		panel_SachQuaHan.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_SachQuaHan.setBounds(839, 321, 291, 204);
		panel_ThongKe.add(panel_SachQuaHan);
		panel_SachQuaHan.setLayout(null);
		
		JLabel lblTitle_panelSachQuaHan = new JLabel("Tống số sách quá hạn");
		lblTitle_panelSachQuaHan.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle_panelSachQuaHan.setForeground(Color.WHITE);
		lblTitle_panelSachQuaHan.setFont(new Font("Calibri", Font.BOLD, 25));
		lblTitle_panelSachQuaHan.setBounds(10, 26, 271, 44);
		panel_SachQuaHan.add(lblTitle_panelSachQuaHan);
		
		JLabel lblTong_panelSachQuaHan = new JLabel(phieuMuonSachDAO.getphieuMuonSachDAO().soLuongSachDaQuaHan() + " cuốn sách");
//		JLabel lblTong_panelSachQuaHan = new JLabel(11 + " cuốn sách");
		lblTong_panelSachQuaHan.setHorizontalAlignment(SwingConstants.CENTER);
		lblTong_panelSachQuaHan.setHorizontalAlignment(SwingConstants.CENTER);
		lblTong_panelSachQuaHan.setForeground(Color.WHITE);
		lblTong_panelSachQuaHan.setFont(new Font("Calibri", Font.BOLD, 23));
		lblTong_panelSachQuaHan.setBounds(36, 70, 218, 44);
		panel_SachQuaHan.add(lblTong_panelSachQuaHan);
		
		JLabel imgBG_panelSachQuaHan = new JLabel("");
		imgBG_panelSachQuaHan.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.choseQLUser();
				controller.changeToQuaHan();
			}
		});
		imgBG_panelSachQuaHan.setBounds(0, 0, 291, 204);
		ImageIcon imgIBG_panelSachQuaHan  = new ImageIcon(
				Paths.get("icon\\tongSachQuaHanjpg.jpg").toAbsolutePath().toString());
//		ImageIcon imgIBG_panelSachQuaHan  = new ImageIcon("C:\\Users\\Admin\\OneDrive\\Desktop\\tongSachQuaHanjpg.jpg");
		Image imBG_panelSachQuaHan  = imgIBG_panelSachQuaHan.getImage();
		Image imageBG_panelSachQuaHan  = imBG_panelSachQuaHan.getScaledInstance(imgBG_panelSachQuaHan.getWidth(), imgBG_panelSachQuaHan.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIconBG_panelSachQuaHan  = new ImageIcon(imageBG_panelSachQuaHan);
		imgBG_panelSachQuaHan.setIcon(imageIconBG_panelSachQuaHan);	
		panel_SachQuaHan.add(imgBG_panelSachQuaHan);
		
		JPanel panel_SachDangDat = new JPanel();
		panel_SachDangDat.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_SachDangDat.setBounds(498, 321, 291, 204);
		panel_ThongKe.add(panel_SachDangDat);
		panel_SachDangDat.setLayout(null);
		
		JLabel lblTitle_panelSachDangDat = new JLabel("Tống số sách đang đặt");
		lblTitle_panelSachDangDat.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle_panelSachDangDat.setForeground(Color.WHITE);
		lblTitle_panelSachDangDat.setFont(new Font("Calibri", Font.BOLD, 25));
		lblTitle_panelSachDangDat.setBounds(10, 26, 271, 44);
		panel_SachDangDat.add(lblTitle_panelSachDangDat);
		
//		JLabel lblTong_panelSachDangDat = new JLabel(11 + " cuốn sách");
		JLabel lblTong_panelSachDangDat = new JLabel(phieuMuonSachDAO.getphieuMuonSachDAO().soLuongSachDangDat() + " cuốn sách");
		lblTong_panelSachDangDat.setHorizontalAlignment(SwingConstants.CENTER);
		lblTong_panelSachDangDat.setForeground(Color.WHITE);
		lblTong_panelSachDangDat.setFont(new Font("Calibri", Font.BOLD, 23));
		lblTong_panelSachDangDat.setBounds(36, 70, 218, 44);
		panel_SachDangDat.add(lblTong_panelSachDangDat);
		
		JLabel imgBG_panelSachDangDat = new JLabel("");
		imgBG_panelSachDangDat.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.choseQLUser();
				controller.changeToDatSach();
			}
		});
		imgBG_panelSachDangDat.setBounds(0, 0, 291, 204);
		ImageIcon imgIBG_panelSachDangDat = new ImageIcon(
				Paths.get("icon\\tongSachDangDatjpg.jpg").toAbsolutePath().toString());
//		ImageIcon imgIBG_panelSachDangDat  = new ImageIcon("C:\\Users\\Admin\\OneDrive\\Desktop\\tongSachDangDatjpg.jpg");
		Image imBG_panelSachDangDat  = imgIBG_panelSachDangDat.getImage();
		Image imageBG_panelSachDangDat  = imBG_panelSachDangDat.getScaledInstance(imgBG_panelSachDangDat.getWidth(), imgBG_panelSachDangDat.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIconBG_panelSachDangDat  = new ImageIcon(imageBG_panelSachDangDat);
		imgBG_panelSachDangDat.setIcon(imageIconBG_panelSachDangDat);	
		panel_SachDangDat.add(imgBG_panelSachDangDat);
		
		JPanel panel_SachDangMuon = new JPanel();
		panel_SachDangMuon.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_SachDangMuon.setBounds(157, 321, 291, 204);
		panel_ThongKe.add(panel_SachDangMuon);
		panel_SachDangMuon.setLayout(null);
		
		JLabel lblTitle_panelSachDangMuon = new JLabel("Tống số sách đang mượn");
		lblTitle_panelSachDangMuon.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle_panelSachDangMuon.setForeground(Color.WHITE);
		lblTitle_panelSachDangMuon.setFont(new Font("Calibri", Font.BOLD, 25));
		lblTitle_panelSachDangMuon.setBounds(10, 26, 271, 44);
		panel_SachDangMuon.add(lblTitle_panelSachDangMuon);
		
		JLabel lblTong_panelSachDangMuon = new JLabel(phieuMuonSachDAO.getphieuMuonSachDAO().soLuongSachDangMuon() + " cuốn sách");
//		JLabel lblTong_panelSachDangMuon = new JLabel(11 + " cuốn sách");
		lblTong_panelSachDangMuon.setHorizontalAlignment(SwingConstants.CENTER);
		lblTong_panelSachDangMuon.setForeground(Color.WHITE);
		lblTong_panelSachDangMuon.setFont(new Font("Calibri", Font.BOLD, 23));
		lblTong_panelSachDangMuon.setBounds(36, 70, 218, 44);
		panel_SachDangMuon.add(lblTong_panelSachDangMuon);
		
		JLabel imgBG_panelSachDangMuon = new JLabel("");
		imgBG_panelSachDangMuon.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.choseQLUser();
				controller.changeToDangMuon();
			}
		});
		imgBG_panelSachDangMuon.setBounds(0, 0, 291, 204);
		ImageIcon imgIBG_panelSachDangMuon  = new ImageIcon(
				Paths.get("icon\\tongSachDangMuonjpg.jpg").toAbsolutePath().toString());
//		ImageIcon imgIBG_panelSachDangMuon = new ImageIcon("C:\\Users\\Admin\\OneDrive\\Desktop\\tongSachDangMuonjpg.jpg");
		Image imBG_panelSachDangMuon = imgIBG_panelSachDangMuon.getImage();
		Image imageBG_panelSachDangMuon = imBG_panelSachDangMuon.getScaledInstance(imgBG_panelSachDangMuon.getWidth(), imgBG_panelSachDangMuon.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imageIconBG_panelSachDangMuon = new ImageIcon(imageBG_panelSachDangMuon);
		imgBG_panelSachDangMuon.setIcon(imageIconBG_panelSachDangMuon);	
		panel_SachDangMuon.add(imgBG_panelSachDangMuon);
		
		// ket thuc code
		
		return panel_ThongKe;
	}

	public JPanel panel_CreatedChart() {
		JPanel panel_CreatedChart = new JPanel();
		panel_CreatedChart.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_CreatedChart.setBackground(new Color(255, 255, 255));
		panel_CreatedChart.setBounds(0, 148, 1286, 616);
		panel_CreatedChart.setLayout(null);
		
		// bat dau code
		
		DefaultPieDataset pieChartData = createdData();
		JFreeChart pieChart = createdChart(pieChartData);
		ChartPanel pieChartPanel = new ChartPanel(pieChart);
		pieChartPanel.setSize(1286, 616);
		pieChartPanel.setLocation(0, 0);
		panel_CreatedChart.add(pieChartPanel);
		
		// ket thuc code
		
		return panel_CreatedChart;
	}
	
	public DefaultPieDataset createdData() {
		List<Object[]> lsit = phieuMuonSachDAO.getphieuMuonSachDAO().selectDoPhoBien();
		
		DefaultPieDataset dataset = new DefaultPieDataset();
		List<sach> listLS = sachDAO.getsachDAO().selectAll();
		for (sach sach : listLS) {
//			dataset.setValue(loaiSach.getTenLoaiSach(), 0.1);
			
			for (Object[] obj : lsit) {
				if(((sach)obj[1]).getMaSach().equals(sach.getMaSach())) {
					 dataset.setValue(sach.getTenSach(), (Long)obj[0]);
				}
			}
		}
        return dataset;
	}
	
	public JFreeChart createdChart(DefaultPieDataset pieChartData) {
		return ChartFactory.createPieChart("ĐỘ PHỔ BIẾN"
				, pieChartData
				, true
				, true
				, false);
	}
	
	public JPanel panel_TTCTUser() {
		user u = new user();
		u.setUsername(emailClone);
		user uDAO = userDAO.getuserDAO().selectG(u);
		
		JPanel panel_TTCTUser = new JPanel();
		panel_TTCTUser.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel_TTCTUser.setBackground(new Color(255, 255, 255));
		panel_TTCTUser.setBounds(0, 148, 1286, 616);
		panel_TTCTUser.setLayout(null);
		
		// bat dau code
		
		JPanel panelTop_panelTTCTUser = new JPanel();
		panelTop_panelTTCTUser.setBounds(100, 10, 1086, 180);
		panel_TTCTUser.add(panelTop_panelTTCTUser);
		panelTop_panelTTCTUser.setLayout(null);
		
		JLabel imgAvatarUser_panelTop = new JLabel("");
		imgAvatarUser_panelTop.setBounds(20, 20, 143, 140);
		
		ImageIcon imgI_AvatarUs = new ImageIcon(Paths.get("icon\\" + uDAO.getTtcn().getHinh()).toAbsolutePath().toString());
		Image img_AvatarUs = imgI_AvatarUs.getImage();
		Image image_AvatarUs = img_AvatarUs.getScaledInstance(imgAvatarUser_panelTop.getWidth(), imgAvatarUser_panelTop.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon imgIcon_AvatarUs = new ImageIcon(image_AvatarUs);
		imgAvatarUser_panelTop.setIcon(imgIcon_AvatarUs);
		
		imgAvatarUser_panelTop.setOpaque(false);
		imgAvatarUser_panelTop.setBackground(new Color(0, 255, 0));
		panelTop_panelTTCTUser.add(imgAvatarUser_panelTop);
		
		JLabel lblTenUser_panelTop = new JLabel(uDAO.getTtcn().getTen());
		lblTenUser_panelTop.setFont(new Font("Arial", Font.BOLD, 22));
		lblTenUser_panelTop.setBounds(183, 36, 271, 31);
		panelTop_panelTTCTUser.add(lblTenUser_panelTop);
		
		lblEmailUser_panelTop = new JLabel(uDAO.getUsername());
		lblEmailUser_panelTop.setFont(new Font("Arial", Font.PLAIN, 20));
		lblEmailUser_panelTop.setBounds(183, 80, 271, 31);
		panelTop_panelTTCTUser.add(lblEmailUser_panelTop);
		
		JLabel lblRoleUser_panelTop = new JLabel("Role: Đọc giả");
		lblRoleUser_panelTop.setFont(new Font("Arial", Font.PLAIN, 20));
		lblRoleUser_panelTop.setBounds(183, 122, 271, 31);
		panelTop_panelTTCTUser.add(lblRoleUser_panelTop);
		
		JLabel lblMatKhauUser_panelTop = new JLabel("Mật khẩu:");
		lblMatKhauUser_panelTop.setFont(new Font("Arial", Font.PLAIN, 20));
		lblMatKhauUser_panelTop.setBounds(566, 20, 137, 31);
		panelTop_panelTTCTUser.add(lblMatKhauUser_panelTop);
		
		JLabel lblSDTUser_panelTop = new JLabel("Số điện thoại");
		lblSDTUser_panelTop.setFont(new Font("Arial", Font.PLAIN, 20));
		lblSDTUser_panelTop.setBounds(566, 61, 137, 31);
		panelTop_panelTTCTUser.add(lblSDTUser_panelTop);
		
		JLabel lblDiaChiUser_panelTop = new JLabel("Địa chỉ:");
		lblDiaChiUser_panelTop.setFont(new Font("Arial", Font.PLAIN, 20));
		lblDiaChiUser_panelTop.setBounds(566, 102, 137, 31);
		panelTop_panelTTCTUser.add(lblDiaChiUser_panelTop);
		
		JTextArea txtDiaChiUser_panelTop = new JTextArea();
		txtDiaChiUser_panelTop.setEditable(false);
		txtDiaChiUser_panelTop.setLineWrap(true);
		txtDiaChiUser_panelTop.setFont(new Font("Arial", Font.PLAIN, 16));
		txtDiaChiUser_panelTop.setBounds(713, 102, 353, 68);
		panelTop_panelTTCTUser.add(txtDiaChiUser_panelTop);
		txtDiaChiUser_panelTop.setText(uDAO.getTtcn().getDiaChi());
		
		JButton btnUpdateMK_panelTop = new JButton("Cập nhật");
		btnUpdateMK_panelTop.setFont(new Font("Arial", Font.BOLD, 16));
		btnUpdateMK_panelTop.setBounds(961, 20, 105, 31);
		btnUpdateMK_panelTop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.updatePassWord_panelTTCTUser();
			}
		});
		panelTop_panelTTCTUser.add(btnUpdateMK_panelTop);
		
		JTextArea txtSDTUser_panelTop = new JTextArea();
		txtSDTUser_panelTop.setEditable(false);
		txtSDTUser_panelTop.setFont(new Font("Arial", Font.PLAIN, 16));
		txtSDTUser_panelTop.setBounds(713, 61, 353, 31);
		panelTop_panelTTCTUser.add(txtSDTUser_panelTop);
		txtSDTUser_panelTop.setText(uDAO.getTtcn().getSoDienThoai());
		
		txtMatKhauUser_panelTop = new JTextArea();
		txtMatKhauUser_panelTop.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtMatKhauUser_panelTop.setEditable(true);
			}
		});
		txtMatKhauUser_panelTop.setFont(new Font("Arial", Font.PLAIN, 16));
		txtMatKhauUser_panelTop.setEditable(false);
		txtMatKhauUser_panelTop.setBounds(713, 20, 228, 31);
		txtMatKhauUser_panelTop.setText(uDAO.getPassword());
		panelTop_panelTTCTUser.add(txtMatKhauUser_panelTop);
		
		JScrollPane scrollPane_panelTTCTUser = new JScrollPane();
		scrollPane_panelTTCTUser.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch m\u01B0\u1EE3n - tr\u1EA3 - qu\u00E1 h\u1EA1n S\u00E1ch", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, new Color(0, 0, 0)));
		scrollPane_panelTTCTUser.setBounds(100, 210, 1086, 386);
		panel_TTCTUser.add(scrollPane_panelTTCTUser);
		
		tblTTCTUser = new JTable();
		tblTTCTUser.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"M\u00E3 s\u00E1ch", "T\u00EAn s\u00E1ch", "Ng\u00E0y m\u01B0\u1EE3n s\u00E1ch", "Ng\u00E0y tr\u1EA3 s\u00E1ch", "Tr\u1EA1ng th\u00E1i"
			}
		) {
			boolean[] columnEditables = new boolean[] {
					false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblTTCTUser.setFont(new Font("Arial", Font.PLAIN, 18));
		tblTTCTUser.setRowHeight(25);
		DefaultTableModel tableModel_panelTTCTUser = (DefaultTableModel) tblTTCTUser.getModel();
		tableModel_panelTTCTUser.setRowCount(0);
		
		List<phieuMuonSach> ListPMS = phieuMuonSachDAO.getphieuMuonSachDAO().selectAll();
		for(phieuMuonSach pmsOfUS : uDAO.getListPM()) {
			for (phieuMuonSach phieuMuonSach : ListPMS) {
				if(pmsOfUS.getMaPhieu().equals(phieuMuonSach.getMaPhieu()) && !phieuMuonSach.getTrangThaiPhieu().equals("Đã xóa")) {
					tableModel_panelTTCTUser.addRow(new Object[] {
							phieuMuonSach.getMaSach().getMaSach(),
							phieuMuonSach.getMaSach().getTenSach(),
							new SimpleDateFormat("dd-MM-yyyy").format(phieuMuonSach.getNgayMuon()),
							new SimpleDateFormat("dd-MM-yyyy").format(phieuMuonSach.getNgayTra()),
							phieuMuonSach.getTrangThaiPhieu()
					});
				}
			}
		}
		
		scrollPane_panelTTCTUser.setViewportView(tblTTCTUser);
		
		// ket thuc code
		
		return panel_TTCTUser;
	}
}

