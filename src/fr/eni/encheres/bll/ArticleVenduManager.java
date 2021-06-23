package fr.eni.encheres.bll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;
import fr.eni.encheres.dal.ArticleVenduDAO;
import fr.eni.encheres.dal.DAOFactory;

public class ArticleVenduManager {

	private static ArticleVenduManager instance;
	private ArticleVenduDAO articleVenduDAO;
	
	private ArticleVenduManager() {
		articleVenduDAO= DAOFactory.getArticleVenduDAO();
	}
	
	public static synchronized ArticleVenduManager getInstance() {
		if(instance==null)
		{
			instance = new ArticleVenduManager();
		}
		return instance;
	}
	
	//Ajout d'un article en vente par l'utilisateur et renvoi une liste des tous les articles de l'utilisateur
	public int ajoutArticle(ArticleVendu article, int idvendeur, String categorie) throws BusinessException {
		int idNouvelleVente = 0;
		
		
		idNouvelleVente = articleVenduDAO.addArticleVendu(article, idvendeur, categorie);
		
		return idNouvelleVente;
	}
	


	//Suppression de l'article avant la date de fin  de l'enchère n'est pas terminée
	public  void cancelArticleVendu(String nomArticle, String pseudoUtilisateur) throws BusinessException {
		ArticleVendu art = articleVenduDAO.recupArticleBYNomEtPseudoVendeur(nomArticle, pseudoUtilisateur);
		Integer idArticleVendu = art.getNoArticle();
		
		if(LocalDate.now().compareTo(art.getDateDebutEncheres())<0) {
			articleVenduDAO.removeArticleVendu(idArticleVendu);
		}
	}
	
	//Récupération des catégories
	public Set<Categorie> getListCategories() throws BusinessException{
		Set<Categorie> lstCat = articleVenduDAO.getListCategorie();
		
		return lstCat;
		
	}
	
	//Récupération de tous les articles selon l'état de la vente
	public List<ArticleVendu> getListeEtatVente(String etatVente) throws BusinessException{
		List<ArticleVendu> lstArticle = new ArrayList<ArticleVendu>();
		int idEtatVente= transcriptEtatVenteToID(etatVente);

		lstArticle = articleVenduDAO.recupListeArticleParEtatVente(idEtatVente);
		
		return lstArticle;
		
	}
	
	//Récupération d'un article son nom et le pseudo du vendeur
	
	public ArticleVendu recupererArticleParNomArticleEtNomVendeur(String nomArticle, String pseudoVendeur) throws BusinessException{
		ArticleVendu articleAGenerer = articleVenduDAO.recupArticleBYNomEtPseudoVendeur(nomArticle, pseudoVendeur);
				
		return articleAGenerer;
	}
	
	//Récupération d'un article par son id
	public ArticleVendu getArticleByID(int idArticle) throws BusinessException{
		ArticleVendu article = articleVenduDAO.getArticleByIDArticle(idArticle);
		
		return article;
	}
	
	
	//Update etat vente
	
	public void updateEtatVenteArticle(int idArticle, String etatVente) throws BusinessException{
		int idEtatVente = transcriptEtatVenteToID(etatVente);
		
			articleVenduDAO.updateEtatVente(idArticle, idEtatVente);
	}
	
	//Récupération de l'id de l'article avec nom de l'article et pseudo du vendeur
	
	public int recuperationIdArticle(String nomArticle, String pseudoVendeur) throws BusinessException{
		int idArticle = 0;
		idArticle = articleVenduDAO.getIdArticleByNomEtPseudo(nomArticle, pseudoVendeur);
		
		return idArticle;
	}
	
	//Permet de transformer l'etat de vente en ID pour l'utiliser en base Créée= 1/En Cours = 2/Enchère Terminée = 3/ Retrait Effectué = 4
	private int transcriptEtatVenteToID(String etatVente) {
		int idEtat = 0;
		switch (etatVente) 
			{
				case "Créée":
					idEtat = 1;
					break;
					
				case "En cours":
					idEtat = 2;
					break;
					
				case "Enchère Terminée":
					idEtat = 3;
					break;
					
				case "Retrait Effectué":
					idEtat = 4;
					break;
	
			}
		return idEtat;
	}

}
