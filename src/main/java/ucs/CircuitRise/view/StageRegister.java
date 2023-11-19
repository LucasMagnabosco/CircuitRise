package ucs.CircuitRise.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ucs.CircuitRise.controller.DataController;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;

public class StageRegister extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	JPanel header = new JPanel();
	MainScreen ms;
	private JTextField tfYear;
	private JScrollPane rolagem1;
	
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
		
		JLabel lblNewLabel = new JLabel("Etapas");
		lblNewLabel.setBounds(387, 11, 126, 31);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 26));
		header.add(lblNewLabel);
		
		ImageIcon icon = new ImageIcon("C:\\Users\\lmagn\\OneDrive\\Área de Trabalho\\Projetos Java\\CircuitRise\\img\\return_icon.png");
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
		add(cbEntity);
		
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
		
		JList<?> teamList = new JList<Object>();
		teamList.setBounds(26, 47, 195, 257);
		panel.add(teamList);
		
		JList<?> pilotList = new JList<Object>();
		pilotList.setBounds(257, 47, 195, 257);
		panel.add(pilotList);
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.setBounds(643, 281, 89, 23);
		panel.add(btnNewButton);
		
		JLabel lblTeam = new JLabel("Equipes");
		lblTeam.setBounds(92, 13, 63, 14);
		lblTeam.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblTeam);
		
		JLabel lblPilot = new JLabel("Pilotos");
		lblPilot.setBounds(325, 13, 63, 14);
		lblPilot.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblPilot);
	
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
		
	}
	
	public void stagePanel() {
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
	}
	
	public void seasonPanel() {
		
		
	}
}
