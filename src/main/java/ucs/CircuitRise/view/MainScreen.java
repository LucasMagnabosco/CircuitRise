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
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblNewLabel;

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
		contentPane = new JPanel();
		contentPane.setBackground(new Color(177, 178, 181));
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(new Color(226, 36, 32));
		panel.setBounds(10, 11, 924, 65);
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		lblNewLabel = new JLabel("    Circuit Rise");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 26));
		panel.add(lblNewLabel);
		
		JButton btnPartRegister = new JButton("Registrar participante");
		btnPartRegister.setFont(new Font("Arial", Font.PLAIN, 16));
		btnPartRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				RegisterScreen regScreen = new RegisterScreen();
				getContentPane().add(regScreen);
				revalidate();
				repaint();
			}
		});
		btnPartRegister.setBounds(657, 137, 193, 52);
		contentPane.add(btnPartRegister);
		
		JButton btnRaceRegister = new JButton("Registrar evento");
		btnRaceRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
			}
		});
		btnRaceRegister.setFont(new Font("Arial", Font.PLAIN, 16));
		btnRaceRegister.setBounds(657, 209, 193, 52);
		contentPane.add(btnRaceRegister);
		
		JButton btnExit = new JButton("Sair");
		btnExit.setFont(new Font("Arial", Font.PLAIN, 12));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnExit.setBounds(845, 467, 89, 23);
		contentPane.add(btnExit);
	}
}
