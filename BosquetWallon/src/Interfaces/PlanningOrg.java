package Interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import POJO.Organizer;
import POJO.Booking;
import POJO.Planning;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class PlanningOrg extends JFrame {

	private JPanel contentPane;
	private JCalendar calendar;
	private JDateChooser dateDebut;
	private JDateChooser dateFin;
	private JButton btnRetour;
	private JButton btnValider;
	private JLabel lblError;
	private JLabel lblPrixTot;
	private JLabel lbl8;
	
	/**
	 * Create the frame.
	 */
	public PlanningOrg(Organizer orga) {
		Booking reserv = new Booking();
		reserv.setPlanning(new Planning());
		setTitle("Bosquet Wallon - Planning");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[25%][25%][25%][25%]", "[65%,fill][][][][][]"));
		
		calendar = new JCalendar();
		calendar.setMinSelectableDate(Calendar.getInstance().getTime());
		contentPane.add(calendar, "cell 0 0 4 1,grow");
		
		JLabel lbl1 = new JLabel("Faire une r\u00E9servation");
		lbl1.setFont(new Font("Tahoma", Font.BOLD, 12));
		contentPane.add(lbl1, "cell 0 1,alignx left,aligny bottom");
		
		JLabel lbl2 = new JLabel("La réservation se fait de midi à midi le lendemain.");
		lbl2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		contentPane.add(lbl2, "cell 1 1 2 1,alignx left,aligny bottom");
		
		JLabel lbl3 = new JLabel("Tarifs : 3000€ / jour");
		contentPane.add(lbl3, "cell 0 2");
		
		JLabel lbl4 = new JLabel("Vendredi/Samedi : 4500€");
		contentPane.add(lbl4, "cell 1 2");
		
		lbl8 = new JLabel("Garantie en cas de d\u00E9g\u00E2ts : 4000\u20AC");
		contentPane.add(lbl8, "cell 2 2 2 1");
		
		JLabel lbl5 = new JLabel("Date de d\u00E9but");
		contentPane.add(lbl5, "cell 0 3");

		JLabel lbl6 = new JLabel("Date de fin");
		contentPane.add(lbl6, "cell 1 3");
		
		JLabel lbl7 = new JLabel("Prix total");
		contentPane.add(lbl7, "cell 2 3");
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		contentPane.add(lblError, "cell 0 5 4 1");

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
		contentPane.add(dateDebut, "cell 0 4,grow");
		
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
		contentPane.add(dateFin, "cell 1 4,grow");
		
		lblPrixTot = new JLabel("");
		lblPrixTot.setFont(new Font("Tahoma", Font.ITALIC, 14));
		contentPane.add(lblPrixTot, "cell 2 4,growx,aligny center");
		
		btnValider = new JButton("R\u00E9server");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(dateDebut.getDate()!=null && dateFin.getDate()!=null) {
					reserv.setStatus("Disponible");
					// Création de la réservation
					if(reserv.create()) {
						Booking b = reserv.getOneNoID();
						orga.addBooking(b);
						// Renseignement de l'organisateur
						if(orga.recBookings()) {
							lblError.setText("Réservation enregistrée");
							btnValider.setEnabled(false);
						} else lblError.setText("Erreur lors de l'enregistrement de la réservation");
					} else lblError.setText("Erreur lors de la création de la réservation");
				} else lblError.setText("Veuillez sélectionner les dates de la réservation");
			}
		});
		contentPane.add(btnValider, "cell 3 3,growx,aligny center");
		
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccueilOrg win = new AccueilOrg(orga);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnRetour, "cell 3 4,growx,aligny center");
	}
}
