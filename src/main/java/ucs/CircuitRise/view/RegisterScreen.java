package ucs.CircuitRise.view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JList;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class RegisterScreen extends JPanel {

	private static final long serialVersionUID = 1L;


	public RegisterScreen() {
		setBounds(0, 0, 960, 540);
		setBackground(new Color(177, 178, 181));
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(new Color(226, 36, 32));
		panel.setBounds(10, 11, 924, 65);
		add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(10, 14, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Cadastros");
		lblNewLabel.setBounds(387, 11, 126, 31);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 26));
		panel.add(lblNewLabel);
	}
}
