package packageTP2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DialogNouveauClient extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField textNomClient;
	private JTextField textNumeroCarte;
	private JButton btnOk;
	private JButton btnAnnuler;
	private Ecouteur ec;
	private String noClientGenere = ""; // contient un string avec le numero du client
	private MenuCaisse parent;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			//DialogNouveauClient dialog = new DialogNouveauClient();
			//dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			//dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogNouveauClient(MenuCaisse parent) {
		super(parent,"Avertissement", true);
		this.parent = parent;
		setBounds(100, 100, 291, 175);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		lblNewLabel = new JLabel("Nom du client:");
		lblNewLabel.setBounds(10, 22, 115, 14);
		contentPanel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Num\u00E9ro de carte boni g\u00E9n\u00E9r\u00E9");
		lblNewLabel_1.setBounds(10, 47, 166, 14);
		contentPanel.add(lblNewLabel_1);
		
		textNomClient = new JTextField();
		textNomClient.setBounds(98, 19, 166, 20);
		contentPanel.add(textNomClient);
		textNomClient.setColumns(10);
		
		textNumeroCarte = new JTextField();
		textNumeroCarte.setBounds(186, 44, 78, 20);
		contentPanel.add(textNumeroCarte);
		textNumeroCarte.setColumns(10);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(50, 72, 68, 46);
		contentPanel.add(btnOk);
		
		btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(149, 72, 68, 46);
		contentPanel.add(btnAnnuler);
		btnAnnuler.setMargin(new Insets(0, 0, 0, 0));
		
		// emp�cher la modification pour le champ du num�ro g�n�r� al�atoirement
		textNumeroCarte.setEditable(false);
		
		// g�n�rer un nombre al�atoire � 6 chiffres
		boolean trouve = false;
		
		while (!trouve)
		{
			int genere = (int)(Math.random()*1000000);
			noClientGenere = String.valueOf(genere);
			
			// EnsembleClients.getListe().keySet();
			try
			{
				Client c = EnsembleClients.getClient(noClientGenere);
				c.getNom(); // forcer l'erreur
			}
			catch (NullPointerException npe) // s'il y a une erreur, ca veut dire que le num�ro n'existe pas, donc c'est ce qu'on veut!
			{
				trouve = true;
			}
		}

		textNumeroCarte.setText(noClientGenere);
		
		// �couteur
		// �tape 1
		ec = new Ecouteur();
		// �tape 2
		btnOk.addActionListener(ec);
		this.btnAnnuler.addActionListener(ec);
	}
	
	private class Ecouteur implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			// v�rifier quel bouton a g�n�rer l'action
			if (e.getSource() == btnOk)
			{
				Client c = new Client(noClientGenere,textNomClient.getText().toString(),0 );
				EnsembleClients.ajouterClient(c);			
			}
			
			// passer directement le num�ro du nouveau client � l'autre fen�tre
			DialogNouveauClient.this.parent.setTextNoBoni(noClientGenere);
			DialogNouveauClient.this.parent.remplirInfosClients();
			
			// peu importe le choix, on d�truit la fen�tre JDialog
			setVisible(false); 
			dispose(); // d�truit le JDiloag
			
			
		}
		
		
	}
}
