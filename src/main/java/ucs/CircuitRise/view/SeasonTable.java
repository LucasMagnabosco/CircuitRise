package ucs.CircuitRise.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ucs.CircuitRise.controller.DataController;
import ucs.CircuitRise.controller.PilotTableModel;
import ucs.CircuitRise.controller.TeamTableModel;
import ucs.CircuitRise.model.FinalTable;

import javax.swing.JTable;

public class SeasonTable extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1578L;
	
	JPanel header = new JPanel();
	MainScreen ms;
	DataController data = new DataController();
	private JTable tableP, tableT;
	private JScrollPane scroll1, scroll2;
	
	public SeasonTable(MainScreen menu) {
		this.ms = menu;
		setBounds(0, 0, 960, 580);
		setBackground(new Color(177, 178, 181));
		setLayout(null);

		String frase = "Tabela geral";
		Header h = new Header(frase);
		header = h.getHeader(this);
		add(header);
		
		String[] opcoes = {"Pilotos", "Construtores"};
		JComboBox<Object> cbEntity = new JComboBox<Object>(opcoes);
		cbEntity.setBounds(784, 87, 150, 22);
		cbEntity.setFont(new Font("Arial", Font.PLAIN, 11));
		cbEntity.addActionListener(this);
		add(cbEntity);
		
		JPanel panel = new JPanel();
		panel.setBounds(16, 87, 918, 482);
		panel.setBackground(new Color(177, 178, 181));
		panel.setLayout(null);
		add(panel);
		
		FinalTable ft = data.getSeason(2023);
		
		PilotTableModel pModel = new PilotTableModel(ft.getPilots());
		TeamTableModel tModel = new TeamTableModel(ft.getTeams());
				
		
		tableP = new JTable(pModel);
		tableP.setBackground(new Color(225, 225, 225));
		tableP.getTableHeader().setBackground(new Color(225, 225, 225));
		scroll1 = new JScrollPane(tableP);
		scroll1.setBounds(184, 25, 522, 352);
		scroll1.getViewport().setBackground(new Color(225, 225, 225));
		panel.add(scroll1);
		
		tableT = new JTable(tModel);
		tableT.setBounds(184, 25, 522, 352);
		tableT.setBackground(new Color(225, 225, 225));
		tableT.getTableHeader().setBackground(new Color(225, 225, 225));
		scroll2 = new JScrollPane(tableT);
		scroll2.setBounds(184, 25, 522, 352);
		scroll2.getViewport().setBackground(new Color(225, 225, 225));
		panel.add(scroll2);
		scroll2.setVisible(false);
		
	}

	public void actionPerformed(ActionEvent e) {
		String teste = "";
		if(e.getSource() instanceof JButton) {
			teste = ((JButton) e.getSource()).getText();
		}
		else if(e.getSource() instanceof JComboBox) {
			teste = (String) ((JComboBox<?>) e.getSource()).getSelectedItem();	
		}
		if(teste.equals("Pilotos")) {
			scroll1.setVisible(true);
			scroll2.setVisible(false);
		}else if(teste.equals("Construtores")) {
			scroll1.setVisible(false);
			scroll2.setVisible(true);
		}else if(teste.equals("")) {
			
		}else if(teste.equals("")) {
			
		}
		else if(teste.equals("Return")) {
			ms.menuReturn();
		}
	}
}
