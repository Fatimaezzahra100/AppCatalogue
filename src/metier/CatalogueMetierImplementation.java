package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// implémentation GDBC 
// travail de Hibernate ça on écrit deux lignes
// tout ça c'est du Mapping objet relationnell
public class CatalogueMetierImplementation implements IcatalogueMetier {

	@Override
	public void addProduit(Produit p) {
		// pour ajouter un produit on a besoin d'une connexion
		Connection conn = SingletonConnexion.getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement
			// La requette qu'on va exécuter
			("insert into produits (REF_P, DESIGNATION, PRIX, QUANTITE) values (?,?,?,?)");
			ps.setString(1, p.getReference());
			ps.setString(2, p.getDesignation());
			ps.setDouble(3, p.getPrix());
			ps.setInt(4, p.getQuantite());
			// on exécute la requette
			ps.executeUpdate();
			// et on ferme l'objet
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Produit> listProduits() {
		List<Produit> prods = new ArrayList<Produit>();
		Connection conn = SingletonConnexion.getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement
			// La requette qu'on va exécuter
			("select * from produits");
			// on exécute la requette ON RECUPERE Là UN object resultset
			ResultSet rs = ps.executeQuery();

			// Mapping objet relationnell on va recuperer ligne par ligne les donnes de db

			while (rs.next()) {

				Produit p = new Produit();
				p.setReference(rs.getString("REF_P"));
				p.setDesignation(rs.getString("DESIGNATION"));
				p.setPrix(rs.getDouble("PRIX"));
				p.setQuantite(rs.getInt("QUANTITE"));
				// On ajoute les lignes dans la liste prods
				prods.add(p);
			}
			// et on ferme l'objet
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prods;

	}

	@Override
	public List<Produit> produitParMc(String mc) {
		List<Produit> prods = new ArrayList<Produit>();
		Connection conn = SingletonConnexion.getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement
			// La requette qu'on va exécuter
			("select * from produits where DESIGNATION = ? ");
			// on exécute la requette ON RECUPERE Là UN object resultset
			ps.setString(1, mc);
			ResultSet rs = ps.executeQuery();

			// on va recuperer ligne par ligne les donnes de db

			while (rs.next()) {
				Produit p = new Produit();
				p.setReference(rs.getString("REF_P"));
				p.setDesignation(rs.getString("DESIGNATION"));
				p.setPrix(rs.getDouble("PRIX"));
				p.setQuantite(rs.getInt("QUANTITE"));
				// On ajoute les lignes dans la liste prods
				prods.add(p);
			}
			// et on ferme l'objet
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prods;
	}

	@Override
	public Produit getProduit(String ref) {
		// ref ici en java = REF_P en java
		Produit p = null;
		Connection conn = SingletonConnexion.getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement
			// La requette qu'on va exécuter
			("select * from produits where REF_P = ? ");
			// on exécute la requette ON RECUPERE Là UN object resultset
			ps.setString(1, ref);
			ResultSet rs = ps.executeQuery();

			// Mapping objet relationnell on va recuperer ligne par ligne les donnes de db

			if (rs.next()) {
				p = new Produit();
				p.setReference(rs.getString("REF_P"));
				p.setDesignation(rs.getString("DESIGNATION"));
				p.setPrix(rs.getDouble("PRIX"));
				p.setQuantite(rs.getInt("QUANTITE"));
			}
			// et on ferme l'objet
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (p == null)
			throw new RuntimeException("produit" + ref + "produit introuvable");
		return p;
	}

	@Override
	public void updateProduit(Produit p) {

		// LE CODE DE INSERT
		Connection conn = SingletonConnexion.getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement
			// La requette qu'on va exécuter on va pas modifier la clé primaire
			("update produits set DESIGNATION= ?, PRIX = ?, QUANTITE=? where REF_P =?");
			ps.setString(1, p.getDesignation());
			ps.setDouble(2, p.getPrix());
			ps.setInt(3, p.getQuantite());
			ps.setString(4, p.getReference());
			// on exécute la requette
			ps.executeUpdate();
			// et on ferme l'objet
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void deleteProduit(String ref) {

		// LE CODE DE INSERT
		Connection conn = SingletonConnexion.getConnection();

		try {
			PreparedStatement ps = conn.prepareStatement
			// La requette qu'on va exécuter on va pas modifier la clé primaire
			("delete from produits where REF_P =?");
			ps.setString(1, ref);
			// on exécute la requette
			ps.executeUpdate();
			// et on ferme l'objet
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
