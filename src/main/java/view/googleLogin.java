package view;

import javax.swing.JPanel;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import controller.loginListener;
import dao.userDAO;
import model.user;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.awt.event.ActionEvent;

public class googleLogin extends JPanel {
	private static final String CLIENT_ID = "739747427774-2ab3trhbcbdjakt4l1diqv6s0fq6etb4.apps.googleusercontent.com";
    private static final String CLIENT_SECRET = "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";
	private static GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
	private static HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
	/**
	 * Create the panel.
	 */
	public googleLogin() {
		setLayout(null);
		
		JButton btnNewButton = new JButton("Login with google");
		btnNewButton.setOpaque(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginGoogle();
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 16));
		setOpaque(false);
		btnNewButton.setBounds(0, 0, 176, 30);
		add(btnNewButton);
		
	}
	
	public void loginGoogle() {
		GoogleAuthorizationCodeFlow flow = this.getFlow();
		
		GoogleAuthorizationCodeRequestUrl authURL = flow.newAuthorizationUrl().setRedirectUri(REDIRECT_URI);
		
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null ; 
		
		try {
			if(desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
				desktop.browse(authURL.toURI());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String code = JOptionPane.showInputDialog(null, "Vùi lòng nhập authorization code");
		if (code != null && !code.trim().isEmpty()) {
			try {
				GoogleTokenResponse token = flow.newTokenRequest(code).setRedirectUri(REDIRECT_URI).execute();
				
				String email = token.parseIdToken().getPayload().getEmail();
//				System.out.println(email);
				
				
				user u = new user();
				u.setUsername(email);
				if(userDAO.getuserDAO().selectG(u) != null) {
					JOptionPane.showMessageDialog(null, "Đăng nhập thành công");
					libraryManagerSystemView view = new libraryManagerSystemView(email);
					view.setVisible(true);
					view.emailLogin = email;
				} else {
					JOptionPane.showMessageDialog(null, "Tài khoản chưa tồn tại");
				}
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public GoogleAuthorizationCodeFlow getFlow() {
		GoogleClientSecrets clinetSecrets;
		try {
			clinetSecrets = GoogleClientSecrets.load(JSON_FACTORY, new FileReader("client_secret.json"));
			return new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, clinetSecrets,  Arrays.asList("https://www.googleapis.com/auth/userinfo.email",
                    "https://www.googleapis.com/auth/userinfo.profile")).build();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
