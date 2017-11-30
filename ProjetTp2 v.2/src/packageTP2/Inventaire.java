package packageTP2;
import java.util.*;

public class Inventaire 
// Tous les magasins utilisent le même inventaire. C'est pour ça que toutes les méthodes et les attributs sont statiques et publiques.
// on ne créera jamais d'objet inventaire. 
{
private static Hashtable<String, Produit> listeProduits = new Hashtable<String, Produit>();
  // table de hachage
  // clé = String, nom du produit
  // élment = un objet Produit (voir la classe Produit)

// méthode pour retourner l'ensemble de la hashtable
  public static Hashtable<String, Produit> getListe()
  {
  return listeProduits;
  }

  // méthode pour obtenir un objet Produit en passant en paramètre le nom du produit
  public static Produit getProduit ( String  nom )
  {
  return listeProduits.get(nom);
  }

  // méthode pour ajouter un produit dans la hashtable
  // va créer une entrée qui est un couple "nom du produit" + un objet Produit
  public static void ajouterProduit ( Produit p)
  {
  listeProduits.put(p.getNom(), p);
  }
  
  public static void lectureFichierProduits()
  {
	  
	  
  }
}