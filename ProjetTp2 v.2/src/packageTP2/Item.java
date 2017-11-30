
package packageTP2;

public class Item 
{
// attributs sur l'item
// chaque fois que le client magasine, un numéro de commande est associé à ses achats.
// la quantité est enregistrée dans cette classe pour que "type" de produit commandé
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

  //méthodes accesseurs 
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
  
  // méthode qui permet d'obtenir l'objet Produit en question, en fontion du string qui représente le nom du produit
  // cela permet d'accéder indirectement par exemple au prix du produit et autres attributs
  public Produit getProduit()
  {
  return Inventaire.getProduit(nomProduit);
  }
  
  // méthode qui retour une string avec les informations sur l'item, utilisée pour l'affichage
  public String afficherItem ()
  {
  return this.nomProduit+"\r"+this.qteDemandee+"\r"+getProduit().getPrix()*this.qteDemandee; // nom --- quantité commandée --- prix total pour l'item
  }
  

  
}