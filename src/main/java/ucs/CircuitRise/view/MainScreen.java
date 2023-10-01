package ucs.CircuitRise.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel cardPanel;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private CardLayout cardLayout;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainScreen frame = new MainScreen();
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
	public MainScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 540);
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		this.add(cardPanel);
		
		RegisterScreen reg = new RegisterScreen(this);
		cardPanel.add(reg, "Register");
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(177, 178, 181));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);

		
		JPanel header = new JPanel();
		header.setBorder(new EmptyBorder(0, 0, 0, 0));
		header.setBackground(new Color(226, 36, 32));
		header.setBounds(10, 11, 924, 65);
		header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));
		contentPane.add(header);
		
		lblNewLabel = new JLabel("    Circuit Rise");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 26));
		header.add(lblNewLabel);
		
		JButton btnPartRegister = new JButton("Registrar participante");
		btnPartRegister.setFont(new Font("Arial", Font.PLAIN, 16));
		btnPartRegister.setBounds(657, 137, 193, 52);
		btnPartRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "Register");
			}
		});
		contentPane.add(btnPartRegister);
		
		JButton btnRaceRegister = new JButton("Registrar evento");
		btnRaceRegister.setFont(new Font("Arial", Font.PLAIN, 16));
		btnRaceRegister.setBounds(657, 209, 193, 52);
		btnRaceRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
			}
		});
		contentPane.add(btnRaceRegister);
		
		JButton btnExit = new JButton("Sair");
		btnExit.setFont(new Font("Arial", Font.PLAIN, 12));
		btnExit.setBounds(845, 467, 89, 23);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				System.exit(0);
			}
		});
		contentPane.add(btnExit);
		cardPanel.add(contentPane, "Main");
		cardLayout.show(cardPanel, "Main");
	}
	
	public void menuReturn() {
		cardLayout.show(cardPanel, "Main");
	}
}
