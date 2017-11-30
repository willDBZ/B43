package packageTP2;
import java.util.*;

public class Inventaire 
// Tous les magasins utilisent le m�me inventaire. C'est pour �a que toutes les m�thodes et les attributs sont statiques et publiques.
// on ne cr�era jamais d'objet inventaire. 
{
private static Hashtable<String, Produit> listeProduits = new Hashtable<String, Produit>();
  // table de hachage
  // cl� = String, nom du produit
  // �lment = un objet Produit (voir la classe Produit)

// m�thode pour retourner l'ensemble de la hashtable
  public static Hashtable<String, Produit> getListe()
  {
  return listeProduits;
  }

  // m�thode pour obtenir un objet Produit en passant en param�tre le nom du produit
  public static Produit getProduit ( String  nom )
  {
  return listeProduits.get(nom);
  }

  // m�thode pour ajouter un produit dans la hashtable
  // va cr�er une entr�e qui est un couple "nom du produit" + un objet Produit
  public static void ajouterProduit ( Produit p)
  {
  listeProduits.put(p.getNom(), p);
  }
  
  public static void lectureFichierProduits()
  {
	  
	  
  }
}