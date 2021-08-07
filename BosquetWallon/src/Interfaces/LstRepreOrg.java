package Interfaces;

import java.awt.Color;
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
import POJO.Planning;
import POJO.Representation;
import POJO.Show;
import net.miginfocom.swing.MigLayout;
import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

@SuppressWarnings("serial")
public class LstRepreOrg extends JFrame {

	private JPanel contentPane;
	private JTable table = new JTable();
	private JButton btnRetour;
	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JLabel lbl1;
	private JLabel lblError;
	private JLabel lblDate;
	private JLabel lblHeureDebut;
	private JLabel lblHeureFin;
	private JDateChooser dateChooser;
	private JLabel label;
	private JLabel label_1;
	private JSpinner spinDebutH;
	private JSpinner spinDebutM;
	private JSpinner spinFinH;
	private JSpinner spinFinM;

	/**
	 * Create the frame.
	 */
	public LstRepreOrg(Organizer orga, Planning pla, Show spec) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Bosquet Wallon - Représentations");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[12.5%,fill][12.5%,fill][12.5%,grow,fill][][12.5%,grow,fill][12.5%,fill][12.5%,grow,fill][][12.5%,grow,fill][12.5%,fill]", "[][195.00][5%][grow][][][]"));
		
		JLabel lblTitre = new JLabel("Liste des représentations du spectacle");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblTitre, "cell 0 0 10 1,growx,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1 10 1,grow");
		
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		String header[] = new String[] { "", "Date", "Heure Début", "Heure Fin" };
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		
		if(!spec.getRepresentationList().isEmpty()) {
			TableColumnModel tcm = table.getColumnModel();       
			tcm.getColumn(0).setMaxWidth(1);
		}
		
		// Remplissage du tableau
		Iterator<Representation> iter = spec.getRepresentationList().iterator();
		while(iter.hasNext()) {
			Representation r = iter.next();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String str = formatter.format(r.getDate().getTime());
			dtm.addRow(new Object[] {r.getId(), str, r.getBeginHour(), r.getEndHour()});
		}
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	    	  if(table.getSelectedRow() > -1){
	    		  btnSupprimer.setEnabled(true);
	    	  }
	      }
	    });
		scrollPane.setViewportView(table);
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblError, "cell 1 6 8 1");
		
		lbl1 = new JLabel("Ajouter une représentation");
		lbl1.setHorizontalAlignment(SwingConstants.CENTER);
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lbl1, "cell 1 2 8 1");
		
		lblDate = new JLabel("Date");
		lblDate.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblDate, "cell 2 3,alignx center");
		
		dateChooser = new JDateChooser();
		dateChooser.setSelectableDateRange(pla.getBeginDate().getTime(), pla.getEndDate().getTime());
		contentPane.add(dateChooser, "cell 4 3 3 1,grow");
		
		lblHeureDebut = new JLabel("Heure de d\u00E9but");
		contentPane.add(lblHeureDebut, "cell 1 4,alignx trailing");

		spinDebutH = new JSpinner();
		spinDebutH.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				lblError.setText("");
			}
		});
		spinDebutH.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		contentPane.add(spinDebutH, "cell 2 4");
		
		label = new JLabel("H");
		contentPane.add(label, "cell 3 4,alignx trailing");
		
		spinDebutM = new JSpinner();
		spinDebutM.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				lblError.setText("");
			}
		});
		spinDebutM.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		contentPane.add(spinDebutM, "cell 4 4");
		
		lblHeureFin = new JLabel("Heure de fin");
		contentPane.add(lblHeureFin, "cell 5 4,alignx trailing");
		
		spinFinH = new JSpinner();
		spinFinH.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				lblError.setText("");
			}
		});
		spinFinH.setModel(new SpinnerNumberModel(0, 0, 23, 1));
		contentPane.add(spinFinH, "cell 6 4");
		
		label_1 = new JLabel("H");
		contentPane.add(label_1, "cell 7 4,alignx trailing");
		
		spinFinM = new JSpinner();
		spinFinM.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				lblError.setText("");
			}
		});
		spinFinM.setModel(new SpinnerNumberModel(0, 0, 59, 1));
		contentPane.add(spinFinM, "cell 8 4");
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dateChooser.getDate()!=null&&spinDebutH.getValue()!=null&&spinDebutM.getValue()!=null
						&&spinFinH.getValue()!=null&&spinFinM.getValue()!=null) {
					lblError.setText("");
					Representation r = new Representation();
					r.setDate(dateChooser.getDate());
					r.setBeginHour(spinDebutH.getValue() + ":" + spinDebutM.getValue());
					r.setEndHour(spinFinH.getValue() + ":" + spinFinM.getValue());
					r.setShow(spec);
					
					if(r.verifyAvailable(pla)) {
						if(spec.addRepresentation(r)) {
								SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
								String str = formatter.format(r.getDate().getTime());
								dtm.addRow(new Object[] {r.getId(), str, r.getBeginHour(), r.getEndHour()});
								lblError.setText("Representation ajouté avec succès");
						} else lblError.setText("Erreur lors de l'ajout de la représentation");
					} else lblError.setText("Horaire indisponible, veuillez choisir une autre tranche horaire");
				} else lblError.setText("Veuillez renseigner tous les champs");
			}
		});
		contentPane.add(btnAjouter, "cell 1 5 2 1");

		btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setEnabled(false);
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		    	  if(table.getSelectedRow() > -1){
					Representation r = new Representation();
					r.setId((int) table.getValueAt(table.getSelectedRow(), 0));
					if(spec.removeRepresentation(r)) {
						DefaultTableModel model = (DefaultTableModel) table.getModel();
						model.removeRow(table.getSelectedRow());
						lblError.setText("Représentation retiré avec succès");
					} else lblError.setText("Erreur lors de la suppression de la représentation");
				} else lblError.setText("Veuillez sélectionner un élément à supprimer");
			}
		});
		contentPane.add(btnSupprimer, "cell 4 5 2 1");
		
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifShowOrg win = new ModifShowOrg(orga, pla, spec);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnRetour, "cell 6 5 3 1");
	}
}
