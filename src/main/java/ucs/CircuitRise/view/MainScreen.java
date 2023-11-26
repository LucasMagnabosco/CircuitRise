package ucs.CircuitRise.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
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

	
	public MainScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 580);
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		this.add(cardPanel);
		
		RegisterScreen reg = new RegisterScreen(this);
		cardPanel.add(reg, "Register");
		StageRegister stg = new StageRegister(this);
		cardPanel.add(stg, "Stage");
		TeamSelect tsc = new TeamSelect(this);
		cardPanel.add(tsc, "Team");
		PilotSelect plt = new PilotSelect(this);
		cardPanel.add(plt, "Pilot");
		
		
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
				
				cardLayout.show(cardPanel, "Stage");

			}
		});
		contentPane.add(btnRaceRegister);
		
		JButton btnTeamSelect = new JButton("Pontuação Constutores");
		btnTeamSelect.setFont(new Font("Arial", Font.PLAIN, 16));
		btnTeamSelect.setBounds(605, 280, 293, 52);
		btnTeamSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "Team");
			}
		});
		contentPane.add(btnTeamSelect);
		
		JButton btnPilotSelect = new JButton("Pontuação Pilotos");
		btnPilotSelect.setFont(new Font("Arial", Font.PLAIN, 16));
		btnPilotSelect.setBounds(605, 350, 293, 52);
		btnPilotSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(cardPanel, "Pilot");
			}
		});
		contentPane.add(btnPilotSelect);
		
		
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
