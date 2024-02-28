package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.controllerRegister;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class registerView extends JFrame {

	public JPanel contentPane;
	public JTextField txtName_panelRegister;
	public JTextField txtEmail_panelRegister;
	public JPasswordField txtPass_panelRegister;
	public JPasswordField txtConfirm_panelRegister;
	public JLabel lblName_panelRegister;
	public JLabel lblEmail_panelRegister;
	public JLabel lblPass_panelRegister;
	public JLabel lblConfirm_panelRegister;
	public JLabel imgBg_panelRegister;
	public controllerRegister c = new controllerRegister(this);
	public JCheckBox ckbAgree_panelRegister;
	public JButton btnHienPass_panelRegister, btnCancel_panelRegister, btnSign_panelRegister;
	public JButton btnAnPass_panelRegister;
	public JButton btnHienConfirm_panelRegister;
	public JButton btnAnConfirm_panelRegister;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerView frame = new registerView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public registerView() {
		// contentPane
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 820, 530);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		// panel_Register
		JPanel panel_Register = new JPanel();
		panel_Register.setBounds(0, 0, 806, 493);
		contentPane.add(panel_Register);
		panel_Register.setLayout(null);

		// lblRegister_panelRegister
		JLabel lblRegister_panelRegister = new JLabel("REGISTER");
		lblRegister_panelRegister.setBounds(315, 40, 419, 49);
		lblRegister_panelRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegister_panelRegister.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 40));
		panel_Register.add(lblRegister_panelRegister);

		// txtName_panelRegister
		txtName_panelRegister = new JTextField();
		txtName_panelRegister.setHorizontalAlignment(SwingConstants.LEFT);
		txtName_panelRegister.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtName_panelRegister.setBounds(483, 139, 251, 28);
		txtName_panelRegister.setMargin(new Insets(3, 5, 0, 0));
		txtName_panelRegister.setColumns(10);
		panel_Register.add(txtName_panelRegister);

		// txtEmail_panelRegister
		txtEmail_panelRegister = new JTextField();
		txtEmail_panelRegister.setHorizontalAlignment(SwingConstants.LEFT);
		txtEmail_panelRegister.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtEmail_panelRegister.setBounds(483, 182, 251, 28);
		txtEmail_panelRegister.setMargin(new Insets(3, 5, 0, 0));
		txtEmail_panelRegister.setColumns(10);
		panel_Register.add(txtEmail_panelRegister);

		// txtPass_panelRegister
		txtPass_panelRegister = new JPasswordField();
		txtPass_panelRegister.setHorizontalAlignment(SwingConstants.LEFT);
		txtPass_panelRegister.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtPass_panelRegister.setBounds(483, 225, 251, 28);
		txtPass_panelRegister.setMargin(new Insets(3, 5, 0, 0));
		panel_Register.add(txtPass_panelRegister);

		// txtConfirm_panelRegister
		txtConfirm_panelRegister = new JPasswordField();
		txtConfirm_panelRegister.setHorizontalAlignment(SwingConstants.LEFT);
		txtConfirm_panelRegister.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtConfirm_panelRegister.setBounds(483, 268, 250, 28);
		txtConfirm_panelRegister.setMargin(new Insets(3, 5, 0, 0));
		panel_Register.add(txtConfirm_panelRegister);

		// ckbAgree_panelRegister
		ckbAgree_panelRegister = new JCheckBox("I Agree to the Term of User");
		ckbAgree_panelRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		ckbAgree_panelRegister.setBackground(Color.WHITE);
		ckbAgree_panelRegister.setBounds(483, 314, 251, 25);
		ckbAgree_panelRegister.setOpaque(false);
		ckbAgree_panelRegister.setFocusPainted(false);
		ckbAgree_panelRegister.setFont(new Font("Calibri Light", Font.BOLD | Font.ITALIC, 18));
		panel_Register.add(ckbAgree_panelRegister);

		// btnSign_panelRegister
		btnSign_panelRegister = new JButton("SIGN UP\r\n");
		btnSign_panelRegister.setFocusPainted(false);
		btnSign_panelRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSign_panelRegister.setBounds(483, 368, 92, 30);
		btnSign_panelRegister.setFont(new Font("Calibri", Font.BOLD, 15));
		btnSign_panelRegister.setMargin(new Insets(5, 0, 0, 0));
		btnSign_panelRegister.addActionListener(c);
		panel_Register.add(btnSign_panelRegister);

		// btnCancel_panelRegister
		btnCancel_panelRegister = new JButton("CANCEL");
		btnCancel_panelRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				c.backLogin();
			}
		});
		btnCancel_panelRegister.setFocusPainted(false);
		btnCancel_panelRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel_panelRegister.setBounds(642, 368, 92, 30);
		btnCancel_panelRegister.setFont(new Font("Calibri", Font.BOLD, 15));
		btnCancel_panelRegister.setMargin(new Insets(5, 0, 0, 0));
		btnCancel_panelRegister.addActionListener(c);
		panel_Register.add(btnCancel_panelRegister);

		// lblName_panelRegister
		lblName_panelRegister = new JLabel("Name");
		lblName_panelRegister.setBounds(315, 147, 150, 20);
		lblName_panelRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblName_panelRegister.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		panel_Register.add(lblName_panelRegister);

		// lblEmail_panelRegister
		lblEmail_panelRegister = new JLabel("Email");
		lblEmail_panelRegister.setBounds(315, 193, 150, 17);
		lblEmail_panelRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail_panelRegister.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		panel_Register.add(lblEmail_panelRegister);

		// lblPass_panelRegister
		lblPass_panelRegister = new JLabel("Password");
		lblPass_panelRegister.setBounds(315, 233, 150, 20);
		lblPass_panelRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblPass_panelRegister.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		panel_Register.add(lblPass_panelRegister);

		// lblConfirm_panelRegister
		lblConfirm_panelRegister = new JLabel("Confirm Password");
		lblConfirm_panelRegister.setBounds(315, 276, 150, 20);
		lblConfirm_panelRegister.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirm_panelRegister.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 18));
		panel_Register.add(lblConfirm_panelRegister);

		// hien pass
		btnHienPass_panelRegister = new JButton("");
		btnHienPass_panelRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHienPass_panelRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				c.hienPass();
			}
		});
		btnHienPass_panelRegister
				.setIcon(new ImageIcon(Paths.get("icon\\hien_mk.png").toAbsolutePath().toString()));
		btnHienPass_panelRegister.setForeground(Color.BLACK);
		btnHienPass_panelRegister.setBounds(744, 225, 37, 28);
		panel_Register.add(btnHienPass_panelRegister);

		// an pass
		btnAnPass_panelRegister = new JButton("");
		btnAnPass_panelRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnPass_panelRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				c.anPass();
			}
		});
		btnAnPass_panelRegister.setVisible(false);
		btnAnPass_panelRegister
				.setIcon(new ImageIcon(Paths.get("icon\\an_mk.png").toAbsolutePath().toString()));
		btnAnPass_panelRegister.setForeground(Color.BLACK);
		btnAnPass_panelRegister.setBounds(744, 225, 37, 28);
		panel_Register.add(btnAnPass_panelRegister);

		// hien confirm
		btnHienConfirm_panelRegister = new JButton("");
		btnHienConfirm_panelRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHienConfirm_panelRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				c.hienConfirm();
			}
		});
		btnHienConfirm_panelRegister
				.setIcon(new ImageIcon(Paths.get("icon\\hien_mk.png").toAbsolutePath().toString()));
		btnHienConfirm_panelRegister.setForeground(Color.BLACK);
		btnHienConfirm_panelRegister.setBounds(743, 268, 37, 28);
		panel_Register.add(btnHienConfirm_panelRegister);

		// an confirm
		btnAnConfirm_panelRegister = new JButton("");
		btnAnConfirm_panelRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAnConfirm_panelRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				c.anConfirm();
			}
		});
		btnAnConfirm_panelRegister.setVisible(false);
		btnAnConfirm_panelRegister
				.setIcon(new ImageIcon(Paths.get("icon\\an_mk.png").toAbsolutePath().toString()));
		btnAnConfirm_panelRegister.setForeground(Color.BLACK);
		btnAnConfirm_panelRegister.setBounds(743, 268, 37, 28);
		panel_Register.add(btnAnConfirm_panelRegister);

		// lblBg imgBg_panelRegister
		imgBg_panelRegister = new JLabel("");
		imgBg_panelRegister.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 15));
		imgBg_panelRegister
				.setIcon(new ImageIcon(Paths.get("icon\\login_BG.png").toAbsolutePath().toString()));
		imgBg_panelRegister.setBounds(-30, -60, 920, 600);
		panel_Register.add(imgBg_panelRegister);
	}
}