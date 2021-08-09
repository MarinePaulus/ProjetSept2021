package Interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import POJO.Manager;
import POJO.Organizer;
import POJO.Person;
import POJO.Spectator;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class Connection extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connection frame = new Connection();
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
	public Connection() {
		setTitle("Bosquet Wallon - Connexion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[24%,fill][24%,grow,fill][5%,fill][24%,fill][24%,fill]", "[50%][][][][][][][][50%]"));
		
		JLabel lblNewLabel = new JLabel("Se connecter");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblNewLabel, "cell 1 1 3 1,alignx center,aligny center");
		
		JLabel lblMail = new JLabel("Mail");
		contentPane.add(lblMail, "cell 1 3");
		
		JTextField textFieldMail = new JTextField();
		textFieldMail.setToolTipText("Email");
		contentPane.add(textFieldMail, "cell 3 3,growx");
textFieldMail.setText("haddock@gmail.com");
		
		JLabel lblMdp = new JLabel("Mot de passe");
		contentPane.add(lblMdp, "cell 1 4");
		
		JPasswordField textFieldMdp = new JPasswordField();
		textFieldMdp.setToolTipText("Mot de passe");
		contentPane.add(textFieldMdp, "cell 3 4,growx");
textFieldMdp.setText("1624974620");
			
		JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		contentPane.add(lblError, "cell 1 8 3 1");
		
		JButton btnConnecter = new JButton("Se connecter");
		contentPane.add(btnConnecter, "cell 1 7");
		btnConnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Person p = new Person();
				p.setEmailAddress(textFieldMail.getText());
				p = p.getOne(); 
				
				if(p.getClass().getName().equals("POJO.Spectator")) {
					Spectator c = (Spectator) p;
					c.setPassword(String.valueOf(textFieldMdp.getPassword()));
					c = c.getOne();
					if(c==null) {lblError.setText("Mot de passe incorrect");}
					else {
						lblError.setText("Bienvenue Spectateur");
						AccueilSpec win = new AccueilSpec(c);
						win.setVisible(true);
						dispose();
					}
				}
				else if(p.getClass().getName().equals("POJO.Manager")) {
					Manager g = (Manager) p;
					g.setPassword(String.valueOf(textFieldMdp.getPassword()));
					g = g.getOne();
					if(g==null) {lblError.setText("Mot de passe incorrect");}
					else {
						lblError.setText("Bienvenue Gestionnaire");
						AccueilMana win = new AccueilMana(g);
						win.setVisible(true);
						dispose();
					}
				}
				else if(p.getClass().getName().equals("POJO.Organizer")) {
					Organizer o = (Organizer) p;
					o.setPassword(String.valueOf(textFieldMdp.getPassword()));
					o = o.getOne();
					if(o==null) {lblError.setText("Mot de passe incorrect");}
					else {
						lblError.setText("Bienvenue Organisateur");
						AccueilOrg win = new AccueilOrg(o);
						win.setVisible(true);
						dispose();
					}
				}
				else {
					lblError.setText("Erreur, vous êtes un artiste");
				}
				
			}
		});
		
		JButton btnInscrire = new JButton("S'inscrire");
		contentPane.add(btnInscrire, "cell 3 7");
		btnInscrire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registration win = new Registration();
				win.setVisible(true);
				dispose();
			}
		});
	}
}
