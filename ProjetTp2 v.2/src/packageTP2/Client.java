package packageTP2;

public class Client 
{

private String noCarteBoni;
private String nom;
private int nbPointsAcc;


  public Client(String noCarteBoni, String nom, int nbPointsAcc)
  {
  this.noCarteBoni=noCarteBoni; // numéro de la carte boni du client. Chaque client doit se faire cette carte pour magasiner au magasin. 
  this.nbPointsAcc=nbPointsAcc; // nombre de points accumulés.
  this.nom = nom; // nom du client
  
  }

  // méthodes accesseurs qui retournent les attributs du client
  public String getNoCarte()
  {
  return noCarteBoni;
  }
  
  public String getNom()
  {
  return nom;
  }
  
  public int getNbPointsAcc()
  {
  return nbPointsAcc;
  }
  
  public void modifierPoints ( int nbPointsSupp )
  {
  this.nbPointsAcc+= nbPointsSupp;
  }

 // vérifier si le client a assez d'argent. 
 // on passe en paramètre un objet commande et le montant d'argent que le client utilise pour payer
  public boolean assezArgent ( Commande c, double montant )
  {
   double total = c.calculerGrandTotal(); // la méthode calculerGrandTotal, qui retourne le montant que le client doit payer
   // assez d'argent
   if ( montant  >= total )
    return true;
   // pas assez d'argent
  else 
    return false;
  }
  
  // méthode qui effectue la transaction
  // en passant en paramètre la commande et le montant payé
  public double paieCommande ( Commande c, double montant )
  {
  double total = c.calculerGrandTotal(); // la méthode calculerGrandTotal, qui retourne le montant que le client doit payer
  double change = montant- total;
  int nbPoints = c.calculerPointsBonis(); // nombre de points en fonction des items achetés
  if ( change > 0) // si le montant payé est plus grand que le montant nécessaire
      modifierPoints(nbPoints); // ajouter les points à la banque de points du client 

  return change; // retourne le change au client
  }
  
}