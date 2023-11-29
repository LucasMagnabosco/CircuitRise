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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import ucs.CircuitRise.controller.DataController;

public class SeasonTable extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1578L;
	
	JPanel header = new JPanel();
	MainScreen ms;
	DataController data = new DataController();
	
	public SeasonTable(MainScreen menu) {
		this.ms = menu;
		setBounds(0, 0, 960, 580);
		setBackground(new Color(177, 178, 181));
		setLayout(null);

		String frase = "Tabela geral";
		Header h = new Header(frase);
		header = h.getHeader(this);
		add(header);
		
	}

	public void actionPerformed(ActionEvent e) {
		String teste = "";
		teste = ((JButton) e.getSource()).getText();
		if(teste.equals("")) {
			
		}else if(teste.equals("Return")) {
			ms.menuReturn();
		}
	}
}
