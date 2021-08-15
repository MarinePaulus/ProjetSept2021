package Interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import POJO.Organizer;
import POJO.Spectator;
import net.miginfocom.swing.MigLayout;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class Registration extends JFrame {

	private JPanel contentPane;
	private JLabel lblNom;
	private JTextField textFieldNom;
	private JLabel lblPrenom;
	private JTextField textFieldPrenom;
	private JLabel lblMdp;
	private JPasswordField textFieldMdp;
	private JLabel lblAdresse;
	private JTextField textFieldAdresse;
	private JLabel lblRole;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private JLabel lblMail;
	private JTextField textFieldMail;
	private JLabel lblTel;
	private JLabel lblGenre;
	private JLabel lblNaiss;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxGenre;
	private JTextField textFieldTel;
	private JDateChooser dateChooserNaiss;
	
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Registration() {
		setTitle("Bosquet Wallon - Inscription");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[25%,fill][5%,grow,fill][20%,grow,fill][5%,grow,fill][5%,grow,fill][20%,grow,fill][25%,fill]", "[50%][][][][][][][][][][][50%]"));
		
		JLabel lblNewLabel = new JLabel("S'inscrire");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, "cell 1 1 5 1,alignx center,aligny center");
		
		lblNom = new JLabel("Nom");
		contentPane.add(lblNom, "cell 1 3");
		
		textFieldNom = new JTextField();
		contentPane.add(textFieldNom, "cell 2 3,growx");
		textFieldNom.setColumns(10);
		
		lblPrenom = new JLabel("Pr\u00E9nom");
		contentPane.add(lblPrenom, "cell 4 3");
		
		textFieldPrenom = new JTextField();
		contentPane.add(textFieldPrenom, "cell 5 3,growx");
		textFieldPrenom.setColumns(10);
		
		lblMail = new JLabel("Mail *");
		contentPane.add(lblMail, "cell 1 4,alignx trailing");
		
		textFieldMail = new JTextField();
		textFieldMail.setColumns(10);
		contentPane.add(textFieldMail, "cell 2 4,growx");
		
		lblMdp = new JLabel("Mot de passe *");
		contentPane.add(lblMdp, "cell 4 4");
		
		textFieldMdp = new JPasswordField();
		contentPane.add(textFieldMdp, "cell 5 4,growx");
		
		lblAdresse = new JLabel("Adresse");
		contentPane.add(lblAdresse, "cell 1 5");
		
		textFieldAdresse = new JTextField();
		contentPane.add(textFieldAdresse, "cell 2 5,growx");
		textFieldAdresse.setColumns(10);
		
		lblTel = new JLabel("T\u00E9l\u00E9phone");
		contentPane.add(lblTel, "cell 4 5,alignx trailing");
		
		textFieldTel = new JTextField();
		textFieldTel.setColumns(10);
		contentPane.add(textFieldTel, "cell 5 5,growx");
		
		lblRole = new JLabel("Role *");
		contentPane.add(lblRole, "cell 1 6");
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Spectateur", "Organisateur"}));
		comboBox.setSelectedIndex(0);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String role = (String) comboBox.getSelectedItem();
				if(role.equals("Spectateur")) {
					lblGenre.setVisible(true);
					comboBoxGenre.setVisible(true);
					lblNaiss.setVisible(true);
					dateChooserNaiss.setVisible(true);
				} else {
					lblGenre.setVisible(false);
					comboBoxGenre.setVisible(false);
					lblNaiss.setVisible(false);
					dateChooserNaiss.setVisible(false);
				}
			}
		});
		contentPane.add(comboBox, "cell 2 6,growx");
		
		lblGenre = new JLabel("Genre");
		contentPane.add(lblGenre, "cell 1 7,alignx trailing");
		
		comboBoxGenre = new JComboBox();
		comboBoxGenre.setModel(new DefaultComboBoxModel(new String[] {"Non spécifié", "Femme", "Homme", "Autres"}));
		comboBoxGenre.setSelectedIndex(0);
		contentPane.add(comboBoxGenre, "cell 2 7,growx");
		
		lblNaiss = new JLabel("Naissance");
		contentPane.add(lblNaiss, "cell 4 7");
		
		dateChooserNaiss = new JDateChooser();
		dateChooserNaiss.setDateFormatString("dd/MM/yyyy");
		contentPane.add(dateChooserNaiss, "cell 5 7,grow");
		
		JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		contentPane.add(lblError, "cell 1 11 5 1");
		
		JButton btnInscrire = new JButton("S'inscrire");
		contentPane.add(btnInscrire, "cell 1 10 2 1");
		btnInscrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String role = (String) comboBox.getSelectedItem();
				boolean ok = false;
				if(!textFieldMail.getText().isEmpty() && textFieldMdp.getPassword().length!=0) {
					if(role.equalsIgnoreCase("Spectateur")) {
						Spectator cli = new Spectator();
						cli.setFirstname(textFieldPrenom.getText());
						cli.setLastname(textFieldNom.getText());
						cli.setAdress(textFieldAdresse.getText());
						cli.setEmailAddress(textFieldMail.getText());
						cli.setPassword(String.valueOf(textFieldMdp.getPassword()));
						cli.setPhoneNumber(textFieldTel.getText());
						cli.setGender((String) comboBoxGenre.getSelectedItem());
						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
						String  strd = "";
						if(dateChooserNaiss.getDate()!=null)
							strd = formatter.format(dateChooserNaiss.getDate());
						
						cli.setBirthdate(strd);
						ok = cli.create();
					}
					else {
						Organizer orga = new Organizer();
						orga.setFirstname(textFieldPrenom.getText());
						orga.setLastname(textFieldNom.getText());
						orga.setAdress(textFieldAdresse.getText());
						orga.setEmailAddress(textFieldMail.getText());
						orga.setPassword(String.valueOf(textFieldMdp.getPassword()));
						orga.setPhoneNumber(textFieldTel.getText());
						ok = orga.create();
					}
					if(ok) lblError.setText("Inscription réussie, vous pouvez vous connecter");
					else lblError.setText("Erreur lors de l'inscription, veuillez réessayer");
				} else lblError.setText("Veuillez remplir tout les champs obligatoires, s'il vous plaît");
			}
		});
		
		JButton btnRetour = new JButton("Retour");
		contentPane.add(btnRetour, "cell 4 10 2 1");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection win = new Connection();
				win.setVisible(true);
				dispose();
			}
		});
	}

}
