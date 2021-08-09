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

import POJO.Spectator;
import POJO.Planning;
import POJO.Show;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class LstShowSpec extends JFrame {

	private JPanel contentPane;
	private JTable table = new JTable();
	private JButton btnRetour;
	private JButton btnCommander;

	/**
	 * Create the frame.
	 */
	public LstShowSpec(Spectator Spectator) {
		setTitle("Bosquet Wallon - Spectacles");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[20%,fill][20%,fill][20%,fill][20%,fill][20%,fill]", "[][80%][]"));
		
		Planning pla = new Planning();
		List<Planning> lstpla = pla.getAll();
		
		
		JLabel lblTitre = new JLabel("Liste des Spectacles organisés");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblTitre, "cell 0 0 5 1,growx,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1 5 1,grow");
		
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		String header[] = new String[] {"", "Date de début", "Date de fin", "Titre", "Config"}; 
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		if(!lstpla.isEmpty()) {
			TableColumnModel tcm = table.getColumnModel();       
			tcm.getColumn(0).setMaxWidth(0);
			tcm.getColumn(1).setMaxWidth(100);
			tcm.getColumn(1).setMinWidth(100);
			tcm.getColumn(2).setMaxWidth(100);
			tcm.getColumn(2).setMinWidth(100);
			tcm.getColumn(4).setMaxWidth(100);
			tcm.getColumn(4).setMinWidth(100);
			
			for(Planning p : lstpla) {
				List<Show> lstSpec= p.getShowList();
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
				String strd = formatter.format(p.getBeginDate().getTime());
				String strf = formatter.format(p.getEndDate().getTime());
				
				// Remplissage du tableau
				Iterator<Show> iter = lstSpec.iterator();
				while(iter.hasNext()) {
					Show s = iter.next();
					s.setConfig(s.getConfig().getOne());
					dtm.addRow(new Object[] { s.getId(), strd, strf, s.getTitle(), s.getConfig().getType()});
				}
				
			}
		}
		scrollPane.setViewportView(table);
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	      public void valueChanged(ListSelectionEvent e) {
	    	  if(table.getSelectedRow() > -1){
	    		  btnCommander.setEnabled(true);
	    	  }
	      }
	    });
		scrollPane.setViewportView(table);
		
		btnCommander = new JButton("Voir les représentations");
		btnCommander.setEnabled(false);
		btnCommander.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Show s = new Show();
				s.setId((int) table.getValueAt(table.getSelectedRow(), 0));
				s = s.getOne();
				CreaOrderSpec win = new CreaOrderSpec(Spectator, s);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnCommander, "cell 1 2");
		
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccueilSpec win = new AccueilSpec(Spectator);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnRetour, "cell 3 2");
	}
}
