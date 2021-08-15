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

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import POJO.Configuration;
import POJO.Spectator;
import POJO.Ticket;
import Utils.GroupSpinner;
import POJO.Order;
import POJO.Representation;
import POJO.Show;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

@SuppressWarnings("serial")
public class CreaOrderSpec extends JFrame {

	private JPanel contentPane;
	private JTable table = new JTable();
	private JButton btnRetour;
	private JButton btnOrder;
	private JPanel panel;
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JLabel lbl4;
	private JLabel lblError;
	private JLabel lblPrixTot;
	private JSpinner txt1;
	private JSpinner txt2;
	private JSpinner txt3;
	private JSpinner txt4;
	int placeMax;
	private JLabel lblSsTot1;
	private JLabel lblSsTot3;
	private JLabel lblSsTot2;
	private JLabel lblSsTot4;
	private JLabel lblSs2;
	private JLabel lblSs1;
	private JLabel lblSs3;
	private JLabel lblSs4;
	Representation r = new Representation();
	Order com = new Order();
	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CreaOrderSpec(Spectator Spectator, Show spec) {
		spec.setRepresentationList();
		spec.getConfig().setCategoryList();
		spec.setCatPrice();
		placeMax = Integer.parseInt(spec.getTicketPerPerson());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Bosquet Wallon - Commander");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[16.5%,fill][16.5%,fill][16.5%,grow,fill][16.5%,fill][16.5%,grow,fill][16.5%,fill]", "[][100.00][5%][][grow][][][][]"));
		
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
			Representation res = iterR.next();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String str = formatter.format(res.getDate().getTime());
			dtm.addRow(new Object[] {res.getId(), str, res.getBeginHour(), res.getEndHour()});
		}
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	    	  if(table.getSelectedRow() > -1){
	    		  spec.getRepresentationList().get(table.getSelectedRow()).setCatPlace();
	    		  r = spec.getRepresentationList().get(table.getSelectedRow());
	    		  for(Ticket t : com.getTicketList()) {
	    			  t.setRepresentation(r);
	    		  }
	    		  AffichePanel(spec.getConfig());
	    		  btnOrder.setEnabled(true);
	    	  }
	      }
	    });
		scrollPane.setViewportView(table);
		
		lblTitre = new JLabel("Passer commande (5\u20AC de frais de dossier)");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lblTitre, "cell 1 2 4 1");
		
		JLabel lblModeDePayement = new JLabel("Mode de payement *");
		contentPane.add(lblModeDePayement, "cell 1 3,alignx trailing");
		
		JComboBox<String> comboBoxPay = new JComboBox<String>();
		comboBoxPay.setModel(new DefaultComboBoxModel(new String[] {"Visa", "PayPal", "Virement SEPA"}));
		comboBoxPay.setSelectedIndex(-1);
		comboBoxPay.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				com.setPaymentMethod((String) comboBoxPay.getSelectedItem());
			}
		});
		contentPane.add(comboBoxPay, "cell 2 3,growx");
		
		JLabel lblModeDeLivraison = new JLabel("Mode de livraison *");
		contentPane.add(lblModeDeLivraison, "cell 3 3,alignx trailing");
		
		JComboBox<String> comboBoxLivr = new JComboBox<String>();
		comboBoxLivr.setModel(new DefaultComboBoxModel(new String[] {"Sur place", "Timbre prior", "Envoi sécurisé"}));
		comboBoxLivr.setSelectedIndex(-1);
		comboBoxLivr.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				com.setDeliveryMethod((String) comboBoxLivr.getSelectedItem());
				com.calculTotal();
				lblPrixTot.setText(com.getTotal() + " €");
			}
		});
		contentPane.add(comboBoxLivr, "cell 4 3,growx");

		panel = new JPanel();
		panel.setVisible(false);
		panel.setBorder(new LineBorder(Color.GRAY));
		contentPane.add(panel, "cell 1 4 4 1,growx,aligny top");
		panel.setLayout(new MigLayout("", "[25%,fill][][25%,grow,fill][25%,fill][][25%,fill]", "[fill][fill][][fill][]"));
		
		JLabel lblCategorie = new JLabel("Choix des places");
		lblCategorie.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblCategorie, "cell 0 0 3 1");
		
		String txtOriginal = "Nombre de place maximum : ";
		JLabel lblNbMax = new JLabel(txtOriginal);
		if(spec.getTicketPerPerson().equals("0")) lblNbMax.setText(txtOriginal + "infini");
		else lblNbMax.setText(lblNbMax.getText() + placeMax);
		panel.add(lblNbMax, "cell 3 0 3 1");

		lbl1 = new JLabel("");
		panel.add(lbl1, "cell 0 1 3 1,alignx trailing");
		
		lblSs1 = new JLabel("Co\u00FBt : ");
		panel.add(lblSs1, "cell 0 2");
		
		lblSsTot1 = new JLabel("0.0 \u20AC");
		panel.add(lblSsTot1, "cell 1 2");
		
		lbl2 = new JLabel("");
		panel.add(lbl2, "cell 0 3 3 1,alignx trailing");
		
		lblSs2 = new JLabel("Co\u00FBt : ");
		panel.add(lblSs2, "cell 0 4");
		
		lblSsTot2 = new JLabel("0.0 \u20AC");
		panel.add(lblSsTot2, "cell 1 4");
		
		lbl3 = new JLabel("");
		panel.add(lbl3, "cell 3 1 3 1,alignx trailing");
		
		lblSs3 = new JLabel("Co\u00FBt : ");
		panel.add(lblSs3, "cell 3 2");
		
		lblSsTot3 = new JLabel("0.0 \u20AC");
		panel.add(lblSsTot3, "cell 4 2");
		
		lbl4 = new JLabel("");
		panel.add(lbl4, "cell 3 3 3 1,alignx trailing");
		
		lblSs4 = new JLabel("Co\u00FBt : ");
	    panel.add(lblSs4, "cell 3 4");
	    
	    lblSsTot4 = new JLabel("0.0 \u20AC");
	    panel.add(lblSsTot4, "cell 4 4");

	    GroupSpinner group = new GroupSpinner(placeMax);
		txt1 = new JSpinner( group.createGroupModel(0, 0, placeMax, 1) );
		txt1.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(com.getDeliveryMethod()!=null && com.getPaymentMethod()!=null) {
					lblError.setText("");
					double SsTot = (int)txt1.getValue()*spec.getConfig().getCategoryList().get(0).getPrice();
					lblSsTot1.setText(SsTot+" €");
					com.getTicketList().removeIf(l->l.getPrice()==spec.getConfig().getCategoryList().get(0).getPrice());
					for(int i = 0;i< (int)txt1.getValue();i++) {
						Ticket ticket = new Ticket();
						ticket.setPrice(spec.getConfig().getCategoryList().get(0).getPrice());
						ticket.setRepresentation(r);
						com.addTicket(ticket);
					}
					com.calculTotal();
					lblPrixTot.setText(com.getTotal() + " €");
				} else lblError.setText("Veuillez choisir un élément dans tout les champs");
			}
		});
		panel.add(txt1, "cell 2 2");
	    
	    txt2 = new JSpinner( group.createGroupModel(0, 0, placeMax, 1) );
	    txt2.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(com.getDeliveryMethod()!=null && com.getPaymentMethod()!=null) {
					lblError.setText("");
					double SsTot = (int)txt2.getValue()*spec.getConfig().getCategoryList().get(1).getPrice();
					lblSsTot2.setText(SsTot+" €");
					com.getTicketList().removeIf(l->l.getPrice()==spec.getConfig().getCategoryList().get(1).getPrice());
					for(int i = 0;i< (int)txt2.getValue();i++) {
						Ticket ticket = new Ticket();
						ticket.setPrice(spec.getConfig().getCategoryList().get(1).getPrice());
						ticket.setRepresentation(r);
						com.addTicket(ticket);
					}
					com.calculTotal();
					lblPrixTot.setText(com.getTotal() + " €");
				} else lblError.setText("Veuillez choisir un élément dans tout les champs");
			}
		});
		panel.add(txt2, "cell 2 4");
		
	    txt3 = new JSpinner( group.createGroupModel(0, 0, placeMax, 1) );
	    txt3.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(com.getDeliveryMethod()!=null && com.getPaymentMethod()!=null) {
					lblError.setText("");
					double SsTot = (int)txt3.getValue()*spec.getConfig().getCategoryList().get(2).getPrice();
					lblSsTot3.setText(SsTot+" €");
					com.removeTickets(spec.getConfig().getCategoryList().get(2).getPrice());
					for(int i = 0;i< (int)txt3.getValue();i++) {
						Ticket ticket = new Ticket();
						ticket.setPrice(spec.getConfig().getCategoryList().get(2).getPrice());
						ticket.setRepresentation(r);
						com.addTicket(ticket);
					}
					com.calculTotal();
					lblPrixTot.setText(com.getTotal() + " €");
				} else lblError.setText("Veuillez choisir un élément dans tout les champs");
			}
		});
	    panel.add(txt3, "cell 5 2");
		
	    txt4 = new JSpinner( group.createGroupModel(0, 0, placeMax, 1) );
	    txt4.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(com.getDeliveryMethod()!=null && com.getPaymentMethod()!=null) {
					lblError.setText("");
					double SsTot = (int)txt4.getValue()*spec.getConfig().getCategoryList().get(3).getPrice();
					lblSsTot4.setText(SsTot+" €");
					com.getTicketList().removeIf(l->l.getPrice()==spec.getConfig().getCategoryList().get(3).getPrice());
					for(int i = 0;i< (int)txt4.getValue();i++) {
						Ticket ticket = new Ticket();
						ticket.setPrice(spec.getConfig().getCategoryList().get(3).getPrice());
						ticket.setRepresentation(r);
						com.addTicket(ticket);
					}
					com.calculTotal();
					lblPrixTot.setText(com.getTotal() + " €");
				} else lblError.setText("Veuillez choisir un élément dans tout les champs");
			}
		});
	    panel.add(txt4, "cell 5 4");

		JLabel lblTot = new JLabel("Prix total");
		lblTot.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblTot, "cell 3 5");
		
		lblPrixTot = new JLabel("0 €");
		lblPrixTot.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(lblPrixTot, "cell 4 5");
		
		JLabel lblSuppl = new JLabel("* Un supplément de 10€ est demandé pour l'envoie sécurisé");
		lblSuppl.setFont(new Font("Tahoma", Font.ITALIC, 8));
		contentPane.add(lblSuppl, "cell 1 6 4 1");
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		contentPane.add(lblError, "cell 0 7 4 1");
		
		btnOrder = new JButton("Commander");
		btnOrder.setEnabled(false);
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(com.getDeliveryMethod()!=null && com.getPaymentMethod()!=null) {
					if(!com.getTicketList().isEmpty()) {
						lblError.setText("");
						if(Spectator.createOrder(com)) {
							ArrayList<Ticket> ts = com.getTicketList();
							com = com.getOneNoID();
							com.setTicketList(ts);
							com.createTickets();
							lblError.setText("Commande enregistrée");
							btnOrder.setEnabled(false);
						} else lblError.setText("Erreur lors de la création de la Commande");
					} else lblError.setText("Veuillez choisir des places à commander");
				} else lblError.setText("Veuillez choisir un élément dans tout les champs étoilés");
			}
		});
		contentPane.add(btnOrder, "cell 1 8 2 1");
		
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
	void AffichePanel(Configuration conf){
		switch(conf.getType()) {
			case "Debout" : 
				lbl1.setText(conf.getCategoryList().get(0).getType() + " - " + conf.getCategoryList().get(0).getAvailableTickets() + " places disponibles - " + conf.getCategoryList().get(0).getPrice()+"€");
				txt2.setVisible(false);
				lbl2.setVisible(false);
				lblSs2.setVisible(false);
				lblSsTot2.setVisible(false);
				txt3.setVisible(false);
				lbl3.setVisible(false);
				lblSs3.setVisible(false);
				lblSsTot3.setVisible(false);
				txt4.setVisible(false);
				lbl4.setVisible(false);
				lblSs4.setVisible(false);
				lblSsTot4.setVisible(false);
				break;
		case "Assis Concert" :
				lbl1.setText(conf.getCategoryList().get(0).getType() + " - " + conf.getCategoryList().get(0).getAvailableTickets() + " places disponibles - " + conf.getCategoryList().get(0).getPrice()+"€");
				lbl2.setText(conf.getCategoryList().get(1).getType() + " - " + conf.getCategoryList().get(1).getAvailableTickets() + " places disponibles - " + conf.getCategoryList().get(1).getPrice()+"€");
				lbl3.setText(conf.getCategoryList().get(2).getType() + " - " + conf.getCategoryList().get(2).getAvailableTickets() + " places disponibles - " + conf.getCategoryList().get(2).getPrice()+"€");
				txt4.setVisible(false);
				lbl4.setVisible(false);
				lblSs4.setVisible(false);
				lblSsTot4.setVisible(false);
				break;
			case "Assis Cirque" :
				lbl1.setText(conf.getCategoryList().get(0).getType() + " - " + conf.getCategoryList().get(0).getAvailableTickets() + " places disponibles - " + conf.getCategoryList().get(0).getPrice()+"€");
				lbl2.setText(conf.getCategoryList().get(1).getType() + " - " + conf.getCategoryList().get(1).getAvailableTickets() + " places disponibles - " + conf.getCategoryList().get(1).getPrice()+"€");
				lbl3.setText(conf.getCategoryList().get(2).getType() + " - " + conf.getCategoryList().get(2).getAvailableTickets() + " places disponibles - " + conf.getCategoryList().get(2).getPrice()+"€");
				lbl4.setText(conf.getCategoryList().get(3).getType() + " - " + conf.getCategoryList().get(3).getAvailableTickets() + " places disponibles - " + conf.getCategoryList().get(3).getPrice()+"€");
				break;
			default :
				
		}
		panel.setVisible(true);
	}
}
