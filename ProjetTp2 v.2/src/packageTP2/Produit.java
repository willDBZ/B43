package packageTP2;

public class Produit 
{
// la classe Produit contient toutes les informations concernant la disponibilit� sur un produit dans les magasins.
	// tel que le prix et sa quantit�.
private int code;
private String nom;
private int qteStock;
private double prix;
private int points;

// des objets de la classe Produit sont cr��s pour les ajouter � l'inventaire des magasins

// constructeur de l'objet Produit
// quand on construit l'objet, il faut lui passer 5 param�tres.
public Produit ( int code, String nom,int qteStock, double prix, int points )
{
this.code = code;
this.qteStock=qteStock;
this.prix=prix;
this.points=points;
this.nom = nom;
}

// les m�thodes getteur pour obtenir les attributs priv�s de l'objet 
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

// la m�thode pour modifier la quantit� en stock. 
// Note: lorsqu'un produit est cr��, tous les 5 param�tres initialis�s ne pourront plus �tre modifi�s, 
	// sauf �videmment la quantit� en stock
public boolean modifierQteStock ( int qteAchetee)
{
	// cette m�thode est un boolean qui prend en param�tre la quantit� achet�e (un int)
	
	// si la quantit� en stock est suffisant, on retourne un bool "true" et on d�duit la quantit� restante dans le magasin.
if ( qteAchetee <= qteStock )
  {
  this.qteStock-=qteAchetee;
  return true;
  }
	// sinon, la quantit� restante dans le magasin reste inchang�e et on retourne un "false"
else
  return false;   
}
}