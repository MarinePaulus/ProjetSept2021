package Interfaces;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import POJO.Organizer;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;

@SuppressWarnings("serial")
public class AccueilOrg extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public AccueilOrg(Organizer orga) {
		setTitle("Bosquet Wallon - Accueil");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[20%,fill][20%,fill][20%,fill][20%,fill][20%,fill]", "[25%][][][][][][][25%]"));
		
		JLabel lblBvn = new JLabel("Bienvenue " + orga.getLastname() + " " + orga.getFirstname());
		lblBvn.setHorizontalAlignment(SwingConstants.CENTER);
		lblBvn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(lblBvn, "cell 0 1 5 1,alignx center,aligny center");
		
		JLabel lblTxtBvn = new JLabel("Que voulez-vous faire ?");
		lblTxtBvn.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTxtBvn, "cell 0 2 5 1");
		
		JLabel lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		contentPane.add(lblError, "cell 1 3 3 1");
		
		
		JButton btnPlanning = new JButton("Voir les dates disponibles");
		btnPlanning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlanningOrg win = new PlanningOrg(orga);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnPlanning, "cell 1 4 3 1");
		
		JButton btnReservation = new JButton("Voir mes r\u00E9servations");
		btnReservation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LstBookingOrg win = new LstBookingOrg(orga);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnReservation, "cell 1 5 3 1");
		
		JButton btnDeconnecter = new JButton("Me d\u00E9connecter");
		btnDeconnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection win = new Connection();
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnDeconnecter, "cell 1 6 3 1");
	}
}
