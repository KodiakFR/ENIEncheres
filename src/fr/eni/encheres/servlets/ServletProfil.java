package fr.eni.encheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bll.UtilisateurManager;
import fr.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletProfil
 */
@WebServlet(
		urlPatterns = {"/Profil", "/ModifProfil",}
		)
public class ServletProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
					
		if (request.getServletPath().equals("/Profil")) {
			
			try {
				Boolean valideP = null;
				UtilisateurManager utilisateur = UtilisateurManager.getInstance();
				Utilisateur UtilisateurGeneral = (Utilisateur) request.getSession().getAttribute("Utilisateur");
				
				// Récupération du pseudo lorsqu'on clique sur le nom du vendeur. A finir lorsque la page accueil sera présente.
//				String pseudoRecup = request.getParameter("AREMPLLIR");
				String pseudoRecup = "benoit";


				// Si le pseudo récupéré est égal ) la session
				if(pseudoRecup.equals(UtilisateurGeneral.getPseudo())) {					
				Utilisateur UtilisateurInconnu= new Utilisateur(pseudoRecup);		
				UtilisateurInconnu = utilisateur.recuperationUtilisateur(UtilisateurInconnu);
				HttpSession session = request.getSession(true);
				session.setAttribute("UtilisateurInconnu", UtilisateurInconnu);
				RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Profil.jsp");
				rd.forward(request, response);
				}
			
				// si le pseudo n'est pas identique à la session alors afficher les infos de l'utilisateur inconnu
				else {
					Utilisateur UtilisateurInconnu = (Utilisateur) request.getSession().getAttribute("Utilisateur");
					System.out.println("pseudo sauvegardé c'est bon " + UtilisateurInconnu);			
					HttpSession session = request.getSession(true);
					session.setAttribute("UtilisateurInconnu", UtilisateurInconnu);
					valideP = true;
					session.setAttribute("valideP", valideP);
					RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/Profil.jsp");
					rd.forward(request, response);
					}
				
			} catch (NumberFormatException | BusinessException e) {
				e.printStackTrace();
			}

		}
		
		
		if (request.getServletPath().equals("/ModifProfil")) {
			Utilisateur Utilisateur = (Utilisateur) request.getSession().getAttribute("Utilisateur");
			System.out.println("pseudo sauvegardé c'est bon " + Utilisateur);
			

			HttpSession session = request.getSession(true);
			session.setAttribute("Utilisateur", Utilisateur);
			RequestDispatcher rd  = request.getRequestDispatcher("/WEB-INF/JSP/ModificationProfil.jsp");
			rd.forward(request, response);
			
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean validationMdp = false;
		Boolean validationMdpAc = false;
		try {
			Utilisateur Utilisateur = (Utilisateur) request.getSession().getAttribute("Utilisateur");
			System.out.println("pseudo sauvegardé c'est bon " + Utilisateur);
			UtilisateurManager utilisateur = UtilisateurManager.getInstance();
			
			String pseudo = request.getParameter("pseudo");
			String prenom = request.getParameter("prenom");
			String tel = request.getParameter("tel");
			String cp = request.getParameter("cp");
			String nom = request.getParameter("nom");
			String email = request.getParameter("email");
			String rue = request.getParameter("rue");
			String ville = request.getParameter("ville");
			String mdpAc = request.getParameter("mdp");
			String newMdp = request.getParameter("newmdp");
			String confirmMdp = request.getParameter("confirm");
			
			
			if(newMdp.equals(confirmMdp)) {
				if(mdpAc.equals(Utilisateur.getMotDePasse())) {
					
//		mettre un if pour chaque string pour utiliser la methode update de la bll
				
				}	
			
			}
			
			// methode verif si les deux nouveaux mdp saisie sont egaux
			else if(!newMdp.equals(confirmMdp)) {
				validationMdp = true;
				request.setAttribute("validationMdp", validationMdp);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ModificationProfil.jsp") ;
				rd.forward(request, response);
			}
			
			// Si mdp saisie n'est pas egal à la mdp session
			else if(!mdpAc.equals(Utilisateur.getMotDePasse())) {
				validationMdpAc = true;
				request.setAttribute("validationMdpAc", validationMdpAc);
				RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/JSP/ModificationProfil.jsp") ;
				rd.forward(request, response);
			}
			
		} catch (NumberFormatException | BusinessException e) {
			e.printStackTrace();
		}
		
		
	}

}
