package web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.CatalogueMetierImplementation;
import metier.IcatalogueMetier;
import metier.Produit;

@WebServlet("/ControleurServlet")
public class ControleurServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IcatalogueMetier metier;

	public ControleurServlet() {
		super();
		metier = new CatalogueMetierImplementation();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		// request.getRequestDispatcher("vueProduits.jsp").forward(request, response);
		// response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// PREMIERE CHOSE QUE LE CONTROLEUR VA FAIRE LORSQU'on L'APPELLE EST
		// D'INSTANCIER LE MODELE
		ProduitModel model = new ProduitModel();
		request.setAttribute("model", model);
		String action = request.getParameter("action");
		// et on test:
		if (action != null) {
			if (action.equals("chercher")) {
				// DANS LE MODEL ON VA STOCKER LES DONNEES SAISIES PAR UTILISATEUR
				model.setMotCle(request.getParameter("motCle"));
				// Couche métier
				List<Produit> produits = metier.produitParMc(model.getMotCle());
				// JE STOKE DANS REQUEST L'OBJET MODEL
				model.setProduits(produits);
			} else if (action.equals("delete")) {
				String ref = request.getParameter("ref");
				metier.deleteProduit(ref);
				model.setProduits(metier.listProduits());
			}
		}

		// je fais un forward vers la vue mais avant il faut stoker le model dans
		// request
		request.getRequestDispatcher("vueProduits.jsp").forward(request, response);

		// Je dois request dans une variable et passer cette dernière dans p de
		// PrintWriter
		PrintWriter p = response.getWriter();
		p.println();

	}

}
