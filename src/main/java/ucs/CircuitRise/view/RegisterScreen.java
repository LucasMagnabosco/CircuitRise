package ucs.CircuitRise.view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ucs.CircuitRise.model.Pilot;

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


public class RegisterScreen extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	JPanel panel = new JPanel();
	JPanel Pilot_form;
	JPanel Team_form;
	MainScreen ms;
	private JTextField tfName;
	private JTextField tfNum;
	
	public RegisterScreen(MainScreen menu) {
		this.ms = menu;
		setBounds(0, 0, 960, 540);
		setBackground(new Color(177, 178, 181));
		setLayout(null);
		
		
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setBackground(new Color(226, 36, 32));
		panel.setBounds(10, 11, 924, 65);
		add(panel);
		panel.setLayout(null);
		
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
		panel.add(btnReturn);
		
		JLabel lblNewLabel = new JLabel("Cadastros");
		lblNewLabel.setBounds(387, 11, 126, 31);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 26));
		panel.add(lblNewLabel);
		String[] opcoes = {"Pilotos", "Equipes"};
		JComboBox cbEntity = new JComboBox<Object>(opcoes);
		cbEntity.setName("cbEntities");
		cbEntity.setFont(new Font("Arial", Font.PLAIN, 11));
		cbEntity.addActionListener(this);
		
		cbEntity.setBackground(new Color(177, 178, 181));
		cbEntity.setBounds(784, 87, 150, 22);
		add(cbEntity);
		
		//Formulario para cadastrar pilotos
		Pilot_form = new JPanel();
		Pilot_form.setBackground(new Color(177, 178, 181));
		Pilot_form.setBounds(61, 118, 874, 91);
		Pilot_form.setLayout(null);
		this.add(Pilot_form);
		
		JLabel lblName = new JLabel("Nome");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Arial", Font.PLAIN, 11));
		lblName.setBounds(293, 8, 27, 14);
		Pilot_form.add(lblName);
		
		tfName = new JTextField();
		tfName.setFont(new Font("Arial", Font.PLAIN, 11));
		tfName.setBounds(334, 5, 138, 20);
		Pilot_form.add(tfName);
		tfName.setColumns(10);
		
		tfNum = new JTextField();
		tfNum.setFont(new Font("Arial", Font.PLAIN, 11));
		tfNum.setBounds(334, 36, 36, 20);
		Pilot_form.add(tfNum);
		tfNum.setColumns(10);
		
		JLabel lblNum = new JLabel("Número");
		lblNum.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNum.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNum.setBounds(278, 38, 42, 14);
		Pilot_form.add(lblNum);
		
		JButton btnRegister = new JButton("Cadastrar");
		btnRegister.setFont(new Font("Arial", Font.PLAIN, 11));
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegister.setBounds(620, 57, 100, 23);
		Pilot_form.add(btnRegister);
		
		
		//Formulario para cadastrar equipes
		
	}
	
	public void actionPerformed(ActionEvent e) {
		String teste = "";
		int option;
		if(e.getSource() instanceof JButton) {
			teste = ((JButton) e.getSource()).getText();
			if(((JButton) e.getSource()).getName().equals("btnReturn")) {
				ms.menuReturn();
			}
		}else if(e.getSource() instanceof JComboBox) {
			option = ((JComboBox) e.getSource()).getSelectedIndex();
			if(option == 0) {
				teste = "Pilotos";
			}
			else if(option == 1){
				teste = "Equipes";
			}
			
		}
		//try {
			if(teste.equals("Pilotos")) {
				Pilot_form.setVisible(true);
			}
			else if(teste.equals("Equipes")) {
				Pilot_form.setVisible(false);
			}
		//}catch{
			
		//}
	}
	

}
