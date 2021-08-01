package Interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import POJO.Category;
import POJO.Spectator;
import POJO.Ticket;
import POJO.Order;
import POJO.Representation;
import POJO.Show;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class CreaOrderSpec extends JFrame {

	private JPanel contentPane;
	private JTable table = new JTable();
	private JButton btnRetour;
	private JButton btnOrderr;
	private JLabel lbl1;
	private JLabel lblError;
	private JLabel lblNbPlaces;
	private JSpinner spinner;
	private JLabel lblModeDePayement;
	private JLabel lblModeDeLivraison;
	private JComboBox<String> comboBoxPay;
	private JComboBox<String> comboBoxLivr;
	private JLabel lblPrix;
	private JLabel lblPrixTot;
	private JLabel lblSuppl;
	private JLabel lblTypeDePlaces;
	private JComboBox<String> comboBoxType;


	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public CreaOrderSpec(Spectator Spectator, Show spec) {
		Order com = new Order();
		spec.getConfig().setCategoryList();
		List<String> cats = new ArrayList<String>();
		Iterator<Category> iterC = spec.getConfig().getCategoryList().iterator();
		while(iterC.hasNext()) {
			Category cat = iterC.next();
			cats.add(cat.getType());
		}
		if(cats.size()==0) {
			cats.add("Normal");
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Bosquet Wallon - Commander");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[16.5%,fill][16.5%,fill][16.5%,grow,fill][16.5%,fill][16.5%,grow,fill][16.5%,fill]", "[][195.00][5%][grow][][][][5%][]"));
		
		JLabel lblTitre = new JLabel("Liste des représentations");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblTitre, "cell 0 0 6 1,growx,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1 6 1,grow");
		
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		String header[] = new String[] { "", "Date", "Heure Début", "Heure Fin" };
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		if(!spec.getRepresentationList().isEmpty()) {
			TableColumnModel tcm = table.getColumnModel();       
			tcm.getColumn(0).setMaxWidth(1);
		}
		
		// Remplissage du tableau
		Iterator<Representation> iterR = spec.getRepresentationList().iterator();
		while(iterR.hasNext()) {
			Representation r = iterR.next();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String str = formatter.format(r.getDate().getTime());
			dtm.addRow(new Object[] {r.getId(), str, r.getBeginHour(), r.getEndHour()});
		}
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	    	  if(table.getSelectedRow() > -1){
	    		  btnOrderr.setEnabled(true);
	    	  }
	      }
	    });
		scrollPane.setViewportView(table);
		
		lbl1 = new JLabel("Passer commande (5\u20AC de frais de dossier)");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lbl1, "cell 1 2 4 1");
		
		lblNbPlaces = new JLabel("Nombre de places");
		contentPane.add(lblNbPlaces, "cell 1 4");
		
		lblPrix = new JLabel("Prix total");
		contentPane.add(lblPrix, "cell 3 5");
		
		lblPrixTot = new JLabel("0 €");
		contentPane.add(lblPrixTot, "cell 4 5");
		
		SpinnerNumberModel spinmodel = new SpinnerNumberModel(1, 1, null, 1);
		spinner = new JSpinner();
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
//				int nb = (int) spinner.getValue();
//				float prix = com.getCout()*nb;
//				System.out.println("spin : " + com.getCout());
//				com.setCout(prix);
//				System.out.println("spin : " + com.getCout());
//				lblPrixTot.setText(com.getCout()+ " €");
			}
		});
		spinner.setModel(spinmodel);
		if(!spec.getTicketPerPerson().equals("0")) {
			spinmodel.setMaximum(spec.getTicketPerPerson());
			spinner.setModel(spinmodel);
		}
		contentPane.add(spinner, "cell 2 4");
		
		lblModeDePayement = new JLabel("Mode de payement ");
		contentPane.add(lblModeDePayement, "cell 1 3,alignx trailing");
		
		comboBoxPay = new JComboBox<String>();
		comboBoxPay.setModel(new DefaultComboBoxModel(new String[] {"Visa", "PayPal", "Virement SEPA"}));
		comboBoxPay.setSelectedIndex(-1);
		comboBoxPay.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				com.setPaymentMethod((String) comboBoxPay.getSelectedItem());
			}
		});
		contentPane.add(comboBoxPay, "cell 2 3,growx");
		
		lblModeDeLivraison = new JLabel("Mode de livraison *");
		contentPane.add(lblModeDeLivraison, "cell 3 3,alignx trailing");
		
		comboBoxLivr = new JComboBox<String>();
		comboBoxLivr.setModel(new DefaultComboBoxModel(new String[] {"Sur place", "Timbre prior", "Envoi sécurisé"}));
		comboBoxLivr.setSelectedIndex(-1);
		comboBoxLivr.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				com.setDeliveryMethod((String) comboBoxLivr.getSelectedItem());
			}
		});
		contentPane.add(comboBoxLivr, "cell 4 3,growx");
		
		lblTypeDePlaces = new JLabel("Type de places *");
		contentPane.add(lblTypeDePlaces, "cell 3 4,alignx trailing");

		comboBoxType = new JComboBox<String>();
		comboBoxType.setModel(new DefaultComboBoxModel(cats.toArray()));
		contentPane.add(comboBoxType, "cell 4 4,growx");
		
		lblSuppl = new JLabel("* Un supplément de 10€ est demandé pour l'envoie sécurisé");
		lblSuppl.setFont(new Font("Tahoma", Font.ITALIC, 10));
		contentPane.add(lblSuppl, "cell 1 6 4 1");
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblError, "cell 1 7 4 1");
		
		btnOrderr = new JButton("Commander");
		btnOrderr.setEnabled(false);
		btnOrderr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(com.getDeliveryMethod()!=null && com.getPaymentMethod()!=null) {
					// Récup de la représentation choisie
					int index = table.getSelectedRow()!=-1?table.getSelectedRow():0;
					Representation r = new Representation();
					r.setId((int) table.getValueAt(index, 0));
					r = r.getOne();
					
					// récup tt les ticket de la représentation
					Ticket p = new Ticket();
					ArrayList<Ticket> lstT = p.getAll();
					
					if(lstT.size()==0) {}
					
					Ticket ticket = new Ticket();
					ticket.setNumPlace(lstT.get(lstT.size()).getNumPlace()+1);
					ticket.setPrice(0);
					
					
					ticket.setRepresentation(r);
					
					
					
					
					
					if(com.create()) {
						lblError.setText("Commande enregistrée");
						btnOrderr.setEnabled(false);
					} else lblError.setText("Erreur lors de l'enregistrement de la Commande");
				} else lblError.setText("Veuillez choisir un élément dans tout les champs");
			}
		});
		contentPane.add(btnOrderr, "cell 1 8 2 1");
		
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LstShowSpec win = new LstShowSpec(Spectator);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnRetour, "cell 3 8 2 1");
	}
}
