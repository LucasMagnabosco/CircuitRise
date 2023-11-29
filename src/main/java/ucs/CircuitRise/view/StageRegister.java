package ucs.CircuitRise.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ucs.CircuitRise.controller.DataController;
import ucs.CircuitRise.exceptions.ExcecaoEspacoVazio;
import ucs.CircuitRise.exceptions.ExcecaoNotNumber;
import ucs.CircuitRise.exceptions.ExcecaoObjetoJaCadastrado;
import ucs.CircuitRise.model.FinalTable;
import ucs.CircuitRise.model.Pilot;
import ucs.CircuitRise.model.Stage;
import ucs.CircuitRise.model.Team;


public class StageRegister extends JPanel implements ActionListener{

	private static final long serialVersionUID = 367L;

	JPanel header = new JPanel();
	MainScreen ms;
	JPanel stage_scrn, season_scrn;
	private JTextField tfYear;
	private JScrollPane rolagem1;
	private JScrollPane rolagem2;
	private StageRegister self = this;
	private JComboBox<FinalTable> cbSeason;
	JList<Pilot> pilotList;
	JList<Team> teamList;
	JList<Stage> stageList;
	
	DataController data = new DataController();
	private JTextField tfName;
	private JTextField tfDate;
	private JTextField tfTime;
	private JTextField tfLaps;
	private JTextField tfLength;
	private JTextField tfSeq;
	
	
	public StageRegister(MainScreen menu) {
		
		
		this.ms = menu;
		setBounds(0, 0, 960, 580);
		setBackground(new Color(177, 178, 181));
		setLayout(null);
		
		String frase = "Eventos";
		Header h = new Header(frase);
		header = h.getHeader(this);
		add(header);

		
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
		add(stage_scrn);
		add(season_scrn);
		
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String teste = "";
		if(e.getSource() instanceof JButton) {
			teste = ((JButton) e.getSource()).getText();
		}
		else if(e.getSource() instanceof JComboBox) {
			teste = (String) ((JComboBox<?>) e.getSource()).getSelectedItem();	
		}
		
		try {
			if(teste.equals("Temporadas")) {
				stage_scrn.setVisible(false);
				season_scrn.setVisible(true);
				
			}else if(teste.equals("Etapas")) {
				season_scrn.setVisible(false);
				stage_scrn.setVisible(true);
			}else if(teste.equals("Cadastrar")) {
				List<Pilot> selectedP = pilotList.getSelectedValuesList();
				Set<Pilot> pilots = new HashSet<Pilot>(selectedP);
				List<Team> selectedT = teamList.getSelectedValuesList();
				Set<Team> teams = new HashSet<Team>(selectedT);
				data.registerSeason(tfYear.getText(), pilots, teams);
				updateCombo();
				JOptionPane.showMessageDialog(self, "Temporada registrada com sucesso");
			}else if(teste.equals("Registrar")) {
				FinalTable ft = (FinalTable) cbSeason.getSelectedItem();
				data.registerStage(ft, tfName.getText(), tfDate.getText(), tfTime.getText(), tfLaps.getText(), tfLength.getText(), tfSeq.getText());
				loadStages(ft);
				JOptionPane.showMessageDialog(self, "Etapa registrada com sucesso");
			}else if(teste.equals("Deletar")) {
				data.deleteSeason(tfYear.getText());
				updateCombo();
				JOptionPane.showMessageDialog(self, "Temporada deletada com sucesso");
			}else if(teste.equals("Deletar etapa")) {
				FinalTable ft = (FinalTable) cbSeason.getSelectedItem();
				List<Stage> stgs = stageList.getSelectedValuesList();
				data.deleteStage(ft, stgs);
				loadStages(ft);
				JOptionPane.showMessageDialog(self, "Etapa deletada com sucesso");
			}else if(teste.equals("Return")) {
				ms.menuReturn();
			}
		}catch(ExcecaoNotNumber | ExcecaoEspacoVazio | ExcecaoObjetoJaCadastrado e1) {
			JOptionPane.showMessageDialog(self, e1.getMessage());
		}
		
	}
	
	public JPanel stagePanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(177, 178, 181));
		panel.setBounds(20, 87, 754, 375);
		add(panel);
		panel.setLayout(null);
		
		cbSeason = new JComboBox<>();
		cbSeason.setBounds(20, 36, 139, 22);
		this.updateCombo();
		cbSeason.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FinalTable selected = (FinalTable) cbSeason.getSelectedItem();
				loadStages(selected);
			}
		});
		panel.add(cbSeason);
		
		JLabel lblSeason = new JLabel("Temporada");
		lblSeason.setBounds(20, 11, 102, 14);
		lblSeason.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblSeason);
		
		JLabel lblStages = new JLabel("Etapas");
		lblStages.setBounds(20, 80, 65, 14);
		lblStages.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblStages);
		
		stageList = new JList<>();
		loadStages((FinalTable) cbSeason.getSelectedItem());
		stageList.setBackground(new Color(195, 196, 199));
		rolagem1 = new JScrollPane(stageList);
		rolagem1.setBackground(new Color(195, 196, 199));
		rolagem1.getViewport().setBackground(new Color(195, 196, 199));
		rolagem1.getVerticalScrollBar().setBackground(Color.GRAY);
		rolagem1.setBounds(20, 105, 139, 167);
		panel.add(rolagem1);
		
		JButton btnDeleteStage = new JButton("Deletar etapa");
		btnDeleteStage.setFont(new Font("Arial", Font.BOLD, 12));
		btnDeleteStage.setBounds(33, 283, 116, 23);
		btnDeleteStage.addActionListener(this);
		panel.add(btnDeleteStage);
		
		JLabel lblname = new JLabel("Nome");
		lblname.setFont(new Font("Arial", Font.PLAIN, 12));
		lblname.setBounds(235, 101, 46, 14);
		panel.add(lblname);
		
		JLabel lblDate = new JLabel("Data");
		lblDate.setFont(new Font("Arial", Font.PLAIN, 12));
		lblDate.setBounds(235, 146, 65, 14);
		panel.add(lblDate);
		
		JLabel lblTime = new JLabel("Horario");
		lblTime.setFont(new Font("Arial", Font.PLAIN, 12));
		lblTime.setBounds(235, 194, 46, 14);
		panel.add(lblTime);
		
		JLabel lblLaps = new JLabel("Número de voltas");
		lblLaps.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLaps.setBounds(446, 101, 124, 14);
		panel.add(lblLaps);
		
		JLabel lblLength = new JLabel("Comprimento de volta (m)");
		lblLength.setFont(new Font("Arial", Font.PLAIN, 12));
		lblLength.setBounds(446, 146, 150, 14);
		panel.add(lblLength);
		
		JLabel lblOrder = new JLabel("Ordem");
		lblOrder.setFont(new Font("Arial", Font.PLAIN, 12));
		lblOrder.setBounds(446, 194, 46, 14);
		panel.add(lblOrder);
		
		tfName = new JTextField();
		tfName.setBounds(235, 118, 144, 20);
		tfName.setColumns(10);
		panel.add(tfName);
		
		tfDate = new JTextField();
		tfDate.setBounds(235, 163, 65, 20);
		tfDate.setColumns(10);
		panel.add(tfDate);
		
		tfTime = new JTextField();
		tfTime.setBounds(235, 211, 65, 20);
		tfTime.setColumns(10);
		panel.add(tfTime);
		
		tfLaps = new JTextField();
		tfLaps.setBounds(446, 118, 46, 20);
		tfLaps.setColumns(10);
		panel.add(tfLaps);
		
		tfLength = new JTextField();
		tfLength.setBounds(446, 163, 46, 20);
		tfLength.setColumns(10);
		panel.add(tfLength);
		
		tfSeq = new JTextField();
		tfSeq.setBounds(446, 211, 46, 20);
		tfSeq.setColumns(10);
		panel.add(tfSeq);
		
		JButton btnRegister = new JButton("Registrar");
		btnRegister.setFont(new Font("Arial", Font.BOLD, 12));
		btnRegister.setBounds(601, 278, 124, 33);
		panel.add(btnRegister);
		btnRegister.addActionListener(this);

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
		
		
		teamList = new JList<>();
		teamList.setFont(new Font("Arial", Font.PLAIN, 11));
		teamList.setBackground(new Color(195, 196, 199));
		rolagem1 = new JScrollPane(teamList);
		rolagem1.setBackground(new Color(195, 196, 199));
		rolagem1.getViewport().setBackground(new Color(195, 196, 199));
		rolagem1.getVerticalScrollBar().setBackground(Color.GRAY);
		rolagem1.setBounds(26, 47, 195, 257);
		panel.add(rolagem1);
		
		pilotList = new JList<>();
		pilotList.setBackground(new Color(195, 196, 199));
		pilotList.setFont(new Font("Arial", Font.PLAIN, 11));
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
		btnRegister.addActionListener(this);
		panel.add(btnRegister);
				
		JButton btnDeleteSeason = new JButton("Deletar");
		btnDeleteSeason.setBounds(635, 247, 97, 23);
		panel.add(btnDeleteSeason);
		btnDeleteSeason.addActionListener(this);
		return panel;
	}

	private void updateCombo() {
		List<FinalTable> list = data.getSeasons();
		DefaultComboBoxModel<FinalTable> model = new DefaultComboBoxModel<>();
		for (FinalTable ft : list) {
		    model.addElement(ft);
		}

		cbSeason.setModel(model);
	}
	public void updateLists() {
		DefaultListModel<Pilot> listMp = new DefaultListModel<Pilot>();
		DefaultListModel<Team> listMt = new DefaultListModel<Team>();
		List<Pilot> listP = data.getPilots();
		List<Team> listT = data.getTeams();
		for(Pilot p : listP) {
			listMp.addElement(p);
		}
		for(Team t : listT) {
			listMt.addElement(t);
		}
		pilotList.setModel(listMp);
		teamList.setModel(listMt);
	}
	public void loadStages(FinalTable ft) {
		DefaultListModel<Stage> listM = new DefaultListModel<Stage>();
		try {
			List<Stage> list = data.getStages(ft);
			Collections.sort(list);
			for(Stage stg : list) {
				listM.addElement(stg);
			}
			stageList.setModel(listM);
		} catch (ExcecaoEspacoVazio e) {
		}
		
	}
}
