package Interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import com.toedter.calendar.JDateChooser;

import POJO.Organizer;
import POJO.Booking;
import POJO.Planning;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class LstPlanningOrg extends JFrame {

	private JPanel contentPane;
	private JDateChooser dateDebut;
	private JDateChooser dateFin;
	private JButton btnRetour;
	private JButton btnValider;
	private JLabel lblError;
	private JLabel lblPrixTot;
	private JTable table = new JTable();
	
	/**
	 * Create the frame.
	 */
	public LstPlanningOrg(Organizer orga) {
		Booking reserv = new Booking();
		reserv.setPlanning(new Planning());
		setTitle("Bosquet Wallon - Planning");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[25%,grow][25%][25%][25%]", "[][65%,grow,fill][][][][][]"));
		
		JLabel lblTitre = new JLabel("Liste des Planning déjà utilisés");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblTitre, "cell 0 0 4 1,growx,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1 4 1,grow");
		
		Planning p = new Planning();
		ArrayList<Planning> lstP = p.getAll();
		
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		String header[] = new String[] {"", "Date de début", "Date de fin"};
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		if(!lstP.isEmpty()) {
			TableColumnModel tcm = table.getColumnModel();       
			tcm.getColumn(0).setMaxWidth(1);
		}
		// Remplissage du tableau
		Iterator<Planning> iter = lstP.iterator();
		while(iter.hasNext()) {
			Planning pla = iter.next();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			String strd = formatter.format(pla.getBeginDate().getTime());
			String strf = formatter.format(pla.getEndDate().getTime());
			
			dtm.addRow(new Object[] {pla.getId(), strd, strf});
		}
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		JLabel lbl1 = new JLabel("Faire une r\u00E9servation");
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lbl1, "cell 0 2,alignx left,aligny bottom");
		
		JLabel lbl2 = new JLabel("La réservation se fait de midi à midi le lendemain.");
		lbl2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(lbl2, "cell 1 2 2 1,alignx left,aligny bottom");
		
		JLabel lbl3 = new JLabel("Tarifs : 3000€ / jour");
		contentPane.add(lbl3, "cell 0 3");
		
		JLabel lbl4 = new JLabel("Vendredi/Samedi : 4500€");
		contentPane.add(lbl4, "cell 1 3");
		
		JLabel lbl8 = new JLabel("Garantie en cas de d\u00E9g\u00E2ts : 4000\u20AC");
		contentPane.add(lbl8, "cell 2 3 2 1");
		
		JLabel lbl5 = new JLabel("Date de d\u00E9but");
		contentPane.add(lbl5, "cell 0 4");

		JLabel lbl6 = new JLabel("Date de fin");
		contentPane.add(lbl6, "cell 1 4");
		
		JLabel lbl7 = new JLabel("Prix total");
		contentPane.add(lbl7, "cell 2 4");
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		contentPane.add(lblError, "cell 0 6 4 1");

		dateDebut = new JDateChooser();
		dateDebut.setMinSelectableDate(Calendar.getInstance().getTime());
		dateDebut.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt)
			{
				lblError.setText("");
				if (evt.getPropertyName().equals("date"))
				{
					Date d = (Date) evt.getNewValue();
					Calendar cdebut = Calendar.getInstance();
					cdebut.setTime(d);
					int valide = reserv.getPlanning().verifyAvailable(cdebut);
					
					switch(valide) {
					case -1 : 
						lblError.setText("La date sélectionnée est déjà utilisée");
						break;
					case 0 :
						lblError.setText("La date sélectionnée est déjà passée");
						break;
					default :
						lblError.setText("");
						reserv.getPlanning().setBeginDate(cdebut.getTime());
						dateFin.setMinSelectableDate(dateDebut.getDate());
					    Calendar cfin = dateFin.getCalendar();
					    if(cdebut.after(cfin)||cfin==null) {
					    	cdebut.add(Calendar.DATE, 1);
					    	dateFin.setDate(cdebut.getTime());
					    } else {
					    	reserv.getPlanning().setEndDate(cfin.getTime());
					    	reserv.calculPrice();
					        reserv.calculBalance();
						    lblPrixTot.setText(reserv.getBalance()+ " €");
					    }
					}
				}
			}
	    });
		contentPane.add(dateDebut, "cell 0 5,grow");
		
		dateFin = new JDateChooser();
		dateFin.getDateEditor().addPropertyChangeListener(new PropertyChangeListener()
	    {
			@Override
			public void propertyChange(PropertyChangeEvent evt)
			{
				lblError.setText("");
				if (evt.getPropertyName().equals("date"))
			    {
			    	Date d = (Date) evt.getNewValue();
			    	Calendar cfin = Calendar.getInstance();
			    	cfin.setTime(d);
			    	int valide = reserv.getPlanning().verifyAvailable(cfin);
					
					switch(valide) {
					case -1 : 
						lblError.setText("La date sélectionnée est déjà utilisée");
						break;
					case 0 :
						lblError.setText("La date sélectionnée est déjà passée");
						break;
					default :
				    	reserv.getPlanning().setEndDate(cfin.getTime());
				        reserv.calculPrice();
				        reserv.calculBalance();
					    lblPrixTot.setText(reserv.getBalance()+ " €");
					}
			    }
			}
	    });
		contentPane.add(dateFin, "cell 1 5,grow");
		
		lblPrixTot = new JLabel("");
		lblPrixTot.setFont(new Font("Tahoma", Font.ITALIC, 14));
		contentPane.add(lblPrixTot, "cell 2 5,growx,aligny center");
		
		btnValider = new JButton("R\u00E9server");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dateDebut.getDate()!=null && dateFin.getDate()!=null) {
					reserv.setStatus("Disponible");
					if(orga.createBooking(reserv)) {
							lblError.setText("Réservation enregistrée");
							SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
							String strd = formatter.format(reserv.getPlanning().getBeginDate().getTime());
							String strf = formatter.format(reserv.getPlanning().getEndDate().getTime());
							dtm.addRow(new Object[] {reserv.getPlanning().getId(), strd, strf});
							btnValider.setEnabled(false);
					} else lblError.setText("Erreur lors de la création de la réservation");
				} else lblError.setText("Veuillez sélectionner les dates de la réservation");
			}
		});
		contentPane.add(btnValider, "cell 3 4,growx,aligny center");
		
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccueilOrg win = new AccueilOrg(orga);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnRetour, "cell 3 5,growx,aligny center");
	}
}
