package Interfaces;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import POJO.Configuration;
import POJO.Organizer;
import POJO.Planning;
import POJO.Show;
import net.miginfocom.swing.MigLayout;

@SuppressWarnings("serial")
public class ModifShowOrg extends JFrame {


	private JPanel contentPane;
	private JButton btnRetour;
	private JButton btnEnreg;
	private JLabel lblTitre;
	private JLabel lblSTitre;
	private JLabel lblNbPlace;
	private JTextField txtTitre;
	private JSpinner spinNbPlace;
	private JLabel lblConfig;
	private JButton btnLstArtistes;
	private JButton btnLstRepr;
	private JPanel panel;
	private JLabel lblCategorie;
	private JLabel lbl1;
	private JLabel lbl2;
	private JLabel lbl3;
	private JLabel lbl4;
	private JTextField txt1;
	private JTextField txt2;
	private JTextField txt3;
	private JTextField txt4;
	final DefaultComboBoxModel<Configuration> model = new DefaultComboBoxModel<Configuration>();
	private JComboBox<Configuration> comboBox;
	private JLabel lblError;
	Configuration conf;
	private JLabel lblDescription;
	private JTextField txtDescription;

	/**
	 * Create the frame.
	 */
	public ModifShowOrg(Organizer orga, Planning pla, Show spec) {
		spec.getConfig().setCategoryList();
		spec.setCatPrice();

		setTitle("Bosquet Wallon - Spectacles");
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[25%,fill][25%,fill][12.5%,grow,fill][12.5%,fill][25%,fill][25%,fill]", "[][][][][][][50%,grow][][][]"));
		
		lblSTitre = new JLabel("Modifier Spectacle");
		lblSTitre.setHorizontalAlignment(SwingConstants.CENTER);
		lblSTitre.setFont(new Font("Tahoma", Font.BOLD, 16));
		contentPane.add(lblSTitre, "cell 0 0 6 1,alignx center,aligny center");

		lblTitre = new JLabel("Titre");
		contentPane.add(lblTitre, "cell 1 1,alignx trailing");
		
		txtTitre = new JTextField();
		txtTitre.setText(spec.getTitle());
		contentPane.add(txtTitre, "cell 2 1 3 1,growx");
		
		lblDescription = new JLabel("Description");
		contentPane.add(lblDescription, "cell 1 2,alignx trailing");
		
		txtDescription = new JTextField();
		txtDescription.setText(spec.getDescription());
		contentPane.add(txtDescription, "cell 2 2 3 1,growx");
		
		lblNbPlace = new JLabel("Nb place maximum");
		contentPane.add(lblNbPlace, "cell 1 4,alignx trailing");
		
		spinNbPlace = new JSpinner();
		spinNbPlace.setModel(new SpinnerNumberModel(0, 0, null, 1));
		spinNbPlace.setValue(Integer.parseInt(spec.getTicketPerPerson()));
		contentPane.add(spinNbPlace, "cell 2 4 3 1,alignx left");
		
		lblConfig = new JLabel("Configuration");
		contentPane.add(lblConfig, "cell 1 5");

		panel = new JPanel();
		panel.setBorder(new LineBorder(Color.GRAY));
		contentPane.add(panel, "cell 1 6 4 1,growx,aligny top");
		panel.setLayout(new MigLayout("", "[25%,fill][25%,grow,fill][25%,fill][25%,fill]", "[fill][fill][fill]"));
		
		lblCategorie = new JLabel("Prix des cat\u00E9gories");
		lblCategorie.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblCategorie, "cell 0 0 4 1");
		
		lbl1 = new JLabel("");
		panel.add(lbl1, "cell 0 1,alignx trailing");
		
		txt1 = new JTextField();
		txt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                //le caractère est numérique
                if (!(c >= '0' && c <= '9')) {
                	if(c==',' || c=='.') evt.setKeyChar('.');
                	else evt.consume();
                }
            }
        });
		panel.add(txt1, "cell 1 1,growx");
		txt1.setColumns(10);
		
		lbl2 = new JLabel("");
		panel.add(lbl2, "cell 0 2,alignx trailing");
		
		txt2 = new JTextField();
		txt2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                //le caractère est numérique
                if (!(c >= '0' && c <= '9')) {
                	if(c==',' || c=='.') evt.setKeyChar('.');
                	else evt.consume();
                }
            }
        });
		panel.add(txt2, "cell 1 2,growx");
		txt2.setColumns(10);
		
		lbl3 = new JLabel("");
		panel.add(lbl3, "cell 2 1,alignx trailing");
		
		txt3 = new JTextField();
		txt3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                //le caractère est numérique
                if (!(c >= '0' && c <= '9')) {
                	if(c==',' || c=='.') evt.setKeyChar('.');
                	else evt.consume();
                }
            }
        });
		panel.add(txt3, "cell 3 1,growx");
		txt3.setColumns(10);
		
		lbl4 = new JLabel("");
		panel.add(lbl4, "cell 2 2,alignx trailing");
		
		txt4 = new JTextField();
		txt4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(KeyEvent evt) {
                char c = evt.getKeyChar();
                //le caractère est numérique
                if (!(c >= '0' && c <= '9')) {
                	if(c==',' || c=='.') evt.setKeyChar('.');
                	else evt.consume();
                }
            }
        });
		panel.add(txt4, "cell 3 2,growx");
		txt4.setColumns(10);
		
		Configuration c = new Configuration();
		List<Configuration> lstc = c.getAll();
		model.addAll(lstc);
		
		comboBox = new JComboBox<Configuration>();
		comboBox.setModel(model);
		comboBox.setSelectedIndex(spec.getConfig().getId()-1);
		AffichePanel(spec.getConfig());
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				conf = (Configuration) comboBox.getSelectedItem();
				conf.setCategoryList();
				AffichePanel(conf);
				spec.setConfig(conf);
			}
		});
		contentPane.add(comboBox, "cell 2 5 3 1,alignx left");

		btnEnreg = new JButton("Enregistrer");
		btnEnreg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblError.setText("");
				spec.setTitle(txtTitre.getText());
				spec.setDescription(txtDescription.getText());
				spec.setTicketPerPerson(spinNbPlace.getValue().toString());
				if(conf==null) {
					conf = (Configuration) comboBox.getSelectedItem();
					conf.setCategoryList();
				}
				spec.setConfig(conf);
				// Création du spectacle
				if(spec.update()) {
					spec.delCatPrice();
					spec.getConfig().setCategoryList();
					spec.getConfig().getCategoryList().get(0).setPrice(Float.parseFloat(txt1.getText()));
					if(spec.getConfig().getType().equalsIgnoreCase("Assis Concert")) {
						spec.getConfig().getCategoryList().get(1).setPrice(Float.parseFloat(txt2.getText()));
						spec.getConfig().getCategoryList().get(2).setPrice(Float.parseFloat(txt3.getText()));
					}
					if(spec.getConfig().getType().equalsIgnoreCase("Assis Cirque")) {
						spec.getConfig().getCategoryList().get(1).setPrice(Float.parseFloat(txt2.getText()));
						spec.getConfig().getCategoryList().get(2).setPrice(Float.parseFloat(txt3.getText()));
						spec.getConfig().getCategoryList().get(3).setPrice(Float.parseFloat(txt4.getText()));
					}
					// Création des champs dans SpecCat
					if(spec.recCatPrice()) {
						lblError.setText("Spectacle enregistré");
					} else lblError.setText("Erreur lors de l'enregistrement des prix des places");
				} else lblError.setText("Erreur lors de la modification du spectacle");
			}
		});
		
		btnLstArtistes = new JButton("Artistes");
		btnLstArtistes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LstArtistsOrg win = new LstArtistsOrg(orga, pla, spec);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnLstArtistes, "cell 4 7");
		
		btnLstRepr = new JButton("Repr\u00E9sentations");
		btnLstRepr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LstRepreOrg win = new LstRepreOrg(orga, pla, spec);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnLstRepr, "cell 1 7 3 1");
		
		
		lblError = new JLabel("");
		lblError.setForeground(Color.RED);
		contentPane.add(lblError, "cell 1 9 4 1");
		contentPane.add(btnEnreg, "cell 1 8 3 1");
		
		btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LstShowOrg win = new LstShowOrg(orga, pla);
				win.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnRetour, "cell 4 8");
	}
	
	void AffichePanel(Configuration conf){
		switch(conf.getType()) {
			case "Debout" : 
				lbl1.setText(conf.getCategoryList().get(0).getType() + " - " + conf.getCategoryList().get(0).getMaximumTickets() + " places");
				txt1.setText(conf.getCategoryList().get(0).getPrice()+"");
				lbl2.setVisible(false);
				txt2.setVisible(false);
				lbl3.setVisible(false);
				txt3.setVisible(false);
				lbl4.setVisible(false);
				txt4.setVisible(false);
				break;
			case "Assis Concert" :
				lbl1.setText(conf.getCategoryList().get(0).getType() + " - " + conf.getCategoryList().get(0).getMaximumTickets() + " places");
				txt1.setText(conf.getCategoryList().get(0).getPrice()+"");
				lbl2.setText(conf.getCategoryList().get(1).getType() + " - " + conf.getCategoryList().get(1).getMaximumTickets() + " places");
				txt2.setText(conf.getCategoryList().get(1).getPrice()+"");
				lbl3.setText(conf.getCategoryList().get(2).getType() + " - " + conf.getCategoryList().get(2).getMaximumTickets() + " places");
				txt3.setText(conf.getCategoryList().get(2).getPrice()+"");
				lbl2.setVisible(true);
				txt2.setVisible(true);
				lbl3.setVisible(true);
				txt3.setVisible(true);
				lbl4.setVisible(false);
				txt4.setVisible(false);
				break;
			case "Assis Cirque" :
				lbl1.setText(conf.getCategoryList().get(0).getType() + " - " + conf.getCategoryList().get(0).getMaximumTickets() + " places");
				txt1.setText(conf.getCategoryList().get(0).getPrice()+"");
				lbl2.setText(conf.getCategoryList().get(1).getType() + " - " + conf.getCategoryList().get(1).getMaximumTickets() + " places");
				txt2.setText(conf.getCategoryList().get(1).getPrice()+"");
				lbl3.setText(conf.getCategoryList().get(2).getType() + " - " + conf.getCategoryList().get(2).getMaximumTickets() + " places");
				txt3.setText(conf.getCategoryList().get(2).getPrice()+"");
				lbl4.setText(conf.getCategoryList().get(3).getType() + " - " + conf.getCategoryList().get(3).getMaximumTickets() + " places");
				txt4.setText(conf.getCategoryList().get(3).getPrice()+"");
				lbl2.setVisible(true);
				txt2.setVisible(true);
				lbl3.setVisible(true);
				txt3.setVisible(true);
				lbl4.setVisible(true);
				txt4.setVisible(true);
				break;
		}
	}
}
