import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JSplitPane;
import javax.swing.JComboBox;
import java.awt.ScrollPane;
import java.awt.Panel;
import javax.swing.JList;
import javax.swing.JToggleButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.List;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtfNome;
	private JTextField txtLocal;
	private JTextField txtdata;
	private JTextField txthora;
	private JTextField txttamanho;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnpatrocinador;
	private JLabel nome;
	private JLabel local;
	private JLabel data;
	private JLabel horario;
	private JLabel tamanho;
	private JLabel ingresso;
	private JLabel corredores;
	private JLabel patrocinadores;
	private List list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("Cadastrar Eventos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		nome = new JLabel("nome");
		contentPane.add(nome);
		
		txtfNome = new JTextField();
		contentPane.add(txtfNome);
		txtfNome.setColumns(20);
		
		local = new JLabel("local");
		contentPane.add(local);
		
		txtLocal = new JTextField();
		contentPane.add(txtLocal);
		txtLocal.setColumns(20);
		
		data = new JLabel("data");
		contentPane.add(data);
		
		txtdata = new JTextField();
		txtdata.setText("00/00/0000");
		txtdata.setToolTipText("");
		contentPane.add(txtdata);
		txtdata.setColumns(8);
		
		horario = new JLabel("Horário");
		contentPane.add(horario);
		
		txthora = new JTextField();
		txthora.setText("00:00");
		txthora.setToolTipText("");
		txthora.setColumns(4);
		contentPane.add(txthora);
		
		tamanho = new JLabel("Tamanho");
		contentPane.add(tamanho);
		
		txttamanho = new JTextField();
		txttamanho.setText("Em KM");
		contentPane.add(txttamanho);
		txttamanho.setColumns(10);
		
		ingresso = new JLabel("Quantidade de Ingressos");
		contentPane.add(ingresso);
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(3);
		
		corredores = new JLabel("Quantidade de Corredores ");
		contentPane.add(corredores);
		
		textField_1 = new JTextField();
		textField_1.setColumns(3);
		contentPane.add(textField_1);
		
		patrocinadores = new JLabel("Patrocinadores ");
		contentPane.add(patrocinadores);
		
		textField_2 = new JTextField();
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		btnpatrocinador = new JButton("+");
		contentPane.add(btnpatrocinador);
		
		list = new List();
		contentPane.add(list);
		
	}

}
