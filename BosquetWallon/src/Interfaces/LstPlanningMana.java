package Interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import POJO.Booking;
import POJO.Manager;
import POJO.Planning;
import net.miginfocom.swing.MigLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

@SuppressWarnings("serial")
public class LstPlanningMana extends JFrame {

	private JPanel contentPane;
	private JButton btnRetour;
	private JLabel lblError;
	private JTable table = new JTable();
	
	/**
	 * Create the frame.
	 */
	public LstPlanningMana(Manager mana) {
		Booking reserv = new Booking();
		reserv.setPlanning(new Planning());
		setTitle("Bosquet Wallon - Planning");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[25%,grow][25%][25%][25%]", "[][65%,grow,fill][][][][][]"));
		
		JLabel lblTitre = new JLabel("Liste des Planning utilisés");
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
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		contentPane.add(lblError, "cell 0 6 4 1");
		
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccueilMana win = new AccueilMana(mana);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnRetour, "cell 3 5,growx,aligny center");
	}
}
