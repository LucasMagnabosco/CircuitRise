package ucs.CircuitRise.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ucs.CircuitRise.controller.DataController;
import ucs.CircuitRise.exceptions.ExcecaoEspacoVazio;
import ucs.CircuitRise.exceptions.ExcecaoNotNumber;
import ucs.CircuitRise.exceptions.ExcecaoObjetoJaCadastrado;

import ucs.CircuitRise.controller.DataController;
import ucs.CircuitRise.exceptions.ExcecaoEspacoVazio;
import ucs.CircuitRise.exceptions.ExcecaoNotNumber;
import ucs.CircuitRise.exceptions.ExcecaoObjetoJaCadastrado;
import ucs.CircuitRise.model.Pilot;
import ucs.CircuitRise.model.Team;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StageRegister extends JPanel implements ActionListener{

	private static final long serialVersionUID = 367L;

	JPanel header = new JPanel();
	private JTable table_stage;
	MainScreen ms;
	JPanel stage_scrn, season_scrn;
	private JTextField tfYear;
	private JScrollPane rolagem1;
	private JScrollPane rolagem2;
	private StageRegister self = this;
	JList<Pilot> pilotList;
	JList<Team> teamList;
	
	DataController data = new DataController();
	
	public StageRegister(MainScreen menu) {
		
		
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
		
		String[] opcoes = {"Temporadas", "Etapas"};
		JComboBox<Object> cbEntity = new JComboBox<Object>(opcoes);
		cbEntity.setName("cbEntities");
		cbEntity.setFont(new Font("Arial", Font.PLAIN, 11));
		cbEntity.addActionListener(this);
		
		cbEntity.setBackground(new Color(177, 178, 181));
		cbEntity.setBounds(784, 87, 150, 22);
		cbEntity.setBackground(new Color(177, 178, 181));
		add(cbEntity);
		
		season_scrn = this.seasonPanel();
		stage_scrn = this.stagePanel();
		stage_scrn.setVisible(false);
		add(season_scrn);
		add(stage_scrn);
		
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String teste = "";
		int option;
		if(e.getSource() instanceof JButton) {
			teste = ((JButton) e.getSource()).getText();
			if(((JButton) e.getSource()).getName()!= null && ((JButton) e.getSource()).getName().equals("btnReturn")) {
				ms.menuReturn();
			}
		}
		else if(e.getSource() instanceof JComboBox) {
			teste = (String) ((JComboBox<?>) e.getSource()).getSelectedItem();	
		}
		
//		steatry {
			if(teste.equals("Temporadas")) {
				stage_scrn.setVisible(false);
				season_scrn.setVisible(true);
				
			}else if(teste.equals("Etapas")) {
				season_scrn.setVisible(false);
				stage_scrn.setVisible(true);
			}
//			else {
//				JOptionPane.showMessageDialog(self, "Pelo menos uma das opções não foram escolhidas");
//			}
//		}catch() {
//			
//		}
		
	}
	
	public void updateStage() {
		DefaultTableModel model = (DefaultTableModel) table_stage.getModel();
		String[] columns = {"Equipes"};
		model.setDataVector(data.stagesToArray(), columns);
		model.fireTableDataChanged();
	}

	public JPanel stagePanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(177, 178, 181));
		panel.setBounds(20, 87, 754, 375);
		add(panel);
		panel.setLayout(null);
		
		JComboBox<?> cbSeason = new JComboBox<Object>(data.seasonsToArray());
		cbSeason.setBounds(20, 43, 139, 22);
		panel.add(cbSeason);
		
		JLabel lblSeason = new JLabel("Temporada");
		lblSeason.setBounds(20, 11, 102, 14);
		lblSeason.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblSeason);
		
		JLabel lblStages = new JLabel("Etapas");
		lblStages.setBounds(20, 88, 65, 14);
		lblStages.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblStages);
		
		JList<?> list = new JList<Object>();
		list.setBackground(new Color(195, 196, 199));
		rolagem1 = new JScrollPane(list);
		rolagem1.setBackground(new Color(195, 196, 199));
		rolagem1.getViewport().setBackground(new Color(195, 196, 199));
		rolagem1.getVerticalScrollBar().setBackground(Color.GRAY);
		rolagem1.setBounds(20, 132, 139, 167);
		panel.add(rolagem1);
		return panel;
	}
	
	public JPanel seasonPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(177, 178, 181));
		panel.setBounds(20, 87, 754, 375);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblSeason = new JLabel("Temporada");
		lblSeason.setBounds(490, 11, 87, 19);
		lblSeason.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblSeason);
		
		tfYear = new JTextField();
		tfYear.setBounds(490, 45, 86, 20);
		tfYear.setColumns(10);
		panel.add(tfYear);
		
		
		List<Team> teams = data.getTeams();
		DefaultListModel<Team> listModel = new DefaultListModel<Team>();
		for(Team t : teams) {
			listModel.addElement(t);
		}
		
		teamList = new JList<>(listModel);
		teamList.setBackground(new Color(195, 196, 199));
		rolagem1 = new JScrollPane(teamList);
		rolagem1.setBackground(new Color(195, 196, 199));
		rolagem1.getViewport().setBackground(new Color(195, 196, 199));
		rolagem1.getVerticalScrollBar().setBackground(Color.GRAY);
		rolagem1.setBounds(26, 47, 195, 257);
		panel.add(rolagem1);
		
		List<Pilot> pilots = data.getPilots();
		DefaultListModel<Pilot> listModel2 = new DefaultListModel<Pilot>();
		for(Pilot p : pilots) {
			listModel2.addElement(p);
		}
		pilotList = new JList<>(listModel2);
		pilotList.setBackground(new Color(195, 196, 199));
		rolagem2 = new JScrollPane(pilotList);
		rolagem2.setBackground(new Color(195, 196, 199));
		rolagem2.getViewport().setBackground(new Color(195, 196, 199));
		rolagem2.getVerticalScrollBar().setBackground(Color.GRAY);
		rolagem2.setBounds(257, 47, 195, 257);
		panel.add(rolagem2);
		
		JLabel lblTeam = new JLabel("Equipes");
		lblTeam.setBounds(92, 13, 63, 14);
		lblTeam.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblTeam);
		
		JLabel lblPilot = new JLabel("Pilotos");
		lblPilot.setBounds(325, 13, 63, 14);
		lblPilot.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblPilot);
		
		JButton btnRegister = new JButton("Cadastrar");
		btnRegister.setBounds(635, 281, 97, 23);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Pilot> selectedP = pilotList.getSelectedValuesList();
				Set<Pilot> pilots = new HashSet<Pilot>(selectedP);
				List<Team> selectedT = teamList.getSelectedValuesList();
				Set<Team> teams = new HashSet<Team>(selectedT);
				try {
					data.registerSeason(tfYear.getText(), pilots, teams);
				} catch (ExcecaoEspacoVazio | ExcecaoNotNumber | ExcecaoObjetoJaCadastrado e1) {
					JOptionPane.showMessageDialog(self, e1.getMessage());
				}
				
			}
		});
	
		panel.add(btnRegister);
		return panel;
	
	}
}
