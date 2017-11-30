package packageTP2;

public class Client 
{

private String noCarteBoni;
private String nom;
private int nbPointsAcc;


  public Client(String noCarteBoni, String nom, int nbPointsAcc)
  {
  this.noCarteBoni=noCarteBoni; // num�ro de la carte boni du client. Chaque client doit se faire cette carte pour magasiner au magasin. 
  this.nbPointsAcc=nbPointsAcc; // nombre de points accumul�s.
  this.nom = nom; // nom du client
  
  }

  // m�thodes accesseurs qui retournent les attributs du client
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

 // v�rifier si le client a assez d'argent. 
 // on passe en param�tre un objet commande et le montant d'argent que le client utilise pour payer
  public boolean assezArgent ( Commande c, double montant )
  {
   double total = c.calculerGrandTotal(); // la m�thode calculerGrandTotal, qui retourne le montant que le client doit payer
   // assez d'argent
   if ( montant  >= total )
    return true;
   // pas assez d'argent
  else 
    return false;
  }
  
  // m�thode qui effectue la transaction
  // en passant en param�tre la commande et le montant pay�
  public double paieCommande ( Commande c, double montant )
  {
  double total = c.calculerGrandTotal(); // la m�thode calculerGrandTotal, qui retourne le montant que le client doit payer
  double change = montant- total;
  int nbPoints = c.calculerPointsBonis(); // nombre de points en fonction des items achet�s
  if ( change > 0) // si le montant pay� est plus grand que le montant n�cessaire
      modifierPoints(nbPoints); // ajouter les points � la banque de points du client 

  return change; // retourne le change au client
  }
  
}