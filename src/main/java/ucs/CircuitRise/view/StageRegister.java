package ucs.CircuitRise.view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ucs.CircuitRise.controller.DataController;
import ucs.CircuitRise.exceptions.ExcecaoEspacoVazio;
import ucs.CircuitRise.exceptions.ExcecaoNotNumber;
import ucs.CircuitRise.exceptions.ExcecaoObjetoJaCadastrado;

public class StageRegister extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	JPanel header = new JPanel();
	private JTextField tfStageName;
	private JTextField tfNum;
	private JTable table_stage;
	private StageRegister self = this;
	MainScreen ms;
	
	DataController data = new DataController();
	
	public StageRegister(MainScreen menu) {
		JPanel stage_panel = new JPanel();
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
		
		JLabel lblNewLabel1 = new JLabel("Cadastros");
		lblNewLabel1.setBounds(387, 11, 126, 31);
		lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel1.setFont(new Font("Arial", Font.BOLD, 26));
		header.add(lblNewLabel1);
		String[] opcoes = {"Stage"};
		JComboBox cbEntity = new JComboBox<Object>(opcoes);
		cbEntity.setName("cbEntities");
		cbEntity.setFont(new Font("Arial", Font.PLAIN, 11));
		cbEntity.addActionListener(this);
		
		cbEntity.setBackground(new Color(177, 178, 181));
		cbEntity.setBounds(784, 87, 150, 22);
		add(cbEntity);
		
		JButton btn = new JButton("Relacionar");
		btn.setBounds(682, 174, 100, 53);
		btn.addActionListener(this);
		add(btn);
		
		JButton btnStageRegister = new JButton("Cadastrar");
		btnStageRegister.setFont(new Font("Arial", Font.PLAIN, 11));
		btnStageRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					data.registerStage(tfStageName.getText(), tfNum.getText());
					updateStage();
					JOptionPane.showMessageDialog(self, "Piloto cadastrado com sucesso");
				} catch (ExcecaoEspacoVazio e1) {
					JOptionPane.showMessageDialog(self, e1.getMessage());
				} catch (ExcecaoObjetoJaCadastrado e1) {
					JOptionPane.showMessageDialog(self, e1.getMessage());
				}
			}
		});
		btnStageRegister.setBounds(620, 57, 100, 23);
		stage_panel.add(btnStageRegister);
		
		return;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String teste = "";
		if(e.getSource() instanceof JButton) {
			teste = ((JButton) e.getSource()).getText();
			if(((JButton) e.getSource()).getName()!= null && ((JButton) e.getSource()).getName().equals("btnReturn")) {
				ms.menuReturn();
			}
		}
		
	}
	
	public void updateStage() {
		DefaultTableModel model = (DefaultTableModel) table_stage.getModel();
		String[] columns = {"Equipes"};
		model.setDataVector(data.stagesToArray(), columns);
		model.fireTableDataChanged();
	}

}
