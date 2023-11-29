package ucs.CircuitRise.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import ucs.CircuitRise.controller.DataController;
import ucs.CircuitRise.exceptions.ExcecaoEspacoVazio;
import ucs.CircuitRise.exceptions.ExcecaoNotNumber;
import ucs.CircuitRise.model.FinalTable;
import ucs.CircuitRise.model.FinalTime;
import ucs.CircuitRise.model.Stage;
import ucs.CircuitRise.model.Pilot;
import javax.swing.ListSelectionModel;
import javax.swing.text.MaskFormatter;
import javax.swing.JTextField;
import javax.swing.JCheckBox;

public class Insertion extends JPanel implements ActionListener{

	private static final long serialVersionUID = 5686L;
	Insertion self = this;
	MainScreen ms;
	JPanel header = new JPanel();
	private JScrollPane rolagem1;
	private JScrollPane rolagem2;
	JList<Stage> stageList;
	JList<Pilot> pilotList;
	List<FinalTime> times = new ArrayList<>();
	JComboBox<FinalTable> cbSeason;
	DataController data = new DataController();
	JFormattedTextField tfTime;
	private JButton btnRegister;
	private JButton btnFinish;
	private JTextField tfGrid;
	JCheckBox bxBestLap;
	
	public Insertion(MainScreen menu) {
		this.ms = menu;
		setBounds(0, 0, 960, 580);
		setBackground(new Color(177, 178, 181));
		setLayout(null);

		String frase = "Inserção";
		Header h = new Header(frase);
		header = h.getHeader(this);
		add(header);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 87, 918, 482);
		panel.setLayout(null);
		add(panel);
		
		JLabel lblSeason = new JLabel("Temporada");
		lblSeason.setBounds(20, 11, 102, 14);
		lblSeason.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblSeason);
		
		JLabel lblStages = new JLabel("Etapas");
		lblStages.setBounds(20, 80, 65, 14);
		lblStages.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblStages);
		
		JLabel lblPilots = new JLabel("Pilotos");
		lblPilots.setBounds(244, 13, 76, 14);
		lblPilots.setFont(new Font("Arial", Font.BOLD, 16));
		panel.add(lblPilots);
		
		JLabel lblTime = new JLabel("Tempo final");
		lblTime.setBounds(513, 40, 86, 14);
		lblTime.setFont(new Font("Arial", Font.BOLD, 12));
		panel.add(lblTime);
		
		JLabel lblGrid = new JLabel("Grid");
		lblGrid.setBounds(513, 100, 46, 14);
		lblGrid.setFont(new Font("Arial", Font.BOLD, 12));
		panel.add(lblGrid);
		
		try {
			MaskFormatter formatter = new MaskFormatter("##:##:##.###");
			tfTime = new JFormattedTextField(formatter);
			tfTime.setFont(new Font("Arial", Font.PLAIN, 11));
			tfTime.setBounds(513, 60, 86, 20);
			tfTime.setValue("00:00:00.000");
			panel.add(tfTime);
			tfTime.setColumns(10);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        
		
		tfGrid = new JTextField();
		tfGrid.setBounds(513, 120, 86, 20);
		panel.add(tfGrid);
		tfGrid.setColumns(10);
		
		bxBestLap = new JCheckBox("Melhor volta?");
		bxBestLap.setFont(new Font("Arial", Font.PLAIN, 11));
		bxBestLap.setBounds(513, 160, 97, 23);
		bxBestLap.setBackground(new Color(177, 178, 181));
		panel.add(bxBestLap);
		
		
		cbSeason = new JComboBox<>();
		cbSeason.setBounds(20, 36, 139, 22);
		panel.setBackground(new Color(177, 178, 181));
		this.updateCombo();
		cbSeason.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FinalTable selected = (FinalTable) cbSeason.getSelectedItem();
				loadStages(selected);
				loadPilots(selected);
			}
		});
		panel.add(cbSeason);
		
		stageList = new JList<>();
		stageList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		stageList.setBackground(new Color(195, 196, 199));
		rolagem1 = new JScrollPane(stageList);
		rolagem1.setBackground(new Color(195, 196, 199));
		rolagem1.getViewport().setBackground(new Color(195, 196, 199));
		rolagem1.getVerticalScrollBar().setBackground(Color.GRAY);
		rolagem1.setBounds(20, 105, 139, 167);
		panel.add(rolagem1);
		
		pilotList = new JList<>();
		pilotList.setBackground(new Color(195, 196, 199));
		pilotList.setFont(new Font("Arial", Font.PLAIN, 11));
		rolagem2 = new JScrollPane(pilotList);
		rolagem2.setBackground(new Color(195, 196, 199));
		rolagem2.getViewport().setBackground(new Color(195, 196, 199));
		rolagem2.getVerticalScrollBar().setBackground(Color.GRAY);
		rolagem2.setBounds(244, 36, 154, 236);
		panel.add(rolagem2);
		
		btnRegister = new JButton("Registrar tempo");
		btnRegister.setFont(new Font("Arial", Font.PLAIN, 11));
		btnRegister.setBounds(500, 249, 117, 23);
		btnRegister.addActionListener(this);
		panel.add(btnRegister);
		
		btnFinish = new JButton("Finalizar");
		btnFinish.setFont(new Font("Arial", Font.PLAIN, 11));
		btnFinish.setBounds(700, 249, 89, 23);
		btnFinish.addActionListener(this);
		panel.add(btnFinish);
		
		loadStages((FinalTable) cbSeason.getSelectedItem());
		loadPilots((FinalTable) cbSeason.getSelectedItem());
		pilotList.setCellRenderer(new MarkedListCellRenderer());
		
		
	}

	public void actionPerformed(ActionEvent e) {
		String teste = "";
		teste = ((JButton) e.getSource()).getText();
		try {
			if(teste.equals("Registrar tempo")) {
				registerTime();
				int selectedIndex = pilotList.getSelectedIndex();
				MarkedListCellRenderer.markRowAsManipulated(selectedIndex);
				pilotList.repaint();
			}else if(teste.equals("Finalizar")) {
				DefaultListModel<Pilot> listModel = (DefaultListModel<Pilot>) pilotList.getModel();
				FinalTable year = (FinalTable) cbSeason.getSelectedItem();
				List<Pilot> pilots = new ArrayList<>();
				for(int i=0; i<listModel.size();i++) {
					pilots.add(listModel.getElementAt(i));
				}
				Stage stg = (Stage) stageList.getSelectedValue();
				Collections.sort(times);
				data.setPoints(stg);
				data.sumTeams(year);
				data.registerTimes(stg, pilots, times);
			}
			else if(teste.equals("Return")) {
			ms.menuReturn();
			}
		}catch (ExcecaoEspacoVazio | ExcecaoNotNumber e1) {
			JOptionPane.showMessageDialog(self, e1.getMessage());
		}
	}
	
	public void registerTime() throws ExcecaoEspacoVazio, ExcecaoNotNumber {
		Pilot p = (Pilot) pilotList.getSelectedValue();
		Stage stg = (Stage) stageList.getSelectedValue();
		FinalTime fTime = data.createFinalTime(stg, p, tfGrid.getText());
		String totalTime = tfTime.getText();
	    String[] timeSplit = totalTime.split("[:.]");

	    int horas = Integer.parseInt(timeSplit[0]);
	    int minutos = Integer.parseInt(timeSplit[1]);
	    int segundos = Integer.parseInt(timeSplit[2]);
	    int mili = Integer.parseInt(timeSplit[3]);
	    
	    fTime.setTime(horas, minutos, segundos, mili);
	    if(bxBestLap.isSelected()) {
	    	fTime.setBestLap();
	    }
	    p.addTime(fTime);
	    times.add(fTime);
	}
	
	private void updateCombo() {
		List<FinalTable> list = data.getSeasons();
		DefaultComboBoxModel<FinalTable> model = new DefaultComboBoxModel<>();
		for (FinalTable ft : list) {
		    model.addElement(ft);
		}

		cbSeason.setModel(model);
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
	public void loadPilots(FinalTable ft) {
		DefaultListModel<Pilot> listMp = new DefaultListModel<Pilot>();
		try {
			List<Pilot> listP = data.getSeasonPilots(ft);
			for(Pilot p : listP) {
				listMp.addElement(p);
			}
			pilotList.setModel(listMp);
		}catch (ExcecaoEspacoVazio e) {
		}
	}
	public void update() {
		FinalTable selected = (FinalTable) cbSeason.getSelectedItem();
		loadStages(selected);
		loadPilots(selected);
	}
}
