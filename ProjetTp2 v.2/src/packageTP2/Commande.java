package packageTP2;
import java.util.*;
public class Commande 
{
// attributs de l'objet commande
private int numero;
private String noCarteBoniClient;
private Vector<Item> items;
private static int compteur; // compteur qui sera pour le num�ro de la commande. Ainsi, deux commandes auront n�cessairement deux num�ros diff�rents

// quand on cr�e un objet Commande, on passe en param�tre le num�ro de la carte du client
// constructeur
  public Commande(String noCarte)
  {
  this.numero=compteur++;
  this.noCarteBoniClient = noCarte;
  items = new Vector<Item>();
  }

// m�thodes accesseurs
  public int getNumero()
  {
  return numero;
  }
  public String getNoCarteBoniClient()
  {
  return noCarteBoniClient;
  }
  public Vector<Item> getItems()
  {
  return items;
  }
  
  // ajouter un item au vecteur d'items 
  public void ajouterItem ( Item i)
  {
  items.add(i);
  }
  
  // calcul du sous-total, avant les taxes
  public double calculerSousTotal ()
  {
  double total = 0;
  for ( int i = 0; i < items.size(); i++ ) // parcourir la liste d'items
    {
    Item item = items.get(i);
    int qte = item.getQte(); // obtenir la quantit� de produits achet� pour un item
    Produit p = Inventaire.getProduit(item.getNomProduit()); // Inventaire.getProduit() est une m�thode statique de la clase Inventaire, qui retourne un objet Produit � partir du String nom du produit 
    double prix = p.getPrix(); // obtenir le prix unitaire du produit
    total += qte * prix; // total avant les taxes
    }

  return total;
  }
  
  // calcul du TPS, qui est de 5% du total avant taxe
  public double calculerTPS ()
  {
    double total = calculerSousTotal();
    return total*0.05;
  }
    //� partir du 1er janvier 2013, la taxe de vente du Qu�bec est harmonis�e avec le r�gime de la TPS. 
    //Ainsi, la TVQ est calcul�e sur le prix excluant la TPS. Pour contrer les pertes de revenu encourues 
    //par le gouvernement qu�b�cois suite � cette modification, le taux de la TVQ a �t� port� � 9,975 %. 
    //Pour les consommateurs, la modification n'a aucun impact en terme de montant � d�bourser, 
    //puisque le taux combin� est identique � celui de 2012, soit 14,975 %
  
  //calcul du TVQ, qui est de 9,975% du total avant taxe
  public double calculerTVQ()
  {
    double total = calculerSousTotal();
    return total * 0.09975;
  }
  
  // grand total, ajouter au total avant taxes les deux taxes
  public double calculerGrandTotal()
  {
    return calculerSousTotal() + calculerTPS() + calculerTVQ();
  }
  
  public int calculerPointsBonis()
  {
  int total = 0;
  for ( int i = 0; i < items.size(); i++ )
    {
    Item item = items.get(i);
    int qte = item.getQte();
    Produit p = Inventaire.getProduit(item.getNomProduit());
    int points = p.getPoints(); // le nombre de point par unit� de produit
    total += qte * points;  // ajouter le nombre de points accumul�s pour l'achat de l'item au nombre de points totaux de la commande
    }

  return total;
  }
 
 
}