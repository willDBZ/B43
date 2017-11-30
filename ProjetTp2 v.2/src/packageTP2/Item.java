
package packageTP2;

public class Item 
{
// attributs sur l'item
// chaque fois que le client magasine, un num�ro de commande est associ� � ses achats.
// la quantit� est enregistr�e dans cette classe pour que "type" de produit command�
private String nomProduit;
private int qteDemandee;
private int numeroCommandeAssociee;

// constructeur
  public Item(String nomProduit, int qteDemandee, int numeroCommandeAssociee)
  {
  this.nomProduit = nomProduit;
  this.qteDemandee = qteDemandee;
  this.numeroCommandeAssociee = numeroCommandeAssociee;
  }

  //m�thodes accesseurs 
  public String getNomProduit()
  {
  return nomProduit;
  }
  public int getQte()
  {
  return qteDemandee;
  }
  public int getNumeroCommandeAssociee()
  {
  return numeroCommandeAssociee;
  }
  
  // m�thode qui permet d'obtenir l'objet Produit en question, en fontion du string qui repr�sente le nom du produit
  // cela permet d'acc�der indirectement par exemple au prix du produit et autres attributs
  public Produit getProduit()
  {
  return Inventaire.getProduit(nomProduit);
  }
  
  // m�thode qui retour une string avec les informations sur l'item, utilis�e pour l'affichage
  public String afficherItem ()
  {
  return this.nomProduit+"\r"+this.qteDemandee+"\r"+getProduit().getPrix()*this.qteDemandee; // nom --- quantit� command�e --- prix total pour l'item
  }
  

  
}