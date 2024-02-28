package view;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.controllerQuenMK;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class quenMK extends JFrame {

	private controllerQuenMK controller = new controllerQuenMK(this);
	private JPanel panelBG;
	public JTextField txtEmail;
	public JTextField txtMaxacnhan;
	public JLabel lblSetEnEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					quenMK frame = new quenMK();
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
	public quenMK() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 862, 569);
		this.setLocationRelativeTo(null);
		panelBG = new JPanel();
		panelBG.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(panelBG);
		panelBG.setLayout(null);
		
		// Quên mk
		JLabel lblQuenMatKhau = new JLabel("Quên mật khẩu");
		lblQuenMatKhau.setBackground(new Color(0, 0, 0));
		lblQuenMatKhau.setHorizontalAlignment(SwingConstants.CENTER);
		lblQuenMatKhau.setForeground(new Color(0, 0, 0));
		lblQuenMatKhau.setFont(new Font("Arial", Font.BOLD, 28));
		lblQuenMatKhau.setBounds(531, 88, 209, 76);
		panelBG.add(lblQuenMatKhau);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblEmail.setBounds(413, 201, 89, 40);
		panelBG.add(lblEmail);
		
		
		JLabel lblMaxacnhan = new JLabel("Mã xác nhận:");
		lblMaxacnhan.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaxacnhan.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblMaxacnhan.setBounds(390, 251, 122, 51);
		panelBG.add(lblMaxacnhan);
		
		lblSetEnEmail = new JLabel("");
		lblSetEnEmail.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				controller.enableEmail();
			}
		});
		lblSetEnEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblSetEnEmail.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblSetEnEmail.setIcon(new ImageIcon(Paths.get("icon\\exit16.png").toAbsolutePath().toString()));
		lblSetEnEmail.setBounds(739, 209, 45, 32);
		lblSetEnEmail.setVisible(false);
		panelBG.add(lblSetEnEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtEmail.setBounds(512, 209, 272, 33);
		panelBG.add(txtEmail);
		txtEmail.setColumns(10);
		
		txtMaxacnhan = new JTextField();
		txtMaxacnhan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtMaxacnhan.setBounds(512, 263, 116, 33);
		panelBG.add(txtMaxacnhan);
		txtMaxacnhan.setColumns(10);
		
		JButton btnMaxacnhan = new JButton("Gửi mã xác nhận");
		btnMaxacnhan.setFocusPainted(false);
		btnMaxacnhan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnMaxacnhan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.guimail();
			}
		});
		btnMaxacnhan.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnMaxacnhan.setBounds(638, 263, 146, 33);
		panelBG.add(btnMaxacnhan);
		
		
		JButton btnDongy = new JButton("Đồng ý");
		btnDongy.setFocusPainted(false);
		btnDongy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnDongy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.clickbtnOK();
			}
		});
		btnDongy.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnDongy.setBounds(519, 342, 92, 30);
		panelBG.add(btnDongy);
		
		JButton btnHuy = new JButton("Huỷ");
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.clickHuy();
			}
		});
		btnHuy.setFocusPainted(false);
		btnHuy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHuy.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnHuy.setBounds(648, 342, 92, 30);
		panelBG.add(btnHuy);
		
		JLabel lblBG = new JLabel("");
		lblBG.setVerticalAlignment(SwingConstants.TOP);
		lblBG.setBounds(0, 0, 862, 569);
		ImageIcon bg = new ImageIcon(Paths.get("icon\\login_BG.png").toAbsolutePath().toString());
		lblBG.setIcon(bg);
		panelBG.add(lblBG);
		
	}
}