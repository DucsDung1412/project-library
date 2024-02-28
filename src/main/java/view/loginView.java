package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.nio.file.Paths;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.loginListener;
import dao.userDAO;
import model.user;
import view.googleLogin;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;

public class loginView extends JFrame {

	private JPanel contentPane;
	private JPanel pnlBackground_contentPane;
	private JPanel pnlFormLogin_contentPane;
	private JLabel lblTitle_pnlFormLogin_contentPane;
	private JLabel lblUsername_pnlFormLogin_contentPane;
	private JLabel lblPassword_pnlFormLogin_contentPane;
	private JLabel lblForgot_pnlFormLogin_contentPane;
	
	public JTextField txtUsername_pnlFormLogin_contentPane;
	public JPasswordField txtPassword_pnlFormLogin_contentPane;
	
	private JButton btnLogin_pnlFormLogin_contentPane;
	
	private Font fontTitle;
	private Font fontLabel;
	private Font fontText;
	
	private loginListener loginC = new loginListener(this);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginView frame = new loginView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public loginView() {
		this.setTitle("LogIn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 200, 862, 569);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setBounds(0, 0, this.getWidth(), this.getHeight());
		contentPane.setLayout(null);
		setContentPane(contentPane);
		this.setLocationRelativeTo(null);
		this.init();
	}
	
	public void init()
	{
		//setPanelMainContent
		pnlBackground_contentPane = new JPanel();
		pnlBackground_contentPane.setBounds(0,0, this.getWidth() , this.getHeight());
		pnlBackground_contentPane.setBackground(Color.green);
		pnlBackground_contentPane.setLayout(null);
		
		//addbackGround
		JLabel lblBackground_pnlBackground_contentPane = new JLabel();
		lblBackground_pnlBackground_contentPane.setIcon(new ImageIcon(Paths.get("icon\\login_BG.png").toAbsolutePath().toString()));
//		lblBackground_pnlBackground_contentPane.setIcon(new ImageIcon("C:/Users/Admin/eclipse-workspace/librarySys/src/main/java/icon/login_BG.png"));
		lblBackground_pnlBackground_contentPane.setBounds(0,0 , 862, 569);
		pnlBackground_contentPane.add(lblBackground_pnlBackground_contentPane);
		
		
		//addForm
		pnlFormLogin_contentPane = new JPanel();
		pnlFormLogin_contentPane.setLayout(null);
		pnlFormLogin_contentPane.setBackground(Color.white);
		pnlFormLogin_contentPane.setForeground(Color.black);
		pnlFormLogin_contentPane.setBounds(450, 75, 350, 400);
		
		fontTitle = new Font("Calibri" , Font.BOLD , 30);
		fontLabel = new Font("Calibri" , Font.BOLD , 14);
		fontText = new Font("Calibri Light" , 0 , 14);
		
		lblTitle_pnlFormLogin_contentPane = new JLabel("USER LOGIN");
		lblTitle_pnlFormLogin_contentPane.setFont(fontTitle);
		lblTitle_pnlFormLogin_contentPane.setForeground(new Color(0, 191, 255));
		lblTitle_pnlFormLogin_contentPane.setBounds(105, 62, 169, 30);
		
		lblUsername_pnlFormLogin_contentPane = new JLabel();
		lblUsername_pnlFormLogin_contentPane.setFont(fontLabel);
		lblUsername_pnlFormLogin_contentPane.setBounds(43, 116, 32, 30);
		lblUsername_pnlFormLogin_contentPane.setIcon(new ImageIcon(Paths.get("icon\\User_Icon.png").toAbsolutePath().toString()));
//		lblUsername_pnlFormLogin_contentPane.setIcon(new ImageIcon("E:\\Eclipse Project\\nhatdlps26321\\src\\main\\java\\img\\User_Icon.png"));
		
		lblPassword_pnlFormLogin_contentPane = new JLabel();
		lblPassword_pnlFormLogin_contentPane.setFont(fontLabel);
		lblPassword_pnlFormLogin_contentPane.setBounds(43, 157, 32, 30);
		lblPassword_pnlFormLogin_contentPane.setIcon(new ImageIcon(Paths.get("icon\\Pass_Icon.png").toAbsolutePath().toString()));
//		lblPassword_pnlFormLogin_contentPane.setIcon(new ImageIcon("E:\\Eclipse Project\\nhatdlps26321\\src\\main\\java\\img\\Pass_Icon.png"));
		
		txtUsername_pnlFormLogin_contentPane = new JTextField(50);
		txtUsername_pnlFormLogin_contentPane.setFont(fontText);
		txtUsername_pnlFormLogin_contentPane.setBounds(85, 116, 225, 30);
		
		txtPassword_pnlFormLogin_contentPane = new JPasswordField(50);
		txtPassword_pnlFormLogin_contentPane.setFont(fontText);
		txtPassword_pnlFormLogin_contentPane.setBounds(85, 157, 225, 30);
		
		lblForgot_pnlFormLogin_contentPane = new JLabel("Forgotten Password!!!");
		lblForgot_pnlFormLogin_contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblForgot_pnlFormLogin_contentPane.setHorizontalAlignment(SwingConstants.CENTER);
		lblForgot_pnlFormLogin_contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				loginC.forgottenPassword();
			}
		});
		lblForgot_pnlFormLogin_contentPane.setForeground(new Color(0, 191, 255));
		lblForgot_pnlFormLogin_contentPane.setFont(fontLabel);
		lblForgot_pnlFormLogin_contentPane.setBounds(171, 208 , 149,30);

		
		btnLogin_pnlFormLogin_contentPane = new JButton("Log In");
		btnLogin_pnlFormLogin_contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin_pnlFormLogin_contentPane.setFont(new Font("Calibri" , Font.BOLD , 20));
		btnLogin_pnlFormLogin_contentPane.setBackground(new Color(0 , 191 , 255));
		btnLogin_pnlFormLogin_contentPane.setForeground(Color.white);
		btnLogin_pnlFormLogin_contentPane.setBounds(130 , 263 , 110, 30);
		btnLogin_pnlFormLogin_contentPane.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				loginC.logIn();
			}
		});

		googleLogin googleLogin = new googleLogin();
		googleLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		googleLogin.setOpaque(false);
		googleLogin.setLocation(96, 324);
		googleLogin.setSize(176, 30);
		pnlFormLogin_contentPane.add(googleLogin);
		
		pnlFormLogin_contentPane.add(lblTitle_pnlFormLogin_contentPane);
		pnlFormLogin_contentPane.add(lblUsername_pnlFormLogin_contentPane);
		pnlFormLogin_contentPane.add(lblPassword_pnlFormLogin_contentPane);
		pnlFormLogin_contentPane.add(txtUsername_pnlFormLogin_contentPane);
		pnlFormLogin_contentPane.add(txtPassword_pnlFormLogin_contentPane);
		pnlFormLogin_contentPane.add(lblForgot_pnlFormLogin_contentPane);
		pnlFormLogin_contentPane.add(btnLogin_pnlFormLogin_contentPane);
		
		
		//add to contentPane
		contentPane.add(pnlFormLogin_contentPane);
		
		JLabel lblCreateAccount_pnlFormLogin_contentPane = new JLabel("Create Account!!!");
		lblCreateAccount_pnlFormLogin_contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblCreateAccount_pnlFormLogin_contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				loginC.creatAccount();
			}
		});
		lblCreateAccount_pnlFormLogin_contentPane.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreateAccount_pnlFormLogin_contentPane.setForeground(new Color(0, 191, 255));
		lblCreateAccount_pnlFormLogin_contentPane.setFont(new Font("Calibri", Font.BOLD, 14));
		lblCreateAccount_pnlFormLogin_contentPane.setBounds(30, 208, 122, 30);
		pnlFormLogin_contentPane.add(lblCreateAccount_pnlFormLogin_contentPane);
		contentPane.add(pnlBackground_contentPane);
	}
}