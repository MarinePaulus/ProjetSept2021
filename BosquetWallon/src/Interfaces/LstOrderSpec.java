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

import POJO.Spectator;
import POJO.Order;
import net.miginfocom.swing.MigLayout;
import java.awt.Color;

@SuppressWarnings("serial")
public class LstOrderSpec extends JFrame {

	private JPanel contentPane;
	private JTable table = new JTable();
	private JButton btnRetour;
	private JLabel lblError;

	/**
	 * Create the frame.
	 */
	public LstOrderSpec(Spectator spec) {
		setTitle("Bosquet Wallon - Commandes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[25%,fill][25%,fill][25%,fill][25%,fill]", "[][][][]"));
		
		JLabel lblTitre = new JLabel("Liste des commandes faites");
		lblTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitre.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblTitre, "cell 0 0 4 1,growx,aligny top");
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, "cell 0 1 4 1,grow");
		
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		String header[] = new String[] {"", "Mode de Payement", "Mode de livraison", "total"};
		dtm.setColumnIdentifiers(header);
		table.setModel(dtm);
		if(!spec.getOrderList().isEmpty()) {
			TableColumnModel tcm = table.getColumnModel();       
			tcm.getColumn(0).setMaxWidth(1);
		}
		// Remplissage du tableau
		Iterator<Order> iter = spec.getOrderList().iterator();
		while(iter.hasNext()) {
			Order c = iter.next();
			dtm.addRow(new Object[] {c.getId(), c.getPaymentMethod(), c.getDeliveryMethod(), c.getTotal()});
		}
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccueilSpect win = new AccueilSpect(spec);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnRetour, "cell 1 2 2 1");
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		contentPane.add(lblError, "cell 0 3 4 1,alignx right,aligny bottom");
	}

}
