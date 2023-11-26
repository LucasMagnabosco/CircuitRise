package ucs.CircuitRise.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import ucs.CircuitRise.controller.DataController;
import ucs.CircuitRise.exceptions.ExcecaoEquipeCheia;
import ucs.CircuitRise.exceptions.ExcecaoEspacoVazio;
import ucs.CircuitRise.exceptions.ExcecaoNotNumber;
import ucs.CircuitRise.exceptions.ExcecaoObjetoJaCadastrado;


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
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



public class RegisterScreen extends JPanel implements ActionListener{

	private static final long serialVersionUID = 789L;

	JPanel header = new JPanel();
	JPanel Pilot_form;
	JPanel Team_form;
	MainScreen ms;
	private JTextField tfPilotName;
	private JTextField tfTeamName;
	private JTextField tfNum;
	private JTable table_pilot;
	private JTable table_team;
	private RegisterScreen self = this;
	private JScrollPane rolagem1, rolagem2;
	
	DataController data = new DataController();
	
	public RegisterScreen(MainScreen menu) {
		this.ms = menu;
		setBounds(0, 0, 960, 580);
		setBackground(new Color(177, 178, 181));
		setLayout(null);
		
		
		header.setBorder(new EmptyBorder(0, 0, 0, 0));
		header.setBackground(new Color(226, 36, 32));
		header.setBounds(10, 11, 924, 65);
		add(header);
		header.setLayout(null);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\Leonel\\Documents\\GitHub\\CircuitRise\\img\\return_icon.png");
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
		JButton btnReturn = new JButton("");
		btnReturn.setName("btnReturn");
		btnReturn.addActionListener(this);
		btnReturn.setBackground(new Color(226, 36, 32));
		btnReturn.setForeground(new Color(226, 36, 32));
		btnReturn.setIcon(new ImageIcon(newImg));
		btnReturn.setBounds(10, 14, 38, 40);
		btnReturn.setBorderPainted(false);
		btnReturn.setFocusPainted(false);
		btnReturn.setContentAreaFilled(false);
		btnReturn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		header.add(btnReturn);
		
		JLabel lblNewLabel = new JLabel("Cadastros");
		lblNewLabel.setBounds(387, 11, 126, 31);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 26));
		header.add(lblNewLabel);
		String[] opcoes = {"Pilotos", "Equipes"};
		JComboBox cbEntity = new JComboBox<Object>(opcoes);
		cbEntity.setName("cbEntities");
		cbEntity.setFont(new Font("Arial", Font.PLAIN, 11));
		cbEntity.addActionListener(this);
		
		cbEntity.setBackground(new Color(177, 178, 181));
		cbEntity.setBounds(784, 87, 150, 22);
		add(cbEntity);
		
		Pilot_form = this.pilotForm();
		Team_form = this.teamForm();
		add(Pilot_form);
		add(Team_form);

		JLabel lblTableName = new JLabel("Pilotos");
		lblTableName.setFont(new Font("Arial", Font.BOLD, 16));
		lblTableName.setBounds(201, 247, 63, 14);
		add(lblTableName);
		
		JLabel lblTableName1 = new JLabel("Equipes");
		lblTableName1.setFont(new Font("Arial", Font.BOLD, 16));
		lblTableName1.setBounds(453, 246, 63, 16);
		add(lblTableName1);
		
		Object[][] rows = data.pilotsToArray();
		String[] columns = {"Pilotos", "Equipe"};
		table_pilot = new JTable();
		table_pilot.setModel(new DefaultTableModel(rows, columns));
		table_pilot.getColumnModel().getColumn(0).setPreferredWidth(109);
		table_pilot.setShowVerticalLines(false);
		table_pilot.setFont(new Font("Arial", Font.PLAIN, 11));
		table_pilot.setBounds(134, 294, 209, 232);
		table_pilot.setBackground(new Color(195, 196, 199));
		table_pilot.getTableHeader().setBackground(new Color(195, 196, 199));
		table_pilot.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		rolagem1 = new JScrollPane(table_pilot);
		rolagem1.setBackground(new Color(195, 196, 199));
		rolagem1.getViewport().setBackground(new Color(195, 196, 199));
		rolagem1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		rolagem1.setBounds(125, 272, 209, 232);
		rolagem1.getVerticalScrollBar().setBackground(Color.GRAY);
		add(rolagem1);
		
		Object[][] rows2 = data.teamsToArray();
		String[] columns2 = {"Equipes"};
		table_team = new JTable();
		table_team.setModel(new DefaultTableModel(rows2, columns2));
		table_team.setShowVerticalLines(false);
		table_team.setBounds(382, 273, 209, 232);
		table_team.setBackground(new Color(195, 196, 199));
		table_team.setFont(new Font("Arial", Font.PLAIN, 11));
		table_team.getTableHeader().setBackground(new Color(195, 196, 199));
		table_team.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		rolagem2 = new JScrollPane(table_team);
		rolagem2.getViewport().setBackground(new Color(195, 196, 199));
		rolagem2.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		rolagem2.setBounds(379, 272, 209, 232);
		add(rolagem2);
		
		JButton btnDelete = new JButton("Remover");
		btnDelete.setBounds(682, 332, 100, 23);
		btnDelete.addActionListener(this);
		add(btnDelete);
		
		JButton btnRelate = new JButton("Relacionar");
		btnRelate.setBounds(682, 374, 100, 23);
		btnRelate.addActionListener(this);
		add(btnRelate);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		String teste = "";
		int option;
		if(e.getSource() instanceof JButton) {
			teste = ((JButton) e.getSource()).getText();
			if(((JButton) e.getSource()).getName()!= null && ((JButton) e.getSource()).getName().equals("btnReturn")) {
				ms.menuReturn();
			}
		}else if(e.getSource() instanceof JComboBox) {
			teste = (String) ((JComboBox) e.getSource()).getSelectedItem();
		}
		try {
			if(teste.equals("Pilotos")) {
				Team_form.setVisible(false);
				Pilot_form.setVisible(true);
			}
			else if(teste.equals("Equipes")) {
				Pilot_form.setVisible(false);
				Team_form.setVisible(true);
			}
			else if(teste.equals("Remover")){
				int rowp = table_pilot.getSelectedRow();
				int rowt = table_team.getSelectedRow();
				if(rowp != -1 && rowt != -1) {
					String pilot = table_pilot.getValueAt(rowp, 0).toString();
					String team = table_team.getValueAt(rowt, 0).toString();
					data.deleteRelation(team, pilot);
					updatePilot();
				}
				else if(rowp != -1 && rowt == -1) {
					String pilot = table_pilot.getValueAt(rowp, 0).toString();
					data.deletePilot(pilot);
					updatePilot();
				}
				else if(rowp == -1 && rowt != -1) {
					String team = table_team.getValueAt(rowt, 0).toString();
					data.deleteTeam(team);
					updateTeam();
				}
				else {
					JOptionPane.showMessageDialog(self, "Pelo menos uma das opções não foram escolhidas");
				}
			}
			else if(teste.equals("Relacionar")) {
				int row1 = table_pilot.getSelectedRow();
				int row2 = table_team.getSelectedRow();
				if(row1 == -1 || row2 == -1) {
					JOptionPane.showMessageDialog(self, "Pelo menos uma das opções não foram escolhidas");
				}else {
					String pilot = table_pilot.getValueAt(row1, 0).toString();
					String team = table_team.getValueAt(row2, 0).toString();
					data.relatePilot(team, pilot);
					updatePilot();
				}
			}
		}
		catch (ExcecaoEquipeCheia e1){
			JOptionPane.showMessageDialog(self, e1.getMessage());
		}
		
	}
	
	//Formulario para cadastrar pilotos
	public JPanel pilotForm() {
		JPanel pilot_panel = new JPanel();
		pilot_panel.setBackground(new Color(177, 178, 181));
		pilot_panel.setBounds(61, 118, 874, 91);
		pilot_panel.setLayout(null);
		
		JLabel lblName = new JLabel("Nome");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Arial", Font.PLAIN, 11));
		lblName.setBounds(278, 7, 27, 14);
		pilot_panel.add(lblName);
		
		tfPilotName = new JTextField();
		tfPilotName.setFont(new Font("Arial", Font.PLAIN, 11));
		tfPilotName.setBounds(334, 5, 138, 20);
		pilot_panel.add(tfPilotName);
		tfPilotName.setColumns(10);
		
		tfNum = new JTextField();
		tfNum.setFont(new Font("Arial", Font.PLAIN, 11));
		tfNum.setBounds(334, 36, 36, 20);
		pilot_panel.add(tfNum);
		tfNum.setColumns(10);
		
		JLabel lblNum = new JLabel("Número");
		lblNum.setHorizontalAlignment(SwingConstants.LEFT);
		lblNum.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNum.setBounds(278, 38, 42, 14);
		pilot_panel.add(lblNum);
		
		JButton btnPilotRegister = new JButton("Cadastrar");
		btnPilotRegister.setFont(new Font("Arial", Font.PLAIN, 11));
		btnPilotRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					data.registerPilot(tfPilotName.getText(), tfNum.getText());
					updatePilot();
					JOptionPane.showMessageDialog(self, "Piloto cadastrado com sucesso");
				} catch (ExcecaoEspacoVazio e1) {
					JOptionPane.showMessageDialog(self, e1.getMessage());
				} catch (ExcecaoNotNumber e1) {
					JOptionPane.showMessageDialog(self, e1.getMessage());
				} catch (ExcecaoObjetoJaCadastrado e1) {
					JOptionPane.showMessageDialog(self, e1.getMessage());
				}
			}
		});
		btnPilotRegister.setBounds(620, 57, 100, 23);
		pilot_panel.add(btnPilotRegister);
		
		return pilot_panel;
	}

	public JPanel teamForm() {
		JPanel team_panel = new JPanel();
		team_panel.setBackground(new Color(177, 178, 181));
		team_panel.setBounds(61, 118, 874, 91);
		team_panel.setLayout(null);
		
		JLabel lblName = new JLabel("Nome");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Arial", Font.PLAIN, 11));
		lblName.setBounds(261, 7, 27, 14);
		team_panel.add(lblName);
		
		tfTeamName = new JTextField();
		tfTeamName.setFont(new Font("Arial", Font.PLAIN, 11));
		tfTeamName.setBounds(334, 5, 138, 20);
		team_panel.add(tfTeamName);
		tfTeamName.setColumns(10);
		
	
		
		JButton btnTeamRegister = new JButton("Cadastrar");
		btnTeamRegister.setFont(new Font("Arial", Font.PLAIN, 11));
		btnTeamRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					data.registerTeam(tfTeamName.getText());
					updateTeam();
				} catch (ExcecaoEspacoVazio | ExcecaoObjetoJaCadastrado | ExcecaoNotNumber e1) {
					JOptionPane.showMessageDialog(self, e1.getMessage());
				}
			}
		});
		btnTeamRegister.setBounds(620, 57, 100, 23);
		team_panel.add(btnTeamRegister);
		
		return team_panel;
	}
	
	public void updateTeam() {
		DefaultTableModel model = (DefaultTableModel) table_team.getModel();
		String[] columns = {"Equipes"};
		model.setDataVector(data.teamsToArray(), columns);
		model.fireTableDataChanged();
	}
	public void updatePilot() {
		DefaultTableModel model = (DefaultTableModel) table_pilot.getModel();
		String[] columns = {"Pilotos", "Equipe"};
		model.setDataVector(data.pilotsToArray(), columns);
		model.fireTableDataChanged();
	}
}
