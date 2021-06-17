package fr.eni.encheres.bll;

import java.util.List;

import fr.eni.encheres.bo.Utilisateur;
import fr.eni.encheres.dal.DAOFactory;
import fr.eni.encheres.dal.UtilisateurDAO;

public class UtilisateurManager {
	private UtilisateurDAO utilisateurDAO;

	
	
	// Méthode de BLL pour inserer une nouvelle inscription

	private static UtilisateurManager instance = null;
	
	
	public UtilisateurManager() throws BusinessException {
		utilisateurDAO = DAOFactory.getUtilisateurDAO();
	}
	
	public static synchronized UtilisateurManager getInstance() throws BusinessException {
		if(instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	// Méthode de BLL pour inserer une nouvelle inscription	
	public Boolean AjouterInscription(Utilisateur utilisateur) throws BusinessException {
		
		Boolean inscriValideP = false;
		Boolean inscriValideE = false;
		
		inscriValideP = validerPseudo(utilisateur.getPseudo());
		inscriValideE = validerMail(utilisateur.getEmail());
		
		
		if(inscriValideP == false & inscriValideE == false) {
			this.utilisateurDAO.insertInscription(utilisateur);
			return true;
		}
		else {
			return false;
		}
	}
	
	

	// Méthode qui vérifie que le pseudo n'est pas vide ou trop long
			private Boolean validerPseudo(String nomPseudo) {
				Boolean StatusValidation = false;
				if(nomPseudo == null | nomPseudo.trim().length() > 30) {
					StatusValidation = true;
				}
				
					List<String> listepseudo;
					try {
						listepseudo = this.utilisateurDAO.selectPseudo();
						if(listepseudo.contains(nomPseudo)) {
							StatusValidation = true;
						}
					} catch (BusinessException e) {
						e.printStackTrace();
					}
					return StatusValidation;
			}
	
	
	
	
	// Méthode qui vérifie si le mail n'est pas vide ou trop long
	private Boolean validerMail(String nomMail) {
		Boolean StatusValidation = false;
		if(nomMail == null | nomMail.trim().length() > 60) {
			StatusValidation = true;
		}
		List<String> listeEmail;
		try {
			listeEmail = this.utilisateurDAO.selectEmail();
			if(listeEmail.contains(nomMail)) {
				StatusValidation = true;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
		}
			return StatusValidation;
	}
	
	//Méthode de connection
			public Boolean connection(Utilisateur utilisateur) throws BusinessException{
				Utilisateur U;
				Boolean testConnection = false;
				System.out.println("je suis dans ma bll avant d'aller dans la DAL");
				testConnection = validerPseudo(utilisateur.getPseudo());
				System.out.println(testConnection);
				if (testConnection == true)
				{
					U = this.utilisateurDAO.SelectUser(utilisateur);
					if(utilisateur.getMotDePasse().equals(U.getMotDePasse())) {
						testConnection = true;
					}
					else {
						testConnection = false;
					}
				}
				return testConnection;
				
			}
			
	//méthode de récuperation d'un utilisateur
			
		public Utilisateur recuperationUtilisateur(Utilisateur utilisateur) throws BusinessException {
			Utilisateur U = null;
			U = this.utilisateurDAO.SelectUser(utilisateur);
			return U;
		}
	

	
}

	
