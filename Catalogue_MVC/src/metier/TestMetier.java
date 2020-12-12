package metier;

import java.util.List;

public class TestMetier {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// objet metier ici crée à l'aide d'une interface par la classe qui implémente
		// cette interface
		IcatalogueMetier metier = new CatalogueMetierImplementation();
		// test ajout des produits à la base des données
		// metier.addProduit(new Produit("REF05", "AA", 870, 3));
		// SI ON TEST CETTE LIGNE CA DONNE EXCEPTION
		// PILOTE GDBC EXISTE PAS IL FAUT L'AJOUTER OU PROJET = fichier.jar
		// metier.addProduit(new Produit("REF06", "BB", 890, 9));
		// metier.addProduit(new Produit("REF07", "CC", 990, 6));

		System.out.println("--------------------------------------------");

		// test liste produits
		List<Produit> prods = metier.listProduits();

		for (Produit produit : prods) {
			System.out.println(produit.getReference());
		}

		System.out.println("--------------------------------------------");
		// test liste produits par mot clé
		List<Produit> prods2 = metier.produitParMc("AA");

		for (Produit p : prods2) {
			System.out.println(p.getPrix());
		}

		System.out.println("--------------------------------------------");
		// test get produit par REF clé primaire
		try {
			Produit p = metier.getProduit("REF05");
			System.out.println(p.getDesignation());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println("--------------------------------------------");
		// test delete produit
		try {
			metier.deleteProduit("REF07");
			metier.deleteProduit("REF06");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		

		System.out.println("--------------------------------------------");
		// modifier produit d'abord je consulte
		// CETTE op vient géneralement après le consultation
		try {
			Produit p = metier.getProduit("PR01");
			p.setPrix(9999); // Attention ici on change la valeur juste en java
			metier.updateProduit(p);
			System.out.println(p.getPrix());

		} catch (Exception e) {
			System.out.println(e.getMessage());

		}

	}
}
