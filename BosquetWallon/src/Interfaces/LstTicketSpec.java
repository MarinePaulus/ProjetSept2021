package Interfaces;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import POJO.Ticket;
import POJO.Order;
import POJO.Spectator;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class LstTicketSpec extends JFrame {

	private JPanel contentPane;
	private JTable table = new JTable();
	private JButton btnRetour;

	/**
	 * Create the frame.
	 */
	public LstTicketSpec(Spectator spec, Order order) {
		setTitle("Bosquet Wallon - Tickets reçu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[25%,fill][25%,fill][25%,fill][25%,fill]", "[][][][]"));
		
		JLabel lblTitre = new JLabel("Liste des tickets de la commandes");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblTitre, "cell 0 0 4 1,growx,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1 4 1,grow");
		
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		String header[] = new String[] {"", "N° de place", "Prix", "Spectacle", "Représentation"};
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		if(!order.getTicketList().isEmpty()) {
			TableColumnModel tcm = table.getColumnModel();       
			tcm.getColumn(0).setMaxWidth(1);
		}
		// Remplissage du tableau
		Iterator<Ticket> iter = order.getTicketList().iterator();
		while(iter.hasNext()) {
			Ticket t = iter.next();
			dtm.addRow(new Object[] {t.getId(), t.getNumPlace(), t.getPrice(), t.getRepresentation().getShow().getTitle(), t.getRepresentation().getBeginHour() + " à " +t.getRepresentation().getEndHour() });
		}
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccueilSpec win = new AccueilSpec(spec);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnRetour, "cell 1 2 2 1");
	}
}
