package ucs.CircuitRise.view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class RegisterScreen extends JPanel {

	private static final long serialVersionUID = 1L;

	JPanel panel = new JPanel();
	MainScreen ms;
	
	public RegisterScreen(MainScreen menu) {
		this.ms = menu;
		setBounds(0, 0, 960, 540);
		setBackground(new Color(177, 178, 181));
		setLayout(null);
		
		
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(new Color(226, 36, 32));
		panel.setBounds(10, 11, 924, 65);
		add(panel);
		panel.setLayout(null);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\lmagn\\OneDrive\\Área de Trabalho\\Projetos Java\\CircuitRise\\img\\return_icon.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton btnReturn = new JButton("");
		btnReturn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ms.menuReturn();		
			}
		});
		btnReturn.setBackground(new Color(226, 36, 32));
		btnReturn.setForeground(new Color(226, 36, 32));
		btnReturn.setIcon(new ImageIcon(newImg));
		btnReturn.setBounds(10, 14, 38, 40);
		btnReturn.setBorderPainted(false);
		btnReturn.setFocusPainted(false);
		btnReturn.setContentAreaFilled(false);
		btnReturn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(btnReturn);
		
		JLabel lblNewLabel = new JLabel("Cadastros");
		lblNewLabel.setBounds(387, 11, 126, 31);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 26));
		panel.add(lblNewLabel);
	}
	
	
}
