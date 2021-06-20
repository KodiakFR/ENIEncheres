package fr.eni.encheres.dal;

import java.util.List;
import java.util.Set;

import fr.eni.encheres.bll.BusinessException;
import fr.eni.encheres.bo.ArticleVendu;
import fr.eni.encheres.bo.Categorie;

public interface ArticleVenduDAO {

	
	//Permet la récupération de l'id de la catégorie
	
	public Integer checkCategorie(String nomCategorie) throws BusinessException;

	
	//Permet de supprimer l'article de la plateforme
	//vendu ou supprimer par le vendeur ou l'admin
	
	public void removeArticleVendu(int idArticle) throws BusinessException;
	
	
	
	//Ajouter un article à la BDD lié à un utilisateur et une catégorie
	
	public void addArticleVendu(ArticleVendu article, int idVendeur, int idCategorie) throws BusinessException;
	
	
	//Récupere tous les articles d'un utilisateur par son id
	
	public List<ArticleVendu> recupListArticleUtilisateur(int idUtilisateur) throws BusinessException;
	
	
	
	//Récupère un article complet d'un utilisateur
	
	public ArticleVendu recupArticleBYNomEtIDVendeur(String nomArticle, int idVendeur) throws BusinessException;
	
	
	//Récupère les catégories existantes
	
	public Set<Categorie> getListCategorie() throws BusinessException;
	
	//Récupère une liste d'article par son état de vente
	
	public List<ArticleVendu> recupListeArticleParEtatVente(int etatVente) throws BusinessException;
	
	//Récupère un article par son id (utilisation pour les enchères principalement)
	
	public ArticleVendu getArticleByIDArticle(int idArticle) throws BusinessException;
	
}
