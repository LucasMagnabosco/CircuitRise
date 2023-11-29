package ucs.CircuitRise.view;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ucs.CircuitRise.controller.DataController;
import ucs.CircuitRise.model.FinalTime;

import javax.swing.BoxLayout;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class MainScreen extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel cardPanel;
	private JPanel contentPane;
	private JLabel lblNewLabel;
	private CardLayout cardLayout;
	RegisterScreen reg = new RegisterScreen(this);
	StageRegister stg = new StageRegister(this);
	SeasonTable tbl = new SeasonTable(this);
	Insertion ins = new Insertion(this);
	
	public MainScreen() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 580);
		
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		getContentPane().add(cardPanel);
		
		
		cardPanel.add(reg, "Register");
		cardPanel.add(stg, "Stage");
		cardPanel.add(tbl, "Table");
		cardPanel.add(ins, "Insert");
		
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
		
		lblNewLabel = new JLabel("       Circuit Rise");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 26));
		header.add(lblNewLabel);
		
		JButton btnPartRegister = new JButton("Registrar participante");
		btnPartRegister.setFont(new Font("Arial", Font.PLAIN, 16));
		btnPartRegister.setBounds(657, 137, 193, 52);
		btnPartRegister.addActionListener(this);
		contentPane.add(btnPartRegister);
		
		JButton btnRaceRegister = new JButton("Registrar evento");
		btnRaceRegister.setFont(new Font("Arial", Font.PLAIN, 16));
		btnRaceRegister.setBounds(657, 209, 193, 52);
		btnRaceRegister.addActionListener(this);
		contentPane.add(btnRaceRegister);
		
		JButton btnTable = new JButton("Tabela geral");
		btnTable.setFont(new Font("Arial", Font.PLAIN, 16));
		btnTable.setBounds(40, 137, 193, 52);
		btnTable.addActionListener(this);
		contentPane.add(btnTable);
		
		JButton btnStageTable = new JButton("Resultados por etapa");
		btnStageTable.setFont(new Font("Arial", Font.PLAIN, 16));
		btnStageTable.setBounds(40, 209, 193, 52);
		btnStageTable.addActionListener(this);
		contentPane.add(btnStageTable);
		
		JButton btnData = new JButton("Inserção de resultados");
		btnData.setBounds(349, 139, 193, 52);
		btnData.setFont(new Font("Arial", Font.PLAIN, 16));
		btnData.addActionListener(this);
		contentPane.add(btnData);
		
		JButton btnExit = new JButton("Sair");
		btnExit.setFont(new Font("Arial", Font.PLAIN, 12));
		btnExit.setBounds(845, 467, 89, 23);
		btnExit.addActionListener(this);
		contentPane.add(btnExit);
	
		cardPanel.add(contentPane, "Main");
		cardLayout.show(cardPanel, "Main");
	}
	
	public void actionPerformed(ActionEvent e) {
		String teste = "";
		teste = ((JButton) e.getSource()).getText();
		if(teste.equals("Registrar participante")) {
			cardLayout.show(cardPanel, "Register");
		}else if(teste.equals("Registrar evento")) {
			cardLayout.show(cardPanel, "Stage");
			stg.updateLists();
		}else if(teste.equals("Tabela geral")) {
			//cardLayout.show(cardPanel, "Table");
			DataController data = new DataController();
			List<FinalTime> list = data.teste();
			
			for(FinalTime f : list) {
				System.out.println("----------------------------");
				System.out.println(f.getPilot().getName());
				System.out.println(f.getFinalTime());
			}
			
			
			
		}else if(teste.equals("Resultados por etapa")) {
			
		}else if(teste.equals("Inserção de resultados")) {
			cardLayout.show(cardPanel, "Insert");	
			ins.update();
		
		}else if(teste.equals("Sair")) {
			this.dispose();
			System.exit(0);
		}
	}
	
	public void menuReturn() {
		cardLayout.show(cardPanel, "Main");
	}
}
