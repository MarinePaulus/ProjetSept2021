package Interfaces;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import POJO.Organizer;
import POJO.Booking;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;

@SuppressWarnings("serial")
public class LstBookingOrg extends JFrame {

	private JPanel contentPane;
	private JTable table = new JTable();
	private JButton btnRetour;
	private JButton btnVoir;
	private JButton btnSupprimer;
	private JLabel lblError;

	/**
	 * Create the frame.
	 */
	public LstBookingOrg(Organizer orga) {
		setTitle("Bosquet Wallon - Réservations");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[25%,fill][][25%,fill][130.00][25%,fill][25%,fill]", "[][][][]"));
		
		JLabel lblTitre = new JLabel("Liste des réservations faites");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblTitre, "cell 0 0 6 1,growx,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1 6 1,grow");
		
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		String header[] = new String[] { "", "Date de début", "Date de fin", "Solde", "Statut"};
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		if(!orga.getBookingList().isEmpty()) {
			TableColumnModel tcm = table.getColumnModel();       
			tcm.getColumn(0).setMaxWidth(1);
		}
		// Remplissage du tableau
		Iterator<Booking> iter = orga.getBookingList().iterator();
		while(iter.hasNext()) {
			Booking r = iter.next();
			r.setPlanning(r.getPlanning().getOne());
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String strd = formatter.format(r.getPlanning().getBeginDate().getTime());
			String strf = formatter.format(r.getPlanning().getEndDate().getTime());
			dtm.addRow(new Object[] {r.getId(), strd,strf,r.getBalance(),r.getStatus()});
		}
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	    	  if(table.getSelectedRow() > -1){
	    		  btnSupprimer.setEnabled(true);
	    		  btnVoir.setEnabled(true);
	    	  }
	      }
	    });
		scrollPane.setViewportView(table);
		
		
		btnSupprimer = new JButton("Annuler Réservation");
		btnSupprimer.setEnabled(false);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	  if(table.getSelectedRow() > -1){
		    		Booking r = new Booking();
					r.setId((int) table.getValueAt(table.getSelectedRow(), 0));
					r = r.getOne();
					r.setStatus("Annulé");
					if(r.update()) {
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.setValueAt(r.getStatus(), table.getSelectedRow(), 4);
						lblError.setText("Réservation annulée avec succès");
					} else lblError.setText("Annulation impossible");
				} else lblError.setText("Veuillez sélectionner un élément à annuler");
			}
		});
		contentPane.add(btnSupprimer, "cell 3 2");
		
		
		btnVoir = new JButton("Voir les spectacles");
		btnVoir.setEnabled(false);
		btnVoir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow()!=-1?table.getSelectedRow():0;
				Booking r = new Booking();
				r.setId((int) table.getValueAt(index, 0));
				r = r.getOne();
				LstShowOrg win = new LstShowOrg(orga, r.getPlanning());
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnVoir, "cell 2 2");
		
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccueilOrg win = new AccueilOrg(orga);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnRetour, "cell 4 2");
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblError, "cell 0 3 6 1");
	}
}
