package packageTP2;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MenuCaisse extends JFrame {
	private JPanel panel_identification;
	private JPanel panel_commande;
	private JPanel panel_boutons;
	private JPanel panel_transaction;
	private JLabel label_noBoni;
	private JLabel label_nomClient;
	private JLabel label_boni;
	private JTextField text_noBoni;
	private JTextField text_nomClient;
	private JTextField text_boni;
	private JLabel label_commande;
	private JLabel label_article;
	private JLabel label_prixUnitaire;
	private JLabel label_stock;
	private JTextField text_stock;
	private JComboBox combo_articles;
	private JTextField text_prixUnitaire_1;
	private JButton bouton_terminer;
	private JButton bouton_achat;
	private JScrollPane scrollPane_achats;
	private JTable table;
	private JLabel label_montantDonne;
	private JLabel label_montantRequis;
	private JTextField text_montantDonne;
	private JTextField text_montantRequis;
	private JButton bouton_nouvCommande;
	private JMenuBar menuBar;
	// mes écouteurs 
	private Ecouteur ec;
	private JMenu menuOptions;
	private JMenuItem menuNouveauClient;
	private JMenuItem menuFermetureDeSession;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuCaisse frame = new MenuCaisse();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				// ouverture du fichier excel		
				try {
					EnsembleClients.lectureFichierClients();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("Échec de lecture --- Fichier non trouvée");
					// e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("Échec de lecture --- Fichier endommagé");
					e.printStackTrace();
				}
				
				
				/*
				try {
					 ecritureFichierClients();
				} catch (InvalidFormatException | IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Écriture pas fonctionnée");
				}
				*/
				
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuCaisse() {
		setTitle("Caisse");
		setBounds(100, 100, 566, 571);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel_identification = new JPanel();
		panel_identification.setBackground(new Color(0, 204, 51));
		panel_identification.setBounds(10, 11, 530, 128);
		getContentPane().add(panel_identification);
		panel_identification.setLayout(null);
		
		label_noBoni = new JLabel("Num\u00E9ro de la carte Boni");
		label_noBoni.setFont(new Font("Tahoma", Font.BOLD, 22));
		label_noBoni.setBounds(48, 27, 292, 17);
		panel_identification.add(label_noBoni);
		
		label_nomClient = new JLabel("Nom du client:");
		label_nomClient.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_nomClient.setBounds(48, 55, 165, 36);
		panel_identification.add(label_nomClient);
		
		label_boni = new JLabel("Nombre de points bonis \u00E0 ce jour:");
		label_boni.setFont(new Font("Tahoma", Font.PLAIN, 17));
		label_boni.setBounds(48, 84, 266, 42);
		panel_identification.add(label_boni);
		
		text_noBoni = new JTextField();
		text_noBoni.setBounds(374, 30, 86, 20);
		panel_identification.add(text_noBoni);
		text_noBoni.setColumns(10);
		
		text_nomClient = new JTextField();
		text_nomClient.setColumns(10);
		text_nomClient.setBounds(374, 66, 86, 20);
		panel_identification.add(text_nomClient);
		text_nomClient.setEditable(false);// champ non éditable
		
		text_boni = new JTextField();
		text_boni.setColumns(10);
		text_boni.setBounds(374, 98, 86, 20);
		panel_identification.add(text_boni);
		text_boni.setEditable(false);// champ non éditable
		
		
		panel_commande = new JPanel();
		panel_commande.setBackground(new Color(255, 153, 51));
		panel_commande.setLayout(null);
		panel_commande.setBounds(10, 150, 394, 128);
		getContentPane().add(panel_commande);
		
		label_commande = new JLabel("COMMANDE");
		label_commande.setBounds(10, 11, 77, 14);
		panel_commande.add(label_commande);
		
		label_article = new JLabel("Article");
		label_article.setBounds(10, 36, 77, 14);
		panel_commande.add(label_article);
		
		label_prixUnitaire = new JLabel("Prix unitaire:");
		label_prixUnitaire.setBounds(10, 82, 77, 14);
		panel_commande.add(label_prixUnitaire);
		
		label_stock = new JLabel("Quantit\u00E9 en stock:");
		label_stock.setBounds(255, 82, 118, 14);
		panel_commande.add(label_stock);
		
		text_stock = new JTextField();
		text_stock.setColumns(10);
		text_stock.setBounds(255, 97, 129, 20);
		panel_commande.add(text_stock);
		
		combo_articles = new JComboBox();
		combo_articles.setBounds(97, 33, 287, 20);
		panel_commande.add(combo_articles);
		
		text_prixUnitaire_1 = new JTextField();
		text_prixUnitaire_1.setColumns(10);
		text_prixUnitaire_1.setBounds(10, 97, 129, 20);
		panel_commande.add(text_prixUnitaire_1);
		
		panel_boutons = new JPanel();
		panel_boutons.setBounds(414, 150, 126, 128);
		getContentPane().add(panel_boutons);
		panel_boutons.setLayout(new GridLayout(0, 1, 0, 0));
		
		bouton_achat = new JButton("Achat");
		panel_boutons.add(bouton_achat);
		
		bouton_terminer = new JButton("Terminer");
		panel_boutons.add(bouton_terminer);
		
		panel_transaction = new JPanel();
		panel_transaction.setBackground(new Color(204, 0, 0));
		panel_transaction.setLayout(null);
		panel_transaction.setBounds(10, 289, 530, 207);
		getContentPane().add(panel_transaction);
		
		scrollPane_achats = new JScrollPane();
		scrollPane_achats.setBounds(10, 11, 510, 113);
		panel_transaction.add(scrollPane_achats);
		
		table = new JTable();
		scrollPane_achats.setViewportView(table);
		
		label_montantDonne = new JLabel("Montant donn\u00E9:");
		label_montantDonne.setBounds(10, 147, 92, 14);
		panel_transaction.add(label_montantDonne);
		
		label_montantRequis = new JLabel("Montant requis:");
		label_montantRequis.setBounds(10, 177, 92, 14);
		panel_transaction.add(label_montantRequis);
		
		text_montantDonne = new JTextField();
		text_montantDonne.setColumns(10);
		text_montantDonne.setBounds(123, 144, 129, 20);
		panel_transaction.add(text_montantDonne);
		
		text_montantRequis = new JTextField();
		text_montantRequis.setColumns(10);
		text_montantRequis.setBounds(123, 174, 129, 20);
		panel_transaction.add(text_montantRequis);
		
		bouton_nouvCommande = new JButton("Nouvelle commande");
		bouton_nouvCommande.setBounds(368, 135, 152, 64);
		panel_transaction.add(bouton_nouvCommande);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		menuOptions = new JMenu("Options");
		menuBar.add(menuOptions);
		
		
		
		menuNouveauClient = new JMenuItem("Nouveau client");
		menuOptions.add(menuNouveauClient);
		
		
		menuFermetureDeSession = new JMenuItem("Fermeture de session");
		menuOptions.add(menuFermetureDeSession);
		
		// menuBar.setVisible(true);
		this.setJMenuBar(menuBar);
		
		
		
		// tests pour l'inventaire
		// int code, String nom,int qteStock, double prix, int points
		Inventaire.ajouterProduit(new Produit(4404, "Feuilles", 55, 11.50, 50));
		Inventaire.ajouterProduit(new Produit(4404, "Feuilles1", 55, 11.50, 50));
		Inventaire.ajouterProduit(new Produit(4404, "Feuilles2", 55, 11.50, 50));
		Inventaire.ajouterProduit(new Produit(4404, "Feuilles3", 55, 11.50, 50));
		Inventaire.ajouterProduit(new Produit(4404, "Feuilles4", 55, 11.50, 50));
		
		//System.out.println(Inventaire.getProduit("Feuilles2"));
		//System.out.println(Inventaire.getProduit("Feuilles3"));
		
		// écouteurs
		ec = new Ecouteur();
		text_noBoni.addActionListener(ec);
		menuNouveauClient.addActionListener(ec);
		
		// menu bar
		/*
		menubar = new JMenuBar();
		JMenu menuOption = new JMenu("Option");
		JMenuItem menuAjoutClient = new JMenuItem("Nouveau client");
		//menuOption.add(menuAjoutClient);
		menubar.add(menuOption);
		setJMenuBar(menubar);
		*/
		
	}

	private class Ecouteur implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == text_noBoni)
			{
				remplirInfosClients();
			}
			else if(e.getSource() == menuNouveauClient)
			{
				DialogNouveauClient dialog = new DialogNouveauClient(MenuCaisse.this);
				dialog.setVisible(true);
				JOptionPane.showMessageDialog(MenuCaisse.this, "Nouveau client ajouté!");
				
			}
		}	
	}
	
	public void setTextNoBoni(String numero)
	{
		this.text_noBoni.setText(numero);
	}
	
	public void remplirInfosClients()
	{
		String noCarteBoni = text_noBoni.getText();
		try
		{	
			Client c = EnsembleClients.getClient(noCarteBoni); // la fonction getClient retourne le client correspondant à la clé "noCarteBoni"
			
			
			// on affecte les valeurs du client trouvé pour les labels du nom et des points bonis
			
			text_nomClient.setText(c.getNom());
			text_boni.setText(String.valueOf(c.getNbPointsAcc()));					
		}
		catch (NullPointerException npe)
		{
			JOptionPane.showMessageDialog(MenuCaisse.this, "Entrez un numéro existant!");
			text_noBoni.setText("");
		}		
		
	}
}
