package Interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;

import POJO.Artist;
import POJO.Organizer;
import POJO.Planning;
import POJO.Show;

@SuppressWarnings("serial")
public class LstArtistsOrg extends JFrame {

	private JPanel contentPane;
	private JTable table = new JTable();
	private JButton btnRetour;
	private JButton btnAjouter;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JLabel lblAdresse;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtAdresse;
	private JButton btnSupprimer;
	private JLabel lbl1;
	private JLabel lblError;
	private JTextField txtAdresseMail;
	private JLabel lblAdresseMail;
	private JLabel lblNomScene;
	private JTextField txtNomScene;
	Artist art;
	final DefaultComboBoxModel<Artist> model = new DefaultComboBoxModel<Artist>();
	private JComboBox<Artist> comboBoxNomScene;

	/**
	 * Create the frame.
	 */
	public LstArtistsOrg(Organizer orga, Planning pla, Show spec) {
		setTitle("Bosquet Wallon - Artistes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[12.5%,grow,fill][12.5%,fill][12.5%,grow,fill][12.5%,fill][12.5%,fill][12.5%,grow,fill][12.5%,fill][12.5%,fill]", "[][195.00][5%][][][][][]"));
		
		JLabel lblTitre = new JLabel("Liste des artistes participants au Spectacle");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblTitre, "cell 0 0 8 1,growx,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1 8 1,grow");
		
		
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		String header[] = new String[] { "Nom de scène", "E-Mail" };
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		
		// Remplissage du tableau
		Iterator<Artist> iter = spec.getArtistList().iterator();
		while(iter.hasNext()) {
			Artist a = iter.next();
			dtm.addRow(new Object[] {a.getShowname(), a.getEmailAddress()});
		}
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	    	  if(table.getSelectedRow() > -1){
				btnSupprimer.setEnabled(true);
				POJO.Person p = new POJO.Person();
				p.setEmailAddress((String) table.getValueAt(table.getSelectedRow(), 1));
				p = p.getOne();
				Artist a = (Artist) p;
				a = a.getOne();
				txtNom.setText(a.getLastname());
				txtPrenom.setText(a.getFirstname());
				txtNomScene.setText(a.getShowname());
				txtAdresseMail.setText(a.getEmailAddress());
				txtAdresse.setText(a.getAdress());
	    	  }
	      }
	    });
		
		scrollPane.setViewportView(table);
		
		lbl1 = new JLabel("Ajouter / supprimer un artiste");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lbl1, "cell 1 2 6 1");
		
		lblNom = new JLabel("Nom");
		contentPane.add(lblNom, "cell 1 3,alignx right,aligny center");
		
		txtNom = new JTextField();
		contentPane.add(txtNom, "cell 2 3 2 1,growx,aligny center");
		txtNom.setColumns(10);
		
		lblPrenom = new JLabel("Pr\u00E9nom");
		contentPane.add(lblPrenom, "cell 4 3,alignx right,aligny center");
		
		txtPrenom = new JTextField();
		contentPane.add(txtPrenom, "cell 5 3 2 1,growx,aligny center");
		txtPrenom.setColumns(10);
		
		lblNomScene = new JLabel("Nom de sc\u00E8ne");
		contentPane.add(lblNomScene, "cell 1 4,alignx trailing");
		
		Artist a = new Artist();
		List<Artist> lsta = a.getAll();
		model.addElement(null);
		model.addAll(lsta);
		
		comboBoxNomScene = new JComboBox<Artist>();
		comboBoxNomScene.setMaximumRowCount(5);
		comboBoxNomScene.setModel(model);
		comboBoxNomScene.setSelectedIndex(0);
		art = (Artist) comboBoxNomScene.getSelectedItem();
		comboBoxNomScene.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				art = (Artist)comboBoxNomScene.getSelectedItem();
				if(art != null) {
					txtNom.setText(art.getLastname());
					txtNom.setEditable(false);
					txtPrenom.setText(art.getFirstname());
					txtPrenom.setEditable(false);
					txtNomScene.setText(art.getShowname());
					txtNomScene.setEditable(false);
					txtAdresseMail.setText(art.getEmailAddress());
					txtAdresseMail.setEditable(false);
					txtAdresse.setText(art.getAdress());
					txtAdresse.setEditable(false);
				} else {
					txtNom.setText("");
					txtNom.setEditable(true);
					txtPrenom.setText("");
					txtPrenom.setEditable(true);
					txtNomScene.setText("");
					txtNomScene.setEditable(true);
					txtAdresseMail.setText("");
					txtAdresseMail.setEditable(true);
					txtAdresse.setText("");
					txtAdresse.setEditable(true);
				}
			}
		});
		
		txtNomScene = new JTextField();
		txtNomScene.setColumns(10);
		contentPane.add(txtNomScene, "cell 2 4 4 1,growx");
		contentPane.add(comboBoxNomScene, "cell 6 4,growx");
		
		lblAdresse = new JLabel("Adresse");
		contentPane.add(lblAdresse, "cell 1 5,alignx right,aligny center");
		
		txtAdresse = new JTextField();
		contentPane.add(txtAdresse, "cell 2 5 2 1,growx,aligny center");
		txtAdresse.setColumns(10);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtNomScene.getText()!=null&&txtAdresseMail.getText()!=null) {
					Artist a = new Artist();
					a.setLastname(txtNom.getText());
					a.setFirstname(txtPrenom.getText());
					a.setShowname(txtNomScene.getText());
					a.setEmailAddress(txtAdresseMail.getText());
					a.setAdress(txtAdresse.getText());
					if(art != null) {
						a.setId(art.getId());
					} else {
						if(a.create()) { 
							POJO.Person p = new POJO.Person();
							p.setEmailAddress(a.getEmailAddress());
							p = p.getOne();
							a = (Artist) p;
							a = a.getOne();
						}  else lblError.setText("Erreur lors de l'ajout de l'artiste");
					}
					if(spec.ArtistAvailable(a)) {
						if(spec.addArtist(a)) {
							dtm.addRow(new Object[] {a.getShowname(), a.getEmailAddress()});
							lblError.setText("Artiste ajouté avec succès");
						} else lblError.setText("Erreur lors de l'ajout de l'artiste");
					} else lblError.setText("Artiste déjà dans la liste");
				} else lblError.setText("Veuillez renseigner Email et nom de scène");
			}
		});
		
		lblAdresseMail = new JLabel("Adresse mail");
		contentPane.add(lblAdresseMail, "cell 4 5,alignx trailing");
		
		txtAdresseMail = new JTextField();
		txtAdresseMail.setColumns(10);
		contentPane.add(txtAdresseMail, "cell 5 5 2 1,growx");
		contentPane.add(btnAjouter, "cell 1 6 2 1");

		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setEnabled(false);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	  if(table.getSelectedRow() > -1){
		    		  Artist a = new Artist();
		    		  POJO.Person p = new POJO.Person();
						p.setEmailAddress(txtAdresseMail.getText());
						p = p.getOne();
						a = (Artist) p;
						a = a.getOne();
						if(spec.removeArtist(a)) {
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							model.removeRow(table.getSelectedRow());
							lblError.setText("Artiste retiré avec succès");
						} else lblError.setText("Erreur lors de la suppression de l'artiste");
				} else lblError.setText("Veuillez sélectionner un élément à supprimer");
			}
		});
		contentPane.add(btnSupprimer, "cell 3 6 2 1");
		
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifShowOrg win = new ModifShowOrg(orga, pla, spec);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnRetour, "cell 5 6 2 1");
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblError, "cell 1 7 6 1");
	}
}
