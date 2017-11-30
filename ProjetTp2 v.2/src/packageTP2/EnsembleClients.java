package packageTP2;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class EnsembleClients 
{
	
// Tous les clients de tous les magasins seront enregistr�s dans cette Hashtable
// Tous les m�thodes et les attributs sont statiques, parce qu'on ne cr�e pas d'objets 
	// de cette classe
// La carte Boni (BusMiles) est obligatoires pour les les clients des magasins Ames.
	// le num�ro de la carte sera de cl� pour la Hashtable "listeClients"
	
private static Hashtable<String, Client> listeClients = new Hashtable<String,Client>();
 
  // retourle la Hashtable listeClients
  public static Hashtable<String, Client> getListe()
  {
  return listeClients;
  }

  // retourne un objet Client en passant en param�tre le String correspondant au num�ro
  	// de la carte boni
  public static Client getClient ( String noCarteBoni )
  {
  return listeClients.get(noCarteBoni);
  }

  // ajoute un client � la Hashtable "listeClients", en passant un objet Client en param�tre.
  
  public static void ajouterClient ( Client c)
  {
	  // la String qui servira de la cl� est obtenu par l'objet Client c pass� en param�tre.
  listeClients.put(c.getNoCarte(), c);
  }
  
	static public void ecritureFichierClients() throws InvalidFormatException, IOException
	{
		// l'�criture va en fait recr�er le fichier excel et �craser celui qui existait d�j�
		
		//System.out.println("en mode �criture");
		// ouverture du fichier et �criture
		// InputStream inp = new FileInputStream ( "Clients2.xlsx"); // pour lire ou �crire dans un fichier
		OutputStream out = new FileOutputStream ( "Clients.xlsx");
      XSSFWorkbook classeur = new XSSFWorkbook();
		XSSFSheet feuille = classeur.createSheet("Feuil1");
		XSSFRow rangee;
		XSSFCell cellule;
		
		
		// cr�er des cellules
		int taille = EnsembleClients.getListe().size() + 1; // + 1 pour la premiere rang�e qui est le nom des colonnes
		// cr�er les rang�es
		for (int r=0;r < taille; r++ )
		{
			rangee = feuille.createRow(r);
	
			//cr�er des colonnes
			for (int c=0;c < 3; c++ )
			{
				cellule = rangee.createCell(c);
				
				// cellule.setCellValue("2");
			}
		}
		
		// �crire la premiere rangee: titre des colonnes
		rangee = feuille.getRow(0); // rang�e commencent � 0
		cellule  = rangee.getCell(0); // 1ere cellule 
		cellule.setCellValue("Num�ro de carte");
		
		cellule  = rangee.getCell(1); // 2ere cellule 
		cellule.setCellValue("Nom Client");
		cellule  = rangee.getCell(2); // 3ere cellule 
		cellule.setCellValue("Nombre de points bonis");
		
		
		int compteur = 1; // on commence a ecrire a la 2e rangee
		for (String key: EnsembleClients.getListe().keySet())
		{
			// chercher les attributs du client
			Client c = EnsembleClients.getClient(key);
			// String noCarteBoni, String nom, int nbPointsAcc)
			double noCarteBoni = Double.parseDouble(c.getNoCarte());
			String nom = c.getNom();
			double nbPointsAcc = c.getNbPointsAcc();
			
			// parcourir les rangees
			rangee = feuille.getRow(compteur); // rang�e commencent � 0
			
			// les placer dans une ligne du classeur Excel
			cellule  = rangee.getCell(0); // 1ere cellule 
			cellule.setCellValue(noCarteBoni);
			
			cellule  = rangee.getCell(1); // 1ere cellule 
			cellule.setCellValue(nom);
			cellule  = rangee.getCell(2); // 1ere cellule 
			cellule.setCellValue(nbPointsAcc);
			
			// passer � la ligne suivante
			compteur++;
		}
		classeur.write(out);  
		out.flush();	
      out.close();
     
      // inp.close();
	}
	
	static public void lectureFichierClients() throws IOException, Exception
	{
		InputStream inp = new FileInputStream ( "Clients.xlsx"); // pour lire ou �crire dans un fichier
		XSSFWorkbook classeur = ( XSSFWorkbook ) WorkbookFactory.create(inp);
		XSSFSheet feuille = classeur.getSheetAt(0); // feuille commence a 0
		XSSFRow rangee = feuille.getRow(0); // rang�e commencent � 0
		rangee.getCell(0);
		XSSFCell cellule  = rangee.getCell(0); // 1ere cellule 
		
		// String noCarteBoni, String nom, int nbPointsAcc
		// compteur pour la ligne qu'on veut lire
		int compteur = 1; // on commence la lecture � l'index 1 (deuxi�me ligne)
		boolean erreur = false;
		do
		{
			rangee = feuille.getRow(compteur); // rang�e commencent � 0
					

			// java.lang.IllegalStateException
			
			try {
				cellule = rangee.getCell(0);
			} catch (NullPointerException e)
			{
				erreur = true;
				//e.printStackTrace();
			} 
			/*
			catch (IllegalStateException e)
			{
				//e.printStackTrace();
				erreur = true;
			}
			*/
			
			if (erreur) // s'il y a une erreur de lecture, c'est qu'on a lu tous les clients
			{
				break;
			}
			else
			{
				// on enregistre les trois attributs du client dans 3 variables	
				String noCarteBoni = String.valueOf((int)cellule.getNumericCellValue());
				//System.out.println(noCarteBoni);
					
				cellule = rangee.getCell(1);
				String nom = cellule.getStringCellValue();
				//System.out.println(nom);
				
				cellule = rangee.getCell(2);
				int nbPointsAcc = (int) cellule.getNumericCellValue();
				//System.out.println(nbPointsAcc);
				
				// on cr�e un client avec les variables lues
				Client c = new Client(noCarteBoni, nom, nbPointsAcc);
				// ajouter le client � la liste de clients
				EnsembleClients.ajouterClient(c);	
				
				compteur++; // pour lire la prochaine ligne
			}
		} while(true); 		
		inp.close();		
	}

}