package metier;

import java.util.List;

public interface IcatalogueMetier {

	// le besoin

	public void addProduit(Produit p);

	public void deleteProduit(String ref);

	public List<Produit> listProduits();

	public List<Produit> produitParMc(String mc);

	public Produit getProduit(String ref);

	public void updateProduit(Produit p);

}
