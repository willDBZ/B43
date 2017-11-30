package packageTP2;

public class Produit 
{
// la classe Produit contient toutes les informations concernant la disponibilité sur un produit dans les magasins.
	// tel que le prix et sa quantité.
private int code;
private String nom;
private int qteStock;
private double prix;
private int points;

// des objets de la classe Produit sont créés pour les ajouter à l'inventaire des magasins

// constructeur de l'objet Produit
// quand on construit l'objet, il faut lui passer 5 paramètres.
public Produit ( int code, String nom,int qteStock, double prix, int points )
{
this.code = code;
this.qteStock=qteStock;
this.prix=prix;
this.points=points;
this.nom = nom;
}

// les méthodes getteur pour obtenir les attributs privés de l'objet 
public String getNom()
{
return nom;
}
public int getCode ()
{
return code;
}
public int getQteStock()
{
return qteStock;
}
public double getPrix()
{
return prix;
}
public int getPoints()
{
return points;
}

// la méthode pour modifier la quantité en stock. 
// Note: lorsqu'un produit est créé, tous les 5 paramètres initialisés ne pourront plus être modifiés, 
	// sauf évidemment la quantité en stock
public boolean modifierQteStock ( int qteAchetee)
{
	// cette méthode est un boolean qui prend en paramètre la quantité achetée (un int)
	
	// si la quantité en stock est suffisant, on retourne un bool "true" et on déduit la quantité restante dans le magasin.
if ( qteAchetee <= qteStock )
  {
  this.qteStock-=qteAchetee;
  return true;
  }
	// sinon, la quantité restante dans le magasin reste inchangée et on retourne un "false"
else
  return false;   
}
}