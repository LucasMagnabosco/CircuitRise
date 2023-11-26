package ucs.CircuitRise.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import ucs.CircuitRise.controller.DataController;
import ucs.CircuitRise.exceptions.ExcecaoEspacoVazio;
import ucs.CircuitRise.exceptions.ExcecaoNotNumber;
import ucs.CircuitRise.exceptions.ExcecaoObjetoJaCadastrado;
import ucs.CircuitRise.model.Pilot;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class PilotSelect extends JPanel implements ActionListener{

	private static final long serialVersionUID = 101L; 
	
	JPanel header = new JPanel();
	JPanel pilotForm;
	MainScreen ms;
	private JTable tablePilot;
	private PilotSelect self = this;
	private JScrollPane rolagem;
	private JTable jTable;
	
	DataController data = new DataController();
	
	public PilotSelect(MainScreen menu) {
		
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
		
		JLabel lblNewLabel = new JLabel("Pilotos");
		lblNewLabel.setBounds(387, 11, 126, 31);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 26));
		header.add(lblNewLabel);
		
		JLabel lblTableName = new JLabel("Pilotos cadastrados");
		lblTableName.setFont(new Font("Arial", Font.BOLD, 16));
		lblTableName.setBounds(260, 247, 200, 14);
		add(lblTableName);
		
		
		
		Object[][] rows = data.pilotsToArray();
		String[] columns = {"Nome", "Pontuação Pilotos"};
		tablePilot = new JTable();
		tablePilot.setModel(new DefaultTableModel(rows, columns));
		tablePilot.getColumnModel().getColumn(0).setPreferredWidth(109);
		tablePilot.setShowVerticalLines(false);
		tablePilot.setFont(new Font("Arial", Font.PLAIN, 11));
		tablePilot.setBounds(134, 294, 209, 232);
		tablePilot.setBackground(new Color(195, 196, 199));
		tablePilot.getTableHeader().setBackground(new Color(195, 196, 199));
		tablePilot.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		rolagem = new JScrollPane(tablePilot);
		rolagem.setBackground(new Color(195, 196, 199));
		rolagem.getViewport().setBackground(new Color(195, 196, 199));
		rolagem.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		rolagem.setBounds(125, 272, 409, 232);
		rolagem.getVerticalScrollBar().setBackground(Color.GRAY);
		add(rolagem);
		
		popularTabela();
		
		JButton btnDelete = new JButton("Remover");
		btnDelete.setBounds(682, 332, 100, 23);
		btnDelete.addActionListener(this);
		add(btnDelete);
		
		JButton btnUpdate = new JButton("Atualizar");
		btnUpdate.setBounds(682, 374, 100, 23);
		btnUpdate.addActionListener(this);
		add(btnUpdate);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String teste = "";
		int option;
		
		DefaultTableModel model = (DefaultTableModel) tablePilot.getModel();
		  	
		if(e.getSource() instanceof JButton) {
			teste = ((JButton) e.getSource()).getText();
			if(((JButton) e.getSource()).getName()!= null && ((JButton) e.getSource()).getName().equals("btnReturn")) {
				ms.menuReturn();
			}
		}else if(e.getSource() instanceof JComboBox) {
			teste = (String) ((JComboBox) e.getSource()).getSelectedItem();
		}
		if ("Atualizar".equals(teste)) {
			for (int row = 0; row < model.getRowCount(); row++) {
		        // Obtém os dados da linha
		        Object[] rowData = new Object[model.getColumnCount()];
		        for (int col = 0; col < model.getColumnCount(); col++) {
		            rowData[col] = model.getValueAt(row, col);
		        }
		 }
            popularTabela();
        }else if (e.getSource() instanceof JComboBox) {
        	teste = (String) ((JComboBox) e.getSource()).getSelectedItem();
        }
		
		if(teste.equals("Equipes")) {
			pilotForm.setVisible(true);
		}else if(teste.equals("Remover")){
			int rowt = tablePilot.getSelectedRow();
			if(rowt != -1) {
				String pilot = tablePilot.getValueAt(rowt, 0).toString();
				data.deletePilot(pilot);
			}else {
				JOptionPane.showMessageDialog(self, "Pelo menos uma das opções não foram escolhidas");
			}
		}
		}

		
		private void popularTabela() {
		    // Cria um EntityManagerFactory a partir do persistence.xml
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("dados");
		    EntityManager em = emf.createEntityManager();
		    em.getTransaction().begin();
		    
		    try {
		        String consulta = "SELECT e FROM Pilot e";
		        Query query = em.createQuery(consulta);
		        List<Pilot> resultados = query.getResultList();
		        DefaultTableModel modeloTabela = new DefaultTableModel();
		        modeloTabela.addColumn("Nome");
		        modeloTabela.addColumn("Pontuação Pilotos");
		        for (Pilot piloto : resultados) {
		            modeloTabela.addRow(new Object[]{piloto.getName(), piloto.getPoints()});
		        }
		        
		        tablePilot.setModel(modeloTabela);
		        em.getTransaction().commit();
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		        em.getTransaction().rollback();
		        
		    } finally {
		        em.close();
		        emf.close();
		    }
		}
}
