package Interfaces;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

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
import POJO.Planning;
import POJO.Show;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;

@SuppressWarnings("serial")
public class LstShowOrg extends JFrame {

	private JPanel contentPane;
	private JTable table = new JTable();
	private JButton btnRetour;
	private JButton btnVoir;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JLabel lblError;

	/**
	 * Create the frame.
	 */
	public LstShowOrg(Organizer orga, Planning pla) {
		Planning pl = pla.getOne();
		setTitle("Bosquet Wallon - Spectacles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[16.5%,fill][16.5%,fill][16.5%,fill][16.5%,fill][16.5%,fill][16.5%,fill]", "[][][][]"));
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String strd = formatter.format(pl.getBeginDate().getTime());
		String strf = formatter.format(pl.getEndDate().getTime());
		JLabel lblTitre = new JLabel("Liste des Spectacles organisés du " + strd + " au " + strf);
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblTitre, "cell 0 0 6 1,growx,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1 6 1,grow");
		
		List<Show> lstSpec= pl.getShowList();
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		String header[] = new String[] { "", "Titre", "Description", "Places / Réservation", "Configuration"};
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		if(!lstSpec.isEmpty()) {
			TableColumnModel tcm = table.getColumnModel();       
			tcm.getColumn(0).setMaxWidth(1);
		}
		
		// Remplissage du tableau
		Iterator<Show> iter = lstSpec.iterator();
		while(iter.hasNext()) {
			Show s = iter.next();
			s.setConfig(s.getConfig().getOne());
			dtm.addRow(new Object[] {s.getId(), s.getTitle(), s.getDescription(), s.getTicketPerPerson(), s.getConfig().getType()});
		}
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	    	  if(table.getSelectedRow() > -1){
	    		  btnSupprimer.setEnabled(true);
	    		  btnModifier.setEnabled(true);
	    	  }
	      }
	    });
		scrollPane.setViewportView(table);
		
		btnVoir = new JButton("Ajouter un Spectacle");
		btnVoir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreaShowOrg win = new CreaShowOrg(orga, pl);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnVoir, "cell 1 2");
		
		btnModifier = new JButton("Modifier Spectacle");
		btnModifier.setEnabled(false);
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow()!=-1?table.getSelectedRow():0;
				Show s = new Show();
				s.setId((int) table.getValueAt(index, 0));
				s = s.getOne();
				ModifShowOrg win = new ModifShowOrg(orga, pl, s);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnModifier, "cell 2 2");
		
		btnSupprimer = new JButton("Supprimer Spectacle");
		btnSupprimer.setEnabled(false);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() > -1){
					Show s = new Show();
					s.setId((int) table.getValueAt(table.getSelectedRow(), 0));
					if(s.delete()) {
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.removeRow(table.getSelectedRow());
						lblError.setText("Spectacle retiré avec succès");
					} else lblError.setText("Suppression impossible");
				} else lblError.setText("Veuillez sélectionner un élément à supprimer");
			}
		});
		contentPane.add(btnSupprimer, "cell 3 2");
		
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LstBookingOrg win = new LstBookingOrg(orga);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnRetour, "cell 4 2");
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		contentPane.add(lblError, "cell 1 3 4 1");


		
	}

}
